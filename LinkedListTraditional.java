import java.util.Scanner;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

class SLL {
    private Node head;
    private Node tail;
    private int size;

    void insertAtEnd(int data) {
        Node node = new Node(data);
        if (head == null) head = tail = node;
        else {
            tail.next = node;
            tail = node;
        }
        size++;
        System.out.println("Inserted " + data + " at end.");
    }

    void insertAtBeginning(int data) {
        Node node = new Node(data);
        if (head == null) head = tail = node;
        else {
            node.next = head;
            head = node;
        }
        size++;
        System.out.println("Inserted " + data + " at beginning.");
    }

    void insertAtIndex(int index, int data) {
        if (index < 0 || index > size) {
            System.out.println("Invalid index.");
            return;
        }
        if (index == 0) { insertAtBeginning(data); return; }
        if (index == size) { insertAtEnd(data); return; }

        Node temp = head;
        for (int i = 0; i < index - 1; i++) temp = temp.next;
        Node node = new Node(data);
        node.next = temp.next;
        temp.next = node;
        size++;
        System.out.println("Inserted " + data + " at index " + index + ".");
    }

    void deleteAtBeginning() {
        if (head == null) { System.out.println("List is empty."); return; }
        System.out.println("Deleted " + head.data + ".");
        head = head.next;
        size--;
        if (head == null) tail = null;
    }

    void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Invalid index.");
            return;
        }
        if (index == 0) { deleteAtBeginning(); return; }

        Node temp = head;
        for (int i = 0; i < index - 1; i++) temp = temp.next;
        Node deleted = temp.next;
        temp.next = deleted.next;
        if (deleted == tail) tail = temp;
        size--;
        System.out.println("Deleted " + deleted.data + ".");
    }

    void getAtIndex(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Invalid index.");
            return;
        }
        Node temp = head;
        for (int i = 0; i < index; i++) temp = temp.next;
        System.out.println("Element at index " + index + ": " + temp.data);
    }

    void updateAtIndex(int index, int newData) {
        if (index < 0 || index >= size) {
            System.out.println("Invalid index.");
            return;
        }
        Node temp = head;
        for (int i = 0; i < index; i++) temp = temp.next;
        temp.data = newData;
        System.out.println("Element updated.");
    }

    int getSize() { return size; }

    void display() {
        if (head == null) { System.out.println("List is empty."); return; }
        Node temp = head;
        System.out.print("List: ");
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
}

public class LinkedListTraditional {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SLL list = new SLL();

        System.out.print("How many initial nodes? ");
        int n = sc.nextInt();
        if (n < 0) n = 0;
        for (int i = 0; i < n; i++) {
            System.out.print("Enter data for node " + (i + 1) + ": ");
            list.insertAtEnd(sc.nextInt());
        }

        int choice;
        do {
            System.out.println("\n===== SINGLY LINKED LIST =====");
            System.out.println("1. Insert at end");
            System.out.println("2. Insert at beginning");
            System.out.println("3. Insert at index");
            System.out.println("4. Delete first node");
            System.out.println("5. Delete at index");
            System.out.println("6. Get element");
            System.out.println("7. Update element");
            System.out.println("8. Display list");
            System.out.println("9. Size");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter value: ");
                    list.insertAtEnd(sc.nextInt());
                }
                case 2 -> {
                    System.out.print("Enter value: ");
                    list.insertAtBeginning(sc.nextInt());
                }
                case 3 -> {
                    System.out.print("Enter index: ");
                    int index = sc.nextInt();
                    System.out.print("Enter value: ");
                    list.insertAtIndex(index, sc.nextInt());
                }
                case 4 -> list.deleteAtBeginning();
                case 5 -> {
                    System.out.print("Enter index: ");
                    list.deleteAtIndex(sc.nextInt());
                }
                case 6 -> {
                    System.out.print("Enter index: ");
                    list.getAtIndex(sc.nextInt());
                }
                case 7 -> {
                    System.out.print("Enter index: ");
                    int index = sc.nextInt();
                    System.out.print("Enter new value: ");
                    list.updateAtIndex(index, sc.nextInt());
                }
                case 8 -> list.display();
                case 9 -> System.out.println("List size: " + list.getSize());
                case 0 -> System.out.println("Exiting.");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
        sc.close();
    }
}
