package core;

import java.awt.Point;

import de.informatics4kids.image.Picture;

public class ImageTransformations {
	
	private static Point rotation(int x, int y, double angle) {
		
		Point result = new Point();
		
		double angleRad = Math.toRadians(angle);
		
		double c = Math.cos(angleRad);
		System.out.println("Cos:" + c);
		double s = Math.sin(angleRad);
		System.out.println("Sin: " + s);

		result.x = (int) (c * x - s * y);
		result.y = (int) (s * x + c * y);

		return result;
	}

	public static Picture rotatePic(Picture pic, double angle) {

		Point points[] = new Point[4];
		points[0] = rotation(0, 0, angle);
		points[1] = rotation(0, pic.heightY(), angle);
		points[2] = rotation(pic.widthX(), 0, angle);
		points[3] = rotation(pic.widthX(), pic.heightY(), angle);
		
		for (int i = 0; i < points.length; i++) {
			System.out.println(points[i]);
		}
		
		int offsetX = 0;
		int offsetY = 0;
		
		for (int i = 0; i < points.length; i++) {
			if (points[i].x < offsetX) {
				offsetX = points[i].x;
			}
			if (points[i].y < offsetY) {
				offsetY = points[i].y;
			}
		}
		
		offsetX *= -1;
		offsetY *= -1;
		
		for (int i = 0; i < points.length; i++) {
			points[i].x += offsetX;
			points[i].y += offsetY;
			System.out.println(points[i]);
		}
		
		System.out.println("Offsetx: " + offsetX);
		System.out.println("Offsety: " + offsetY);
		
		int minx = 0;
		int miny = 0;
		int maxx = 0;
		int maxy = 0;

		for (int i = 0; i < points.length; i++) {
			if (points[i].x < minx) {
				minx = points[i].x;
			}
			if (points[i].y < miny) {
				miny = points[i].y;
			}
			if (points[i].x > maxx) {
				maxx = points[i].x;
			}
			if (points[i].y > maxy) {
				maxy = points[i].y;
			}
		}
		
		System.out.println("Minx: " + minx);
		System.out.println("Miny: " + miny);
		System.out.println("Maxx: " + maxx);
		System.out.println("Maxy: " + maxy);
		
		int width = maxx - minx + 1;
		int height = maxy - miny + 1;
	
//		System.out.println("Width: " + width);
//		System.out.println("Height: " + height);
		
		Picture rotatedPicture = new Picture(width, height);
		Point p = new Point();
		
		System.out.println(rotatedPicture.widthX());
		System.out.println(rotatedPicture.heightY());
		
		//TODO
		for (int x = 0; x < pic.widthX() - 1; x++) {
			for (int y = 0; y < pic.heightY() - 1; y++) {
				
			p = rotation(x, y, angle);	
			p.x += offsetX;
			p.y += offsetY;
			
			if (p.x < 0 ) {
				System.err.println("Error(x): " + p.x);
				System.err.println("Error(y): " + p.y);
			}
			
			System.out.println("X: " + p.x + " Y: " + p.y);
			
			rotatedPicture.setColor(p.x, p.y, pic.getColor(x, y));	
			}
		}
		
		return rotatedPicture;
	}
}