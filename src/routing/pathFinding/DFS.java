package routing.pathFinding;

import routing.num.Node;

import java.util.*;

public class DFS implements PathInterface{
    int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    @Override
    public List<int[]> findPath(int[][] map, Node SNode, Node ENode) {
        List<int[]> path = new ArrayList<>();
        findPath(map, SNode, ENode, path);

        return path; // 如果没有找到路径，返回空列表
    }

    public boolean findPath (int[][] map, Node SNode, Node ENode, List<int[]> path) {
        int rows = map.length;
        int cols = map[0].length;

        if (SNode.getX() < 0 || SNode.getX() >= rows || SNode.getY() < 0 || SNode.getY() >= cols || map[SNode.getX()][SNode.getY()] == 1) {
            return false;
        }

        if (SNode.getX() == ENode.getX() && SNode.getY() == ENode.getY()) {
            path.add(new int[]{SNode.getX(), SNode.getY()});
            return true;
        }

        map[SNode.getX()][SNode.getY()] = 1;
        for (int[] dir : DIRECTIONS) {
            Node cNode = new Node(SNode.getX() + dir[0],SNode.getY() + dir[1]);
            if (findPath(map, cNode, ENode, path)) {
                path.add(0,new int[]{SNode.getX(), SNode.getY()});
                return true;
            }
        }

        return false;
    }

}
