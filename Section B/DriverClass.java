import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class DriverClass {
    public static void main(String[] args) {

        FarmerSimulator simulator = new FarmerSimulator();

        Farmer[] farmers = simulator.generateFarmers(4);

        // // start timer
        System.out.println("\nSequential Activity Generation Starts");
        System.out.println("\nStart Timer");
        Timer timer = new Timer();
        timer.startTime();
        // sequential activity generation here
        for (Farmer farmer : farmers) {
            /*
                Idea to use the farmer simulator class and add a sequential activity generation method
                which accepts a Farmer as its parameter to generate the activities for the farmer
            */
            simulator.sequentialActivityGenerate(farmer);
        }
        // stop timer and print time taken for sequential approach
        timer.endTime();
        System.out.println("\nStop Timer");
        System.out.println("\nTime taken for dummy farmers' simulation using sequential approach: " + timer.timeTaken() + "ns (" + TimeUnit.NANOSECONDS.toMillis(timer.timeTaken()) + "ms)\n");

        // this is the data visualization section
        DataVisualization visualize = new DataVisualization();
        
        // calling the process activities method to process the activities 
        visualize.processActivities();

        // cli commands starts here
        int input = 0;
        Scanner s = new Scanner(System.in);

        // while loop to run the commands until exit
        while(true){
            System.out.println("\nTo visualize the activites select a particular number for to view the data (enter -1 to exit)\n");
            System.out.println("Enter 1 for displaying all activity logs for a target farm");
            System.out.println("Enter 2 for displaying all activity logs for a target farmer");
            System.out.println("Enter 3 for displaying all activity logs for a target farm and plant / fertilizer / pesticide");
            System.out.println("Enter 4 for displaying all activity logs for a target farm and plant / fertilizer / pesticide between date A and date B (inclusive)");
            System.out.println("Enter 5 for displayign summarized logs by plants, fertilizers and pesticides for a target farm and plant / fertilizer / pesticide between date A and date B (inclusive) for selected field and row number\n");

            System.out.print("Input: ");

            // try catch to get input mismatch
            try{
                input = s.nextInt();
            } catch(InputMismatchException e){
                System.out.println("\nInput error");
                break;
            }
            
            // program end
            if(input == -1){
                System.out.println("Program ending");
                break;
            }
            
            String farmID, farmerID, typeName, fromDate, toDate, fieldNumber, rowNumber;

            // switch case to perform sql commands
            switch (input) {
                case 1:
                    System.out.print("\nEnter Farm ID: ");
                    do{
                        farmID = s.next();
                    } while(farmID.isEmpty());
                    visualize.displayActivityLogsFarm(farmID);    
                    break;
                case 2:
                    System.out.print("\nEnter Farmer ID: ");  
                    do{
                        farmerID = s.next();
                    } while(farmerID.isEmpty()); 
                    visualize.displayActivityLogsFarmer(farmerID);    
                    break;
                case 3:
                    System.out.print("\nEnter Farm ID: ");
                    do{
                        farmID = s.next();
                    } while(farmID.isEmpty()); 
                    System.out.print("\nEnter plant / fertilizer / pesticide name: ");
                    do{
                        typeName = s.next();
                    } while(typeName.isEmpty());

                    visualize.displayActivityLogsFarmType(farmID, typeName);
                    break;
                case 4:
                    System.out.print("\nEnter Farm ID: ");
                    do{
                        farmID = s.next();
                        s.nextLine();
                    } while(farmID.isEmpty()); 
                    System.out.print("\nEnter plant / fertilizer / pesticide name: ");
                    do{
                        typeName = s.next();
                        s.nextLine();
                    } while(typeName.isEmpty());
                    System.out.print("\nEnter start date: ");
                    do{
                        fromDate = s.next();
                        s.nextLine();
                    } while(fromDate.isEmpty());
                    System.out.print("\nEnter end date: ");
                    do{
                        toDate = s.next();
                        s.nextLine();
                    } while(toDate.isEmpty());            

                    visualize.displayActivityLogsFarmTypeDate(farmID, typeName, fromDate, toDate);
                    break;
                case 5:
                    System.out.print("\nEnter Farm ID: ");
                    do{
                        farmID = s.next();
                        s.nextLine();
                    } while(farmID.isEmpty()); 
                    System.out.print("\nEnter plant / fertilizer / pesticide name: ");
                    do{
                        typeName = s.next();
                        s.nextLine();
                    } while(typeName.isEmpty());
                    System.out.print("\nEnter start date: ");
                    do{
                        fromDate = s.next();
                        s.nextLine();
                    } while(fromDate.isEmpty());
                    System.out.print("\nEnter end date: ");
                    do{
                        toDate = s.next();
                        s.nextLine();
                    } while(toDate.isEmpty());
                    System.out.print("\nEnter field number: ");
                    do{
                        fieldNumber = s.next();
                        s.nextLine();
                    } while(toDate.isEmpty());
                    System.out.print("\nEnter row number: ");
                    do{
                        rowNumber = s.next();
                        s.nextLine();
                    } while(toDate.isEmpty());

                    visualize.displayActivityLogsFarmTypeDateFieldRow(farmID, typeName, fromDate, toDate, fieldNumber, rowNumber);
                    break;
                default:
                    System.out.println("Invalid input\n");
                    break;
            }
        }
        s.close();
            
        


        // try {
        //     simulator.concurrentActivityGeneration(farmers);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }

    }
}
