package leetcode.stack;

import java.util.*;

public class StackMain {


    public static void main(String[] args) {
        String s = "100[leetcode]";
        int[] nums = {2,1,5,6,2,3};

        System.out.println(largestRectangleArea(nums));
//        System.out.println(Arrays.toString(maxSlidingWindow(nums, 3)));
    }

    /**
     * leetcode 84. 柱状图中最大的矩形
     */
    public static int largestRectangleArea(int[] heights) {
        int ans = 0;
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = -1;
        right[n - 1] = n;

        for (int i = 1; i < n; i++) {
            int p = i - 1;
            while (p >= 0 && heights[p] >= heights[i]) {
                p = left[p];
            }
            left[i] = p;
        }

        for (int i = n - 2; i >= 0; i--) {
            int p = i + 1;
            while (p < n && heights[p] >= heights[i]) {
                p = right[p];
            }
            right[i] = p;
        }

        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, heights[i] * (right[i] - left[i] - 1));
        }

        return ans;

    }

    /**
     * leetcode 739. 每日温度
     */
    public static int[] dailyTemperatures(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                int index = stack.pop();
                res[index] = i - index;
            }
            if (i == nums.length-1) break;
            if (nums[i+1] <= nums[i]) {
                stack.push(i);
            } else {
                res[i] = 1;
            }
        }
        return res;
    }

    /**
     * leetcode 394. 字符串解码
     */
    public static String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        char[] ss = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> value = new Stack<>();

        while (i < ss.length) {
            char c = ss[i];
            if (c >= '0' && c <= '9') {
                int begin = i;
                while (i < ss.length && ss[i+1] >= '0' && ss[i+1] <= '9') {
                    i++;
                }
                stack.push(i);
                value.push(Integer.parseInt(s.substring(begin, i + 1)));
                i++;
            } else if (c == ']'){
                int index = stack.pop();
                int num = value.pop();
                StringBuilder tmp = new StringBuilder();
                for (int j = 0; j < num; j++) {
                    tmp.append(s, index + 2, i);
                }
                StringBuilder res = new StringBuilder();
                res.append(s,0,index - (int) Math.log10(num)).append(tmp).append(s,i+1,s.length());
                return decodeString(res.toString());
            } else if (stack.isEmpty()) {
                sb.append(c);
            }
            i++;
        }
        return sb.toString();
    }

    /**
     * leetcode 150. 逆波兰表达式求值
     */
    public static int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for (String token : tokens) {
            if (!token.equals("+") && !token.equals("-") && !token.equals("*") && !token.equals("/")) {
                stack.push(token);
                continue;
            }

            int num1 = Integer.parseInt(stack.pop());
            int num2 = Integer.parseInt(stack.pop());
            switch (token) {
                case "+" -> stack.push(String.valueOf(num2 + num1));
                case "-" -> stack.push(String.valueOf(num2 - num1));
                case "*" -> stack.push(String.valueOf(num2 * num1));
                case "/" -> stack.push(String.valueOf(num2 / num1));
            }
        }
        return Integer.parseInt(stack.pop());
    }

    /**
     * leetcode 1047. 删除字符串中的所有相邻重复项
     */
    public static String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (stack.isEmpty() || c != stack.peek()) {
                stack.push(c);
            } else {
                stack.pop();
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.insert(0, stack.pop());
        }
        return res.toString();
    }

    /**
     * leetcode 20. 有效的括号
     */
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') return false;
            }
            if (c == ']') {
                if (stack.isEmpty() || stack.pop() != '[') return false;
            }
            if (c == '}') {
                if (stack.isEmpty() || stack.pop() != '{') return false;
            }
        }
        return stack.isEmpty();
    }
}
