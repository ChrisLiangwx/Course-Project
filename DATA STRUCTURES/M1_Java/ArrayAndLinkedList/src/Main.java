// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
       User user1 =  new User("Wx", "Newport Beach", "1111", 2000);
       User user2 = new User("Mh", "Newport Beach", "2222", 1500);
       User user3 = new User("Sd", "Newport Beach", "3333", 3500);
       User user4 = new User("Tom", "UCI", "4444", 1000);
       Bank bankOfOrangeCounty = new Bank();

       //add users
       System.out.println("add users:");
       bankOfOrangeCounty.addUser(user1);
       bankOfOrangeCounty.addUser(user2);
       bankOfOrangeCounty.addUser(user3);
       bankOfOrangeCounty.addUser(user4);
       bankOfOrangeCounty.displayAllUser();

       System.out.println("-----------------");

       //delete users
       System.out.println("delete users:");
       bankOfOrangeCounty.deleteUser(1);
       bankOfOrangeCounty.deleteUser(4);
       bankOfOrangeCounty.displayAllUser();
       System.out.println("-----------------");

      //insert users again
       System.out.println("insert users again:");
       User userNew1 = new User("Jerry", "UCI", "5678", 2000);
       User userNew2 = new User("Mike", "UCI", "8765", 2000);

       bankOfOrangeCounty.addUser(userNew1);
       bankOfOrangeCounty.addUser(userNew2);
       bankOfOrangeCounty.displayAllUser();


//       System.out.println("-----------------");
//       //pay from user1 to user2
//       System.out.println("pay from user1 to user2:");
//       bankOfOrangeCounty.payUserToUser(2,3,500);
//       bankOfOrangeCounty.displayAllUser();
//
//       System.out.println("-----------------");
//       //show median
//       System.out.println("show median:");
//       System.out.println(bankOfOrangeCounty.getMedianID());
//
//       System.out.println("-----------------");
//       //merge user account
//       User user11 =  new User("Sd", "Newport Beach", "3333", 3500);
//       System.out.println("merge user account:");
//       bankOfOrangeCounty.addUser(user11);
//       System.out.println("before merge:");
//       bankOfOrangeCounty.displayAllUser();
//       bankOfOrangeCounty.mergeAccount(2, 3);
//       System.out.println();
//       System.out.println("after merge:");
//       bankOfOrangeCounty.displayAllUser();
//
//
//       System.out.println("-----------------");
//       //merge banks
//       System.out.println("merge banks:");
//       Bank bankOfLosAngeles = new Bank();
//       User user01 =  new User("AA", "LA", "1111", 100);
//       User user02 = new User("BB", "LA", "2222", 100);
//       User user03 = new User("CC", "LA", "3333", 100);
//       bankOfLosAngeles.addUser(user01);
//       bankOfLosAngeles.addUser(user02);
//       bankOfLosAngeles.addUser(user03);
//       System.out.println();
//       System.out.println("display bank1:");
//       bankOfOrangeCounty.displayAllUser();
//       System.out.println();
//       System.out.println("display bank2:");
//       bankOfLosAngeles.displayAllUser();
//
//       System.out.println();
//       System.out.println("display merged bank");
//       Bank bankofSouthernCalifornia = Bank.mergeBanks(bankOfOrangeCounty,bankOfLosAngeles);
//       bankofSouthernCalifornia.displayAllUser();


    }
}