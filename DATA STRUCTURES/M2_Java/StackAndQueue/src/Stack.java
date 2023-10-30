public class Stack {
    private Node top;

    public Stack() {
        top = null;
    }

    public void push(Node newNode) {
        newNode.next = top;
        top = newNode;
    }

    public Node pop() {
        if (isEmpty()) {
            return null;
        } else {
            Node temp = top;
            top = top.next;
            return temp;
        }
    }

    public Node peek() {
        if (isEmpty()) {
            return null;
        } else {
            return top;
        }
    }

    public int size() {
        int count = 0;
        Node current = top;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void displayStack() {
        Node cur = top;
        while (cur != null) {
            System.out.print("->" + cur.data);
            cur = cur.next;
        }
    }
}
