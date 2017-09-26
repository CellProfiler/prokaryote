package org.cellprofiler.imageset.filter;

import org.cellprofiler.imageset.ImageFile;


/**
 * @author Lee Kamentsky
 * 
 * A predicate that determines whether a file name has an extension determined 
 * by the subexpression that follows.
 *
 */
public class ExtensionPredicate extends AbstractURLPredicateBase {
	final static public String SYMBOL = "extension";
	
	public String getSymbol() {
		return SYMBOL;
	}

	public boolean eval(ImageFile candidateFile) {
		String candidate = candidateFile.getFileName();
		if (candidate == null) return false;
		int index = candidate.length();
		while (index > 0) {
			index = candidate.lastIndexOf(".", index-1);
			if (index < 0) break;
			if (subpredicate.eval(candidate.substring(index+1))) return true;
		}
		return false;
	}
}
