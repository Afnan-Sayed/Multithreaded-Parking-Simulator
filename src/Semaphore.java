//semaphore class -> controls access to available slots
//acquire()
//release()
//available() -> checks available slots
//
//classes need this class
//parkingLot: 34an y-control el slots lw full fa mafe4 cars hated5ol l7ad ama tm4e

public class Semaphore {
    private static int TOTAL_SLOTS = 4;
    public synchronized void acquire() throws InterruptedException{
        while(TOTAL_SLOTS<=0){
            wait();
        }
        TOTAL_SLOTS--;
    }

    public synchronized void release(){
        TOTAL_SLOTS++;
        notify();
    }

    public synchronized boolean available(){
        return TOTAL_SLOTS>0;
    }
}
