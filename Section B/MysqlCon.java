import java.sql.*;  

public class MysqlCon {  
        
        private Connection con;

        public MysqlCon() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");  
                this.con = DriverManager.getConnection ("jdbc:mysql://localhost:3306/ifarm","farmer","farmer");   
            } catch(Exception e) {
                System.out.println(e);
            }
        }

        public Statement conn() {
            Statement stmt = null;
            try {
                stmt = this.con.createStatement();
            } catch(Exception e) {
                System.out.println(e);
            }
            return stmt;
        }

        public void closeConn() {
            try {
                this.con.close();
            } catch(Exception e) {
                System.out.println(e);
            }
        }

}  