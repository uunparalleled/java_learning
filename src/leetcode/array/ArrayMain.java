package leetcode.array;

import java.util.*;

public class ArrayMain {

    public static void main(String[] args) {
//        int[][] nums = {{2,3},{4,5},{6,7},{8,9},{1,10}};
        int[] nums = {3,3,3,1,2,1,1,2,3,3,4};
//        int[] res = searchRange(nums,3);
//        System.out.println(Arrays.toString(res));

        System.out.println(totalFruit(nums));
    }


    // 长度最小的子数组     滑动窗口

    /**
     * leetcode 904. 水果成篮   Map记录水果数量   最大滑动窗口
     */
    public static int totalFruit(int[] fruits) {
        int n = fruits.length;
        Map<Integer, Integer> cnt = new HashMap<>();
        int l = 0, r = 0, ans = 0;

        // 最大滑动窗口
        while (r < n) {
            cnt.put(fruits[r], cnt.getOrDefault(fruits[r],0) + 1);
            // 不满足条件
            while (cnt.size() > 2) {
                cnt.put(fruits[l], cnt.get(fruits[l]) - 1);
                if (cnt.get(fruits[l]) == 0) {
                    cnt.remove(fruits[l]);
                }
                // 右移左边界
                l++;
            }
            // 更新结果   在循环外！！！
            ans = Math.max(ans,r-l+1);
            // 右移右边界
            r++;
        }
        return ans;
    }

    /**
     * leetcode 209. 长度最小的子数组   前缀和/滑动窗口  最小滑动窗口
     */
    public static int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int ans = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;

        // 最小滑动窗口
        while (end < n) {
            sum += nums[end];
            // 满足条件
            while (sum >= s) {
                // 更新结果
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                // 右移左边界，缩小窗口
                start++;
            }
            // 右移右边界
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static int maxSubArraySum(int[] nums, int mid) {
        int sum = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i < mid) {
                sum += nums[i];
                max = sum;
            } else {
                sum += nums[i] - nums[i-mid];
                max = Math.max(sum, max);
            }
        }
        return max;
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
}
