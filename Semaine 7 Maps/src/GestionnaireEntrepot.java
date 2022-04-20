import java.util.LinkedList;
import java.util.Scanner;

public class GestionnaireEntrepot {

    private static Scanner scanner = new Scanner(System.in);
    private static Entrepot entrepot;

    public static void main(String[] args) {

        System.out.println("********************************");
        System.out.println("GESTION ENTREPOT");
        System.out.println("********************************");
        System.out.println("Entrez le nombre de hangars disponibles dans l'entrepot :");

        int choix = scanner.nextInt();
        entrepot = new Entrepot(choix);

        do {
            System.out.println("1 -> Attribuer un hangar");
            System.out.println("2 -> Lib�rer un hangar");
            System.out.println("3 -> Lister les hangars d'une soci�t�");
            System.out.println("4 -> Lister les num�ros des hangars libres");
            System.out.println("Entrez votre choix :");
            choix = scanner.nextInt();
            switch(choix){
                case 1: attribuerHangar();
                    break;
                case 2: libererUnHangar();
                    break;
                case 3: listerHangarsDeLaSociete();
                    break;
                case 4: listerHangarsLibres();
                    break;
            }
        } while (choix > 0 && choix <= 4);
        System.out.println("Merci de votre visite.");
    }

    public static void attribuerHangar() {
        if (entrepot.estPlein()){
            System.out.println("D�sol�, l'entrep�t est plein !");
            return;
        }

        System.out.println("Entrez le num�ro de la soci�t� :");
        int numSoc = scanner.nextInt();
        String nomSociete = entrepot.getNomSociete(numSoc);
        if (nomSociete == null){
            System.out.println("Cette soci�t� n'est pas encore enregistr�e dans l'entrep�t.");
            System.out.println("Entrez un nom pour cette soci�t� :");
            scanner.nextLine();
            nomSociete = scanner.nextLine();
        } else System.out.println("Cette soci�t� est r�pertori�e dans l'entrep�t.");
        int hangarAttribue = entrepot.attribuerHangar(numSoc,nomSociete);
        System.out.println("La soci�t� " + nomSociete + " s'est fait attribuer le hangar num�ro " + hangarAttribue);
    }

    public static void listerHangarsDeLaSociete() {
        System.out.println("Entrez le num�ro de la soci�t� :");
        int numSoc = scanner.nextInt();
        Societe societe = entrepot.getSociete(numSoc);
        if (societe == null){
            System.out.println("D�sol�, cette soci�t� n'est pas pr�sente dans l'entrep�t.");
            return;
        }
        System.out.println("Voici la liste des hangars poss�d�s par la soci�t� " + societe.getNom());
        System.out.println(societe.lesHangars());
    }

    public static void libererUnHangar() {
        if (entrepot.estVide()){
            System.out.println("L'entrep�t est d�j� vide !");
            return;
        }
        System.out.println("Entrez le num�ro du hangar � lib�rer :");
        int numHangar = scanner.nextInt();
        if (!entrepot.libererHangar(numHangar)) System.out.println("Le hangar num�ro " + numHangar + " est d�j� libre.");
        else {
            System.out.println("Le hangar num�ro " + numHangar + " s'est lib�r�.");
        }
    }

    public static void listerHangarsLibres() {
        if (entrepot.estPlein()){
            System.out.println("Aucun entrep�t n'est libre.");
            return;
        };
        System.out.println(entrepot.getNumeroHangarsLibres());
    }
}
