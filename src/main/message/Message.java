package main.message;

import com.expodb.Config.MsgType;

import main.transaction_YCSB.Transaction;

import java.io.Serializable;

public class Message implements Serializable{

    private MsgType type;
    private int txnThreadId; //default id = -1
    private String txnID; //default NULL
    private Integer coordinatorID;

    /*private String receiverIP;
    private String receiverPort;
    private String senderIp;
    private String senderPort;*/

    public Message(){}

    public Message(MsgType type, int threadId, String txnId, int coordinatorID){
        this.type = type;
        this.txnThreadId = threadId;
        this.txnID = txnId;
        this.coordinatorID = coordinatorID;
    }

    public void setType(MsgType type) {
        this.type = type;
    }

    public int getTxnThreadId() {
        return txnThreadId;
    }

    public void setTxnThreadId(int txnThreadId) {
        this.txnThreadId = txnThreadId;
    }

    public void setTxnID(String txnID) {
        this.txnID = txnID;
    }

    public Integer getCoordinatorID() {
        return coordinatorID;
    }

    public void setCoordinatorID(Integer coordinatorID) {
        this.coordinatorID = coordinatorID;
    }

    public MsgType getType() {
        return type;
    }

    public String getTxnID() {
        return txnID;
    }

}
