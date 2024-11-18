import java.util.LinkedList;
import java.util.Queue;

public class QueueExample {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();

        // 向队列中添加元素
        queue.offer("Item1");
        queue.offer("Item2");
        queue.offer("Item3");

        // 从队列中取出并移除元素
        String item = queue.poll();
        System.out.println("Removed item: " + item); // 输出 "Removed item: Item1"

        item = queue.poll();
        System.out.println("Removed item: " + item); // 输出 "Removed item: Item2"

        item = queue.poll();
        System.out.println("Removed item: " + item); // 输出 "Removed item: Item3"

        // 队列为空时继续尝试移除元素
        item = queue.poll();
        System.out.println("Removed item: " + item); // 输出 "Removed item: null"
    }
}