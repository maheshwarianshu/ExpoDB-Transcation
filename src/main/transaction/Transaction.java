package main.transaction;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.expodb.Config.*;

public class Transaction {

	Integer txnID;
	Timestamp time_stamp;
	//WorkerID -> ThreadID
	Map<Integer,Integer> workerToThreadMap;
	TxnStatus status;
	TxnType type;
	Integer coordinatorID;
	List<TxnFragment> txnFragments;
	
}
