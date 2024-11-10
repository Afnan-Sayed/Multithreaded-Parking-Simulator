package src.main.java.org.example;

import java.util.concurrent.Semaphore;
public class ParkingLot
{
    private Semaphore sem;
    private int totalSpots;
    private int carsServed;
    private Logger logger;
    public ParkingLot(int totalSpots, Logger logger)
    {
        this.totalSpots=totalSpots;
        this.sem= new Semaphore(4);
        this.logger = logger;
        this.carsServed= 0;
    }

    public boolean parkSpot(Car car) {
        try {
            if (sem.available())
            {
                sem.acquire();
                logger.logParking(car, getOccupiedSpots());
                carsServed++;
                return true;
            }
            else return false;

        }

        catch (InterruptedException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public void leaveSpot(Car car) {
        sem.release();
        logger.logLeaving(car, getOccupiedSpots());
    }

    public int getOccupiedSpots() {
        return totalSpots - 4;
    }

    public int getTotalCarsServed() {
        return carsServed;
    }
}
