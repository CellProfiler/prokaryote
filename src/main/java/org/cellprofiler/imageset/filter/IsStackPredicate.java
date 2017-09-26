package org.cellprofiler.imageset.filter;

import org.cellprofiler.imageset.ImagePlaneDetailsStack;

/**
 * @author Lee Kamentsky
 * 
 * A predicate that determines whether a stack
 * has a single frame or has multiple ones
 * 
 */
public class IsStackPredicate extends
		AbstractTerminalPredicate<ImagePlaneDetailsStack> {
	final static public String SYMBOL="isstack";

	protected IsStackPredicate() {
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
		for (int i=0; i<candidate.numDimensions(); i++) {
			if (candidate.size(i) > 1) return true;
		}
		return false;
	}
}
