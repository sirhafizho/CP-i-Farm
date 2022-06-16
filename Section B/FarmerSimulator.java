/*
    This class is responsible for Farmer generation and Farmer simulation
*/

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

class MyThreadPoolExecutor extends ThreadPoolExecutor {
    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
            TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }
    
    @Override
    public void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        // If submit() method is called instead of execute()
        if (t == null && r instanceof Future<?>) {
            try {
                Object result = ((Future<?>) r).get();
            } catch (CancellationException e) {
                t = e;
            } catch (ExecutionException e) {
                t = e.getCause();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (t != null) {
            // Exception occurred
            System.err.println("Uncaught exception is detected! " + t
                    + " st: " + Arrays.toString(t.getStackTrace()));
            // ... Handle the exception
            // Restart the runnable again
            execute(r);
        }
        // ... Perform cleanup actions
    }
}

public class FarmerSimulator implements FarmerSimulatorInterface {

    // to increment the activity id
    static int activityID = 1;

     // Initailize a convenient Random object
    Random random = new Random();

    //initializing the mysql connection class
    MysqlCon mysqlCon = new MysqlCon();
    Statement stmt = mysqlCon.conn();

    // This method generates a number of farmers
    public Farmer[] generateFarmers(int numberOfFarmers) {

        // Exception Handling
        if(numberOfFarmers < 0) {
            System.out.println("Number of Farmers must be more than 0");
        }


        // Create array to store generated farmer
        Farmer[] farmers = new Farmer[numberOfFarmers];
        
        // This variable is used to create a random string as password for a farmer
        String alphanumericCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";

        // Fix the farmer's password length
        final int PASSWORD_LENGTH = 8;

        //  Fix the maximum number of farms a farmer can be employed by
        final int MAX_FARM_NUMBER = 5;

        // Binary tree data structure is used to insert sort farms for a farmer
        BinaryTree binaryTree = null;

        // Prepare SQL string statement to store generated farmers in database
        String preparedSQL = "INSERT INTO Users(_id, name, email, password, phoneNumber, farms) "+ "VALUES(?,?,?,?,?,?)";

        try {

            // Preparing the insert query to insert farmer into database
            PreparedStatement pstmt = mysqlCon.getCon().prepareStatement(preparedSQL);

            // Arrays to hold first names and last names
            String[] firstNames = new String[100];
            String[] lastNames = new String[100];

            // Create FileReader object for FirstName.txt and LastName.txt
            FileReader firstNameFR = new FileReader("./DummyDataGeneration/Farmers/FirstNames.txt");
            FileReader lastNameFR = new FileReader("./DummyDataGeneration/Farmers/LastNames.txt");

            // Open stream to FileReader objects
            Scanner firstNamesS = new Scanner(firstNameFR);
            Scanner lastNamesS = new Scanner(lastNameFR);

            // Get first names and last names
            for(int i = 0; i < 100; i++) {
                firstNames[i] = firstNamesS.nextLine();
                lastNames[i] = lastNamesS.nextLine();
            }

            // Close streams
            firstNameFR.close();
            lastNameFR.close();
            firstNamesS.close();
            lastNamesS.close();

            // For each farmer to be generated
            for(int i = 0; i < farmers.length; i++) {

                // Determine the _id of current farmer
                String _id = Integer.toString(i + 1);

                // Determine the name of current farmer
                String name = firstNames[random.nextInt(100)] + " " + lastNames[random.nextInt(100)];

                // Determine the email of current farmer
                String email = name.replaceAll("\\s+", "").toLowerCase() + _id + "@gmail.com";

                // Determine the password of current farmer
                String password = "";
                for(int j = 0; j < PASSWORD_LENGTH; j++)
                    password += alphanumericCharacters.charAt(random.nextInt(alphanumericCharacters.length()));

                // Determine the phoneNumber of current farmer
                String phoneNumber = "01" + Integer.toString(random.nextInt(10)) + "-" + Integer.toString(1000000 + random.nextInt(9000000));

                // Determine the farms the current farmer is employed by
                String[] farms = new String[1 + random.nextInt(MAX_FARM_NUMBER)];
                binaryTree = new BinaryTree();
                while(binaryTree.getSize() < farms.length)
                    binaryTree.add(Integer.toString(1 + random.nextInt(10)));
                farms = binaryTree.toStringArray();


                System.out.println("Adding farmer " + _id);

                // Insert current farmer into database
                pstmt.setString(1, _id);
                pstmt.setString(2, name);
                pstmt.setString(3, email);
                pstmt.setString(4, password);
                pstmt.setString(5, phoneNumber);
                pstmt.setString(6, Arrays.toString(farms));
                pstmt.executeUpdate();

                // Save the generated farmer in an array
                farmers[i] = new Farmer(_id, name, email, password, phoneNumber, farms);
            }
        } 
        // Catch SQLException or IOException
        catch(SQLException | IOException e) {

            // Display error message
            System.out.println(e.getMessage());
        } 

        return farmers;
    }

    // This method generates random date
    public String generateDate() {
        NumberFormat formatter = new DecimalFormat("00");
        String year = formatter.format(random.nextInt(22));
        String month = formatter.format(1 + random.nextInt(12));
        String day = formatter.format(1 + random.nextInt(28));
        String date = "20" + year + "-" + month + "-" + day;
        return date;
    }

    public String getKgUnit(int val){
        switch (val) {
            case 0:
                return "kg";
            case 1:
                return "g";
            default:
                return "";
        }
    }

    public String getVolumeUnit(int val){
        switch (val) {
            case 0:
                return "l";
            case 1:
                return "ml";
            default:
                return "";
        }
    }

    public String getPackUnit(int val){
        switch (val) {
            case 0:
                return "pack (500g)";
            case 1:
                return "pack (1000g)";
            default:
                return "";
        }
    }

    //This method simulates the sequential activity generation
    public void sequentialActivityGenerate(Farmer farmer){

        // resetActivitiesTable();
        
        System.out.println("\nFarmer " + farmer.getId() + ": Farms " + Arrays.toString(farmer.getFarms()));
    

        for(String farm : farmer.getFarms()){
            String[] plants = new String[0];
            String[] fertilizers = new String[0];
            String[] pesticides = new String[0];

            // get the array of plants, pesticides and fertilizers of the farm
            try {  
                ResultSet rs = stmt.executeQuery("select * from farms where _id =" + farm);  
                while(rs.next()) {
                    plants = rs.getString("plants").split(",");
                    fertilizers = rs.getString("fertilizers").split(",");
                    pesticides = rs.getString("pesticides").split(",");
                }
            } catch(SQLException e){
                System.out.println(e.getMessage());
            }

            // to get random number of activities
            int numOfActivity = 1000 + random.nextInt(1000);
            System.out.println("Farmer " + farmer.getId() + " generates " + numOfActivity + " activities of random types for Farm " + farm);

            // Write the farmers’ sent operations and success operations into log file
            try {
                FileWriter myWriter = new FileWriter("Sequential Activity Log.txt", true);
                myWriter.write("Farmer " + farmer.getId() + " generates " + numOfActivity + " activities of random types for Farm " + farm + "\n");
                myWriter.close();
              } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }

            // to generate random activities
            for(int i=0; i<numOfActivity; i++){
                
                String date = generateDate();
                int act = 1 + random.nextInt(5);
                String action = "";
                String type = "";
                String unit = "";
                int quantity = 0;
                int field = 1 + random.nextInt(10);
                int row = 1 + random.nextInt(10);

                if(act == 1) {
                    action = "sowing";
                    unit = getKgUnit(random.nextInt(2));
                    if(unit == "kg"){
                        //kg max value set to 15
                        quantity = 1+random.nextInt(15);
                    } else{
                        quantity = 1+random.nextInt(750);
                    }
      	            String plantId = plants[random.nextInt(plants.length)];
                    try {  
                        ResultSet rs = stmt.executeQuery("select * from plants where _id =" + plantId);  
                        while(rs.next()) {
                            type = rs.getString("name");
                        }
                    } catch(SQLException e){
                        System.out.println(e.getMessage());
                    }
                } else if(act == 2) {
                    action = "harvest";
                    unit = getKgUnit(random.nextInt(2));
                    if(unit == "kg"){
                        //kg max value set to 15
                        quantity = 1+random.nextInt(15);
                    } else{
                        quantity = 1+random.nextInt(750);
                    }
                    String plantId = plants[random.nextInt(plants.length)];
                    try {  
                        ResultSet rs = stmt.executeQuery("select * from plants where _id =" + plantId);  
                        while(rs.next()) {
                            type = rs.getString("name");
                        }
                    } catch(SQLException e){
                        System.out.println(e.getMessage());
                    }
                } else if(act == 3) {
                    action = "pesticide";
                    unit = getVolumeUnit(random.nextInt(2));
                    if(unit == "l"){
                        //l max value set to 15
                        quantity = 1+random.nextInt(15);
                    } else{
                        quantity = 1+random.nextInt(2500);
                    }
                    String pesticideId = pesticides[random.nextInt(pesticides.length)];
                    try {  
                        ResultSet rs = stmt.executeQuery("select * from pesticides where _id =" + pesticideId);  
                        while(rs.next()) {
                            type = rs.getString("name");
                        }
                    } catch(SQLException e){
                        System.out.println(e.getMessage());
                    }
                } else if(act == 4) {
                    action = "fertilizer";
                    unit = getPackUnit(random.nextInt(2));
                    if(unit == "pack (500g)"){
                        //pack max value set to 5
                        quantity = 1+random.nextInt(10);
                    } else{
                        quantity = 1+random.nextInt(5);
                    }
                    String fertilizerId = fertilizers[random.nextInt(fertilizers.length)];
                    try {  
                        ResultSet rs = stmt.executeQuery("select * from fertilizers where _id =" + fertilizerId);  
                        while(rs.next()) {
                            type = rs.getString("name");
                        }
                    } catch(SQLException e){
                        System.out.println(e.getMessage());
                    }
                } else {
                    action = "sales";
                    unit = getKgUnit(random.nextInt(2));
                    if(unit == "kg"){
                        //kg max value set to 15
                        quantity = 1+random.nextInt(15);
                    } else{
                        quantity = 1+random.nextInt(750);
                    }
                    String plantId = plants[random.nextInt(plants.length)];
                    try {  
                        ResultSet rs = stmt.executeQuery("select * from plants where _id =" + plantId);  
                        while(rs.next()) {
                            type = rs.getString("name");
                        }
                    } catch(SQLException e){
                        System.out.println(e.getMessage());
                    }
                } 

                try{

                    // preparing the insert query to insert activity into database
                    String preparedSQL = "INSERT INTO activities(_id, date, action, type, unit, quantity, field, row, farmId, userId) "+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement pstmt = mysqlCon.getCon().prepareStatement(preparedSQL);   

                    pstmt.setString(1, Integer.toString(activityID));
                    pstmt.setString(2, date);
                    pstmt.setString(3, action);
                    pstmt.setString(4, type);
                    pstmt.setString(5, unit);
                    pstmt.setInt(6, quantity);
                    pstmt.setInt(7, field);
                    pstmt.setInt(8, row);
                    pstmt.setString(9, farm);
                    pstmt.setString(10, farmer.getId());

                    pstmt.executeUpdate();

                    activityID++;
                    
                }catch(SQLException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void resetActivitiesTable() {
        try {
            //checking if Activities table already exists and dropping it if it does
            DatabaseMetaData meta = mysqlCon.getCon().getMetaData();
            ResultSet resultSet = meta.getTables(null, null, "Activities",  new String[] {"TABLE"});
            if(resultSet.next()){
                System.out.println("Activities table already exists...");
                String dropSQL = "DROP TABLE Activities";
                stmt.executeUpdate(dropSQL);
                System.out.println("Activities table dropped");
            }

            //creating a new Activities table
            String createSQL = "CREATE TABLE Activities " +
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

    public void concurrentActivityGeneration(Farmer[] farmers) throws InterruptedException {

        
        // Resetting Activities Table for Concurrent Approach
        resetActivitiesTable();

        // Delete concurrent activity log if already exists
        try{
            Path fileToDeletePath = Paths.get("Concurrent Activity Log.txt");
            File file = new File("Concurrent Activity Log.txt");
            if(file.exists()){
                Files.delete(fileToDeletePath);
            }
        }catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // Create new concurrent activity log
        try {
            FileWriter myWriter = new FileWriter("Concurrent Activity Log.txt");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // << Normal Concurrent Activity Generation >>
        System.out.println("\nConcurrent Activity Generation Starts");
        System.out.println("\nStart Timer\n");

        // Initalize then start the timer
        Timer timer = new Timer();
        timer.startTime();

        // Create a fixed thread pool executor
        ExecutorService threadPool = new MyThreadPoolExecutor(50, 50, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        for(int i = 0; i<farmers.length; i++){
            threadPool.execute(farmers[i]);
        }
        threadPool.shutdown();
        while(!threadPool.isTerminated()){         
        }

        // Stop the timer then display time it took for farmers to concurrently generate activities and to write the activities to the database
        timer.endTime();
        System.out.println("\nStop Timer");
        System.out.println("\nConcurrent activity generation took " + timer.timeTaken() + "ns (" + TimeUnit.NANOSECONDS.toMillis(timer.timeTaken()) + "ms)\n" );



        // << Concurrent Activity Generation with disaster simulation >>
        // System.out.println("\nConcurrent Activity Generation Starts");
        
        // // Create a fixed thread pool executor
        // ExecutorService threadPool = new MyThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS,
        //         new LinkedBlockingQueue<>());
        // for(int i = 0; i<farmers.length; i++){
        //     threadPool.execute(farmers[i]);
        // }

        // threadPool.awaitTermination(8, TimeUnit.SECONDS);
        // threadPool.shutdown();







        

        // ExecutorService executor = Executors.newFixedThreadPool(1000);
        // List<Future<?>> list = new ArrayList<Future<?>>();
        // for (int i = 0; i < farmers.length; i++) {
        //     // executor.execute(farmers[i]);
        //     Future<?> submit = executor.submit(farmers[i]);
        //     list.add(submit);
        //     // submit.cancel(true);
        //   }
        // for (Future<?> future : list) {
        //     try {
        //         future.get();
        //     } catch (InterruptedException e) {
        //     } catch (ExecutionException e) {
        //         // Extract the actual exception from its wrapper
        //         Throwable t = e.getCause();
        //         System.err.println("Uncaught exception is detected! " + t);
        //         // ... Handle the exception
        //     }
        // }
        // executor.shutdown();
        // while (!executor.isTerminated()) {
        // }

        // int counterid = 1;
        // // For each farmer
        // for(int i = 0; i < farmers.length; i++) {
        //     // Get the activities that they have performed
        //     Activity[][] activities = farmers[i].getActivities();
        //     // For the activities of a farm
        //     for(int j = 0; j < activities.length; j++) {
        //         // For each of the activities
        //         for(int k = 0; k < activities[j].length; k++) {
        //             try {
        //                 System.out.println("Inserting");
        //                 // Prepare the insert query statement to insert activity into database then get the editable PreparedStatement
        //                 String preparedSQL = "INSERT INTO activities(_id, date, action, type, unit, quantity, field, row, farmId, userId) "+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
        //                 PreparedStatement preparedStatement = this.mysqlCon.getCon().prepareStatement(preparedSQL);
        
        //                 // Insert the activity information into the PreparedStatement
        //                 preparedStatement.setString(1, Integer.toString(counterid));
        //                 preparedStatement.setString(2, activities[j][k].getDate());
        //                 preparedStatement.setString(3, activities[j][k].getAction());
        //                 preparedStatement.setString(4, activities[j][k].getType());
        //                 preparedStatement.setString(5, activities[j][k].getUnit());
        //                 preparedStatement.setInt(6, activities[j][k].getQuantity());
        //                 preparedStatement.setInt(7, activities[j][k].getField());
        //                 preparedStatement.setInt(8, activities[j][k].getRow());
        //                 preparedStatement.setString(9, Integer.toString(activities[j][k].getFarmId()));
        //                 preparedStatement.setString(10, Integer.toString(activities[j][k].getUserId()));
        
        //                 // Insert the activity into the database
        //                 preparedStatement.executeUpdate();
        //                 counterid++;
        //             }
        //             catch(SQLException e) {
        //                 // Print out error message to the terminal if any
        //                 System.out.println(e.getMessage());
        //             }
        //         }
        //     }
        // }

        
    }
}