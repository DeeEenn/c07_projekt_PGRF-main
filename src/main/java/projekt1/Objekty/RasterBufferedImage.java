package projekt1.Objekty;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class RasterBufferedImage implements Raster {
    private BufferedImage image;
    private int clearColor; 

    public RasterBufferedImage(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        clearColor = 0xFFFFFFFF; 
    }

    @Override
    public void clear(int color) {
        Graphics gr = image.getGraphics();
        gr.setColor(new Color(color));
        gr.fillRect(0, 0, image.getWidth(), image.getHeight());
    }

    @Override
    public void setClearColor(int color) {
        this.clearColor = color;
    }

    @Override
    public int getPixel(int x, int y) {
        if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
            return clearColor; 
        }
        return image.getRGB(x, y);
    }
    

    @Override
    public void setPixel(int x, int y, int color) {
        image.setRGB(x, y, color);
    }

    @Override
    public int getWidth() {
        return image.getWidth();
    }

    @Override
    public int getHeight() {
        return image.getHeight();
    }

    public void repaint(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
}
