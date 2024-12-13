package leetcode;

import leetcode.data.inputData;

import java.util.*;

public class MapMain {

    public static void main(String[] args) {
//        char[][] array = {
//                {'1','0','1','1','1'},
//                {'1','0','1','0','1'},
//                {'1','1','1','0','1'}
//        };
        String input = "[[0,1],[1,2],[2,0]]";
        char[][] chars = inputData.inputArrayChar(input);
        int[][] nums = inputData.inputArrayInt(input);
        System.out.println(canFinish(3,nums));
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Integer> courses = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            if (prerequisites[i][0] == prerequisites[i][1]) return false;
            if (!courses.containsKey(prerequisites[i][0])) {
                if (!courses.containsKey(prerequisites[i][1])) {
                    courses.put(prerequisites[i][0],0);
                    courses.put(prerequisites[i][1],1);
                } else {
                    courses.put(prerequisites[i][0],courses.get(prerequisites[i][1])-1);
                }
            } else if (!courses.containsKey(prerequisites[i][1])){
                courses.put(prerequisites[i][1],courses.get(prerequisites[i][0])+1);
            } else if (courses.get(prerequisites[i][1]) < courses.get(prerequisites[i][0])) {
                return false;
            }
        }
        return true;
    }

    /**
     * leetcode 994. 腐烂的橘子 done
     * BFS两种方式 1、四向都探索，判断不能探索的格子   2、判断四周能探索的格子去探索
     * 记录深度： 每次循环都将 Queue完全取出
     * 探索记录：直接使用 Queue<int[]>记录ij
     * @param grid
     * @return
     */
    public static int orangesRotting(int[][] grid) {
        int res = 0;
        // 腐烂的橘子
        Queue<String> queue = new LinkedList<>();
        List<String> goodQueue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(i + "&" + j);
                }
                if (grid[i][j] == 1) {
                    goodQueue.add(i + "&" + j);
                }
            }
        }
        if (goodQueue.size() == 0) return 0;
        if (queue.size() == 0 && goodQueue.size() > 0) return -1;
        // 遍历
        while (!queue.isEmpty()) {
            // 所有待腐烂的橘子
            List<String> list = new ArrayList<>();
            while (!queue.isEmpty()) {
                list.add(queue.poll());
            }
            for (String code : list) {
                String[] ij = code.split("&");
                int i = Integer.parseInt(ij[0]);
                int j = Integer.parseInt(ij[1]);

                // 两种方式 1、四向都探索，判断不能探索的格子   2、判断四周能探索的格子去探索
                if (!inArea(grid,i,j)) {
                    continue;
                }
                if (grid[i][j] >= 3) {
                    continue;
                }
                if (grid[i][j] == 0) {
                    continue;
                }

                grid[i][j] = res + 3;
                goodQueue.remove(i + "&" + j);
                // 上下左右
                queue.offer((i-1) + "&" + (j));
                queue.offer((i+1) + "&" + (j));
                queue.offer((i) + "&" + (j-1));
                queue.offer((i) + "&" + (j+1));
            }
            res++;
        }

        if (goodQueue.isEmpty()) {
            return res-2;
        }
        return -1;
    }

    /**
     * leetcode 200. 岛屿数量 done
     * @param grid
     * @return
     */
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
    public static boolean inArea(int[][] array, String i, String j) {
        int i1 = Integer.parseInt(i);
        int j1 = Integer.parseInt(j);
        return 0 <= i1 && i1 < array.length
                && 0 <= j1 && j1 < array[0].length;
    }
}
