package main.message;

import main.transaction_YCSB.Transaction;

public class NewTxnMessage extends Message {
    private Transaction txn;

    public Transaction getTxn() {
        return txn;
    }
}
