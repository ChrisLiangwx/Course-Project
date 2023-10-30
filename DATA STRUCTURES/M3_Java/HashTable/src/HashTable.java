import java.util.LinkedList;

public class HashTable {
    public LinkedList<String>[] linkedListArray;
    public int size;

    //constructor to create hashtable and set capacity
    public HashTable(int capacity){
        linkedListArray = new LinkedList[capacity];
        for(int i = 0; i < capacity; i++){
            linkedListArray[i] = new LinkedList<>();
        }
        size = 0;
    }

    public int hash(String str){
        //multiply the index of the char with its ASCII and add all chars
        int hash = 0;
        for(int i =0; i < str.length(); i++){
            hash += (i + 1) * (int)str.charAt(i);
        }
        hash %= linkedListArray.length;
        return hash;
    }

    public void insert(String str){
        int hash = hash(str);
        //check if it has already been added
        for(String ele : linkedListArray[hash]){
            if(ele.equals(str)){
                return ;
            }
        }
        linkedListArray[hash].add(str);
        size ++;
    }

    public int size(){
        return size;
    }

}
