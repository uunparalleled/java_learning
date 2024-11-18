import java.util.Scanner;

public class SubArrayXORSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(); // 数组大小
        int[] nums = new int[n]; // 存储数组元素
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
//        int[] nums = {2,3,1,2};
//        int n = nums.length;

        long result = 0; // 存储结果
        int[][] bq = new int[n][n];

        for (int len = 1; len < n; len++) {
            for (int i = 0; i < n - len; i++) {
                if (len == 0 || len == 1) {
                    bq[len][i] = nums[i] ^ nums[i + len];
                } else {
                    for (int slen= 1; slen < len + 1; slen++) {
                        bq[len][i] += nums[i] ^ nums[i + slen];
//                        System.out.println(":" + (nums[i] ^ nums[i + j]));
                    }
                    bq[len][i] += bq[len - 1][i + 1];
//                    System.out.println("?" + bq[len - 1][i + 1]);
                }
//                System.out.println(bq[len][i]);
                result += bq[len][i]; // 累加到结果
                }
            }
        System.out.println(result);
   /*     for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                long sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += nums[k]; // 计算子序列的和
                }
                result += sum; // 累加到结果
            }
        }*/
    }

    //公共子串长度
    public static int seq(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}