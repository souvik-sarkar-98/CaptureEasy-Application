package captureEasy.UI;

import java.awt.*; 
import javax.swing.*; 

public class ToastMsg extends JFrame { 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String s; 
	JWindow w; 
	public ToastMsg(String s, int x, int y) 
	{ 
		w = new JWindow(); 
		w.setBackground(new Color(0, 0, 0, 0)); 
		w.setAlwaysOnTop(true);
		JPanel p = new JPanel() { 
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) 
			{ 
				int wid = g.getFontMetrics().stringWidth(s); 
				int hei = g.getFontMetrics().getHeight(); 
				g.setColor(Color.BLACK); 
				g.fillRect(10, 10, wid + 45, hei + 15); 
				g.setColor(Color.BLACK); 
				g.drawRect(10, 10, wid + 45, hei + 15); 
				g.setFont(new Font("Tahoma", Font.BOLD, 18));
				g.setColor(new Color(255, 255, 255, 240)); 
				int t = 250; 
				g.drawString(s, 20, 32); 
				for (int i = 0; i < 4; i++) { 
					t -= 60; 
					g.setColor(new Color(0, 0, 0, t)); 
					g.drawRect(10 - i, 10 - i, wid + 45 + i * 2, hei + 15 + i * 2); 
				} 
			} 
		}; 	
		w.add(p); 
		w.setLocation(x, y); 
		w.setSize(400, 100); 
	} 
	public void terminationLogic() throws InterruptedException
	{
		Thread.sleep(2000);
	}
	// function to pop up the toast 
	public void showToast() 
	{ 
		new Thread(new Runnable(){
			@Override
			public void run() {
				try { 
					w.setOpacity(1); 
					w.setVisible(true);
					terminationLogic();
					/*if (terID==0)
					{
						
					}
					else if(terID==1)
					else if(terID==2)
					{
					}
					else
					{
					}*/
					for (double d = 1.0; d > 0.2; d -= 0.1) { 
						try {Thread.sleep(100);} catch (InterruptedException e) {} 
						w.setOpacity((float)d); 
					} 
					w.setVisible(false); 
					w.dispose();
				} 
				catch (Exception e) { 
					System.out.println(e.getMessage()); 
				} 
				
			}
			
		}).start();
	} 
} 