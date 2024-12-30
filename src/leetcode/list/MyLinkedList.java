package leetcode.list;

/**
 * leetcode 707. 设计链表       虚拟头节点   不要画蛇添足
 */
public class MyLinkedList {
    int len;

    ListNode head;

    public MyLinkedList() {
    }

    public int get(int index) {
        if (index < 0 || index >= len) return -1;
        ListNode node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.val;
    }

    public void addAtHead(int val) {
        ListNode node = head;
        head = new ListNode(val);
        head.next = node;
        len++;
    }

    public void addAtTail(int val) {
        ListNode cNode = head;
        if (len == 0) {
            head = new ListNode(val);
            len++;
            return;
        }

        for (int i = 0; i < len-1; i++) {
            cNode = cNode.next;
        }
        cNode.next = new ListNode(val);
        len++;
    }

    public void addAtIndex(int index, int val) {
        if (index > len) return;
        else if (index == len) {
            addAtTail(val);
            return;
        }
        else if (index <= 0) {
            addAtHead(val);
            return;
        }

        ListNode cNode = head;
        for (int i = 0; i < index-1; i++) {
            cNode = cNode.next;
        }

        ListNode node = new ListNode(val);
        node.next = cNode.next;
        cNode.next = node;
        len++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= len) return;

        if (index == 0) {
            head = head.next;
            len--;
            return;
        }

        ListNode cNode = head;
        for (int i = 0; i < index-1; i++) {
            cNode = cNode.next;
        }
        cNode.next = cNode.next.next;
        len--;
    }

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(4);
        myLinkedList.get(1);
        myLinkedList.addAtHead(1);
        myLinkedList.addAtHead(5);
        myLinkedList.deleteAtIndex(3);    // 现在，链表变为 1->3
        myLinkedList.addAtHead(7);
        myLinkedList.get(3);
        myLinkedList.get(3);
        myLinkedList.get(3);
        myLinkedList.addAtHead(1);
        myLinkedList.deleteAtIndex(4);    // 现在，链表变为 1->3


        System.out.println(1);
    }
}
