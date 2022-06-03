import java.sql.*;

import com.mysql.cj.xdevapi.Result;

public class DataVisualization {
    //initializing the mysql connection class
    MysqlCon mysqlCon = new MysqlCon();
    Statement stmt = mysqlCon.conn();

    public DataVisualization() {}

    public void processActivities(){
        try{
            //to check if activities table exists
            DatabaseMetaData meta = mysqlCon.getCon().getMetaData();
            ResultSet resultSet = meta.getTables(null, null, "Activities",  new String[] {"TABLE"});
            if(!resultSet.next()){
                System.out.println("Activities table does not exist!");
            }else{
                try{
                     //to check if processed_activities table exists
                    resultSet = meta.getTables(null, null, "Processed_Activities",  new String[] {"TABLE"});
                    if(resultSet.next()){
                        System.out.println("Processed_Activities table already exists....");
                        String dropSQL = "DROP TABLE Processed_Activities";
                        stmt.executeUpdate(dropSQL);
                        System.out.println("Processed_Activities table dropped");
                    }

                    String createSQL = " CREATE TABLE `processed_activities` ( " +
                        " `_id` varchar(255) NOT NULL, " + 
                        " `date` date NOT NULL, " +
                        " `action` varchar(100) NOT NULL, " +
                        " `type` varchar(100) NOT NULL, " +
                        " `unit` varchar(50) NOT NULL, " +
                        " `quantity` decimal(5,3) NOT NULL, " +
                        " `field` int(50) NOT NULL, " +
                        " `row` int(50) NOT NULL, " +
                        " `farmId` varchar(100) NOT NULL, " +
                        " `userId` varchar(100) NOT NULL )";
                    System.out.println("Creating new Processed_Activities table...");
                    stmt.executeUpdate(createSQL);
                    System.out.println("Processed_Activities table created...");

                }catch(SQLException e){
                    System.out.println(e.getMessage());
                }

                //process the activities and store them into processed_activities table
                String createSQL = "INSERT INTO processed_activities(" +
                    " SELECT _id, date, action, type, " +
                    " CASE " +
                        " WHEN unit = 'g' THEN 'kg' " +
                        " WHEN unit = 'ml' THEN 'l' " +
                    " END AS unit, quantity/1000 AS quantity, field, row, farmId, userId " +
                    " FROM activities where unit = 'g' OR unit = 'ml' " +
                    " UNION " +
                    " SELECT _id, date, action, type,  " +
                    " CASE " +
                        " WHEN unit = 'pack (500g)' THEN 'pack (1000g)' " +
                    " END AS unit, quantity/2 AS quantity, field, row, farmId, userId FROM activities where unit = 'pack (500g)' " +
                    " UNION " +
                    " SELECT * FROM activities where NOT (unit = 'g' OR unit = 'ml' OR unit = 'pack (500g)'))";

                System.out.println("Inserting data into processed_activities table...");
                stmt.executeUpdate(createSQL);
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public ResultSet displayActivityLogsFarm(String farmID){
        ResultSet rs = null;
        try{
            //get activities based on farm id
            String sqlQuery = "SELECT * FROM Processed_Activities"+
            " WHERE farmId = "+farmID +
            " ORDER BY date ASC";                
             rs = stmt.executeQuery(sqlQuery);
             printActivityLog(rs);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return rs;
    }

    public ResultSet displayActivityLogsFarmer(String farmerID){
        ResultSet rs = null;
        try{
            //get activities based on farmer id
            String sqlQuery = "SELECT * FROM Processed_Activities"+
            " WHERE userId = "+farmerID +
            " ORDER BY date ASC";                
             rs = stmt.executeQuery(sqlQuery);
             printActivityLog(rs);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return rs;
    }

    public ResultSet displayActivityLogsFarmType(String farmID, String type){
        ResultSet rs = null;
        try{
            //get activities based on farm id & type of plant / fertilizer / pesticide
            String sqlQuery = "SELECT * FROM Processed_Activities"+
            " WHERE farmId = "+ farmID +
            " AND LOWER(type) = LOWER('"+type+"')" +
            " ORDER BY date ASC";                
             rs = stmt.executeQuery(sqlQuery);
             printActivityLog(rs);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return rs;
    }

    public void printActivityLog(ResultSet rs){
        try{
            if(rs == null){
                System.out.println("\nNo records found");
            } else{
                while(rs.next()){
                   String action = rs.getString("action");
                   String type = rs.getString("type");
                   int field = rs.getInt("field");
                   int row = rs.getInt("row");
                   int quantity = rs.getInt("quantity");
                   String unit = rs.getString("unit");
                   Date date = rs.getDate("date");
    
                   System.out.println(action + " " + type + " Field " + field + " Row " + row + " " + quantity + " " + unit + " " + date.toString());
                }
            }
        } catch(SQLException e){
            System.out.println(e.toString());
        }
        
    }
}
