package src.main.java.org.example;
public class Car implements Runnable

{    private ParkingLot parkingLot;
    private int Id;
    private int gate;
    private int duration;
    private int occupiedCars;
    private int arrivalTime;

    public Car(int gate, int duration, int Id, int arrivalTime ,ParkingLot parkingLot)
    {
        this.gate=gate;
        this.duration=duration;
        this.parkingLot = parkingLot;
        this.arrivalTime = arrivalTime;
        this.Id = Id;
        occupiedCars = 0;
    }
    @Override
    public void run()
    {
        try
        {
            Thread.sleep(getArriveTime() * 1000L);
        }

        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }

        long waitStartTime = System.currentTimeMillis();
        long waitingTime = 0;
        System.out.println("Car " + Id + " from Gate " + gate + " arrived at time " + arrivalTime);
        if(!parkingLot.readyToPark())
        {
            System.out.println("Car " + Id + " from Gate " + gate + " waiting for a spot.");
            while (!parkingLot.readyToPark())
            {
                waitingTime = (System.currentTimeMillis() - waitStartTime) / 1000;
                try
                {
                    Thread.sleep(1000L);
                }
                catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                }
            }
        }

        if(waitingTime>0)
        {
            System.out.println("Car " + Id + " from Gate " + gate +
                    " parked after waiting for " + waitingTime + " units of time.");
        }
        else if(waitingTime<=0)
        {
            System.out.println("Car " + Id + " from Gate " + gate + " has parked. (Parking Status: "+
                    (4 - parkingLot.getAvailableSpots())+" spots occupied)");
        }
        try
        {
            Thread.sleep(duration * 1000L);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
        System.out.println("Car " + Id + " from Gate " + gate + " left after duration "+
                duration+" units of time (Parking Status: "+ (4 - parkingLot.getAvailableSpots())+
                " spots occupied)");
        parkingLot.leaveSpot();
    }

    public long getArriveTime()
    {
        return arrivalTime;
    }
}