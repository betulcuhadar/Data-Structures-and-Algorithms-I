import sorts.SortingAlgorithms;
import java.util.Arrays;
import java.util.Random;

public class SortingAlgorithmTester {
    public static void main(String[] args) {
        Random rand = new Random();
        
        // initialize array with ascending order
        int arr[] = new int[48000];
        for(int i = 0; i < 48000; i++){
            arr[i] = i;
        }
        // calculating ascending order compile time for each sort method
        System.out.println("Ascending time:");
        // sort1
        long at1 = System.nanoTime();
        SortingAlgorithms.sort1(arr);
        long ate1 = System.nanoTime();
        System.out.println("1: " + (ate1 - at1));

        // sort2
        long at2 = System.nanoTime();
        SortingAlgorithms.sort2(arr);
        long ate2 = System.nanoTime();
        System.out.println("2: " + (ate2  - at2));

        // sort3
        long at3 = System.nanoTime();
        SortingAlgorithms.sort3(arr);
        long ate3 = System.nanoTime();
        System.out.println("3: " + (ate3 - at3));

        // sort4
        long at4 = System.nanoTime();
        SortingAlgorithms.sort4(arr);
        long ate4 = System.nanoTime();
        System.out.println("4: " + (ate4 - at4));

        // sort5
        long at5 = System.nanoTime();
        SortingAlgorithms.sort5(arr);
        long ate5 = System.nanoTime();
        System.out.println("5: " + (ate5 - at5));

        // initialize array with descending order
        int[] arr1 = new int[48000];
        int j = 0;
        for(int i = 47999; i >= 0; i--){
            arr1[j] = i;
            j++;
        }

        // calculating descending order compile time for each sort method
        System.out.println("Descending time:");
        // sort1
        long dt1 = System.nanoTime();
        SortingAlgorithms.sort1(arr1);
        long dte1 = System.nanoTime();
        System.out.println("1: " + (dte1 - dt1));

        // sort2
        long dt2 = System.nanoTime();
        SortingAlgorithms.sort2(arr1);
        long dte2 = System.nanoTime();
        System.out.println("2: " + (dte2  - dt2));

        // sort3
        long dt3 = System.nanoTime();
        SortingAlgorithms.sort3(arr1);
        long dte3 = System.nanoTime();
        System.out.println("3: " + (dte3 - dt3));

        // sort4
        long dt4 = System.nanoTime();
        SortingAlgorithms.sort4(arr1);
        long dte4 = System.nanoTime();
        System.out.println("4: " + (dte4 - dt4));

        // sort5
        long dt5 = System.nanoTime();
        SortingAlgorithms.sort5(arr1);
        long dte5 = System.nanoTime();
        System.out.println("5: " + (dte5 - dt5));

        // initialize array with random numbers
        int[] arr2 = new int[48000];
        for(int i = 0; i < 48000; i++){
            arr2[i] = rand.nextInt(50);
        }

        // calculating random order compile time for each sort method
        System.out.println("Random time:");
        // sort1
        long rt1 = System.nanoTime();
        SortingAlgorithms.sort1(arr2);
        long rte1 = System.nanoTime();
        System.out.println("1: " + (rte1 - rt1));

        // sort2
        long rt2 = System.nanoTime();
        SortingAlgorithms.sort2(arr2);
        long rte2 = System.nanoTime();
        System.out.println("2: " + (rte2  - rt2));

        // sort3
        long rt3 = System.nanoTime();
        SortingAlgorithms.sort3(arr2);
        long rte3 = System.nanoTime();
        System.out.println("3: " + (rte3 - rt3));

        // sort4
        long rt4 = System.nanoTime();
        SortingAlgorithms.sort4(arr2);
        long rte4 = System.nanoTime();
        System.out.println("4: " + (rte4 - rt4));

        // sort5
        long rt5 = System.nanoTime();
        SortingAlgorithms.sort5(arr2);
        long rte5 = System.nanoTime();
        System.out.println("5: " + (rte5 - rt5));

    }
}
