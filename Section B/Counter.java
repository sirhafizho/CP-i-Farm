import java.util.concurrent.atomic.AtomicInteger;
public class Counter {
    private AtomicInteger c = new AtomicInteger(1);

      public void increment() {
         c.getAndIncrement();
      }

      public int value() {
         return c.get();
      }

      public int getAndIncrement() {
         return c.getAndIncrement();
      }

}
