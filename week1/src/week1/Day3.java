package week1;

public class Day3 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
    }
    // stack example, local variable and method parameters are stored in stack
    void method1(int param) {
        int a = 1;
    }

}
/**
 *  1. JVM memory model
 *       heap    stack(invoke in stack would last in, first out)
 *       stack:
 *          frame which hold local reference variable
 *          method parameters
 *          each thread has its own stack
 *       heap:
 *          object, including attributes, fields
 *          when object created, like new Object(), it will first be created in eden -> after several gc
 *                                                                              -> sur1/sur2
 *                                                                              -> promote to old generation
 *                  [o1   03] empty slot doest not have enough space in memory, so we want to compact object
 *                  copy and paste old object to other area
 *                            [o1   o3]
 *          [eden][survivor1][survivor2] young generation (serial GC/parallel GC/ parallel New GC)
 *          [] old generation: much larger memory size (serial GC/ parallel old gc/ CMS concurrent mark sweep)
 *          [                           ] old generation
 *          [                           ] meta space, method area
 *
 *
 *          objects will survive few periods then go to old generations
 *
 *          gc called automatically from java 8
 *          not force you call System.gc()
 *          STOP THE WORLD -- freeze program
 *
 *          CMS:
 *              1. initial mark(STW) -mark object is live or not
 *              2. concurrent mark
 *              3. final mark(STW) - final check in case some objects still alive
 *              4. concurrent sweep
 *
 *          minor gc: (young) parallel New GC
 *          major gc: (old) parallel old gc, CMS
 *          full gc(both in young and old): G1
 *          [][][s][e][] divide memory into multiple block
 *          [][][][o][]  random assign block with eden, old, survivor generation
 *          [][][][][]
 *          gc root: this function likes a tree, check which object or class is alive or not
 *
 *          example:
 *          new Test(); -> no reference points to it so GC would collect and remove it
 *          Test t = new Test() -> would be collected
 */

/**
 * 2. Multithreading
 *      Create a new thread:
 *          1. Extends a thread class
 *          2. Implements a runnable interface provide a task to your thread
 *
 *      Life cycle of thread:
 *          1. new Thread(): new state, waiting
 *          2. start(): active run/runnable one core do multithreading
 *          3. sleep() wait(): block wait
 *          4. Terminate stage: once thread is terminated, you can no longer turn it again
 *
 *      ThreadPool
 *
 */
class TestThread extends Thread {
    // if we override start(), we can no longer create new thread
//    @Override
//    public void start() {
//        System.out.println(Thread.currentThread().getName());
//    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) throws Exception {
        System.out.println(Thread.currentThread().getName());
        TestThread testThread = new TestThread();
//        testThread.run();
        testThread.start(); // create new thread

        Thread thread = new Thread(new Task());
        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        thread.start();
    }
}

class Task implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
/**
 * Race condition
 *      i = 0;
 *      thread0(+1) thread1(+1)
 *      i = 1        i = 1
 */

/**
 * 3. synchronized -- make code block critical
 *      monitor -- like a lock
 *
 */
class TestRaceCondition {
    static int num = 0;
    synchronized static void add() {
        num ++;
    }

    public static void main(String[] args) throws Exception {
        Object lock = new Object();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i ++) {
                // this is critical code block
//                synchronized (TestRaceCondition.class) {
//                    num ++;
//                }
//                synchronized (lock) {
//                    num ++;
//                }
                num ++;
//                add();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i ++) {
                num ++;
//                synchronized (TestRaceCondition.class) {
//                    num ++;
//                }
//                add();
            }
        });

        t1.start();
        t2.start();
        // block the main to let main to wait for t1 and t2 to finish
        t1.join();
        t2.join();
        System.out.println(num);
    }
}

/**
 * 4. volatile
 *      cache
 *      memory
 */
