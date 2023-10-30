import Tree.src.BSTree;


import java.util.List;

public class Task2Main {
    public static void main(String[] args) {
        BSTree<Integer> tree = new BSTree<>();
        Tree.src.Node<Integer> node1 = new Tree.src.Node<Integer>(5);
        Tree.src.Node<Integer> node2 = new Tree.src.Node<Integer>(3);
        Tree.src.Node<Integer> node3 = new Tree.src.Node<Integer>(4);
        Tree.src.Node<Integer> node4 = new Tree.src.Node<Integer>(1);
        Tree.src.Node<Integer> node5 = new Tree.src.Node<Integer>(7);

        tree.insert(node1);
        tree.insert(node2);
        tree.insert(node3);
        tree.insert(node4);
        tree.insert(node5);



        BSTToHeapTransformer minHeapTransformer = new BSTToHeapTransformer();
        List<Integer> bstList = minHeapTransformer.bstToMinHeap(tree);
//        System.out.println(bstList);
        Heap minHeap = new Heap();
        Node minHeapRoot = minHeap.createMinHeap(bstList);
        System.out.println("Min Heap:");
        minHeap.display(minHeapRoot);

        BSTToHeapTransformer maxHeapTransformer = new BSTToHeapTransformer();
        bstList = maxHeapTransformer.bstToMinHeap(tree);
//        System.out.println(bstList);
        Heap maxHeap = new Heap();
        Node maxHeapRoot = maxHeap.createMaxHeap(bstList);
        System.out.println("Max Heap:");
        minHeap.display(maxHeapRoot);



    }


}
