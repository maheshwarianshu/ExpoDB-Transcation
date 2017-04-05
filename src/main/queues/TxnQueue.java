package main.queues;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import main.transaction_YCSB.Transaction;

public class TxnQueue{

	  Queue<Transaction> multiParitionTxnqueue;
	  Queue<Transaction> twoPCTxnqueue;
	  Queue<Transaction> twoPLTxnqueue;

	  public TxnQueue(){
          multiParitionTxnqueue = new LinkedList<>();
          twoPCTxnqueue = new LinkedList<>();
          twoPLTxnqueue = new LinkedList<>();
      }

	  public Transaction dequeueMultiPartition()
	  { 
		  return null;
	  }

    public Transaction dequeue2PC()
    {
        return null;
    }

    public Transaction dequeue2PL()
    {
        return null;
    }
	  
    public void enqueue(Transaction txn)
	  {
		  return;
	  }
  
}
