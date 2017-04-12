package main.message;

import expodb.util.Config.MsgType;

import java.io.Serializable;

public class Message implements Serializable{

    private MsgType type;
    private int txnThreadId; //default id = -1
    private String txnID; //default NULL
    private Integer coordinatorID;

    private String receiverIP;
    private String receiverPort;
    private String senderIp;
    private String senderPort;

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

    public String getReceiverIP() {
        return receiverIP;
    }

    public void setReceiverIP(String receiverIP) {
        this.receiverIP = receiverIP;
    }

    public String getReceiverPort() {
        return receiverPort;
    }

    public void setReceiverPort(String receiverPort) {
        this.receiverPort = receiverPort;
    }

    public String getSenderIp() {
        return senderIp;
    }

    public void setSenderIp(String senderIp) {
        this.senderIp = senderIp;
    }

    public String getSenderPort() {
        return senderPort;
    }

    public void setSenderPort(String senderPort) {
        this.senderPort = senderPort;
    }
}
