package routing;

import java.util.*;

public class GridMapBFS {
    private static final int ROWS = 5;
    private static final int COLS = 5;
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static List<int[]> findPath(int[][] grid, int startX, int startY, int endX, int endY) {
        List<int[]> path = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        Map<Integer, Integer> parentMap = new HashMap<>();

        queue.offer(new int[]{startX, startY});
        parentMap.put(startX * COLS + startY, -1);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            if (x == endX && y == endY) {
                // 从终点回溯到起点，构建路径
                while (x != startX || y != startY) {
                    path.add(new int[]{x, y});
                    int parent = parentMap.get(x * COLS + y);
                    x = parent / COLS;
                    y = parent % COLS;
                }
                Collections.reverse(path);
                return path;
            }

            for (int[] dir : DIRECTIONS) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                if (newX >= 0 && newX < ROWS && newY >= 0 && newY < COLS && grid[newX][newY] == 0
                        && !parentMap.containsKey(newX * COLS + newY)) {
                    queue.offer(new int[]{newX, newY});
                    parentMap.put(newX * COLS + newY, x * COLS + y);
                }
            }
        }

        return path; // 如果没有找到路径，返回空列表
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1},
                {0, 0, 0, 0, 0}
        };

        int startX = 0;
        int startY = 0;
        int endX = 4;
        int endY = 4;

        List<int[]> path = findPath(grid, startX, startY, endX, endY);

        if (!path.isEmpty()) {
            System.out.println("存在路径从起点到终点.");
            System.out.println("路径：");
            for (int[] point : path) {
                System.out.println("(" + point[0] + ", " + point[1] + ")");
            }
        } else {
            System.out.println("不存在路径从起点到终点.");
        }
    }
}

