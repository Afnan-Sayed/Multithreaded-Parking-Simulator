public class Car {
    static int idCounter = 0;

    int id, arriveTime, parkTime, leaveTime, gate;
    Parking parking;
    boolean waiting;
    public Car(int arrive, int park, int gate, Parking parking){
        id = idCounter++;
        arriveTime = arrive;
        parkTime = park;
        this.gate = gate;
        waiting = false;
        this.parking = parking;
        leaveTime = arriveTime + parkTime;
    }

    public void run() {
        try {
            Thread.sleep(arriveTime * 1000);
            parking.parkSpot(id, gate, parkTime);
        } catch (InterruptedException e) {
            System.out.println("Error");
        }
    }
}