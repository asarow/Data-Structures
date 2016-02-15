import java.util.LinkedList;

public class Stack <E> {
    private StackNode top;
    private int size;

    Stack() {
	this.size = 0;
	top = new StackNode(null);
    }

    private class StackNode {
	private StackNode next;
	private E data;

	StackNode(E data) {
	    this.data = data;
	}

	public void setNext(StackNode next) {
	    this.next = next;
	}

	public StackNode getNext() {
	    return this.next;
	}

	public E getData() {
	    return this.data;
	}
    }

    public E push(E item) {
	if (top.getNext() == null) {
	    top.setNext(new StackNode(item));
	    size++;
	    return item;
	}

	StackNode newNode = new StackNode(item);
	newNode.setNext(top.getNext());
	top.setNext(newNode);
	size++;
	return item;
    }
    

    public E pop() {
	if (top.getNext() == null)
	    return null;
	
	E dataToRemove = top.getNext().getData();
	top.setNext(top.getNext().getNext());
	size--;
	return dataToRemove;
    }

    public E peek() {
	return top.getNext().getData();
    }

    /* Sorts data (Integer only) into a temporary
       stack and displays the sorted data. Does not
       sort this stack. 
    */

    public void sort() {
	if (size < 2) {
	    this.display();
	    return;
	}

	Stack<E> s2 = new Stack<E>();
	final int maxSize = this.size;
	
	while(s2.size() != maxSize) {
	    while (!this.isEmpty() && !s2.isEmpty() && 
		   (Integer)this.peek() > (Integer)s2.peek()) {
		s2.push(this.pop());
	    }

	    E tempNum = this.pop();
	    
	    while(!s2.isEmpty() && (Integer)s2.peek() > (Integer) tempNum) {
		this.push(s2.pop());
	    }
	    s2.push(tempNum);
	    
	    while(!this.isEmpty() && (Integer)this.peek() > (Integer)s2.peek()) {
		s2.push(this.pop());
	    }
	}
	s2.display();
    }
    
    public boolean isEmpty() {
	return this.size == 0;
    }

    public int size() {
	return this.size;
    }

    public void display() {
	StackNode curr = top;
	while(curr.getNext() != null) {
	    System.out.println(curr.getNext().getData());
	    curr = curr.getNext();
	}
    }
}
