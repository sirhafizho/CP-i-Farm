public class DriverClass {
    public static void main(String[] args) {
        Runnable farmer = new Farmer("1", "ABCD", "abcd@example.com", "abcd12345", "012-3456789", new String[0]);

        Thread t1 = new Thread(farmer, "t1");

        t1.start();
    }
}
