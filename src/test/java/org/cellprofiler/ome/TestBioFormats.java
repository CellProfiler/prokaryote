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

import static org.junit.Assert.assertNotNull;


/**
 * Tests that Bio-Formats is available.
 * @author Chris Allan <callan@glencoesoftware.com>
 *
 */
public class TestBioFormats {

    @Test
    public void testOnClasspath() throws ClassNotFoundException {
        assertNotNull(Class.forName("loci.formats.ImageReader"));
    }

}
