
package concurrentdemodriver;

// Java program to illustrate ConcurrentCollection uses
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.*;

class ConcurrentDemo extends Thread {
    static CopyOnWriteArrayList l = 
                     new CopyOnWriteArrayList();
    
    //will throw exception error if use arraylist
    //static ArrayList l = 
    //                 new ArrayList();
    
    public void run()
    {
        try {
            Thread.sleep(200);
        }
        catch (InterruptedException e) {
            System.out.println("Child Thread"
                     + " going to add element");
        }
  
        // Child thread trying to add new
        // element in the Collection object
        l.add("D");
    }
  
        
}
