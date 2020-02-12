/**
 * @author Matteo Montalbetti
 * @mat. 559881
 * @corso B
 */
package pr2.project;

public class UserNotFoundException extends Exception {

    /**
     * Overview: eccezione lanciata nel momento in cui non viene trovato
     * all'interno della collezione un certo utente.
     */
    /**
     * @effects Costruttore che rimanda alla superclasse Exception
     */
    public UserNotFoundException() {
        super();
    }

    /**
     * @effects Costruttore che rimanda alla superclasse Exception con parametro
     * String s
     * @param s
     */
    public UserNotFoundException(String s) {
        super(s);
    }

}
