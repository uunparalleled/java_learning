
import java.util.*;
class Pair {
    int value;
    int relatedValue;

    public Pair(int value, int relatedValue) {
        this.value = value;
        this.relatedValue = relatedValue;
    }
}
public class tencent {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int h = in.nextInt();
        int a = in.nextInt();
        int[] hp = new int[n];
        int[] at = new int[n];
        for (int i = 0; i < n; i++) {
            hp[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            at[i] = in.nextInt();
        }
        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair(hp[i], at[i]);
        }
        // 对对象数组进行排序，根据原始数组的值进行排序
        Arrays.sort(pairs, Comparator.comparing((Pair pair) -> pair.value)
                .thenComparing(pair -> pair.relatedValue));
        // 遍历 TreeMap，会按键的大小顺序输出
        int x = 0;
        for (Pair pair : pairs) {
            if (pair.value < h && pair.relatedValue < a) at[x] = pair.relatedValue;
            x++;
        }

        int[] dp = new int[x];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < x; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (at[i] > at[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }
        System.out.println(maxans);
    }
}
        /*        Scanner in = new Scanner(System.in);
                int x = in.nextInt(); // 数组大小
                int y = in.nextInt(); // 数组大小
                int z = (int) Math.ceil(Math.sqrt(x*x + y*y));
                if (z == 0) System.out.println(10);
                else if (z >= 11) System.out.println(0);
                    else System.out.println(11 - z);*/
/*
3 4 4
3 5 7
1 2 4
1 2 3 3

 */