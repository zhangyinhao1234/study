package com.github.zhangyinhao1234.study.guarded_suspension;

import java.util.function.Predicate;

public class ServerRequest  {

    String id;

    String msg;

    public ServerRequest(String id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
