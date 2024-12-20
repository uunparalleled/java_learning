package leetcode.list;

public class ListMain {
    public static void main(String[] args) {
        int[] nums = {1,2,6,3,4,5,6};

        ListNode le = new ListNode(nums);

        System.out.println(removeElements(le,6));
    }

    public static ListNode removeElements(ListNode head, int val) {
        return head;
    }
}
