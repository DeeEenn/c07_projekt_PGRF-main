package projekt1.Objekty;

import java.util.ArrayList;

public class Pentagon extends Polygon {
    private int x,y;
    private double radius;

    public Pentagon(int x, int y, double radius){
        this.x= x;
        this.y = y;
        this.radius = radius;
        drawPentagon(x,y, radius);
    }
    public void drawPentagon(int x, int y, double radius){
        double angle = 2 * Math.PI / 5;

        for (int i = 0; i < 5; i++) {
            int xx = (int)(x + radius * Math.cos( angle * i));
            int yy = (int)(y + radius * Math.sin( angle * i));
            addPentagonPoints(xx, yy);
        }
    }

  
}
