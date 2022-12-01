
public class Question3 {

  /**
   * @author Arda Atasoy - 20180808045
   * @since 20.11.2022
 */
  public static void main(String[] args) {

    report();
  
  }

  static void report(){
    //array with size 2^16 ->[1,2,3..2^16]
    int size = (int)Math.pow(2, 16); 
    int[] arr = new int[size];
    for(int i = 0; i<size; i++){
        arr[i]=i+1;
      }

    //to store each running times    
    float[] runningTimes =new float[17];

    
    for(int j= 0; j<= 16; j++){
      if(j==0){
        //it is for shift 1
        int[] arr2 = arr.clone();//it is for obtain initial sequantial array
        arr2=shift(arr2, 1);// shifting
        float start = System.nanoTime();       
        insertionSort(arr2);//sorting
        float finish= System.nanoTime();
        float passedTime= (finish-start);//passed for sorting
        runningTimes[j]=passedTime;
      }
      
      else if(j==16){
        //it is for shift n-1
        int[] arr2 = arr.clone(); //it is for obtain initial sequantial array
        arr2=shift(arr2, size-1); // shifting
        float start = System.currentTimeMillis();
        insertionSort(arr2); //sorting
        float finish= System.currentTimeMillis();
        float passedTime= finish-start;//passed for sorting
        runningTimes[j]=passedTime;
      }else{
      //this part for n/16,2n/16,3n/16.......15n/16,n-1 shift.  
      int[] arr2 = arr.clone(); //it is for obtain initial sequantial array
      arr2=shift(arr2, (size*j)/16);// shifting
      float start = System.nanoTime();
      insertionSort(arr2);
      float finish= System.nanoTime();
      float passedTime= finish-start; //passed for sorting
      runningTimes[j]=passedTime;
      }
    }
    printArray(runningTimes);//print running times
  }
  



   static void insertionSort(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
      int key = arr[i];
      int j = i - 1;

      while (j >= 0 && arr[j] > key) {
        arr[j + 1] = arr[j];
        j = j - 1;
      }
      arr[j + 1] = key;
    }
  }

    //shifting right 
    static int[] shift(int[] arr,int k) {
    for(int i = 0; i<k; i++){
    int n = arr.length - 1;
    int temp = arr[n];

    for (int j = n - 1; j >= 0; j--) {
      arr[j + 1] = arr[j];
    }
    arr[0] = temp;
    }
    return arr;
  }

  public static void printArray(float[] arr) {
    for(int i=0;  i<arr.length; i++){
        System.out.print(arr[i]+" ");
    }
    System.out.println("\n");
  }
}

