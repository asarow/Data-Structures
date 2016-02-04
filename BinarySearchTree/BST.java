import java.util.LinkedList;
import java.util.ArrayList;

public class BST <E> {
    private BSTNode root;
    private int size;
    
    BST() {
	size = 0;
    }
    
    private class BSTNode {
	private BSTNode left;
	private BSTNode right;
	private BSTNode parent;
	private E data;
	
	BSTNode(E data) {
	    this.data= data;
	}
	
	BSTNode(BSTNode leftChild, BSTNode rightChild, BSTNode parent,
		E data) 
	{
	    this.setLeftChild(leftChild);
	    this.setRightChild(rightChild);
	    this.setParent(parent);
	    this.data = data;
	}

	public void setLeftChild(BSTNode left) {
	    this.left = left;
	}
	
	public void setRightChild(BSTNode right) {
	    this.right = right;
	}

	public void setParent(BSTNode parent) {
	    this.parent = parent;
	}
	
	public BSTNode getLeftChild() {
	    return this.left;
	}

	public BSTNode getRightChild(){
	    return this.right;
	}
	
	public BSTNode getParent() {
	    return this.parent;
	}
	
	public E getData() {
	    return this.data;
	}           

	/* Temporary CompareTo which only works with numeric data 
	public int compareTo(Node n2) {
	    if ((int) this.data > (int) n2.getData()) {
		return 1;
	    } else if ((int) this.data < (int) n2.getData()) {
		return -1;
	    } else {
		return 0;
	    }
	}
	*/

	public int compareTo(E data) {
	    if ((Integer) this.data > (Integer) data) {
		return 1;
	    } else if ((Integer) this.data < (Integer) data) {
		return -1; 
	    } else {
		return 0;
	    }
	}
    } //End BSTNode
    
    /* Begin BST Implementation */
    
    public boolean insert(E data) {
	if (root == null) {
	    root = new BSTNode(null, null, null, data);
	    size++;
	    return true;
	}
	
	return recursiveInsert(root, data);
    }

    public boolean find (E data) {
	if (root.compareTo(data) == 0) return true;
	
	return recursiveFind(root, data);
    }

    public boolean remove(E data) {
	BSTNode node = retrieveBSTNode(root, data);
	
	if (node == null) {
	    return false; 
	} else {
	    if (node.getLeftChild() == null) {	   
		if (node.getRightChild() == null) {
		    clipNode(node);
		} else {
		    node.getRightChild().setParent(node.getParent());
		    node.getParent().setRightChild(node.getRightChild());
		    clipNode(node);
		}
	    } else if (node.getRightChild() == null) {
		node.getLeftChild().setParent(node.getParent());
		node.getParent().setLeftChild(node.getLeftChild());
		clipNode(node);
	    } else {
		BSTNode minNode = findMin(node.getRightChild());
		if (node.getParent().getLeftChild() == node) {
		    node.getParent().setLeftChild(minNode);
		} else {
		    node.getParent().setRightChild(minNode);
		}
		
		minNode.setParent(node.getParent());
		minNode.setLeftChild(node.getLeftChild());
		minNode.setRightChild(node.getRightChild());
		node = null;
		clipNode(minNode);
	    }
	    return true;
	}
    }
    
    public void display() {
	inorder(root);
    }

    public void invert() {
	if (root == null) return;
	recursiveInvert(root);
    }

    public ArrayList<E> getLevelOrder() {
	LinkedList<BSTNode> queue = new LinkedList<BSTNode>();
	ArrayList<E> arr = new ArrayList<E>();

	BSTNode curr = root;
	queue.add(curr);
	while(!queue.isEmpty()) {
	    arr.add(queue.removeFirst().getData());
	    if (curr.getLeftChild() != null)
		queue.add(curr.getLeftChild());
	    if (curr.getRightChild() != null)
		queue.add(curr.getRightChild());
	    curr = queue.peekFirst();	   
	}
	return arr;
    }

    /* Compares structure, not values */
    public boolean compareBST(BSTNode root) {
	return recursiveCompare(this.root, root); 
    }

    public BSTNode root() {
	return root;
    }

    public int size() {
	return size;
    }
    
    public E findMin() {
	return recursiveFindMin(root);
    }

    public E findMax() {
	return recursiveFindMax(root);
    }

    /* Begin private helper methods */
    
    private BSTNode recursiveInvert(BSTNode node) {
	if (node == null) return null;	

	BSTNode oldLeftNode = node.getLeftChild();
	node.setLeftChild(recursiveInvert(node.getRightChild()));
	node.setRightChild(recursiveInvert(oldLeftNode));
	return node;
    }

    private boolean recursiveInsert(BSTNode node, E data) {
	if (node.getLeftChild() == null && node.compareTo(data) == 1) {
	    BSTNode newNode = new BSTNode(null, null, node, data);
	    node.setLeftChild(newNode);
	    size++;
	    return true;
	} else if (node.getRightChild() == null && node.compareTo(data) == -1) {
	    BSTNode newNode = new BSTNode(null, null, node, data);
	    node.setRightChild(newNode);
	    size++;
	    return true;
	}
	
	if (node.compareTo(data) == 1) {
	    return recursiveInsert(node.getLeftChild(), data);
	} else if (node.compareTo(data) == -1) {
	    return recursiveInsert(node.getRightChild(), data);
	} else {
	    return false;
	}
    }

    private boolean recursiveFind(BSTNode node, E data) {
	if (node.compareTo(data) == 0) return true;
	
	if (node.compareTo(data) == 1) {
	    return recursiveFind(node.getLeftChild(), data);
	} else if (node.compareTo(data) == -1) {
	    return recursiveFind(node.getRightChild(), data);
	} else {
	    return false;
	}
    }
    
    private E recursiveFindMin(BSTNode node) {
	if (node == null) return null;
	if (node.getLeftChild() == null) {
	    return node.getData();
	}
	
	return recursiveFindMin(node.getLeftChild());
    }

    private E recursiveFindMax(BSTNode node) {
	if (node == null) return null;
	if (node.getRightChild() == null) {
	    return node.getData();
	}
	
	return recursiveFindMax(node.getRightChild());
    }
    private boolean recursiveCompare(BSTNode n1, BSTNode n2) {
	if (n1 == null && n2 == null) return true;
	if (n1 == null && n2 != null) return false;
	if (n1 != null && n2 == null) return false;

	boolean isLeftSubtreeSame = recursiveCompare(n1.getLeftChild(), n2.getLeftChild());
	boolean isRightSubtreeSame = recursiveCompare(n1.getRightChild(), n2.getRightChild());

	return (isLeftSubtreeSame && isRightSubtreeSame);
    }

    private BSTNode retrieveBSTNode(BSTNode node, E data) {
	if (node.compareTo(data) == 0) //return node;
	    {
		return node;
	    }
	if (node.compareTo(data) == 1) {
	    return retrieveBSTNode(node.getLeftChild(), data);
	} else if (node.compareTo(data) == -1) {
	    return retrieveBSTNode(node.getRightChild(), data);
	} else {
	    return null;
	}
    }

    private BSTNode findMin(BSTNode root) {
	if (root.getLeftChild() == null && root.getRightChild() == null) {
	    return root;
	}
	
	if (root.getLeftChild() != null) {
	    return findMin(root.getLeftChild());
	} else {
	    return findMin(root.getRightChild());
	}
    }

    private void clipNode(BSTNode node) {
	BSTNode tempNode = root;
	BSTNode nodeToFind = node;
	
	while(tempNode != node) {
	    if (tempNode.compareTo(node.getData()) == 1) {
		tempNode = tempNode.getLeftChild();
	    } else {
		tempNode = tempNode.getRightChild();
	    }
	}

	if (tempNode.getParent().getLeftChild() == tempNode) {
	    tempNode.getParent().setLeftChild(null);
	} else {
	    tempNode.getParent().setRightChild(null);
	}
	tempNode = null;
    }
    
    private void inorder(BSTNode node) {
	if (node == null) return;

	inorder(node.getLeftChild());
	System.out.println(node + " " + node.getData());
	inorder(node.getRightChild());
    }
}
