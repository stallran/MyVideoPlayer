

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class MainWindow extends JFrame {

    private static ServerSocket ss;
    private static Socket s;
    private static BufferedReader br;

	private JPanel contentPane;
	EmbeddedMediaPlayerComponent  playerComponent;

	/**
	 * Launch the application.
	 */

		
		 static  MainWindow frame;
		    static  int flag;
			public static void main(String[] args) {
				NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "D:\\developTools\\vlc\\install\\VLC");
				Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							 frame = new MainWindow();
							 frame.setUndecorated(true);
							Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();	// 取得屏幕大小
							Rectangle bounds = new Rectangle( screenSize );

							frame.setBounds( bounds );
							 frame.setVisible(true);
							 ss = new ServerSocket(8001);
							 System.out.println("Server is starting...");
							while(true){
								    
								    frame.getMediaPlayer().setRepeat(true);
								    frame.getMediaPlayer().playMedia("C:\\Users\\Administrator\\Desktop\\v.mp4");
					                s = ss.accept();
					                System.out.println("-----------accept------------");
					                br = new BufferedReader(new InputStreamReader(s.getInputStream()));
					                String line = br.readLine();
					                System.out.println(line);
					                br.close();
							 if( line.equals("kaiqi")){
							   frame.getMediaPlayer().playMedia("C:\\Users\\Administrator\\Desktop\\v.mp4");
							   Thread.sleep(5000);
							   System.out.println("-----------sleep------------");
							    }
							
							 
							}
							 
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			
			}


	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));//5
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel videopanel = new JPanel();
		videopanel.setSize(800, 600);
		contentPane.add(videopanel, BorderLayout.CENTER);
		videopanel.setLayout(new BorderLayout(0, 0));
		
		playerComponent = new EmbeddedMediaPlayerComponent();
		playerComponent.setCursorEnabled(false);
		

		videopanel.add(playerComponent);
		
	}
	public EmbeddedMediaPlayer getMediaPlayer(){
		
		return playerComponent.getMediaPlayer();
	}

}
