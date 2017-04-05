package main;

import main.message.Message;
import main.message.MultiPartitionMessage;
import main.message.NewTxnMessage;
import main.queues.MsgQueue;
import main.queues.TxnQueue;
import main.transaction_YCSB.Transaction;

import static java.lang.Thread.sleep;

public class TxnManager implements Runnable{

    MsgQueue msgQueue;
    TxnQueue txnQueue;

    public TxnManager(MsgQueue msgQueue){
        this.msgQueue = msgQueue;
        this.txnQueue = new TxnQueue();
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
            int txnID = msg.getTxnID();
            Transaction txn;
            switch(msg.getType()){
                case NEWTXN:
                    txn = ((NewTxnMessage)msg).getTxn();
                    break;
                case MULTIPARTITION:
                    txn = ((MultiPartitionMessage)msg).getTxn();
                    break;
                case TWOPC:
                    txnID = msg.getTxnID();

            }
        }
    }
}
