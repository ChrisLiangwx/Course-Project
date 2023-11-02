import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MainThread implements Runnable{
    static ConcurrentLinkedQueue<Thread> allThreads= new ConcurrentLinkedQueue<>();
    public static void takeOrder(){
        Scanner sc = new Scanner(System.in);
        String order = sc.nextLine();
        if(order.equals("c")){
            //Order c: stop all threads and exit
            for(Thread thread : allThreads){
                thread.interrupt();
            }
            //stop the program
            sc.close();
            System.exit(0);
        }else if(order.equals("a")){
            //Order a: create a new thread
            Thread t = new Thread(new HelloWorldThread());
            t.start();
            allThreads.add(t);
        }else if(order.startsWith("b ")){
            //Order b: stop a thread with exact number
            String numbers = order.replaceAll("\\D+", "");
            for(Thread thread : allThreads){
                if(thread.getName().equals("Thread-" + numbers)){
                    thread.interrupt();
                    allThreads.remove(thread);
                    break;
                }
            }
        }else{
            System.out.println("Invalid Order!!");
        }
    }

    @Override
    public void run() {
        while(true){
            takeOrder();
        }

    }
    public static void main(String args[]){
        System.out.println("\"Here are your options:\n" +
                "\n" +
                "a - Create a new thread\n" +
                "b - Stop a given thread (e.g. \"b 2\" kills thread 2)\n" +
                "c - Stop all threads and exit this program.\"");
        Thread main = new Thread(new MainThread());
        main.start();
    }


}
