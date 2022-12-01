import java.util.Random;


class Question1 {
  
/**
 * @author Arda Atasoy - 20180808045
 * @since 20.11.2022
 */

  static int partition(int[] arr, int low, int high, int mid) {

    int pivot = mid;

    //locate all small values goes to left, big values right
    int i = 0;
    for (int j = 0; j <= high; j++) {
      if (arr[j] <= pivot) {
        swap(arr, i, j);
        i++;
      }
    }
    return i;
  }

  static void quickSort(int[] arr, int low, int high) {
    /*
    we want to reach T(n) = T(n/2) + n = O(n)
    so we split the arrays in half with giving pivot value
    from outside to specify all partitions mid
    we behave like binary search fashion without all element not completed
    */ 

    if (low < high) {
      int mid = (low + high + 2) / 2;
      int pi = partition(arr, low, high, mid);
      if (pi == mid) {
        quickSort(arr, pi, high);//search right
      } else {
        quickSort(arr, low, pi);//search left
      }
    } else {
      System.out.print(" "+(low + 1));//print miss value
    }
  }

  static void report() {

    double[] runningTimes =new double[10]; //to store each running times 
    for (int i = 1; i <= 10; i++) {
      Random rand = new Random();
      int size = 10000 * i;
      int[] array = new int[size - 1];
      int missValue = rand.nextInt(size) + 1; //specify missing number
      
      //this for generate sequential array only one element miss
      int j=0;
      int k =j;
      for (j = 0; j < size-1; j++) {
      if (j + 1 == missValue) {
          k++;
          array[j] = k + 1;
          k++;
          continue;
        } 
        array[j] = k + 1;
        k++;
      }

      //shuffle one element miss sequentinall array
      shuffle(array);

      int s = array.length;
      double start = System.nanoTime();
      quickSort(array, 0, s-1);
      double finish= System.nanoTime();
      double passedTime= (finish-start);//passed time
      runningTimes[i-1]=passedTime/1000000;
    }
    System.out.println("\n\nRunning Times");
    printArray(runningTimes);
  }

  static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  //shuffles given array-> it is use in creating test arrays
  static void shuffle(int[] array){
    Random rand = new Random();
    for (int i = 0; i < array.length; i++) {
			int randomIndex = rand.nextInt(array.length);
			int temp = array[randomIndex];
			array[randomIndex] = array[i];
			array[i] = temp;
		}
  }

  public static void printArray(double[] arr) {
    for(int i=0;  i<arr.length; i++){
        System.out.print(arr[i]+" ");
    }
    System.out.println("\n");
  }
  public static void main(String[] args) {

    report();

  }
}
