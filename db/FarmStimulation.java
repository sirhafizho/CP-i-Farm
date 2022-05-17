
import java.sql.*;


public class FarmStimulation {

    public static void main(String[] args) {
        
        
        
        try{
            Connection con = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/i-farm","root","");
            System.out.println("Connection is established successfully!");
            Statement stmt = con.createStatement();
//            stmt.executeUpdate("insert into fertilizers values ('3','NP 10 30 0','pack');");
//            System.out.println("Record inserted successfully!");
            ResultSet ferti = stmt.executeQuery("Select * from fertilizers;");
            while(ferti.next()){
                System.out.println(ferti.getInt("_id")+" | "+ferti.getString("name")+" | "+ferti.getString("unitType"));
            }
            stmt.close();
            con.close();
            
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        
        
    }
    
}
