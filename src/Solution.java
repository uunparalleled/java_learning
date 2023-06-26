import java.util.Arrays;

public class Solution {

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