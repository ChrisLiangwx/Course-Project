import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Heap {
    Node root = null;

    public Node createMinHeap(List<Integer> values) {
        if (values == null || values.isEmpty()) {
            return null;
        }

        Heap minHeap = new Heap();
        for (int value : values) {
            minHeap.insertIntoMin(value);
        }

        return minHeap.root;
    }

    public  void insertIntoMin(int data) {
        Node newNode = new Node(data);
        if (root == null) {
            root = newNode;
            return;
        }

        //insert node
        //make sure all levels are full before moving on to the next level
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.left == null) {
                cur.left = newNode;
                break;
            } else if (cur.right == null) {
                cur.right = newNode;
                break;
            } else {
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
        }

        heapifyMinHeap(newNode);
    }

    public void heapifyMinHeap(Node node) {
        Node parent = getParent(root, node);

        //make sure each node is smaller than its son
        //if not, swap node with its parent
        while (node != root && node.data < parent.data) {
            int temp = node.data;
            node.data = parent.data;
            parent.data = temp;
            node = parent;
            parent = getParent(root, node);
        }
    }

    public Node createMaxHeap(List<Integer> values){
        if (values == null || values.isEmpty()){
            return null;
        }

        Heap maxHeap = new Heap();
        for(int value : values){
            maxHeap.insertIntoMax(value);
        }

        return maxHeap.root;
    }

    public void insertIntoMax(int data) {
        Node newNode = new Node(data);
        if (root == null) {
            root = newNode;
            return;
        }

        //insert node
        //make sure all levels are full before moving on to the next level
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.left == null) {
                cur.left = newNode;
                break;
            } else if (cur.right == null) {
                cur.right = newNode;
                break;
            } else {
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
        }

        heapifyMaxHeap(newNode);
    }

    public void heapifyMaxHeap(Node node) {
        Node parent = getParent(root, node);

        //make sure each node is bigger than its son
        //if not, swap node with its parent
        while (node != root && node.data > parent.data) {
            int temp = node.data;
            node.data = parent.data;
            parent.data = temp;
            node = parent;
            parent = getParent(root, node);
        }
    }

    public Node getParent(Node current, Node target) {
        if (current == null || current == target) {
            return null;
        }

        if ((current.left != null && current.left == target) || (current.right != null && current.right == target)) {
            return current;
        }

        Node leftParent = getParent(current.left, target);
        if (leftParent != null) {
            return leftParent;
        }

        return getParent(current.right, target);
    }

    public void display(Node root) {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                Node currentNode = queue.poll();
                System.out.print(currentNode.data + " ");

                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }

                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            System.out.println();
        }
    }

}
