import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class AutoGobbler {
	private static Robot robot;
	private static Random random;
	
	public static void main(String[] args) throws NativeHookException, AWTException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		LogManager.getLogManager().reset();
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		GlobalScreen.registerNativeHook();
		robot = new Robot();
		random = new Random();
		GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
			
			private Timer t;

			@Override
			public void nativeKeyTyped(NativeKeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void nativeKeyReleased(NativeKeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void nativeKeyPressed(NativeKeyEvent arg0) {
				if(arg0.getKeyCode() == 66){
					if(t == null){
						t = new Timer();
						t.scheduleAtFixedRate(new ClickTask(), 1, random.nextInt(500) + 5050);
					}else{
						t.cancel();
						t = null;
					}
				}
			}
		});
		JFrame frame = new JFrame("AutoGobbler");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(200, 200, 550, 130);
		JLabel label = new JLabel("Hold your cursor above the Gobbler and press F8 to start AutoGobbling");
		frame.add(label);
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		img.setRGB(0, 0, new Color(255, 255, 255).getRGB());
		frame.setIconImage(img);
		frame.setVisible(true);
	}
	
	static class ClickTask extends TimerTask{

		@Override
		public void run() {
			
			try {
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				Thread.sleep(random.nextInt(50) + 40);
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
