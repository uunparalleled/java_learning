import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class pp {
    int id, x, v;
}

// 重写compare
class cmp1 implements Comparator<pp> {
    public int compare(pp a, pp b) {
        return a.id - b.id;
    }
}

class cmp2 implements Comparator<pp> {
    public int compare(pp a, pp b) {
        return a.v - b.v;
    }
}

class Compare {
    public static int n;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        pp[] p = new pp[n + 1];
        for (int i = 1; i <= n; i++)
            p[i] = new pp();
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            p[i].id = i;
            a[i] = scanner.nextInt();
            p[i].v = scanner.nextInt();
        }
        Arrays.sort(a, 1, n + 1);//坐标从小到大排序
        Arrays.sort(p, 1, n + 1, new cmp2());//速度从小到大排序
        for (int i = 1; i <= n; i++) {//速度匹配对应坐标
            p[i].x = a[i];
        }
        Arrays.sort(p, 1, n + 1, new cmp1());//编号排序
        for (int i = 1; i <= n; i++)
            System.out.println(p[i].x + " " + p[i].v);
    }
}