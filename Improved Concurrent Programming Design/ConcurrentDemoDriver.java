
package concurrentdemodriver;

import static concurrentdemodriver.ConcurrentDemo.l;
import java.util.Iterator;


public class ConcurrentDemoDriver {

    
    public static void main(String[] args) 
            throws InterruptedException
    {
        l.add("A");
        l.add("B");
        l.add("c");
  
        // We create a child thread that is
        // going to modify ArrayList l.
        ConcurrentDemo t = new ConcurrentDemo();
        t.start();
  
        // Now we iterate through the ArrayList
        // and get exception.
        Iterator itr = l.iterator();
        while (itr.hasNext()) {
            String s = (String)itr.next();
            System.out.println(s);
            Thread.sleep(600);
        }
        System.out.println(l);
    }
    
}
