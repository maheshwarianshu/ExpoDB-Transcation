package main.lockManager;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.expodb.Config;
import main.Key;
import main.transaction_YCSB.Transaction;

public class LockNode {
    private Key key;
    private Lock spinlock;
    private Config.LLockType keyNodeLock; //Read,Write,Free
	private String accessTimeStamp;
	private Queue<TxnWaitQueueNode> waitTxnQueue;
	
    public LockNode(Key key) {
        this.key = key;
        spinlock = new ReentrantLock();
        keyNodeLock = Config.LLockType.FREE;
        accessTimeStamp = null;
        waitTxnQueue = new LinkedList<>();
    }

    public void enqueue(Transaction txn, List<LockRequest> readWriteSet){
        TxnWaitQueueNode txnWaitQueueNode = new TxnWaitQueueNode(txn.getTxnID(), txn.getThreadId(),
                                                    txn.getTxnTimeStamp(), readWriteSet);
        // TODO: spin lock to add to queue
    }

    public String getAccessTimeStamp() {
        return accessTimeStamp;
    }

    public void setAccessTimeStamp(String accessTimeStamp) {
        this.accessTimeStamp = accessTimeStamp;
    }

    public Config.LLockType getKeyNodeLock() {
        return keyNodeLock;
    }

    public void setKeyNodeLock(Config.LLockType keyNodeLock) {
        this.keyNodeLock = keyNodeLock;
    }

    public Queue<TxnWaitQueueNode> getWaitTxnQueue() {
        return waitTxnQueue;
    }

    public void setWaitTxnQueue(Queue<TxnWaitQueueNode> waitTxnQueue) {
        this.waitTxnQueue = waitTxnQueue;
    }
}