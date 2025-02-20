package leetcode;

import leetcode.array.ArrayMain;
import leetcode.dp.dpBase;
import leetcode.week.Week20250215;

import java.util.ArrayList;
import java.util.List;

public class testMain {
    public static void main(String[] args) {

//        int[][] nums = inputData.inputArrayInt("[1,2,3,4],[12,13,14,5],[11,16,15,6],[10,9,8,7]");
//        int[] nums = {100000,2000};
        int[][] nums = {{0, 0, 1},{2, 2, 1}};
//        int[] res = searchRange(nums,3);
        String s = "baccbaadbc";
        String t = "cc*baa*adb";
        String[] ss = {"leet", "code"};
        List<String> ls = new ArrayList<>(List.of(ss));
//        System.out.println(Arrays.toString(res));

//        int a = (int) ((10e10 + 3) / 10e9);
//        System.out.println(10e9+7 - 1000000000);
//        System.out.println(Arrays.toString(getFinalState(nums, 2, 1000000)));

//        System.out.println(dpBase.lengthOfLIS(nums));
        System.out.println(Week20250215.shortestMatchingSubstring(s,t));
//        System.out.println(searchRotatedSortedArray(nums,0));
    }

}
