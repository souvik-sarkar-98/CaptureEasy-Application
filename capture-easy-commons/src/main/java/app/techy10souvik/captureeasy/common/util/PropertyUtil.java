package app.techy10souvik.captureeasy.common.util;

import java.awt.Point;
import java.io.IOException;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.RandomStringUtils;

/**
 * @author Souvik Sarkar
 * @createdOn 02-Jul-2022
 * @purpose
 */
public class PropertyUtil {
	private static PropertiesConfiguration property;

	public PropertyUtil() throws ConfigurationException, IOException {
		if (property == null) {
			property = new PropertiesConfiguration(SystemUtil.getPropFile());
			property.setAutoSave(true);
		}
	}

	public static PropertyUtil init() throws Exception {
		return new PropertyUtil();
	}

	public String getDocumentPath() {
		return property.getString("DocPath", SystemUtil.getDocumentFolder());
	}

	public String getAppVersion() {
		return property.getString("AppVersion", "-");
	}

	public Point getGUILocation() {
		int dXLoc = SystemUtil.getScreenSize().width - 160;
		int dYLoc = SystemUtil.getScreenSize().height / 2 + 100;
		return new Point(property.getInteger("Xlocation", dXLoc), property.getInteger("Ylocation", dYLoc));
	}

	public String getAppLockKey() {
		if (property.getString("AppLockKey") == null) {
			property.setProperty("AppLockKey", RandomStringUtils.random(20, true, false));
		}
		return property.getString("AppLockKey");
	}

	public String getTempPath() throws Exception {
		return property.getString("TempPath");
	}
	
	public void setTempPath(String path) throws Exception {
		property.setProperty("TempPath",path);
	}

}
