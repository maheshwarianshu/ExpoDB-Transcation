package main;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

import main.lockManager.LockManager;
import main.message.Message;
import main.queues.MsgQueue;

import static com.expodb.Config.NUMTXNTHREADS;

public class Worker{

    private class NetworkAddress{
        String IP;
        String port;
    }

    private static String workerId;
    private static GlobalPartitionInfo globalPartitionInfo;
    private List<TxnManager> txnManagerList;
    private static List<MsgQueue> msgQueueList;
    private Queue<Message> newTransactionQueue;
    private static LockManager lockManager;
    private static Map<String,NetworkAddress> workerNetworkAddressMapping;
    private DatagramSocket receiveSocket = null;
    private List<Key> dataKeys;
    private static AtomicLong globalTxnTimestamp = new AtomicLong(0L);

	public Worker(String id)
	{
        workerId = id;
        globalPartitionInfo = new GlobalPartitionInfo();

        initializeDataKeys();
        lockManager = new LockManager(dataKeys);

        newTransactionQueue = new ConcurrentLinkedQueue<>();
        workerNetworkAddressMapping = new HashMap<>();
        initializeMsgQueues();

        for(TxnManager t : txnManagerList){
            Thread thread = new Thread(t);
            thread.start();
        }

        //TODO: read ip and port from properties file

        try {
            receiveSocket = new DatagramSocket(9876);
        } catch (SocketException e) {
            e.printStackTrace();
        }
	}

	private void initializeDataKeys(){
        //TODO: Get RIDs/Keys from storage layer
        //TODO: give keyIDs for ordering the keys
    }

	private void initializeMsgQueues(){
        msgQueueList = new ArrayList<>();
        for(int i=0; i<NUMTXNTHREADS; i++){
            MsgQueue msgQueue =new MsgQueue(newTransactionQueue, workerId);
            msgQueueList.add(msgQueue);
            TxnManager txnManager = new TxnManager(msgQueue, i);
            txnManagerList.add(txnManager);
        }
    }

	public ObjectInputStream recieve()
	{
        byte[] incomingData = new byte[1024];
        DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);

        try {
            receiveSocket.receive(incomingPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] data = incomingPacket.getData();
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream inputStream = null;

        try {
            inputStream = new ObjectInputStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputStream;
    }
	
	public void runWorker()
	{
        while(true){
            ObjectInputStream inputStream = recieve();
            Message msg = null;
            try {
                msg = (Message) inputStream.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            int threadId = msg.getTxnThreadId();
            msgQueueList.get(threadId).enqueue(msg);
        }
	}

    public static LockManager getLockManager() {
        return lockManager;
    }

    public static void setLockManager(LockManager lockManager) {
        Worker.lockManager = lockManager;
    }

    public static Long getTimeStamp(){
        return globalTxnTimestamp.incrementAndGet();
    }

    public static String getWorkerId(){
        return workerId;
    }

    public static void setWorkerId(String workerId) {
        Worker.workerId = workerId;
    }

    public static GlobalPartitionInfo getGlobalPartitionInfo() {
        return globalPartitionInfo;
    }

    public static void setGlobalPartitionInfo(GlobalPartitionInfo globalPartitionInfo) {
        Worker.globalPartitionInfo = globalPartitionInfo;
    }

    public List<TxnManager> getTxnManagerList() {
        return txnManagerList;
    }

    public void setTxnManagerList(List<TxnManager> txnManagerList) {
        this.txnManagerList = txnManagerList;
    }

    public static List<MsgQueue> getMsgQueueList() {
        return msgQueueList;
    }

    public static void setMsgQueueList(List<MsgQueue> msgQueueList) {
        Worker.msgQueueList = msgQueueList;
    }

    public Queue<Message> getNewTransactionQueue() {
        return newTransactionQueue;
    }

    public void setNewTransactionQueue(Queue<Message> newTransactionQueue) {
        this.newTransactionQueue = newTransactionQueue;
    }

    public static Map<String, NetworkAddress> getWorkerNetworkAddressMapping() {
        return workerNetworkAddressMapping;
    }

    public static void setWorkerNetworkAddressMapping(Map<String, NetworkAddress> workerNetworkAddressMapping) {
        Worker.workerNetworkAddressMapping = workerNetworkAddressMapping;
    }

    public DatagramSocket getReceiveSocket() {
        return receiveSocket;
    }

    public void setReceiveSocket(DatagramSocket receiveSocket) {
        this.receiveSocket = receiveSocket;
    }

    public static AtomicLong getGlobalTxnTimestamp() {
        return globalTxnTimestamp;
    }

    public static void setGlobalTxnTimestamp(AtomicLong globalTxnTimestamp) {
        Worker.globalTxnTimestamp = globalTxnTimestamp;
    }
}
