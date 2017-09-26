package org.cellprofiler.imageset;

import java.util.List;


/**
 * @author Lee Kamentsky
 * 
 * An ImageSetError which reports that more than one ipd has the same key
 *
 */
public class ImageSetDuplicateError extends ImageSetError {
	final private List<ImagePlaneDetailsStack> ipds;
	public ImageSetDuplicateError(
			String channelName, String message, List<String> key, 
			List<ImagePlaneDetailsStack> ipds) {
		super(channelName, message, key);
		this.ipds = ipds;
	}
	public List<ImagePlaneDetailsStack> getImagePlaneDetailsStacks() { return ipds; }
}
