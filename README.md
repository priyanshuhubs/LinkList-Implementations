Singly Linked List — Function-wise implementations

Files:
1. LinkedList_C.c — C implementation, includes actual node and next pointers printed with %p.
2. LinkedListTraditional.java — traditional Java syntax with Scanner.
3. LinkedListJava25.java — Java 25 compact source file and IO API preview syntax.
4. LinkedListPython.py — Python implementation.

All implementations include user input for initial node creation and a menu for:
- insert at end
- insert at beginning
- insert at index
- delete first node
- delete at index
- get element by index
- update element by index
- display list
- display size

Indexing starts at 0.

Run C (GCC):
    gcc LinkedList_C.c -o LinkedList_C
    ./LinkedList_C
On Windows:
    gcc LinkedList_C.c -o LinkedList_C.exe
    .\LinkedList_C.exe

Run traditional Java:
    javac LinkedListTraditional.java
    java LinkedListTraditional

Run Java 25 compact source file: java --enable-preview --source 25 LinkedListJava25.java
    
Note: IO and compact source file use Java 25 preview features.

Run Python: python LinkedListPython.py

Address note:
- C prints actual pointer values using %p.
- Java does not expose actual object memory addresses through ordinary Java syntax.
- Python id() displays object identity, not a portable raw memory address.

The uploaded teacher program creates a list and displays address/data/next-address. This pack preserves those core ideas and adds the requested menu-based operations.
