package main.java.org.example;

import java.io.IOException;
import java.nio.CharBuffer;

public class Car implements Runnable{

    private ParkingLot parkingLot;
    private int Id;
    private int gate;
    private int duration;
    private int occupiedCars;
    private int arrivalTime;

    public Car(int gate, int duration, int Id, int arrivalTime ,ParkingLot parkingLot){
        this.gate=gate;
        this.duration=duration;
        this.parkingLot = parkingLot;
        this.arrivalTime = arrivalTime;
        occupiedCars = 0;
    }

    @Override
    public void run() {
        int waitingTime = 0;
        while (!parkingLot.tryToPark()) {
            System.out.println("Car " + Id + " from Gate " + gate + " waiting for a spot.");
            waitingTime++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        if(waitingTime>0 && occupiedCars < 4){
            occupiedCars++;
            System.out.println("Car " + Id + " from Gate " + gate + " parked after waiting for " + waitingTime + " units of time.");
            try {
                Thread.sleep(duration * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }else if(waitingTime<=0 && occupiedCars < 4){
            occupiedCars++;
            System.out.println("Car " + Id + " from Gate " + gate + " has parked. (Parking Status: "+occupiedCars+"spots occupied)");
            try {
                Thread.sleep(duration * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }else if(waitingTime<=0 && occupiedCars >= 4){
            System.out.println("Car " + Id + " from Gate " + gate + " waiting for a spot.");
        }
        occupiedCars--;
        System.out.println("Car " + Id + " from Gate " + gate + " left after duration "+ duration+" units of time (Parking Status: "+occupiedCars+"spots occupied)");
        parkingLot.leaveSpot();
    }

    public long getArriveTime() {
        return arrivalTime;
    }

}