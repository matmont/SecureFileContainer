/**
 * @author Matteo Montalbetti
 * @mat. 559881
 * @corso B
 */
package pr2.project;

public class WrongPswException extends Exception {

    /**
     * Overview: eccezione lanciata nel momento in cui si tenta un accesso con
     * una password non corrispondente all'id indicato.
     */
    /**
     * @effects Costruttore che rimanda alla superclasse Exception
     */
    public WrongPswException() {
        super();
    }

    /**
     * @effects Costruttore che rimanda alla superclasse Exception con parametro
     * String s
     * @param s
     */
    public WrongPswException(String s) {
        super(s);
    }

}
