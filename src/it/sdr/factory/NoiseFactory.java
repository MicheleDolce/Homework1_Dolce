package it.sdr.factory;

import it.sdr.dominio.Noise;
//********  Classe che genera un array di rumori in base a quante prove vogliamo fare  ********/
public class NoiseFactory {
	
	
	/** Genera un vettore di un certo numero di segnali di rumore(numeroProve), ogni segnale di rumore è un vettore di una certa lunghezza(length) **/
	public static Noise[] generateNoise(int numeroProve, double snr,int campioniRumore){
		Noise[] noises= new Noise[numeroProve];
		for(int i=0; i<numeroProve; i++){
			noises[i]= new Noise(snr,campioniRumore);
		}
		return noises;
	}
	
}
