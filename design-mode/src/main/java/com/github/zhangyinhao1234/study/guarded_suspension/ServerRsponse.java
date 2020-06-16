package com.github.zhangyinhao1234.study.guarded_suspension;

public class ServerRsponse {
    String id;

    String result;

    public ServerRsponse(String id, String result) {
        this.id = id;
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
