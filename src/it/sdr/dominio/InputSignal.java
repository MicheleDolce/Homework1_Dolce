package it.sdr.dominio;
import java.io.Serializable;
public class InputSignal extends Signal implements Serializable {

	
	
	public InputSignal(double[] pReale, double[] pImmaginaria, int length){
		this.immaginaria= pImmaginaria;
		this.reale= pReale;
		this.length=length;
	}
	
	
	public InputSignal(int length) {
		this.length= length;
		this.reale= new double[length];
		this.immaginaria= new double[length];
		for(int i = 0; i < this.length; i++) {
			double v = Math.random();
			if(v < 0.5)
				this.reale[i] = 1/Math.sqrt(2);
			else
				this.reale[i] = -1/Math.sqrt(2);
			double p = Math.random();
			if(p < 0.5)
				this.immaginaria[i] = 1/Math.sqrt(2);
			else
				this.immaginaria[i] = -1/Math.sqrt(2);
		}
	}

}
