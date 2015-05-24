package serializzazione;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import it.sdr.dominio.Complex;
import it.sdr.dominio.InputSignal;
import it.sdr.sequenze.SequenceTranslator;





/* Dato che prendere i campioni dai file di testo forniti era troppo dispendioso in termini di tempo ho creato questa classe.
 * Ho preso ogni file di output e lo ho trasformato in un oggetto segnale tramite la classe SequenceTranslator.
 * Ho poi preso i due array dell'oggetto e li ho serializzati cioè ho salvato i due oggetti array come file di testo.
 * Questo ad esempio mi ha permesso di convertire il file output1 in due file output1reale e output1immaginaria che sono proprio i due array
 * che rappresentano la parte reale e l'immaginaria dei campioni.
 * Tramite il metodo caricaSegnale viene caricato direttamente l'oggetto array in un tempo minimo da file di testo.
 * Ho salvato quindi i due array come file di testo.
 * 
 * FACENDO AD ESEMPIO:
 * InputSignal segnaleParteUno= SequenceTranslator.sequenceReader("C:/Users/Michele/workspace/Homework1_Dolce/src/it/sdr/sequenze/sequenza2/output_2.txt",2000000);
 * Serializzatore.salvaSegnaleInput(segnaleParteUno.getReale(),"outpu2reale");
 * Serializzatore.salvaSegnaleInput(segnaleParteUno.getImmaginaria(),"output2Immaginaria");
 * 
 * Prendo tutti i 2milioni di campioni dal file di testo e mi genero il segnale.
 * Salvo la rappresentazione dei due array del segnale(parte reale e immaginaria) in un file di testo all'interno della cartella specificata all'interno del metodo salvaSegnale
 * 
 * Nel progetto ho messo direttamente i file serializzati senza mostrare il processo che è stato questo descritto qui sopra per ogni output
 */
public class Serializzatore {
	
	
	
	public static void salvaSegnaleInput(double[] segnale,String nome){
		String fileName=" PATH DOVE SI VUOLE SALVARE L'ARRAY " + nome +".txt";
		ObjectOutputStream oss;
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {
		fileOutputStream = new FileOutputStream(fileName);
		objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(segnale);
		objectOutputStream.close();
		fileOutputStream.close();
		System.out.println("Oggetto correttamente salvato su file.");
		} catch (IOException ex) {
		ex.printStackTrace();
		}
	}
	
	public static double[] caricaSegnale(String numeroSequenza, String nomeFile){
		double[] segnale=new double[1000000];
		String fileName = "src/serializzazione/sequenza"+ numeroSequenza +"Serializzata/"+ nomeFile +".txt";
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		try {
			fileInputStream = new FileInputStream(fileName);
			objectInputStream = new ObjectInputStream(fileInputStream);
			segnale = (double[]) objectInputStream.readObject();
			objectInputStream.close();
			fileInputStream.close();
			} catch (IOException ex) {
			ex.printStackTrace();
			} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			}
		return segnale;
	}
	
	
	
	
	
	
}
