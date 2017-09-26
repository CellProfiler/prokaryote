package org.cellprofiler.imageset.filter;

import net.imglib2.meta.Axes;
import org.cellprofiler.imageset.ImagePlane;
import org.cellprofiler.imageset.ImagePlaneDetails;
import org.cellprofiler.imageset.ImagePlaneDetailsStack;

/**
 * @author Lee Kamentsky
 * 
 * A predicate that determines whether an image plane
 * is a color image.
 *
 */
public class IsColorPredicate 
		extends AbstractTerminalPredicate<ImagePlaneDetailsStack> {
	final static public String SYMBOL="iscolor";

	protected IsColorPredicate() {
		super(ImagePlaneDetailsStack.class);
	}

	/* (non-Javadoc)
	 * @see org.cellprofiler.imageset.filter.FilterPredicate#getSymbol()
	 */
	public String getSymbol() {
		return SYMBOL;
	}

	/* (non-Javadoc)
	 * @see org.cellprofiler.imageset.filter.FilterPredicate#eval(java.lang.Object)
	 */
	public boolean eval(ImagePlaneDetailsStack candidate) {
		// We have a color image if it contains channels
		for (int i=0;i<candidate.numDimensions();i++) {
			if (candidate.axis(i).type().equals(Axes.CHANNEL)) {
				if (candidate.size(i) > 1) return true;
				for (ImagePlaneDetails ipd:candidate){
					if (ipd.getImagePlane().getChannel() != ImagePlane.INTERLEAVED) return false;
				}
				return true;
			}
		}
		return false;
	}

}
