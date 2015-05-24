package it.sdr.dominio;

import java.io.Serializable;

public class Complex{

	public double reale;
	public double immaginaria;

	public Complex(double reale, double immaginario){
		this.reale = reale;
		this.immaginaria = immaginario;
	}

	/**** GETTER & SETTER ****/
	
	public double getReale() {
		return reale;
	}

	public void setReale(double reale) {
		this.reale = reale;
	}

	public double getImmaginaria() {
		return immaginaria;
	}

	public void setImmaginaria(double immaginaria) {
		this.immaginaria = immaginaria;
	}

	public String toString(){
		String complex = "";

		if(this.immaginaria == 0)
			complex += this.reale;
		else if(this.immaginaria < 0 && this.reale!=0) 
			complex = this.reale +" "+ this.immaginaria+" j";
		else if(this.reale==0)
			complex = this.immaginaria+" j";
		else
			complex = this.reale+" + "+ this.immaginaria+" j";

		return complex;
	}
	
	/*************** METODI BASE NUMERI COMPLESSI ***************/
	
	/**
	 * Calcolo del modulo di un numero complesso visto come la radice quadrata
	 * del quadrato della parte reale + il quadrato della parte immaginaria
	 * @return double
	 */
	public double abs(){
		return Math.hypot(this.reale, this.immaginaria);
		
		//alternativa: Math.sqrt(Math.pow(this.reale,2) + Math.pow(this.immaginario,2));
	}
	
	public double moduloQuadro(){
		return this.abs()*this.abs();
	}
	
	public Complex coniugato(){
		return new Complex(this.reale, - this.immaginaria);
	}
	
	public void complConiugato(){
		this.immaginaria= - this.immaginaria;
	}
	
	public double phase(){
		return Math.atan(this.immaginaria/this.reale);
	}
	
	/**
	 * Effettua la somma tra il numero complesso su cui viene richiamato il metodo e il
	 * numero complesso passato da parametro
	 * @param b
	 * @return Complex
	 */
	public Complex somma(Complex b){
		Complex result = new Complex((this.reale+b.getReale()),
				(this.immaginaria+b.getImmaginaria()));
		return result;
	}
	
	public Complex differenza(Complex b){
		Complex result = new Complex((this.reale -  b.getReale()), 
				(this.immaginaria - b.getImmaginaria()));
		return result;
	}
	
	
	public Complex prodotto(Complex b){
		double reale = this.reale * b.getReale() - this.getImmaginaria() * b.getImmaginaria();
		double immag = this.reale * b.getImmaginaria() + b.getReale() * this.immaginaria;
		return new Complex(reale, immag);
	}
	
	public Complex prodottoScalare(double s){
		return new Complex(this.reale*s, this.immaginaria*s);
	}
	
	
	public Complex reciproco(){
		double scalare = Math.pow(this.reale,2) + Math.pow(this.immaginaria,2);
		Complex result = new Complex(this.reale/scalare , - (this.immaginaria/scalare));
		return result;
	}
	
	public Complex rapporto(Complex b){
		return this.prodotto(b.reciproco());
	}
	
	
	@Override
	public int hashCode() {
		return (int) (this.reale + this.immaginaria);
	}

	@Override
	public boolean equals(Object o) {

		Complex c = (Complex)o;

		if(this.getReale() == c.getReale() && this.getImmaginaria() == c.getImmaginaria())
			return true;

		else 
			return false;

	}	 
	

	

}
