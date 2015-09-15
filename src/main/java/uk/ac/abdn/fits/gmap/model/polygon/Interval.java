package uk.ac.abdn.fits.gmap.model.polygon;




/*************************************************************************
 *  Compilation:  javac Interval.java
 *  Execution:    java Interval
 *
 *  ADT for an Interval [min, max].
 *
 *************************************************************************/

public class Interval { 
    public final double min;    // min endpoint
    public final double max;    // max endpoint

    // constructor   
    public Interval(double x1, double x2) {
        min = Math.min(x1, x2);
        max = Math.max(x1, x2);
    }

    // is x in [min, max] ?
    public boolean contains(double x) {
        return min <= x && x <= max;
    }

   

    // does this Interval a intersect Interval b?
    public boolean intersects(Interval b) {
        Interval a = this;
        if (a.max < b.min) return false;
        if (b.max < a.min) return false;
        return true;
    }

    // return the length of this Interval
    public double length() { return max - min; }

    // return the smallest Interval containing this Interval and b
    public Interval union(Interval b) {
        return new Interval(Math.min(this.min, b.min), Math.max(this.max, b.max));
    }

    // return string representation
    public String toString() {
        return "[" + min + ", " + max + "]";
    }



    // generate N random points in [-1, 2] and compute
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Interval interval = new Interval(0.0, 1.0);
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            double x = 3 * Math.random() - 1.0;
            if (interval.contains(x)) cnt++;
        }
//        StdOut.println("fraction = " + (1.0 * cnt / N));
    }
}
