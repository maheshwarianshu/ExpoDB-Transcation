package main.lockManager;

import java.util.List;
import java.util.concurrent.locks.Lock;

import com.expodb.Config;
import main.transaction_YCSB.Transaction;

import main.Key;

public class TxnWaitQueueNode {
    String txnId;
    Integer threadId;
    Long txnTimestamp;
    List<LockRequest> readWriteSet;
   
    TxnWaitQueueNode(String txnId, Integer threadId, Long txnTimestamp, List<LockRequest> readWriteSet) {
        this.txnId = txnId;
        this.threadId = threadId;
        this.txnTimestamp = txnTimestamp;
        this.readWriteSet = readWriteSet;
    }
}
