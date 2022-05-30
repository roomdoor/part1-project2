package ch02.LinearDS03;// Practice2
// 양방향 연결 리스트 (Doubly Linked List) 구현

class NodeBi {
    int data;
    NodeBi next;
    NodeBi prev;

    NodeBi(int data, NodeBi next, NodeBi prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
}

class DoublyLinkedList extends LinkedList {
    NodeBi head;
    NodeBi tail;

    DoublyLinkedList(NodeBi node) {
        this.head = node;
        this.tail = node;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public void addData(int data, Integer beforeData) {
        if (this.isEmpty()) {
            this.head = new NodeBi(data, null, null);
            this.tail = this.head;
        } else if (beforeData == null) {
            this.tail.next = new NodeBi(data, null, this.tail);
            this.tail = this.tail.next;
        } else {
            NodeBi cur = this.head;
            NodeBi pre = cur;
            while (cur != null) {
                if (cur.data == beforeData) {
                    if (cur == this.head) {
                        this.head = new NodeBi(data, this.head, null);
                        this.head.next.prev = this.head;
                    } else {
                        pre.next = new NodeBi(data, cur, pre);
                        cur.prev = pre.next;
                    }
                    break;
                }
                pre = cur;
                cur = cur.next;
            }
        }
    }
}


public class Practice2 {
    public static void main(String[] args) {

//      Test code
//        DoublyLinkedList myList = new DoublyLinkedList(new NodeBi(1, null, null));
//        myList.showData();          // 1
//
//        myList.addData(2, null);
//        myList.addData(3, null);
//        myList.addData(4, null);
//        myList.addData(5, null);
//        myList.showData();          // 1 2 3 4 5
//        myList.showDataFromTail();  // 5 4 3 2 1
//
//        myList.addData(100, 1);
//        myList.addData(200, 2);
//        myList.addData(300, 3);
//        myList.addData(400, 4);
//        myList.addData(500, 5);
//        myList.showData();          // 100 1 200 2 300 3 400 4 500 5
//        myList.showDataFromTail();  // 5 500 4 400 3 300 2 200 1 100
//
//        myList.removeData(300);
//        myList.removeData(100);
//        myList.removeData(500);
//        myList.removeData(200);
//        myList.removeData(400);
//        myList.showData();          // 1 2 3 4 5
//        myList.showDataFromTail();  // 5 4 3 2 1
//
//        myList.removeData(3);
//        myList.removeData(1);
//        myList.removeData(5);
//        myList.removeData(2);
//        myList.removeData(4);
//        myList.showData();          // List is empty!
//        myList.showDataFromTail();  // List is empty!
    }
}
