import java.util.Arrays;

import java.util.Random;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.io.FileWriter;
import java.io.IOException;

public class Farmer implements Runnable {
    private String _id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String[] farms;

    // private Counter counter_id;
    private Activity[][] activities;

    // Initailize a convenient Random object
    Random randomC = new Random();

    

    Farmer(String _id, String name, String email, String password, String phoneNumber, String[] farms) {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.password= password;
        this.phoneNumber = phoneNumber;
        this.farms = farms;
        this.activities = new Activity[this.farms.length][];
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String[] getFarms() {
        return farms;
    }

    public void setFarms(String[] farms) {
        this.farms = farms;
    }


    // public void setCounter(Counter counter) {
    //     this.counter_id = counter;
    // }

    public Activity[][] getActivities() {
        return this.activities;
    }

    // This method generates random date
    public String generateDate() {
        NumberFormat formatter = new DecimalFormat("00");
        String year = formatter.format(randomC.nextInt(22));
        String month = formatter.format(1 + randomC.nextInt(12));
        String day = formatter.format(1 + randomC.nextInt(28));
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

    @Override
    public void run() {
        //initializing the mysql connection class
        MysqlCon mysqlCon = new MysqlCon();
        Statement stmt = mysqlCon.conn();

        //Throw exception error randomly
        // int randomFailedThreadSim = randomC.nextInt(3) + 1;

        //       if(randomFailedThreadSim == 2) {
        //         System.out.println("Farmer " + this.getId() + " fails to generate activities." );
        //         throw new RuntimeException("Oh no disaster!!!");
        //       }

        // For each farm that the current farmer is employed by
        for(int i = 0; i < farms.length; i++) {

            // throw exception error randomly for each farm 
            //   int randomFailedThreadSim = randomC.nextInt(5) + 1;

            //   if(randomFailedThreadSim == 5) {
            //     System.out.println("Farmer " + this.getId() + " fails to generate activities for Farm: " + farms[i]);
            //     throw new RuntimeException("Oh no disaster!!!");
            //   }

            int numOfActivity = randomC.nextInt(1000) + 1000;
            this.activities[i] = new Activity[numOfActivity];

            String[] plants = new String[0];
            String[] fertilizers = new String[0];
            String[] pesticides = new String[0];

             // get the array of plants, pesticides and fertilizers of the farm
             try {  
                ResultSet rs = stmt.executeQuery("select * from farms where _id =" + farms[i]);  
                while(rs.next()) {
                    plants = rs.getString("plants").split(",");
                    fertilizers = rs.getString("fertilizers").split(",");
                    pesticides = rs.getString("pesticides").split(",");
                }
            } catch(SQLException e){
                System.out.println(e.getMessage());
            }

            System.out.println("Farmer " + this.getId() + " generates " + numOfActivity + " activities of random types for Farm " + farms[i]);
            // Write the farmers’ sent operations and success operations into log file
            // try {
            //     FileWriter myWriter = new FileWriter("Concurrent Activity Log.txt", true);
            //     myWriter.write("Farmer " + this.getId() + " generates " + numOfActivity + " activities of random types for Farm " + farms[i] + "\n");
            //     myWriter.close();
            //   } catch (IOException e) {
            //     System.out.println("An error occurred.");
            //     e.printStackTrace();
            //   }
              
            
            // Generate the activities performed by the farmer for the farm
            for(int j = 0; j < numOfActivity; j++) {
                String date = generateDate();

                int act = 1 + randomC.nextInt(5);
                String action = "";
                String type = "";
                String unit = "";
                int quantity = 0;
                int field = 1 + randomC.nextInt(10);
                int row = 1 + randomC.nextInt(10);

            
                if(act == 1) {
                    action = "sowing";
                    unit = getKgUnit(randomC.nextInt(2));
                    if(unit == "kg"){
                        //kg max value set to 15
                        quantity = 1+randomC.nextInt(15);
                    } else{
                        quantity = 1+randomC.nextInt(750);
                    }
      	            String plantId = plants[randomC.nextInt(plants.length)];
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
                    unit = getKgUnit(randomC.nextInt(2));
                    if(unit == "kg"){
                        //kg max value set to 15
                        quantity = 1+randomC.nextInt(15);
                    } else{
                        quantity = 1+randomC.nextInt(750);
                    }
                    String plantId = plants[randomC.nextInt(plants.length)];
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
                    unit = getVolumeUnit(randomC.nextInt(2));
                    if(unit == "l"){
                        //l max value set to 15
                        quantity = 1+randomC.nextInt(15);
                    } else{
                        quantity = 1+randomC.nextInt(2500);
                    }
                    String pesticideId = pesticides[randomC.nextInt(pesticides.length)];
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
                    unit = getPackUnit(randomC.nextInt(2));
                    if(unit == "pack (500g)"){
                        //pack max value set to 5
                        quantity = 1+randomC.nextInt(10);
                    } else{
                        quantity = 1+randomC.nextInt(5);
                    }
                    String fertilizerId = fertilizers[randomC.nextInt(fertilizers.length)];
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
                    unit = getKgUnit(randomC.nextInt(2));
                    if(unit == "kg"){
                        //kg max value set to 15
                        quantity = 1+randomC.nextInt(15);
                    } else{
                        quantity = 1+randomC.nextInt(750);
                    }
                    String plantId = plants[randomC.nextInt(plants.length)];
                    try {  
                        ResultSet rs = stmt.executeQuery("select * from plants where _id =" + plantId);  
                        while(rs.next()) {
                            type = rs.getString("name");
                        }
                    } catch(SQLException e){
                        System.out.println(e.getMessage());
                    }
                }
                String tempid = "U" + this._id + "F" + farms[i] + "" + j; 
                // this.activities[i][j] = new Activity(tempid,date,action,type,unit,quantity,field,row,Integer.parseInt(farms[i]),Integer.parseInt(this._id));
                try {
                        // Prepare the insert query statement to insert activity into database then get the editable PreparedStatement
                        String preparedSQL = "INSERT INTO activities(_id, date, action, type, unit, quantity, field, row, farmId, userId) "+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
                        PreparedStatement preparedStatement = mysqlCon.getCon().prepareStatement(preparedSQL);
                    
                        // Insert the activity information into the PreparedStatement
                        preparedStatement.setString(1, tempid);
                        preparedStatement.setString(2, date);
                        preparedStatement.setString(3, action);
                        preparedStatement.setString(4, type);
                        preparedStatement.setString(5, unit);
                        preparedStatement.setInt(6, quantity);
                        preparedStatement.setInt(7, field);
                        preparedStatement.setInt(8, row);
                        preparedStatement.setString(9, farms[i]);
                        preparedStatement.setString(10, this._id);
                    
                        // Insert the activity into the database
                        preparedStatement.executeUpdate();
                        }
                        catch(SQLException e) {
                            // Print out error message to the terminal if any
                            System.out.println(e.getMessage());
                        }
            }
        }
        // close db connection
        mysqlCon.closeConn();
    }

    @Override
    public String toString() {
        return "[" + _id + ", " + name + ", " + email + ", " + password + ", " + phoneNumber + ", " + Arrays.toString(this.farms) + "]";
    }
}
