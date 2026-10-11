// Java 25 compact source file; run with --enable-preview --source 25
class Node {
    int data;
    Node next;
    Node(int data) { this.data = data; }
}

class SLL {
    private Node head;
    private Node tail;
    private int size;

    void insertAtEnd(int data) {
        Node node = new Node(data);
        if (head == null) head = tail = node;
        else { tail.next = node; tail = node; }
        size++;
        IO.println("Inserted " + data + " at end.");
    }

    void insertAtBeginning(int data) {
        Node node = new Node(data);
        if (head == null) head = tail = node;
        else { node.next = head; head = node; }
        size++;
        IO.println("Inserted " + data + " at beginning.");
    }

    void insertAtIndex(int index, int data) {
        if (index < 0 || index > size) { IO.println("Invalid index."); return; }
        if (index == 0) { insertAtBeginning(data); return; }
        if (index == size) { insertAtEnd(data); return; }
        Node temp = head;
        for (int i = 0; i < index - 1; i++) temp = temp.next;
        Node node = new Node(data);
        node.next = temp.next;
        temp.next = node;
        size++;
        IO.println("Inserted " + data + " at index " + index + ".");
    }

    void deleteAtBeginning() {
        if (head == null) { IO.println("List is empty."); return; }
        IO.println("Deleted " + head.data + ".");
        head = head.next;
        size--;
        if (head == null) tail = null;
    }

    void deleteAtIndex(int index) {
        if (index < 0 || index >= size) { IO.println("Invalid index."); return; }
        if (index == 0) { deleteAtBeginning(); return; }
        Node temp = head;
        for (int i = 0; i < index - 1; i++) temp = temp.next;
        Node deleted = temp.next;
        temp.next = deleted.next;
        if (deleted == tail) tail = temp;
        size--;
        IO.println("Deleted " + deleted.data + ".");
    }

    void getAtIndex(int index) {
        if (index < 0 || index >= size) { IO.println("Invalid index."); return; }
        Node temp = head;
        for (int i = 0; i < index; i++) temp = temp.next;
        IO.println("Element at index " + index + ": " + temp.data);
    }

    void updateAtIndex(int index, int data) {
        if (index < 0 || index >= size) { IO.println("Invalid index."); return; }
        Node temp = head;
        for (int i = 0; i < index; i++) temp = temp.next;
        temp.data = data;
        IO.println("Element updated.");
    }

    int getSize() { return size; }

    void display() {
        if (head == null) { IO.println("List is empty."); return; }
        Node temp = head;
        IO.print("List: ");
        while (temp != null) { IO.print(temp.data + " -> "); temp = temp.next; }
        IO.println("null");
    }
}

void main() {
    SLL list = new SLL();
    int n = Integer.parseInt(IO.readln("How many initial nodes? "));
    for (int i = 0; i < n; i++) {
        int data = Integer.parseInt(IO.readln("Enter data for node " + (i + 1) + ": "));
        list.insertAtEnd(data);
    }

    while (true) {
        IO.println("\n===== SINGLY LINKED LIST =====");
        IO.println("1. Insert at end");
        IO.println("2. Insert at beginning");
        IO.println("3. Insert at index");
        IO.println("4. Delete first node");
        IO.println("5. Delete at index");
        IO.println("6. Get element");
        IO.println("7. Update element");
        IO.println("8. Display list");
        IO.println("9. Size");
        IO.println("0. Exit");
        String input = IO.readln("Enter choice: ");
        if (input == null) return;
        int choice;
        try { choice = Integer.parseInt(input); }
        catch (NumberFormatException e) { IO.println("Enter a valid integer."); continue; }

        try {
            switch (choice) {
                case 1 -> list.insertAtEnd(Integer.parseInt(IO.readln("Enter value: ")));
                case 2 -> list.insertAtBeginning(Integer.parseInt(IO.readln("Enter value: ")));
                case 3 -> {
                    int index = Integer.parseInt(IO.readln("Enter index: "));
                    int data = Integer.parseInt(IO.readln("Enter value: "));
                    list.insertAtIndex(index, data);
                }
                case 4 -> list.deleteAtBeginning();
                case 5 -> list.deleteAtIndex(Integer.parseInt(IO.readln("Enter index: ")));
                case 6 -> list.getAtIndex(Integer.parseInt(IO.readln("Enter index: ")));
                case 7 -> {
                    int index = Integer.parseInt(IO.readln("Enter index: "));
                    int data = Integer.parseInt(IO.readln("Enter new value: "));
                    list.updateAtIndex(index, data);
                }
                case 8 -> list.display();
                case 9 -> IO.println("List size: " + list.getSize());
                case 0 -> { IO.println("Exiting."); return; }
                default -> IO.println("Invalid choice.");
            }
        } catch (NumberFormatException e) {
            IO.println("Please enter a valid integer.");
        }
    }
}
