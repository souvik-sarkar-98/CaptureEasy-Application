package app.techy10souvik.captureeasy.core;

import java.io.IOException;

import javax.swing.SwingUtilities;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;


import app.techy10souvik.captureeasy.common.ui.AlertPopup;
import app.techy10souvik.captureeasy.common.util.PropertyUtil;
import app.techy10souvik.captureeasy.common.util.SystemUtil;
import app.techy10souvik.captureeasy.core.ui.ControlWindow;
import app.techy10souvik.captureeasy.core.ui.components.SplashScreenComponent;

/**
 * @author Souvik Sarkar
 * @createdOn 03-Jun-2022
 * @purpose
 */
public class CaptureEasy implements App {
	{
		System.setProperty("log.home", SystemUtil.getLogFolder());
	}
	
	private final Logger log = LogManager.getLogger(this.getClass());
	///private String[] args;
	private SplashScreenComponent splash;

	/**
	 * @param args
	 * @throws ConfigurationException
	 */
	public CaptureEasy(String[] args) throws ConfigurationException {
		log.info("** Starting application **");
		//this.args = args;
		this.splash=new SplashScreenComponent();
	}

	public void init() throws Exception {
		splash.start();
		splash.setMessage("Initializing default configurations...");
 		Thread.sleep(500);
		splash.setVersion(PropertyUtil.init().getAppVersion());
	}

	public void launch() throws Exception {
		log.info("** Launching GUI **");
		splash.setMessage("Initializing user interface...");
 		Thread.sleep(500);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ControlWindow.init().registerActions().show();
				} catch (Exception e) {
					log.error("** Failed to Launch GUI **",e);
				}
			}
		});
 		splash.setMessage("Initializing Native Key Listener");
 		Thread.sleep(500);
       // GlobalScreen.addNativeKeyListener(new KeypressListener());
       // GlobalScreen.registerNativeHook();
 		splash.setMessage("Launching Application");
 		Thread.sleep(500);
		splash.stop();
	}

	public void handleError() {
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			public void uncaughtException(Thread t, Throwable e) {
		 		splash.setMessage(e.getClass().getSimpleName()+" : "+e.getMessage());
		 		AlertPopup.init().type(AlertPopup.ERROR).message(e.getClass().getSimpleName()+" : "+e.getMessage()).button2("Okay");
				e.printStackTrace();
			}
		});
		
	}

}
