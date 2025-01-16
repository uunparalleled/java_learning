package leetcode.queue;

import java.util.*;

public class QueueMain {

    public static void main(String[] args) {

    }


    /**
     * leetcode 347. 前 K 个高频元素  PriorityQueue<Integer[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
     */
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 从小到大
        PriorityQueue<Integer[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        // 从大到小
//        PriorityQueue<Integer[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]).reversed());

        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            pq.offer(new Integer[]{entry.getKey(), entry.getValue()});
            // 弹出小的
            if (pq.size() > k) {
                pq.poll();
            }
        }
        int[] res = new int[k];
        int i = 0;
        while (!pq.isEmpty()) {
            Integer[] arr = pq.poll();
            res[i++] = arr[0];
        }
        return res;
    }

    /**
     * leetcode 239. 滑动窗口最大值   使用双端队列 Deque    list.remove(0)的时间复杂度是O(n)    只记录索引 简化操作
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i < k; ++i) {
            // 学习！！！
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;

    }
}
