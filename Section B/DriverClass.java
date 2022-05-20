import java.time.Instant;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class DriverClass {
    public static void main(String[] args) {

        FarmerSimulator simulator = new FarmerSimulator();

        Farmer[] farmers = simulator.generateFarmers(10);

        // start timer
        // System.out.println("\nSequential Activity Generation Starts");
        // System.out.println("\nStart Timer");
        // Timer timer = new Timer();
        // timer.startTime();
        // // // TODO: Sequential activity generation here
        // for (Farmer farmer : farmers) {
        //     /*
        //         Idea to use the farmer simulator class and add a sequential activity generation method
        //         which accepts a Farmer as its parameter to generate the activities for the farmer
        //     */
        //     simulator.sequentialActivityGenerate(farmer);
        // }
        // // stop timer and print time taken for sequential approach
        // timer.endTime();
        // System.out.println("\nStop Timer");
        // System.out.println("\nTime taken for dummy farmers' simulation using sequential approach: " + timer.timeTaken() + "ns (" + TimeUnit.NANOSECONDS.toMillis(timer.timeTaken()) + "ms)\n");


        try {
            simulator.concurrentActivityGeneration(farmers);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
