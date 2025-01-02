package leetcode.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ListMain {
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        int[] nums = {-1,5,3,4,0};
        int[] nums1 = {0,1,4};

        ListNode le = new ListNode(nums);
        ListNode le1 = new ListNode(nums1);
        System.out.println(sortList(le));
        System.out.println(le);
    }

    /**
     * leetcode 23. 合并 K 个升序链表              分治合并
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        int min = (int) -1e4;
        int n = (int) 2e4;
        int[] nums = new int[n];

        for (ListNode head : lists) {
            ListNode cur = head;
            while (cur != null) {
                nums[cur.val-min]++;
                cur = cur.next;
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0) {
                cur.next = new ListNode(i+min);
                cur = cur.next;
                nums[i]--;
            }
        }
        return dummy.next;
    }

    /**
     * leetcode 148. 排序链表       元素范围有限，直接把元素存到数组中，再取出
     */
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode mid = findMid(head);
        ListNode rightHead = mid.next;
        mid.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);

        // 合并两个有序链表 (升序)
        return mergeTwoLists(left, right);
    }

    // 返回 链表中间偏左的节点
    public static ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 冒泡排序
    /*
        boolean flag = false;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode tail = null;

        while (!flag) {
            flag = true;
            ListNode pre = dummyHead;
            ListNode cur = pre.next;

            while (cur != null && cur.next != null && cur.next != tail) {
                ListNode next = cur.next;
                if (cur.val > next.val) {
                    cur.next = next.next;
                    next.next = cur;
                    pre.next = next;
                    flag = false;
                }
                pre = pre.next;
                cur = pre.next;
            }
            tail = cur;
            System.out.println(tail.val);
        }
        return dummyHead.
     */

    /**
     * leetcode 138. 随机链表的复制
     */
    public static Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node resHead = new Node(0);
        Node res = resHead;
        Node cur = head;

        while (cur != null) {
            res.next = new Node(cur.val);
            map.put(cur, res.next);
            res = res.next;
            cur = cur.next;
        }

        res = resHead;
        cur = head;

        while (cur != null) {
            res = res.next;
            res.random = map.getOrDefault(cur.random, null);

            cur = cur.next;
        }

        return resHead.next;
    }
    /**
     * leetcode 链表的复制
     */
    public static ListNode copyRandomListVal(ListNode head) {
        if (head == null) return null;

        ListNode node = new ListNode(head.val);
        node.next = copyRandomListVal(head.next);
        return node;
    }

    /**
     * leetcode 21. 合并两个有序链表        链表可以直接拼接，数组才要一个个填
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = (list1 != null) ? list1 : list2; // 将剩余节点直接连接

        return dummyHead.next;
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
     * leetcode 25. K 个一组翻转链表
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode cur = dummyHead.next;
        ListNode pre = dummyHead;
        ListNode tmp = null;
        ListNode tmp1 = null;
        ListNode nextPre = null;
        while (cur != null) {
            ListNode last = getIndex(pre, k);
            if (last == null) break;

            nextPre = cur;

            tmp = cur.next;
            cur.next = last.next;

            for (int i = 0; i < k - 1; i++) {
                tmp1 = tmp.next;
                tmp.next = cur;

                cur = tmp;
                tmp = tmp1;
            }

            pre.next = cur;
            pre = nextPre;
            cur = pre.next;
        }
        return dummyHead.next;
    }

    public static ListNode getIndex (ListNode head, int index) {
        if (index < 0) return null;
        ListNode cur = head;
        for (int i = 0; i < index; i++) {
            if (cur == null) return null;
            cur = cur.next;
        }
        return cur;
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
