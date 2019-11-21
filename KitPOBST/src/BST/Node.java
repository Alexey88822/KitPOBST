package BST;
import BST.Node;
import java.io.*;

public class Node<T1 extends Comparable, T2 extends Comparable> {
    T1 key; // Значение ключа узла
    T2 value; // Значение, хранимое в узле
    int height; // Высота поддерева данного узла
    Node<T1, T2> left, right, parent;

    Node (T1 key, T2 value) {
        this.key = key;
        this.value = value;
        this.height = 0;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    Node () {
        this.key = null;
        this.value = null;
        this.height = 0;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public T1 getKey() {
       return this.key;
    }

    public T2 getValue() {
        return this.value;
    }

    public Node getRight() {
        return this.right;
    }

    public Node getLeft() {
        return this.left;
    }

    public Node getParent() { return this.parent; }
}
