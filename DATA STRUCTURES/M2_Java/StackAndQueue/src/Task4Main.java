public class Task4Main {
    public static void main(String[] args) {
        StackWithTwoQs stack = new StackWithTwoQs();

        //add nodes
        stack.push(new Node(1));
        stack.push(new Node(2));
        stack.push(new Node(3));


        //size of the stack
        System.out.println("Stack size: " + stack.size());



        //delete from stack
        Node poppedNode = stack.pop();
        System.out.println("Popped element: " + poppedNode.data);

        //peek
        System.out.println("Peek: " + stack.peek().data);

        System.out.println("Stack size: " + stack.size());


    }
}
