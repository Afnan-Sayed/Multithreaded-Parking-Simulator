package src.main.java.org.example;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Simulation
{
    private ParkingLot parkingLot;
    private File file;
    private List<Thread> carThreads ;
    private List<Car> carsList;
    private int[] gates;

    Simulation(String filepath)
    {
        //logger = new Logger();
        parkingLot = new ParkingLot();
        file = new File(filepath);
        carsList = new ArrayList<>();
        carThreads = new ArrayList<>();
        gates = new int[]{0, 0, 0};
    }

    public void run() throws InterruptedException
    {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null)
            {
                String[] input = line.split(", ");

                int carId = Integer.parseInt(input[1].split(" ")[1].trim());
                int parkTime = Integer.parseInt(input[3].split(" ")[1].trim());
                int gateId = Integer.parseInt(input[0].split(" ")[1].trim());
                int arriveTime = Integer.parseInt(input[2].split(" ")[1].trim());
                gates[gateId-1]++;
                carsList.add(new Car(gateId,parkTime,carId,arriveTime,parkingLot));


            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }



        for(Car car : carsList)
        {
            Thread carThread = new Thread(car);
            carThreads.add(carThread);
            carThread.start();
        }
    }

    public void endSimulation()
    {
        try
        {
            for (Thread carThread : carThreads)
            {
                carThread.join();
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        generateReport();
    }

    public void generateReport()
    {
        System.out.println("Simulation Complete");
        System.out.println("Total Cars Served: " + parkingLot.getTotalCarsServed());
        System.out.println("Current Cars in Parking: " + (4 - parkingLot.getAvailableSpots()));
        System.out.println("Details:");
        System.out.println("-Gate 1 served " + gates[0] + " Cars");
        System.out.println("-Gate 2 served " + gates[1] + " Cars");
        System.out.println("-Gate 3 served " + gates[2] + " Cars");
    }

}