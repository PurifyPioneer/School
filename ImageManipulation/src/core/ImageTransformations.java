package core;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;

import de.informatics4kids.image.Picture;

public class ImageTransformations {

	private static Point rotation(int x, int y, double angle) {

		Point result = new Point();

		double c = Math.cos(angle);
		double s = Math.sin(angle);

		result.x = (int) (c * x - s * y);
		result.y = (int) (s * x + c * y);

		return result;
	}

	public static RotateRecInfo calculate(int width, int height, double angle) {

		Point ul = rotation(0,0, angle);
		Point ur = rotation(width, 0, angle);
		Point ll = rotation(0, height, angle);
		Point lr = rotation(width, height, angle);

		int[] x = new int[] { ul.x, ur.x, ll.x, lr.x };
		int[] y = new int[] { ul.y, ur.y, ll.y, lr.y };

		Polygon pol = new Polygon(x, y, 4);
		Rectangle rec = pol.getBounds();

		return new RotateRecInfo(rec.width, rec.height, -rec.x, -rec.y);

	}

	public static Picture rotatePic(Picture pic, double angle) {

		RotateRecInfo rotInf = calculate(pic.widthX(), pic.heightY(), angle);

		Picture rotatedPicture = new Picture(rotInf.getWidth(),
				rotInf.getHeight());

		for (int i = 0; i < pic.widthX(); i++) {
			for (int j = 0; j < pic.heightY(); j++) {

				Point p = rotation(i, j, angle);
				p.x += rotInf.xOff;
				p.y += rotInf.yOff;
				rotatedPicture.setColor(p.x - 1, p.y-1, pic.getColor(i, j));
			}
		}

		return rotatedPicture;
	}

	private static class RotateRecInfo {
		@Override
		public String toString() {
			return "RotateRecInfo [width=" + width + ", height=" + height
					+ ", xOff=" + xOff + ", yOff=" + yOff + "]";
		}

		public RotateRecInfo(int width, int height, int xOff, int yOff) {
			this.width = width;
			this.height = height;
			this.xOff = xOff;
			this.yOff = yOff;
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}

		public int getxOff() {
			return xOff;
		}

		public int getyOff() {
			return yOff;
		}

		private final int width, height, xOff, yOff;

	}

}
