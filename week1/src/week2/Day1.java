package week2;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day1 {
    static int num = 0;
    static List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

    public static void main(String[] args) throws Exception {
        Executor pool0 = Executors.newSingleThreadExecutor();
        ExecutorService pool1 = Executors.newCachedThreadPool();
        ExecutorService pool2 = Executors.newFixedThreadPool(5);
        ScheduledExecutorService pool3 = Executors.newScheduledThreadPool(1);
//        pool3.schedule(() -> {
//            System.out.println("a task");
//        }, 3000, TimeUnit.MILLISECONDS);
//        pool3.scheduleAtFixedRate(() -> {
//            System.out.println("task run: " + ++num + "times");
//        }, 3000, 1000, TimeUnit.MILLISECONDS);
//    }

        ForkJoinPool pool4 = new ForkJoinPool(3);
//        pool4.execute(() -> {
//            for (int i = 0; i < list.size(); i++) {
//                list.set(i, 2 * list.get(i));
//            }
//            System.out.println(list);
//        });

        Callable<Integer> callable0 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("in the callable");
                return 10;
            }
        };
        Future<Integer> future = pool1.submit(callable0);
        // get() is blocking main thread
        int i = future.get();
        System.out.println(future.get());
        //
        i *= 2;
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
 *              ScheduleThreadPoolExecutor
 *              ForkJoinPool, divide original task into small tasks
 *              For example, we want to double numbers from a list
 *              [1,2,3,4,5] -> [1,2] [3, 4] [5] -> after task finishes double, back to result [2,4,6,8,10]
 */

/**
 * Java 8 new features
 *  1. interface: default static private
 *  2. Functional interface
 *  3. Lambda Expression
 *  4. Optimal
 *  5. Stream / parallelStream
 *  6. CompletableFuture
 */

/**
 *      Functional interface
 *          only contains one abstract method
 *          runnable
 *          callable
 *          runnable vs                                 callable
 *            void                                      return type
 *            no throws, need try catch block           since it has throws, not need try catch block
 *
 *
 *          Function
 *          supplier  () -> return xxx;
 *          Comparator
 *          Comparable
 *          Consumer xxx -> void
 */
//@FunctionalInterface
//interface Inter1 {
//    default void method0(){}
//    default void method1(){}
//    void abstrMethod0();
//}
class Test7 {
    public static void main(String[] args) {

        Comparable comparable = new Comparable() {
            @Override
            public int compareTo(Object o) {
                return 0;
            }
        };

        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        };

        Function<Integer, String> function = new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return "" + integer;
            }
        };
        String res = function.apply(10);
//        Inter inter1 = () -> {};
    }
}

/**
 * Lambda Expression - can only work with functional interface
 * ()
 * xx -> 10
 */

class TestLambda {
    public static void main(String[] args) {
//        Callable<Integer> callable = new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                return null;
//            }
//        };
        Callable<Integer> callable = () -> {return 10;};
        Consumer<String> consumer = str -> {
            System.out.println(str.getClass());
        };
        consumer.accept("aaa");

        Supplier<HashMap> supplier = () -> {
            System.out.println("another logic");
            return new HashMap();
        };
    }
}

/**
 * Optional(wrapper)
 *  avoid null keyword in code
 *  var == null
 *  opt(var)
 *  opt.isPresent
 *  opt.isPresent()
 *      orElse()
 *      orElseGet()
 *
 */
class TestOptional {
    public static void main(String[] args) {
        Optional<Integer> opt = Optional.ofNullable(10);
//        System.out.println(opt.isPresent());
        System.out.println(opt.orElseGet(() -> {
            System.out.println("value is null, so returning default value");
            return 99;
        }));
    }
}

/**
 * Stream API (single thread)
 *  doing work like for loop but more neat and readable
 *  chain operation
 *      consist of intermediate step termination step (only when this termination execute, the pipeline start running)
 *          collect()
 *          reduce() reduce to one single value
 *          forEach()
 */
class TestStream {
    public static void main(String[] args) {
        //List()    *2      >10     -1
//        List<Integer> list = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8));
//        for (int i = 0; i < list.size(); i ++) {
//            list.set(i, list.get(i) * 2);
//        }
//
//        Iterator<Integer> it = list.iterator();
//        while (it.hasNext()) {
//            int curr = it.next();
//            if (curr > 10) {
//                it.remove();
//            }
//        }
//        System.out.println(list);
        // Stream Object(0, 1,2,3,4...9)
        List<Integer> list = IntStream.range(0, 10)
                                      .map((ele) -> {return 2 * ele;})
                                      .filter((x -> x > 10))
                                      .mapToObj(x -> x)
                                        .collect(Collectors.toList());
//        System.out.println(list);

//        Stream stream = IntStream.range(0, 10)
//                .parallel()
//                .map((ele) -> {
//                    System.out.println("step1: " + Thread.currentThread().getName());
//                    return 2 * ele;
//                })
//                .filter(x -> {
//                    System.out.println("step2: " + Thread.currentThread().getName());
//                    return x > 10;
//                })
//                .mapToObj(x -> x);
//
//        stream.collect(Collectors.toList());

        List<List<Integer>> list2 = new LinkedList<>();
        list2.add(Arrays.asList(1,2));
        list2.add(Arrays.asList(3,4));
        list2.add(Arrays.asList(5,6));
        System.out.println(list2);
        List<Integer> list3 = list2.stream().flatMap((eleList) -> {
            return eleList.stream();
        }).collect(Collectors.toList());
    }
}