package main.lockManager;

import java.util.List;

import main.Key;

public class TxnWaitQueueNode {
   Transcation txn;
   List<Key> readSet;
   List<Key> writeSet;
   
   TxnWaitQueueNode()
   {
	   
   }
}
