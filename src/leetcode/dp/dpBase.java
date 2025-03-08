package leetcode.dp;

import java.util.*;

public class dpBase {


    /**
     * leetcode 416. 分割等和子集
     */
    public static boolean canPartition(int[] nums) {
        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        if (sum % 2 == 1) return false;

        boolean[] dp = new boolean[sum/2 + 1];
        List<Integer> dpList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] < sum/2) {
                for (int j = 0; j < dpList.size(); j++) {
                    if (dpList.get(j) + nums[i] <= sum/2) {
                        dpList.add(dpList.get(j) + nums[i]);
                    }
                }
                dpList.add(nums[i]);
            } else if (nums[i] == sum/2) return true;
            if (dpList.contains(sum/2)) return true;
        }
        return false;
    }

    /**
     * leetcode 152. 乘积最大子数组
     */
    public static int maxProduct(int[] nums) {

        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int res = nums[0];
        int min1 = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i-1] * nums[i];
            if (dp[i] < 0) {
                if (dp[i] > min1) {
                    min1 = dp[i];
                }
                res = Math.max(res,dp[i]/min1);
            } else {
                res = Math.max(res,dp[i]);
            }
        }
        return res;
    }

    /**
     * leetcode 300. 最长递增子序列
     */
    public static int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int res = 0;
        for(int num : nums) {
            int i = 0, j = res;
            while(i < j) {
                int m = (i + j) / 2;
                if(tails[m] < num) i = m + 1;
                else j = m;
            }
            tails[i] = num;
            if(res == j) res++;
        }
        return res;
    }

    /**
     * leetcode 139. 单词拆分
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        int maxLen = 0;
        for (String word : wordDict) {
            maxLen = Math.max(maxLen, word.length());
        }
        boolean[] dp = new boolean[n+1];
        wordDict.add("");
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            String tmp1 = s.substring(0,i);
            for (int j = i-1; j >= Math.max(i - maxLen, 0); j--) {
                String tmp = s.substring(j,i);
                if (dp[j] && wordDict.contains(s.substring(j,i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    /**
     * leetcode 322. 零钱兑换
     */
    public static int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[max];
        dp[0] = 0;
        Arrays.fill(dp,max);


        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i],dp[i-coin]+1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];

    }

    /**
     * leetcode 279. 完全平方数
     */
    public static int numSquares(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = i;
            for (int j = 0; i - j * j >= 0; j++) {
                dp[i] = Math.min(dp[i],dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    /**
     * leetcode 118. 杨辉三角
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> last = new ArrayList<>();
        last.add(1);
        res.add(last);
        for (int i = 1; i < numRows; i++) {
            List<Integer> tmp = new ArrayList<>();
            tmp.add(1);
            int num = last.get(0);
            for (int j = 1; j <= i-1; j++) {
                tmp.add(num + last.get(j));
                num = last.get(j);
            }
            tmp.add(1);
            last = tmp;
            res.add(tmp);
        }
        return res;
    }

    /**
     * leetcode 70. 爬楼梯
     */
    public static int climbStairs(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    /**
     * leetcode 509. 斐波那契数
     */
    public static int fib(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;

        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
        // 递归
//        return fib(n-1) + fib(n-2);
    }
}
