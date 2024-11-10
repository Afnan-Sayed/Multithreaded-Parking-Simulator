package src.main.java.org.example;
//-feha attributes 3an el car zy arrival time, leave time, .. mmkn n7ot id ll car brdo
public class Car extends Thread
{
    static int idCounter = 0;

    int carID, arriveTime, parkTime, leaveTime;
    Gate gate;
    ParkingLot parking;
    boolean waiting;
    public Car(int arrive, int park, Gate gate, ParkingLot parking)
    {
        carID= idCounter++;
        arriveTime = arrive;
        parkTime = park;
        this.gate = gate;
        waiting = false;
        this.parking = parking;
        leaveTime = arriveTime + parkTime;
    }

    public void run()
    {
        try
        {
            Logger.logArrival(this);
            if (parking.parkSpot(this))
            {
                Thread.sleep(parkTime*1000);
                parking.leaveSpot(this);
            }
            else {
                System.out.println("car"+ carID+ "waiting for a spot");
            }
        }
        catch (InterruptedException e) {
            System.out.println("Error");
        }
    }
}