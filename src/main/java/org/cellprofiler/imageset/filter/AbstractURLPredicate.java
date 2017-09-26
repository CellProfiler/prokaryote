package org.cellprofiler.imageset.filter;


import org.cellprofiler.imageset.ImageFile;

/**
 * @author Lee Kamentsky
 *
 */
public abstract class AbstractURLPredicate extends AbstractURLPredicateBase {
	/* (non-Javadoc)
	 * @see org.cellprofiler.imageset.filter.FilterPredicate#eval(java.lang.Object)
	 */
	public boolean eval(ImageFile candidate) {
		String value = getValue(candidate);
		if (value == null) return false;
		return subpredicate.eval(value);
	}

	protected abstract String getValue(ImageFile candidate);

}
