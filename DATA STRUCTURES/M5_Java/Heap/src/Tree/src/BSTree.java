package Tree.src;

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



    public void delete(T value){
        root = deleteFrom(root, value);
    }

    public Node<T> deleteFrom(Node<T> current, T value){
        if(current == null){
            return null;
        }
        if(current.data.compareTo(value) < 0){
            //right branch
            current.right = deleteFrom(current.right, value);
        }else if(current.data.compareTo(value) > 0){
            //left branch
            current.left = deleteFrom(current.left, value);
        }else{
            //node has been found
            //only one branch
            if(current.left == null){
                return current.right;
            }else if (current.right == null){
                return current.left;
            }
            //two branches
            else{
                //go to the most left node on the right branch
                Node replaceNode = current.right;
                T replaceValue = current.data;
                while(replaceNode.left != null){
                    replaceValue = (T) replaceNode.left.data;
                    replaceNode = replaceNode.left;
                }
                current.data = replaceValue;
                current.right = deleteFrom(current.right, replaceValue);
            }
        }


        return current;
    }



    public void displayBSTree(Node<T> node) {
        if (node != null) {
            displayBSTree(node.right);
            System.out.println(node.data);
            System.out.println(node.otherData);
            displayBSTree(node.left);
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
