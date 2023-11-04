import java.util.concurrent.*;

public class Main3 {
    //use binary semaphore, which allow only one thread to get access
    private static final Semaphore mutex = new Semaphore(1);


   private static void nap(int millisecs) {
        try {
            Thread.sleep(millisecs);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void addProc(HighLevelDisplay d) {
        try{

            while(true){
                int randomNum = (int) (1000 + Math.random() * 501);
                Thread.sleep(randomNum);
                mutex.acquire();
                d.addRow("AAAAAAAAAAAA");
                mutex.release();


                randomNum = (int) (1000 + Math.random() * 501);
                Thread.sleep(randomNum);
                mutex.acquire();
                d.addRow("BBBBBBBBBBBB");
                mutex.release();


                randomNum = (int) (1000 + Math.random() * 501);
                Thread.sleep(randomNum);
                mutex.acquire();
                d.addRow("CCCCCCCCCCCC");
                mutex.release();
            }
        }catch (InterruptedException e){
            System.err.println(e.getMessage());
        }finally {
            mutex.release();
        }
   }

    private static void deleteProc(HighLevelDisplay d) {
        try{
            while(true){
                int randomNum = (int) (2000 + Math.random() * 2001);
                Thread.sleep(randomNum);
                mutex.acquire();
                d.deleteRow(0);
                mutex.release();


                randomNum = (int) (2000 + Math.random() * 2001);
                Thread.sleep(randomNum);
                mutex.acquire();
                d.deleteRow(2);
                mutex.release();
            }
        }catch (InterruptedException e){
            System.err.println(e.getMessage());
        } finally {
            mutex.release();
        }
    }

    public static void main(String [] args) {
	final HighLevelDisplay d = new JDisplay2();

	new Thread () {
	    public void run() {
		addProc(d);
	    }
	}.start();


	new Thread () {
	    public void run() {
		deleteProc(d);
	    }
	}.start();

    }
}