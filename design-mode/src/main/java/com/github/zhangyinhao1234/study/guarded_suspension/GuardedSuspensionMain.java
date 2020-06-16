package com.github.zhangyinhao1234.study.guarded_suspension;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Guarded Suspension意为【保护暂停】，
 * 其核心思想是仅当服务进程准备好时，才提供服务。
 * 设想一种场景，服务器可能在很短时间内承受大量的客户请求，
 * 客户端请求的数量可能超过服务器本身的处理能力，而服务器又不能丢弃任何一个客户请求。
 * 此时，最佳的处理方案莫过于让客户端请求排队，由服务端程序一个接一个处理，
 * 这样，既保证了所有的客户端程序不丢失，同时也避免了服务器由于处理太多的请求而崩溃。
 *
 *
 * 模拟的例子是Web服务获取一个请求后，发送消息到消息队列（Kafka），等到下游服务处理完成返回对应的数据后，获取消息队列数据，返回给前端。
 * 所以消息队列有两个topic，
 * 放请求数据的topic，下游系统处理数据
 * 获取结果的topic，下游系统处理完成后将数据放入这个topic，等上游系统消费
 */
public class GuardedSuspensionMain {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService clientExecutor = Executors.newFixedThreadPool(10);

        ServerService serverService = new ServerService();
        serverService.start();

        RequestClient requestClient = new RequestClient();
        requestClient.start();

        for(int i=0;i<20;i++){
            int finalI = i;
            clientExecutor.execute(()-> {
                try {
                    requestClient.handleWebReq(""+ finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }


    }


}
