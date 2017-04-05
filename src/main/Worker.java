package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

import main.lockManager.LockManager;
import main.message.Message;
import main.queues.MsgQueue;

import static com.expodb.Config.NUMTXNTHREADS;

public class Worker{

	Integer workerId;
	GlobalPartitionInfo globalPartitionInfo;
	List<TxnManager> txnManagerList;
	List<MsgQueue> msgQueueList;
    Queue<Message> newTransactionQueue;
	LockManager lockManager;
    //AtomicLong globalTxnID;

	public Worker()
	{
        globalPartitionInfo = new GlobalPartitionInfo();
        lockManager = new LockManager();
        newTransactionQueue = new ConcurrentLinkedQueue();
        //globalTxnID = new AtomicLong(0L);

        msgQueueList = new ArrayList<>();
        for(int i=0; i<NUMTXNTHREADS; i++){
            MsgQueue msgQueue =new MsgQueue(newTransactionQueue);
            msgQueueList.add(msgQueue);
            TxnManager txnManager = new TxnManager(msgQueue);
            txnManagerList.add(txnManager);
        }

        for(TxnManager t : txnManagerList){
            Thread thread = new Thread(t);
            thread.start();
        }
	}

	public void recieve(Message msg)
	{
		
	}
	
	public static void main(String [] args)
	{
		
	}
}
