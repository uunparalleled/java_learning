package leetcode;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] nums = new int[n][m];
        int[][] nSum = new int[n][m];
        int[][] mSum = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                nums[i][j] = sc.nextInt();
                nSum[i][j] += nums[i][j] + (i == 0 ? 0 : nSum[i-1][j]);
                mSum[i][j] += nums[i][j] + (j == 0 ? 0 : mSum[i][j-1]);
            }
        }
        int[] nDisSum = new int[n];
        int[] mDisSum = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                nSum[i][j] = 2*nSum[i][j] - nSum[n-1][j];
                nDisSum[i] += nSum[i][j];
                mSum[i][j] = 2*mSum[i][j] - mSum[i][m-1];
                mDisSum[j] += mSum[i][j];
            }
        }
        int res = Math.abs(nDisSum[0]);
        for (int dis : nDisSum) {
            if (Math.abs(dis) < Math.abs(res)) res = Math.abs(dis);
        }
        for (int dis : mDisSum) {
            if (Math.abs(dis) < Math.abs(res)) res = Math.abs(dis);
        }
        System.out.println(res);
    }
}

/**
 * ACM 笔记
 */
/*public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // 读取一个单词 例如：Hello
        String word = sc.next();
        // 读取整行文本
        String line = sc.nextLine();
        // 消耗换行符
        sc.nextLine();
        // 判断空白行
        boolean isEmptyLine = !line.trim().isEmpty();
        // 读取浮点数
        double d = sc.nextDouble();
    }*/
