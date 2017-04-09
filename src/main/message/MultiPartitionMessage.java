package main.message;

import main.transaction_YCSB.Request;
import main.transaction_YCSB.Transaction;

public class MultiPartitionMessage  extends Message {
    private Request request;

    public Request getRequest() {
        return request;
    }
}
