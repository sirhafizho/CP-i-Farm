/*
    This class is responsible for Farmer generation and Farmer simulation
*/

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class FarmerSimulator implements FarmerSimulatorInterface {

    // to increment the activity id
    static int activityID = 1;

     // Initailize a convenient Random object
    Random random = new Random();

    //initializing the mysql connection class
    MysqlCon mysqlCon = new MysqlCon();
    Statement stmt = mysqlCon.conn();

    // This method generates a number of farmers
    @Override
    public Farmer[] generateFarmers(int numberOfFarmers)
    {   

        // Prepare variables required for the generation of the details of the farmers
        Farmer[] farmers = new Farmer[numberOfFarmers];
        
        final int PASSWORD_LENGTH = 8;
        String alphanumericCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
        final int MAX_FARM_NUMBER = 5;
        BinaryTree binaryTree = null;

        String preparedSQL = "INSERT INTO Users(_id, name, email, password, phoneNumber, farms) "+ "VALUES(?,?,?,?,?,?)";

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
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            
            // preparing the insert query to insert farmer into database
            PreparedStatement pstmt = mysqlCon.getCon().prepareStatement(preparedSQL);      

            // Generate farmers
            for(int i = 0; i < farmers.length; i++) {
                // Generate the detail of the current farmer
                String id = Integer.toString(i + 1);
                String name = null;
                try {
                    // Initalize the URL object for the API
                    URL url = new URL("https://api.namefake.com/english-united-states");

                    // Establish connection with the API
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.connect();

                    // Get response code of the API call
                    int responseCode = conn.getResponseCode();

                    if(responseCode != 200)
                        throw new RuntimeException("HttpResponseCode: " + responseCode);
                    else
                    {
                        // Initalize the string builder object and scanner object
                        StringBuilder sb = new StringBuilder();
                        Scanner scanner = new Scanner(url.openStream());
                            
                        // While there is still information to read from the API call
                        while(scanner.hasNext())
                            // Append the information to the string builder object
                            sb.append(scanner.nextLine());

                        // Close the scanner object
                        scanner.close();

                        // Get the name from the JSON response
                        name = sb.substring(9, sb.indexOf("\"", 9));
                    }
                } catch (Exception e) {
                    // TODO: Do something with the error
                    System.out.println("Name API didn't work");
                    System.exit(1);
                }
                String email = name.replaceAll("\\s+", "").toLowerCase() + id + "@gmail.com";
                String password = "";
                for(int j = 0; j < PASSWORD_LENGTH; j++)
                    password += alphanumericCharacters.charAt(random.nextInt(alphanumericCharacters.length()));
                String phoneNumber = "01" + Integer.toString(random.nextInt(10)) + "-" + Integer.toString(1000000 + random.nextInt(9000000));
                String[] farms = new String[1 + random.nextInt(MAX_FARM_NUMBER)];
                binaryTree = new BinaryTree();
                while(binaryTree.getSize() < farms.length)
                    binaryTree.add(Integer.toString(1 + random.nextInt(10)));
                farms = binaryTree.toStringArray();

                pstmt.setString(1, id);
                pstmt.setString(2, name);
                pstmt.setString(3, email);
                pstmt.setString(4, password);
                pstmt.setString(5, phoneNumber);
                pstmt.setString(6, Arrays.toString(farms));

                System.out.println("Adding farmer "+id);
                pstmt.executeUpdate();
                
                farmers[i] = new Farmer(id, name, email, password, phoneNumber, farms);
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
        } 
        // finally{
        //     System.out.println("Closing database connection");
        //     mysqlCon.closeConn();
        // }

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
            int numOfActivity = 1 + random.nextInt(12);
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

    public void concurrentActivityGeneration(Farmer[] farmers) throws InterruptedException {
        // Initalize an array to hold all the threads
        Thread[] threads = new Thread[farmers.length];

        // Initalize then start the timer
        Timer timer = new Timer();
        timer.startTime();

        for(int i = 0; i < farmers.length; i++) {
            // Initiazlie a thread then start it
            threads[i] = new Thread(farmers[i]);
            threads[i].start();
        }

        // Wait for all threads to complete the run
        for(Thread thread : threads) {
            thread.join();
        }

        // Initialize a variable that will keep track of the 
        int activity_id = 1;

        // For each farmer
        for(int i = 0; i < farmers.length; i++) {
            // Get the activities that they have performed
            Activity[][] activities = farmers[i].getActivities();

            // For the activities of a farm
            for(int j = 0; j < activities.length; j++) {
                // For each of the activities
                for(int k = 0; k < activities[j].length; k++) {
                    try {
                        // Prepare the insert query statement to insert activity into database then get the editable PreparedStatement
                        String preparedSQL = "INSERT INTO activities(_id, date, action, type, unit, quantity, field, row, farmId, userId) "+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
                        PreparedStatement preparedStatement = this.mysqlCon.getCon().prepareStatement(preparedSQL);
        
                        // Insert the activity information into the PreparedStatement
                        preparedStatement.setString(1, Integer.toString(activity_id));
                        preparedStatement.setString(2, activities[j][k].getDate());
                        preparedStatement.setString(3, activities[j][k].getAction());
                        preparedStatement.setString(4, activities[j][k].getType());
                        preparedStatement.setString(5, activities[j][k].getUnit());
                        preparedStatement.setInt(6, activities[j][k].getQuantity());
                        preparedStatement.setInt(7, activities[j][k].getField());
                        preparedStatement.setInt(8, activities[j][k].getRow());
                        preparedStatement.setString(9, Integer.toString(activities[j][k].getFarmId()));
                        preparedStatement.setString(10, Integer.toString(activities[j][k].getUserId()));
        
                        // Insert the activity into the database
                        preparedStatement.executeUpdate();

                        // Increment the activity _id for the next activity to be inserted into the database
                        activity_id++;
                    }
                    catch(SQLException e) {
                        // Print out error message to the terminal if any
                        System.out.println(e.getMessage());
                    }
                }
            }
        }

        // Stop the timer then display time it took for farmers to concurrently generate activities and to write the activities to the database
        timer.endTime();
        System.out.println("Concurrent activity generation took " + timer.timeTaken() + "ns");
    }
}
