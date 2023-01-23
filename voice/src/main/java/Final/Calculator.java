package Final;

import java.util.Locale;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

public class Calculator {
    public int addition(int num1, int num2) {
    	System.out.println("Inside Main.................");
    	speak("hello");
        return (num1 + num2);
    }

    public int substraction(int num1, int num2) {
        return (num1 - num2);
    }

    public int multiplication(int num1, int num2) {
        return (num1 * num2);
    }

    public int division(int num1, int num2) {
        return (num1 / num2);
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