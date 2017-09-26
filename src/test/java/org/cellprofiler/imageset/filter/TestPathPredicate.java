package org.cellprofiler.imageset.filter;

import org.cellprofiler.imageset.ImageFile;
import org.cellprofiler.imageset.filter.Filter.BadFilterExpressionException;
import org.cellprofiler.imageset.filter.TestFileNamePredicate.Expects;
import org.junit.Test;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

/**
 * @author Lee Kamentsky
 *
 */
public class TestPathPredicate {

	@Test
	public void testEvalFile() {
		PathPredicate pred = new PathPredicate();
		File root = new File(System.getProperty("user.home"));
		File fileAtPath = new File(root, "foo.jpg");
		String expectedPath = root.getAbsolutePath();
		try {
			pred.setSubpredicates(Expects.expects(expectedPath));
			ImageFile imgfile = new ImageFile(fileAtPath.toURI());
			pred.eval(imgfile);
		} catch (BadFilterExpressionException e) {
			fail("Path predicate takes a subpredicate.");
		}
	}
	@Test
	public void testEvalHTTP() {
		PathPredicate pred = new PathPredicate();
		try {
			pred.setSubpredicates(Expects.expects("http://www.cellprofiler.org/linked_files"));
			ImageFile imgfile = new ImageFile(new URI("http://www.cellprofiler.org/linked_files/bar.jpg"));
			pred.eval(imgfile);
		} catch (BadFilterExpressionException e) {
			fail("Path predicate takes a subpredicate.");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
