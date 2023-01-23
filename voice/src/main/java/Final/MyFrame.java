package Final;

import javax.swing.*;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.awt.Window.*;

public class MyFrame extends JFrame implements ActionListener,Runnable{
   

	private Container c;
    private JLabel title;
    private JLabel x;
    private JTextField xname;
    private JLabel y;
    private JTextField yname;
    private JButton sub;
    private JButton reset;
	public static Main mainobj;
	public static LiveSpeechRecognizer speech;
	
	static JMenuBar mb;
	static JMenu help,Menu,Rect,a,b;
	static JMenuItem m1, m2, m3, m4, r2,m5;
 

    
    
    
	
    public MyFrame() 
    {
        mainobj  = new Main();
        speech = mainobj.speech;
        setTitle("Rectangle Form");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
 
        c = getContentPane();
        c.setLayout(null);
 
        title = new JLabel("Choose Co-ordinates");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(0+10, 0);
        c.add(title);
        
        
        x = new JLabel("x");
        x.setFont(new Font("Arial", Font.PLAIN, 20));
        x.setSize(100, 20);
        x.setLocation(100, 100);
        c.add(x);
        
        xname = new JTextField();
        xname.setFont(new Font("Arial", Font.PLAIN, 15));
        xname.setSize(190, 20);
        xname.setLocation(200, 100);
        xname.setText("100");
        c.add(xname);
        
        y = new JLabel("y");
        y.setFont(new Font("Arial", Font.PLAIN, 20));
        y.setSize(100, 20);
        y.setLocation(100, 100+50);
        c.add(y);
        
        yname = new JTextField();
        yname.setFont(new Font("Arial", Font.PLAIN, 15));
        yname.setSize(190, 20);
        yname.setLocation(200, 100+50);
        c.add(yname);
        
        
        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 50);
        sub.setLocation(150, 450);
        sub.addActionListener(this);
        c.add(sub);
// 
        reset = new JButton("Record");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 50);
        reset.setLocation(270, 450);
        reset.addActionListener(this);
        c.add(reset);
        
       
    	mb = new JMenuBar();

		// create a menu
		help = new JMenu("Help");
		Menu = new JMenu("Menu");
		Rect = new JMenu("Rectangle");
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

		Rect.addActionListener(this);
		// add menu items to menu
		help.add(m1);
		Menu.add(m2);
		Rect.add(m3);
		a.add(m4);
		b.add(m5);

		// add menu to menu bar
		mb.add(help);
		mb.add(Menu);
		mb.add(Rect);
		mb.add(a);
		mb.add(b);

		// add menubar to frame
		this.setJMenuBar(mb);
        
        
        

        setVisible(true);
//        Thread.sleep(1000*5);
        AddDetails();
        
        
    }
 
    // method actionPerformed()
    // to get the action performed
    // by the user and act accordingly
    
    public void AddDetails()  {
    	
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
//    				xname.setText(hm.get(x_coordinate));
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
//    				yname.setText(hm.get(y_coordinate));
    				break;
    			}
    			y_coordinate = voiceCommand;
    			yname.setText(hm.get(y_coordinate));
  
    		}
    		
    		speech.stopRecognition();
    		
    		
        	String x = "\""+ xname.getText()+ "\"";
        	String y =  "\""+ yname.getText()+ "\"";
//        	
//  	
//        	System.out.println("x = "+xname.getText()+" y = "+yname.getText()+ " \n Closing!!!");
        	String rec_code = "    <svg width=\"500\" height=\"500\"> <rect x=" + x + " y= "+y+" width= "+"\"150\""+" height="+"\"150\" "+"style=\"fill:blue;stroke:pink;stroke-width:5;fill-opacity:0.1;stroke-opacity:0.9\" /></svg>";
//    		System.out.print(rec_code);
    		
    		
    		
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
    String sendmsg() {
    	return "go";
    }
    
    public void actionPerformed(ActionEvent e) 
    {
    	if(e.getSource() == m2) {
//    		this.dispose();
//    		try {
//    			
//				menu1 m = new menu1();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
    		
//    		
//    		SwingUtilities.invokeLater(new Runnable(){
//			public void run() {
//				try {
//					menu1 mn = new menu1();
////					mainobj.runMenu();
//					
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//		});
    		
    	   this.dispose();
     	   new Thread(new Runnable() {
     		      public void run() {
     		     
     		          // Runs inside of the Swing UI thread
     		          SwingUtilities.invokeLater(new Runnable() {
     		            public void run() {
     		            	
     		            	try {
								menu1 mn = new menu1();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
     		            }
     		          });

     		          try {
     		            java.lang.Thread.sleep(100);
     		          }
     		          catch(Exception e) { }
     		        
     		      }
     		    }).start();
    		
    		
    		
    		
    	}
    	
    	if(e.getSource() == m5) {
			this.dispose();
			System.out.println("SIUUUUUUUUUUUUUUU");
//			Triangle frame =  new Triangle();
			
			
		}
    	
        if (e.getSource() == sub) {
        	
        	
        	String x = "\""+ xname.getText()+ "\"";
        	String y =  "\""+ yname.getText()+ "\"";
//        	
//  	
//        	System.out.println("x = "+xname.getText()+" y = "+yname.getText()+ " \n Closing!!!");
        	String rec_code = "    <svg width=\"500\" height=\"500\"> <rect x=" + x + " y= "+y+" width= "+"\"150\""+" height="+"\"150\" "+"style=\"fill:blue;stroke:pink;stroke-width:5;fill-opacity:0.1;stroke-opacity:0.9\" /></svg>";
//    		System.out.print(rec_code);
    		
    		
    		
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
        	
        	
//        	this.dispose();
//    		try {
//    			
//    			
//				menu1 mn2 = new menu1();
//				
//				
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} 
        	
//    		SwingUtilities.invokeLater(new Runnable(){
//    			public void run() {
//    				try {
//    					menu1 mn = new menu1();
////    					mainobj.runMenu();
//    					
//    				} catch (IOException e1) {
//    					// TODO Auto-generated catch block
//    					e1.printStackTrace();
//    				}
//    			}
//    		});
    		
    		
    		
        	
        	

        }
        if(e.getSource() == m4) {
        		this.dispose();
        	   new Thread(new Runnable() {
        		      public void run() {
        		     
        		          // Runs inside of the Swing UI thread
        		          SwingUtilities.invokeLater(new Runnable() {
        		            public void run() {
        		            	
        		    			System.out.println("SIUUUUUUUUUUUUUUU");
//        		    			Circle frame =  new Circle();
        		            }
        		          });

        		          try {
        		            java.lang.Thread.sleep(100);
        		          }
        		          catch(Exception e) { }
        		        
        		      }
        		    }).start();
//			this.dispose();
//			System.out.println("SIUUUUUUUUUUUUUUU");
//			Circle frame =  new Circle();
			
			
		}
        
    	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
//		try {
//			menu1 mc = new menu1();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
	}
    }