package routing.pathFinding;

import routing.num.Node;

import java.util.List;

public interface PathInterface {
    List<int[]> findPath(int[][] grid, Node SNode, Node ENode);
}
