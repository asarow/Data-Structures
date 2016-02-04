public class AVL <E> extends BST <E>{
    AVL() {

    }

    public boolean insert(E data) {
	boolean isInserted = super.insert(data);
	rotate(super.retrieveBSTNode(root, data));
	return isInserted;
    }

    private void rotate(BSTNode node) {

    }
	
    
}
