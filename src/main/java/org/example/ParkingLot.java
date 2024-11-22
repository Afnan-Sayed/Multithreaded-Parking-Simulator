package src.main.java.org.example;
public class ParkingLot
{
    private Semaphore semSpots;
    private int NoOfcarsServed;
    //private Logger logger;

    public ParkingLot()
    {
        this.semSpots= new Semaphore();
        //this.logger = logger;
        this.NoOfcarsServed=0;
    }

    public boolean readyToPark()
    {
        try
        {
            if(semSpots.acquire())
            {
                NoOfcarsServed++;
                return true;
            }
            return semSpots.acquire();
        }

        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public void leaveSpot()
    {
        semSpots.release();
        //logger.logLeaving(car, getOccupiedSpots());
    }
    public int getAvailableSpots()
    {
        return semSpots.availablePermits();
    }
    public int getTotalCarsServed()
    {
        return NoOfcarsServed;
    }
}