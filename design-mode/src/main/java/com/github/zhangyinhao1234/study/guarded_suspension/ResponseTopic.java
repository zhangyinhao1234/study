package com.github.zhangyinhao1234.study.guarded_suspension;

import java.util.concurrent.LinkedBlockingQueue;

public class ResponseTopic {
    private static LinkedBlockingQueue<ServerRsponse> responseQueue = new LinkedBlockingQueue<ServerRsponse>();


    public void sendResult(ServerRsponse rsponse) throws InterruptedException {
        responseQueue.put(rsponse);
    }

    public ServerRsponse getResult() throws InterruptedException {
        return responseQueue.take();
    }



}
