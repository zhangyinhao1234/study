package org.binpo.study.java8.concurrent;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @Author yinhao.zhang
 * @Date 2019/11/4 15:15
 * 有序队列，也叫优先级队列，add元素后会进行排序
 **/
public class PriorityBlockingQueueTest {

    private PriorityBlockingQueue<User> queue = new PriorityBlockingQueue<User>(5);

    public static void main(String[] args) throws InterruptedException {

        PriorityBlockingQueueTest test = new PriorityBlockingQueueTest();
        test.add();
        test.readOne();
        test.addOne();
        test.read();
    }

    private void add() {
        queue.add(new User(10, "zhangsan"));
        queue.add(new User(44, "lisi"));
        queue.add(new User(32, "wangwu"));
        queue.add(new User(15, "zhaoliu"));
        queue.add(new User(22, "zhuzhu"));
    }

    private void addOne() {
        queue.add(new User(8, "zhangsan"));

    }

    private void readOne() throws InterruptedException {
        User take = queue.take();
        System.out.println("age:" + take.age + "   name:" + take.name);
    }

    private void read() throws InterruptedException {

        while (true) {
            User take = queue.take();
            System.out.println("age:" + take.age + "   name:" + take.name);

        }
    }

    static class User implements Comparable<User> {

        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public int compareTo(User o) {
            return this.age < o.age ? -1 : 1;
        }
    }
}
