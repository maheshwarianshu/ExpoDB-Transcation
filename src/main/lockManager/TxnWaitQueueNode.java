package main.lockManager;

import java.util.List;

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
