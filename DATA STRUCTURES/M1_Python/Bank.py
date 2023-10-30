from User import User


class Bank:
    def __init__(self):
        self.head = None
        self.user_nums = 0

    def add_user(self, new_user: User) -> None:
        if self.head is None:
            self.head = new_user
            new_user.ID = 1
            self.user_nums += 1
            return
        else:
            cur = self.head
            expection_id = 1
            if cur.ID != expection_id:
                new_user.ID = expection_id
                self.head = new_user
                self.user_nums += 1
                return

            expection_id = 2
            while cur.next is not None:
                if cur.next.ID != expection_id:
                    break
                expection_id += 1
                cur = cur.next
            new_user.next = cur.next
            cur.next = new_user
            new_user.ID = expection_id

        self.user_nums += 1

    def delete_user(self, user_id: int) -> None:
        if self.head is None:
            print("No accounts in bank")
        else:
            delete_flag = False

            if self.head.ID == user_id:
                if self.user_nums == 1:
                    self.head = None
                else:
                    self.head = self.head.next
                self.user_nums -= 1
                return

            cur = self.head
            while cur.next is not None:
                if cur.next.ID == user_id:
                    cur.next = cur.next.next
                    delete_flag = True
                    break
                cur = cur.next
            if not delete_flag:
                print("Cannot find user")

    def pay_user_to_user(self, payer_id: int, payee_id: int, amount: float) -> None:
        cur = self.head
        while cur is not None:
            if cur.ID == payer_id:
                if cur.balance >= amount:
                    cur.balance -= amount
                else:
                    print("Not enough money")
            if cur.ID == payee_id:
                cur.balance += amount
            cur = cur.next

    def get_median_id(self) -> None:
        count = 1
        cur = self.head
        if self.user_nums % 2 == 0:
            while cur is not None:
                if count == self.user_nums // 2:
                    return cur.ID
                count += 1
                cur = cur.next
        else:
            median = (self.user_nums // 2) + 1
            while cur is not None:
                if count == median:
                    return cur.ID
                count += 1
                cur = cur.next
        return -1

    def merge_account(self, id1: int, id2: int) -> None:
        user1 = None
        user2 = None
        sum_balance = 0
        cur = self.head

        while cur is not None:
            if cur.ID == id1:
                user1 = cur
                sum_balance += cur.balance
            elif cur.ID == id2:
                user2 = cur
                sum_balance += cur.balance
            cur = cur.next

        if user1 is not None and user2 is not None:
            if user1.name == user2.name and user1.address == user2.address and user1.social_security == user2.social_security:
                if user1.ID < user2.ID:
                    user1.balance += user2.balance
                    self.delete_user(user2.ID)
                else:
                    user2.balance += user1.balance
                    self.delete_user(user1.ID)

    @staticmethod
    def merge_banks(bank1: 'Bank', bank2: 'Bank') -> 'Bank':
        new_bank = Bank()
        cur1 = bank1.head
        while cur1 is not None:
            new_user = User(cur1.name, cur1.address, cur1.social_security, cur1.balance)
            new_bank.add_user(new_user)
            cur1 = cur1.next

        cur2 = bank2.head
        while cur2 is not None:
            new_user = User(cur2.name, cur2.address, cur2.social_security, cur2.balance)
            new_bank.add_user(new_user)
            cur2 = cur2.next

        return new_bank

    def display_all_users(self) -> None:
        print("Number of Users:", self.user_nums)
        cur = self.head
        while cur is not None:
            print(cur.get_user())
            cur = cur.next
