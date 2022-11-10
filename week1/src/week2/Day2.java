package week2;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class Day2 {
    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<Integer> future = es.submit(() -> {
            Thread.sleep(3000);
            return 10;});

        System.out.println(future.get());
        System.out.println("main resume");
    }
}
/**
 * CompletableFuture
 *      Fully synchronized
 *      chain operation
 *      customized the pool used
 */
class TestCF {
    public static void main(String[] args) throws Exception {
        Executor es = Executors.newSingleThreadExecutor();
        Supplier supplier = new Supplier() {
            @Override
            public Integer get() {
                try {
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName());
                } catch (Exception ex){
                    System.out.println(ex);
                }
                return 10;
            }
        };
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(supplier, es);
//        System.out.println(cf.get()); // get() and join() are blocking method which block main thread
        cf.thenAccept(x -> {
            // 2nd step
            System.out.println(Thread.currentThread().getName());
            System.out.println(x * 2);
        });
        System.out.println("main is running here");
        cf.join();
    }
}

class TestAllOf {
    static int i = 0;
    static int res = 0;
    public static void main(String[] args) {
        List<CompletableFuture<Integer>> completableFutures = new LinkedList<>();
        for (i = 0; i < 3; i ++) {
            CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
                // making a request to some api
                System.out.println(Thread.currentThread().getName() + " sending request: " + i);
                return 2 * i;
            });
            completableFutures.add(cf);
        }
        CompletableFuture signal = CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0]));
        CompletableFuture<Integer> ans = signal.thenApply(Void -> {
            try {
                for (int i = 0; i < 3; i ++) {
                    res += completableFutures.get(i).get();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            return res;
        });
        signal.join();
        System.out.println(ans);
    }

}