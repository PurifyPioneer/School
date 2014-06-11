package core;

import de.informatics4kids.image.Picture;
import de.informatics4kids.image.PictureViewer;

public class Main {

	public static void main(String[] args) {
		
		Picture pic = new Picture(
				"./rsc/testbild.jpg");
		PictureViewer viewer;
		Picture testPic = new Picture(pic.widthX(), pic.heightY());

//		testPic = ImageUtilities.makeGreen(pic);
//		viewer = new PictureViewer(testPic.getPicture());
//		viewer.show();
//
//		testPic = ImageUtilities.makeBlue(pic);
//		viewer = new PictureViewer(testPic.getPicture());
//		viewer.show();
//
//		testPic = ImageUtilities.makeRed(pic);
//		viewer = new PictureViewer(testPic.getPicture());
//		viewer.show();
//		
//		testPic = ImageUtilities.makeGrey(pic);
//		viewer = new PictureViewer(testPic.getPicture());
//		viewer.show();
//
//		testPic = ImageUtilities.makeBin(pic, 90);
//		viewer = new PictureViewer(testPic.getPicture());
//		viewer.show();
		
		// Bild drehen ------------------------------------
		testPic = ImageTransformations.rotatePic(pic, 180);
		viewer = new PictureViewer(testPic.getPicture());
		viewer.show();
		// ------------------------------------------------
		
	}

}
