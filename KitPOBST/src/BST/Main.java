package BST;
import BST.BSTree;
import BST.Node;
import java.util.Comparator;
import java.util.Random;
import java.util.function.Consumer;

public class Main {
    static Random random= new Random();
    public static StringBuilder genStr(){
        final String letters = "abcdefghijklmnopqrstuvwxyz";
        //final String CHAR_UPPER = letters.toUpperCase();
        final String number ="0123456789";
        final String data = letters + number /*CHAR_UPPER*/;
        int length=random.nextInt(5);
        length+=5;
        StringBuilder sb = new StringBuilder(length);
        for (int i=0;i<length;i++) {
            int rnd = random.nextInt(data.length());
            char rndChar = data.charAt(rnd);
            sb.append(rndChar);
        }
        return sb;
    }
    public static void main(String[] args) {
        BSTree<Integer, Integer> Tree = new BSTree();
        BSTree<StringBuilder, StringBuilder> TreeString = new BSTree();
        Tree.addToEnd(1,1);
        Tree.addToEnd(2,2);
        Tree.addToEnd(3,3);
        Tree.addToEnd(0,0);
        Tree.addToEnd(-1,-1);
        Tree.addToEnd(-7,-7);
        Tree.print();
        Tree.remove(1);
        Tree.print();
        Tree.addToStart(1,1);
        Tree.print();
        Tree.addToEnd(-5,-5);
        Tree.addToEnd(-8,-8);
        Tree.print();
        Tree.getElemTreeByIndex(3);
        Tree.deleteElemTreeByIndex(5);
        Tree.print();
        Tree.addToTreeByIndex(4,4,5);
        Tree.print();
        Tree.addToTreeByIndex(5,5,5);
        Tree.print();
        Tree.addToTreeByIndex(-2,-2,2);
        Tree.print();
        long start = System.nanoTime();
        System.out.println("Балансировка: ");
        Tree.balanceTree();
        long finish = System.nanoTime();
        long timeConsumedMillis = finish - start;
        Tree.print();
        System.out.println("Время балансировки: " + String.format("%.10f",(float)timeConsumedMillis/1000000000) + " секунд");
        System.out.print("forEach (Обход N->L->R): ");
        Tree.forEach((vv)->{ System.out.print(vv+ " "); });
        System.out.println("");
        System.out.println("Тестирование структуры со строками: ");
        TreeString.addToStart(genStr(), genStr());
        TreeString.addToEnd(genStr(), genStr());
        TreeString.addToEnd(genStr(), genStr());
        TreeString.print();
        TreeString.deleteElemTreeByIndex(2);
        TreeString.print();
        TreeString.getElemTreeByIndex(1);
        BSTree<Integer, Integer> Tree2 = new BSTree();
        Tree2.addToEnd(1,1);
        Tree2.addToEnd(2,2);
        Tree2.addToEnd(3,3);
        Tree2.addToEnd(0,0);
        Tree2.addToEnd(9,9);
        Tree2.addToEnd(10,10);
        Tree2.addToEnd(11,11);
        Tree2.addToEnd(12,12);
        Tree2.addToEnd(13,13);
        Tree2.addToEnd(14,14);
        Tree2.addToEnd(15,15);
        start = System.nanoTime();
        System.out.println("Балансировка: ");
        Tree2.balanceTree();
        finish = System.nanoTime();
        timeConsumedMillis = finish - start;
        Tree2.print();
        System.out.println("Время балансировки: " + String.format("%.10f",(float)timeConsumedMillis/1000000000) + " секунд");
    }
}
