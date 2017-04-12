package main.transaction_YCSB;

import expodb.util.Config.*;
import main.Worker;

public class Operation { // YCSB request
    OperationType type; // YCSB accessType
    Integer workerID; // YCSB thread id
    //TODO: Parameters
    // TODO: key

    public void execute(Transaction txn){

        if(!workerID.equals(Worker.getWorkerId())){
            //create Message and send to remote
        }
        else if(type == OperationType.READ){
            // read()
        }
        else if(type == OperationType.WRITE){
            // write
        }
        else if (type == OperationType.SCAN){
            // scan
        }
    }

    public boolean isLocal(){
        return workerID.equals(Worker.getWorkerId());
    }
}
