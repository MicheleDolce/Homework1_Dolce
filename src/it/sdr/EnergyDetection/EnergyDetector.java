package it.sdr.EnergyDetection;

import it.sdr.dominio.Complex;
import it.sdr.dominio.Signal;

public class EnergyDetector {
	
	
	//metodo che dato un segnale in entrata ne calcola l'energia
	public static double detectEnergy(Signal signal){
		Complex campione;
		double sommatoriaModuli=0;
		int signalLength= signal.getLength();
		for(int i=0;i<signalLength;i++){
			campione= new Complex(signal.getReale()[i],signal.getImmaginaria()[i]);
			sommatoriaModuli = campione.moduloQuadro() + sommatoriaModuli;
		}
		return (double)sommatoriaModuli/(double)signalLength;
	}
	
	//metodo che dato un certo numero di segnali ne calcola l'energia restituendo un array contenente l'energia di ogni segnale
	public static double[] detectEnergies(Signal[] signals){
		double[] energies= new double[signals.length];
		for(int i=0;i<signals.length;i++){
			energies[i]= EnergyDetector.detectEnergy(signals[i]);
		}
		return energies;
	}
	
	
}
