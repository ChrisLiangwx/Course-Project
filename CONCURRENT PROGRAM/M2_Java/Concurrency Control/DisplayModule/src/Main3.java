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
                   int randomNum = (int) (500 + Math.random() * 501);
                   Thread.sleep(randomNum);
                   d.addRow("AAAAAAAAAAAA");


                   randomNum = (int) (500 + Math.random() * 501);
                   Thread.sleep(randomNum);
                   d.addRow("BBBBBBBBBBBB");

                   randomNum = (int) (500 + Math.random() * 501);
                   Thread.sleep(randomNum);
                   d.addRow("CCCCCCCCCCCC");


                   randomNum = (int) (500 + Math.random() * 501);
                   Thread.sleep(randomNum);
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
                    int randomNum = (int) (3000 + Math.random() * 2001);
                    Thread.sleep(randomNum);
                    d.deleteRow(0);

                    randomNum = (int) (2000 + Math.random() * 1001);
                    Thread.sleep(randomNum);
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