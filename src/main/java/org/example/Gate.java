package src.main.java.org.example;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Gate
{
    private int gateNumber;
    private Queue<Car> carQueue;
    public Gate(int gateNumber)
    {
        this.gateNumber = gateNumber;
        this.carQueue = new LinkedList<>();
    }

    public void addCar(Car car) {
        carQueue.add(car);

    }

    public Queue<Car> getCars() {
        return carQueue;
    }

    public boolean hasCars() {
        return !carQueue.isEmpty();
    }


    public int getGateNumber() {
        return gateNumber;
    }
}