package main.lockManager;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.*;

import main.Key;

public class LockNode {
    private Key key;
    //private ReadWriteLock txnLock;
    private Lock keyNodeLock;
	private Queue<TxnWaitQueueNode> waitTxnQueue;
	
    public LockNode(Key key)
    {
    	//TODO: check which lock to use
    	keyNodeLock = new ReentrantLock(); 
    	//txnLock = new ReentrantReadWriteLock();
    	this.key = key;
    	waitTxnQueue = new LinkedList<>();
    }
    
    public Lock acquireKeyNodeLock() {
    	keyNodeLock.lock();
		return keyNodeLock;
	}
	
    public void releaseKeyNodeLock() {
		 keyNodeLock.unlock();
	}

	public Key getKey() {
		return key;
	}

//	public int acquireTxnReadLock() {
//		 txnLock.readLock().lock();
//		 return 0;
//	}
//	
//	public int acquireTxnWriteLock() {
//		 txnLock.writeLock().lock();
//		 return 0;
//	}

//	public void releaseTxnReadLock() {
//		txnLock.readLock().unlock();
//	}
//
//	public void releaseTxnWriteLock() {
//		txnLock.writeLock().unlock();
//	}
}