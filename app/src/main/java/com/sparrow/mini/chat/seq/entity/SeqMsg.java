package com.sparrow.mini.chat.seq.entity;

/**
 * 同步消息单位
 * */
public class SeqMsg {
    private long seqId;
    private Scene scene;
    private String msgBody;

    public long getSeqId() {
        return seqId;
    }

    public Scene getScene() {
        return scene;
    }

    public String getMsgBody() {
        return msgBody;
    }
}
