import java.util.Random;
public class main {
    public static void main(String[] args) {
	BST<Integer> bst = new BST<Integer>();
	Random random = new Random();
	
	for (int i = 0; i < 20; ++i) {
	    bst.insert(random.nextInt(20));
	}

	bst.displayData();
    }
}
