package uk.ac.abdn.fits.gmap.model.polygon;




/*************************************************************************
 *  Compilation:  javac BoundingBox.java
 *  Execution:    java BoundingBox N
 *  Dependencies: Interval.java Point.java
 *
 *  Immutable data type for a bounding box (axis-aligned rectangle).
 *
 *************************************************************************/

public class BoundingBox { 
    private Interval intervalX;  // x-axis interval
    private Interval intervalY;  // y-axis interval
   
    // smallest bounding box containing (x1, y1) and (x2, y2)
    public BoundingBox(Point p1, Point p2) {
        intervalX = new Interval(Math.min(p1.x(), p2.x()), Math.max(p1.x(), p2.x()));
        intervalY = new Interval(Math.min(p1.y(), p2.y()), Math.max(p1.y(), p2.y()));
    }

    // smallest bounding box containing the two points
    public BoundingBox(Interval x, Interval y) {
        intervalX = x;
        intervalY = y;
    }


    // smallest bounding box containing all the points
    public BoundingBox(Point[] points) {
        double xmin = Double.POSITIVE_INFINITY;
        double ymin = Double.POSITIVE_INFINITY;
        double xmax = Double.NEGATIVE_INFINITY;
        double ymax = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < points.length; i++) {
            if (points[i].x() < xmin) xmin = points[i].x();
            if (points[i].y() < ymin) ymin = points[i].y();
            if (points[i].x() > xmax) xmax = points[i].x();
            if (points[i].y() > ymax) ymax = points[i].y();
        }
        intervalX = new Interval(xmin, xmax);
        intervalY = new Interval(ymin, ymax);
    }


    // is (x, y) inside this BoundingBox?
    public boolean contains(double x, double y) {
        return intervalX.contains(x) && intervalY.contains(y);
    }

    // does this BoundingBox intersect b?
    public boolean intersects(BoundingBox b) {
        return intervalX.intersects(b.intervalX) &&
               intervalY.intersects(b.intervalY);
    }

    // smallest bounding box containing this bounding box and b
    public BoundingBox union(BoundingBox b) {
        return new BoundingBox(intervalX.union(b.intervalX),
                               intervalY.union(b.intervalY));
    }

    // return the width, height and area
    public double width()  { return intervalX.length(); }
    public double height() { return intervalY.length(); }
    public double area()   { return width() * height(); }

    // return a string representation
    public String toString() {
        return "[ " + intervalX + " " + intervalY + " ]";
    }

}
