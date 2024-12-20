package leetcode.list;

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    ListNode(int[] nums) {
        if (nums == null || nums.length == 0) return;

        ListNode head = this;
        head.val = nums[0];

        for (int i = 1; i < nums.length; i++) {
            head.next = new ListNode(nums[i]);
            head = head.next;
        }
    }

    @Override
    public String toString() {
        ListNode head = this;
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
