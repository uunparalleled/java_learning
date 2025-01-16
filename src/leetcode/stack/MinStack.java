package leetcode.stack;

import java.util.Stack;

class MinStack {
    private Stack<Integer> stack;  // 主栈，用来存储所有的元素
    private Stack<Integer> minStack;  // 辅助栈，用来存储当前栈的最小元素

    /** 初始化栈 */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    /** 将元素 x 推入栈中 */
    public void push(int x) {
        stack.push(x);  // 把元素压入主栈
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);  // 将当前最小值压入辅助栈
        }
    }

    /** 删除栈顶元素 */
    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        int poppedElement = stack.pop();  // 从主栈弹出元素
        if (poppedElement == minStack.peek()) {
            minStack.pop();  // 如果弹出的元素是最小值，则从辅助栈弹出
        }
    }

    /** 获取栈顶元素 */
    public int top() {
        return stack.peek();
    }

    /** 获取栈中的最小元素 */
    public int getMin() {
        return minStack.peek();
    }
}
