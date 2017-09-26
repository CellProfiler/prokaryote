package org.cellprofiler.imageset;


/**
 * @author Lee Kamentsky
 *
 * The ImageSeries details holds metadata for
 * one of the series (or the Image in OME-speak) 
 * within an image file.
 */
public class ImageSeriesDetails extends Details {
	final private ImageSeries imageSeries;
	public ImageSeriesDetails(ImageSeries imageSeries, Details parent) {
		super(parent);
		this.imageSeries = imageSeries;
	}
	
	public ImageSeries getImageSeries() {
		return imageSeries;
	}
}
