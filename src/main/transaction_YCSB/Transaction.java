package main.transaction_YCSB;

import java.util.List;
import java.util.Map;

import com.expodb.Config.*;

public class Transaction {

	private String txnID;
	private Long txnTimeStamp; // for ordering on different nodes
	private Integer threadId;
	private TxnStatus status;
	private TxnType type;
	private Integer coordinatorID;
	private List<TxnFragment> txnFragments;
	private Map<Integer,Integer> workerToACKMap;
	private String replyBackPort;
	private String replyBackIp;
	private boolean hasLocks;

	public Transaction(String txnID, Long txnTimeStamp, Integer threadId, TxnStatus status,
					   Integer coordID, List<TxnFragment> txnFragments,
					   String replyBackPort, String replyBackIp){ //TxnType type - add if needed, take as input from message
		this.txnID = txnID;
		this.txnTimeStamp = txnTimeStamp;
		this.threadId = threadId;
		this.status = status;
		this.coordinatorID = coordID;
		this.txnFragments = txnFragments;
		this.replyBackPort = replyBackPort;
		this.replyBackIp = replyBackIp;
		this.hasLocks = false;
	}

	public void execute(){
		if(txnFragments.size()==1){
			type = TxnType.SINGLEPARTITION;
		}
		else{
			type = TxnType.MULTIPARTITION;
		}
		for(TxnFragment fragment : txnFragments){
			fragment.execute(this);
		}
	}

	public Long getTxnTimeStamp(){
		return txnTimeStamp;
	}

	public void setTxnTimeStamp(Long txnTimeStamp){
		this.txnTimeStamp = txnTimeStamp;
	}

	public String getTxnID() {
		return txnID;
	}

	public void setTxnID(String txnID) {
		this.txnID = txnID;
	}

	public Integer getThreadId() {
		return threadId;
	}

	public void setThreadId(Integer threadId) {
		this.threadId = threadId;
	}

	public TxnStatus getStatus() {
		return status;
	}

	public void setStatus(TxnStatus status) {
		this.status = status;
	}

	public TxnType getType() {
		return type;
	}

	public void setType(TxnType type) {
		this.type = type;
	}

	public Integer getCoordinatorID() {
		return coordinatorID;
	}

	public void setCoordinatorID(Integer coordinatorID) {
		this.coordinatorID = coordinatorID;
	}

	public List<TxnFragment> getTxnFragments() {
		return txnFragments;
	}

	public void setTxnFragments(List<TxnFragment> txnFragments) {
		this.txnFragments = txnFragments;
	}

	public Map<Integer, Integer> getWorkerToACKMap() {
		return workerToACKMap;
	}

	public void setWorkerToACKMap(Map<Integer, Integer> workerToACKMap) {
		this.workerToACKMap = workerToACKMap;
	}

	public String getreplyBackPort() {
		return replyBackPort;
	}

	public void setreplyBackPort(String replyBackPort) {
		this.replyBackPort = replyBackPort;
	}

	public String getreplyBackIp() {
		return replyBackIp;
	}

	public void setreplyBackIp(String replyBackIp) {
		this.replyBackIp = replyBackIp;
	}

	public boolean isHasLocks() {
		return hasLocks;
	}

	public void setHasLocks(boolean hasLocks) {
		this.hasLocks = hasLocks;
	}
}
