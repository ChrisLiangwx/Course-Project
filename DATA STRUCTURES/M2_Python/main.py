from Node import Node
from Stack import Stack


def test_stack():
    n1 = Node(1)
    n2 = Node(2)
    n3 = Node(3)
    n4 = Node(4)
    s = Stack()
    s.push(n1)
    s.push(n2)
    s.pop()
    s.push(n3)
    s.push(n4)
    s.display_stack()
    print("size", s.size())
    print("top", s.peek().data)


if __name__ == '__main__':
    test_stack()


