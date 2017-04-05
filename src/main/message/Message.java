package main.message;

import com.expodb.Config.MsgType;

import main.transaction_YCSB.Transaction;

public class Message {
	private MsgType type;
    private int destThreadId; // -1 : default
    private int txnID;
    private String receiverIP;
    private String receiverPort;
    private String senderIp;
    private String senderPort;

    public Message(){}

    public Message(MsgType type, int threadId, int txnId, String receiverIP,
                   String receiverPort, String senderIp, String senderPort){
        this.type = type;
        this.destThreadId = threadId;
        this.txnID = txnId;
        this.receiverIP = receiverIP;
        this.receiverPort = receiverPort;
        this.senderIp = senderIp;
        this.senderPort = senderPort;
    }

    public MsgType getType() {
        return type;
    }

    public int getDestThreadId() {
        return destThreadId;
    }

    public int getTxnID() {
        return txnID;
    }
}
