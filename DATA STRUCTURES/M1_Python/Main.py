from User import User
from Bank import Bank


class Main:
    def __init__(self):
        self.user1 = User("John Doe", "123 Main St", "123-45-6789", 1000.0)
        self.user1.ID = 1  # Set the user's ID

        self.user2 = User("Alice Smith", "456 Elm St", "987-65-4321", 1500.0)
        self.user2.ID = 2

        self.bank = Bank()
        self.bank.add_user(self.user1)
        self.bank.add_user(self.user2)
        self.bank.delete_user(1)

    def run(self):
        self.bank.display_all_users()



main_program = Main()
main_program.run()
