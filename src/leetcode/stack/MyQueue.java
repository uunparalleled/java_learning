package leetcode.stack;

import java.util.Stack;

class MyQueue {

    Stack<Integer> stack;
    Stack<Integer> tmp;

    public MyQueue() {
        stack = new Stack<>();
        tmp = new Stack<>();
    }

    public void push(int x) {
        while (!stack.empty()) {
            tmp.push(stack.pop());
        }
        tmp.push(x);

        while (!tmp.empty()) {
            stack.push(tmp.pop());
        }
    }

    public int pop() {
        return stack.pop();
    }

    public int peek() {
        return stack.peek();
    }

    public boolean empty() {
        return stack.empty();
    }
}

