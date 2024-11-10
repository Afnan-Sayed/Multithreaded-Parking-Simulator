package main.java.org.example;

public class Semaphore {
    private static int TOTAL_SLOTS = 4;
    public synchronized boolean acquire() throws InterruptedException
    {
        while(TOTAL_SLOTS<=0){
            wait();
        }
        TOTAL_SLOTS--;
        return true;
    }

    public synchronized void release(){
        TOTAL_SLOTS++;
        notify();
    }

    public synchronized boolean available(){
        return TOTAL_SLOTS>0;
    }
}