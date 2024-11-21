package projekt1.Objekty;

public class Edge {
    private Point p1, p2;

    public Edge(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public boolean isHorizontal() {
        return p1.getY() == p2.getY();
    }

    public void orientace() {
        if (p1.getY() > p2.getY()) {
            Point temp = p1;
            p1 = p2;
            p2 = temp;
        }
    }

    public boolean intersectionExist(int y) {
        return y >= p1.getY() && y < p2.getY();
    }

    public int getIntersection(int y) {
        if (!intersectionExist(y)) return -1;
        double slope = (double) (p2.getX() - p1.getX()) / (p2.getY() - p1.getY());
        return (int) (p1.getX() + slope * (y - p1.getY()));
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }
}


