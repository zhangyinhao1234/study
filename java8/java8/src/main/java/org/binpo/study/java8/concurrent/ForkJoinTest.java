package org.binpo.study.java8.concurrent;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @Author yinhao.zhang
 * @Date 2019/11/5 17:42
 * 使用并行计算计算一个大数组的值加法计算
 **/
public class ForkJoinTest {

    public static void main(String[] args) {
        ForkJoinTest test = new ForkJoinTest();
        long[] num = test.getInitNum();

        ForkJoinPool forkJoinPool = new ForkJoinPool(5);
        SumTask sumTask = new SumTask(num, 0, num.length);
        Long invoke = forkJoinPool.invoke(sumTask);
        System.out.println("计算结果："+invoke);

    }

    private long[] getInitNum(){
        long[] num = new long[1000];
        Random random = new Random();
        for (int i=0;i<1000;i++){
            num[i] = random.nextInt(10000);
        }
        return num;
    }

    static class  SumTask extends RecursiveTask<Long>{

        final int shol = 100;

        long[] array;
        int start;
        int end;

        public SumTask(long[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }


        @Override
        protected Long compute() {
            //小任务直接计算
            if(end- start <= shol){
                System.out.println("执行简单计算。。。。。");
                return sum();
            }
            //大任务一分为二
            int m = (end + start) / 2;
            SumTask sumTask1 = new SumTask(this.array, start, m);
            SumTask sumTask2 = new SumTask(this.array, m, end);

            invokeAll(sumTask2,sumTask1);
            Long l1 = sumTask1.join();
            Long l2 = sumTask2.join();
            System.out.println("拆分计算：  l1："+l1 + "   l2:"+l2);
            return l1+l2;

        }

        private long sum(){
            long sum = 0;
            for(int i=0;i<array.length;i++){
                sum +=array[i];
            }
            return sum;
        }


    }
}
