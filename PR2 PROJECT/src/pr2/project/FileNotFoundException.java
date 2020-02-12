/**
 * @author Matteo Montalbetti
 * @mat. 559881
 * @corso B
 */
package pr2.project;

public class FileNotFoundException extends Exception {

    /**
     * Overview: eccezione lanciata nel momento in cui non viene trovato un
     * certo file all'interno della collezione
     */
    /**
     * @effects Costruttore che rimanda alla superclasse Exception
     */
    public FileNotFoundException() {
        super();
    }

    /**
     * @effects Costruttore che rimanda alla superclasse Exception con parametro
     * String s
     * @param s
     */
    public FileNotFoundException(String s) {
        super(s);
    }

}
