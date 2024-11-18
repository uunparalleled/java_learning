package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Array {

    public static void main(String[] args) {
        int[][] nums = {{2,3},{4,5},{6,7},{8,9},{1,10}};
        System.out.println(merge(nums));

    }

    /**
     * leetcode 56. 合并区间 done
     * 先对数组进行排序，从而大幅简化操作    Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
     * 可以用列表：   List<int[]> merged = new ArrayList<int[]>();
     * 列表转数组：   return merged.toArray(new int[merged.size()][]);
     * 也可以在数组上操作，序号从0开始，所以输入长度 index+1
     * 大数组转小数组：return Arrays.copyOf(merge, index+1);
     * @param intervals
     * @return
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
     * @param nums
     * @return
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
