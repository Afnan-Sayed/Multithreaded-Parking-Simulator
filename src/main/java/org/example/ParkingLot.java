package src.main.java.org.example;

//ParkingLot class -> manage available spots and cars arrivinf and leaving
//-parkSpot() -> 7agz spot ll car ama tege
//-leaveSpot() -> nfadde spot lma el car tm4e
//-getAvailableSpots()
//-getTotalCarsServed()
//-getCurrentCars()
//-getDetails() -> anhe gates served kam car
//
//classes need this class
//logger:  logParkingStatus() hatetagha

import java.util.concurrent.*;

public class ParkingLot
{
    private Semaphore semaphore;
    private int totalCarsServed;
    private int currentCars;
    private Logger logger;

    public ParkingLot(int totalSpots, Logger logger)
    {
        this.semaphore =new Semaphore(totalSpots);
        this.totalCarsServed =0;
        this.currentCars=0;
        this.logger=logger;
    }

    public boolean parkSpot()
    {
        if (semaphore.acquire())
        {
            currentCars++;
            totalCarsServed++;
            logger.logParkingStatus(this);
            return true;
        }
        return false;
    }

    public void leaveSpot()
    {
        semaphore.release();
        currentCars--;
        logger.logParkingStatus(this);
    }

    public int getCurrentCars()
    {
        return currentCars;
    }

      public int getTotalCarsServed()
      {
        return totalCarsServed;
    }

    public void getDetails()
    {
        System.out.println("total served: " + totalCarsServed);
        System.out.println("currrent in parking: " + currentCars);
    }
}
