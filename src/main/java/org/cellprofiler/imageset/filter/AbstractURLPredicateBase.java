package org.cellprofiler.imageset.filter;

import org.cellprofiler.imageset.ImageFile;
import org.cellprofiler.imageset.filter.Filter.BadFilterExpressionException;

import java.util.List;

abstract public class AbstractURLPredicateBase  implements
	FilterPredicate<ImageFile, String> {

	protected FilterPredicate<String, ?> subpredicate;

	public AbstractURLPredicateBase() {
		super();
	}

	public void setSubpredicates(List<FilterPredicate<String, ?>> subpredicates)
			throws BadFilterExpressionException {
				if (subpredicates.size() != 1) {
					throw new BadFilterExpressionException(String.format("The %s predicate takes a single subpredicate", getSymbol()));
				}
				subpredicate = subpredicates.get(0);
			}

	public void setLiteral(String literal) throws BadFilterExpressionException {
		throw new BadFilterExpressionException(String.format("The %s predicate does not take a literal", getSymbol()));
	}

	public Class<ImageFile> getInputClass() {
		return ImageFile.class;
	}

	public Class<String> getOutputClass() {
		return String.class;
	}

}