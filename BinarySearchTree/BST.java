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
	    this.setParent(null);
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

    public void display() {
	inorder(root);
    }

    public void invert() {
	if (root == null) return;
	recursiveInvert(root);
    }


    private BSTNode recursiveInvert(BSTNode node) {
	if (node == null) return null;	

	BSTNode oldLeftNode = node.getLeftChild();
	node.setLeftChild(recursiveInvert(node.getRightChild()));
	node.setRightChild(recursiveInvert(oldLeftNode));
	return node;
    }

    private void inorder(BSTNode node) {
	if (node == null) return;

	inorder(node.getLeftChild());
	System.out.println(node.getData());
	inorder(node.getRightChild());
    }

    private boolean recursiveInsert(BSTNode node, E data) {
	if (node.getLeftChild() == null && node.compareTo(data) == 1) {
	    BSTNode newNode = new BSTNode(null, null, node, data);
	    node.setLeftChild(newNode);
	    size++;
	} else if (node.getRightChild() == null && node.compareTo(data) == -1) {
	    BSTNode newNode = new BSTNode(null, null, node, data);
	    node.setRightChild(newNode);
	    size++;
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

    public int getSize() {
	return size;
    }
}
