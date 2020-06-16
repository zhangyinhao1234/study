package com.github.zhangyinhao1234.study.guarded_suspension;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Web端请求
 */
public class RequestClient {


    private RequestTopic requestTopic =new RequestTopic();

    private ResponseTopic responseTopic = new ResponseTopic();

    private ExecutorService clientExecutor = Executors.newFixedThreadPool(2);

    public void start(){
        listenMsg();
    }



    public ServerRsponse handleWebReq(String msg) throws InterruptedException {

        String id = UUID.randomUUID().toString();
        ServerRequest request = new ServerRequest(id, msg);
        System.out.println("客户端：接收到请求；ID="+request.getId()+"内容===="+request.getMsg());
        requestTopic.sendMsg(request);
        System.out.println("客户端：发送id是="+id+"的数据");
        GuardedObject<ServerRsponse> go= GuardedObject.create(id);
        ServerRsponse serverRsponse = go.get(t -> t != null);
        System.out.println("客户端：收到id是="+id+"的结果是===="+serverRsponse.getResult());

        return serverRsponse;
    }


    private void listenMsg(){
        for(int i=0;i<2;i++){
            clientExecutor.execute(()-> {
                try {
                    loadResult();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }




    private void loadResult() throws InterruptedException {
        while (true){
            ServerRsponse result = responseTopic.getResult();
            GuardedObject.fireEvent(result.getId(),result);
        }
    }





}
