import java.util.concurrent.*;

public class Main3 {
    private static final Object lock = new Object();


    private static void nap(int millisecs) {
        try {
            Thread.sleep(millisecs);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void addProc(HighLevelDisplay d){
       try{
           while(true){
               //synchronized (lock){
                   Thread.sleep(1000);
                   d.addRow("AAAAAAAAAAAA");
                   Thread.sleep(1000);
                   d.addRow("BBBBBBBBBBBB");
                    Thread.sleep(1000);
                   d.addRow("CCCCCCCCCCCC");
                    Thread.sleep(1000);
                   d.addRow("DDDDDDDDDDDD");
               //}

           }
       }catch (InterruptedException e){
           System.err.println(e.getMessage());
       }

	// Add a sequence of addRow operations with short random naps.

   }

    private static void deleteProc(HighLevelDisplay d) {
        try{
            while(true){
                //synchronized (lock){
                    Thread.sleep(5000);
                    d.deleteRow(0);
                    Thread.sleep(3000);
                    d.deleteRow(2);
                //}

            }
        }catch (InterruptedException e){
            System.err.println(e.getMessage());
        }
	// Add a sequence of deletions of row 0 with short random naps.
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