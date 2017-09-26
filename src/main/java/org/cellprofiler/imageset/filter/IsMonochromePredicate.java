package org.cellprofiler.imageset.filter;

import org.cellprofiler.imageset.ImagePlaneDetailsStack;

/**
 * @author Lee Kamentsky
 * 
 * A predicate that determines whether an image plane
 * is a color image.
 *
 */
public class IsMonochromePredicate extends
		AbstractTerminalPredicate<ImagePlaneDetailsStack> {
	final static public String SYMBOL="ismonochrome";
	final static private AbstractTerminalPredicate<ImagePlaneDetailsStack> inversePredicate = 
		new IsColorPredicate();

	protected IsMonochromePredicate() {
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
		return ! inversePredicate.eval(candidate);
	}

}
