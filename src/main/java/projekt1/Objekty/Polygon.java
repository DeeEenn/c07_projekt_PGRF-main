package projekt1.Objekty;

import java.util.ArrayList;

public class Polygon {

    private final ArrayList<Point> points;
    private final ArrayList<Point> pentagonPoints;

    public Polygon() {
        this.points = new ArrayList<>();
        this.pentagonPoints = new ArrayList<>();
    }

    public void addPoint(Point p) {
        points.add(p);
    }

    public Point getPoint(int index) {
        return points.get(index);
    }

    public int getSize() {
        return points.size();
    }

    public void addPentagonPoints(int X, int Y){
        pentagonPoints.add(new Point(X, Y));
    }

    public ArrayList<projekt1.Objekty.Point> getPentagonPoints() {
        return pentagonPoints;
    }

}

