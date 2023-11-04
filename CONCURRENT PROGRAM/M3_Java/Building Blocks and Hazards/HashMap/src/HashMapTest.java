import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class HashMapTest {

    private volatile boolean running = true;
    private final HashMap<String, Integer> people = new HashMap<String, Integer>();
	private final ReadWriteLock lock = new ReentrantReadWriteLock();


	private void addPerson() {
		//add a ReentrantReadWriteLock
		lock.writeLock().lock();
		try {
			people.put(RandomUtils.randomString(), RandomUtils.randomInteger());
		} finally {
			//close a ReentrantReadWriteLock
			lock.writeLock().unlock();
		}
	}

	private void deletePeople(String pattern) {
		//add a ReentrantReadWriteLock
		lock.writeLock().lock();
		try {
			Vector<String> hasPattern = new Vector<String>();
			for (String key : people.keySet()) {
				if (key.contains(pattern))
					hasPattern.add(key);
			}
			for (String key : hasPattern)
				people.remove(key);
		} finally {
			//close a ReentrantReadWriteLock
			lock.writeLock().unlock();
		}
	}

	private void printPeople() {
		//add a ReentrantReadWriteLock
		lock.readLock().lock();
		try {
			for (HashMap.Entry<String, Integer> entry : people.entrySet()) {
				System.out.println(entry.getKey() + ": " + entry.getValue());
			}
			System.out.println("-----------------------------------------");
		} finally {
			//close a ReentrantReadWriteLock
			lock.readLock().unlock();
		}
	}

    public void run() {
	// Start printer thread
	new Thread(new Runnable () {
		public void run() {
		    Thread.currentThread().setName("Printer");
		    while (running) {
			printPeople();
			try {
			    Thread.sleep(200);
			} catch (InterruptedException e) {
				//stop and exit thread
				Thread.currentThread().interrupt();
				return;
			}
		    }
		}
	    }).start();

	// Start deleter thread
	new Thread(new Runnable () {
		public void run() {
		    Thread.currentThread().setName("Deleter");
		    while (running) {
			deletePeople("a");
			try {
			    Thread.sleep(200);
			} catch (InterruptedException e) {
				//stop and exit thread
				Thread.currentThread().interrupt();
				return;
			}
		    }
		}
	    }).start();

	// This thread adds entries
	for (int i = 0; i < 100; i++) {
	    addPerson();
	    try {
		Thread.sleep(500);
	    } catch (InterruptedException e) {
			//stop and exit thread
			Thread.currentThread().interrupt();
			return;
		}
	}
	running = false;
    }

    public static void main(String[] args) {
	HashMapTest hm = new HashMapTest();
	hm.run();
    }

}
