import java.io.*;
import java.net.*;

public class Serveur {
	public static volatile VecteurJoueurs joueursConnectes; // vecteur des joueurs connect�s
//	public static volatile // chats.
	public static volatile VecteurParties parties; // vecteur des parties (jou�es et en attente d'adversaire).
// le serveur envoie la partie au client ce qui lui permettra de savoir si c'est � lui de jouer ou pas.
// le client ne pourra jouer que si dans la Partie designee le joueur qui a la main (le joueur que le serveur �coute afin de recevoir un mouvement)
	public static volatile int nbMessagesEnvoyes;
	public static long startTime = System.currentTimeMillis();
	
	
	public static void main(String[] args) {
// init
		nbMessagesEnvoyes 	= 0;
		joueursConnectes 	= new VecteurJoueurs();
		parties			= new VecteurParties();
		
		
		try {
			ServerSocket srvskt = new ServerSocket(11111);

			// on attend maintenant les connexions
			while(true) {
				System.out.println("Pret a accepter une connexion");
				Socket s = srvskt.accept();
				System.out.println("J'ajoute le client a la liste : ");
				joueursConnectes.ajouter(s);
				System.out.println(startTime+"-"+System.currentTimeMillis()+"="+(System.currentTimeMillis()-startTime)+" millisecondes");
				System.out.println("Je demarre le thread : ");
				(new Thread(new CommunicationServeur(s))).start(); // c'est un Runnable object qu'on lui passe (sa methode run() sera executee).
			}
			
		}
		catch (IOException e) {
			System.err.println("erreur de creation du socket");
		}
	}
}