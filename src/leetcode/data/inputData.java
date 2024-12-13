package leetcode.data;

public class inputData {

    int[] nums1 = {0,0,0,1,1,0,0,1};
    int[][] nums2 = {{0,1},{0,2},{1,3},{1,4},{2,5},{5,6},{5,7}};
    char[][] chars2 = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
    char[][] chars2a = {
            {'1','1','1','1','1','0','1','1','1','1'},
            {'1','0','1','0','1','1','1','1','1','1'},
            {'0','1','1','1','0','1','1','1','1','1'},
            {'1','1','0','1','1','0','0','0','0','1'},
            {'1','0','1','0','1','0','0','1','0','1'},
            {'1','0','0','1','1','1','0','1','0','0'},
            {'0','0','1','0','0','1','1','1','1','0'},
            {'1','0','1','1','1','0','0','1','1','1'},
            {'1','1','1','1','1','1','1','1','0','1'},
            {'1','0','1','1','1','1','1','1','1','0'}};

    /**
     * 输入数据转换
     * @param input  "[[2,1,1],[1,1,0],[0,1,1]]"
     * @return
     */
    public static int[][] inputArrayInt(String input) {
        // 移除输入字符串的前后方括号和多余空格
        input = input.trim().replaceAll("^\\[|\\]$", "");

        // 按行分割
        String[] rows = input.split("\\],\\[");

        int[][] result = new int[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            // 去除行的中括号并分割每一行的数字
            String row = rows[i].replaceAll("[\\[\\]]", "");
            String[] elements = row.split(",");

            // 将字符串数组转化为整数数组
            result[i] = new int[elements.length];
            for (int j = 0; j < elements.length; j++) {
                result[i][j] = Integer.parseInt(elements[j]);
            }
        }
        return result;
    }
    public static char[][] inputArrayChar(String input) {
        // 移除输入字符串的前后方括号和多余空格
        input = input.trim().replaceAll("^\\[|\\]$", "");

        // 按行分割
        String[] rows = input.split("\\],\\[");
        char[][] result = new char[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            // 去除行的中括号并去掉逗号和多余空格
            String row = rows[i].replaceAll("[\\[\\]]", "").replace(",", "");

            // 将每行字符串转为字符数组
            result[i] = row.toCharArray();
        }
        return result;
    }

}
