package leetcode;

import java.util.*;
import java.util.stream.IntStream;

/**
 *  mark标记  star重点  done完成
 */
public class MainLe {
    public static void main(String[] args) {
        int[] nums = {1,5,3,2,5,6,7};
//        char[][] array = {
//                {'1','0','1','1','1'},
//                {'1','0','1','0','1'},
//                {'1','1','1','0','1'}
//        };
//        firstMissingPositive(nums);
        System.out.println(minAnagramLength("bbb"));
    }

    /**
     * leetcode 3138. 同位字符串连接的最小长度
     */
    public static int minAnagramLength(String s) {
        int n = s.length();
        char[] ss = s.toCharArray();
        for (int i = 1; i <= n / 2; i++) {
            if (n % i != 0) continue;
            int end = i;
            int[] hash = new int[128];
            for (int j = 0; j < i; j++) {
                hash[ss[j]]++;
            }
            int count = 0;
            out:
            while (end < n) {
                count = 0;
                int[] tmp = new int[128];
                for (int j = end; j < end+i; j++) {
                    tmp[ss[j]]++;
                    if (tmp[ss[j]] <= hash[ss[j]]) count++;
                    else {
                        break out;
                    }
                }
                end = end + i;
            }
            if (end == n && count == (n % i -1)) return i;
        }
        return n;
    }

    /**
     * leetcode 41. 缺失的第一个正数 done
     * @param nums
     * @return
     */
    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] >= 1 && nums[i] <= n && nums[nums[i]-1] != nums[i]) {
                int tmp = nums[i];
                nums[i] = nums[nums[i]-1];
                nums[tmp-1] = tmp;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i+1) {
                return i+1;
            }
        }
        return nums.length+1;
        // O(N log(N))
//        Arrays.sort(nums);
//        int res = 1;
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] <= 0) continue;
//            if (i > 0 && nums[i] == nums[i-1]) continue;
//            if (res != nums[i]) return res;
//            res++;
//        }
//        return res;
    }

    // 左右乘积列表 res[i] = L[i] * R[i]
    /**
     * leetcode 238. 除自身以外数组的乘积 done
     * @param nums
     * @return
     */
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = nums[i-1] * res[i-1];
        }
        int R = 1;
        for (int i = n-1; i >= 0; i--) {
            res[i] = res[i] * R;
            R *= nums[i];
        }
        return res;
    }


    // Arrays.copyOf 返回一个新数组的地址 System.arraycopy 直接改变数组内的值
    /**
     * leetcode 189. 轮转数组 done
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int[] newArr = new int[n];
        for (int i = 0; i < n; i++) {
            if (i < k) {
                newArr[i] = nums[n-k+i];
            } else {
                newArr[i] = nums[i-k];
            }
        }
//        nums = Arrays.copyOf(newArr,n);

        System.arraycopy(newArr, 0, nums, 0, n);
    }

    // 双指针  排序！！！ 遍历+判断条件
    /**
     * leetcode 15. 三数之和 done
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
//        Set<List<Integer>> res = new HashSet<>();
//        List<Integer> list = new ArrayList<>(Arrays.stream(nums).boxed().toList());
//        Collections.sort(list);

        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int k = n-1;
            for (int j = i+1; j < n; j++) {
                if (j > i+1 && nums[j] == nums[j-1]) {
                    continue;
                }
                while (k > j && nums[i] + nums[j] + nums[k] > 0) {
                    k--;
                }
                if (k == j) break;
                if (nums[i] + nums[j] + nums[k] == 0) {
                    List<Integer> tmp = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k]));
                    res.add(tmp);
                }
            }
        }
        return res;

//        遍历+判断条件 超时！！
//        Set<List<Integer>> res = new HashSet<>();
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i+1; j < nums.length; j++) {
//                for (int k = j+1; k < nums.length; k++) {
//                    if (nums[i] + nums[j] + nums[k] == 0) {
//                        List<Integer> tmp = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k]));
//                        Collections.sort(tmp);
//                        res.add(tmp);
//                    }
//                }
//            }
//
//        }
//        return new ArrayList<>(res);
    }

    /**
     * leetcode 42. 接雨水 done
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        int res = 0;
        int maxIndex = IntStream.range(0,height.length)
                .reduce((i,j) -> height[i] > height[j] ? i : j)
                .orElse(-1);
        int[] leftArray = Arrays.copyOfRange(height, 0, maxIndex + 1); // 左侧部分（不含最大值）
        int[] rightArray = Arrays.copyOfRange(height, maxIndex, height.length); // 包含最大值的右侧部分
        for (int i = 0; i < rightArray.length / 2; i++) {
            int temp = rightArray[i];
            rightArray[i] = rightArray[rightArray.length - 1 - i];
            rightArray[rightArray.length - 1 - i] = temp;
        }
        res = getRes(leftArray, res);
        res = getRes(rightArray, res);

        return res;
    }

    private static int getRes(int[] height, int res) {
        for (int i = 0; i < height.length-1; i++) {
            int tmp = 0;
            for (int j = i+1; j < height.length ; j++) {
                if (height[j] < height[i]) {
                    tmp += height[i] - height[j];
                }
                if (height[j] >= height[i]) {
                    i = j-1;
                    res += tmp;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * leetcode 661. 图片平滑器 done
     * @param img
     * int[][] img = {{100,200,100},{200,50,200},{100,200,100}};
     * @return
     */
    public static int[][] imageSmoother(int[][] img) {
        int[][] res = new int[img.length][img[0].length];
        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[i].length; j++) {
                boolean up = i == 0;
                boolean down = i == img.length - 1;
                boolean left = j == 0;
                boolean right = j == img[0].length - 1;
                res[i][j] += img[i][j];
                int count = 1;
                if (!up) {
                    res[i][j] += img[i-1][j];
                    count++;
                }
                if (!down) {
                    res[i][j] += img[i+1][j];
                    count++;
                }
                if (!left) {
                    res[i][j] += img[i][j-1];
                    count++;
                }
                if (!right) {
                    res[i][j] += img[i][j+1];
                    count++;
                }
                if (!up && !left) {
                    res[i][j] += img[i-1][j-1];
                    count++;
                }
                if (!up && !right) {
                    res[i][j] += img[i-1][j+1];
                    count++;
                }
                if (!down && !left) {
                    res[i][j] += img[i+1][j-1];
                    count++;
                }
                if (!down && !right) {
                    res[i][j] += img[i+1][j+1];
                    count++;
                }
                res[i][j] = res[i][j] / count;
            }
        }
        return res;
    }

    /**
     * leetcode 2806. 取整购买后的账户余额 done
     * @param purchaseAmount
     * @return
     */
    public static int accountBalanceAfterPurchase(int purchaseAmount) {
        return 100 - (purchaseAmount / 10 + (purchaseAmount % 10 > 4 ? 1 : 0)) * 10;
    }
    /**
     * leetcode 419. 甲板上的战舰 done
     * 学习 stream
     String a = "X..X-...X-...X";
     char[][] board = Arrays.stream(a.split("-"))
     .map(String::toCharArray)
     .toArray(char[][]::new);
     System.out.println(countBattleships(board));
     * @param board
     * @return
     */
    public static int countBattleships(char[][] board) {
        int res = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'X') {
                    res++;
                    if (i > 0 && board[i-1][j] == 'X') {
                        res--;
                    }
                    if (j > 0 && board[i][j-1] == 'X') {
                        res--;
                    }
                }

            }
        }
        return res;
    }

    /**
     * leetcode 59. 螺旋矩阵 II done
     * @param n
     * @return
     */
    public static int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        res[0][0] = 1;
        int i = 2;
        int x = 0;
        int y = 0;
        boolean rightUp = true;
        boolean rightDown = true;
        int up = 0;
        int down = 0;
        int left = 0;
        int right = 0;
        while (i <= n * n) {
            if (rightUp && rightDown) {
                x++;
                if (x == n -  right - 1) {
                    rightUp = false;
                    up++;
                }
            }else if (!rightUp && rightDown) {
                y++;
                if (y == n -  right - 1) {
                    rightDown = false;
                    right++;
                }
            }else if (rightUp && !rightDown) {
                y--;
                if (y == up) {
                    rightDown = true;
                    left++;
                }
            }else if (!rightUp && !rightDown) {
                x--;
                if (x == left) {
                    rightUp = true;
                    down++;
                }
            }
            res[y][x] = i;
            i++;
        }
        return res;
    }

    /**
     * leetcode 94. 二叉树的中序遍历 done
     * 好好研究dfs 函数
     * 前序遍历：打印 - 左 - 右
     * 中序遍历：左 - 打印 - 右
     * 后序遍历：左 - 右 - 打印
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
//        List<Integer> res = new ArrayList<Integer>();
//        dfs(res,root);
//        return res;

        // 莫里斯遍历
        List<Integer> res = new ArrayList<Integer>();
        TreeNode pre = null;
        while(root!=null) {
            //如果左节点不为空，就将当前节点连带右子树全部挂到
            //左节点的最右子树下面
            if(root.left!=null) {
                pre = root.left;
                while(pre.right!=null) {
                    pre = pre.right;
                }
                pre.right = root;
                //将root指向root的left
                TreeNode tmp = root;
                root = root.left;
                tmp.left = null;
                //左子树为空，则打印这个节点，并向右边遍历
            } else {
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }
    void dfs(List<Integer> res, TreeNode root) {
        if(root==null) {
            return;
        }
        //按照 左-打印-右的方式遍历
        dfs(res,root.left);
        res.add(root.val);
        dfs(res,root.right);
    }

    /**
     * leetcode 894. 所有可能的真二叉树 done
     * for (TreeNode left : f[j]) {
     *    for (TreeNode right : f[i-j]) {
     * @param n
     * @return
     */
    public List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode>[] f = new ArrayList[11];
        Arrays.setAll(f, i -> new ArrayList<>());
        f[1].add(new TreeNode());

        for (int i = 2; i < f.length; i++) {
            for (int j = 1; j < i; j++) {
                for (TreeNode left : f[j]) {
                    for (TreeNode right : f[i-j]) {
                        f[i].add(new TreeNode(0,left,right));
                    }
                }
            }
        }

        return f[n % 2 > 0 ? (n + 1) / 2 : 0];
    }

    /**
     * leetcode 2809. 使数组和小于等于 x 的最少时间
     * 0-1背包 动态规划
     * @param nums1
     * @param nums2
     * @param x
     * @return
     */
    public static int minimumTime(List<Integer> nums1, List<Integer> nums2, int x) {
        int n = nums1.size(), s1 = 0, s2 = 0;
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            int a = nums1.get(i);
            int b = nums2.get(i);
            pairs[i][0] = a;
            pairs[i][1] = b;
            s1 += a;
            s2 += b;
        }
        // 如果已经选定k个索引，那么按照 先小后大 的顺序能使 和 最小
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);

        // dp[j][i]：对前 j 个元素进行 i 次操作可以减少的最大总值
        // dp[j][i]=max(dp[j−1][i] , dp[j−1][i−1] + nums2[j−1]×i + nums1[j−1])

        int[] f = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int a = pairs[i][0];
            int b = pairs[i][1];
            for (int j = i + 1; j > 0; j--) {
                f[j] = Math.max(f[j], f[j - 1] + a + b * j);
            }
        }

        for (int t = 0; t <= n; t++) {
            if (s1 + s2 * t - f[t] <= x) {
                return t;
            }
        }
        return -1;
    }
    /**
     * leetcode 2765. 最长交替子数组 done easy
     int[] nums = {2,3,4,3,4};
     System.out.println(alternatingSubarray(nums));
     */
    public static int alternatingSubarray(int[] nums) {
        int max = -1;
        for (int i = 0; i < nums.length - 1; i++) {
            int flag = 1;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == nums[j-1] + flag) {
                  flag = -flag;
                  max = Math.max(max,j-i+1);
                } else break;
            }
        }
        return max;
    }
    /**
     * leetcode 3. 无重复字符的最长子串 redo star
     * 滑动窗口
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    /**
     * leetcode 670. 最大交换 done
     * 不能一看到最大的就换，在最大值最左边找比他小的
     int num = 98368;
     System.out.println(maximumSwap(num));
     * @param num
     * @return
     */
    public static int maximumSwap(int num) {
        char[] s = Integer.toString(num).toCharArray();
        int maxIdx = s.length - 1;
        int p = -1, q = 0;
        for (int i = s.length - 2; i >= 0; i--) {
            if (s[i] > s[maxIdx]) { // s[i] 是目前最大数字
                maxIdx = i;
            } else if (s[i] < s[maxIdx]) { // s[i] 右边有比它大的
                p = i;
                q = maxIdx; // 更新 p 和 q
            }
        }
        if (p == -1) { // 这意味着 s 是降序的
            return num;
        }
        char temp = s[p];
        s[p] = s[q];
        s[q] = temp; // 交换 s[p] 和 s[q]
        return Integer.parseInt(new String(s));
    }

    /**
     * leedcode 410. 分割数组的最大值 done mark
     * 看到「最大化最小值」或者「最小化最大值」就要想到二分答案
     * 「元素和的最大值」越小，需要划分出的段数就越多，反之越少。有单调性的保证，就可以二分答案了。
     int[] nums = {10,5,13,4,8,4,5,11,14,9,16,10,20,8};
     int k = 8;
     System.out.println(splitArray(nums,k));
     * @param nums
     * @param k
     * @return
     */
    public static int splitArray(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        int left = sum / k;
        int right = sum;

        while (left + 1 < right) {
            int mid = (left + right)/2;
            if (check(nums,k,mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }
    // star 给定最大值，求可以分多少段
    private static boolean check(int[] nums, int k, int mid) {
        int count = 0;
        for (int num : nums) {
            if (num > mid) return false;
            if (count + num > mid) {
                count = num;
                if (--k == 0) return false;
            } else {
                count += num;
            }
        }
        return true;
    }

    /**
     * leedcode 318. 最大单词长度乘积 done
     * 有限字符类型的字符串可以通过位掩码判断是否有公共字符，字符串均为小写字母，位掩码为 masks[i] |= 1 << (word.charAt(j) - 'a');
     * 通过 masks[i] & masks[j]==0 判断是否有公共字符
     *
     String[] words = {"abcw","baz","foo","bar","xtfn","abcdef"};
     System.out.println(maxProduct(words));
     * @param words
     * @return
     */
    public static int maxProduct(String[] words) {

        int len = words.length;
        int[] masks = new int[len];
        for(int i=0;i<len;i++){
            String word=words[i];
            int wordlen=word.length();
            for(int j=0;j<wordlen;j++){
                masks[i] |=1 << (word.charAt(j)-97);
            }
        }

        int max=0;
        for(int i=0;i<len;i++){
            for(int j=i+1;j<len;j++){
                if((masks[i]&masks[j])==0){
                    max=Math.max(max,words[i].length()*words[j].length());
                }
            }
        }
        return max;
    }

    /**
     * leetcode 76. 最小覆盖子串 done
     String s = "C";
     String t = "1";
     System.out.println(minWindow(s,t));
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        // 定义区间左端点，右端点，最小长度初始值为s长度+1
        int left = 0, right = 0, min = s.length() + 1;
        String result = "";
        // 定义哈希表res，存储t中字符及出现次数
        // res初始化
        Map<Character, Integer> res = new HashMap<>();
        for (char ch : t.toCharArray()) {
            res.put(ch,res.getOrDefault(ch,0) + 1);
        }
        // 正式执行，每轮向右拓展右端点
        for (right = 0; right < s.length(); right++) {
            char chr = s.charAt(right);
            char chl = s.charAt(left);
            // 右端点
            if (res.containsKey(chr)) {
                res.put(chr,res.get(chr) - 1);
            }
            // 左端点能拓展则拓展，1、左端点字符不在res中。2、区间内左端点字符过多
            while (res.getOrDefault(s.charAt(left),-1) < 0) {
                if (res.getOrDefault(s.charAt(left),1) < 0) {
                    res.put(s.charAt(left),res.get(s.charAt(left)) + 1);
                }
                left++;
                if (left >= s.length()) return "";
            }
            if (res.values().stream().allMatch(value -> value <= 0) && right - left < min) {
                min = right - left;
                result = s.substring(left,right+1);
            }
        }
        return result;
    }

    /**
     * 最大滑动窗口  undo
     int[] nums = {1,3,-1,-3,5,3,6,7};
     System.out.println(maxSlidingWindow(nums,3));
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n-k+1];
        int[] index = new int[n-k+1];
        res[0] = nums[0];
        index[0] = 0;
        for (int i = 1; i < k; i++) {
            if (res[0] < nums[i]) {
                res[0] = nums[i];
                index[0] = i;
            }
        }

        for (int i = 1; i < (n - k + 1); i++) {
            if (index[i-1] == i-1) {
                res[i] = nums[i];
                index[i] = i;
                for (int j = 1; j < k; j++) {
                    if (res[i] < nums[i+j]) {
                        res[i] = nums[i+j];
                        index[i] = i+j;
                    }
                }
            } else if (nums[i + k - 1] > res[i-1]) {
                res[i] = nums[i + k - 1];
                index[i] = i + k - 1;
            }else {
                res[i] = res[i-1];
                index[i] = index[i-1];
            }
        }

        return res;
    }

    /**
     * leetcode 560. 和为 K 的子数组  done
     * hashmap保存前i个数字之和-次数，转化为map.containsKey(sum - k)
     int[] nums = {1,2,3};
     System.out.println(subarraySum(nums,3));
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum(int[] nums, int k) {
        int sum = 0;
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        for(int num :nums){
            sum = sum + num;
            if(map.containsKey(sum - k)){
                count = count + map.get(sum - k);
            }

            map.put(sum,map.getOrDefault(sum,0) + 1);
        }
        return count;
    }

    /**
     * leetcode 2488. 统计中位数为 K 的子数组
     int[] nums = {3,2,1,4,5};
     System.out.println(countSubarrays(nums,4));
     * @param nums
     * @param k
     * @return
     */
    public static int countSubarrays(int[] nums, int k) {
        int n = nums.length;
        if (k == n) return 1;

        int[] left = new int[n];
        HashMap<Integer,Integer> map1 = new HashMap<>();
        HashMap<Integer,Integer> map2 = new HashMap<>();
        int right = 0;
        int res = 0;
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == k) {
                index = i;
                map2.put(0,1);
            }
        }
        for (int j = index; j >= 0; j--) {
            if (j != index) {
                left[j] = left[j + 1] + (nums[j] > k ? 1 : -1);
                if ((index - j) % 2 == 1) map1.put(left[j],map1.getOrDefault(left[j],0) + 1);
                else map2.put(left[j],map2.getOrDefault(left[j],0) + 1);
            }
        }
        for (int i = index; i < n; i++) {
            if (i != index) right += nums[i] > k ? 1 : -1;
                // 长度奇数
            if ((i - index) % 2 == 0) {
                res += map1.getOrDefault(1 - right,0);
                res += map2.getOrDefault(0 - right,0);
            } else {    // 长度偶数
                res += map1.getOrDefault(0 - right,0);
                res += map2.getOrDefault(1 - right,0);
            }
        }
        return res;
    }

    /**
     * leetcode 128. 最长连续序列
     * 求连续序列，但数组未排序，那就sort排序一下呀！
     int[] nums = {100,4,200,1,3,2};
     System.out.println(longestConsecutive(nums));
     * @param nums
     * @return
     */
    public static int longestConsecutive(int[] nums) {
        int res = 0;
        if (nums.length <= 1) return nums.length;
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                int left = map.getOrDefault(num-1,0);
                int right = map.getOrDefault(num+1,0);
                map.put(num,1 + left + right);
                if (left != 0) map.put(num - left,1 + left + right);
                if (right != 0) map.put(num + right,1 + left + right);
                res = res > (1 + left + right) ? res : (1 + left + right);
            }
        }
        return res;
    }

    /**
     * leetcode 49. 字母异位词分组
     String[] strs = {"eat","tea","tan","ate","nat","bat"};
     System.out.println(groupAnagrams(strs));
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        int i = 0;
        List<List<String>> res = new ArrayList<>();
        HashMap<String,Integer> map = new HashMap();
        for (String str : strs) {
            char[] carr = str.toCharArray();
            Arrays.sort(carr);
            String tmp = new String(carr);
            if (map.containsKey(tmp)) {
                res.get(map.get(tmp)).add(str);
            } else {
                map.put(tmp,i);
                List<String> s = new ArrayList<>();
                s.add(str);
                res.add(i++,s);
            }
        }
        return  res;
    }

    public static int minCost(int[] nums, int k) {
        int res = 0;
        // 读取数组各数字出现次数
        int n = nums.length;
        int[] degree = new int[n];
        for (int num : nums) {
            degree[num]++;
        }
        int cost = k;
        for (int de : degree) {
            cost += (de == 1 ? 0 : de);
        }

        int[] times = new int[n];
        int minIndex = -1;
        int minCost = cost;
        cost += k;
        for (int index = 0; index < n; index++) {
            int left = ++times[nums[index]];
            int right = --degree[nums[index]];
            if (left == 2) cost += 2;
            else if (left != 1) cost += 1;
            if (right == 1) cost -= 2;
            else if(right != 0) cost-= 1;
            if (cost < minCost) {
                minCost = cost;
                minIndex = index + 1;
            }
        }
        return minCost;
    }

    /**
     * leetcode 2571. 将整数减少到零需要的最少操作数
     System.out.println(minOperations(25));
     * @param n
     * @return
     */
    public static int minOperations(int n) {
        int ans = 1;
        while ((n & (n - 1)) > 0) { // n 不是 2 的幂次
            int lb = n & -n;
            if ((n & (lb << 1)) > 0) n += lb; // 多个连续 1
            else n -= lb; // 单个 1
            ++ans;
        }
        return ans;
    }

    public static int collectTheCoins(int[] coins, int[][] edges) {
        return 0;
    }

    public static int longestValidSubstring(String word, List<String> forbidden) {
        int n = word.length();
        for(int i = n; i >= 0; i--) {
            for(int j = 0; j < n-i+1; j++) {
                if (is(word.substring(j,j+i),forbidden)) return i;
            }
        }
        return 0;
    }
    public static boolean is (String key, List<String> forbidden) {
        for (String word : forbidden) {
            if (key.contains(word)) return false;
        }
        return true;
    }

    /**
     * leetcode 2560. 打家劫舍 IV 未完成
     int[] nums = {6,7,9,3,1};
     System.out.println(minCapability(nums,2));
     * @param nums
     * @param k
     * @return
     */
    public static int minCapability(int[] nums, int k) {
        int n = nums.length;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0],nums[1]);

        int[] dp = new int[k];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        int min = nums[0];
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }

        return 0;
    }

    /**
     * leetcode 2858. 可以到达每一个节点的最少边反转次数
     int[][] edges = {{2,0},{2,1},{1,3}};
     int[] res = minEdgeReversals(4,edges);
     for (int i = 0; i < res.length; i++) {
     System.out.println(res[i]);
     }
     * @param n
     * @param edges
     * @return
     */
    public static int[] minEdgeReversals(int n, int[][] edges) {
        Map<Integer,ArrayList<int[]>> map = new HashMap<>();
        for (int[] edge : edges) {
            if (!map.containsKey(edge[0])) map.put(edge[0],new ArrayList<>());
            if (!map.containsKey(edge[1])) map.put(edge[1],new ArrayList<>());
            map.get(edge[0]).add(edge);
            map.get(edge[1]).add(edge);
        }
        int[] result = new int[n];
        result[0] = minEdgeReversals(0,-1,map);
        result = minEdgeReversals(0,-1,result,map);
        return result;
    }

    public static int minEdgeReversals(int a, int b, Map<Integer,ArrayList<int[]>> map) {
        int count = 0;
        for (int[] branch : map.get(a)) {
            if (branch[0] != b && branch[1] != b) {
                count += branch[0] != a ? 1 : 0;
                count += minEdgeReversals(branch[branch[0] != a ? 0 : 1],branch[branch[0] != a ? 1 : 0],map);
            }
        }
        return count;
    }

    public static int[] minEdgeReversals(int a, int b, int[] result, Map<Integer, ArrayList<int[]>> map) {
        for (int[] branch : map.get(a)) {
            if (branch[0] != b && branch[1] != b) {
                System.out.println(branch[branch[0] != a ? 0 : 1]);
                System.out.println(branch[branch[0] != a ? 1 : 0]);
                result[branch[branch[0] != a ? 0 : 1]] = result[branch[branch[0] != a ? 1 : 0]] + (branch[0] == a ? 1 : -1);
                result = minEdgeReversals(branch[branch[0] != a ? 0 : 1],branch[branch[0] != a ? 1 : 0],result,map);
            }
        }
        return result;
    }
    /*  标答
    public static int[] minEdgeReversals(int n, int[][] edges) {
        HashMap<Integer, ArrayList<int[]>> map = new HashMap<>();
        for (int[] edge : edges) {
            map.computeIfAbsent(edge[0], t -> new ArrayList<>()).add(edge);
            map.computeIfAbsent(edge[1], t -> new ArrayList<>()).add(edge);
        }
        int[] result = new int[n];
        result[0] = minEdgeReversals(0, -1, map);
        minEdgeReversals(0, -1, result, map);
        return result;
    }

    private static int minEdgeReversals(int u, int v, HashMap<Integer, ArrayList<int[]>> map) {
        int count = 0;
        for (int[] i : map.get(u)) {
            if (i[0] != v && i[1] != v) {
                count += (i[0] == u ? 0 : 1) + minEdgeReversals(i[i[0] == u ? 1 : 0], u, map);
            }
        }
        return count;
    }

    private static void minEdgeReversals(int u, int v, int[] result, HashMap<Integer, ArrayList<int[]>> map) {
        for (int[] i : map.get(u)) {
            if (i[0] != v && i[1] != v) {
                result[i[i[0] == u ? 1 : 0]] = result[u] + (i[0] == u ? 1 : -1);
                minEdgeReversals(i[i[0] == u ? 1 : 0], u, result, map);
            }
        }
    }*/

    /**
     * leetcode 213.打家劫舍II
        int[] nums = {2,1,1,2};
        System.out.println(rob(nums));
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        int[][] dp = new int[nums.length][2];
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0],nums[1]);
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        dp[1][0] = nums[1];
        dp[1][1] = dp[0][1];
        for (int i = 2; i < nums.length - 1; i++) {
            dp[i][0] = Math.max(dp[i-2][0] + nums[i], dp[i-1][0]) ;
            dp[i][1] = Math.max(dp[i-2][1] + nums[i], dp[i-1][1]) ;
        }
        dp[nums.length-1][0] = Math.max(dp[nums.length-3][0] + nums[nums.length-1], dp[nums.length-2][0]);
        dp[nums.length-1][1] = dp[nums.length-2][1];
        return Math.max(dp[nums.length-1][0],dp[nums.length-1][1]);
    }

    /**
     * leetcode 2856. 删除数对后的最小数组长度
     int[] nums = {1, 9, 8, 7, 6, 9, 5, 4};
     List<Integer> a = new ArrayList<>();
     for (int i = 0; i < nums.length; i++) {
     a.add(nums[i]);
     }
     System.out.println(minLengthAfterRemovals(a));
     * @param nums
     * @return
     */
    public static int minLengthAfterRemovals(List<Integer> nums) {
        int k = 0;
        for (int i = (nums.size() + 1) / 2; i < nums.size(); i++) {
            k += nums.get(k) < nums.get(i) ? 1 : 0;
        }
        return nums.size() - 2 * k;
    }

    /**
     * leetcode 2857. 统计距离为 k 的点对
     int[][] nums = {{5,2},{5,2},{5,2},{5,2},{5,2},{5,2}};
     List<List<Integer>> coordinates = new ArrayList<>();
     for (int[] num : nums) {
     List<Integer> tmp = new ArrayList<>();
     tmp.add(0,num[0]);
     tmp.add(1,num[1]);
     coordinates.add(tmp);
     }
     System.out.println(countPairs(coordinates,5));
     * @param coordinates
     * @param k
     * @return
     */
    public static int countPairs(List<List<Integer>> coordinates, int k) {
        HashMap<List<Integer>, Integer> map = new HashMap<>();
        int count = 0;
        for (List<Integer> coordinate : coordinates) {
            for (int i = 0; i <= k; i++) {
                count += map.getOrDefault(List.of(i ^ coordinate.get(0), k - i ^ coordinate.get(1)), 0);
            }
            map.put(coordinate, map.getOrDefault(coordinate, 0) + 1);
        }
        return count;
    }

    // AC
    public static int minimumRightShifts(List<Integer> nums) {

        for (int i = 0; i < nums.size(); i++) {
            if (is(nums)) return i;
            Integer lastElement = nums.remove(nums.size() - 1); // 移除最后一个元素
            nums.add(0, lastElement);
        }
        return 0;
    }
    public static boolean is(List<Integer> nums) {
        for (int j = 0; j < nums.size()-1; j++) {
            if (nums.get(j) >= nums.get(j+1)) return false;
        }
        return true;
    }

    /**
     1
     6 3
     1 2
     1 3
     2 4
     2 5
     3 6
     */


    public static String longestPalindrome(String s) {
        List<List<Integer>> res = new ArrayList<>();
        int start = 0, end = -1;
        StringBuffer t = new StringBuffer("#");
        for (int i = 0; i < s.length(); ++i) {
            t.append(s.charAt(i));
            t.append('#');
        }
        t.append('#');
        s = t.toString();

        List<Integer> arm_len = new ArrayList<Integer>();
        int right = -1, j = -1;
        for (int i = 0; i < s.length(); ++i) {
            int cur_arm_len;
            if (right >= i) {
                int i_sym = j * 2 - i;
                int min_arm_len = Math.min(arm_len.get(i_sym), right - i);
                cur_arm_len = expand(s, i - min_arm_len, i + min_arm_len);
            } else {
                cur_arm_len = expand(s, i, i);
            }
            arm_len.add(cur_arm_len);
            if (i + cur_arm_len > right) {
                j = i;
                right = i + cur_arm_len;
            }
            if (cur_arm_len * 2 + 1 > end - start) {
                start = i - cur_arm_len;
                end = i + cur_arm_len;
            }
        }

        StringBuffer ans = new StringBuffer();
        for (int i = start; i <= end; ++i) {
            if (s.charAt(i) != '#') {
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
    }

    public static int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return (right - left - 2) / 2;
    }

    /**
     * 重写compare方法
     Integer[] nums = {1,2,1,2,5,3,1};
     leetcode.cmp leetcode.cmp = new leetcode.cmp();
     Arrays.sort(nums,leetcode.cmp);
     */
    class cmp implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return 0;
        }
    }
}
