package main.message;

import main.transaction_YCSB.Request;
import main.transaction_YCSB.Transaction;
import main.transaction_YCSB.TxnFragment;

import java.util.List;

public class NewTxnMessage extends Message {
    private Request request;

    public NewTxnMessage(Request request){
        super();
        this.request = request;
    }

    public Request getRequest() {
        return request;
    }
}
