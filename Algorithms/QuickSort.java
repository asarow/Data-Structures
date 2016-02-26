import java.util.Random;
public class QuickSort {
    private static int[] array;
    
    public static int[] sort(int[] arr) {
	if (arr.length == 0) return arr;
	array = arr;
	quicksort(0, array.length-1);
	return array;
    }
    
    public static void quicksort(int left, int right) {
	int pivot = array[(left+right)/2];
	int i = left, j = right;

	while (i <= j) {
	    while (array[i] < pivot) {
		i++;
	    }
	    while (array[j] > pivot) {
		j--;
	    }

	    if (i <= j) {
		swap(i, j);
		i++;
		j--;
	    }
	}

	if (left < j) {
	    quicksort(left, j);
	}
	
	if (i < right) {
	    quicksort(i, right);
	}
    }

    private static void swap(int i, int j) {
	int temp = array[i];
	array[i] = array[j];
	array[j] = temp;
    }

    public static void main(String[] args) {
	int arr[] = new int[10];
	Random random = new Random();

	for (int i = 0; i < 10; ++i) {
	    arr[i] = random.nextInt(10);
	}

	for (int x : arr) {
	    System.out.print(x + " ");
	} System.out.println();

	arr = sort(arr);

	for (int x : arr) {
	    System.out.print(x + " " );
	} System.out.println();
    }
}
