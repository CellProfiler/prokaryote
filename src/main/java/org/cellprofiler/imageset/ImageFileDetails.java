package org.cellprofiler.imageset;


/**
 * @author Lee Kamentsky
 *
 * This class represents the metadata for an image file.
 */
public class ImageFileDetails extends Details {
	final private ImageFile imageFile;
	public ImageFileDetails(ImageFile imageFile) {
		this.imageFile = imageFile;
	}
	
	public ImageFile getImageFile() {
		return imageFile;
	}
}
