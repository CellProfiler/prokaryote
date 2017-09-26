package org.cellprofiler.imageset.filter;

import org.cellprofiler.imageset.ImagePlaneDetails;
import org.cellprofiler.imageset.ImagePlaneDetailsStack;

/**
 * @author Lee Kamentsky
 *
 */
public class TokenParserStackAdapter<T> extends StackAdapter<T> implements
		TokenParser<ImagePlaneDetailsStack, T> {
	TokenParser<ImagePlaneDetails, T> tp;
	static public <T> StackAdapter<T> makeAdapter(TokenParser<ImagePlaneDetails, T> p) {
		Class<T> klass = p.getOutputClass();
		TokenParserStackAdapter<T> adapter = new TokenParserStackAdapter<T>(klass);
		adapter.planeFilterPredicate = p;
		adapter.tp = p;
		return adapter;
	}
	protected TokenParserStackAdapter(Class<T> klass) {
		super(klass);
	}
	public FilterPredicate<T, ?> parse(String token) {
		return tp.parse(token);
	}

}
