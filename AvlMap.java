public class AvlMap<K extends Comparable<? super K>, V> implements Map<K, V> {

	//instance variables
	//making a tree that contains pairs (which are made of Ks and Vs)
	AvlTree<Pair<K, V>> tree;

	//constructor
	public AvlMap() {
		tree = new AvlTree<Pair<K, V>>();
	}

	//methods
	@Override
	public void put(K key, V value) {
		Pair<K, V> p = new Pair<K, V>(key, value);
		tree.insert(p);
	}

	@Override
	public V get(K key) {
		V val = null;
		Pair<K, V> p = new Pair<K, V>(key, val);
		return tree.get(p).value;
	}
}
