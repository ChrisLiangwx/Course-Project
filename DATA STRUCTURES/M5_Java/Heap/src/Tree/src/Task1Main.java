package Tree.src;

import java.io.*;

public class Task1Main {
    public static void main(String[] args) {


        //create tree and display it
        BSTree<String> tree = new BSTree<String>();
        String filePath = "../tree-input.txt";
        try{
            FileReader fr =new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while((line = br.readLine()) != null){
                if(line.charAt(0) == 'I'){
                    Node<String> newNode = new Node<>(line.substring(1, 7));
                    newNode.otherData = line;
                    tree.insert(newNode);
                }else{
                    Node<String> newNode = new Node<>(line.substring(1, 7));
                    tree.delete(newNode.data);
                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }
        tree.displayBSTree(tree.root);


        //do DFS and BFS
        //create the txt files
        String DFSPath = "../DFS.txt";
        String BFSPath = "../BFS.txt";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(DFSPath));
            tree.DFS(tree.root, writer);
            writer.close();

            writer = new BufferedWriter(new FileWriter(BFSPath));
            tree.BFS(tree.root, writer);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}