package core;

import java.awt.Point;

import de.informatics4kids.image.Picture;

public class ImageTransformations {

	/**
	 * Method to calculated the new coordinates of an point,
	 * which should be rotated.
	 * 
	 * @param x The x-coordinate of the point which should be rotated
	 * @param y The y-coordinate of the point which should be rotated
	 * @param angle The angle by which the point should be rotated
	 * @return The new coordinates of the rotated point
	 */
	
	//Winkel in Rad umrechnen
	private static double angleRad;
	
	// Sinus und Kosinus bestimmen
	private static double c;
	private static double s;
	
	private static Point rotation(int x, int y) {

		//Punkt
		Point result = new Point();

		//Dem Punkt die neuen Werte f�r die Koordinaten geben
		result.x = (int) (c * x - s * y);
		result.y = (int) (s * x + c * y);

		return result;
	}

	/**
	 * This Method rotates the picture by calculating
	 * new coordinates for the points from a given picture
	 * using a given angle.
	 * 
	 * @param pic Picture which should be rotated
	 * @param angle The angle by which the picture should be rotated
	 * @return The rotated picture
	 */
	public static Picture rotatePic(Picture pic, double angle) {
		
		angleRad = Math.toRadians(angle);
		c = Math.cos(angleRad);
		s = Math.sin(angleRad);

		//Extrem Punkte des Bildes bestimmen(Punkte an denen das Bild die maximale Ausdehnung hat)
		Point points[] = new Point[4];
		points[0] = rotation(0, 0);
		points[1] = rotation(0, pic.heightY());
		points[2] = rotation(pic.widthX(), 0);
		points[3] = rotation(pic.widthX(), pic.heightY());

		//Offsets
		int offsetX = 0;
		int offsetY = 0;

		//Die Offsets berechen (x+y)
		for (int i = 0; i < points.length; i++) {
			if (points[i].x < offsetX) {
				offsetX = points[i].x;
			}
			if (points[i].y < offsetY) {
				offsetY = points[i].y;
			}
		}

		//Offsets positiv machen
		offsetX *= -1;
		offsetY *= -1;

		//Extrempunkte um Offset verschieben
		for (int i = 0; i < points.length; i++) {
			points[i].x += offsetX;
			points[i].y += offsetY;
		}

		//Variablen f�r minimale Ausdehnung in x und y Richtung
		int minx = 0;
		int miny = 0;
		int maxx = 0;
		int maxy = 0;

		//Werte f�r eben deklarierte Variablen berechnen 
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

		//Breite und H�he bestimmen
		int width = maxx - minx + 1;
		int height = maxy - miny + 1;

		//Das rotierete Bild erzeugen/Einen Punkt erzeugen(au�erhalb der for-Schleife um speicher verbrauch zu minimieren)
		Picture rotatedPicture = new Picture(width, height);
		Point p = new Point();

		//�ber Bildpunkte des zugrundeliegenden Bildes laufen und neue Werte bestimmen
		for (int x = 0; x < pic.widthX() - 1; x++) {
			for (int y = 0; y < pic.heightY() - 1; y++) {

				//Errechnung des neuen Wertes
				p = rotation(x, y);
				
				//Verschiebung durch Offsets
				p.x += offsetX;
				p.y += offsetY;

				//Farbwerte auf dem rotierten Bild setzten
				rotatedPicture.setColor(p.x, p.y, pic.getColor(x, y));
			}
		}

		return rotatedPicture;
	}
}