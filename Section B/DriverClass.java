public class DriverClass {
    public static void main(String[] args) {
        // Runnable farmer = new Farmer("1", "ABCD", "abcd@example.com", "abcd12345", "012-3456789", new String[0]);

        // Thread t1 = new Thread(farmer, "t1");

        // t1.start();

        // Establish the DB connection
        // MysqlCon con = new MysqlCon();

        // Farmer f = new Farmer(con.conn(), "1");
        // Thread t = new Thread(f);
        // t.start();

        // // Close DB connection
        // con.closeConn();

        FarmerSimulator simulator = new FarmerSimulator();

        Farmer[] farmers = simulator.generateFarmers(4);

        // TODO: Sequential activity generation here
        for (Farmer farmer : farmers) {
            /*
                Idea to create a sequential activity generation class which takes
                farmer object as a parameter in its constructor and generates the 
                activities for each farm
            */
            SequentialActivity activity = new SequentialActivity(farmer);
            
        }


    }
}
