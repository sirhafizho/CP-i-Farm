public class Timer {
    private static long startTime;
    private static long endTime;
    
    public void startTime() {
        startTime = System.nanoTime();
    }
    
    public void endTime () {
        endTime = System.nanoTime();
    }

    public static long getStartTime() {
        return startTime;
    }

    public static long getEndTime() {
        return endTime;
    }
    
    public long timeTaken() {
        return (endTime - startTime);
    }
}
