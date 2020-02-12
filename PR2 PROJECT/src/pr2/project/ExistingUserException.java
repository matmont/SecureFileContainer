/**
 * @author Matteo Montalbetti
 * @mat. 559881
 * @corso B
 */
package pr2.project;

public class ExistingUserException extends Exception {

    /**
     * Overview: eccezione lanciata successivamente al tentativo di inserire un
     * nuovo utente all'interno della collezione con associato un id peró
     * registrato. Ricordarsi che l'id é unico all'interno della collezione
     * (Leggere overview SecureFileContainer)
     */
    /**
     * @effects Costruttore che rimanda alla superclasse Exception
     */
    public ExistingUserException() {
        super();
    }

    /**
     * @effects Costruttore che rimanda alla superclasse Exception con
     * paramentro String s
     * @param s
     */
    public ExistingUserException(String s) {
        super(s);
    }

}
