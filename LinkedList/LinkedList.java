public class LinkedList<E> {
    private Node head;
    private Node tail;
    private int size;

    LinkedList() {
	head = new Node(null);
	tail = new Node(null);
	head.setNext(tail);
	tail.setPrev(head);
	size = 0;
    }
    
    private class Node {
	private Node next;
	private Node prev;
	private E data;

	Node(E data) {
	    this.data = data;
	}
	
	Node(Node prev, Node next, E data) {
	    this.data = data;
	    prev.setNext(this);
	    this.setPrev(prev);
	    this.setNext(next);
	    next.setPrev(this);
	}

	public void setNext(Node next) {
	    this.next = next;
	}
	
	public Node getNext() {
	    return this.next;
	}

	public void setPrev(Node prev) {
	    this.prev = prev;
	}

	public Node getPrev() {
	    return this.prev;
	}

	public E getData() {
	    return this.data;
	}
    } // end inner node class

    /* Begin LinkedList implementation */
    
    public boolean add(E data) {
	Node newNode = new Node(tail.getPrev(), tail, data);
	size++;
	return true; //temporarily return true;
    }
    
    public void add(int index, E data) {
	if (index < 0 || index >= size) {
	    throw new IndexOutOfBoundsException();
	}

	Node curr = head;
	int counter = 0;
	while(curr.getNext() != null) {
	    if (counter == index) break;
	    curr = curr.getNext();
	    counter++;
	}
	
	Node newNode = new Node(curr, curr.getNext(), data);
	size++;
    }

    public E remove(int index) {
	if (index < 0 || index >= size) {
	    throw new IndexOutOfBoundsException();
	}
	
	Node curr = head;
	E dataToRemove = null;
	int counter = 0;
	while (curr.getNext() != null) {
	    if (counter == index) break;
	    curr = curr.getNext();
	    counter++;
	}
	
	dataToRemove = curr.getNext().getData();
	curr.getNext().getNext().setPrev(curr);
	curr.setNext(curr.getNext().getNext());
	size--;
	return dataToRemove;
    }

    public E get(int index) {
	if (index < 0 || index >= size) {
	    throw new IndexOutOfBoundsException();
	}
	
	Node curr = head;
	int counter = 0;
	while (curr.getNext() != null) {
	    if (counter == index) break;
	    curr = curr.getNext();
	    counter++;
	}
	
	return curr.getNext().getData();
    }

    public int size() {
	return size;
    }
}

