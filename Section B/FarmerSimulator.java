/*
    This class is responsible for Farmer generation and Farmer simulation
*/

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import java.sql.*;

public class FarmerSimulator implements FarmerSimulatorInterface {
    // This method generates a number of farmers
    @Override
    public Farmer[] generateFarmers(int numberOfFarmers)
    {   
        // Initailize a convenient Random object
        Random random = new Random();

        // Prepare variables required for the generation of the details of the farmers
        Farmer[] farmers = new Farmer[numberOfFarmers];
        final int PASSWORD_LENGTH = 8;
        String alphanumericCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
        final int MAX_FARM_NUMBER = 5;
        BinaryTree binaryTree = null;

        //initializing the mysql connection class
        MysqlCon mysqlCon = new MysqlCon();
        Statement stmt = mysqlCon.conn();
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
                } 
                catch (Exception e) {
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
        } finally{
            System.out.println("Closing database connection");
            mysqlCon.closeConn();
        }

        return farmers;
    }

    //This method simulates the sequential activity generation
    public void sequentialActivityGenerate(Farmer farmer){
        // TODO: Sequential activity generation

        System.out.println("Farmer " + farmer.getId() + "; Farms " + Arrays.toString(farmer.getFarms()));

        for (String farms : farmer.getFarms()) {
            // generate activities for each farms
        }
    }
}
