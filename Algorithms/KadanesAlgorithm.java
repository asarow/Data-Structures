import java.util.Scanner;
public class KadanesAlgorithm {
    public static int findMaxSumSubarray(int[] array) {
	int maxSum = 0;
	int currentMax = 0;
	for (int i : array) {
	    currentMax = Math.max(0, currentMax + i);
	    maxSum = Math.max(maxSum, currentMax);
	}
	return maxSum;
    }

    public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);
	System.out.print("Enter the array size: ");
	final int arrSize = scan.nextInt();
	int[] array = new int[arrSize];

	for (int i = 0; i < array.length; ++i) {
	    array[i] = scan.nextInt();
	}

	int maxSumSubarray = findMaxSumSubarray(array);
	System.out.println(maxSumSubarray);
    }
    
}
