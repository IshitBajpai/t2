package Final;

import java.awt.*;
import javax.swing.*;

import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
public class menu1 extends JFrame implements ActionListener,Runnable {
	// menubar
	
	static JMenuBar mb;
	static JMenu x,y,z,a,b;
	static JMenuItem m1, m2, m3, m4,m5;
	static JLabel l;
	public static Main mainobj = new Main();
	public static LiveSpeechRecognizer speech  = mainobj.speech;;
	private Container c;
    private JLabel vr;
    private static JTextField vr_name;
    private JButton sub;
    private JButton rec;
    
	public menu1() throws IOException
	{
        
       
        
        
        setTitle("Menu");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
		c = getContentPane();
		c.setLayout(null);

		// create a frame
//		f = new JFrame("Menu demo");

		// create a label
//		l = new JLabel("no task ");
		

		// create a menubar
		mb = new JMenuBar();

		// create a menu
		x = new JMenu("Help");
		y = new JMenu("Menu");
		z = new JMenu("Rectangle");
		a = new JMenu("Circle");
		b = new JMenu("Triangle");


		// create menuitems
		m1 = new JMenuItem("commands");
		m2 = new JMenuItem("Open Menu");
		m3 = new JMenuItem("Make a rectangle");
		m4 = new JMenuItem("Make a Circle");
		m5 = new JMenuItem("Make a Triangle");


		// add ActionListener to menuItems
		m1.addActionListener(this);
		m2.addActionListener(this);
		m3.addActionListener(this);
		m4.addActionListener(this);
		m5.addActionListener(this);

//		z.addActionListener(this);
		// add menu items to menu
		x.add(m1);
		x.add(m2);
		z.add(m3);
		a.add(m4);
		b.add(m5);
		

		// add menu to menu bar
		mb.add(x);
		mb.add(y);
		mb.add(z);
		mb.add(a);
		mb.add(b);
		

		// add menubar to frame
		this.setJMenuBar(mb);
		
	
        vr = new JLabel("Input  ");
        vr.setFont(new Font("Arial", Font.PLAIN, 20));
        vr.setSize(100, 50);
        vr.setLocation(200, 200);
        c.add(vr);
        
        
        vr_name = new JTextField();
        vr_name.setFont(new Font("Arial", Font.PLAIN, 15));
        vr_name.setSize(300,50);
        vr_name.setLocation(300, 200);
        vr_name.setText("Speak");
        c.add(vr_name);
        
        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(200, 100);
        sub.setLocation(325, 300);
        sub.addActionListener(this);
        c.add(sub);
        
        
        rec = new JButton("Record");
        rec.setFont(new Font("Arial", Font.PLAIN, 15));
        rec.setSize(100, 50);
        rec.setLocation(300, 450);
        rec.addActionListener(this);
//        c.add(rec);

        this.setVisible(true);        
        inputspeech();
        
//    	SwingUtilities.invokeLater(new Runnable(){
//			public void run() {
//				try {
//					inputspeech();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
//		});
        
        
	}
	public void actionPerformed(ActionEvent e)
	{

		
		String s = e.getActionCommand();
		System.out.println(e.getSource()==m1 );
		if(e.getSource() == m5) {
			this.dispose();
			System.out.println("SIUUUUUUUUUUUUUUU");
			
			
			
		}
		if(e.getSource() == m4) {
			this.dispose();
			System.out.println("SIUUUUUUUUUUUUUUU");
		
			
			
		}
		if(e.getSource()==m1) {
			File url = new File("C:\\Users\\admin\\Desktop\\HLLL.txt");
			try {
				Desktop.getDesktop().edit(url);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource() == sub) {
//			speech.stopRecognition();
			this.dispose();
//			speech.stopRecognition();
			String result = vr_name.getText();
			if(result.equalsIgnoreCase("RECTANGLE")) {
				MyFrame f1 = new MyFrame();
			}
			if(result.equalsIgnoreCase("CIRCLE")) {
				MyFrame f1 = new MyFrame();
			}
		}
		
		if(e.getSource() == m3) {
			this.dispose();
			System.out.println("SIUUUUUUUUUUUUUUU");
			MyFrame frame =  new MyFrame();
		}
		
		if(e.getSource() == rec) {
//			try {
//				inputspeech();
//			} catch (IOException | InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			
		}

		// set the label to the menuItem that is selected
		
	}
	String result() {
		System.out.println("Start Speaking!!");		
		return "view";
	}
	
	
	void inputspeech() throws IOException {
//		Thread.sleep(1000*5);
		        System.out.println("Start Speaking!!");
		

			   	
				mainobj.speak("Start Speaking");
//				Thread T = new Thread(this);
//				T.start();
//				try {
//					T.join();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				
				
				
				
				ArrayList<String> words = new ArrayList<>();
				words.add("");
				words.add("SUBMIT");
				speech.startRecognition(true);
				
				SpeechResult speechResult = null;
				String input = "";
				String voiceCommand = "";
				while ((speechResult = speech.getResult()) != null) {
					System.out.println("Inside Menu!!");
					voiceCommand = speechResult.getHypothesis();
					System.out.println("Voice Command is " + voiceCommand);
					if(voiceCommand.equalsIgnoreCase("SUBMIT") && !words.contains(input)) {
						mainobj.speak("You Entered "+vr_name.getText());
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						speech.stopRecognition();
						break;
					}
					input = voiceCommand;
					vr_name.setText(input);
				
				}		
				
				
				this.dispose();
				if(vr_name.getText().equals("RECTANGLE")) {
					
				}
				if(vr_name.getText().equals("CIRCLE")) {
					
				}

		
		
		
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		speech.startRecognition(true);
		
		SpeechResult speechResult = null;
		String input = "";
		String voiceCommand = "";
		while ((speechResult = speech.getResult()) != null) {
			System.out.println("Inside Menu!!");
			voiceCommand = speechResult.getHypothesis();
			System.out.println("Voice Command is " + voiceCommand);
			if(voiceCommand.equalsIgnoreCase("SUBMIT")) {
				speech.stopRecognition();
				break;
			}
			input = voiceCommand;
			vr_name.setText(input);
								
			
		}
		speech.stopRecognition();
		
	}
	

}
