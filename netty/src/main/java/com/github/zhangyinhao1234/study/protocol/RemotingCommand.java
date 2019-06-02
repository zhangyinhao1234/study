package com.github.zhangyinhao1234.study.protocol;

/**
 * @author 【张殷豪】
 * Date 2019/6/2 15:04
 */
public class RemotingCommand {
    String uid;

    private transient byte[] body;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
