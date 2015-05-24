package it.sdr.dominio;
import java.util.Random;
public class Noise extends Signal {

	private double pot_rumore;
	private double varianza;
	
	public Noise(double snr, int length) {
		Random campione = null;
		double  snr_linearizzato= Math.pow(10, (snr/10));
		this.pot_rumore= (1/snr_linearizzato);
		this.length= length;
		this.reale= new double[length];
		for(int i = 0; i < this.length; i++) {
			campione = new Random();
			this.reale[i] = campione.nextGaussian() * Math.sqrt(pot_rumore/2);
		}
		this.immaginaria= new double[length];
		for(int i = 0; i < this.length; i++) {
			campione = new Random();
			immaginaria[i] = campione.nextGaussian() * Math.sqrt(pot_rumore/2);
		}
	}

	public double getPot_rumore() {
		return pot_rumore;
	}

	public void setPot_rumore(double pot_rumore) {
		this.pot_rumore = pot_rumore;
	}

	public double getVarianza() {
		return varianza;
	}

	public void setVarianza(double varianza) {
		this.varianza = varianza;
	}

	

	
	
	
}
