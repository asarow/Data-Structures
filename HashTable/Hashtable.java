import java.util.LinkedList;
import java.util.ArrayList;

/** Hashtable with separate chaining. This implementation
 *  is closer to a Hashmap in that it allows one null key
 *  and is not synchronized. 
*/
public class Hashtable <K,V> {
    private ArrayList<Entry> entry;
    private int capacity, size;
    private final double LOAD_LIMIT = 0.75;

    @SuppressWarnings("unchecked")
    Hashtable(int capacity) {
	this.size = 0;
	this.capacity = capacity;
	entry = new ArrayList<Entry>();
	initializeTable();
    }
    
    private class Entry {
	private K key;
	private V value;
	private Entry next;
	private Entry prev;
	
	Entry(K key, V value, Entry prev, Entry next) {
	    this.key = key;
	    this.value = value;
	    this.prev = prev;
	    this.next = next;
	}

	public void setNext(Entry next) {
	    this.next = next;
	}

	public void setPrev(Entry prev) {
	    this.prev = prev;
	}

	public Entry getNext() {
	    return this.next;
	}

	public Entry getPrev() {
	    return this.prev;
	}
	
	public K getKey() {
	    return this.key;
	}

	public V getValue() {
	    return this.value;
	}
    }
    
    public void put(K key, V value) {
	if (contains(key)) return;

	int index = hash(key);
	Entry curr = entry.get(index);
	boolean isDuplicate = false;
	
	while (curr.getNext() != null) {	    
	    curr = curr.getNext();
	}

	Entry newEntry = new Entry(key, value, curr, null);
	curr.setNext(newEntry);
	size++;	
	
	if (loadFactor() >= LOAD_LIMIT) 
	    rehash();
    }   

    public double loadFactor() {
	return (double) size / (double) capacity;
    }
    
    public boolean contains(K key) {
	int indexOfKey = hash(key);
	Entry curr = entry.get(indexOfKey);
	boolean isInTable = false;
	while(curr.getNext() != null) {
	    if (curr.getNext().getKey().equals(key)) {
		isInTable = true;
		break;
	    }
	    curr = curr.getNext();
	} return isInTable;
	
    }
    
    public void display() {
	for (Entry x : entry) {
	    while(x.getNext() != null) {     
		x = x.getNext();
		System.out.print("[" + x.getKey() + ", " +  x.getValue() + "] ");
	    } System.out.println();
	}
    }
    
    /* Not an efficient hash function for rehasing. 
       Table capacity is a factor of 2 */
    private int hash(K key) {
	return  key.hashCode() % capacity;
    }

    private void initializeTable() {
	for (int i = 0; i < this.capacity; ++i) {
	    entry.add(new Entry(null, null, null, null));
	}
    }

    private void rehash() {
	ArrayList<Entry> oldEntry = entry;
	entry = new ArrayList<Entry>(capacity*2);
	initializeTable();
	this.capacity *= 2;
	
	for (int i = 0; i < oldEntry.size(); ++i) {
	    if (oldEntry.get(i).getNext() == null)
		continue;
	    int newIndex = hash(oldEntry.get(i).getNext().getKey());
	    entry.add(newIndex, oldEntry.get(i));
	}
    }
}
