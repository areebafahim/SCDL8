
public class DeadlockExample {
    // Create three lock objects
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();
    private static final Object lock3 = new Object();

    public static void main(String[] args) {
        // Create three threads
        Thread thread1 = new Thread(new Task1());
        Thread thread2 = new Thread(new Task2());
        Thread thread3 = new Thread(new Task3());

        // Start the threads
        thread1.start();
        thread2.start();
        thread3.start();
    }

    // Task 1: Locks lock1 and then lock2
    static class Task1 implements Runnable {
        public void run() {
            synchronized (lock1) {
                System.out.println("Thread 1: Locked lock1");
                try { Thread.sleep(50); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                synchronized (lock2) {
                    System.out.println("Thread 1: Locked lock2");
                }
            }
        }
    }

    // Task 2: Locks lock2 and then lock3
    static class Task2 implements Runnable {
        public void run() {
            synchronized (lock2) {
                System.out.println("Thread 2: Locked lock2");
                try { Thread.sleep(50); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                synchronized (lock3) {
                    System.out.println("Thread 2: Locked lock3");
                }
            }
        }
    }

    // Task 3: Locks lock3 and then lock1
    static class Task3 implements Runnable {
        public void run() {
            synchronized (lock3) {
                System.out.println("Thread 3: Locked lock3");
                try { Thread.sleep(50); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                synchronized (lock1) {
                    System.out.println("Thread 3: Locked lock1");
                }
            }
        }
    }
}
   

