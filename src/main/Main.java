package main;
import decoder.Decoder;
import decoder.DecoderInterface;
import controller.Controller;
import gui.GUI;
import plotter.Plotter;

public class Main
{
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DecoderInterface decoder = new Decoder();
		Plotter p = new Plotter();
		
		Controller c = new Controller(decoder, p);
		GUI view = new GUI(c);
	}
}
