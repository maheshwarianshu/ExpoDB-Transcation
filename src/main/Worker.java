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

	private String workerId;
    private GlobalPartitionInfo globalPartitionInfo;
    private List<TxnManager> txnManagerList;
    private List<MsgQueue> msgQueueList;
    private Queue<Message> newTransactionQueue;
    private LockManager lockManager;
    private Map<String,NetworkAddress> workerNetworkAddressMapping;
    private DatagramSocket receiveSocket = null;
    private static AtomicLong globalTxnTimestamp = new AtomicLong(0L);

	public Worker(String id)
	{
        this.workerId = id;
        this.globalPartitionInfo = new GlobalPartitionInfo();
        this.lockManager = new LockManager();
        this.newTransactionQueue = new ConcurrentLinkedQueue();
        this.workerNetworkAddressMapping = new HashMap<>();

        this.msgQueueList = new ArrayList<>();
        for(int i=0; i<NUMTXNTHREADS; i++){
            MsgQueue msgQueue =new MsgQueue(newTransactionQueue, workerId);
            this.msgQueueList.add(msgQueue);
            TxnManager txnManager = new TxnManager(msgQueue);
            this.txnManagerList.add(txnManager);
        }

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

	public static Long getTimeStamp(){
        return globalTxnTimestamp.incrementAndGet();
    }
}
