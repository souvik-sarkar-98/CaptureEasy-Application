package base;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.JDialog;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.Map;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.FileBrowserSheet;
import org.apache.pivot.wtk.Form;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.Sheet;
import org.apache.pivot.wtk.SheetCloseListener;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.Window;
 
public class Sample1 {
	public static void main(final String[] args) throws IOException {
		System.setProperty("java.vm.version", "15.0.2");
		System.out.println(System.getProperty("java.vm.version"));
		//DesktopApplicationContext.main(MyApp.class, args);
		//DesktopApplicationContext.main(MyApp.class, args);
       // new LoginUI();
		Console console=System.console();
		System.out.println(readLine("Enter Username: "));
		System.out.println(readPassword("Enter Password: "));
		
	}
	
	
	private static String readLine(String format, Object... args) throws IOException {
	    if (System.console() != null) {
	        return System.console().readLine(format, args);
	    }
	    System.out.print(String.format(format, args));
	    BufferedReader reader = new BufferedReader(new InputStreamReader(
	            System.in));
	    return reader.readLine();
	}

	private static char[] readPassword(String format, Object... args)
	        throws IOException {
	    if (System.console() != null)
	        return System.console().readPassword(format, args);
	    return readLine(format, args).toCharArray();
	}
 
	public static class MyApp extends Window implements Application,Bindable {
		private final Form form = new Form();
		private final Form.Section section = new Form.Section();
		private final Label label = new Label("Select file to upload...");
		
		private final PushButton btOpenFileDialog = new PushButton();
		private final FileBrowserSheet fileBrowser = new FileBrowserSheet();
 
		public MyApp() {
			compose();
		}
 
		private void compose() {
			setHeight(200);
			//form.setHeight(500);
			setWidth(200);
			section.add(label);
			section.add(new TextInput());
			btOpenFileDialog.setButtonData("Select File..");
			section.add(btOpenFileDialog);
			form.getSections().add(section);
			fileBrowser.setMode(FileBrowserSheet.Mode.SAVE_AS);
			btOpenFileDialog.getButtonPressListeners().add(
					fileDialogDisplayListener);
			this.setContent(form);
			this.setTitle("hasCode.com - Apache Pivot Example 1 - Programmatic construction");
			this.setMaximized(true);
		}
 
		private final ButtonPressListener fileDialogDisplayListener = new ButtonPressListener() {
			@Override
			public void buttonPressed(final Button button) {
				fileBrowser.open(MyApp.this, new SheetCloseListener() {
					@Override
					public void sheetClosed(final Sheet sheet) {
						if (sheet.getResult()) {
							Sequence<File> selectedFiles = fileBrowser
									.getSelectedFiles();
 
							ListView listView = new ListView();
							listView.setListData(new ArrayList<File>(
									selectedFiles));
							listView.setSelectMode(ListView.SelectMode.NONE);
							listView.getStyles().put("backgroundColor", null);
 
							Alert.alert(MessageType.INFO,
									"Files selected for upload:", listView,
									MyApp.this);
						} else {
							Alert.alert(MessageType.INFO,
									"No files selected for upload.", MyApp.this);
						}
					}
				});
			}
		};
 
		@Override
		public void startup(final Display display,
				final Map<String, String> properties) throws Exception {
			this.open(display);
//			Alert.alert(MessageType.INFO,
//					"No files selected for upload.", MyApp.this);
		}
 
		@Override
		public boolean shutdown(final boolean optional) throws Exception {
			this.close();
			return false;
		}
 
		@Override
		public void suspend() throws Exception {
		}
 
		@Override
		public void resume() throws Exception {
		}

		@Override
		public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
			
		}
	}
}