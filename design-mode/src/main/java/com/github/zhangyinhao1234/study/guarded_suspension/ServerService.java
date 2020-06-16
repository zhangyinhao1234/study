package com.github.zhangyinhao1234.study.guarded_suspension;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务端数据处理
 */
public class ServerService {

    private RequestTopic requestTopic;

    private ResponseTopic responseTopic;

    private ExecutorService executorService = Executors.newFixedThreadPool(2);


    public void start() throws InterruptedException {
        init();
        readRequestMsg();
    }

    private void init() {
        requestTopic = new RequestTopic();
        responseTopic = new ResponseTopic();
    }

    public void readRequestMsg() throws InterruptedException {
        for(int i=0;i<4;i++){
            System.out.println("创建监听。。。。");
            executorService.execute(() -> {
                try {
                    run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void run() throws InterruptedException {
        while (true){
            ServerRequest request = requestTopic.getRequest();
            ServerRsponse serverRsponse = doRequest(request);
            responseTopic.sendResult(serverRsponse);
        }
    }


    public ServerRsponse doRequest(ServerRequest request) {
        String msg = request.getMsg();
        System.out.println("服务端：处理id=" + request.getId() + "；消息内容是====" + msg + "的数据");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String r = System.currentTimeMillis()+UUID.randomUUID().toString().replace("-","");
        System.out.println("服务端：对ID是="+request.getId()+"的处理结果是===="+r);
        return new ServerRsponse(request.getId(), r);
    }

}
