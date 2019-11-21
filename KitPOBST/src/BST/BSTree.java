package BST;
import BST.Main;
import java.util.Queue;
import java.util.LinkedList;
import java.util.*;

public class BSTree<T1 extends Comparable, T2 extends Comparable> {
    private Node<T1, T2> root ;
    private int length;

    BSTree() {
        root = null;
        length = 0;
    }

    public Node getRoot() {
        return this.root;
    }

    public int height(Node<T1,T2> node)
    {
        return (node != null) ? (node.height) : 0;
    }

    public int bfactor(Node<T1,T2> node) // Балансировка высот
    {
        return height(node.right)-height(node.left);
    }

    public void fixheight(Node<T1,T2> node) // Установка корректного значения высоты поддерева
    {
        int hl = height(node.left);
        int hr = height(node.right);
        node.height = (hl>hr?hl:hr)+1;
    }

    Node<T1,T2> rotateright(Node<T1,T2> p) // правый поворот вокруг p
    {
        if (p == root) {
            Node<T1, T2> q = p.left;
            p.left = q.right;
            q.right = p;
            p.parent = q;
            root = q;
            fixheight(p);
            fixheight(q);
            return q;
        }
        else {
            Node<T1, T2> q = p.left;
            if (p.parent.right == p) {
                p.parent.right = q;
            } else {
                p.parent.left = q;
            }
            p.left = q.right;
            q.right = p;
            q.parent = p.parent;
            fixheight(p);
            fixheight(q);
            return q;
        }
    }

    Node<T1,T2> rotateleft(Node<T1,T2> q) // левый поворот вокруг q
    {
        if (q==root) {
            Node<T1, T2> p = q.right;
            q.right = p.left;
            p.left = q;
            q.parent = p;
            root=p;
            fixheight(q);
            fixheight(p);
            return p;
        }
        else {
            Node<T1, T2> p = q.right;
            if (q.parent.right == q) {
                q.parent.right = p;
            } else {
                q.parent.left = p;
            }
            q.right = p.left;
            p.left = q;
            p.parent = q.parent;
            fixheight(q);
            fixheight(p);
            return p;
        }
    }

    Node<T1,T2> balance(Node<T1,T2> p) // балансировка узла p
    {
        fixheight(p);
        if( bfactor(p)>=2 )
        {
            if( bfactor(p.right) < 0 )
                p.right = rotateright(p.right);
            return rotateleft(p);
        }
        if( bfactor(p)<=-2 )
        {
            if( bfactor(p.left) > 0  )
                p.left = rotateleft(p.left);
            return rotateright(p);
        }
        return p; // балансировка не нужна
    }

    public void addToStart(T1 key, T2 value) {  // Добавление в начало дерева по ключу
        Node<T1, T2> newNode = new Node<T1,T2>(key, value);
        if (root == null) {
            root = newNode;
        }
        else {
            if (root.key.compareTo(key) > 0) {
                newNode.right = root;
                newNode.left = root.left;
                root.left.parent = newNode;
                root.parent = newNode;
                root.left = null;
                root = newNode;
            }
            else {
                newNode.left = root;
                newNode.right = root.right;
                root.right.parent = newNode;
                root.parent = newNode;
                root.right = null;
                root = newNode;
            }
        }
        length++;
    }

    public void addToTreeByIndex(T1 key, T2 value, int index) { // Добавление узла в дерево по логическому номеру
        ArrayList<Node<T1,T2>> listOfNodes = new ArrayList();
        ArrayList<Node<T1,T2>> array = recPreOrder(root, 0, index, listOfNodes);
        Node<T1,T2> newNode = new Node(key, value);
        if (index==0) {
            addToStart(key, value);
        }
        if (index < 0 || index > array.size()){
            System.out.println("Такого элемента не существует");
        }
        else {
            Node<T1,T2> Node = array.get(index);
            if (Node.key.compareTo(key) > 0) {
                if (Node == Node.parent.right) {
                    Node.parent.right = newNode;
                }
                else {
                    Node.parent.left = newNode;
                }
                newNode.parent = Node.parent;
                newNode.right = Node;
                //newNode.left = Node.left;
                Node.parent = newNode;
                //Node.left = null;
            }
            else {
                if (Node == Node.parent.right) {
                    Node.parent.right = newNode;
                }
                else {
                    Node.parent.left = newNode;
                }
                newNode.parent = Node.parent;
                newNode.left = Node;
                //newNode.right = Node.right;
                Node.parent = newNode;
                //Node.right = null;
            }
        }
        length++;
    }

    public void addToEnd(T1 k, T2 v) {  // Добавление в конец дерева по ключу
        Node<T1, T2> x = root, y = null;
        while (x != null) {
            int cmp = k.compareTo(x.key);
            if (cmp == 0) {
                x.value = v;
                return;
            } else {
                y = x;
                if (cmp < 0) {
                    x = x.left;
                } else {
                    x = x.right;
                }
            }
        }
        Node<T1, T2> newNode = new Node<T1, T2>(k, v);
        if (y == null) {
            root = newNode;
        } else {
            if (k.compareTo(y.key) < 0) {
                y.left = newNode;
                newNode.parent = y;
            } else {
                y.right = newNode;
                newNode.parent = y;
            }
        }
        length++;
    }

    public void getElemTreeByIndex(int index){
        ArrayList<Node<T1,T2>> listOfNodes = new ArrayList();
        ArrayList<Node<T1,T2>> array = recPreOrder(root, 0, index, listOfNodes);
        if (array == null) {
            System.out.println("Узлы не были добавлены в массив");
        }
        else {
            if (index > array.size() || index <= 0) {
                System.out.println("Такого узла не существует");
            }
            else {
                System.out.println("Ключ искомого узла: " + array.get(index).getKey() + " , значение искомого узла: " + array.get(index).getValue());
            }
        }
    }

    public void deleteElemTreeByIndex(int index){
        ArrayList<Node<T1,T2>> listOfNodes = new ArrayList();
        ArrayList<Node<T1,T2>> array = recPreOrder(root, 0, index, listOfNodes);
        if (array == null) {
            System.out.println("Узлы не были добавлены в массив");
        }
        else {
            if (index < array.size() && index >= 0) {
                Node<T1, T2> deleteNode = array.get(index);
                remove(deleteNode.getKey());
                System.out.println("Узел был успешно удалён из дерева");
            }
            else {
                System.out.println("Такого узла не существует");
            }
        }
    }

    ArrayList<Node<T1,T2>> recPreOrder(Node<T1,T2> node, int index, int logindex, ArrayList<Node<T1,T2>> listOfNodes){
            listOfNodes.add(node);
            if (node.left != null) recPreOrder(node.left, ++index, logindex, listOfNodes);
            if (node.right != null) recPreOrder(node.right, ++index, logindex, listOfNodes);
            return listOfNodes;
    }

    public T2 get(T1 k) { // Получение значения по ключу
        Node<T1, T2> x = root;
        while (x != null) {
            int cmp = k.compareTo(x.key);
            if (cmp == 0) {
                return x.value;
            }
            if (cmp < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        return null;
    }

    public void remove(T1 k) { // Удаление из дерева по ключу
        Node<T1, T2> x = root, y = null;
        while (x != null) {
            int cmp = k.compareTo(x.key);
            if (cmp == 0) {
                break;
            } else {
                y = x;
                if (cmp < 0) {
                    x = x.left;
                } else {
                    x = x.right;
                }
            }
        }
        if (x == null) {
            return;
        }
        if (x.right == null) {
            if (y == null) {
                root = x.left;
            } else {
                if (x == y.left) {
                    y.left = x.left;
                } else {
                    y.right = x.left;
                }
            }
        } else {
            Node<T1, T2> leftMost = x.right;
            y = null;
            while (leftMost.left != null) {
                y = leftMost;
                leftMost = leftMost.left;
            }
            if (y != null) {
                y.left = leftMost.right;
            } else {
                x.right = leftMost.right;
            }
            x.key = leftMost.key;
            x.value = leftMost.value;
        }
    }

    public void print() {
        print("", this.root, false);
    }

    public void print(String prefix, Node<T1,T2> n, boolean isLeft) {
        if (n != null) {
            if (n == root) {
                System.out.println("H-- " + n.key);
            }
            else {
                System.out.println(prefix + (isLeft ? "L-- " : "R-- ") + n.key);
            }
            print(prefix + (isLeft ? "|   " : "    "), n.left, true);
            print(prefix + (isLeft ? "|   " : "    "), n.right, false);
        }
    }

    public void forEach(Todo fun) {
        ArrayList<Node<T1,T2>> listOfNodes = new ArrayList();
        ArrayList<Node<T1,T2>> array = recPreOrder(root, 0, 0, listOfNodes);
        for (int i = 0; i<array.size(); i++) {
            Node<T1, T2> curNode = array.get(i);
            fun.todo((Comparable) curNode.value);
        }
    }

    void balanceNodes(Node<T1,T2> root) { // Установка высот каждого узла дерева (Обратный обход дерева)
        if (root.left != null) balanceNodes(root.left);
        if (root.right != null) balanceNodes(root.right);
        balance(root);
    }

    void setHeight(Node<T1,T2> root) {
        fixheight(root);
        if (root.left != null) setHeight(root.left);
        if (root.right != null) setHeight(root.right);
    }

    void balanceTree() { // Балансировка всего дерева
        Node<T1,T2> root = this.root;
        setHeight(root);
        balanceNodes(root);
        System.out.println("Высота сбалансированного дерева: " + root.height);
    }
}
