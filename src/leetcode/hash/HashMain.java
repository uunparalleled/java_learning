package leetcode.hash;

import java.util.*;

public class HashMain {

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        String s = "AAAAAAABBCCCC";

        System.out.println(Arrays.toString(new List[]{threeSum(nums)}));
    }


    // 数组就是简单的哈希表，但是数组的大小可不是无限开辟的

    /**
     * leetcode 383. 赎金信
     */
    public static boolean canConstruct(String ransomNote, String magazine) {
        int count = 0;
        int[] hash = new int[26];
        for (char ch : ransomNote.toCharArray()) {
            hash[ch - 'a']++;
            count++;
        }
        for (char ch : magazine.toCharArray()) {
            hash[ch - 'a']--;
            if (hash[ch - 'a'] >= 0) {
                count--;
            }
        }
        return count == 0;
    }

    /**
     * leetcode 454. 四数相加 II
     */
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();

        for (int n1 : nums1) {
            for (int n2 : nums2) {
                map1.put(n1 + n2, map1.getOrDefault(n1 + n2, 0) + 1);
            }
        }

        int res = 0;
        for (int n1 : nums3) {
            for (int n2 : nums4) {
                if (map1.containsKey(-n1 - n2)) {
                    res += map1.get(-n1 - n2);
                }
            }
        }
        return res;
    }

    /**
     * leetcode 18. 四数之和
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        return null;
    }

    /**
     * leetcode 15. 三数之和            双指针  排序！！！ 遍历+判断条件
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        return null;
    }

    /**
     * leetcode 1. 两数之和
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i],i);
        }
        return null;
    }

    /**
     * leetcode 202. 快乐数
     */
    public static boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();

        while (n != 1) {
            int tmp = n;
            int sum = 0;
            while (tmp != 0) {
                sum += (tmp % 10) * (tmp % 10);
                tmp = tmp / 10;
            }
            if (set.contains(sum)) return false;
            set.add(sum);
            n = sum;
        }
        return true;
    }

    /**
     * leetcode 349. 两个数组的交集
     */
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> res = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }
        for (int num : nums2) {
            if (set.contains(num)) res.add(num);
        }

        return res.stream()
                .mapToInt(x->x)
                .toArray();
    }

    /**
     * leetcode 242. 有效的字母异位词
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] hash = new int[26];
        for (char ch : s.toCharArray()) {
            hash[ch - 'a']++;
        }
        for (char ch : t.toCharArray()) {
            hash[ch - 'a']--;
            if (hash[ch - 'a'] < 0) return false;
        }
        return true;
    }
}
