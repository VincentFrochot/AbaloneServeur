import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * sert a lire les objets recus.
 *
 */

public class CommunicationClient implements Runnable {
	ObjectInputStream ois;
	ObjectOutputStream oos;
	BufferedInputStream bis;
	BufferedOutputStream bos;
	Object o;
	Mouvement m;		
	
	public CommunicationClient(Socket sock) {
		try {
			bis = new BufferedInputStream(sock.getInputStream());
			bos = new BufferedOutputStream(sock.getOutputStream());
		}
		catch (IOException e) {
			System.err.println("Erreur d'e/s");	
		}
		
		System.out.println("connexion creee.");
	}
	
	public void run() {
		
		try {	
			System.out.println("Je cree oos.");
			this.oos = new ObjectOutputStream(
						bos
				);
			System.out.println("Je flush oos.");
			this.oos.flush();
			System.out.println("Je cree ois.");
			this.ois = new ObjectInputStream(
						bis
				);
			System.out.println("ois cree.");
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		
			// connexion : on doit envoyer un Identifiant de connexion et attendre la reponse du serveur.
//			Joueur j = new Joueur("Iawliet");
			//joueurConnectes.add(j);
			
			// on doit ici initialiser les valeurs par d�faut des diff�rents objets que l'on pourra r�cup�rer.
			Mouvement m = new Mouvement((byte)(1),(byte)(2),(byte)(3));
//			boolean m = true;

			
			Object o = new Object();
			while(o != null) {			
/*				
				System.out.println("pause");
				try {
					Thread.currentThread().sleep(3000);
				}
				catch(InterruptedException e) {
					System.err.print("zut");
				}
*/				
				/*				try {
					oos.writeObject(m); // ces actions sont d�clench�es par un click
					oos.flush();
			*/		
				/*
					try  { // si c'est un Mouvement que l'on vient d'envoyer, on doit se mettre en �coute
						o = ois.readObject(); // les clients sont constamment � l'�coute du serveur : seules les actions via la souris permettent d'envoyer des messages au serveur.
						// ici on doit maintenant tester via instanceof l'objet re�u et modifier notre interface de fa�on appropri�e.
						if(o instanceof Mouvement) {
							m = (Mouvement)o;
							System.out.println(m);
						}
						else {
							System.out.println("Je n'ai pas recu un mouvement.");
						}
					
					}
					catch (ClassNotFoundException e) {
						
					}
					catch (IOException e) {
						System.out.println("erreur d'entree sortie");
					}

/*				}
				catch (IOException e) {
					
				}			
				*/

				
				
				try {
					Thread.currentThread().sleep(3000);
				}
				catch(InterruptedException e) {
					System.err.print("zut");
				}
				System.out.println("test");
			}
	}
	/*
		Liste des actions:
		    0000 : CreerPartie
		    0001 : rejoindrePartie
		    0010 : JouerMouvement
		    0011 : Abandonner
		    0100 : ReprendreUnCoup
		    0101 : AccepterRepriseCoup
		    0110 : RefuserRepriseCoup
		    0111 : PlacerUneBille (editer)
		    1000 : PlacerUnSymbole (editer)
		    1001 : RevenirAUnCoupPrecis (editer)
		    1010 : EcrireChat
		    1011 : 
	*/
	
	
	/** Code un mouvement en binaire pour l'envoi vers le serveur.
	*
	* @param m Le mouvement à coder
	* @return Le mouvement coder en binaire 
	*
	*  codage : 
	*     6bits premiere bille
	*     6bits deuxieme bille
	*     3bits le vecteur de deplacement
	*/
	public static short codeMouvement(Mouvement m){
		int mouv=0;
		
		//La premiere bille
		mouv = m.getPremiere();
		//La deuxieme bille
		mouv = mouv << 6;
		mouv += m.getDerniere();
		//Le vecteur
		mouv = mouv << 3;
		mouv += m.getVecteur();
		
		return (short)(mouv);
	}
}