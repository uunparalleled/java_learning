package leetcode.week;

import java.util.*;

import static java.util.Comparator.*;

public class Week20250215 {


    public static int shortestMatchingSubstring(String s, String p) {
        String[] pp = p.split("\\*");

        int res = Integer.MAX_VALUE;
        int index = -1;
        if (s.contains(pp[0])) {
            while (index != 0) {
                index = s.indexOf(pp[0], index);
                index += 1;

                int t1 = index;
                while (index != 0 && t1 != 0) {
                    t1 = s.indexOf(pp[1], t1);
                    t1 += 1;

                    int t2 = t1;
                    while (t1 != 0 && t2 != pp[2].length()-1) {
                        t2 = s.indexOf(pp[2], t2);
                        t2 += pp[2].length();

                        if (t2 != pp[2].length()-1) {
                            System.out.println(s.substring(index-1,t2));
                            res = Math.min(res, t2 - index + 1);
                        }
                    }
                }
            }
        }
        return res;
    }

    /**
     * leetcode 3453. 分割正方形 I   150双周赛 题3   浮点二分
     * r = mid - 0.00001    l = mid + 0.00001   加快搜索
     * 注意 int范围 double y = (double) square[1] 避免越界
     */
    public double separateSquares(int[][] squares) {
        double l = Double.MAX_VALUE;
        double r = Double.MIN_VALUE;
        double mid = 0;
        for (int[] square : squares) {
            l = Math.min(square[1],l);
            r = Math.max(square[1]+square[2],r);
        }

        while (l < r) {
            mid = (l + r) / 2;
            double ls = 0, rs = 0;
            for (int[] square : squares) {
                double y = (double) square[1];
                double d = (double) square[2];
                if (y >= mid) {
                    rs += d * d;
                } else if (y + d <= mid) {
                    ls += d * d;
                } else {
                    rs += d * (y + d - mid);
                    ls += d * (mid - y);
                }
            }
            if (ls > rs) {
                r = mid - 0.00001;
            } else if (rs > ls) {
                l = mid + 0.00001;
            } else {
                r = mid;
                if (Math.abs(l-r) <= 0.00001) {
                    return mid;
                }
            }
        }
        return l;
    }


    public static int sumOfGoodNumbers(int[] nums, int k) {
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int l = Integer.MIN_VALUE;;
            int r = Integer.MIN_VALUE;;
            if (i - k >= 0) {
                l = nums[i-k];
            }
            if (i + k < n) {
                r = nums[i+k];
            }
            if (nums[i] > l && nums[i] > r) res += nums[i];
        }
        return res;
    }
}
