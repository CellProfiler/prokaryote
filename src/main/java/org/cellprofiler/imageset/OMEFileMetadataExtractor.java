package org.cellprofiler.imageset;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lee Kamentsky
 *
 * Extract file-level items from an ImageFile's OME metadata.
 * 
 */
public class OMEFileMetadataExtractor implements MetadataExtractor<ImageFile> {

	/* (non-Javadoc)
	 * @see org.cellprofiler.imageset.MetadataExtractor#extract(java.lang.Object)
	 */
	public Map<String, String> extract(ImageFile source) {
		// Currently, we extract no file-level data
		// from the OME metadata.
		final Map<String, String> map = new HashMap<String, String>();
		return map;
	}

	/* (non-Javadoc)
	 * @see org.cellprofiler.imageset.MetadataExtractor#getMetadataKeys()
	 */
	public List<String> getMetadataKeys() {
		return Collections.emptyList();
	}

}
