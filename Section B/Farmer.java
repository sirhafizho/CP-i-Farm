import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

import com.mysql.cj.xdevapi.Statement;

public class Farmer implements Runnable {
    private String farmerId;
    private String[] farms;
    private Random random;
    MysqlCon mysqlCon = new MysqlCon();

    public Farmer(String farmerId, String[] farms) {
        this.farmerId = farmerId;
        this.farms = farms;
        this.random = new Random();
    }

    public String generateDate() {
        NumberFormat formatter = new DecimalFormat("00");
        String year = formatter.format(random.nextInt(22));
        String month = formatter.format(1 + random.nextInt(12));
        String day = formatter.format(1 + random.nextInt(30));
        String date = "20" + year + "-" + month + "-" + day;
        return date;
    }

    @Override
    public void run() {
        // loop for all farms associated with the farmer
        for(String farm : this.farms){

            // get the arr of plants, pesticides and fertilizers of that farm
            Statement stmt = mysqlCon.conn();
            String[] plants = new String[0];
            String[] fertilizers = new String[0];
            String[] pesticides = new String[0];
            try {  
                ResultSet rs = stmt.executeQuery("select * from farms where _id =" + farm);  
                while(rs.next()) {
                    plants = rs.getString("plants").split(",");
                    fertilizers = rs.getString("fertilizers").split(",");
                    pesticides = rs.getString("pesticides").split(",");
                }
            } catch (Exception e) { 
                System.out.println(e);
            } 

            // System.out.println("Plants: "+ Arrays.toString(plants));
            // System.out.println("Fertilizers: "+ Arrays.toString(fertilizers));
            // System.out.println("Pesticides: "+ Arrays.toString(pesticides));

            int numOfActivity = 1 + this.random.nextInt(12);
            // System.out.println("Random number: " + numOfActivity);
            for(int i=0; i<numOfActivity; i++){
                String date = generateDate();

                int act = 1 + this.random.nextInt(5);
                String action = "";
                String type = "";
                String unit = "kg";
                int quantity = 1 + this.random.nextInt(10);
                int field = 1 + this.random.nextInt(10);
                int row = 1 + this.random.nextInt(10);
                String id = "";

                if(act == 1) {
                    action = "sowing";
      	            String plantId = plants[this.random.nextInt(plants.length)];
                    try {  
                        ResultSet rs = stmt.executeQuery("select * from plants where _id =" + plantId);  
                        while(rs.next()) {
                            type = rs.getString("name");
                        }
                    } catch (Exception e) { 
                        System.out.println(e);
                    }
                    unit = "kg";
                } else if(act == 2) {
                    action = "harvest";
                    String plantId = plants[this.random.nextInt(plants.length)];
                    try {  
                        ResultSet rs = stmt.executeQuery("select * from plants where _id =" + plantId);  
                        while(rs.next()) {
                            type = rs.getString("name");
                        }
                    } catch (Exception e) { 
                        System.out.println(e);
                    }
                } else if(act == 3) {
                    action = "pesticide";
                    String pesticideId = pesticides[this.random.nextInt(pesticides.length)];
                    try {  
                        ResultSet rs = stmt.executeQuery("select * from pesticides where _id =" + pesticideId);  
                        while(rs.next()) {
                            type = rs.getString("name");
                        }
                    } catch (Exception e) { 
                        System.out.println(e);
                    }
                } else if(act == 4) {
                    action = "fertilizer";
                    String fertilizerId = fertilizers[this.random.nextInt(fertilizers.length)];
                    try {  
                        ResultSet rs = stmt.executeQuery("select * from fertilizers where _id =" + fertilizerId);  
                        while(rs.next()) {
                            type = rs.getString("name");
                        }
                    } catch (Exception e) { 
                        System.out.println(e);
                    }
                } else {
                    action = "sales";
                    String plantId = plants[this.random.nextInt(plants.length)];
                    try {  
                        ResultSet rs = stmt.executeQuery("select * from plants where _id =" + plantId);  
                        while(rs.next()) {
                            type = rs.getString("name");
                        }
                    } catch (Exception e) { 
                        System.out.println(e);
                    }
                } 

                try{
                    String preparedSQL = "INSERT INTO activities(_id, date, action, type, unit, quantity, field, row, farmId, userId) "+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement pstmt = mysqlCon.getCon().prepareStatement(preparedSQL);   

                    pstmt.setString(1, Integer.toString(i));
                    pstmt.setString(2, date);
                    pstmt.setString(3, action);
                    pstmt.setString(4, type);
                    pstmt.setString(5, unit);
                    pstmt.setInt(6, quantity);
                    pstmt.setInt(7, field);
                    pstmt.setInt(8, row);
                    pstmt.setString(9, farm);
                    pstmt.setString(10, farmerId);

                    pstmt.executeUpdate();
                    
                }catch(SQLException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
