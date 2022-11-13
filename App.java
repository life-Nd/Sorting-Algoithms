
// Student name: Ralph NDUWIMANA
// Student id: 261066154
import java.util.Random;

/**
 * Execute this file to run the program.
 */

public class App {
    public static void main(String[] args) throws Exception {

        int numItem = 10000; // This decide how big you array is
        int[] myArr = new int[numItem];
        int[] heapArray = new int[numItem];
        int[] pqArray = new int[numItem];
        int[] spqArray = new int[numItem];
        int[] spqArray2 = new int[numItem];

        Random myRand = new Random(); // creating Random object
        // Range for random to select from
        int min = 5;
        int max = 1000;

        int indexMin = 0;
        for (int i = 0; i < myArr.length; i++) {
            myArr[i] = myRand.nextInt(max - min + 1) + min; // storing random integers in an array
            heapArray[i] = myArr[i];
            pqArray[i] = myArr[i];
            spqArray[i] = myArr[i];
            spqArray2[i] = myArr[i];
            System.out.print(myArr[i] + ", ");
        }

        System.out.println("");
        // Methods to call a process and start the timer.
        // The timer is stopped when the process is complete.
        heapTimer(heapArray);
        priorityQueueTimer(pqArray);
        sortedPriorityQueueTimer(spqArray);

    }

    /**
     * This method takes an array as a parameter and uses a priority queue with
     * heapSort sort to sort the array.
     * The time it takes to sort the array is printed out.
     * 
     * @param array the array that is being sorted
     */

    public static void heapTimer(int[] array) {
        // Heap
        double startTime = System.nanoTime();
        MyHeap heap = new MyHeap();
        // Sorting
        heap.heapSort(array);
        double endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1000000; // divide by 1000000 to get milliseconds.
        printSorted(array);
        System.out.println("\t❗️ Heap: The time it took to sort the  with heapSort is \t\t\t\t" + duration);
    }

    /**
     * This method takes an array as a parameter and uses a priority queue with
     * insertion sort to sort the array.
     * The time it takes to sort the array is printed out.
     * 
     * @param array the array that is being sorted
     */
    public static void priorityQueueTimer(int[] array) {

        // Priority Queue
        double startTime = System.nanoTime();
        MyPriorityQueue pq = new MyPriorityQueue();
        // Sorting
        pq.insertionSort(array);
        double endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1000000; // divide by 1000000 to get milliseconds.
        printSorted(array);
        System.out.println("\t❗️ Priority Queue: The time it took to sort the Priority Queue with insertion sort is \t"
                + duration);

    }

    /**
     * This method takes an array as a parameter and uses a priority queue with
     * selection sort to sort the array.
     * The time it takes to sort the array is printed out.
     * 
     * @param array the array that is being sorted
     */
    public static void sortedPriorityQueueTimer(int[] array) {
        // Sorted Priority Queue
        double startTime = System.nanoTime();
        MySortedPriorityQueue spq = new MySortedPriorityQueue();
        // Sorting
        spq.selectionSort(array);
        double endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1000000; // divide by 1000000 to get milliseconds.
        printSorted(array);
        System.out.println(
                "\t❗️ Sorted Priority Queue: The time it took to sort  with selection sort is \t\t" + duration);

    }

    /**
     * This method loops through the array received as parameter and
     * prints out the 20 first element in the console.
     * 
     * @param array the array that is being sorted
     */
    public static void printSorted(int[] array) {
        int size = 20;
        System.out.print("✅ Sorted myArr: ");
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println("..................,]");

    }

}

class MyHeap {

    /**
     * Class constructor
     */
    MyHeap() {
    };

    /**
     * This method sorts an array using the heap sort algorithm.
     * 
     * @param array The array to be sorted.
     */
    public void heapSort(int[] array) {
        for (int i = (array.length - 2) / 2; i >= 0; i--)
            heapify(array, i, array.length - 1);

        for (int i = array.length - 1; i > 0; i--) {
            swapKeys(array, 0, i);
            heapify(array, 0, i - 1);
        }
    }

    /**
     * This method takes in an array, the index of the root node, and the size of
     * the heap. The method then swaps the root node with the largest child node if
     * the root node is less than the child node. The method repeats this process
     * until the heap is sorted.
     * 
     * @param array array to be used
     * @param i     is the index of the root node.
     * @param m     is the size of the heap.
     */
    public void heapify(int[] array, int i, int m) {
        int j;
        while (2 * i + 1 <= m) {
            j = 2 * i + 1;
            if (j < m) {
                if (array[j] < array[j + 1])
                    j++;
            }
            if (array[i] < array[j]) {
                swapKeys(array, i, j);
                i = j;
            } else
                i = m;
        }
    }

    /**
     * This method swaps the keys in an array.
     * 
     * @param array the array to swap keys in
     * @param i     the first index to swap
     * @param j     the second index to swap
     */
    public void swapKeys(int[] array, int i, int j) {
        int temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * This method displays the heap as a tree. The root node is at the top,
     * the left child is to the left of the parent node, and the right child
     * is to the right of the parent node.
     * 
     * @param array The array of Integer values to use.
     */
    public void displayHeap(int[] array) {
        int size = array.length;
        System.out.println("\nPARENT NODE" + "\t" + "LEFT NODE" + "\t" + "RIGHT NODE");
        for (int i = 0; i < size / 2; i++) {
            int parent = 0;
            int leftChild = 0;
            int rightChild = 0;
            parent = array[2 * i];
            if (2 * i + 1 < size) {
                leftChild = array[2 * i + 1];
            }
            if (2 * i + 2 < size) {
                rightChild = array[2 * i + 2];
            }
            System.out.print(" " + parent + "\t\t" + leftChild
                    + "\t\t" + rightChild);
            System.out.println();
        }
    }

}

class MyPriorityQueue {

    /**
     * Class constructor
     */
    MyPriorityQueue() {

    }

    /**
     * 
     * This method sorts the array by iterating through an array and inserting each
     * element into its
     * correct position in a new, sorted array. The method begins by assuming that
     * the first element in the array is sorted, and then it iterates through the
     * rest of the array, inserting each element into its correct position in the
     * sorted array.
     * 
     * @param array The array of Integer values to use.
     */
    public void insertionSort(int[] array) {
        int i;
        int key;

        for (int j = 1; j < array.length; j++) {

            key = array[j];
            i = j - 1;

            while ((i >= 0) && (array[i] > key)) {
                array[i + 1] = array[i];
                i--;
            }

            array[i + 1] = key;
        }
    }
}

class MySortedPriorityQueue {

    /**
     * Class constructor
     */
    MySortedPriorityQueue() {
    }

    /**
     * This method finds the minimum value in an
     * array and swaps it with the value in the first position. It then finds the
     * second minimum value and swaps it with the value in the second position, and
     * so on.
     * 
     * @param array The array of Integer values to use.
     */
    public void selectionSort(int[] array) {
        int minpos;
        for (int i = 0; i < array.length; i++) {
            minpos = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minpos])
                    minpos = j;
            }
            swapKeys(array, minpos, i);
        }
    }

    /**
     * The swapKeys method swaps the values of two elements in an array. The first
     * element is specified by the 'i' parameter and the second element is specified
     * by the 'j' parameter. A temporary variable is used to store the value of the
     * first element so that it does not get overwritten when the second element is
     * assigned to the first element's position.
     * 
     * @param array The array of Integer values to use.
     * @param i is the first element in the array.
     * @param j is the second element in the array.
     */
    public void swapKeys(int[] array, int i, int j) {
        int temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
