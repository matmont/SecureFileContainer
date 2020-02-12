/**
 * @author Matteo Montalbetti
 * @mat. 559881
 * @corso B
 */
package pr2.project;

import java.util.Iterator;

public class testClass {

    /**
     * Overview: classe usata per testare le due implementazioni.
     *
     */
    public static void main(String[] args) {

        //INIZIO TEST PRIMA IMPLEMENTAZIONE
        System.out.println("------------------------FIRST IMPLEMENTATION----------------------------\n\n");
        //Creo la collezzione
        SecureFileContainer<String> firstContainer = new MySecureFileContainer<String>();

        System.out.println("Programma di test per la prima implementazione.\nVerranno testati tutti i metodi uno dopo l'altro\n\n");

        //Test di createUser valido
        System.out.println("Test di createUser: [valido]\n");
        try {
            firstContainer.createUser("id1", "psw1");
            System.out.println("É stato creato un nuovo utente con dati (id1, psw1)\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (ExistingUserException e) {
            System.out.println("Un utente con l'id indicato é giá presente -> failure\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Hai inserito un username non valido -> failure\n");
        }

        //Test di createUser valido
        System.out.println("Test di createUser: [valido]\n");
        try {
            firstContainer.createUser("id2", "psw2");
            System.out.println("É stato creato un nuovo utente con dati (id2, psw2)\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (ExistingUserException e) {
            System.out.println("Un utente con l'id indicato é giá presente -> failure\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Hai inserito un username non valido -> failure\n");
        }

        //Test di getSize non valido
        System.out.println("Test di getSize: [not valido -> WrongPswException]\n");
        try {

            System.out.println("Numero dei file posseduti dall'utente: " + firstContainer.getSize("id1", "psw") + "\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        }

        //Test di getSize valido
        System.out.println("Test di getSize: [valido | su vuoto]\n");
        try {

            System.out.println("Numero dei file posseduti dall'utente (id1): " + firstContainer.getSize("id1", "psw1") + "\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        }

        //Test di put valido
        System.out.println("Test di put: [valido]\n");
        try {
            firstContainer.put("id1", "psw1", "ciao1");
            firstContainer.put("id1", "psw1", "prova1");
            System.out.println("É stato inserito il file 'ciao1' con diritti di accesso 'w' nel container di (id1)");
            System.out.println("É stato inserito il file 'prova1' con diritti di accesso 'w' nel container di (id1)\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        }

        //Test di put valido
        System.out.println("Test di put: [valido]\n");
        try {
            firstContainer.put("id1", "psw1", "file1");
            firstContainer.put("id2", "psw2", "ciao2");
            System.out.println("É stato inserito il file 'file1' con diritti di accesso 'w' nel container di (id1)\n");
            System.out.println("É stato inserito il file 'ciao2' con diritti di accesso 'w' nel container di (id2)\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        }

        //Test di put non valido
        System.out.println("Test di put: [not valido -> UserNotFoundException]\n");
        try {
            firstContainer.put("id", "psw1", "ciao2");
            System.out.println("É stato inserito il file 'ciao2' con diritti di accesso 'w' nel container di (id)\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        }

        //Test di getSize valido
        System.out.println("Test di getSize: [valido | !(su vuoto)]\n");
        try {
            //testo su entrambi gli utenti
            System.out.println("Numero dei file posseduti dall'utente (id1): " + firstContainer.getSize("id1", "psw1"));
            System.out.println("Numero dei file posseduti dall'utente (id2): " + firstContainer.getSize("id2", "psw2") + "\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        }

        //Test di get valido
        System.out.println("Test di get: [valido]\n");
        try {
            String fileReq = firstContainer.get("id1", "psw1", "ciao1");
            System.out.println("Hai richiesto e ottenuto il file: " + fileReq + "\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        } catch (FileNotFoundException e) {
            System.out.println("Il file richiesto non é stato trovato -> failure\n");
        }

        //Test di get non valido
        System.out.println("Test di get: [not valido -> FileNotFoundException]\n");
        try {
            String fileReq = firstContainer.get("id1", "psw1", "ciao100");
            System.out.println("Hai richiesto e ottenuto il file: " + fileReq + "\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        } catch (FileNotFoundException e) {
            System.out.println("Il file richiesto non é stato trovato -> failure\n");
        }

        //Test di getSize valido (per vedere che get non mi abbia tolto file)
        System.out.println("Test di getSize: [valido | !(su vuoto)]\n");
        try {
            //testo su entrambi gli utenti
            System.out.println("Numero dei file posseduti dall'utente (id1): " + firstContainer.getSize("id1", "psw1"));
            System.out.println("Numero dei file posseduti dall'utente (id2): " + firstContainer.getSize("id2", "psw2") + "\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        }

        //Test di remove valido
        System.out.println("Test di remove: [valido]\n");
        try {
            String fileRem = firstContainer.remove("id1", "psw1", "ciao1");
            System.out.println("Hai rimosso il file: " + fileRem + " dal container di (id1)\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        } catch (FileNotFoundException e) {
            System.out.println("Il file richiesto non é stato trovato -> failure\n");
        }

        //Test di getSize valido (per vedere che get non mi abbia tolto file)
        System.out.println("Test di getSize: [valido | !(su vuoto)]\n");
        try {
            //testo su entrambi gli utenti
            System.out.println("Numero dei file posseduti dall'utente (id1): " + firstContainer.getSize("id1", "psw1"));
            System.out.println("Numero dei file posseduti dall'utente (id2): " + firstContainer.getSize("id2", "psw2") + "\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        }

        //Test di copy valido
        System.out.println("Test di copy: [valido]\n");
        try {
            firstContainer.copy("id1", "psw1", "prova1");
            firstContainer.copy("id2", "psw2", "ciao2");
            firstContainer.remove("id2", "psw2", "ciao2");
            System.out.println("Hai duplicato il file 'prova1' nel container di (id1)\n");
            System.out.println("Hai duplicato e poi eliminato il file 'ciao2' nel container di (id2)\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        } catch (FileNotFoundException e) {
            System.out.println("Il file richiesto non é stato trovato -> failure\n");
        }

        //Test di getSize valido (per vedere che get non mi abbia tolto file)
        System.out.println("Test di getSize: [valido | !(su vuoto)]\n");
        try {
            //testo su entrambi gli utenti
            System.out.println("Numero dei file posseduti dall'utente (id1): " + firstContainer.getSize("id1", "psw1"));
            System.out.println("Numero dei file posseduti dall'utente (id2): " + firstContainer.getSize("id2", "psw2") + "\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        }

        //Test dei due share valido
        System.out.println("Test dei due Share: [valido]\n");
        try {
            //testo su entrambi gli utenti
            firstContainer.shareR("id1", "psw1", "id2", "prova1");
            System.out.println("L'utente (id1) ha condiviso in lettura il file 'prova1' con l'utente (id2)");
            firstContainer.shareW("id2", "psw2", "id1", "ciao2");
            System.out.println("L'utente (id2) ha condiviso in lettura il file 'ciao2' con l'utente (id1)\n");
            System.out.println("Numero dei file posseduti dall'utente (id1): " + firstContainer.getSize("id1", "psw1"));
            System.out.println("Numero dei file posseduti dall'utente (id2): " + firstContainer.getSize("id2", "psw2") + "\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        } catch (FileNotFoundException e) {
            System.out.println("Il file richiesto non é stato trovato -> failure\n");
        } catch (RightsErrorException e) {
            System.out.println("L'utente non é possessore del file (no 'w' rights) -> failure\n");
        } catch (ShareWithYouException e) {
            System.out.println("Non é possibile condividere con se stessi! -> failure\n");
        }

        //Test dell'Iteratore valido
        System.out.println("Test dell'iteratore: [valido]");
        try {
            Iterator<String> it1 = firstContainer.getIterator("id1", "psw1");
            Iterator<String> it2 = firstContainer.getIterator("id2", "psw2");
            //stampo tutti gli elementi di (id1)
            System.out.print("Gli elementi di (id1) sono:   ");
            while (it1.hasNext()) {
                System.out.print(it1.next() + "   ");
            }
            System.out.println("\n");
            //stampo tutti gli elementi di (id2)
            System.out.print("Gli elementi di (id2) sono:   ");
            while (it2.hasNext()) {
                System.out.print(it2.next() + "   ");
            }
            System.out.println("\n");

        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        }

        //INIZIO TEST SECONDA IMPLEMENTAZIONE
        System.out.println("------------------------SECOND IMPLEMENTATION----------------------------\n\n");
        //Creo la collezzione
        SecureFileContainer<String> secondContainer = new MySecondSecureFileContainer<String>();

        System.out.println("Programma di test per la seconda implementazione.\nVerranno testati tutti i metodi uno dopo l'altro\n\n");

        //Test di createUser valido
        System.out.println("Test di createUser: [valido]\n");
        try {
            secondContainer.createUser("id1", "psw1");
            System.out.println("É stato creato un nuovo utente con dati (id1, psw1)\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (ExistingUserException e) {
            System.out.println("Un utente con l'id indicato é giá presente -> failure\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Hai inserito un username non valido -> failure\n");
        }

        //Test di createUser valido
        System.out.println("Test di createUser: [valido]\n");
        try {
            secondContainer.createUser("id2", "psw2");
            System.out.println("É stato creato un nuovo utente con dati (id2, psw2)\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (ExistingUserException e) {
            System.out.println("Un utente con l'id indicato é giá presente -> failure\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Hai inserito un username non valido -> failure\n");
        }

        //Test di getSize non valido
        System.out.println("Test di getSize: [not valido -> WrongPswException]\n");
        try {

            System.out.println("Numero dei file posseduti dall'utente: " + secondContainer.getSize("id1", "psw") + "\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        }

        //Test di getSize valido
        System.out.println("Test di getSize: [valido | su vuoto]\n");
        try {

            System.out.println("Numero dei file posseduti dall'utente (id1): " + secondContainer.getSize("id1", "psw1") + "\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        }

        //Test di put valido
        System.out.println("Test di put: [valido]\n");
        try {
            secondContainer.put("id1", "psw1", "ciao1");
            secondContainer.put("id1", "psw1", "prova1");
            System.out.println("É stato inserito il file 'ciao1' con diritti di accesso 'w' nel container di (id1)");
            System.out.println("É stato inserito il file 'prova1' con diritti di accesso 'w' nel container di (id1)\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        }

        //Test di put valido
        System.out.println("Test di put: [valido]\n");
        try {
            secondContainer.put("id1", "psw1", "file1");
            secondContainer.put("id2", "psw2", "ciao2");
            System.out.println("É stato inserito il file 'file1' con diritti di accesso 'w' nel container di (id1)\n");
            System.out.println("É stato inserito il file 'ciao2' con diritti di accesso 'w' nel container di (id2)\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        }

        //Test di put non valido
        System.out.println("Test di put: [not valido -> UserNotFoundException]\n");
        try {
            secondContainer.put("id", "psw1", "ciao2");
            System.out.println("É stato inserito il file 'ciao2' con diritti di accesso 'w' nel container di (id)\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        }

        //Test di getSize valido
        System.out.println("Test di getSize: [valido | !(su vuoto)]\n");
        try {
            //testo su entrambi gli utenti
            System.out.println("Numero dei file posseduti dall'utente (id1): " + secondContainer.getSize("id1", "psw1"));
            System.out.println("Numero dei file posseduti dall'utente (id2): " + secondContainer.getSize("id2", "psw2") + "\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        }

        //Test di get valido
        System.out.println("Test di get: [valido]\n");
        try {
            String fileReq = secondContainer.get("id1", "psw1", "ciao1");
            System.out.println("Hai richiesto e ottenuto il file: " + fileReq + "\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        } catch (FileNotFoundException e) {
            System.out.println("Il file richiesto non é stato trovato -> failure\n");
        }

        //Test di get non valido
        System.out.println("Test di get: [not valido -> FileNotFoundException]\n");
        try {
            String fileReq = secondContainer.get("id1", "psw1", "ciao100");
            System.out.println("Hai richiesto e ottenuto il file: " + fileReq + "\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        } catch (FileNotFoundException e) {
            System.out.println("Il file richiesto non é stato trovato -> failure\n");
        }

        //Test di getSize valido (per vedere che get non mi abbia tolto file)
        System.out.println("Test di getSize: [valido | !(su vuoto)]\n");
        try {
            //testo su entrambi gli utenti
            System.out.println("Numero dei file posseduti dall'utente (id1): " + secondContainer.getSize("id1", "psw1"));
            System.out.println("Numero dei file posseduti dall'utente (id2): " + secondContainer.getSize("id2", "psw2") + "\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        }

        //Test di remove valido
        System.out.println("Test di remove: [valido]\n");
        try {
            String fileRem = secondContainer.remove("id1", "psw1", "ciao1");
            System.out.println("Hai rimosso il file: " + fileRem + " dal container di (id1)\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        } catch (FileNotFoundException e) {
            System.out.println("Il file richiesto non é stato trovato -> failure\n");
        }

        //Test di getSize valido (per vedere che get non mi abbia tolto file)
        System.out.println("Test di getSize: [valido | !(su vuoto)]\n");
        try {
            //testo su entrambi gli utenti
            System.out.println("Numero dei file posseduti dall'utente (id1): " + secondContainer.getSize("id1", "psw1"));
            System.out.println("Numero dei file posseduti dall'utente (id2): " + secondContainer.getSize("id2", "psw2") + "\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        }

        //Test di copy valido
        System.out.println("Test di copy: [valido]\n");
        try {
            secondContainer.copy("id1", "psw1", "prova1");
            secondContainer.copy("id2", "psw2", "ciao2");
            secondContainer.remove("id2", "psw2", "ciao2");
            System.out.println("Hai duplicato il file 'prova1' nel container di (id1)\n");
            System.out.println("Hai duplicato e poi eliminato il file 'ciao2' nel container di (id2)\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        } catch (FileNotFoundException e) {
            System.out.println("Il file richiesto non é stato trovato -> failure\n");
        }

        //Test di getSize valido (per vedere che get non mi abbia tolto file)
        System.out.println("Test di getSize: [valido | !(su vuoto)]\n");
        try {
            //testo su entrambi gli utenti
            System.out.println("Numero dei file posseduti dall'utente (id1): " + secondContainer.getSize("id1", "psw1"));
            System.out.println("Numero dei file posseduti dall'utente (id2): " + secondContainer.getSize("id2", "psw2") + "\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        }

        //Test dei due share valido
        System.out.println("Test dei due Share: [valido]\n");
        try {
            //testo su entrambi gli utenti
            secondContainer.shareR("id1", "psw1", "id2", "prova1");
            System.out.println("L'utente (id1) ha condiviso in lettura il file 'prova1' con l'utente (id2)");
            secondContainer.shareW("id2", "psw2", "id1", "ciao2");
            System.out.println("L'utente (id2) ha condiviso in lettura il file 'ciao2' con l'utente (id1)\n");
            System.out.println("Numero dei file posseduti dall'utente (id1): " + secondContainer.getSize("id1", "psw1"));
            System.out.println("Numero dei file posseduti dall'utente (id2): " + secondContainer.getSize("id2", "psw2") + "\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        } catch (FileNotFoundException e) {
            System.out.println("Il file richiesto non é stato trovato -> failure\n");
        } catch (RightsErrorException e) {
            System.out.println("L'utente non é possessore del file (no 'w' rights) -> failure\n");
        } catch (ShareWithYouException e) {
            System.out.println("Non é possibile condividere con se stessi! -> failure\n");
        }

        //Test dell'Iteratore valido
        System.out.println("Test dell'iteratore: [valido]");
        try {
            Iterator<String> it1 = secondContainer.getIterator("id1", "psw1");
            Iterator<String> it2 = secondContainer.getIterator("id2", "psw2");
            //stampo tutti gli elementi di (id1)
            System.out.print("Gli elementi di (id1) sono:   ");
            while (it1.hasNext()) {
                System.out.print(it1.next() + "   ");
            }
            System.out.println("\n");
            //stampo tutti gli elementi di (id2)
            System.out.print("Gli elementi di (id2) sono:   ");
            while (it2.hasNext()) {
                System.out.print(it2.next() + "   ");
            }
            System.out.println("\n");

        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        }

        //INIZIO TEST PARTICOLARI
        System.out.println("------------------------OTHER TESTS (on second impl.)----------------------------\n\n");

        //Testo l'IllegalArgument sul createUser
        System.out.println("Test di createUser: [not valido -> IllegalArgumentException]\n");
        try {
            secondContainer.createUser("     ", "passwordProva");
            System.out.println("É stato creato un nuovo utente con dati (id1, psw1)\n");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (ExistingUserException e) {
            System.out.println("Un utente con l'id indicato é giá presente -> failure\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Hai inserito un username non valido -> failure\n");
        }

        //Testo la shareW non valida
        System.out.println("Test dei due Share: [not valido -> ShareWithYouException]\n");
        try {
            //testo su entrambi gli utenti
            secondContainer.put("id1", "psw1", "fileProva");
            secondContainer.shareR("id1", "psw1", "id1", "fileProva");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        } catch (FileNotFoundException e) {
            System.out.println("Il file richiesto non é stato trovato -> failure\n");
        } catch (RightsErrorException e) {
            System.out.println("L'utente non é possessore del file (no 'w' rights) -> failure\n");
        } catch (ShareWithYouException e) {
            System.out.println("Non é possibile condividere con se stessi! -> failure\n");
        }

        //Testo la shareR non valida
        System.out.println("Test dei due Share: [not valido -> RightsErrorException]\n");
        try {
            //testo su entrambi gli utenti
            secondContainer.put("id1", "psw1", "fileProva");
            secondContainer.shareR("id1", "psw1", "id2", "fileProva");
            secondContainer.createUser("id3", "psw3");
            secondContainer.shareR("id2", "psw2", "id3", "fileProva");
        } catch (NullPointerException e) {
            System.out.println("Uno degli input é nullo -> failure\n");
        } catch (UserNotFoundException e) {
            System.out.println("Un utente con l'id indicato non é presente -> failure\n");
        } catch (WrongPswException e) {
            System.out.println("Dati di accesso non validi -> failure\n");
        } catch (FileNotFoundException e) {
            System.out.println("Il file richiesto non é stato trovato -> failure\n");
        } catch (RightsErrorException e) {
            System.out.println("L'utente non é possessore del file (no 'w' rights) -> failure\n");
        } catch (ShareWithYouException e) {
            System.out.println("Non é possibile condividere con se stessi! -> failure\n");
        } catch (ExistingUserException e) {
            System.out.println("Un utente con l'id indicato é giá presente -> failure\n");
        }

        System.out.println("\n===============FINE TEST - SUCCESS===================\n");

    }

}
