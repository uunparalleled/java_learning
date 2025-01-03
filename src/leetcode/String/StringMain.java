package leetcode.String;

public class StringMain {

    public static void main(String[] args) {
        String s = "hello my friend";

        char[] ss = s.toCharArray();
        reverseString(ss);
        System.out.println(reverseWords(s));

    }

    /**
     * leetcode 151. 反转字符串中的单词
     */
    public static String reverseWords(String s) {
        StringBuilder res = new StringBuilder();
        String[] ss = s.split(" ");
        for (int i = ss.length-1; i >= 0; i--) {
            if (ss[i].isEmpty()) continue;
            res.append(ss[i]).append(" ");
        }
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }


    /**
     * leetcode 541. 反转字符串 II
     */
    public static String reverseStr(String s, int k) {
        if (s.length() <= 1 || k == 1) return s;

        char[] ss = s.toCharArray();
        int i = 0, n = s.length();
        while (i < n) {
            if (i + k > n) k = n - i;
            // 反转 [i, i + k - 1]
            for (int j = i; j < i + k/2; j++) {
                char tmp = ss[j];
                ss[j] = ss[2*i + k - j - 1];
                ss[2*i + k - j - 1] = tmp;
            }
            i += 2*k;
        }

        return new String(ss);
    }

    /**
     * leetcode 344. 反转字符串
     */
    public static void reverseString(char[] s) {
        for (int i = 0; i < s.length / 2; i++) {
            char temp = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = temp;
        }
    }
}
