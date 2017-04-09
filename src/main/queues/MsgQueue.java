package main.queues;

import com.expodb.Config.*;
import main.message.*;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class MsgQueue {

    Long txnIDgenerator;
    String workerId;
    Queue<Message> multiParitionMsgqueue;
    Queue<Message> twoPCMsgqueue;
    Queue<Message> twoPLMsgqueue;
    Queue<Message> newTxnQueue;

  public MsgQueue(Queue<Message> newTxnQueue, String workerId){
      this.multiParitionMsgqueue = new ConcurrentLinkedQueue<>();
      this.twoPCMsgqueue = new ConcurrentLinkedQueue<>();
      this.twoPLMsgqueue = new ConcurrentLinkedQueue<>();
      this.newTxnQueue = newTxnQueue;
      this.txnIDgenerator = 0L;
      this.workerId = workerId;
  }

  public Message dequeue()  {
      if(!twoPCMsgqueue.isEmpty()){
          return twoPCMsgqueue.poll();
      }
      else if(!twoPLMsgqueue.isEmpty()){
          return twoPLMsgqueue.poll();
      }
      else if(!multiParitionMsgqueue.isEmpty()){
          return multiParitionMsgqueue.poll();
      }
      else if(!newTxnQueue.isEmpty()){
          return newTxnQueue.poll();
      }
      return null;
  }

  void insertIntoQueue(Queue<Message> queue, Message msg) {
      try {
          queue.add(msg);
      }
      catch(Exception e){
          e.printStackTrace();
      }
  }

  public void enqueue(Message msg) {
      if(msg.getTxnThreadId()==-1){ // new transaction
          txnIDgenerator+=1;
          msg.setTxnID(workerId + "_t" + txnIDgenerator);
          insertIntoQueue(newTxnQueue, msg);
      }
      else{
          switch(msg.getType()){
              case MULTIPARTITION:
                  insertIntoQueue(multiParitionMsgqueue, msg);
                  break;
              case TWOPC:
                  insertIntoQueue(twoPCMsgqueue, msg);
                  break;
              case TWOPL:
                  insertIntoQueue(twoPLMsgqueue, msg);
                  break;
              default:
                  insertIntoQueue(newTxnQueue, msg);
          }
      }
  }
  
}
