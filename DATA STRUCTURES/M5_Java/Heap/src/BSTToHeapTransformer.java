import Tree.src.*;
import Tree.src.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BSTToHeapTransformer {
    public List<Integer> bstToMinHeap(BSTree bst){
        List<Integer> resultList = new ArrayList<>();
        if (bst.root == null) {
            return null;
        }

        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.offer(bst.root);

        //create a list with all nodes in BSTree
        while (!queue.isEmpty()) {
            Node<Integer> current = queue.poll();
            resultList.add(current.data);
//            System.out.println(current.data);

            if (current.left != null) {
                queue.offer(current.left);
            }

            if (current.right != null) {
                queue.offer(current.right);
            }
        }

        return resultList;
    }


    public List<Integer> bstToMaxHeap(BSTree bst){
        List<Integer> resultList = new ArrayList<>();
        if (bst.root == null) {
            return null;
        }

        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.offer(bst.root);

        //create a list with all nodes in BSTree
        while (!queue.isEmpty()) {
            Node<Integer> current = queue.poll();
            resultList.add(current.data);

            if (current.left != null) {
                queue.offer(current.left);
            }

            if (current.right != null) {
                queue.offer(current.right);
            }
        }

        return resultList;
    }





}
