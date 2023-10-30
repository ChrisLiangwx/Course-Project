import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.Queue;

public class BSTree<T extends Comparable<T>> {
    public Node<T> root;

    public BSTree(){
        this.root= null;
    }

    public void insert(Node<T> newNode){
        root = insertInto(root, newNode);
    }

    public Node<T> insertInto(Node<T> current, Node<T> newNode) {
        // New tree
        if (current == null) {
            return newNode;
        }

        if (newNode.data.compareTo(current.data) >= 0) {
            current.right = insertInto(current.right, newNode);
        } else {
            current.left = insertInto(current.left, newNode);
        }

        return current;
    }



    public void delete(T value) {
        root = deleteFrom(root, value);
    }

    private Node<T> deleteFrom(Node<T> current, T value) {
        if (current == null) {
            return null;
        }

        int comparison = value.compareTo(current.data);

        if (comparison < 0) {
            // Delete from the left subtree
            current.left = deleteFrom(current.left, value);
        } else if (comparison > 0) {
            // Delete from the right subtree
            current.right = deleteFrom(current.right, value);
        } else {
            // Node to be deleted is found
            if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            } else {
                // Node has two children
                Node<T> replaceNode = findMinNode(current.right);
                current.data = replaceNode.data;
                current.right = deleteFrom(current.right, replaceNode.data);
            }
        }

        return current;
    }

    private Node<T> findMinNode(Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }



    public void displayBSTree(Node<T> node) {
        if (node != null) {
            displayBSTree(node.left);
            System.out.println(node.data);
            System.out.println(node.otherData);
            displayBSTree(node.right);
        }
    }

    public void DFS(Node<T> node, BufferedWriter writer) throws IOException {
        if(node == null){
            return ;
        }
        DFS(node.left, writer);
        writer.write(node.otherData.toString());
        writer.newLine();
//        System.out.println(node.otherData);
        DFS(node.right, writer);

    }

    public void BFS(Node<T> node, BufferedWriter writer) throws IOException{
        if(node == null){
            return ;
        }
        Queue<Node<T>> queue = new LinkedList<>();

        queue.add(node);

        while(!queue.isEmpty()){
            Node<T> out = queue.poll();
            writer.write(out.otherData.toString());
            writer.newLine();

            if (out.left != null){
                queue.add(out.left);
            }
            if (out.right != null){
                queue.add(out.right);
            }
        }


    }



}
