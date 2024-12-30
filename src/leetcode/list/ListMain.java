package leetcode.list;

import java.util.ArrayList;
import java.util.List;

public class ListMain {
    public static void main(String[] args) {
        int[] nums = {1,2};
        int[] nums1 = {0,5,1};

        ListNode le = new ListNode(nums);
        ListNode le1 = new ListNode(nums1);
        le1.next.next.next = le.next.next;
        System.out.println(isPalindrome(le));
        System.out.println(le);
    }

    /**
     * leetcode 234. 回文链表       快慢指针 将链表分为两段
     */
    public static boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        // 找到前半部分链表的尾节点 slow
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 链表过短 无需反转
        if (fast == slow) {
            if (fast.next != null && fast.next.val == fast.val) return true;
            else return fast.next == null;
        }

        // 反转后半部分链表
        ListNode pre = slow;
        ListNode cur = slow.next;
        ListNode tmp = null;
        slow.next = null;
        if (fast.next != null) fast = fast.next;
        while (cur != null) {
            tmp = cur.next;
            cur.next = pre;

            pre = cur;
            cur = tmp;
        }

        // 判断是否回文
        ListNode index1 = head;
        ListNode index2 = fast;
        while (index1 != null && index2 != null) {
            if (index1.val != index2.val) return false;
            index1 = index1.next;
            index2 = index2.next;
        }
        return true;
    }

    /**
     * leetcode 141. 环形链表       快慢指针
     */
    public static boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) return true;
        }
        return false;
    }

    /**
     * leetcode 142. 环形链表 II    快慢指针
     */
    public static ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode index1 = fast;
                ListNode index2 = head;
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index2;
            }
        }
        return null;

//        时间复杂度太高
//        List<ListNode> list = new ArrayList<>();
//        ListNode cur = head;
//
//        while (cur != null) {
//            if (list.contains(cur)) return cur;
//            list.add(cur);
//            cur = cur.next;
//        }
//        return null;
    }

    /**
     * leetcode 160. 相交链表   面试题 02.07. 链表相交
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;
        int lenA = 0, lenB = 0;

        while (curA != null) {
            curA = curA.next;
            lenA++;
        }
        while (curB != null) {
            curB = curB.next;
            lenB++;
        }
        curA = headA;
        curB = headB;
        if (lenA > lenB) {
            for (int i = 0; i < lenA - lenB; i++) {
                curA = curA.next;
            }
        } else {
            for (int i = 0; i < lenB - lenA; i++) {
                curB = curB.next;
            }
        }

        while (curA != null && curB != null) {
            if (curA == curB) return curB;
            curA = curA.next;
            curB = curB.next;
        }
        return null;
    }

    /**
     * leetcode 24. 两两交换链表中的节点
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode cur = dummyNode;
        int len = 0;

        while (cur.next != null) {
            cur = cur.next;
            len++;
        }

        cur = dummyNode;
        for (int i = 0; i < len - n; i++) {
            cur = cur.next;
        }

        cur.next = cur.next.next;

        return dummyNode.next;
    }

    /**
     * leetcode  24. 两两交换链表中的节点
     */
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode pre = null;
        ListNode cur1 = head;
        ListNode cur2 = head.next;
        head = cur2;

        while (true) {
            cur1.next = cur2.next;
            cur2.next = cur1;
            if (pre != null) pre.next = cur2;

            pre = cur1;
            cur1 = cur1.next;
            if (cur1 == null || cur1.next == null) return head;
            cur2 = cur1.next;
        }
    }

    /**
     * leetcode 206. 反转链表
     */
    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp = null;
        while (cur != null) {
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }

        return cur;
    }

    /**
     * leetcode 203. 移除链表元素
     */
    public static ListNode removeElements(ListNode head, int val) {
        while (head != null) {
            if (head.val == val) {
                head = head.next;
            } else break;
        }
        if (head == null) return null;

        ListNode res = head;
        ListNode next = head.next;
        while (next != null) {
            if (next.val == val) {
                head.next = next.next;
            } else {
                head = head.next;
            }
            next = next.next;
        }
        return res;
    }
}
