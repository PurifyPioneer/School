package core;

import java.awt.Color;

import de.informatics4kids.image.Picture;

/**
 * Class that provides methods to apply color-filters to a {@link=Picture} .
 * 
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
public class ImageUtilities {

	/**
	 * Method to apply a green filter to a {@link=Picture}
	 * 
	 * @param pic
	 *            The Picture the filter should be applied to
	 * @return The modified picture
	 */
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

	/**
	 * Method to apply a blue filter to a {@link=Picture}
	 * 
	 * @param pic
	 *            The Picture the filter should be applied to
	 * @return The modified picture
	 */
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

	/**
	 * Method to apply a red filter to a {@link=Picture}
	 * 
	 * @param pic
	 *            The Picture the filter should be applied to
	 * @return The modified picture
	 */
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

	/**
	 * Method to apply a grey filter to a {@link=Picture}
	 * 
	 * @param pic
	 *            The Picture the filter should be applied to
	 * @return The modified picture
	 */
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

	/**
	 * Method to create a binary Picture just with the colors black and white.
	 * First a grey filter is used, and then the binary value is calculated.
	 * 
	 * @param pic
	 *            The Picture the filter should be applied to
	 * @return The modified picture
	 */
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

				binColor = new Color(binarvalue, binarvalue, binarvalue);
				binPic.setColor(i, j, binColor);

			}
		}

		return binPic;
	}

}
