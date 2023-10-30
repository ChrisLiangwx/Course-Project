// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Task1Main {
    public static void main(String[] args) {
        Node n1 = new Node<>(3);
        Node n2 = new Node<>(6);
        Node n3 = new Node<>(7);

        //add nodes
        Stack s1 = new Stack();
        s1.push(n1);
        s1.push(n2);
        s1.push(n3);


        System.out.println("Stack elements:");
        s1.displayStack();

        //peek
        Node topNode = s1.peek();
        System.out.println("Top element: " + topNode.data);

        //show size
        int stackSize = s1.size();
        System.out.println("Stack size: " + stackSize);

        //delete nodes
        System.out.println("Stack elements after pop:");
        s1.pop();
        s1.displayStack();

    }
}