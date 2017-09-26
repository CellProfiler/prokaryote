package org.cellprofiler.imageset.filter;

import org.cellprofiler.imageset.ImagePlaneDetailsStack;

/**
 * @author Lee Kamentsky
 * 
 * A predicate that determines whether an ImagePlaneDetailsStack
 * has just a single image plane.
 * 
 */
public class IsStackFramePredicate extends
		FilterPredicateInverter<ImagePlaneDetailsStack, Object> {
	final static public String SYMBOL="isstackframe";

	public IsStackFramePredicate() {
		super(new IsStackPredicate(), SYMBOL);
	}
}
