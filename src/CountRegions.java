public class CountRegions {
    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 1, 1},
                {1, 1, 0, 0},
                {1, 0, 1, 0},
                {1, 0, 1, 1}
        };

        int regions = countRegions(grid);
        System.out.println("二维数组被划分成了 " + regions + " 个区域。");
    }

    public static int countRegions(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int regions = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    regions++;
                    dfs(grid, i, j, visited);
                }
            }
        }

        return regions;
    }

    private static void dfs(int[][] grid, int row, int col, boolean[][] visited) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col] == 0 || visited[row][col]) {
            return;
        }

        visited[row][col] = true;

        // 检查相邻的格子
        dfs(grid, row + 1, col, visited);
        dfs(grid, row - 1, col, visited);
        dfs(grid, row, col + 1, visited);
        dfs(grid, row, col - 1, visited);
    }
}