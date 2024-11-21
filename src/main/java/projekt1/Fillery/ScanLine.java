package projekt1.Fillery;


import java.util.*;

import projekt1.Objekty.Edge;
import projekt1.Objekty.Line;
import projekt1.Objekty.LineRasterizer;
import projekt1.Objekty.Point;
import projekt1.Objekty.Polygon;

public class ScanLine implements Filler {
    private LineRasterizer rasterizer;
    private Polygon polygon;
    private List<Edge> edges;
    private List<Integer> intersections;

    public ScanLine(LineRasterizer rasterizer, Polygon polygon){
        this.rasterizer = rasterizer;
        this.polygon = polygon;
        this.edges = new ArrayList<>();
        this.intersections = new ArrayList<>();
    }

    @Override
    public void fill(){
        scanLineFill();
    }

    private void scanLineFill(){
        List<Edge> edges = new ArrayList<>();
        int yMin = Integer.MAX_VALUE, yMax = Integer.MIN_VALUE;


        for (int i = 0; i < polygon.getSize(); i++) {
            Point p1 = polygon.getPoint(i);
            Point p2 = polygon.getPoint((i + 1) % polygon.getSize());

            Edge edge = new Edge(p1, p2);
            if (!edge.isHorizontal()) {
                edge.orientace();
                edges.add(edge);
                yMin = Math.min(yMin, edge.getP1().getY()); 
                yMax = Math.max(yMax, edge.getP2().getY()); 
            }
            
        }

        for (int y = yMin; y < yMax; y++) {
            List<Integer> intersections = new ArrayList<>();

            for (Edge edge : edges) {
                if (edge.intersectionExist(y)) {
                    intersections.add(edge.getIntersection(y));
                }
            }
            Collections.sort(intersections);

            for (int i = 0; i < intersections.size() - 1; i += 2) {
                int x1 = intersections.get(i);            
                int x2 = intersections.get(i + 1);
                
                rasterizer.drawLine(x1,y,x2,y);
            }
        }

 

        for (Edge edge : edges) {
            rasterizer.drawLine(edge.getP1().getX(), edge.getP1().getY(), edge.getP2().getX(), edge.getP2().getY());
        }

        }

        public void clearData() {
            edges.clear(); 
            intersections.clear(); 
        }
        

    }





