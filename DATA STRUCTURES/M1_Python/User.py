class User:

    def __init__(self, name: str, address: str, social_security: str, balance: float):
        self.name = name
        self.address = address
        self.social_security = social_security
        self.balance = balance
        self.ID = None
        self.next = None

    def get_user(self) -> str:
        return f"{self.name} {self.address} {self.social_security} {self.balance} {self.ID}"
