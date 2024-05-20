package app.techy10souvik.captureeasy.core.controller;

import app.techy10souvik.captureeasy.common.util.PropertyUtil;

/**
 * @author Souvik Sarkar
 * @createdOn 10-Sep-2022
 * @purpose 
 */
public class ControlWindowController {

	private PropertyUtil propertyUtil;

	/**
	 * @throws Exception 
	 * 
	 */
	public ControlWindowController() throws Exception {
		this.propertyUtil=PropertyUtil.init();
	}
	
	public void closeApplication() {
		System.exit(0);
	}
	
	public void deleteScreenshots() throws Exception {
		propertyUtil.getTempPath();
	}
	
	public void saveDocuments(String extension) {
		System.exit(0);
	}

}
