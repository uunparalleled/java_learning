package leetcode.hash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HashMain {

    public static void main(String[] args) {
        int[] nums = {0,4,3,6,7,2,23,4,4,57,48,23,67,237,347,37};
        String s = "AAAAAAABBCCCC";

        System.out.println(Arrays.toString(nums));
    }


    // 数组就是简单的哈希表，但是数组的大小可不是无限开辟的


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
