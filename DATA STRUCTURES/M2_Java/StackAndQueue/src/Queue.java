public class Queue {
    Node front = null;
    Node rear = null;

    public void enqueue(Node n){
        if (isEmpty()){
            front = n;
            rear = n;
        }else{
            rear.next = n;
            rear = rear.next;
        }
    }


    public Node dequeue(){
        if(isEmpty()){
            System.out.println("No element in the queue");
            return null;
        }

        Node result = front;
        //only 1 node in the queue
        if (rear == front){
            rear = null;
            front = null;
            return result;
        }
        //more than 1 node
        front = front.next;

        return result;
    }


    public Node poll(){
        if(isEmpty()){
            System.out.println("No element in the queue");
            return null;
        }

        return front;
    }


    public int size(){
        int count = 1;
        if(isEmpty()){
            return 0;
        }
        else{
            Node cur = front;
            while(cur != rear){
                cur = cur.next;
                count++;
            }
        }
        return count;
    }

    public void displayQueue(){
        Node cur = front;
        while(cur != null){
            System.out.print("->" + cur.data);
            cur = cur.next;
        }
        System.out.println();
    }

    public boolean isEmpty(){
        return front == null;
    }
}
