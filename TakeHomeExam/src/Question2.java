import java.util.Random;

public class Question2 {
  /**
    * @author Arda Atasoy - 20180808045
    * @since 20.11.2022
 */
  public static void main(String[] args) {
    
    int[] arr = {3,0,1,8,7,2,5,4,9,6};
    int size= arr.length;
   quicksort(arr, 0, size-1);

    report();
  }

  static void report() {
    int exp=10;
    Random random = new Random();
    double[] runningTimes =new double[exp];//to store each running times 
    for (int i = 1; i <= exp; i++) {
      //Create test arrays asked sizes
      int[] array = random.ints(i * 10000).toArray();
      int n = array.length;
      double start = System.nanoTime();
      quicksort(array, 0, n - 1); //sorting
      double finish = System.nanoTime();
      double passedTime = finish - start;
      runningTimes[i-1]= passedTime;//passed for sorting
    }
    printArray(runningTimes);
  }

  static void quicksort(int[] arr, int low, int high) {
    if (low < high) {
      int pi = partition(arr, low, high);
      quicksort(arr, low, pi - 1);
      quicksort(arr, pi + 1, high);
    }
  }


  static int partition(int[] arr, int low, int high) {

    int pivot = low; //pivot is at most of each partition
    int i = low;

    while (i < high) {

      //pivot is change during sorting process, comperasion doing circular fashion depends on i and pivot location
      
      if (arr[i] <= arr[high] && pivot <= i) {
        high--;
      } else if (arr[i] > arr[high] && pivot <= i) {
        swap(arr, i, high);
        pivot = high;
        i++;
      } else if (arr[i] <= arr[high] && pivot > i) {
        i++;
      } else if (arr[i] > arr[high] && pivot > i) {
        swap(arr, i, high);
        pivot = i;
        high--;
      }
    }
    return pivot;
  }

  static void swap(int[] arr, int index1, int index2) {
    int temp = arr[index1];
    arr[index1] = arr[index2];
    arr[index2] = temp;
  }

  public static void printArray(double[] arr) {
    for(int i=0;  i<arr.length; i++){
        System.out.print(arr[i]+" ");
    }
    System.out.println("\n");
  }
}
