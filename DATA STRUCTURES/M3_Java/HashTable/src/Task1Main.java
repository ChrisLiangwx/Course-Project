// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Task1Main {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(5);

        // Insert some strings
        hashTable.insert("apple");
        hashTable.insert("banana");
        hashTable.insert("cherry");
        hashTable.insert("peach");
        hashTable.insert("elephant");

        // Test size()
        System.out.println("Size of the hash table: " + hashTable.size()); // Expected output: 5

        // Insert a duplicate string, it should not increase the size
        //collision
        hashTable.insert("apple");
        System.out.println("Size of the hash table after duplicate insertion: " + hashTable.size()); // Expected output: 5




    }
}