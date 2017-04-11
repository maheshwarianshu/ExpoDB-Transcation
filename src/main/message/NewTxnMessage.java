package main.message;

import main.transaction_YCSB.TxnFragment;

import java.util.List;

public class NewTxnMessage extends Message {
    private List<TxnFragment> txnFragments;
    //List of parameters

    public List<TxnFragment> getTxnFragments() {
        return txnFragments;
    }

    public void setTxnFragments(List<TxnFragment> txnFragments) {
        this.txnFragments = txnFragments;
    }
}
