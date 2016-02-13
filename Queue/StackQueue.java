import java.util.Stack;

public class StackQueue <E> {
    private int size;
    private Stack<E> pushStack;
    private Stack<E> popStack;

    public StackQueue() {
	this.size = 0;
	pushStack = new Stack<E>();
	popStack = new Stack<E>();
    }

    public void add(E data) {
	pushStack.push(data);
	size++;
    }

    public E remove() {
	if (size == 0) return null;
	
	size--;
	if (popStack.isEmpty()) {
	    while (!pushStack.isEmpty()) {
		popStack.push(pushStack.pop());
	    } return popStack.pop();
	} return popStack.pop();
    }

    public int size() {
	return this.size();
    }

    public boolean isEmpty() {
	return size == 0;
    }
}
