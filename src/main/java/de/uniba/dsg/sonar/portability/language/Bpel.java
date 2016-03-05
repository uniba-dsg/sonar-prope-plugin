
package de.uniba.dsg.sonar.portability.language;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.sonar.api.config.Settings;
import org.sonar.api.resources.AbstractLanguage;

import com.google.common.collect.Lists;

import de.uniba.dsg.sonar.portability.PortabilityPlugin;

/**
 * This class defines the Bpel language. This class is oriented on the XML
 * Plugin
 */
public class Bpel extends AbstractLanguage {

	/** All the valid bpel files suffixes. */
	private static final String[] DEFAULT_SUFFIXES = { ".bpel" };

	/** The bpel language key. */
	public static final String KEY = "bpel";

	/** The bpel language name */
	private static final String BPEL_LANGUAGE_NAME = "BPEL";

	private Settings settings;

	/**
	 * Default constructor.
	 */
	public Bpel(Settings settings) {
		super(KEY, BPEL_LANGUAGE_NAME);
		this.settings = settings;
	}

	/**
	 * {@inheritDoc}
	 */
	public String[] getFileSuffixes() {
		String[] suffixes = filterEmptyStrings(settings.getStringArray(PortabilityPlugin.FILE_SUFFIXES_KEY));
		if (suffixes.length == 0) {
			suffixes = Bpel.DEFAULT_SUFFIXES;
		}
		return suffixes;
	}

	private String[] filterEmptyStrings(String[] stringArray) {
		List<String> nonEmptyStrings = Lists.newArrayList();
		for (String string : stringArray) {
			if (StringUtils.isNotBlank(string.trim())) {
				nonEmptyStrings.add(string.trim());
			}
		}
		return nonEmptyStrings.toArray(new String[nonEmptyStrings.size()]);
	}

}
