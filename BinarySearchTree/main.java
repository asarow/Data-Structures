import java.util.Random;

public class main {
    public static void main(String[] args) {
	BST<Integer> bst = new BST<Integer>();
	Random random = new Random();

	for (int i = 0; i < 2; ++i) {
	    bst.insert(random.nextInt(10));
	}

	bst.display();

	System.out.println();
	
	bst.invert();

	bst.display();
    }
    
}
