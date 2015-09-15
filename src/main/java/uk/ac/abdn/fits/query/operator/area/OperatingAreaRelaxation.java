package uk.ac.abdn.fits.query.operator.area;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import uk.ac.abdn.fits.gmap.model.polygon.Point;
import uk.ac.abdn.fits.gmap.model.polygon.Polygon;
import uk.ac.abdn.fits.query.operator.Operator;
import uk.me.jstott.jcoord.LatLng;
import uk.me.jstott.jcoord.OSRef;


public class OperatingAreaRelaxation {
	
	public enum Direction{
		North, South, West, East, North_West, South_West, North_East, South_East;
	}
	
	protected static OperatingAreaRelaxation instance = null;
	
	public static OperatingAreaRelaxation getInstance(){
		
		if(instance == null){
			instance = new OperatingAreaRelaxation();
		}
		return instance;
	}
	
	private OperatingAreaRelaxation(){
		
	}
	
	public Polygon relax(Polygon polygon){
		
		Point[] points = polygon.getPoints();
		Point[] enlarged = new Point[points.length];
		for(int i=0; i< points.length -1; i++){
			if (i == 0){
				enlarged[i] = getNewPointAfterEnlarging(points[i], points[points.length-2], points[i+1], 5);
			}else if (i == points.length -1){
				enlarged[i] = getNewPointAfterEnlarging(points[i], points[i -1], points[0], 5);
			}else{
				enlarged[i] = getNewPointAfterEnlarging(points[i], points[i -1], points[i+1], 5);
			}
		}
		enlarged[enlarged.length-1]= enlarged[0];
		LatLng latLng = null;
		OSRef osRef = null;
//		for(int i=0; i< enlarged.length; i++){
//			osRef = new OSRef(enlarged[i].x(), enlarged[i].y());
//			latLng = osRef.toLatLng();
//			System.out.print(latLng.getLat()+", " + latLng.getLng()+",0.0,");
//		}
		return new Polygon(enlarged);
	}
	
	public Polygon relax(Polygon polygon, Direction direction, int miles){
		
		List<Integer> indexes = getPointIndexesByDirection(polygon, direction);
		Point[] points = polygon.getPoints();
		Point[] enlarged = new Point[points.length];
		for(int i=0; i< points.length-1; i++){
			if(indexes.contains(i)){
				if (i == 0){
					enlarged[i] = getNewPointAfterEnlarging(points[i], points[points.length-2], points[i+1], miles);
				}else if (i == points.length -1){
					enlarged[i] = getNewPointAfterEnlarging(points[i], points[i -1], points[0], miles);
				}else{
					enlarged[i] = getNewPointAfterEnlarging(points[i], points[i -1], points[i+1], miles);
				}
			}else{
				enlarged[i] = points[i];
			}
		}
		enlarged[enlarged.length-1]= enlarged[0];
		LatLng latLng = null;
		OSRef osRef = null;
//		System.out.println("point has been converted to (lat, lng). The point actually stores the easting and northing");
//		for(int i=0; i< enlarged.length; i++){
//			osRef = new OSRef(enlarged[i].x(), enlarged[i].y());
//			latLng = osRef.toLatLng();
//			System.out.print(latLng.getLat()+", " + latLng.getLng()+",0.0,");
//		}
		return new Polygon(enlarged);
	}
	
	
	public List<Integer> getPointIndexesByDirection(Polygon polygon, Direction direction){
		
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		Point[] points = polygon.getPoints();
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
        double line_horizon = (ymin + ymax)/2;
        double line_vertical = (xmax + xmin)/2;
		for(int i=0; i< points.length-1; i++){
			if(direction == Direction.North){
				if(points[i].y() - line_horizon > 0){
					indexes.add(i);
				} 
			}else if(direction == Direction.South){
				if(points[i].y() - line_horizon < 0 ){
					indexes.add(i);
				}
			}else if(direction == Direction.West){
				if(points[i].x() - line_vertical < 0){
					indexes.add(i);
				}
			}else if(direction == Direction.East){
				if(points[i].x() - line_vertical > 0){
					indexes.add(i);
				}
			}
		}
		return indexes;
	}
	
	/** old
	public static List<Integer> getPointIndexesByDirection(Polygon polygon, String direction){
		
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		Point[] points = polygon.getPoints();
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
		double line1_k = (ymin - ymax)/(xmax - xmin);    // y = kx + b ; xmax will not be equal to xmin
		double line1_b = (ymax*xmax - ymin*xmin)/(xmax - xmin);
		double line2_k = (ymax - ymin)/(xmax - xmin);
		double line2_b = (ymin*xmax - ymax*xmin)/(xmax - xmin);
		double line1_y = 0, line2_y = 0;
		
		for(int i=0; i< points.length-1; i++){
			line1_y = points[i].x()*line1_k + line1_b;
			line2_y = points[i].x()*line2_k + line2_b;
			if(direction.equals("north")){
				if(points[i].y() - line1_y > 0 && points[i].y() - line2_y > 0){
					indexes.add(i);
				} 
			}else if(direction.equals("south")){
				if(points[i].y() - line1_y < 0 && points[i].y() - line2_y < 0){
					indexes.add(i);
				}
			}else if(direction.equals("west")){
				if(points[i].y() - line1_y < 0 && points[i].y() - line2_y > 0){
					indexes.add(i);
				}
			}else if(direction.equals("east")){
				if(points[i].y() - line1_y > 0 && points[i].y() - line2_y < 0){
					indexes.add(i);
				}
			}
		}
		
		return indexes;
	}
	 */
	
	public static Point getNewPointAfterEnlarging(Point P, Point pMinus1, Point pPlus1, int miles){
		
		double distance = 1609.344 * miles;
		Point Q = null;
		Vector v1 = new Vector(P.x() - pPlus1.x(), P.y() - pPlus1.y());
		v1.normalize();
		Vector v2 = new Vector(P.x() - pMinus1.x(), P.y() - pMinus1.y());
		v2.normalize();
		double sin_theta = v1.getX()*v2.getY() - v2.getX()*v1.getY();
		double Q_x = 0;
		double Q_y = 0;
		Q_x = P.x() + distance/sin_theta*(v1.getX()+ v2.getX());
		Q_y = P.y() + distance/sin_theta*(v1.getY() + v2.getY());
		Q = new Point(Q_x, Q_y);
		return Q;
	}
	
	
	
	public static void main(String[] args){
		
		String POLYGON_STR= "-2.7685546875,57.69240553526457,0.0 -2.775421142578125,57.629963916724314,0.0 -2.7472686767578125,57.602014062545535,0.0 -2.6992034912109375,57.57514724603766,0.0 -2.65045166015625,57.54826058225397,0.0 -2.618865966796875,57.544207445951805,0.0 -2.5872802734375,57.56492896950416,0.0 -2.52685546875,57.57873677041636,0.0 -2.4616241455078125,57.58278606520832,0.0 -2.39501953125,57.59456326992429,0.0 -2.300262451171875,57.59612714310655,0.0 -2.2913360595703125,57.694974357167204,0.0 -2.7685546875,57.69240553526457,0.0"; 

		Polygon ploygon = new Polygon(Operator.getPoints(POLYGON_STR));
		
		Polygon new_polygon = OperatingAreaRelaxation.getInstance().relax(ploygon, Direction.West, 5);
		
	}
	

}

class Vector{
	
	private double x; // easting
	private double y; // northing
	
	public Vector(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double getX(){
		return x;
	}
	public void setX(double x){
		this.x = x;
	}
	public double getY(){
		return y;
	}
	public void set(double y){
		this.y = y;
	}
	
	public void normalize(){
		double length = Math.sqrt(x*x + y*y);
		x = x/length;
		y = y/length;
		
	}
	
	
} 
