
package EX01;

/**
 *
 * @author Khoi Nguyen
 */
public class ThreadSimple_SE140132 extends Thread {

    @Override
    public void run() {
        System.out.println("Age :" + 20);
        System.out.println("Full Name :" + "Nguyen Khoi Nguyen");
        System.out.println("Address :" + "Ho Chi Minh City");
    }
    
    public static void main(String... args) {
        ThreadSimple_SE140132 t1 = new ThreadSimple_SE140132();
        t1.start();
    }
    
}
