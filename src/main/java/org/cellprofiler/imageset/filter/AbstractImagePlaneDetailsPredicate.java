package org.cellprofiler.imageset.filter;

import org.cellprofiler.imageset.ImagePlaneDetails;


/**
 * @author Lee Kamentsky
 *
 * A filter predicate that takes an ImagePlaneDetals as input
 * 
 * @param <TOUT>
 */
public abstract class AbstractImagePlaneDetailsPredicate<TOUT> 
	implements FilterPredicate<ImagePlaneDetails, TOUT> {

	public Class<ImagePlaneDetails> getInputClass() {
		return ImagePlaneDetails.class;
	}

}
