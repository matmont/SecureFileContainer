/**
 * @author Matteo Montalbetti
 * @mat. 559881
 * @corso B
 */
package pr2.project;

public class RightsErrorException extends Exception {

    /**
     * Overview: eccezione lanciata successivamente ad una violazione di
     * diritti.
     */
    /**
     * @effects Costruttore che rimanda alla superclasse Exception
     */
    public RightsErrorException() {
        super();
    }

    /**
     * @effects Costruttore che rimanda alla superclasse Exception con
     * paramentro String s
     * @param s
     */
    public RightsErrorException(String s) {
        super(s);
    }

}
