package core;

import java.awt.Color;

import de.informatics4kids.image.Picture;

public class ImageUtilities {

	public static Picture makeGreen(Picture pic) {

		Color green;
		Picture greenPic = new Picture(pic.widthX(), pic.heightY());

		for (int i = 0; i < pic.widthX(); i++) {
			for (int j = 0; j < pic.heightY(); j++) {

				Color color = pic.getColor(i, j);

				green = new Color(0, color.getGreen(), 0);

				greenPic.setColor(i, j, green);

			}
		}

		return greenPic;
	}

	public static Picture makeBlue(Picture pic) {

		Color blue;
		Picture bluePic = new Picture(pic.widthX(), pic.heightY());

		for (int i = 0; i < pic.widthX(); i++) {
			for (int j = 0; j < pic.heightY(); j++) {

				Color color = pic.getColor(i, j);

				blue = new Color(0, 0, color.getBlue());

				bluePic.setColor(i, j, blue);

			}
		}

		return bluePic;
	}

	public static Picture makeRed(Picture pic) {

		Color red;
		Picture bluePic = new Picture(pic.widthX(), pic.heightY());

		for (int i = 0; i < pic.widthX(); i++) {
			for (int j = 0; j < pic.heightY(); j++) {

				Color color = pic.getColor(i, j);

				red = new Color(color.getRed(), 0, 0);

				bluePic.setColor(i, j, red);

			}
		}

		return bluePic;
	}

	public static Picture makeGrey(Picture pic) {

		Color oldColor;
		Color grey;
		Picture greyPic = new Picture(pic.widthX(), pic.heightY());

		for (int i = 0; i < pic.widthX(); i++) {
			for (int j = 0; j < pic.heightY(); j++) {

				oldColor = pic.getColor(i, j);
				int y = (int) (0.3 * oldColor.getRed() + 0.59
						* oldColor.getGreen() + 0.11 * oldColor.getBlue());

				grey = new Color(y, y, y);
				greyPic.setColor(i, j, grey);
			}
		}

		return greyPic;

	}

	public static Picture makeBin(Picture pic, int value) {

		Picture greyPic = makeGrey(pic);
		Picture binPic = new Picture(pic.widthX(), pic.heightY());
		Color col;
		Color binColor;

		for (int i = 0; i < pic.widthX(); i++) {
			for (int j = 0; j < pic.heightY(); j++) {

				col = greyPic.getColor(i, j);

				int g = col.getRed();
				int binarvalue;
				if (g <= value) {
					binarvalue = 0;
				} else {
					binarvalue = 255;
				}
				
				binColor =  new Color(binarvalue, binarvalue, binarvalue);
				binPic.setColor(i, j, binColor);

			}
		}
		
		return binPic;
	}

}
