package Final;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

/**
 * Servlet implementation class log
 */
@WebServlet("/log")
public class log extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	
		String uname = request.getParameter("uname");
		System.out.println("JJJJJJJJJJJJJJ");
		
//		speak("HELLO");
		response.sendRedirect("welcome.jsp");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
