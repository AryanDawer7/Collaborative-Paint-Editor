import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * A multi-segment Shape, with straight lines connecting "joint" points -- (x1,y1) to (x2,y2) to (x3,y3) ...
 * 
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Spring 2016
 * @author CBK, updated Fall 2016
 * @author Aryan Dawer, Dartmouth CS 10, Fall 2022
 * @author Lindsey Drumm, Dartmouth CS 10, Fall 2022
 */
public class Polyline implements Shape {
	// TODO: YOUR CODE HERE
	private Color color; 				// color
	private List<Point> pointList;		// List of all points in polyline

	/**
	 * Constructor when only color of polyline is given
	 * @param color
	 */
	public Polyline(Color color) {
		this.color = color;
		this.pointList = new ArrayList<>();
	}

	/**
	 * Constructor when we already have a list of points and we're adding to it and also color given
	 * @param pointList
	 * @param color
	 */
	public Polyline(ArrayList<Point> pointList, Color color){
		this.color = color;
		this.pointList = pointList;
	}

	/**
	 * helper method to add given point to list
	 * @param x
	 * @param y
	 */
	public void addToPointList(int x, int y){
		pointList.add(new Point(x,y));
	}

	@Override
	public void moveBy(int dx, int dy) {
		for(int i=0;i< pointList.size();i++){
			pointList.set(i, new Point((int)pointList.get(i).getX()+dx,(int)pointList.get(i).getY()+dy));
		}
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	public boolean contains(int x, int y) {
		for(int i=0;i<pointList.size()-1;i++){
			if((Segment.pointToSegmentDistance(x,y,
					(int)pointList.get(i).getX(),(int)pointList.get(i).getY(),
					(int)pointList.get(i+1).getX(),(int)pointList.get(i+1).getY()))<=5){
				return true;
			}
		}
		return false;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		for(int i =0;i<pointList.size()-1;i++) {
			g.drawLine((int)pointList.get(i).getX(),(int)pointList.get(i).getY(),
					(int)pointList.get(i+1).getX(),(int)pointList.get(i+1).getY());
		}
	}

	@Override
	public String toString() {
		String out = "polyline ";
		for (int i = 0; i < pointList.size(); i++) {
			out += (int)pointList.get(i).getX() + " " + (int)pointList.get(i).getY() + " ";
		}
		out += color.getRGB();
		return out;
	}
}
