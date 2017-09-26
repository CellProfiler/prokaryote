package org.cellprofiler.imageset.filter;

import loci.formats.ImageReader;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Lee Kamentsky
 *
 */
public class IsImagePredicate extends AbstractTerminalPredicate<String> {
	final static public String SYMBOL = "isimage";
	final static HashSet<String> disallowedSuffixes = new HashSet<String>(Arrays.asList(
			new String [] { "cfg", "csv", "eps", "epsi", "htm", "html", "inf", 
						    "log", "ps", "txt", "wav", "xml", "zip" }));
	private static Set<String> cachedImageSuffixes;
	
	/**
	 * @return the suffixes that this predicate considers to be
	 *         suffixes of image files.
	 */
	static public Set<String> getImageSuffixes() {
		synchronized(disallowedSuffixes) {
			if (cachedImageSuffixes == null) {
				cachedImageSuffixes = new HashSet<String>(Arrays.asList(
						new ImageReader().getSuffixes()));
				cachedImageSuffixes.removeAll(disallowedSuffixes);
			}
		}
		return cachedImageSuffixes;
	}
	public IsImagePredicate() {
		super(String.class);
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
	public boolean eval(String candidate) {
		return getImageSuffixes().contains(candidate.toLowerCase());
	}

}
