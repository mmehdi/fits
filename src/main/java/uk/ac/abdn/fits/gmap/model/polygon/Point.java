package uk.ac.abdn.fits.gmap.model.polygon;



/*************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *
 *  Immutable data type for 2D points.
 *
 *************************************************************************/

public class Point { 
    private double x;   // Cartesian  easting
    private double y;   // coordinates northing
   
    // create and initialize a random point in unit square
    public Point() {
        this.x = Math.random();
        this.y = Math.random();
    }

    // create and initialize a point with given (x, y)
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }


    // accessor methods  
    public double x() { return x; }
    public double y() { return y; }

    // return Euclidean distance between this point and that point
    public double distanceTo(Point that) {
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

    // return string representation of this point
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // test client
    public static void main(String[] args) {
        Point p = new Point();
        System.out.println("p  = " + p);
        Point q = new Point(0.5, 0.5);
        System.out.println("q  = " + q);
        System.out.println("dist(p, q) = " + p.distanceTo(q));
    }
}
