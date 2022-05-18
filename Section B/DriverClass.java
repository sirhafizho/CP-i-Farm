import java.time.Instant;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DriverClass {
    public static void main(String[] args) {

        FarmerSimulator simulator = new FarmerSimulator();

        Farmer[] farmers = simulator.generateFarmers(4);

        // // TODO: Sequential activity generation here
        for (Farmer farmer : farmers) {
            /*
                Idea to use the farmer simulator class and add a sequential activity generation method
                which accepts a Farmer as its parameter to generate the activities for the farmer
            */
            simulator.sequentialActivityGenerate(farmer);
        }

    }
}
