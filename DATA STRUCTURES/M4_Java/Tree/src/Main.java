import java.io.*;

public class Main {
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
                    String name = line.substring(8, 32);
                    name = name.replaceAll("\\s", "");
                    Node<String> newNode = new Node<>(name);
                    newNode.otherData = line;
                    tree.insert(newNode);
                }else{
                    String name = line.substring(8, 32);
                    name = name.replaceAll("\\s", "");
                    Node<String> newNode = new Node<>(name);
                    tree.delete(newNode.data);
                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }
        tree.displayBSTree(tree.root);


//        //do DFS and BFS
//        //create the txt files
//        String DFSPath = "../DFS.txt";
//        String BFSPath = "../BFS.txt";
//
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(DFSPath));
//            tree.DFS(tree.root, writer);
//            writer.close();
//
//            writer = new BufferedWriter(new FileWriter(BFSPath));
//            tree.BFS(tree.root, writer);
//
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }
}