package it.sdr.dominio;

public abstract class Signal {

	public int length;
	public double[] reale;
	public double[] immaginaria;
	
	
	
	
	
	/******              GETTER AND SETTERS             *****/
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public double[] getReale() {
		return reale;
	}
	public void setReale(double[] reale) {
		this.reale = reale;
	}
	public double[] getImmaginaria() {
		return immaginaria;
	}
	public void setImmaginaria(double[] immaginaria) {
		this.immaginaria = immaginaria;
	}
	
	
	
	
	
	
	
}
