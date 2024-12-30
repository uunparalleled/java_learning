package leetcode.list;

public class LinkedListNode {
    int val;
    LinkedListNode next;
    LinkedListNode() {}
    LinkedListNode(int val) { this.val = val; }
    LinkedListNode(int val, LinkedListNode next) { this.val = val; this.next = next; }

    LinkedListNode(int[] nums) {
        if (nums == null || nums.length == 0) return;

        LinkedListNode head = this;
        head.val = nums[0];

        for (int i = 1; i < nums.length; i++) {
            head.next = new LinkedListNode(nums[i]);
            head = head.next;
        }
    }

    @Override
    public String toString() {
        LinkedListNode head = this;
        StringBuilder sb = new StringBuilder();

        while (head != null) {
            sb.append(head.val);
            head = head.next;
            if (head != null) {
                sb.append(" -> ");
            }
        }

        return sb.toString();
    }
}
