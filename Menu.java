package Final_1;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import javax.swing.JMenuBar;
import javax.swing.JFrame;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.awt.*;

public class Menu extends JFrame implements Runnable {
	public static Main mainobj = new Main();
	public static LiveSpeechRecognizer speech  = mainobj.speech;;
	JPanel p_1 = new JPanel();
	JPanel p_2 = new JPanel();
	JPanel p_3 = new JPanel();
	JLabel title;
	JLabel x;
    JTextField xname;
    JLabel y;
    JTextField yname;
    JButton sub;
	JDesktopPane jdpDesktop;
	static int openFrameCount = 0;
	public Menu() {
		super("Menu");
		// Make the main window positioned as 50 pixels from each edge of the
		// screen.
		int inset = 50;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, screenSize.width - inset * 2,
				screenSize.height - inset * 2);
		// Add a Window Exit Listener
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// Create and Set up the GUI.
		jdpDesktop = new JDesktopPane();
		// A specialized layered pane to be used with JInternalFrames
//		createFrame("r"); // Create first window
		setContentPane(jdpDesktop);
		setJMenuBar(createMenuBar());
		// Make dragging faster by setting drag mode to Outline
		jdpDesktop.putClientProperty("JDesktopPane.dragMode", "outline");
	}
	protected JMenuBar createMenuBar() {
				
		JMenuBar mb = new JMenuBar();;
		JMenu help,rect,circle,tri,nf;
		JMenuItem h,r,c,t,n;
		
		help = new JMenu("Help");
		rect = new JMenu("Rectangle");
		circle = new JMenu("Circle");
		tri = new JMenu("Triangle");
		nf = new JMenu("Frame");
		
		h = new JMenuItem("commands");
		r = new JMenuItem("Make a rectangle");
		c = new JMenuItem("Make a Circle");
		t = new JMenuItem("Make a Triangle");
		n =  new JMenuItem("New Frame");

		// add menu items to menu
		help.add(h);rect.add(r);circle.add(c);tri.add(t);nf.add(n);
		mb.add(rect);mb.add(circle);mb.add(tri);mb.add(help);mb.add(nf);

		n.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createFrame("n");
			}
		});
		
		r.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createFrame("r");
			}
		});
		
		c.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createFrame("c");
			}
		});
		
		t.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createFrame("t");
			}
		});
		
		h.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File url = new File("C:\\Users\\admin\\Desktop\\HLLL.txt");
				try {
					Desktop.getDesktop().edit(url);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		return mb;
		
		
		
		
	}
	 void createFrame(String s) {
//		JPanel p_1 = new JPanel();
//		JPanel p_2 = new JPanel();
//		JPanel p_3 = new JPanel();
//		JLabel title;
//		JLabel x;
//        JTextField xname;
//        JLabel y;
//        JTextField yname;
//        JButton sub;
        Container c = getContentPane();
        c.setLayout(null);
        
		MyInternalFrame frame = new MyInternalFrame();
		frame.setVisible(true);
		if(s.equals("r")) {
			title = new JLabel("Rectangle");
				        
	        x = new JLabel("x(r) ");
	        x.setFont(new Font("Arial", Font.PLAIN, 20));
	        x.setSize(100, 20);
	        p_1.add(x);
        
	        xname = new JTextField(10);
	        xname.setFont(new Font("Arial", Font.PLAIN, 15));
	        xname.setSize(100, 20);
	        p_1.add(xname);
	        
	        
	        y = new JLabel("y(r) ");
	        y.setFont(new Font("Arial", Font.PLAIN, 20));
	        p_2.add(y);
	        
	        yname = new JTextField(10);
	        yname.setFont(new Font("Arial", Font.PLAIN, 15));
	        p_2.add(yname);
 
	        sub = new JButton("Submit");
	        sub.setFont(new Font("Arial", Font.PLAIN, 15));
	        p_3.add(sub);
	        
	        sub.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 
				    HashMap<String,String> hm = new HashMap<>();
			    	
			        hm.put("FIFTY","50");
			        hm.put("ONE HUNDRED", "100");
			        hm.put("TWO HUNDRED", "200");
					
				
					speech.startRecognition(true);
					
					SpeechResult speechResult = null;
					String x_coordinate = "";
					String y_coordinate = "";
					mainobj.speak("Enter X CO-ORDINATE");
					while ((speechResult = speech.getResult()) != null) {
						String voiceCommand = speechResult.getHypothesis();
						System.out.println("Voice Command is " + voiceCommand);
						if(voiceCommand.equalsIgnoreCase("SUBMIT")) {
//							xname.setText(hm.get(x_coordinate));
							break;
						}
						x_coordinate = voiceCommand;
						xname.setText(hm.get(x_coordinate));
						
					}
					
					
					mainobj.speak("Enter Y CO-ORDINATE");
					while ((speechResult = speech.getResult()) != null) {
						String voiceCommand = speechResult.getHypothesis();
						System.out.println("Voice Command is " + voiceCommand);
						if(voiceCommand.equalsIgnoreCase("SUBMIT")) {
//							yname.setText(hm.get(y_coordinate));
							break;
						}
						y_coordinate = voiceCommand;
						yname.setText(hm.get(y_coordinate));

					}
					
					speech.stopRecognition();
					
				}
			});
	        
	        frame.add(p_1,BorderLayout.NORTH);
	        frame.add(p_2,BorderLayout.CENTER);
	        frame.add(p_3,BorderLayout.SOUTH);
	        
	       
	       
	       
	        
 
    		open("r",xname.getText(),yname.getText());
    		
	       	        
		}
		if(s.equals("c")) {
			    x = new JLabel("x(c) ");
		        x.setFont(new Font("Arial", Font.PLAIN, 20));
		        x.setSize(100, 20);
//		        x.setLocation(100, 100);
		        p_1.add(x);
//		        frame.add(x);
//		        
		        xname = new JTextField(10);
		        xname.setFont(new Font("Arial", Font.PLAIN, 15));
		        xname.setSize(100, 20);
//		        xname.setLocation(200, 100);
//		        xname.setText("100");
		        p_1.add(xname);
		        
//		        frame.add(xname);
//		        
		        y = new JLabel("y(c) ");
		        y.setFont(new Font("Arial", Font.PLAIN, 20));
//		        y.setSize(100, 20);
//		        y.setLocation(100, 100+50);
		        p_2.add(y);
//		        frame.add(y);
		        
		        yname = new JTextField(10);
		        yname.setFont(new Font("Arial", Font.PLAIN, 15));
//		        yname.setSize(190, 20);
//		        yname.setLocation(200, 100+50);
		        p_2.add(yname);
//		        frame.add(yname);
//		        
//		        
		        sub = new JButton("Submit");
		        sub.setFont(new Font("Arial", Font.PLAIN, 15));
//		        sub.setSize(50, 50);
////		        sub.setLocation(150, 450);
//		        frame.add(sub);
		        p_3.add(sub);
		        
		        
		}
		if(s.equals("t")) {
			
		}

		
//        frame.add(p_1,BorderLayout.NORTH);
//        frame.add(p_2,BorderLayout.CENTER);
//        frame.add(p_3,BorderLayout.SOUTH);
       
        
     
		jdpDesktop.add(frame);
		try {
			frame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
		}
		
		
//		AddDetails();
	}
	 
	 
	 
	 
	 
	 
	 
	
	
private void open(String string, String x1, String y1) {
		// TODO Auto-generated method stub
		String x = "\""+ x1+ "\"";
		String y =  "\""+ y1+ "\"";
//	
//
//	System.out.println("x = "+xname.getText()+" y = "+yname.getText()+ " \n Closing!!!");
	String rec_code = "    <svg width=\"500\" height=\"500\"> <rect x=" + x + " y= "+y+" width= "+"\"150\""+" height="+"\"150\" "+"style=\"fill:blue;stroke:pink;stroke-width:5;fill-opacity:0.1;stroke-opacity:0.9\" /></svg>";
//	System.out.print(rec_code);
	
	
	
	File file = new File("C:\\Users\\admin\\eclipse-workspace\\Demo\\hello.html");
	BufferedWriter bw = null;
	try {
		bw = new BufferedWriter(new FileWriter(file));
	} catch (IOException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	try {
		bw.write("<html><head><title>New Page</title></head><body><p>This is Body</p>"+ rec_code  +"</body></html>");
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {
		bw.close();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	
	String url = "C:\\Users\\admin\\eclipse-workspace\\Demo\\hello.html";
	File f = new File(url);
	try {
		Desktop.getDesktop().browse(f.toURI());
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
		
	}









//	public static void main(String[] args) {
//		Menu frame = new Menu();
//		frame.setVisible(true);
//	}
	class MyInternalFrame extends JInternalFrame {

		static final int xPosition = 30, yPosition = 30;
		public MyInternalFrame() {
			super("IFrame #" + (++openFrameCount), true, // resizable
					true, // closable
					true, // maximizable
					true);// iconifiable
			setSize(400, 400);
			// Set the window's location.
			setLocation(xPosition * openFrameCount, yPosition
					* openFrameCount);
		}
	}









	@Override
	public void run() {
		// TODO Auto-generated method stub
        HashMap<String,String> hm = new HashMap<>();
    	
        hm.put("FIFTY","50");
        hm.put("ONE HUNDRED", "100");
        hm.put("TWO HUNDRED", "200");
		
	
		speech.startRecognition(true);
		
		SpeechResult speechResult = null;
		String x_coordinate = "";
		String y_coordinate = "";
		mainobj.speak("Enter X CO-ORDINATE");
		while ((speechResult = speech.getResult()) != null) {
			String voiceCommand = speechResult.getHypothesis();
			System.out.println("Voice Command is " + voiceCommand);
			if(voiceCommand.equalsIgnoreCase("SUBMIT")) {
//				xname.setText(hm.get(x_coordinate));
				break;
			}
			x_coordinate = voiceCommand;
			xname.setText(hm.get(x_coordinate));
			
		}
		
		
		mainobj.speak("Enter Y CO-ORDINATE");
		while ((speechResult = speech.getResult()) != null) {
			String voiceCommand = speechResult.getHypothesis();
			System.out.println("Voice Command is " + voiceCommand);
			if(voiceCommand.equalsIgnoreCase("SUBMIT")) {
//				yname.setText(hm.get(y_coordinate));
				break;
			}
			y_coordinate = voiceCommand;
			yname.setText(hm.get(y_coordinate));

		}
		
		speech.stopRecognition();
		
	}
}