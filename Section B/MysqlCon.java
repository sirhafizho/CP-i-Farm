import java.sql.*;  

public class MysqlCon {  
        
        private Connection con;

        public MysqlCon() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");  
                // database name ifarm, user: root, pwd: ""
                this.con = DriverManager.getConnection ("jdbc:mysql://localhost:3306/ifarm","root","root");
                System.out.println("Database connected"); 
            } catch(Exception e) {
                System.out.println(e);
            }
        }

        public Connection getCon() {
            return con;
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