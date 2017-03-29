package main.queues;

import java.util.Queue;

import main.transaction.Transaction;

public class TxnQueue{

	  Queue<Transaction> multiParitionTxnqueue;
	  Queue<Transaction> twoPCTxnqueue;
	  Queue<Transaction> twoPLTxnqueue;

	  Transaction dequeue()
	  { 
		  return null;
	  }
	  
	  void enqueue(Transaction txn)
	  {
		  return;
	  }
  
}
