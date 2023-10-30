public class Bank {
    public User head;

    int userNums; //number of users in the bank

    public Bank(){
        this.head = null;
    }

    void addUser(User newUser){
        if(head == null){
            //if the linked list is empty, create the first node
            head = newUser;
            newUser.ID = 1;
            userNums ++;
            return ;
        }else{
            //traverse the linked list to find an ID that is not consecutive
            //if so, insert the new user(node)
            //if not, append the new user
            User cur = head;

            //if the head's ID is larger than 1, then the inserted user's ID should be 1
            int expectedID = 1;
            if(cur.ID != expectedID){
                newUser.next = cur;
                newUser.ID = expectedID;
                head = newUser;
                userNums ++;
                return ;
            }

            expectedID = 2;
            while(cur.next != null){
                if(cur.next.ID != expectedID){
                    break;
                }
                expectedID ++;
                cur = cur.next;
            }
            newUser.next = cur.next;
            cur.next = newUser;
            newUser.ID = expectedID;
        }
        userNums ++;
    }


    public void deleteUser(int userID){
        //traverse the linked list to find the user with ID
        if(head == null){
            System.out.println("No accounts in bank");
            return ;
        }else{
            boolean deleteFlag = false;

            //if the first node is going to be deleted
            if(head.ID == userID){
                if(userNums == 1){
                    head = null;
                }else{
                    head = head.next;
                }
                userNums --;
                return ;
            }

            //for other nodes
            User cur = head;
            while(cur.next != null){
                if(cur.next.ID == userID){
                    //delete the node
                    cur.next = cur.next.next;
                    deleteFlag = true;
                    break;
                }
                cur = cur.next;
            }
            if(!deleteFlag){
                System.out.println("Cannot find user");
                return ;
            }
            userNums --;
        }

    }


    public void payUserToUser(int payerID, int payeeID, int amount){
        User cur = head;
        while(cur != null){
            //reduce the amount from payer account
            if(cur.ID == payerID){
                if(cur.balance >= amount){
                    cur.balance -= amount;
                }else{
                    System.out.println("No enough money");
                }
            }

            //add the amount to payee account
            if(cur.ID == payeeID){
                cur.balance += amount;
            }
            cur = cur.next;
        }
    }

    public int getMedianID(){
        int count = 1;
        User cur = head;
        if (userNums % 2 == 0){
            //there are even accounts
            while(cur != null){
                if(count == userNums / 2){
                    //return the first of the average ID
                    return cur.ID;
                }
                count ++;
            }
        }else{
            //there are odd accounts
            int median = (int)(userNums / 2) + 1;
            while(cur != null){
                if(count == median){
                    return cur.ID;
                }
                cur = cur.next;
                count ++;
            }
        }
        return -1;
    }

    public void mergeAccount(int ID1, int ID2){
        String name1, name2;
        String address1, address2;
        String socialSecurity1, socialSecurity2;
        double sum = 0;
        User user1 = null, user2 = null;

        User cur = head;
        while(cur != null){
            if(cur.ID == ID1){
                //record this user
                user1 = cur;
                name1 = cur.name;
                address1 = cur.address;
                socialSecurity1 = cur.socialSecurity;
                sum = sum + cur.balance;
            }else if(cur.ID == ID2){
                //record this user
                user2 = cur;
                name2 = cur.name;
                address2 = cur.address;
                socialSecurity2 = cur.socialSecurity;
                sum = sum + cur.balance;
            }
            cur = cur.next;
        }

        //compare the two
        if(user1.name == user1.name && user1.address == user2.address && user1.socialSecurity == user2.socialSecurity){
            //delete user with bigger ID and update the balance for user with smaller ID
            if(user1.ID < user2.ID){
                user1.balance += user2.balance;
                deleteUser(user2.ID);
            }else{
                user2.balance += user1.balance;
                deleteUser(user1.ID);
            }
        }


    }


    public static Bank mergeBanks(Bank bank1, Bank bank2){
        Bank newBank = new Bank();

        //two head nodes
        User cur1 = bank1.head;
        User cur2 = bank2.head;

        while(cur1 != null){
            User newUser = new User(cur1.name, cur1.address, cur1.socialSecurity, cur1.balance);
            newBank.addUser(newUser);
            cur1 = cur1.next;
        }

        while(cur2 != null){
            User newUser = new User(cur2.name, cur2.address, cur2.socialSecurity, cur2.balance);
            newBank.addUser(newUser);
            cur2 = cur2.next;
        }
        return newBank;
    }

    public void displayAllUser(){
        System.out.println("Number of Users:" + userNums);
        User cur = head;
        while(cur != null) {
            cur.getUser();
            cur = cur.next;
        }

    }
}
