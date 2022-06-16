import java.sql.ResultSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.sql.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileWriter;

public class DriverClass {

    public static void main(String[] args) {

        //Check the Database and reset
        checkDB();

        // Initalise Farmer Simulator
        FarmerSimulator simulator = new FarmerSimulator();

        // Generate Farmers
        Farmer[] farmers = simulator.generateFarmers(10);

        // // SEQUENTIAL PART
        // createNewSeqLog();
        // // start timer
        // System.out.println("\nSequential Activity Generation Starts");
        // System.out.println("\nStart Timer");
        // Timer timer = new Timer();
        // timer.startTime();
        // // sequential activity generation here
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

        // CONCURRENT PART
        try {
            simulator.concurrentActivityGeneration(farmers);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // //this is the data visualization section
        DataVisualization visualize = new DataVisualization();
        
        // // calling the process activities method to process the activities 
        visualize.processActivities();

        //to display the frame
        DataVisualizationFrame frame = new DataVisualizationFrame();
        frame.setTitle("i-Farm Data Visualization");
        // frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }

    public static void checkDB() {
        //initializing the mysql connection class
        MysqlCon mysqlCon = new MysqlCon();
        Statement stmt = mysqlCon.conn();
        try {
            //checking if Users table already exists and dropping it if it does
            DatabaseMetaData meta = mysqlCon.getCon().getMetaData();
            ResultSet resultSet = meta.getTables(null, null, "Users",  new String[] {"TABLE"});
            if(resultSet.next()){
                System.out.println("Users table already exists...");
                String dropSQL = "DROP TABLE Users";
                stmt.executeUpdate(dropSQL);
                System.out.println("Users table dropped");
            }

            //creating a new Users table
            String createSQL = "CREATE TABLE Users " +
                   "(_id VARCHAR(255) not NULL, " +
                   " name VARCHAR(255), " + 
                   " email VARCHAR(255), " + 
                   " password VARCHAR(255), " +
                   " phoneNumber VARCHAR(255), " + 
                   " farms VARCHAR(255), " + 
                   " PRIMARY KEY ( _id ))";
            System.out.println("Creating new Users table...");
            stmt.executeUpdate(createSQL);
            System.out.println("Users table created...");

            //checking if Activities table already exists and dropping it if it does
            resultSet = meta.getTables(null, null, "Activities",  new String[] {"TABLE"});
            if(resultSet.next()){
                System.out.println("Activities table already exists...");
                String dropSQL = "DROP TABLE Activities";
                stmt.executeUpdate(dropSQL);
                System.out.println("Activities table dropped");
            }

            //creating a new Activities table
            createSQL = "CREATE TABLE Activities " +
                   "(_id VARCHAR(255) not NULL, " +
                   " date date NOT NULL, " + 
                   " action varchar(100) NOT NULL, " + 
                   " type varchar(100) NOT NULL, " +
                   " unit varchar(50) NOT NULL, " + 
                   " quantity int(50) NOT NULL, " +
                   " field int(50) NOT NULL, " +
                   " row int(50) NOT NULL, " +
                   " farmId varchar(100) NOT NULL, " +
                   " userId varchar(100) NOT NULL, " +
                   " PRIMARY KEY ( _id ))";
            System.out.println("Creating new Activities table...");
            stmt.executeUpdate(createSQL);
            System.out.println("Activities table created..." + "\n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createNewSeqLog() {
        // delete sequential activity log if already exists
        try{
            Path fileToDeletePath = Paths.get("Sequential Activity Log.txt");
            File file = new File("Sequential Activity Log.txt");
            if(file.exists()){
                Files.delete(fileToDeletePath);
            }
        }catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // create new sequential activity log
        try {
            FileWriter myWriter = new FileWriter("Sequential Activity Log.txt");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // public static void cLIDataVisualisation(DataVisualization visualize) {
    //     // cli commands starts here
    //     int input = 0;
    //     Scanner s = new Scanner(System.in);

    //     // while loop to run the commands until exit
    //     while(true){
    //         System.out.println("\nTo visualize the activites select a particular number for to view the data (enter -1 to exit)\n");
    //         System.out.println("Enter 1 for displaying all activity logs for a target farm");
    //         System.out.println("Enter 2 for displaying all activity logs for a target farmer");
    //         System.out.println("Enter 3 for displaying all activity logs for a target farm and plant / fertilizer / pesticide");
    //         System.out.println("Enter 4 for displaying all activity logs for a target farm and plant / fertilizer / pesticide between date A and date B (inclusive)");
    //         System.out.println("Enter 5 for displayign summarized logs by plants, fertilizers and pesticides for a target farm and plant / fertilizer / pesticide between date A and date B (inclusive) for selected field and row number\n");

    //         System.out.print("Input: ");

    //         // try catch to get input mismatch
    //         try{
    //             input = s.nextInt();
    //         } catch(InputMismatchException e){
    //             System.out.println("\nInput error");
    //             break;
    //         }
            
    //         // program end
    //         if(input == -1){
    //             System.out.println("Program ending");
    //             break;
    //         }
            
    //         String farmID, farmerID, typeName, fromDate, toDate, fieldNumber, rowNumber;

    //         // switch case to perform sql commands
    //         switch (input) {
    //             case 1:
    //                 System.out.print("\nEnter Farm ID: ");
    //                 do{
    //                     farmID = s.next();
    //                 } while(farmID.isEmpty());
    //                 visualize.displayActivityLogsFarm(farmID);    
    //                 break;
    //             case 2:
    //                 System.out.print("\nEnter Farmer ID: ");  
    //                 do{
    //                     farmerID = s.next();
    //                 } while(farmerID.isEmpty()); 
    //                 visualize.displayActivityLogsFarmer(farmerID);    
    //                 break;
    //             case 3:
    //                 System.out.print("\nEnter Farm ID: ");
    //                 do{
    //                     farmID = s.next();
    //                 } while(farmID.isEmpty()); 
    //                 System.out.print("\nEnter plant / fertilizer / pesticide name: ");
    //                 do{
    //                     typeName = s.next();
    //                 } while(typeName.isEmpty());

    //                 visualize.displayActivityLogsFarmType(farmID, typeName);
    //                 break;
    //             case 4:
    //                 System.out.print("\nEnter Farm ID: ");
    //                 do{
    //                     farmID = s.next();
    //                     s.nextLine();
    //                 } while(farmID.isEmpty()); 
    //                 System.out.print("\nEnter plant / fertilizer / pesticide name: ");
    //                 do{
    //                     typeName = s.next();
    //                     s.nextLine();
    //                 } while(typeName.isEmpty());
    //                 System.out.print("\nEnter start date: ");
    //                 do{
    //                     fromDate = s.next();
    //                     s.nextLine();
    //                 } while(fromDate.isEmpty());
    //                 System.out.print("\nEnter end date: ");
    //                 do{
    //                     toDate = s.next();
    //                     s.nextLine();
    //                 } while(toDate.isEmpty());            

    //                 visualize.displayActivityLogsFarmTypeDate(farmID, typeName, fromDate, toDate);
    //                 break;
    //             case 5:
    //                 System.out.print("\nEnter Farm ID: ");
    //                 do{
    //                     farmID = s.next();
    //                     s.nextLine();
    //                 } while(farmID.isEmpty()); 
    //                 System.out.print("\nEnter plant / fertilizer / pesticide name: ");
    //                 do{
    //                     typeName = s.next();
    //                     s.nextLine();
    //                 } while(typeName.isEmpty());
    //                 System.out.print("\nEnter start date: ");
    //                 do{
    //                     fromDate = s.next();
    //                     s.nextLine();
    //                 } while(fromDate.isEmpty());
    //                 System.out.print("\nEnter end date: ");
    //                 do{
    //                     toDate = s.next();
    //                     s.nextLine();
    //                 } while(toDate.isEmpty());
    //                 System.out.print("\nEnter field number: ");
    //                 do{
    //                     fieldNumber = s.next();
    //                     s.nextLine();
    //                 } while(toDate.isEmpty());
    //                 System.out.print("\nEnter row number: ");
    //                 do{
    //                     rowNumber = s.next();
    //                     s.nextLine();
    //                 } while(toDate.isEmpty());

    //                 visualize.displayActivityLogsFarmTypeDateFieldRow(farmID, typeName, fromDate, toDate, fieldNumber, rowNumber);
    //                 break;
    //             default:
    //                 System.out.println("Invalid input\n");
    //                 break;
    //         }
    //     }
    //     s.close();
    // }
}
