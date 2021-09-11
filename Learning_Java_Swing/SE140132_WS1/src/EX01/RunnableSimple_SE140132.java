
package EX01;

/**
 *
 * @author Khoi Nguyen
 */
public class RunnableSimple_SE140132 implements Runnable{

    @Override
    public void run() {
        System.out.println("Code :" + "SE140132");
        System.out.println("Full Name :" + "Nguyen Khoi Nguyen");
        System.out.println("Class :" + "Software Engineering");
    }
    
    public static void main(String... args) {
        RunnableSimple_SE140132 runnable = new RunnableSimple_SE140132();
        Thread t1 = new Thread(runnable);
        t1.start();
    }
    
}
