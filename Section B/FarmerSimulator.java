/*
    This class is responsible for Farmer generation and Farmer simulation
*/

import java.net.HttpURLConnection;
import java.net.URL;
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
            //checking if table already exists and dropping it if it does
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

    //This method simulates the sequential activity generation
    public void sequentialActivityGenerate(Farmer farmer){

        System.out.println("Farmer " + farmer.getId() + ": Farms " + Arrays.toString(farmer.getFarms()));
        
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

            // to generate random activities
            for(int i=0; i<numOfActivity; i++){
                String date = generateDate();

                int act = 1 + random.nextInt(5);
                String action = "";
                String type = "";
                String unit = "kg";
                int quantity = 1 + random.nextInt(10);
                int field = 1 + random.nextInt(10);
                int row = 1 + random.nextInt(10);

                if(act == 1) {
                    action = "sowing";
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
}
