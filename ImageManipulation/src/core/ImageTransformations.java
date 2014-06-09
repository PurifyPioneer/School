package core;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;

import de.informatics4kids.image.Picture;

public class ImageTransformations {
	
	private static int xOffset = 0;
	private static int yOffset = 0;

	private static Point rotation(int x, int y, double angle) {

		Point result = new Point();

		double angleRad = Math.toRadians(angle);

		double c = Math.cos(angleRad);
		double s = Math.sin(angleRad);

		result.x = (int) (c * x - s * y);
		result.y = (int) (s * x + c * y);

		return result;
	}

	public static Picture rotatePic(Picture pic, double angle) {
		
		Picture rPic;
		RectInfo rectInf;
		rectInf = calculate(pic.widthX(), pic.heightY(), angle);
		rPic = new Picture(rectInf.width, rectInf.height);
		
		Point rPoint = new Point();
		
		for (int x = 0; x < pic.widthX(); x++) {
			for (int y = 0; y < pic.heightY(); y++) {
				rPoint = rotation(x, y, angle);
				rPoint.x += xOffset;
				rPoint.y += yOffset;
				//System.out.println("xRPoint: " + rPoint.x + " yRPoint: " + rPoint.y);
				//System.out.println("xlauf: " + x + " ylauf: " + y);
				rPic.setColor(rPoint.x -1, rPoint.y, pic.getColor(x, y));
			}
		}
		
		return rPic;
	}

	private static RectInfo calculate(int width, int height, double angle) {
		
		RectInfo inf = new RectInfo(width, height);
		
		Point points[] = new Point[4];
		points[0] = rotation(0, 0, angle);
		points[1] = rotation(width, 0, angle);
		points[2] = rotation(0, height, angle);
		points[3] = rotation(width, height, angle);
		
//		System.out.println("x: " + points[0].x + " y: " + points[0].y);
//		System.out.println("x: " + points[1].x + " y: " + points[1].y);
//		System.out.println("x: " + points[2].x + " y: " + points[2].y);
//		System.out.println("x: " + points[3].x + " y:" + points[3].y);
		
		// OFFSETCALC
		
		for (int i = 0; i < points.length; i++) {
			if (points[i].x < xOffset) {
				xOffset = points[i].x;
			}
			if (points[i].y < yOffset) {
				yOffset = points[i].y;
			}
		}
		
		// Make OFFSETS pos
		
		if (xOffset < 0) {
			xOffset *= -1;
		}
		if (yOffset < 0) {
			yOffset *= -1;
		}
		
		System.out.println("xOff: " + xOffset);
		System.out.println("yOff: " + yOffset);
		
		// OFFSETCORRECTION
		for (int i = 0; i < points.length; i++) {
			points[i].x += xOffset;
			points[i].y += yOffset;
		}
		
		System.out.println("x: " + points[0].x + " y: " + points[0].y);
		System.out.println("x: " + points[1].x + " y: " + points[1].y);
		System.out.println("x: " + points[2].x + " y: " + points[2].y);
		System.out.println("x: " + points[3].x + " y: " + points[3].y);
		
		int xpoints[] = new int[4];
		int ypoints[] = new int[4];
		
		for (int i = 0; i < points.length; i++) {
			xpoints[i] = points[i].x;
			ypoints[i] = points[i].y;
//			System.out.println("pol x:" + xpoints[i] + " pol y:" + ypoints[i]);
		}
		
		Polygon pol = new Polygon(xpoints, ypoints, 4);
		Rectangle rect = pol.getBounds();
		
		inf.width = rect.width;
		inf.height = rect.height;
		
		return inf;
	}
	
	private static class RectInfo {
		
		int width;
		int height;
		
		public RectInfo(int width, int height) {
			this.width = width;
			this.height = height;
		}
		
	}

}