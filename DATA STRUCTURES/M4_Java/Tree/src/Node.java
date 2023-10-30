public class Node<T extends Comparable<T>> {
    public T data; //used to sort

    public T otherData; //used to store other information

    public Node<T> left;

    public Node<T> right;

    public Node (T data){

        this.data=data;

        this.left=null;

        this.right=null;


    }
}

