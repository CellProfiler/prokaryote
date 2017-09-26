package org.cellprofiler.imageset.filter;

import org.cellprofiler.imageset.ImageFile;
import org.cellprofiler.imageset.filter.Filter.BadFilterExpressionException;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestFileNamePredicate {
	static class Expects extends AbstractStringPredicate {
		private final String expected;
		Expects(String expected) {
			this.expected = expected;
		}

		public String getSymbol() {
			return null;
		}

		public void setSubpredicates(List<FilterPredicate<String, ?>> subpredicates) throws BadFilterExpressionException {
		}

		public Class<String> getOutputClass() {
			return String.class;
		}

		@Override
		protected boolean eval(String candidate, String literal) {
			assertEquals(expected, candidate);
			return false;
		}
		static List<FilterPredicate<String, ?>> expects(String name) {
			ArrayList<FilterPredicate<String, ?>> result = new ArrayList<FilterPredicate<String, ?>>();
			result.add(new Expects(name));
			return result;
		}
	};

	@Test
	public void testEvalFile() {
		FileNamePredicate pred = new FileNamePredicate();
		try {
			pred.setSubpredicates(Expects.expects("foo.jpg"));
			ImageFile imgfile = new ImageFile(new URI("file:///imaging/analysis/foo.jpg"));
			pred.eval(imgfile);
		} catch (BadFilterExpressionException e) {
			fail("File predicate takes a subpredicate");
		} catch (URISyntaxException e) {
			fail();
		}
	}
	@Test
	public void testEvalHTTP() {
		FileNamePredicate pred = new FileNamePredicate();
		try {
			pred.setSubpredicates(Expects.expects("bar.jpg"));
			ImageFile imgfile = new ImageFile(new URI("http://www.cellprofiler.org/linked_files/bar.jpg"));
			pred.eval(imgfile);
		} catch (BadFilterExpressionException e) {
			fail("File predicate takes a subpredicate");
		} catch (URISyntaxException e) {
			fail();
		}
	}
	@Test
	public void testEvalOMERO() {
		FileNamePredicate pred = new FileNamePredicate();
		try {
			pred.setSubpredicates(Expects.expects("iid=12345"));
			ImageFile imgfile = new ImageFile(new URI("omero:iid=12345"));
			pred.eval(imgfile);
		} catch (BadFilterExpressionException e) {
			fail("File predicate takes a subpredicate");
		} catch (URISyntaxException e) {
			fail();
		}
		
	}
		
}
