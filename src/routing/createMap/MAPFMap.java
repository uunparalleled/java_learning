package routing.createMap;

public class MAPFMap implements MapInterface{

    @Override
    public int[][] createMap() {
        return new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1},
                {0, 0, 0, 0, 0}
        };
    }
}
