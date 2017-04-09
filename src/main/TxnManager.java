package main;

import com.expodb.Config;
import main.message.Message;
import main.queues.MsgQueue;
import main.transaction_YCSB.Transaction;
import main.transaction_YCSB.TxnFragment;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

public class TxnManager implements Runnable{

    MsgQueue msgQueue;
    Map<String,Transaction> transactionMap;

    public TxnManager(MsgQueue msgQueue){
        this.msgQueue = msgQueue;
        this.transactionMap = new HashMap<>();
    }

    @Override
    public void run() {
        while(true){
            Message msg = msgQueue.dequeue();
            if(msg==null){
                //TODO: handle thread scheduling here
                try {
                    sleep(1);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            //TODO: execution phase: extract txn from message, send parallel units, execute
            String txnID = msg.getTxnID();
            Transaction txn;
            switch(msg.getType()){
                case NEWTXN:
                    txn = new Transaction();
                    break;
                case MULTIPARTITION:
                    break;
                case TWOPC:
                    break;
                case TWOPL:
                    break;
            }
        }
    }
}
