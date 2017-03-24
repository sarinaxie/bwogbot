import java.util.LinkedList;
public class SeparateChainingMap<K extends Comparable<? super K>, V> implements Map<K, V> {

	public static final int SCALE_FACTOR = 2;
	public static final int INITIAL_TABLE_SIZE = 8;
	public static final double MAX_LOAD_FACTOR = 1.0;

	@SuppressWarnings("unchecked")
	private LinkedList<Pair<K, V>>[] table = (LinkedList<Pair<K, V>>[]) new LinkedList[INITIAL_TABLE_SIZE];
	private int size;

	public SeparateChainingMap() {
		//initializing the lists
		for (int k = 0; k < table.length; k++) {
			table[k] = new LinkedList<Pair<K,V>>();
		}
	}

	public int getSize() {
		return size;
	}

	public int getTableSize() {
		return table.length;
	}

	public void put(K key, V value) {
		if (size + 1 > table.length * MAX_LOAD_FACTOR) {
			upsize();
		}

		//find where pair goes
		int tableSize = getTableSize();
		int loc = key.hashCode() % tableSize;
		if (loc < 0) {
			loc += tableSize;
		}

		//overwrite if pair with same key is found
		for (Pair<K, V> pair : table[loc]) {
			if (key.equals(pair.key)) {
				pair.value = value;
				//return so key isn't added after it's overwritten
				return;
			}
		}

		//adds key
		Pair<K, V> p = new Pair<K, V>(key, value);
		table[loc].addFirst(p);
		size++;
	}

	public V get(K key) {
		//find where pair goes
		int tableSize = getTableSize();
		int loc = key.hashCode() % tableSize;
		if (loc < 0) {
			loc += tableSize;
		}

		//gets the value of the key
		for (Pair<K, V> pair : table[loc]) {
			if (key.equals(pair.key)) {
				return pair.value;
			}
		}

		//returns null if there's no such key
			return null;
	}

	public void upsize() {	  
		//create bigger table
		@SuppressWarnings("unchecked")
		LinkedList<Pair<K, V>>[] theTable = (LinkedList<Pair<K, V>>[]) new LinkedList[table.length*SCALE_FACTOR];
		for (int k = 0; k < theTable.length; k++) {
			theTable[k] = new LinkedList<Pair<K,V>>();
		}

		for (int j = 0; j < table.length; j++) {
			for (Pair<K, V> pair : table[j]) {
				int loc = pair.key.hashCode() % theTable.length;
				if (loc < 0) {
					loc += theTable.length;
				}
				theTable[loc].add(pair);
			}
		}  

		table = theTable;
	} 
}
