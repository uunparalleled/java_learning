package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapMain {

    public static void main(String[] args) {
        char[][] array = {
                {'1','0','1','1','1'},
                {'1','0','1','0','1'},
                {'1','1','1','0','1'}
        };
        System.out.println(numIslands(array));
    }

    public static int numIslands(char[][] grid) {
        int[][] areas = new int[grid.length][grid[0].length];
        int area = 1;
        Map<Integer, List<String>> areaMap = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    if (j > 0 && areas[i][j - 1] != 0) {
                        areas[i][j] = areas[i][j - 1];
                    }
                    if (i > 0 && areas[i - 1][j] != 0) {
                        if (areas[i][j] == 0) {
                            areas[i][j] = areas[i - 1][j];
                        } else if (areas[i][j] != areas[i-1][j]) {
                            int oldarea = areas[i-1][j];
                            for (String code : areaMap.get(oldarea)) {
                                String[] ij = code.split("-");
                                areas[Integer.parseInt(ij[0])][Integer.parseInt(ij[1])] = areas[i][j];
                            }
                            areaMap.get(areas[i][j]).addAll(areaMap.get(oldarea));
                            areaMap.remove(oldarea);
                            System.out.println("remove " + oldarea);
                        }
                    }
                    if (areas[i][j] == 0) {
                        areas[i][j] = area;
                        areaMap.put(areas[i][j], new ArrayList<>());
                        area++;
                    }

                    areaMap.get(areas[i][j]).add(i + "-" + j);
                }
            }
        }
        return areaMap.size();
    }

    /**
     * 岛屿类问题的通用解法、DFS 遍历框架
     * @param grid
     * @param r
     * @param c
     */
    void dfs(int[][] grid, int r, int c) {
        // 判断 base case
        if (!inArea(grid, r, c)) {
            return;
        }
        // 如果这个格子不是岛屿，直接返回
        if (grid[r][c] != 1) {
            return;
        }
        grid[r][c] = 2; // 将格子标记为「已遍历过」

        // 访问上、下、左、右四个相邻结点
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    int area(int[][] grid, int r, int c) {
        return 1
                + area(grid, r - 1, c)
                + area(grid, r + 1, c)
                + area(grid, r, c - 1)
                + area(grid, r, c + 1);
    }
    public static boolean inArea(char[][] array, int i, int j) {
        return 0 <= i && i < array.length
                && 0 <= j && j < array[0].length;
    }
    public static boolean inArea(int[][] array, int i, int j) {
        return 0 <= i && i < array.length
                && 0 <= j && j < array[0].length;
    }
}
