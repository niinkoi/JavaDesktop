
package EX02;

/**
 *
 * @author Khoi Nguyen
 */
public class Demo_Customer {
    
    public static void main(String... args) {
        Customer_SE140132 c = new Customer_SE140132();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                c.withdraw(2000);
            }
        };
        t1.start();
        
        Thread t2 = new Thread() {
            @Override
            public void run() {
                c.deposit(3000);
            }
          
        };
        t2.start();
    }
}
