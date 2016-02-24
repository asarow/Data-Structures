import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
/**
 * Binary search in a sorted array.
 */

public class BinarySearch {
    /* Note: Overflow is possible for max integer and 
       stack overflow is possible for value not found */
    public static int  binarySearch(int num, int[] array, int begin, int end) {
	int middlePoint = (end + begin)/2;
	
	if (array[middlePoint] == num) return num;
	if (array[middlePoint] < num) {
	    return binarySearch(num, array, middlePoint+1, end);
	} else {
	    return binarySearch(num, array, begin, middlePoint-1);
	}
    }
    
    public static boolean isSorted(int[] array) {
	if (array.length == 0 || array.length == 1) {
	    return true;
	}

	for (int i = 1; i < array.length; ++i) {
	    if (array[i] < array[i-1]) {
		return false;
	    }
	} return true;
    }

    /* Array size must be > 0 */
    public static void main(String[] args) {
	Random random = new Random();
	Scanner scan = new Scanner(System.in);
	System.out.println("Enter the array size: ");
	final int arrSize = scan.nextInt();
	int[] array = new int[arrSize];
	
	for (int i = 0; i < array.length; ++i) {
	    array[i] = scan.nextInt();
	}

	if (!isSorted(array))
	    Arrays.sort(array); //nlog(n) operation

	int numToSearch = array[random.nextInt(array.length)];
	int numFound = binarySearch(numToSearch, array, 0, array.length-1);
	System.out.println("Searching for " + numToSearch + "...");
	System.out.println(numToSearch == numFound);
    }
}
