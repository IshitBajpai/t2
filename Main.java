package Final_1;

import java.io.IOException;
import java.util.Locale;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.swing.SwingUtilities;


import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;



public class Main {       
	
	public static LiveSpeechRecognizer speech;
    public static void main(String[] args) throws Exception {
    	
    	System.out.println("Inside Main.................");
    	
    	
        Configuration configuration = new Configuration();
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("src\\main\\resources\\1874.dic");
		configuration.setLanguageModelPath("src\\main\\resources\\1874.lm");
		speech = new LiveSpeechRecognizer(configuration);
		
		Menu frame = new Menu();
		frame.setVisible(true);
		
		
		
//    	SwingUtilities.invokeLater(new Runnable(){
//			public void run() {
//			   	menu1 m;
//				try {
//					m = new menu1();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
//		});
		
//		menu1 m = new menu1();
//		String res = m.result();
//		System.out.println("Inside Main................. "+res);
//		if(res.equalsIgnoreCase("VIEW")) {
//			m.dispose();
//			MyFrame f = new MyFrame();
//			
//			
//		}
		
		
	
    }
    
	public  static void speak(String msg) {
		
		try {
			// Set property as Kevin Dictionary
			System.setProperty(
				"freetts.voices",
				"com.sun.speech.freetts.en.us"
					+ ".cmu_us_kal.KevinVoiceDirectory");

			// Register Engine
			Central.registerEngineCentral(
				"com.sun.speech.freetts"
				+ ".jsapi.FreeTTSEngineCentral");

			// Create a Synthesizer
			Synthesizer synthesizer
				= Central.createSynthesizer(
					new SynthesizerModeDesc(Locale.US));

			synthesizer.allocate();
			synthesizer.resume();
			synthesizer.speakPlainText(msg, null);
			synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
    

}