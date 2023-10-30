import java.io.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Task2Main {
    public static void main(String[] args) throws FileNotFoundException {
        HashTable hashTable = new HashTable(10000);

        String filePath = "../pride-and-prejudice.txt";
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                //split line into words
                String[] words = line.split("[^a-zA-Z0-9]+");
                for (String word:words) {
                    hashTable.insert(word);
                }

//                System.out.println(line);
            }

            br.close();
            System.out.println(hashTable.size);

        }catch (IOException e){
            e.printStackTrace();
        }









    }
}