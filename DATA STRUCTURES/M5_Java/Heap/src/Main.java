import java.util.*;


public class Main {
            public static void main(String[] args) {
                Heap heap = new Heap();


                List<Integer> minHeapValues = Arrays.asList(4, 7, 2, 9, 1, 5);
                System.out.println(minHeapValues);
                Node minHeapRoot = heap.createMinHeap(minHeapValues);
                System.out.println("Min Heap:");
                heap.display(minHeapRoot);


                List<Integer> maxHeapValues = Arrays.asList(4, 7, 2, 9, 1, 5);
                Node maxHeapRoot = heap.createMaxHeap(maxHeapValues);
                System.out.println("Max Heap:");
                heap.display(maxHeapRoot);
            }
}

