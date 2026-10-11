#include <stdio.h>
#include <stdlib.h>

struct node {
    int data;
    struct node *next;
};

struct node *head = NULL;

struct node *createNode(int data) {
    struct node *newnode = malloc(sizeof(struct node));
    if (newnode == NULL) {
        printf("Memory allocation failed.\n");
        exit(EXIT_FAILURE);
    }
    newnode->data = data;
    newnode->next = NULL;
    return newnode;
}

void insertAtEnd(int data) {
    struct node *newnode = createNode(data);
    if (head == NULL) {
        head = newnode;
    } else {
        struct node *temp = head;
        while (temp->next != NULL) temp = temp->next;
        temp->next = newnode;
    }
    printf("Inserted %d at end.\n", data);
}

void insertAtBeginning(int data) {
    struct node *newnode = createNode(data);
    newnode->next = head;
    head = newnode;
    printf("Inserted %d at beginning.\n", data);
}

/* Indexing starts at 0. Valid insertion indexes: 0 to length. */
void insertAtIndex(int index, int data) {
    if (index < 0) {
        printf("Invalid index.\n");
        return;
    }
    if (index == 0) {
        insertAtBeginning(data);
        return;
    }

    struct node *temp = head;
    for (int i = 0; temp != NULL && i < index - 1; i++)
        temp = temp->next;

    if (temp == NULL) {
        printf("Invalid index.\n");
        return;
    }

    struct node *newnode = createNode(data);
    newnode->next = temp->next;
    temp->next = newnode;
    printf("Inserted %d at index %d.\n", data, index);
}

void deleteAtBeginning(void) {
    if (head == NULL) {
        printf("List is empty.\n");
        return;
    }
    struct node *temp = head;
    head = head->next;
    printf("Deleted %d.\n", temp->data);
    free(temp);
}

void deleteAtIndex(int index) {
    if (index < 0 || head == NULL) {
        printf("Invalid index or empty list.\n");
        return;
    }
    if (index == 0) {
        deleteAtBeginning();
        return;
    }

    struct node *temp = head;
    for (int i = 0; temp != NULL && i < index - 1; i++)
        temp = temp->next;

    if (temp == NULL || temp->next == NULL) {
        printf("Invalid index.\n");
        return;
    }

    struct node *deleted = temp->next;
    temp->next = deleted->next;
    printf("Deleted %d.\n", deleted->data);
    free(deleted);
}

void getAtIndex(int index) {
    if (index < 0) {
        printf("Invalid index.\n");
        return;
    }
    struct node *temp = head;
    for (int i = 0; temp != NULL && i < index; i++)
        temp = temp->next;

    if (temp == NULL) printf("Invalid index.\n");
    else printf("Element at index %d: %d\n", index, temp->data);
}

void updateAtIndex(int index, int newData) {
    if (index < 0) {
        printf("Invalid index.\n");
        return;
    }
    struct node *temp = head;
    for (int i = 0; temp != NULL && i < index; i++)
        temp = temp->next;

    if (temp == NULL) printf("Invalid index.\n");
    else {
        temp->data = newData;
        printf("Element updated.\n");
    }
}

int listSize(void) {
    int count = 0;
    struct node *temp = head;
    while (temp != NULL) {
        count++;
        temp = temp->next;
    }
    return count;
}

void displayList(void) {
    if (head == NULL) {
        printf("List is empty.\n");
        return;
    }
    struct node *temp = head;
    printf("List: ");
    while (temp != NULL) {
        printf("%d -> ", temp->data);
        temp = temp->next;
    }
    printf("NULL\n");
}

void displayAddresses(void) {
    if (head == NULL) {
        printf("List is empty.\n");
        return;
    }
    struct node *temp = head;
    printf("Node Address\t\tData\tNext Address\n");
    while (temp != NULL) {
        printf("%p\t%d\t%p\n", (void *)temp, temp->data, (void *)temp->next);
        temp = temp->next;
    }
}

void freeList(void) {
    while (head != NULL) deleteAtBeginning();
}

int readInt(const char *prompt) {
    int value;
    printf("%s", prompt);
    while (scanf("%d", &value) != 1) {
        int ch;
        while ((ch = getchar()) != '\n' && ch != EOF) {}
        printf("Please enter an integer: ");
    }
    return value;
}

int main(void) {
    int choice, value, index, n;
    n = readInt("How many initial nodes? ");
    if (n < 0) {
        printf("Count cannot be negative. Starting with an empty list.\n");
        n = 0;
    }
    for (int i = 0; i < n; i++) {
        char prompt[80];
        snprintf(prompt, sizeof(prompt), "Enter data for node %d: ", i + 1);
        value = readInt(prompt);
        insertAtEnd(value);
    }

    do {
        printf("\n===== SINGLY LINKED LIST =====\n");
        printf("1. Insert at end\n2. Insert at beginning\n3. Insert at index\n");
        printf("4. Delete first node\n5. Delete at index\n6. Get element by index\n");
        printf("7. Update element\n8. Display list\n9. Display addresses\n");
        printf("10. Size\n0. Exit\n");
        choice = readInt("Enter choice: ");

        switch (choice) {
            case 1:
                value = readInt("Enter value: ");
                insertAtEnd(value);
                break;
            case 2:
                value = readInt("Enter value: ");
                insertAtBeginning(value);
                break;
            case 3:
                index = readInt("Enter index: ");
                value = readInt("Enter value: ");
                insertAtIndex(index, value);
                break;
            case 4: deleteAtBeginning(); break;
            case 5:
                index = readInt("Enter index: ");
                deleteAtIndex(index);
                break;
            case 6:
                index = readInt("Enter index: ");
                getAtIndex(index);
                break;
            case 7:
                index = readInt("Enter index: ");
                value = readInt("Enter new value: ");
                updateAtIndex(index, value);
                break;
            case 8: displayList(); break;
            case 9: displayAddresses(); break;
            case 10: printf("List size: %d\n", listSize()); break;
            case 0: printf("Exiting.\n"); break;
            default: printf("Invalid choice.\n");
        }
    } while (choice != 0);

    freeList();
    return 0;
}
