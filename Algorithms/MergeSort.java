import java.util.Random;

public class MergeSort {
    private static int[] temp;
    private static int[] mergeArr;

    public static int[] sort(int[] arrToSort) {
	temp = new int[arrToSort.length];
	mergeArr = arrToSort;
	
	split(0, arrToSort.length-1);
	return mergeArr;
    }


    private static void split(int low, int high) {
	if (low < high) {
	    int middle = (low + high)/2;
	    
	    split(low, middle);
	    split(middle+1, high);
	    
	    merge(low, middle, high);
	}
    }
    private static void merge(int low, int middle, int high) {
	for (int i = low; i <= high; ++i) {
	    temp[i] = mergeArr[i];
	}

	int i = low, j = middle+1, k = low;
	while(i <= middle && j <= high) {
	    if (temp[i] <= temp[j]) {
		mergeArr[k++] = temp[i++];
	    } else {
		mergeArr[k++] = temp[j++];
	    }
	}
	
	
	while(i <= middle) {
	    mergeArr[k++] = temp[i++];
	}	

	
	while(j <= high) {
	    mergeArr[k++] = temp[j++];
	}
	
    }

    public static void main(String[] args) {
	Random random = new Random();
	int[] arr = new int[10];

	for (int i = 0; i < arr.length; ++i) {
	    arr[i] = random.nextInt(20);
	}
	
	for (int x : arr) {
	    System.out.print(x + " " );
	} System.out.println();
	
	arr = sort(arr);
	
	for (int x : arr) {
	    System.out.print(x + " " );
	} System.out.println();	
    }
}
