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
	private String clientPort;
	private String clientIp;

	public Transaction(String txnID, Long txnTimeStamp, Integer threadId, TxnStatus status,
					   TxnType type, Integer coordID, List<TxnFragment> txnFragments,
					   String clientPort, String clientIp){
		this.txnID = txnID;
		this.txnTimeStamp = txnTimeStamp;
		this.threadId = threadId;
		this.status = status;
		this.type = type;
		this.coordinatorID = coordID;
		this.txnFragments = txnFragments;
		this.clientPort = clientPort;
		this.clientIp = clientIp;
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

	public String getClientPort() {
		return clientPort;
	}

	public void setClientPort(String clientPort) {
		this.clientPort = clientPort;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
}
