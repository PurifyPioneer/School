package core;

import java.awt.Color;
import java.awt.Point;
import java.util.LinkedList;

import de.informatics4kids.image.Picture;

public class ImageTransformations {

	private static Point rotation(Point point, double angle){

	      Point result = new Point();

	      double c = Math.cos(angle);
	      double s = Math.sin(angle);

	      result.x = (int) (c*point.x - s*point.y);
	      result.y = (int) (s*point.x + c*point.y);

	      return result;
	}
	
	public static Picture rotatePic(Picture pic, double angle) {
		
		Picture rotatedPicture = new Picture(pic.widthX(), pic.widthX());		
		Point p = new Point();
		LinkedList<Color> colors = new LinkedList<Color>();
		LinkedList<Point> points = new LinkedList<Point>();
		int offsetx = 0;
		int offsety = 0;
		
		for (int i = 0; i < pic.widthX(); i++) {
			for (int j = 0; j < pic.heightY(); j++) {
				colors.add(pic.getColor(i, j));
				
				p.x = i;
				p.y = j;
				
				p = rotation(p, 90);
				
				// --------------
				if (p.x < 0 || p.y < 0) {
					//System.err.println("Error at:" + p.x + " || " + p.y);
					if (p.x < offsetx) {
						offsetx = p.x * -1;
					}
					if (p.y < offsety) {
						offsety = p.y * -1;
					}
				}
				
				points.add(p);
				
				//System.out.println("X: " + p.x + " Y: " + p.y);
			}
		}
		
		System.out.println(points.size());
		
		for (int i = 0; i < points.size(); i++) {
			
			Color col = colors.get(i);
			Point pt = points.get(i);
			
			if (col == null) {
				col = Color.BLUE;
			}
			
			if (pt.x < 0 || pt.y < 0) {
				System.err.println(" 1 Error at:" + p.x + " || " + p.y);
			}
			
			pt.x += offsetx;
			pt.y -= offsety;
			
			if (pt.x < 0 || pt.y < 0) {
				System.err.println(" 2 Error at:" + p.x + " || " + p.y);
			}
			
			rotatedPicture.setColor(pt.x, pt.y, col);
		}
		
		return rotatedPicture;
	}
	
}
