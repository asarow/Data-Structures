public class Queue <E> {
    private Node front;
    private Node rear;
    private int size;
    private int capacity;
    private final int DEFAULT_CAPACITY = 16;

    Queue(int capacity) {
	front = new Node(null, null, rear);
	rear = new Node(null, front, null);
	this.size = 0;
	this.capacity = capacity;
    }

    Queue() {
	front = new Node(null, null, rear);
	rear = new Node(null, front, null);
	this.size = 0;
	this.capacity = DEFAULT_CAPACITY;

    }

    protected class Node {
	private Node prev;
	private Node next;
	private E data;

	Node(E data) {
	    this.data = data;
	}
	 
	Node(E data, Node prev, Node next) {
	    this.data = data;
	    this.prev = prev;
	    this.next = next;	       
	}
	
	public void setNext(Node next) {
	    this.next = next;
	}

	public void setPrev(Node prev) {
	    this.prev = prev;;
	}
	
	public Node getNext() {
	    return this.next;
	}

	public Node getPrev() {
	    return this.prev;
	}

	public E getData() {
	    return this.data;
	}
    }
    
    public boolean add(E item) {
	if (size == capacity) return false;

	if (front.getNext() == null) {
	    front.setNext(new Node(item, front, null));
	    size++;
	    return true;
	}
	
	Node curr = front;
	while(curr.getNext() != null) {
	    curr = curr.getNext();
	}
	
	curr.setNext(new Node(item, front, null));
	size++;
	return true;
    }
    
    public E peek() {
	return front.getNext().getData();
    }

    public E poll() {
	if (size == 0) return null;
	
	E dataToReturn = front.getNext().getData();
	front.setNext(front.getNext().getNext());
	size--;
	return dataToReturn;
    }
    
    public boolean isEmpty() {
	return size == 0;
    }
    
    public int size() {
	return size;
    }

    public void display() {
	Node curr = front;
	while(curr.getNext() != null) {
	    System.out.println(curr.getNext().getData());
	    curr = curr.getNext();
	}
    }
}
