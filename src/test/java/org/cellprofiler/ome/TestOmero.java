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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Tests that OMERO is available.
 * @author Chris Allan <callan@glencoesoftware.com>
 *
 */
public class TestOmero {

    @Test
    public void testOnClasspath() throws ClassNotFoundException {
        assertNotNull(Class.forName("omero.client"));
    }

    @Test
    public void testOmeroReaderOnClassPath() throws ClassNotFoundException {
        assertNotNull(Class.forName("loci.ome.io.OmeroReader"));
    }

    @Test
    public void testOmeroRtypeUsage()
            throws ClassNotFoundException, InstantiationException,
                IllegalAccessException, NoSuchMethodException,
                SecurityException, IllegalArgumentException,
                InvocationTargetException {
        Class<?> klass = Class.forName("omero.rtypes");
        assertNotNull(klass);
        Method method = klass.getMethod("rtype", Object.class);
        assertNotNull(method);
        Object object = method.invoke(null, 1);
        assertNotNull(object);

        method = klass.getMethod("unwrap", omero.RType.class);
        assertNotNull(method);
        object = method.invoke(null, object);
        assertNotNull(object);
        assertEquals(1, object);
    }

}
