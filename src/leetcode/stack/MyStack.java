package leetcode.stack;

import java.util.LinkedList;
import java.util.Queue;

class MyStack {
    Queue<Integer> q;
    Queue<Integer> tmp;

    public MyStack() {
        q = new LinkedList<>();
        tmp = new LinkedList<>();
    }

    public void push(int x) {
        q.add(x);
    }

    public int pop() {
        Integer last = null;
        while (!q.isEmpty()) {
            last = q.poll();
            if (q.isEmpty()) break;
            tmp.add(last);
        }
        while (!tmp.isEmpty()) {
            q.add(tmp.poll());
        }
        return last;
    }

    public int top() {
        Integer last = null;
        while (!q.isEmpty()) {
            last = q.poll();
            tmp.add(last);
        }
        while (!tmp.isEmpty()) {
            q.add(tmp.poll());
        }
        return last;
    }

    public boolean empty() {
        return q.isEmpty();
    }
}