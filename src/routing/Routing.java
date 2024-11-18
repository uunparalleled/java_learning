package routing;

import routing.createMap.MAPFMap;
import routing.createMap.MapInterface;
import routing.num.Node;
import routing.pathFinding.BFS;
import routing.pathFinding.DFS;
import routing.pathFinding.PathInterface;

import java.util.List;

public class Routing {
    public static MapInterface mapInterface = new MAPFMap();

//    public static PathInterface pathInterface = new BFS();
    public static PathInterface pathInterface = new DFS();


    public static void main(String[] args) {

        // 创建地图
        int[][] map =  mapInterface.createMap();

        // 创建起始点，终止点
        Node snode = new Node(0,0);
        Node enode = new Node(4,4);

        List<int[]> path = pathInterface.findPath(map, snode, enode);

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
