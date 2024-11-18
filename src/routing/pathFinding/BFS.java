package routing.pathFinding;

import routing.num.Node;

import java.util.*;

public class BFS implements PathInterface{
    int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    @Override
    public List<int[]> findPath(int[][] map, Node SNode, Node ENode) {
        int ROWS = map.length;
        int COLS = map[0].length;

        List<int[]> path = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        Map<Integer, Integer> parentMap = new HashMap<>();

        queue.offer(new int[]{SNode.getX(), SNode.getY()});
        parentMap.put(SNode.getX() * COLS + SNode.getY(), -1);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            if (x == ENode.getX() && y == ENode.getY()) {
                // 从终点回溯到起点，构建路径
                while (x != SNode.getX() || y != SNode.getY()) {
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

                if (newX >= 0 && newX < ROWS && newY >= 0 && newY < COLS && map[newX][newY] == 0
                        && !parentMap.containsKey(newX * COLS + newY)) {
                    queue.offer(new int[]{newX, newY});
                    parentMap.put(newX * COLS + newY, x * COLS + y);
                }
            }
        }

        return path; // 如果没有找到路径，返回空列表
    }
}
