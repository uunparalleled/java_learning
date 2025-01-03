package leetcode.stack;

import java.util.Stack;

public class StackMain {


    public static void main(String[] args) {
        String s = "()[]{}";


        System.out.println(isValid(s));
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
