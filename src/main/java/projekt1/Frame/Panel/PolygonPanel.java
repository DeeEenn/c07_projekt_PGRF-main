package projekt1.Frame.Panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;

import javax.swing.JPanel;

import projekt1.Fillery.ScanLine;
import projekt1.Fillery.SeedFiller;
import projekt1.Objekty.FilledLineRasterizer;
import projekt1.Objekty.LineRasterizer;
import projekt1.Objekty.Pentagon;
import projekt1.Objekty.Polygon;
import projekt1.Objekty.Raster;
import projekt1.Objekty.RasterBufferedImage;

public class PolygonPanel extends JPanel {
    private static final int width = 1200;
    private static final int height = 800;
    private SeedFiller seedFiller;
    private final RasterBufferedImage raster;
    private final Polygon polygon; 
    private final ArrayList<Point> points; 
    private boolean drawPentagon = false;
    private Polygon pentagon;
    private int positionX, positionY;
    private int pentagonX, pentagonY;
    private int radius;

    public PolygonPanel() {
        setFocusable(true); 
        setPreferredSize(new Dimension(width, height));
        raster = new RasterBufferedImage(width, height);
        polygon = new Polygon();
        pentagon = new Polygon();
        LineRasterizer lineRasterizer = new FilledLineRasterizer(raster);
        ScanLine scanLine = new ScanLine(lineRasterizer, polygon);
        raster.clear(0xffffffff);

        points = new ArrayList<>(); 

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                positionX = e.getX();
                positionY = e.getY();
            }
            @Override
            public void mouseDragged(MouseEvent e){
                if(drawPentagon){
                    raster.clear(0xffffffff);
                    radius = (int) Math.sqrt(Math.pow(e.getX() - pentagonX, 2) + Math.pow(e.getY() - pentagonY, 2));
                    pentagon = new Pentagon(pentagonX, pentagonY, radius);
                    repaint();
                }
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 'C' || e.getKeyChar() == 'c') {
                    points.clear();
                    raster.clear(0xffffffff);
                    repaint();
                } else if (e.getKeyChar() == 'f' || e.getKeyChar() == 'F') {
                    int pixelColor = raster.getPixel(positionX, positionY);
                    String hexColor = String.format("#%06X", (0xFFFFFF & pixelColor));
                    System.out.println("Pixel color: " + hexColor);
                    System.out.println("Position: " + positionX + ", " + positionY);
            
                    seedFiller = new SeedFiller(raster, positionX, positionY, 0xFF00FF00);
                    seedFiller.fill();
                    repaint();
                } else if (e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
                    scanLine.fill();
                    repaint();
                } else if (e.getKeyChar() == 'p' || e.getKeyChar() == 'P') {
                    if(drawPentagon == false){
                        drawPentagon = true;
                        System.out.println("Pentagon mode on");
                    } else {
                        drawPentagon = false;
                        System.out.println("Pentagon mode off");
                    }
                }
            }
               
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(drawPentagon){
                    clear(0xffffffff);
                    pentagonX = e.getX();
                    pentagonY = e.getY();
                } else {
                    addPoint(e.getX(), e.getY()); 
                    raster.clear(0xffffffff);
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        raster.repaint(g);
        drawPolygon(g);

        if (drawPentagon && pentagon != null) {
            drawPentagonEdges(g);
        }

        g.setColor(Color.BLACK); 
        g.setFont(new Font("Arial", Font.PLAIN, 16)); 
        String text = "| C = Clear Canvas | | F = Fill color seed | S = Fill color scanline | P = Pentagon mode off/on"; 
        g.drawString(text, 5, height - 30);
    }

    public void clear(int color) {
        System.out.println("Clearing Canvas");
        raster.clear(0x2f2f2f);
        pentagon = new Polygon();
    }


    private void addPoint(int x, int y) {
        points.add(new Point(x, y)); 
        polygon.addPoint(new projekt1.Objekty.Point(x, y)); 
    }

      private void drawLine(int x1, int y1, int x2, int y2) {
        FilledLineRasterizer lineDrawer = new FilledLineRasterizer(raster);
        lineDrawer.setColor(Color.BLACK); 
        lineDrawer.drawLine(x1, y1, x2, y2);
    }

    private void drawPentagonEdges(Graphics g) {
        ArrayList<projekt1.Objekty.Point> pentagonPoints = pentagon.getPentagonPoints();
    
        if (pentagonPoints.size() < 2) return; 
    
        for (int i = 0; i < pentagonPoints.size(); i++) {
            projekt1.Objekty.Point p1 = pentagonPoints.get(i);
            projekt1.Objekty.Point p2 = pentagonPoints.get((i + 1) % pentagonPoints.size()); 
            drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }
    

    private void drawPolygon(Graphics g) {
        if (points.size() < 2) return; 
        g.setColor(Color.BLACK); 
        for (int i = 0; i < points.size() - 1; i++) {
            drawLine(points.get(i).x, points.get(i).y, points.get(i + 1).x, points.get(i + 1).y);
        }
       
        if (points.size() > 2) { 
            drawLine(points.get(0).x, points.get(0).y, points.get(points.size() - 1).x, points.get(points.size() - 1).y);
            repaint();
        }
    }
}
