package main.java.org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    public static void main(String[] args)
    {   

        Simulation simulation = new Simulation("D:\\test.txt");
        simulation.run();
        simulation.endSimulation();
    }
}