import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue<Message> queue;
    private int id;

    public Consumer(BlockingQueue<Message> q, int n) {
		queue = q;
		id = n;
    }
    
    public void run() {
		Message msg = null;
		int count = 0;
		while(true) {
			try {
			msg = queue.take(); // Get a message from the queue
			count++;
			RandomUtils.print("Consumed " + msg.get(), id);
			Thread.sleep(RandomUtils.randomInteger());
			} catch (InterruptedException e) {
//				count--;
				RandomUtils.print("Messages received: " + count, id);
//				e.printStackTrace();
			}
		}

//		//received a stop message
//		//put the message back to the queue since other consumer will also need it
//		Message msgStop = new Message("stop");
//		try {
//			queue.put(msgStop);
//		} catch (InterruptedException e) {
//			throw new RuntimeException(e);
//		}
//		// Don't count the "stop" message
//		count--;
//		RandomUtils.print("Messages received: " + count, id);
    }
}
