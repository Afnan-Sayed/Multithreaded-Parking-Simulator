package main.java.org.example;
public class ParkingLot
{
    private Semaphore spots;
    private int carsServed;
    //private Logger logger;
    public ParkingLot()//Logger logger as a parameter
    {
        this.spots= new Semaphore();
        //this.logger = logger;
        this.carsServed=0;
    }

    public boolean tryToPark() {
        try {
            if (spots.available())
            {
                spots.acquire();
                synchronized(this){
                    //logger.logParking(car, getOccupiedSpots());
                    carsServed++;
                    return true;
                }
                
            }
            else{
                
            }

        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public void leaveSpot() {
        spots.release();
        //logger.logLeaving(car, getOccupiedSpots());
    }

    public int getTotalCarsServed() {
        return carsServed;
    }
}
