package leetcode.array;

import leetcode.data.inputData;

import java.util.*;

public class ArrayMain {
    public static void main(String[] args) {

//        int[][] nums = inputData.inputArrayInt("[1,2,3,4],[12,13,14,5],[11,16,15,6],[10,9,8,7]");
//        int[] nums = {100000,2000};
        int[] nums = {4,6};
//        int[] res = searchRange(nums,3);
        String s = "AAAAAAABBCCCC";
        String t = "AAAABB";
//        System.out.println(Arrays.toString(res));

//        int a = (int) ((10e10 + 3) / 10e9);
//        System.out.println(10e9+7 - 1000000000);
//        System.out.println(Arrays.toString(getFinalState(nums, 2, 1000000)));
        quickSort(nums,0,nums.length-1);
        System.out.println(maxConsecutive(2,9,nums));
//        System.out.println(searchRotatedSortedArray(nums,0));
    }

    // 排序

    /**
     * leetcode 2274. 不含特殊楼层的最大连续楼层数
     */
    public static int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int l = bottom;
        int len = 0;
        for (int i = 0; i < special.length; i++) {
            len = Math.max(special[i] - l,len);
            l = special[i] + 1;

        }
        len = Math.max(top - l + 1,len);
        return len;
    }

    /**
     * leetcode 2545. 根据第 K 场考试的分数排序
     */
    public static int[][] sortTheStudents(int[][] score, int k) {
        Arrays.sort(score, (a, b) -> Integer.compare(b[k],a[k]));
        return score;
    }

    public static void quickSort(int[] q, int l, int r) {
        // 递归终止情况
        if (l >= r) return;
        // 第一步：分成子问题
        int i = l - 1, j = r + 1, x = q[l + r >> 1];
        // 左右指针分别进行移动，然后遇到异常的数字后交换
        while (i < j) {
            do i++; while (q[i] < x);
            do j--; while (q[j] > x);
            if (i < j) {
                int tmp = q[i];
                q[i] = q[j];
                q[j] = tmp;
            }
        }
        // 第二步：递归处理子问题
        quickSort(q, l, j);
        quickSort(q, j + 1, r);
        // 第三步：子问题合并，快排不需要，但归并排序的核心在这一步骤
    }


    /**
     * 冒泡排序
     */
    public static int[] bubbleSort(int[] nums) {
        return null;
    }


    // 开发商购买土地
    /**
     * kamaCoder 44. 开发商购买土地
     */
    /*import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] nums = new int[n][m];
        int[][] nSum = new int[n][m];
        int[][] mSum = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                nums[i][j] = sc.nextInt();
                nSum[i][j] += nums[i][j] + (i == 0 ? 0 : nSum[i-1][j]);
                mSum[i][j] += nums[i][j] + (j == 0 ? 0 : mSum[i][j-1]);
            }
        }
        int[] nDisSum = new int[n];
        int[] mDisSum = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                nSum[i][j] = 2*nSum[i][j] - nSum[n-1][j];
                nDisSum[i] += nSum[i][j];
                mSum[i][j] = 2*mSum[i][j] - mSum[i][m-1];
                mDisSum[j] += mSum[i][j];
            }
        }
        int res = Math.abs(nDisSum[0]);
        for (int dis : nDisSum) {
            if (Math.abs(dis) < Math.abs(res)) res = Math.abs(dis);
        }
        for (int dis : mDisSum) {
            if (Math.abs(dis) < Math.abs(res)) res = Math.abs(dis);
        }
        System.out.println(res);
    }
}*/

    // 区间和
    /**
     * kamaCoder 58. 区间和        前缀和 在涉及计算区间和的问题时非常有用
     */
    /*
      import java.util.*;
      public class MainLe {
          public static void main(String[] args) {
              Scanner sc = new Scanner(System.in);
              int n = sc.nextInt();
              int[] nums = new int[n];
              int[] sum = new int[n];
              for (int i = 0; i < n; i++) {
                  nums[i] = sc.nextInt();
                  sum[i] = nums[i] + (i == 0 ? 0 : sum[i-1]);
              }
              while (sc.hasNextInt()) {
                  int a = sc.nextInt();
                  int b = sc.nextInt();
                  System.out.println(sum[b] - (a == 0 ? 0 : sum[a-1]));
              }
          }
      }
     */

    // 矩阵       考虑清楚边界情况、过程逻辑

    /**
     * leetcode 48. 旋转图像
     */
    public static void rotate(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n-j-1][i];
                matrix[n-j-1][i] = matrix[n-i-1][n-j-1];
                matrix[n-i-1][n-j-1] = matrix[j][n-i-1];
                matrix[j][n-i-1] = tmp;
            }
        }
    }

    /**
     * leetcode 73. 矩阵置零
     */
    public static void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * leetcode LCR 146. 螺旋遍历二维数组       同 54题
     */
    public static int[] spiralArray(int[][] array) {
        if (array.length == 0) return new int[0];
        int a = 0, b = 0, m = array.length, n = array[0].length;
        double angle = 0.0;
        int[] res = new int[m*n];
        boolean[][] flag = new boolean[m][n];

        for (int i = 0; i < m * n; i++) {
            res[i] = array[a][b];
            flag[a][b] = true;
            a += (int) Math.sin(angle);
            b += (int) Math.cos(angle);

            if ((a == 0 && b == n) || (a == m && b == n-1) || (a == m-1 && b == -1) || flag[a][b]) {
                a -= (int)Math.sin(angle);
                b -= (int)Math.cos(angle);
                angle += Math.PI/2;
                a += (int)Math.sin(angle);
                b += (int)Math.cos(angle);
            }
        }
        return res;
    }

    /**
     * leetcode 54. 螺旋矩阵
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        double angle = 0;
        int a = 0, b = 0;
        int m = matrix.length, n = matrix[0].length;
        boolean[][] flag = new boolean[m][n];
        for (int i = 1; i <= m*n; i++) {
            res.add(matrix[a][b]);
            flag[a][b] = true;
            a += (int)Math.sin(angle);
            b += (int)Math.cos(angle);

            if ((a == 0 && b == n) || (a == m && b == n-1) || (a == m-1 && b == -1)
                    || (flag[a][b])
            ) {
                a -= (int)Math.sin(angle);
                b -= (int)Math.cos(angle);
                angle += Math.PI/2;
                a += (int)Math.sin(angle);
                b += (int)Math.cos(angle);
            }
        }
        return res;
    }

    /**
     * leetcode 59. 螺旋矩阵 II
     */
    public static int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        double angle = 0;
        int a = 0, b = 0;
        res[a][b] = 1;
        for (int i = 2; i <= n*n; i++) {
            a += (int) Math.sin(angle);
            b += (int)Math.cos(angle);
            res[a][b] = i;

            if (a == 0 && b == n-1) angle += Math.PI / 2;
            else if (a == n-1 && b == n-1) angle += Math.PI / 2;
            else if (a == n-1 && b == 0) angle += Math.PI / 2;
            else if (res[a+(int)Math.sin(angle)][b+(int)Math.cos(angle)] != 0) {
                angle += Math.PI / 2;
            }
        }
        return res;
    }

    // 长度最小的子数组     滑动窗口

    /**
     * leetcode 438. 找到字符串中所有字母异位词
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int left = 0, right = 0, count = 0, other = 0;
        char[] ss = s.toCharArray();
        char[] pp = p.toCharArray();
        int[] hash = new int[128];
        int[] tmp = new int[128];
        for (char ch : pp) hash[ch]++;
        while (right < s.length()) {
            char in = ss[right++];
            tmp[in]++;
            if (tmp[in] > hash[in]) other++;
            else count++;
            while (other != 0) {
                char out = ss[left++];
                tmp[out]--;
                if (tmp[out] >= hash[out]) other--;
                else count--;
            }

            if (count == p.length()) res.add(left);
        }
        return res;
    }

    /**
     * leetcode 3. 无重复字符的最长子串
     */
    public static int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0, n = s.length(), count = 0, l = -1, r = -1;
        int[] tmp = new int[128];
        char[] ss = s.toCharArray();

        while (right < n) {
            char in = ss[right++];
            if (tmp[in]++ == 1) count++;
            while (count != 0) {
                char out = ss[left++];
                if (--tmp[out] == 1) count--;
            }
            if (r - l < right - left) { r = right; l = left; }

        }
        return l == -1 ? 0 : (r-l);
    }

    /**
     * !!! leetcode 76. 最小覆盖子串   int[128]代替 Map     count计数来判断是否符合条件
     */
    public static String minWindow(String s, String t) {
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();
        int[] hash = new int[128];
        int[] tmp = new int[128];
        int left = 0, right = 0, n1 = s.length(), n2 = t.length(), count = 0;
        int l = -1, r = n1;
        for (char ch : tt) hash[ch]++;

        while (right < n1) {
            char in = ss[right++];
            tmp[in]++;
            if (tmp[in] <= hash[in]) count++;
            while (count == n2) {
                // update
                if (right-left < r-l) {
                    r = right;
                    l = left;
                }
                char out = ss[left++];
                tmp[out]--;
                if (tmp[out] < hash[out]) count--;
            }
        }
        return l == -1 ? "" : s.substring(l,r);
    }

    /**
     * !!! leetcode 904. 水果成篮   Map记录水果数量   最大滑动窗口
     */
    public static int totalFruit(int[] fruits) {
        int start = 0, end = 0;
        int len = 0;
        Map<Integer,Integer> myFruits = new HashMap<>();
        while (end < fruits.length) {
            myFruits.put(fruits[end],myFruits.getOrDefault(fruits[end],0)+1);
            while (myFruits.size() > 2) {
                if (myFruits.get(fruits[start]) == 1) {
                    myFruits.remove(fruits[start]);
                } else {
                    myFruits.put(fruits[start],myFruits.get(fruits[start])-1);
                }
                start++;
            }
            len = Math.max(end - start + 1,len);
            end++;
        }
        return len;
    }

    /**
     * !!! leetcode 209. 长度最小的子数组   前缀和/滑动窗口  最小滑动窗口
     */

    public static int minSubArrayLen(int s, int[] nums) {
        int start = 0, end = 0;
        int sum = 0;
        int len = nums.length+1;
        while (end < nums.length) {
            sum += nums[end];
            while (sum >= s) {
                len = Math.min(end - start + 1,len);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return len == nums.length+1 ? 0 : len;
    }

    // 移除元素         双指针
    /**
     * leetcode 977. 有序数组的平方
     */
    public static int[] sortedSquares(int[] nums) {
        int l = 0;
        int r = nums.length-1;
        int[] res = new int[nums.length];
        for (int i = nums.length-1; i >= 0; i--) {
            if (nums[l] + nums[r] > 0) {
                res[i] = nums[r] * nums[r];
                r--;
            } else {
                res[i] = nums[l] * nums[l];
                l++;
            }
        }
        return res;
    }

    /**
     * leetcode 844. 比较含退格的字符串
     */
    public static boolean backspaceCompare(String s, String t) {
        int i = s.length()-1,j = t.length()-1;
        int skipS = 0, skipT = 0;
        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else break;
            }
            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else break;
            }
            if (i < 0 && j < 0) return true;
            if (i < 0 || j < 0) {
                return false;
            } else if (s.charAt(i) != t.charAt(j)){
                return false;
            } else {
                i--;
                j--;
            }
        }
        return true;
    }

    /**
     * leetcode 283. 移动零
     */
    public static void moveZeroes(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                index++;
            }
        }
        if (index == 0) return;
        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    /**
     * leetcode 26. 删除有序数组中的重复项
     */
    public static int removeDuplicates(int[] nums) {
        int index = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1]) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }

    /**
     * leetcode 27. 移除元素            快慢指针
     */
    public static int removeElement(int[] nums, int val) {
        int c = 0;
        for (int i = 0; i <nums.length; i++) {
            if (nums[i] != val) {
                nums[c] = nums[i];
                c++;
            }
        }
        return c;
    }


    // 二分查找 条件：有序、不重复
    /**
     * leetcode 240. 搜索二维矩阵 II
     */
    public static boolean searchMatrix2(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            if (matrix[i][n-1] >= target && matrix[i][0] <= target) {
                int l = 0, r = n-1;
                while (l <= r) {
                    int mid = (l + r) / 2;
                    if (matrix[i][mid] < target) {
                        l = mid + 1;
                    } else if (matrix[i][mid] > target) {
                        r = mid - 1;
                    } else return true;

                }
            }
        }
        return false;
    }

    /**
     * leetcode 4. 寻找两个正序数组的中位数     第k小数
     */
    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        return 0.0;
    }

    /**
     * leetcode 4. 寻找两个正序数组的中位数     二分查找
     */
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        return 0.0;
    }

    /**
     * leetcode 153. 寻找旋转排序数组中的最小值
     */
    public int findMinInRotatedArray(int[] nums) {
        int n = nums.length;
        if (nums[n-1] >= nums[0]) return nums[0];

        int l = 0, r = n-1;
        while (l <= r) {
            int mid = (l+r)/2;
            if (nums[mid] > nums[n-1]) {
                l = mid + 1;
            } else {
                r = mid;
            }

            if (nums[r] >= nums[l]) return nums[l];
        }
        return nums[0];
    }

    /**
     * leetcode 33. 搜索旋转排序数组
     *  定义target在左闭右闭的区间里
     *  1、while (l <= r) 因为 == 有意义  target还在[l,r]里
     *  2、if (nums[mid] > target) r = mid - 1;    因为当前 nums[mid] 一定不是 target
     *  3、同理 if (nums[mid] < target) l = mid + 1;    因为当前 nums[mid] 一定不是 target
     *
     *  定义target在左闭右开的区间里
     *  1、while (l < r) 因为 == 没有意义  target不在[l,r)里
     *  2、if (nums[mid] > target) r = mid;    因为当前 nums[mid]不等于target，去左区间继续寻找，而寻找区间是左闭右开区间，
     *  所以right更新为middle，即：下一个查询区间不会去比较nums[mid]
     *  3、同理 if (nums[mid] < target) l = mid + 1;   因为左边是闭区间，所以 l要 +
     */
    public static int searchRotatedSortedArray(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n-1;
        while (l <= r) {
            int mid = (l+r)/2;
            // 判断 mid 是否在旋转点的左侧
            if (nums[mid] >= nums[0]) {
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            if (nums[mid] == target) return mid;
        }
        return -1;
    }

    /**
     * leetcode 74. 搜索二维矩阵
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        int a = matrix.length, b = matrix[0].length;
        int l = 0, r = a*b;

        while (l < r) {
            int mid = (l+r)/2;
            if (matrix[mid/b][mid%b] < target) {
                l = mid + 1;
            } else if (matrix[mid/b][mid%b] > target){
                r = mid;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * leetcode 367. 有效的完全平方数
     */
    public boolean isPerfectSquare(int num) {
        int l = 0;
        int r = num;

        while (l <= r) {
            int mid = (l+r)/2;
            if ((long) mid * mid < num) {
                l = mid+1;
            } else if ((long) mid * mid > num) {
                r = mid-1;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * leetcode 69. x 的平方根          需要加上(long)防止超出int范围
     */
    public static int mySqrt(int x) {
        if (x <= 1) return x;
        int l = 0;
        int r = x;

        while (l <= r) {
            int mid = (l+r)/2;
            if ((long) mid * mid < x) {
                l = mid+1;
            } else if ((long) mid * mid > x) {
                r = mid-1;
            } else {
                return mid;
            }
        }
        return r;
    }

    /**
     * leetcode 34. 在排序数组中查找元素的第一个和最后一个位置
     */
    public static int[] searchRange(int[] nums, int target) {
        if (target < nums[0] || target > nums[nums.length-1]) return new int[]{-1,-1};

        int l = 0;
        int r = nums.length;
        int mid;
        boolean find = false;
        while (l < r) {
            mid = (l+r)/2;
            if (nums[mid] < target) {
                l = mid+1;
            } else if (nums[mid] > target) {
                r = mid;
            } else {
                l = mid;
                r = mid;
                find = true;
                break;
            }
        }
        if (!find) return new int[]{-1,-1};
        while (l >= 0 && nums[l] == target) {
            l--;
        }
        while (r < nums.length && nums[r] == target) {
            r++;
        }
        return new int[]{l+1,r-1};
    }

    /**
     * leetcode 35. 搜索插入位置
     */
    public static int searchInsert(int[] nums, int target) {
        if (target < nums[0]) return 0;
        if (target > nums[nums.length-1]) return nums.length-1;

        int l = 0;
        int r = nums.length;
        int mid = 0;
        while (l < r) {
            mid = (l+r)/2;
            if (nums[mid] < target) {
                l = mid+1;
            } else if (target < nums[mid]) {
                r = mid;
            } else {
                return mid;
            }
        }
        return nums[mid] < target ? mid+1 : mid;
    }

    /**
     * leetcode 704. 二分查找 左闭右闭
     */
    public static int searchBinary1(int[] nums, int target) {
        if (target < nums[0] || target > nums[nums.length-1]) return -1;

        int l = 0;
        int r = nums.length-1;
        while (l <= r) {
            int mid = (l+r)/2;
            if (nums[mid] < target) {
                l = mid + 1;
            } else if (target < nums[mid]) {
                r = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * leetcode 704. 二分查找 左闭右开
     */
    public static int searchBinary2(int[] nums, int target) {
        if (target < nums[0] || target > nums[nums.length-1]) return -1;

        int l = 0;
        int r = nums.length;
        while (l < r) {
            int mid = (l+r)/2;
            if (nums[mid] > target) {
                r = mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    // other

    /**
     * leetcode 3285. 找到稳定山的下标      水题
     */
    public static List<Integer> stableMountains(int[] height, int threshold) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < height.length; i++) {
            if (height[i-1] > threshold) res.add(i);
        }
        return res;
    }


    //
    /**
     * leetcode 56. 合并区间 done
     * 先对数组进行排序，从而大幅简化操作    Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
     * 可以用列表：   List<int[]> merged = new ArrayList<int[]>();
     * 列表转数组：   return merged.toArray(new int[merged.size()][]);
     * 也可以在数组上操作，序号从0开始，所以输入长度 index+1
     * 大数组转小数组：return Arrays.copyOf(merge, index+1);
     */
    public static int[][] merge(int[][] intervals) {
        //获取数组长度
        int length = intervals.length;
        //初始化一个新的数组存放合并后的值，但是长度可能存在多余
        int[][] merge = new int[length][2];
        //对数组的左端点进行排序
        Arrays.sort(intervals, Comparator.comparingInt(v -> v[0]));
        //将第一个值直接放入数组初始化
        merge[0] = intervals[0];
        //新数组中最后一个元素下标的位置
        int index = 0;
        //循环遍历
        for(int i = 1; i < length; i++){
            //因为数组中的左端点已经按照从大到小的顺序排列
            //那么当新数组中最后一个右端点如果小于当前元素的左端点，那么一定不重合，
            //直接加入新的数组集合，否则就是一定会有重合需要合并的
            if(intervals[i][0] > merge[index][1]){
                merge[++index] = intervals[i];
            }else{
                //这里要获取两个右端点的最大值作为合并后的右端点，
                //因为左端点排序之后你不知道右端点到底是大是小的。
                merge[index][1] = Math.max(intervals[i][1], merge[index][1]);
            }
        }
        //这里copy新的数组是因为原来的初始化的merge数组是最大长度length，这样如果
        //存在合并的数组之后，那么merge就存在了[0,0]这种给的元素，这样是不符合要求的
        //所以重新复制一个新的数组，长度是index+1是因为index是实际元素的下标，
        //如果变成长度要+1
        return Arrays.copyOf(merge, index+1);
    }

    /**
     * leetcode 53. 最大子数组和 done
     * 动态规划 ！！！
     * 之前思维局限在 第一个元素开始 挨个判断要不要取
     * 转变思维： 用 f(i) 代表以第 i个数结尾的「连续子数组的最大和」
     * 第 i个数取不取不会影响遍历，我们只看数的大小
     */
    public static int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            // 能接上就加，不能就直接舍，从新的开始
            // 依次求和，一旦小于0就舍去
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

    /**
     * !!! leetcode 3266. K 次乘运算后的最终数组 II   最小堆     快速幂
     */
    private static final int MOD = 1_000_000_007;

    public int[] getFinalState(int[] nums, int k, int multiplier) {
        if (multiplier == 1) { // 数组不变
            return nums;
        }

        int n = nums.length;
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }

        // 每个数直接暴力操作到 >= mx
        long[] a = new long[n];
        int left = k;
        outer:
        for (int i = 0; i < n; i++) {
            long x = nums[i];
            while (x < mx) {
                x *= multiplier;
                if (--left < 0) {
                    break outer;
                }
            }
            a[i] = x;
        }

        if (left < 0) {
            // 暴力模拟
            PriorityQueue<long[]> pq = new PriorityQueue<>((p, q) -> p[0] != q[0] ? Long.compare(p[0], q[0]) : Long.compare(p[1], q[1]));
            for (int i = 0; i < n; i++) {
                pq.offer(new long[]{nums[i], i});
            }
            while (k-- > 0) {
                long[] p = pq.poll();
                p[0] *= multiplier;
                pq.offer(p);
            }
            while (!pq.isEmpty()) {
                long[] p = pq.poll();
                nums[(int) p[1]] = (int) (p[0] % MOD);
            }
            return nums;
        }

        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, i -> i);
        Arrays.sort(ids, (i, j) -> Long.compare(a[i], a[j]));

        // 剩余的操作可以直接用公式计算
        k = left;
        long pow1 = pow(multiplier, k / n);
        long pow2 = pow1 * multiplier % MOD;
        for (int i = 0; i < n; i++) {
            int j = ids[i];
            nums[j] = (int) (a[j] % MOD * (i < k % n ? pow2 : pow1) % MOD);
        }
        return nums;
    }

    /**
     * 快速幂
     */
    private long pow(long x, int n) {
        long res = 1;
        for (; n > 0; n /= 2) {
            if (n % 2 > 0) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
        }
        return res;
    }

}
