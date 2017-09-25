/**
 * CellProfiler is distributed under the GNU General Public License.
 * See the accompanying file LICENSE for details.
 *
 * Copyright (c) 2017 Glencoe Software, Inc.
 * All rights reserved.
 *
 * Please see the AUTHORS file for credits.
 *
 * Website: http://www.cellprofiler.org
 */
package org.cellprofiler.ome;

import org.junit.Test;

import loci.formats.FormatException;
import loci.formats.ImageReader;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Tests that Bio-Formats is available.
 * @author Chris Allan <callan@glencoesoftware.com>
 *
 */
public class TestBioFormats {

    @Test
    public void testImageReaderInstantiation()
            throws URISyntaxException, FormatException, IOException {
        URL resource = this.getClass().getClassLoader().getResource(
                "org/cellprofiler/imageset/omexml.xml");
        Path path = Paths.get(resource.toURI());

        try (ImageReader reader = new ImageReader()) {
            reader.setId(path.toString());
            assertEquals(4, reader.getSeriesCount());
        }
    }

}
