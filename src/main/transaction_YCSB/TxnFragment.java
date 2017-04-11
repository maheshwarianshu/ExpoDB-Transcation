package main.transaction_YCSB;

import main.Worker;
import main.lockManager.LockRequest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TxnFragment implements Serializable{
   private String workerId;
   private List<Operation> operations;
   private List<LockRequest> readWriteSet; //NULL when sent in message, set locally

   public void execute(Transaction txn){
      if (isLocal()){
         readWriteSet = new ArrayList<>();
         for(Operation op : operations) {
            if (op.isLocal()) {
               // TODO: make read write set
            }
         }
         // acquire locks from lock manager using this readwrite set, call enqueue for first key
         // releasing lock thread will enqueue it further
      }
      else {
         //TODO: send txn fragment to another worker
      }
   }

   public boolean isLocal(){
      return (workerId.equals(Worker.getWorkerId()));
   }
}
