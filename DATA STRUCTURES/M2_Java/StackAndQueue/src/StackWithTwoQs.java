public class StackWithTwoQs {
    Queue queueOne =  new Queue(); // your implemented Queue class

    Queue queueTwo = new Queue(); // your implemented Queue class


    public void push(Node n) {
        while(!queueOne.isEmpty()){
            queueTwo.enqueue(queueOne.dequeue());
        }
        queueOne.enqueue(n);
        while(!queueTwo.isEmpty()){
            queueOne.enqueue(queueTwo.dequeue());
        }
    }

    public Node pop() {
        return queueOne.dequeue();
    }

    public Node peek() {
        return queueOne.poll();
    }

    public int size(){
        return queueOne.size();
    }
}
