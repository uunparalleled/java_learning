package routing;

import java.util.*;

public class GridMapDFS {
    private static final int ROWS = 5;
    private static final int COLS = 5;
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static boolean findPath(int[][] grid, int startX, int startY, int endX, int endY, List<int[]> path) {
        if (startX < 0 || startX >= ROWS || startY < 0 || startY >= COLS || grid[startX][startY] == 1) {
            return false;
        }

        if (startX == endX && startY == endY) {
            path.add(new int[]{startX, startY});
            return true;
        }

        grid[startX][startY] = 1;
        for (int[] dir : DIRECTIONS) {
            int newX = startX + dir[0];
            int newY = startY + dir[1];
            if (findPath(grid, newX, newY, endX, endY, path)) {
                path.add(new int[]{startX, startY});
                return true;
            }
        }

        return false;
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

        List<int[]> path = new ArrayList<>();
        boolean result = findPath(grid, startX, startY, endX, endY, path);

        if (result) {
            System.out.println("存在路径从起点到终点.");
            System.out.println("路径：");
            for (int i = path.size() - 1; i >= 0; i--) {
                int[] point = path.get(i);
                System.out.println("(" + point[0] + ", " + point[1] + ")");
            }
        } else {
            System.out.println("不存在路径从起点到终点.");
        }
    }
}


