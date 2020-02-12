/**
 * @author Matteo Montalbetti
 * @mat. 559881
 * @corso B
 */
package pr2.project;

public class ShareWithYouException extends Exception {

    /**
     * Overview: eccezione lanciata successivamente al tentativo di condividere
     * con se stessi. Esiste la copy!
     */
    /**
     * @effects Costruttore che rimanda alla superclasse Exception
     */
    public ShareWithYouException() {
        super();
    }

    /**
     * @effects Costruttore che rimanda alla superclasse Exception con
     * paramentro String s
     * @param s
     */
    public ShareWithYouException(String s) {
        super(s);
    }

}
