package org.cellprofiler.imageset;

import ome.xml.model.Image;
import ome.xml.model.OME;

/**
 * @author Lee Kamentsky
 *
 * For formats like .flex and others, the Series
 * is a subsection of an image file that represents
 * a single stack of images with a definite and uniform
 * extent in X, Y, Z, T and channel.
 * 
 * In the OME schema, series = Image
 */
public class ImageSeries implements Comparable<ImageSeries>{
	final private ImageFile imageFile;
	final private int series;
	public ImageSeries(ImageFile imageFile, int series) {
		this.imageFile = imageFile;
		this.series = series;
	}
	/**
	 * @return the ImageFile representing the file containing the series
	 */
	public ImageFile getImageFile() {
		return imageFile;
	}
	
	/**
	 * @return the series index of this stack
	 */
	public int getSeries() {
		return series;
	}
	
	/**
	 * @return the OME XML image data or null if no OMEXML data
	 */
	public Image getOMEImage() {
		final OME metadata = imageFile.getMetadata(); 
		if (metadata == null) return null;
		return metadata.getImage(series);
	}
	/* 
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(ImageSeries o) {
		if (this == o) return 0;
		if (imageFile == o.imageFile) return series - o.series;
		int result = imageFile.compareTo(o.imageFile);
		if (result == 0) return series - o.series;
		return result;
	}
}
