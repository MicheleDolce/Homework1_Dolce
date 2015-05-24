package it.sdr.sequenze;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import it.sdr.dominio.InputSignal;



/*** CLASSE CHE TRADUCE I FILES IN SEGNALI  ***/
public class SequenceTranslator {
	
	
	
	/** Metodo che dato il path del file restituisce un segnale che lo rappresenta **/
	
	/* Ogni riga contiene due numeri che rappresentano la parte reale e quella immaginaria di ogni campione,in tutto sono 1milione di coppie reale/immaginario;
	* ho usato due scanner: uno per le righe e uno che all'interno della riga salva i due numeri.
	* Questo metodo è servito per la serializzazione degli output poi non è stato più usato(vedi classe Serializzatore). */
	public static InputSignal sequenceReader(String path, int numeroCampioni) throws FileNotFoundException{
		
		//array che poi andranno a generare l'oggetto InputSignal
		double[] pReale;
		double[] pImmaginaria;
		
		//variabile booleana che serve per capire se sto leggendo da file la parte reale o immaginaria all'interno di ogni riga
		boolean parteReale = true;
		
		//liste in cui salvo temporaneamente i campioni che poi andrò a salvare negli array
		ArrayList<Double> partiReali= new ArrayList<Double>();
		ArrayList<Double> partiImmaginarie= new ArrayList<Double>();
		
		FileReader f= new FileReader(path);
		Scanner scannerDiRiga= new Scanner(f);
		Scanner scannerDiDouble;
		
		int controllo=0;
		
		
		while(controllo<numeroCampioni){
			scannerDiDouble = new Scanner(scannerDiRiga.nextLine());
			while(scannerDiDouble.hasNext()){
				if(parteReale){
					partiReali.add(new Double(scannerDiDouble.nextDouble()));
					parteReale=false;
					controllo++;
				}else{
					partiImmaginarie.add(new Double(scannerDiDouble.nextDouble()));
					parteReale=true;
					controllo++;
				}
			}
			scannerDiDouble.close();
		}
		scannerDiRiga.close();
		pReale = new double[partiReali.size()];
		pImmaginaria = new double[partiImmaginarie.size()];
		for(int i=0;i<partiReali.size();i++){
			pReale[i]=partiReali.get(i).doubleValue();
			pImmaginaria[i]=partiImmaginarie.get(i).doubleValue();
		}
		return new InputSignal(pReale,pImmaginaria,partiReali.size());
	}
	
	
	
		
	//Metodo che prende il segnale rappresentante un singolo file di output e genere mille segnali di lunghezza mille
	public static InputSignal[] generateTestSignalsOnSingleOutput(InputSignal segnaleOutput){
		InputSignal[] segnaliDiTest= new InputSignal[1000];
		int nSegnali=1;
		int j,i;
		segnaliDiTest[0]= new InputSignal(new double[1000],new double[1000],1000);
		for(i=0,j=0;i<1000000;i++,j++){
			if(i>=1000*nSegnali){
				nSegnali++;
				j=0;
				segnaliDiTest[nSegnali-1]=new InputSignal(new double[1000],new double[1000],1000);
			}
			segnaliDiTest[nSegnali-1].getReale()[j]= segnaleOutput.getReale()[i];
			segnaliDiTest[nSegnali-1].getImmaginaria()[j]= segnaleOutput.getImmaginaria()[i];
		}
		return segnaliDiTest;
		
	}
	
	
	
}
