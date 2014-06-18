package core;

import java.awt.Color;

import de.informatics4kids.image.Picture;

public class DigitalFilters {

	public static Picture digFilter(Picture pic) {

		Picture p = new Picture(pic.widthX(), pic.heightY());

		p = ImageUtilities.makeGrey(pic);

		int surroundingPixels[] = new int[9];
		int mask[] = { 1, 2, 1, 2, 3, 2, 1, 2, 1 };

		int xpos = 0;
		int ypos = 0;

		for (int x = 0; x < p.widthX(); x++) {
			for (int y = 0; y < p.heightY(); y++) {
				
				
				
//				/////////////////////////////////////////////////
//				
//				xpos = x - 1;
//				ypos = y - 1;
//				for (int i = 0; i < 3; i++) {
//					for (int j = 0; j < 3; j++) {
//						Color col = pic.getColor(xpos + i, ypos + j);
//						surroundingPixels[j * i] = (col.getAlpha()
//								+ col.getBlue() + col.getGreen()) / 3;
//					}
//				}
//				int resultCom = 0;
//				for (int i = 0; i < surroundingPixels.length; i++) {
//					int result[] = new int[9];
//					result[i] = surroundingPixels[i] * mask[i];
//					resultCom += result[i];
//				}
//				
//				for (int i = 0; i < surroundingPixels.length; i++) {
//					System.out.println(surroundingPixels[i]);
//				}
//				
//				System.out.println(resultCom);
//
//				/////////////////////////////////////////////////
				
				p.setColor(x, y, new Color(0));
			}
		}

		return p;

	}

}
