package main.lockManager;

import java.util.List;
import main.transaction_YCSB.Transaction;

import main.Key;

public class TxnWaitQueueNode {
   Transaction txn;
   List<Key> readSet;
   List<Key> writeSet;
   
   TxnWaitQueueNode()
   {
	   
   }
}
