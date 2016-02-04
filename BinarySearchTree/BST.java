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
		    clipMinNode(node);
		} else {
		    node.getRightChild().setParent(node.getParent());
		    node.getParent().setRightChild(node.getRightChild());
		    clipMinNode(null);;
		}
	    } else if (node.getRightChild() == null) {
		node.getLeftChild().setParent(node.getParent());
		node.getParent().setLeftChild(node.getLeftChild());
		clipMinNode(null);
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
		clipMinNode(minNode);
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

    public int getSize() {
	return size;
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
	System.out.println(node + " " + node.getData());
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

    private void clipMinNode(BSTNode node) {
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

    public void preorder() {
	doit(root);
    }

    private void doit(BSTNode node) {
	if (node == null) 
	    return;
	
	System.out.println(node.getData() + " " + node.getParent());
	doit(node.getLeftChild());
	doit(node.getRightChild());
    }
}
