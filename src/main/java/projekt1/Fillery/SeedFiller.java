package projekt1.Fillery;

import projekt1.Objekty.RasterBufferedImage;

public class SeedFiller implements Filler {
    private RasterBufferedImage raster;
    private int x, y;
    private int fillColor;
    private int backgroundColor;

    public SeedFiller(RasterBufferedImage raster, int x, int y, int fillColor){
        this.raster = raster;
        this.x = x;
        this.y = y;
        this.fillColor = fillColor;
        this.backgroundColor = raster.getPixel(x, y);
    }

    @Override
    public void fill(){
        if(backgroundColor != fillColor){
            seedFill(x,y);
        }
    }
    private void seedFill(int x, int y){
        if(x < 0 || x >= raster.getWidth() || y < 0 || y >= raster.getHeight()){
            return;
        }
        if(raster.getPixel(x, y) == backgroundColor){
            raster.setPixel(x, y, fillColor);
            seedFill(x + 1, y);
            seedFill(x - 1 , y);
            seedFill(x, y + 1);
            seedFill(x, y - 1);
        
        }
    }
}
