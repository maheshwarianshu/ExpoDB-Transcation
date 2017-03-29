package main;

import java.util.ArrayList;
import java.util.List;

import main.lockManager.LockManager;
import main.message.Message;
import main.queues.MsgQueue;

public class Worker {

	Integer workerId;
	GlobalPartitionInfo globalPartitionInfo;
	TxnManager txnManager;
	List<MsgQueue> msgQueueList;
	LockManager lockManager;
	
	
	public Worker()
	{
		msgQueueList = new ArrayList<>();
		globalPartitionInfo = new GlobalPartitionInfo();  
	}
	
	
	public void recieve(Message msg)
	{
		
	}
	
	public static void main(String [] args)
	{
		
	}
}
