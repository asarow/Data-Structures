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
