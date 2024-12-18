package projekt1.Objekty;

import java.awt.Color;

public abstract class LineRasterizer {

    Raster raster;
    Color color;

    public LineRasterizer(Raster raster) {
        this.raster = raster;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setColor(int color) {
        this.color = new Color(color);
    }

    public void rasterize(Line line) {
        drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
    }
}

