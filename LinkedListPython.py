class Node:
    def __init__(self, data):
        self.data = data
        self.next = None


class SinglyLinkedList:
    def __init__(self):
        self.head = None
        self.tail = None
        self.size = 0

    def insert_at_end(self, data):
        node = Node(data)
        if self.head is None:
            self.head = self.tail = node
        else:
            self.tail.next = node
            self.tail = node
        self.size += 1
        print(f"Inserted {data} at end.")

    def insert_at_beginning(self, data):
        node = Node(data)
        if self.head is None:
            self.head = self.tail = node
        else:
            node.next = self.head
            self.head = node
        self.size += 1
        print(f"Inserted {data} at beginning.")

    def insert_at_index(self, index, data):
        if index < 0 or index > self.size:
            print("Invalid index.")
            return
        if index == 0:
            self.insert_at_beginning(data)
            return
        if index == self.size:
            self.insert_at_end(data)
            return
        temp = self.head
        for _ in range(index - 1):
            temp = temp.next
        node = Node(data)
        node.next = temp.next
        temp.next = node
        self.size += 1
        print(f"Inserted {data} at index {index}.")

    def delete_at_beginning(self):
        if self.head is None:
            print("List is empty.")
            return
        deleted = self.head
        self.head = self.head.next
        self.size -= 1
        if self.head is None:
            self.tail = None
        print(f"Deleted {deleted.data}.")

    def delete_at_index(self, index):
        if index < 0 or index >= self.size:
            print("Invalid index.")
            return
        if index == 0:
            self.delete_at_beginning()
            return
        temp = self.head
        for _ in range(index - 1):
            temp = temp.next
        deleted = temp.next
        temp.next = deleted.next
        if deleted is self.tail:
            self.tail = temp
        self.size -= 1
        print(f"Deleted {deleted.data}.")

    def get_at_index(self, index):
        if index < 0 or index >= self.size:
            print("Invalid index.")
            return
        temp = self.head
        for _ in range(index):
            temp = temp.next
        print(f"Element at index {index}: {temp.data}")

    def update_at_index(self, index, new_data):
        if index < 0 or index >= self.size:
            print("Invalid index.")
            return
        temp = self.head
        for _ in range(index):
            temp = temp.next
        temp.data = new_data
        print("Element updated.")

    def display(self):
        if self.head is None:
            print("List is empty.")
            return
        temp = self.head
        print("List: ", end="")
        while temp is not None:
            print(temp.data, end=" -> ")
            temp = temp.next
        print("None")

    def display_identities(self):
        # id() is Python's object identity, not a portable raw memory address.
        if self.head is None:
            print("List is empty.")
            return
        print("Object identity\tData\tNext identity")
        temp = self.head
        while temp is not None:
            next_id = id(temp.next) if temp.next is not None else None
            print(f"{id(temp)}\t{temp.data}\t{next_id}")
            temp = temp.next


def read_int(prompt):
    while True:
        try:
            return int(input(prompt))
        except ValueError:
            print("Please enter a valid integer.")


def main():
    linked_list = SinglyLinkedList()
    n = read_int("How many initial nodes? ")
    if n < 0:
        print("Count cannot be negative; starting with an empty list.")
        n = 0

    for i in range(n):
        linked_list.insert_at_end(read_int(f"Enter data for node {i + 1}: "))

    while True:
        print("\n===== SINGLY LINKED LIST =====")
        print("1. Insert at end")
        print("2. Insert at beginning")
        print("3. Insert at index")
        print("4. Delete first node")
        print("5. Delete at index")
        print("6. Get element")
        print("7. Update element")
        print("8. Display list")
        print("9. Display object identities")
        print("10. Size")
        print("0. Exit")
        choice = read_int("Enter choice: ")

        if choice == 1:
            linked_list.insert_at_end(read_int("Enter value: "))
        elif choice == 2:
            linked_list.insert_at_beginning(read_int("Enter value: "))
        elif choice == 3:
            index = read_int("Enter index: ")
            value = read_int("Enter value: ")
            linked_list.insert_at_index(index, value)
        elif choice == 4:
            linked_list.delete_at_beginning()
        elif choice == 5:
            linked_list.delete_at_index(read_int("Enter index: "))
        elif choice == 6:
            linked_list.get_at_index(read_int("Enter index: "))
        elif choice == 7:
            index = read_int("Enter index: ")
            value = read_int("Enter new value: ")
            linked_list.update_at_index(index, value)
        elif choice == 8:
            linked_list.display()
        elif choice == 9:
            linked_list.display_identities()
        elif choice == 10:
            print("List size:", linked_list.size)
        elif choice == 0:
            print("Exiting.")
            break
        else:
            print("Invalid choice.")


if __name__ == "__main__":
    main()
