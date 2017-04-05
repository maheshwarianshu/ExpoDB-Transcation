package main.message;

import main.transaction_YCSB.Transaction;

public class MultiPartitionMessage  extends Message {
    private Transaction txn;

    public Transaction getTxn() {
        return txn;
    }
}
