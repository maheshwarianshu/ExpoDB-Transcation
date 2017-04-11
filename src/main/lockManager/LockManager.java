package main.lockManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.Key;

public class LockManager {

	// TODO: rethink lockTable indexing - hashtable or list, depending on key format
	private List<LockNode> lockTable;

	public LockManager(List<Key> dataKeys){
		lockTable = new ArrayList<>();
		for(Key key : dataKeys){
			LockNode node = new LockNode(key);
			lockTable.add(node);
		 }
	}
}
