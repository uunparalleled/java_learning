package routing.num;

public class Node {
    private int X;
    private int Y;

    public Node (int x, int y) {
        X = x;
        Y = y;
    }

    public void setX(int x) {
        X = x;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
}
