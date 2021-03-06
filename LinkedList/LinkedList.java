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
    
    public boolean isCycle() {	
	
	if (size == 0) return false;

	Node slowPointer = head;
	Node fastPointer = head.getNext();
	
	//Uncomment below to test cycle
	//tail.getPrev().setNext(head.getNext());
	while (fastPointer != slowPointer) {
	    if (fastPointer == null || fastPointer.getNext() == null) 
		break;
	    fastPointer = fastPointer.getNext().getNext();
	    slowPointer = slowPointer.getNext();
	}
	
	//Uncomment to remove cycle
	//tail.getPrev().setNext(tail);
	return (fastPointer == slowPointer);
    }
    
    
    /* Only works with Integers */
    public void removeDuplicates() {
	Node curr = head.getNext();
	
	while(curr.getNext() != null) {
	    Node temp = curr;
	    while(temp.getNext() != null) {
		if ((Integer) curr.getData() == (Integer) temp.getNext().getData()) {
		    temp.setNext(temp.getNext().getNext());
		    size--;
		}
		
		temp = temp.getNext();
	    } curr = curr.getNext();
	}
    }
    
    public void printKthToLast(int k) {
	final int index = size-k;
	if (index < 0 || index > size-1) return;

	Node curr = head;
	int counter = 0;
	while (counter < index) {
	    curr = curr.getNext();
	    counter++;
	}

	System.out.println(curr.getNext().getData());
    }


    /* Using this method breaks a the doubly-linked
       list implementation. */
    public void reverseSingly() {
	if (size == 1) return;

	if (size == 2) {
	    Node temp = head.getNext();
	    head.setNext(temp.getNext());
	    head.getNext().setNext(temp);
	    head.getNext().getNext().setNext(tail);
	    return;
	}
	
	Node firstNode = head.getNext();
	Node nodeToReverse = firstNode.getNext();
	Node nextNode = nodeToReverse.getNext();
	
	while(nextNode != tail) {
	    nodeToReverse.setNext(head.getNext());
	    head.setNext(nodeToReverse);
	    nodeToReverse = nextNode;
	    nextNode = nextNode.getNext();
	}

	nodeToReverse.setNext(head.getNext());
	head.setNext(nodeToReverse);
	firstNode.setNext(tail);
    }

    public void reverseDoubly() {
	if (size == 1) return;

	if (size == 2) {
	    Node temp = head.getNext();
	    head.setNext(tail.getPrev());
	    head.getNext().setPrev(head);
	    head.getNext().setNext(temp);
	    head.getNext().getNext().setPrev(head.getNext());
	    head.getNext().getNext().setNext(tail);
	    tail.setPrev(head.getNext().getNext());
	    return;
	}

	Node nodeToReverse = head.getNext().getNext();
	while (nodeToReverse != tail) {
	    Node tempNode = nodeToReverse.getNext();;
	    nodeToReverse.getPrev().setNext(nodeToReverse.getNext());
	    nodeToReverse.getNext().setPrev(nodeToReverse.getPrev());
	    head.getNext().setPrev(nodeToReverse);
	    nodeToReverse.setNext(head.getNext());
	    head.setNext(nodeToReverse);
	    nodeToReverse.setPrev(head);
	    nodeToReverse = tempNode;
	}
    }

    public int size() {
	return size;
    }
}

