public class TrafficController {
    boolean isAvailable = true;
    public synchronized void enterLeft() {
        //if the bridge is blocked, then wait until it is available
        while(!isAvailable){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //if it is available, then cross the bridge and occupy it(set it to unavailable)
        isAvailable = false;
    }
    public synchronized void enterRight() {
        //if the bridge is blocked, then wait until it is available
        while(!isAvailable){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //if it is available, then cross the bridge and occupy it(set it to unavailable)
        isAvailable = false;
    }
    public synchronized void leaveLeft() {
        //the car has exited the bridge, notify other threads and set it to available
        isAvailable = true;
        notifyAll();
    }
    public synchronized void leaveRight() {
        //the car has exited the bridge, notify other threads and set it to available
        isAvailable = true;
        notifyAll();
    }

}