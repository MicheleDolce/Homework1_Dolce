package it.sdr.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import serializzazione.Serializzatore;
import it.sdr.EnergyDetection.CalculatingHelper;
import it.sdr.EnergyDetection.EnergyDetector;
import it.sdr.dominio.Complex;
import it.sdr.dominio.InputSignal;
import it.sdr.dominio.Noise;
import it.sdr.dominio.Signal;
import it.sdr.factory.NoiseFactory;
import it.sdr.sequenze.SequenceTranslator;

public class AvvioTest {
	 
	
	
	
	public static void startSequenzaOutput(String numeroSequenza, String numeroOutput){
		String outputParteReale= null;
		String outputParteImmaginaria=null;
		//a seconda degli input scelgo l'output
		if(numeroOutput.equals("outputUno")){
			outputParteReale= "output1reale";
			outputParteImmaginaria= "output1Immaginaria";
		}if(numeroOutput.equals("outputTre")){
			outputParteReale= "output3reale";
			outputParteImmaginaria= "output3Immaginaria";
		}if(numeroOutput.equals("outputQuattro")){
			outputParteReale= "output4reale";
			outputParteImmaginaria= "output4Immaginaria";
		}
		
		double soglia=0; //inizializzata per non generare errori dal compilatore
		double probDetection;
		
		//Vettore che contiene le energie dei segnali che verranno usati in input presi da file
		double[] energieSegnali;
		
		//Vettore che conterrà l'energia di ogni rumore che mi genero
		double[] energieRumori;
		
		//mi genero il segnale che rappresenta l'intero output
		InputSignal segnaleOutput= new InputSignal(Serializzatore.caricaSegnale(numeroSequenza,outputParteReale),Serializzatore.caricaSegnale(numeroSequenza,outputParteImmaginaria), 1000000);
		
		//calcolo l'snr del segnale
		double snr= CalculatingHelper.calculateSnr(segnaleOutput);
		
		//IPOTESI H0 -> settaggio soglia
		//Genero 10mila rumori di lunghezza mille con l'snr appena calcolato
		Noise[] rumori= NoiseFactory.generateNoise(10000, snr, 1000);
		
		Double pFalsoAllarme= 1/Math.pow(10, 3);
		
		//calcolo le energie dei rumori
		energieRumori= EnergyDetector.detectEnergies(rumori);
		
		//setto la soglia con il vettore delle energie e il falso allarme
		try {
			soglia= CalculatingHelper.calculateThreesold(energieRumori, pFalsoAllarme);
		} catch (Exception e) {
			System.out.println("Errore nel calcolo della soglia");
		}
		
		//IPOTESI H1 -> calcolo la detection per 1000 segnali
		//Dato il segnale rappresentante il file di output genero un array contenente mille segnali di lunghezza mille
		InputSignal[] segnaliInput = SequenceTranslator.generateTestSignalsOnSingleOutput(segnaleOutput);
		
		//Ne calcolo le energie
		energieSegnali= EnergyDetector.detectEnergies(segnaliInput);
		
		//calcolo la probabilità di detection
		probDetection= CalculatingHelper.detectionProbability(energieSegnali, soglia);
		
		System.out.println("SOGLIA:  "+ soglia);
		System.out.println("SNR:  "+ snr);
		System.out.print("PROBABILITA' DETECTION CON FALSO ALLARME 10^-3:  " +probDetection);
	}
	
	
	public static void start() throws IOException{
		InputStreamReader is = new InputStreamReader(System.in);
	    BufferedReader br = new BufferedReader(is);
		String scelta;
		
	
			System.out.println("Scegli sequenza, input possibili: 1   2   3");
			scelta= br.readLine();
			System.out.println("");
			if(scelta.equals("1")){
				System.out.println("Scegli file di output, input possibili: 1	3	4");
				scelta= br.readLine();
				System.out.println("");
				if(scelta.equals("1"))
					AvvioTest.startSequenzaOutput("Uno", "outputUno");
				else if(scelta.equals("3"))
					AvvioTest.startSequenzaOutput("Uno", "outputTre");
				else if(scelta.equals("4"))
					AvvioTest.startSequenzaOutput("Uno", "outputQuattro");
				else System.out.println("Input errato");
			}else if(scelta.equals("2")){
				System.out.println("Scegli file di output, input possibili: 1	3	4");
				scelta= br.readLine();
				System.out.println("");
				if(scelta.equals("1"))
					AvvioTest.startSequenzaOutput("Due", "outputUno");
				else if(scelta.equals("3"))
					AvvioTest.startSequenzaOutput("Due", "outputTre");
				else if(scelta.equals("4"))
					AvvioTest.startSequenzaOutput("Due", "outputQuattro");
				else System.out.println("Input errato");
			}else if(scelta.equals("3")){
				System.out.println("Scegli file di output, input possibili: 1	3	4");
				scelta= br.readLine();
				System.out.println("");
				if(scelta.equals("1"))
					AvvioTest.startSequenzaOutput("Tre", "outputUno");
				else if(scelta.equals("3"))
					AvvioTest.startSequenzaOutput("Tre", "outputTre");
				else if(scelta.equals("4"))
					AvvioTest.startSequenzaOutput("Tre", "outputQuattro");
				else System.out.println("Input errato");
			}else System.out.println("Input errato devi digitare uno di questi numeri: 1	2	3");
		
	}
	
	public static void main(String[] args) throws IOException{
		AvvioTest.start();	
		
	}
	
	
		
	
	
	
	
}
