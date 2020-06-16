package com.github.zhangyinhao1234.study.guarded_suspension;

import java.util.concurrent.LinkedBlockingQueue;

public class RequestTopic {


    private static LinkedBlockingQueue<ServerRequest> requestQueue = new LinkedBlockingQueue<ServerRequest>();


    public void sendMsg(ServerRequest request) throws InterruptedException {
        requestQueue.put(request);
    }

    public ServerRequest getRequest() throws InterruptedException {
        return requestQueue.take();
    }
}
