public class User {
    public String name; //Name
    public String address; //Address
    public String socialSecurity; //Social Security
    public double balance; //An initial deposit amount

    public int ID; //ID distributed for users

    public User next;

    public User(String name, String address, String socialSecurity, double balance) {
        this.name = name;
        this.address = address;
        this.socialSecurity = socialSecurity;
        this.balance = balance;
    }

    public void getUser(){
        System.out.print(name);
        System.out.print(" " + address);
        System.out.print(" " + socialSecurity);
        System.out.print(" " + balance);
        System.out.println(" " + ID);
    }


}

