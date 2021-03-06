import java.io.*;
import java.net.*;

class CommunicationServeur implements Runnable {
	ObjectInputStream ois;
	ObjectOutputStream oos;
	BufferedInputStream bis;
	BufferedOutputStream bos;	
	Socket s;
	Object o;
	private Mouvement myMouvement;
	private Message myMessage;
//	private VectorParties;
	
	public CommunicationServeur(Socket sock) {
		try {
			bis = new BufferedInputStream(sock.getInputStream());
			bos = new BufferedOutputStream(sock.getOutputStream());
		}
		catch (IOException e) {
			System.err.println("Erreur d'e/s");	
		}
		
		System.out.println("connexion creee.");
	}
	
/*	
	public Mouvement decodeMouvement(){
=======
	public static void main(String[] args) {
		System.out.println(decodeMouvement((short)531));
	}
	
	/** Code un mouvement en binaire pour l'envoi vers le serveur.
	*
	* @param code Le code a decoder en mouvement
	* @return Le Mouvement 
	*
	*  codage : 
	*     6bits premiere bille
	*     6bits deuxieme bille
	*     3bits le vecteur de deplacement
	*/
	// public static Mouvement decodeMouvement(short code){
	// 		int premiere;
	// 		int deuxieme;
	// 		int vecteur;
	// 		
	// 		premiere = (code & 077000) >> 9;
	// 		deuxieme = (code & 0770) >> 3;
	// 		vecteur = (code & 07);
	// 		
	// 		Mouvement m = new Mouvement((byte)premiere, (byte)deuxieme, (byte)vecteur);
	// 		
	// 		return m;
	// 	}
	
	public void run() {
		System.out.println("run ! ");
		
		
		try {	
			System.out.println("Je cree oos.");
			this.oos = new ObjectOutputStream(
						bos
				);
			this.oos.flush();
			this.ois = new ObjectInputStream(
						bis
				);	
			System.out.println("Je suis pret a recevoir des ordres.");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		

		Serveur.joueursConnectes.afficherListe();
		o = new Object();
		myMouvement = new Mouvement((byte)1, (byte)12, (byte)2);
		// on doit recuperer les identifiants de connexion et verifier qu'ils sont corrects.
		
		// s'ils sont corrects on envoie un objet Joueur qui va �tre instanci� par le client.
		// sinon on essaie de fermer la connexion (comment faire ?)
		while(o != null) {		
			try {
				Thread.currentThread().sleep(3000);
			}
			catch(InterruptedException e) {
				System.err.print("zut");
			}
			System.out.println("test");
		}
			
			
			
		while(o != null) {
			System.out.println("je suis dans le run");
			try {
				Thread.currentThread().sleep(3000);
			}
			catch(InterruptedException e) {
				System.err.println("interrupted");
			}

			try {
				oos.writeObject(myMouvement);
				oos.flush();
				Serveur.joueursConnectes.afficherListe();
/*				
				o = (ois.readObject());
				if(o instanceof Mouvement) {
					System.out.println("Ceci est un mouvement...");
					myMouvement = (Mouvement)o;
					System.out.println(myMouvement);
					// on doit l'enregistrer dans la bdd et l'envoyer � tous les clients.
				}
				else if(o instanceof Message) {
					myMessage = (Message)o;
					System.out.println(myMessage);
				}
*/
				
				
/*
 				else if(o instanceof CreationPartie) {
 					Partie partie = new Partie(Serveur.getNbParties()); // la partie devra contenir son numero de partie puisque le Client doit pouvoir l'identifier (ce num�ro de partie sera g�n�r� par le serveur en fonction du nombre de parties actuellement jouees).
					this.VectorParties.add(partie, partie.getNumPartie()); // on place la partie � la position partie.getNumPartie() : le client la placera � la meme position pour facilement y acceder ?
				}
*/
			}
/*			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
*/			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}