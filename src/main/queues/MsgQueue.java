package main.queues;

import main.message.*;

import java.util.Queue;


public class MsgQueue {

  Queue<MultiPartitionMessage> multiParitionMsgqueue;
  Queue<TwoPCMessage> twoPCMsgqueue;
  Queue<TwoPLMessage> twoPLMsgqueue;

  Message dequeue()
  { 
	  return null;
  }
  
  void enqueue(Message msg)
  {
	  return;
  }
  
}
