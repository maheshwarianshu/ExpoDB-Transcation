package main;

import com.expodb.Config;
import main.message.Message;
import main.message.NewTxnMessage;
import main.queues.MsgQueue;
import main.transaction_YCSB.Transaction;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

public class TxnManager implements Runnable{

    Integer txnThreadID;
    MsgQueue msgQueue;
    Map<String,Transaction> transactionMap;

    public TxnManager(MsgQueue msgQueue, Integer txnThreadID){
        this.msgQueue = msgQueue;
        this.txnThreadID = txnThreadID;
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
                    txn = new Transaction(txnID, Worker.getTimeStamp(), txnThreadID, Config.TxnStatus.NEW,
                            msg.getCoordinatorID(), ((NewTxnMessage)msg).getTxnFragments(),
                            msg.getSenderPort(), msg.getSenderIp());
                    transactionMap.put(txnID, txn);
                    txn.execute();
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
