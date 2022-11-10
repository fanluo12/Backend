package week1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Day4 {
    volatile static boolean flag = true;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println("t1 is sleeping");
            flag = false;
        });

        Thread t2 = new Thread(() -> {
            while (flag) {

            }
        System.out.println("out from while loop!");
        });

        t1.start();
        t2.start();
    }
}
/** volatile(just applied for variables)
 *      1. provide visibility between different threads, cannot provide thread-safe
 *      2. m fence
 *      3. prevent code from reordering
 */

/** 2. CAS compare and set(atomic two step happen in one instruction) optimistic lock
 *      num = 0
 *      t1            t2
 *      num + 1 when num == expected value
 *
 *      true
 *      false
 *      while(!CAS) {}
 *         num = 0
 *         t1       t2
 *         1 curr == old read(exp)
 *         unsafe.compareAndSet(object, (offset)attributes, old + 1, old)
 */
class TestAtomicLibrary {
    static AtomicInteger ai = new AtomicInteger();
    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i ++) {
                ai.incrementAndGet();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i ++) {
                ai.incrementAndGet();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(ai.get());
    }
}

/**
 * 1. synchronized
 * 2. volatile + cas
 *          Hashtable vs CpncurrentHashMap 16 nodes allows 16 thread same time
 */

/** Deadlock
 *                        t1                               t2
 *      holding         lock1                             lock2
 *      try to acquire  lock2                             lock1
 *      Solution:
 *          1. lock in order
 *          2. set time out, when cannot acquire the lock for certain time, then give up the lock it is holding
 *          3. try lock
 */
class TestDeadLock {
    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();
        Thread t1 = new Thread(() -> {
            try {
                synchronized (lock1) {
                    Thread.sleep(1000);
                    System.out.println("t1 trying to lock t2");
                    synchronized (lock2) {
                        System.out.println("t1 finished");
                    }
                }
            } catch (Exception e) {}
        });

        Thread t2 = new Thread(() -> {
            try {
                synchronized (lock2) {
                    Thread.sleep(1000);
                    System.out.println("t2 trying to lock t1");
                    synchronized (lock1) {
                        System.out.println("t2 finished");
                    }
                }
            } catch (Exception e) {}
        });

//        Thread t2 = new Thread(() -> {
//            synchronized (lock2) {
//                synchronized (lock1) {
//                    System.out.println("t2 finished");
//                }
//            }
//        });

        t1.start();
        t2.start();
    }
}

/**
 * cons of synchronized
 *      1. no try lock
 *      2. no fair lock
 *      3. no multiple waiting list
 */

/**
 * ReentrantLock
 *      1. lock.lock(): lock multiple times
 *              lock.lock();
 *              tryLock();
 *              Condition multiple waiting list
 */

class TestReentrantLock {
    static ReentrantLock lock = new ReentrantLock();
    static Condition list0 = lock.newCondition();
    static Condition list1 = lock.newCondition();

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("t1 has the lock, then release lock");
                //..critical part
                list0.await();
                System.out.println("t1 wakes up");
                lock.unlock();
            } catch (Exception e) {
                System.out.println(e);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                lock.lock();
                //..critical part
                System.out.println("t2 has the lock");
                Thread.sleep(2000);
                System.out.println("t2 end sleeping");
                list0.signal();
                lock.unlock();
            } catch (Exception e) {
                System.out.println(e);
            }
        });

        t1.start();
        t2.start();
    }
}

/**
 * Blocking queue
 * producer -> [][][][][] -> consumer
 *  when queue is full, block producer
 *  when queue is empty, block consumer
 */
class MyBlockingQueue {
    int capacity = 16;
    ReentrantLock lock = new ReentrantLock();
    Condition producerList = lock.newCondition();
    Condition consumerList = lock.newCondition();
    Queue<Integer> queue = new LinkedList<>(); // first in first out queue
    // method called by producer
    boolean offer(int ele) throws Exception {
        lock.lock();
        while (queue.size() == capacity) {
            producerList.await();
        }
        queue.offer(ele);
        consumerList.signal();
        lock.unlock();
        return true;
    }
    // method called by consumer
    int poll() throws Exception {
        lock.lock();
        while (queue.isEmpty()) {
            consumerList.await();
        }
        int v = queue.poll();
        producerList.signal();
        lock.unlock();
        return v;
    }

}

class TestQueue {
    public static void main(String[] args) {
        MyBlockingQueue myBlockingQueue = new MyBlockingQueue();
        Thread t1 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i ++) {
                    myBlockingQueue.offer(i);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i ++) {
                    System.out.println(myBlockingQueue.poll());
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        });

        t1.start();
        t2.start();
    }
}

/**
 * ThreadPool
 *      create a thread is expensive
 *      blocking queue(task0, task1)
 *      pool(worker0, worker1)
 */

/**
 * Executor, ExecutorService, provide method to work with pool
 * Executors, ThreadPoolExecutor
 */

class TestThreadPool {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        pool.execute(() -> {
            System.out.println("this is task1");
        });
        pool.execute(() -> {
            System.out.println("this is task2");
        });
        pool.execute(() -> {
            System.out.println("this is task3");
        });
        pool.shutdown();
    }
}