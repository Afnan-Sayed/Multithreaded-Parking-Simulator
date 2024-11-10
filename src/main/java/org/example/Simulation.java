package main.java.org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import main.java.org.example.ParkingLot;

//simulation class zy cli ele 3mlnaha "b3d ma n5las kolo"
//startSimulation()
//endSimulation()
//generateReport()
public class Simulation{
    private ParkingLot parkingLot;
    private File file;
    private List<Thread> gateThreads ;
    private Map<Integer, List<Car>> carsByGate;
    private Map<Integer, Integer> arrivalTimes;  
    private Map<Integer, Integer> parkingDurations; 

    Simulation(String filepath){
        //logger = new Logger();
        parkingLot = new ParkingLot();
        gateThreads = new ArrayList<>();
        file = new File(filepath);
        arrivalTimes = new HashMap<>();
        parkingDurations = new HashMap<>();
        this.carsByGate = new HashMap<>();
    }

    public void run(){
        BufferedReader reader;     
        
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] input = line.split(", ");

                int carId = Integer.parseInt(input[1].split(" ")[1].trim());
                int parkTime = Integer.parseInt(input[3].split(" ")[1].trim());
                int gateId = Integer.parseInt(input[0].split(" ")[1].trim());
                int arriveTime = Integer.parseInt(input[2].split(" ")[1].trim());
                
                Car car = new Car(gateId,parkTime,carId,arriveTime,parkingLot);

                carsByGate.putIfAbsent(gateId, new ArrayList<>());
                carsByGate.get(gateId).add(car);

                arrivalTimes.put(carId,arriveTime);
                parkingDurations.put(carId,parkTime);

                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    
        // Create threads for each gate
        for (Map.Entry<Integer, List<Car>> entry : carsByGate.entrySet()) {
            int gateId = entry.getKey();
            List<Car> carsAtGate = entry.getValue();

            // Create a thread for each gate
            Thread gateThread = new Thread(() -> {
                for (Car car : carsAtGate) {
                    car.run();
                }
            });
            gateThreads.add(gateThread);
        }

        // Start all gate threads
        for (Thread gateThread : gateThreads) {
            gateThread.start();
        }
    }

    public void endSimulation() {
        try {
            for (Thread gateThread : gateThreads) {
                gateThread.join(); // Wait for all gate threads to finish
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Generate report or any end-of-simulation logic here
        generateReport();
    }

    public void generateReport() {
        System.out.println("Simulation Complete");
        System.out.println("Total Cars Served: " + parkingLot.getTotalCarsServed());
    }

}