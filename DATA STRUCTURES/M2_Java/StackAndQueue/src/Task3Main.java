public class Task3Main {
    public static void main(String[] args) {
        Queue queue = new Queue();


        //add nodes
        queue.enqueue(new Node(1));
        queue.enqueue(new Node(8));
        queue.enqueue(new Node(5));
        queue.enqueue(new Node(9));

        System.out.println("Queue size: " + queue.size());



        //delete nodes
        Node dequeuedNode1 = queue.dequeue();
        System.out.println("Dequeued: " + dequeuedNode1.data);
        queue.displayQueue();


        System.out.println("poll: " + queue.poll().data);
        queue.displayQueue();


    }

}
