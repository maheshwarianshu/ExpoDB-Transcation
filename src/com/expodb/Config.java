package com.expodb;

public class Config {

  public enum TxnQueueType { TWOPL, TWOPC, MULTIPARTITION};

  public enum MsgType { TWOPL, TWOPC, MULTIPARTITION, NEWTXN};

  public enum TxnStatus {COMMIT, ABORT, MULTIPARTITION, NEW, WAIT_LOCK, READY, WAIT_ACK, SENT_ACK};

  public enum TxnType { SINGLEPARTITION, CROSSPARITION, MULTIPARTITION};

  public enum OperationType { READ,WRITE,SCAN};

  public enum LLockType{READ, WRITE, FREE};

  public static final Integer NUMTXNTHREADS = 5;

  public static final Integer NUMMSGQTHREADS = 5;

  
}
  
