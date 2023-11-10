import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueue {
    private static int n_ids;

    public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Please input the number of producer");
		int producerNum = scanner.nextInt();

		System.out.println("Please input the number of consumer");
		int consumerNum = scanner.nextInt();
		scanner.close();


		BlockingQueue<Message> queue = new LinkedBlockingQueue<>(10);
		List<Producer> producerLinkedList = Collections.synchronizedList(new LinkedList<>());
		for(int i = 0; i < producerNum; i++){
			//create producer
			Producer p = new Producer(queue, n_ids++);
			Thread tp = new Thread(p);
			tp.start();
			producerLinkedList.add(p);
		}

		List<Thread> consumerLinkedList = Collections.synchronizedList(new LinkedList<>());
		for(int i = 0; i < consumerNum; i++){
			//create producer
			Consumer c = new Consumer(queue, n_ids++);
			Thread tc = new Thread(c);
			tc.start();
			consumerLinkedList.add(tc);
		}


//		Producer p1 = new Producer(queue, n_ids++);
//		Producer p2 = new Producer(queue, n_ids++);
//		Consumer c1 = new Consumer(queue, n_ids++);
//		Consumer c2 = new Consumer(queue, n_ids++);
//
//		Thread tp1 = new Thread(p1);
//		tp1.start();
//		Thread tp2 = new Thread(p2);
//		tp2.start();
//		Thread tc1 = new Thread(c1);
//		tc1.start();
//		Thread tc2 = new Thread(c2);
//		tc2.start();


		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

//			p1.stop();
//			p2.stop();
		for(Producer p : producerLinkedList){
			p.stop();;
		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(Thread tc : consumerLinkedList){
			tc.interrupt();
		}
//		tc1.interrupt();
//		tc2.interrupt();

		}

}
