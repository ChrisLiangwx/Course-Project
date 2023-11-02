public class HelloWorldThread implements Runnable{

    @Override
    public void run() {
        try{
            while(true){
                String info = Thread.currentThread().getName();
                long time = System.currentTimeMillis();
                System.out.println("Hello World! I'm thread<" + info + ">. The time is <" + time + ">");
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            String info = Thread.currentThread().getName();
            System.out.println(info + " is interrupted.");
        }
    }


}
