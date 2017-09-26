package org.cellprofiler.preferences;

import java.util.prefs.Preferences;
import java.util.prefs.PreferencesFactory;

/**
 * @author Lee Kamentsky
 *
 */
public class CellProfilerPreferencesFactory implements PreferencesFactory {

	/* (non-Javadoc)
	 * @see java.util.prefs.PreferencesFactory#systemRoot()
	 */
	public Preferences systemRoot() {
		return CellProfilerPreferences.getSystemRoot();
	}

	/* (non-Javadoc)
	 * @see java.util.prefs.PreferencesFactory#userRoot()
	 */
	public Preferences userRoot() {
		return CellProfilerPreferences.getUserRoot();
	}

}
