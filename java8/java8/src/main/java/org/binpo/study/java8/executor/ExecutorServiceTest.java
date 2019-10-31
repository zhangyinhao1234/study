package org.binpo.study.java8.executor;/**
 * @author 【张殷豪】
 * Date 2019/10/31 14:46
 */

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author yinhao.zhang
 * @Date 2019/10/31 14:46
 **/
public class ExecutorServiceTest {
    
    final ExecutorService executorService = Executors.newFixedThreadPool(5);
    /**
     * 可缓存的线程池，线程数量无线大
     */
    final ExecutorService executorService2 = Executors.newCachedThreadPool();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorServiceTest test = new ExecutorServiceTest();
//        test.callAbleTest();

        test.futureTaskTest();

        test.executorService.shutdown();
    }
    
    
    public void callAbleTest() throws ExecutionException, InterruptedException {
        Future<Integer> submit = this.executorService.submit(new AddCall(1,1));
        Future<Integer> submit2 = this.executorService.submit(new AddCall(2, 2));
        System.out.println(submit.get() + submit2.get());
    }

    public void futureTaskTest() throws InterruptedException {
        List<AddCall> collect = Stream.of(new AddCall(1, 1), new AddCall(2, 2), new AddCall(2, 2)).collect(Collectors.toList());
        List<Future<Integer>> futures = this.executorService.invokeAll(collect);
        AtomicInteger all = new AtomicInteger();
        futures.forEach(f ->{
            try {
                all.addAndGet(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        System.out.println("all: "+all.intValue());
    }
}



class AddCall implements Callable<Integer>{
    int a;
    int b;
    public AddCall(int a,int b){
        this.a = a;this.b = b;
    }
    @Override
    public Integer call() throws Exception {
        return a + b;
    }
}
