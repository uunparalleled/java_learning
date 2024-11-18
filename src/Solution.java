import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt(); // 数组大小
//        String[] s = new String[n];
        int n = 3;
        int[][] pre = {{0, 1},{1, 2},{2, 3}};
        int[][] que = {{0, 3}};
        System.out.println(checkIfPrerequisite(n,pre,que));
    }


    /**
     * LeetCode 1462. 课程表 IV
     * @param numCourses
     * @param prerequisites
     * @param queries
     * @return
     */
    public static List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Boolean> res = new ArrayList<>();
        boolean[][] isNeed = new boolean[numCourses + 1][numCourses + 1];
        for (int i = 0; i < prerequisites.length; i++) {
            isNeeded(isNeed,prerequisites[i][0],prerequisites[i][1]);
        }
        for (int i = 0; i < queries.length; i++) {
            res.add(i,isNeed[queries[i][0]][queries[i][1]]);
        }
        return res;
    }
    public static boolean[][] isNeeded(boolean[][] isNeed, int pre, int af) {
        isNeed[pre][af] = true;
        for (int i = 0; i < isNeed.length; i++) {
            if (isNeed[af][i] && !isNeed[pre][i]) {
                isNeeded(isNeed,pre,i);
            }
        }
        for (int i = 0; i < isNeed.length; i++) {
            if (isNeed[i][pre] && !isNeed[i][af]) {
                isNeeded(isNeed,i,af);
            }
        }
        return isNeed;
    }
    /*//公共子串长度
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
    }*/

    /*public static int max(int n, int left, int right) {
        if (Math.abs(right - left) > n - 1) {
            return -1;
        }
        if (n == 3 && left == right) return left + 1;
        if (n == 3 && Math.abs(left - right) == 1) return Math.max(left,right);

        return (int) (Math.max(right,left) + (n - 2 - Math.abs(right - left))/2);
    }*/
   /* public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(); // 数组大小
        int m = scanner.nextInt();
        int left = n;
        int i = 1;
        int chayi = 0;
        ArrayList<Integer> list = new ArrayList<>();
        while (true){
            if (m - i <= left){
                i ++;
                list.add(i);
                left --;
            }else{
                if (m - i -chayi <= left){
                    i++;
                    list.add(i);
                    left --;
                }else{
                    i += chayi;
                    chayi ++;
                    list.add(i);
                    left --;
                }
            }
            if (list.size() == n){
                break;
            }
        }
        System.out.println(list);
    }*/
    /* // no.242.有效的字母异位词 答案：字符串转字符数组，使用数组sort排序，equals判断相等
    public static boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1,str2);
    }
    // no.242.有效的字母异位词
    public static boolean isAnagram(String s, String t) {
        int[] s_count = new int[26];
        int[] t_count = new int[26];
        for(int i = 0;i<s.length();i++){
            s_count[s.charAt(i)-'a']++;
        }
        for(int i = 0;i<t.length();i++){
            t_count[t.charAt(i)-'a']++;
        }
        if(Arrays.equals(t_count,s_count)) return true;
        else return false;
    }
    public static void main(String[] args){
        String s = "anagram",t = "nagaram";
        System.out.println(isAnagram(s,t));
    }*/
}