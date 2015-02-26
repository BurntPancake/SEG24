import decoder.Decoder;
import decoder.DecoderInterface;
import controller.Controller;
import gui.TestGUI;
import plotter.Plotter;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DecoderInterface decoder = new Decoder();
		Plotter p = new Plotter("Number of Impression");
		
		Controller c = new Controller(decoder, p);
		TestGUI view = new TestGUI(c);
		
	}

}
