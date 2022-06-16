import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataVisualization {
    // Initializing the mysql connection class
    MysqlCon mysqlCon = new MysqlCon();
    Statement stmt = mysqlCon.conn();

    public DataVisualization() {
    }

    public void processActivities() {
        try {
            // To check if activities table exists
            DatabaseMetaData meta = mysqlCon.getCon().getMetaData();
            ResultSet resultSet = meta.getTables(null, null, "Activities", new String[] { "TABLE" });
            if (!resultSet.next()) {
                System.out.println("Activities table does not exist!");
            } else {
                try {
                    // To check if processed_activities table exists
                    resultSet = meta.getTables(null, null, "processed_activities", new String[] { "TABLE" });
                    if (resultSet.next()) {
                        System.out.println("processed_activities table already exists....");
                        String dropSQL = "DROP TABLE processed_activities";
                        stmt.executeUpdate(dropSQL);
                        System.out.println("processed_activities table dropped");
                    }

                    // Creating processed_activities table
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
                    System.out.println("Creating new processed_activities table...");
                    stmt.executeUpdate(createSQL);
                    System.out.println("processed_activities table created...");
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

                // Process the activities and store them into processed_activities table
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
                        " END AS unit, quantity/2 AS quantity, field, row, farmId, userId FROM activities where unit = 'pack (500g)' "
                        +
                        " UNION " +
                        " SELECT * FROM activities where NOT (unit = 'g' OR unit = 'ml' OR unit = 'pack (500g)'))";

                System.out.println("Inserting data into processed_activities table...");
                stmt.executeUpdate(createSQL);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet displayActivityLogsFarm(String farmID, int from, int toLoad) {
        ResultSet rs = null;
        try {
            // Get activities based on farm id
            String sqlQuery = "SELECT * FROM processed_activities" +
                    " WHERE farmId = " + farmID +
                    " ORDER BY date ASC"+
                    " LIMIT "+from+", "+toLoad;
            rs = stmt.executeQuery(sqlQuery);
            // printActivityLog(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return rs;
    }

    public ResultSet displayActivityLogsFarmer(String farmerID, int from, int toLoad) {
        ResultSet rs = null;
        try {
            // Get activities based on farmer id
            String sqlQuery = "SELECT * FROM processed_activities" +
                    " WHERE userId = " + farmerID +
                    " ORDER BY date ASC"+
                    " LIMIT "+from+", "+toLoad;
            rs = stmt.executeQuery(sqlQuery);
            // printActivityLog(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return rs;
    }

    public ResultSet displayActivityLogsFarmType(String farmID, String type, int from, int toLoad) {
        ResultSet rs = null;
        try {
            // Get activities based on farm id & type of plant / fertilizer / pesticide
            String sqlQuery = "SELECT * FROM processed_activities" +
                    " WHERE farmId = " + farmID +
                    " AND LOWER(type) = LOWER('" + type + "')" +
                    " ORDER BY date ASC"+
                    " LIMIT "+from+", "+toLoad;
            rs = stmt.executeQuery(sqlQuery);
            // printActivityLog(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return rs;
    }

    // This method dispalys activity logs based on the specified farmID, type of materials used, start date, and end date
    public ResultSet displayActivityLogsFarmTypeDate(String farmID, String type, String fromDate, String toDate, int from, int toLoad) {
        ResultSet rs = null;
        try {
            // Get activities from database based on farm id, type of plant / fertilizer / pesticide, start date, and end date
            String sqlQuery = String.format(
                    "SELECT * FROM processed_activities WHERE farmId = %s AND LOWER(type) = LOWER('%s') AND date >= '%s' AND date <= '%s' ORDER BY date ASC LIMIT %d, %d",
                    farmID, type, fromDate, toDate, from, toLoad);
            rs = stmt.executeQuery(sqlQuery);
            // printActivityLog(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return rs;
    }

    // This method dispalys activity logs based on the specified farmID, type of materials used, start date, end date, field number in the farm, row number in the field
    public ResultSet displayActivityLogsFarmTypeDateFieldRow(String farmID, String type, String fromDate, String toDate, String fieldNumber, String rowNumber, int from, int toLoad) {
        ResultSet rs = null;
        try {
            System.out.println("Field Number "+Integer.parseInt(fieldNumber));
            System.out.println("Row Number "+Integer.parseInt(rowNumber));
            // Get activities from database based on farm id, type of plant / fertilizer / pesticide, start date, end date, field number & row number
            String sqlQuery = String.format(
                    "SELECT * FROM processed_activities WHERE farmId = %s AND LOWER(type) = LOWER('%s') AND date >= '%s' AND date <= '%s' AND field = %d AND row = %d ORDER BY date ASC LIMIT %d, %d",
                    farmID, type, fromDate, toDate, Integer.parseInt(fieldNumber), Integer.parseInt(rowNumber), from, toLoad);
            rs = stmt.executeQuery(sqlQuery);
            // printSummarizedActivityLog(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return rs;
    }

    public String printActivityLog(ResultSet rs) {
        String output = ""; 
        try {
            if (!rs.isBeforeFirst()) {
                return "No records found\n";
            } else {
                while (rs.next()) {
                    String action = rs.getString("action");
                    String type = rs.getString("type");
                    int field = rs.getInt("field");
                    int row = rs.getInt("row");
                    int quantity = rs.getInt("quantity");
                    String unit = rs.getString("unit");
                    Date date = rs.getDate("date");

                    output +=  action + " " + type + " Field " + field + " Row " + row + " " + quantity + " "
                    + unit + " " + date.toString() + "\n\n";
                }
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return output;
    }

    public String printSummarizedActivityLog(ResultSet rs) {
        String output = "";
        try {
            if (!rs.isBeforeFirst()) {
                return "No records found\n";
            } else {
                int quantitySum = 0;
                String action = rs.getString("action");
                String type = rs.getString("type");
                int field = rs.getInt("field");
                int row = rs.getInt("row");
                String unit = rs.getString("unit");

                while (rs.next()) {
                    quantitySum += rs.getInt("quantity");
                }

                System.out.println(action + " " + type + " Field " + field + " Row " + row + " " + quantitySum + " " + unit + " ");

                output += action + " " + type + " Field " + field + " Row " + row + " " + quantitySum + " " + unit + " \n\n";
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return output;
    }

    public List<String> getFarmsFarmer(String queryType) {
        String sqlQuery = "";
        if(queryType == "farmer"){
            sqlQuery = "SELECT _id FROM users" +
                    " ORDER BY _id ASC";
        } else {
            sqlQuery = "SELECT _id FROM farms" +
                    " ORDER BY _id ASC";
        }
        ResultSet rs = null;
        List<String> ids = new ArrayList<String>();
        try {
            rs = stmt.executeQuery(sqlQuery);
            
            while(rs.next()){
                ids.add(rs.getString(("_id")));
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return ids;
    }

    public List<String> getChoice(String queryType) {
        String sqlQuery = "";
        if(queryType == "plants"){
            sqlQuery = "SELECT name FROM plants" +
                    " ORDER BY name ASC";
        } else if(queryType == "fertilizers") {
            sqlQuery = "SELECT name FROM fertilizers" +
                    " ORDER BY name ASC";
        } else {
            sqlQuery = "SELECT name FROM pesticides" +
                    " ORDER BY name ASC";
        }
        ResultSet rs = null;
        List<String> names = new ArrayList<String>();
        try {
            rs = stmt.executeQuery(sqlQuery);
            
            while(rs.next()){
                names.add(rs.getString(("name")));
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return names;
    }
}