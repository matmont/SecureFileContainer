/**
 * @author Matteo Montalbetti
 * @mat. 559881
 * @corso B
 */
package pr2.project;

import java.util.ArrayList;
import java.util.Iterator;

public interface SecureFileContainer<E> {

    /**
     * Overview: SecureFileContainer é una collezione di oggetti di tipo
     * generico E. Il compito della collezione é quello di permettere la
     * memorizzazione e la condivisione di file, garantendo meccanismi di
     * sicurezza e protezione per gli utenti. Ogni file della collezione é
     * associato ad un certo utente, il quale ne é proprietario. A sua volta
     * ogni utente é identificato (e si identifica) attraverso dei dati di
     * accesso quali username (o id) e password. Il primo tra i due dovrá essere
     * unico all'interno della collezione, mentre per la password questo non
     * viene richiesto. É quindi necessario che la collezione si occupi, prima
     * di procedere ad attuare certe operazioni su un certo file, di controllare
     * la corrispondenza tra identitá di chi sta richiedendo le operazioni e
     * identitá di chi possiede il file.
     *
     * Typical element: (id, passw) -> (file0, ..., fileN)
     *
     * diciamo che cercando di generalizzare il piú possibile il concetto di
     * SecureFileContainer, possiamo vedere la collezione come un meccanismo di
     * associazione (funzione) tra utente e propria collezione di file. Bisogna
     * peró dire che in realtá la collezione puó essere implementata in svariati
     * modi differenti, ognuno dei quali, vede essa stessa prendere forme
     * diverse. (ad esempio potrei implementare in modo tale che la collezione
     * sia un insieme di triple <id, passw, {files}>;in questo caso si potrebbe
     * essere piú chiari nello specificare un 'typical element')
     */
    /**
     * @effects Crea un nuovo utente con dati di accesso id e passw e lo
     * inserisce all'interno della collezione, creando ed associandogli un nuovo
     * insieme di file di tipo generico E, inizialmente vuoto. Puó lanciare una
     * NullPointerException se i dati passati al metodo sono nulli (ne basta
     * uno) oppure una ExistingUserException nel caso in cui esista giá un
     * utente con l'id passato come parametro al metodo. Puó lanciare una
     * IllegalArgumentException se l'username proposto é una stringa vuota.
     * @param id
     * @param passw
     * @modify this
     * @throws NullPointerException if (id == null || passw == null) [unchecked]
     * @throws ExistingUserException if (exists User u | u.id == id) [checked]
     * @throws IllegalArgumentException if (id.replaceAll("\\s", "").equals(""))
     * [unchecked]
     */
    public void createUser(String id, String passw) throws NullPointerException, ExistingUserException, IllegalArgumentException;

    /**
     * @effects Calcola la grandezza dell'insieme dei file di cui un utente é
     * proprietario. Puó lanciare una NullPointerException se i dati inseriti
     * sono nulli (ne basta uno), una WrongPswException se la password inserita
     * non é la corrispondente dell'id oppure, infine, puó lanciare anche una
     * UserNotFoundException se l'id passato al metodo non corrisponde ad un
     * utente all'interno della collezione. (Ricordo che l'id é univoco
     * all'interno della collezione, per questo non serve controllare anche la
     * password per questa eccezione).
     * @param id
     * @param passw
     * @return Ritorna il numero di file associati all'utente
     * @throws NullPointerException if (id == null || passw == null) [unchecked]
     * @throws WrongPswException if (exists User u | u.id == id && u.passw !=
     * passw) [checked]
     * @throws UserNotFoundException if (not exists User u | u.id == id)
     * [checked]
     */
    public int getSize(String id, String passw) throws NullPointerException, WrongPswException, UserNotFoundException;

    /**
     * @effects Inserisce il file (di tipo E) all'interno della collezione,
     * associandolo all'utente avente i dati di accesso indicati con diritti di
     * accesso in scrittura (e quindi anche in lettura). Puó lanciare una
     * NullPointerException se i dati inseriti sono nulli (ne basta uno), una
     * WrongPswException se la password inserita non é la corrispondente dell'id
     * oppure, infine, puó lanciare anche una UserNotFoundException se l'id
     * passato al metodo non corrisponde ad un utente all'interno della
     * collezione. (Ricordo che l'id é univoco all'interno della collezione, per
     * questo non serve controllare anche la password per questa eccezione).
     * @param id
     * @param passw
     * @param file
     * @return Ritorna true: se l'operazione é andata a buon fine, false:
     * altrimenti (non dovrebbe mai tornare false a causa della copertura data
     * dalle eccezioni)
     * @modify this
     * @throws NullPointerException if (id == null || passw == null || file ==
     * null) [unchecked]
     * @throws WrongPswException if (exists User u | u.id == id && u.passw !=
     * passw) [checked]
     * @throws UserNotFoundException if (not exists User u | u.id == id)
     * [checked]
     */
    public boolean put(String id, String passw, E file) throws NullPointerException, WrongPswException, UserNotFoundException;

    /**
     * @effects Restituisce una copia del file richiesto. Si restituisce una
     * copia per non modificare la collezione e/o rischiare di perdere file. Puó
     * lanciare una NullPointerException se i dati inseriti sono nulli (ne basta
     * uno), una WrongPswException se la password inserita non é la
     * corrispondente dell'id oppure, una UserNotFoundException se l'id passato
     * al metodo non corrisponde ad un utente all'interno della collezione
     * (ricordo che l'id é univoco all'interno della collezione, per questo non
     * serve controllare anche la password per questa eccezione), ed infine una
     * FileNotFoundException se il file indicato non é presente all'interno
     * della collezione.
     * @param id
     * @param passw
     * @param file
     * @return Una copia del file richiesto. (In caso di piú file uguali, il
     * primo trovato scorrendo la collezione)
     * @throws NullPointerException if (id == null || passw = null || file ==
     * null) [unchecked]
     * @throws WrongPswException if (exists User u | u.id == id && u.passw !=
     * passw) [checked]
     * @throws UserNotFoundException if (not exists User u | u.id == id)
     * [checked]
     * @throws FileNotFoundException if (not exists File f | f is in collection)
     * [checked]
     */
    public E get(String id, String passw, E file) throws NullPointerException, WrongPswException, UserNotFoundException, FileNotFoundException;

    /**
     * @effects Restituisce il file di cui é stato richiesta l'eliminazione. Puó
     * lanciare una NullPointerException se i dati inseriti sono nulli (ne basta
     * uno), una WrongPswException se la password inserita non é la
     * corrispondente dell'id oppure, una UserNotFoundException se l'id passato
     * al metodo non corrisponde ad un utente all'interno della collezione
     * (ricordo che l'id é univoco all'interno della collezione, per questo non
     * serve controllare anche la password per questa eccezione), ed infine una
     * FileNotFoundException se il file indicato non é presente all'interno
     * della collezione.
     * @param id
     * @param passw
     * @param file
     * @return Il file eliminato dalla collezione. (In caso di piú file uguali,
     * il primo trovato scorrendo la collezione)
     * @modify this
     * @throws NullPointerException if (id == null || passw = null || file ==
     * null) [unchecked]
     * @throws WrongPswException if (exists User u | u.id == id && u.passw !=
     * passw) [checked]
     * @throws UserNotFoundException if (not exists User u | u.id == id)
     * [checked]
     * @throws FileNotFoundException if (not exists File f | f is in collection)
     * [checked]
     */
    public E remove(String id, String passw, E file) throws NullPointerException, WrongPswException, UserNotFoundException, FileNotFoundException;

    /**
     * @effects Crea una copia del file richiesto e la associa all'utente. Puó
     * lanciare una NullPointerException se i dati inseriti sono nulli (ne basta
     * uno), una WrongPswException se la password inserita non é la
     * corrispondente dell'id oppure, una UserNotFoundException se l'id passato
     * al metodo non corrisponde ad un utente all'interno della collezione
     * (ricordo che l'id é univoco all'interno della collezione, per questo non
     * serve controllare anche la password per questa eccezione), ed infine una
     * FileNotFoundException se il file indicato non é presente all'interno
     * della collezione.
     * @param id
     * @param passw
     * @param file
     * @modify this
     * @throws NullPointerException if (id == null || passw = null || file ==
     * null) [unchecked]
     * @throws WrongPswException if (exists User u | u.id == id && u.passw !=
     * passw) [checked]
     * @throws UserNotFoundException if (not exists User u | u.id == id)
     * [checked]
     * @throws FileNotFoundException if (not exists File f | f is in collection)
     * [checked]
     */
    public void copy(String id, String passw, E file) throws NullPointerException, WrongPswException, UserNotFoundException, FileNotFoundException;

    /**
     * @effects Permette ad un certo utente di condividere un proprio certo file
     * con un altro utente. Il file in questione sará per l'altro utente
     * accessibile solo in lettura. Puó lanciare una NullPointerException se i
     * dati inseriti sono nulli (ne basta uno), una WrongPswException se la
     * password inserita non é la corrispondente dell'id oppure, una
     * UserNotFoundException se l'id passato al metodo non corrisponde ad un
     * utente all'interno della collezione (ricordo che l'id é univoco
     * all'interno della collezione, per questo non serve controllare anche la
     * password per questa eccezione), ed infine una FileNotFoundException se il
     * file indicato non é presente all'interno della collezione. Puó lanciare
     * una RightsErrorException se l'utente che vuole condividere non é
     * proprietario del file, ovvero se non ha diritti in scrittura. Puó
     * lanciare una ShareWithYouException se si tenta una condivisione con se
     * stessi.
     * @param Owner
     * @param passw
     * @param Other
     * @param file
     * @modify files collection of (User) other
     * @throws NullPointerException if (id == null || passw = null || file ==
     * null) [unchecked]
     * @throws WrongPswException if (exists User u | u.id == id && u.passw !=
     * passw) [checked]
     * @throws UserNotFoundException if (not exists User u | u.id == id)
     * [checked]
     * @throws FileNotFoundException if (not exists File f | f is in collection)
     * [checked]
     * @throws RightsErrorException if (User(id, passw) have file &&
     * !(file.getRights.equals("w"))) [checked]
     * @throws ShareWithYouException if (User(id, passw) == User(other))
     * [checked]
     */
    public void shareR(String id, String passw, String other, E file) throws NullPointerException, WrongPswException, UserNotFoundException, FileNotFoundException, RightsErrorException, ShareWithYouException;

    /**
     * @effects Permette ad un certo utente di condividere un proprio certo file
     * con un altro utente. Il file in questione sará per l'altro utente
     * accessibile in scrittura (e quindi anche in lettura). Puó lanciare una
     * NullPointerException se i dati inseriti sono nulli (ne basta uno), una
     * WrongPswException se la password inserita non é la corrispondente dell'id
     * oppure, una UserNotFoundException se l'id passato al metodo non
     * corrisponde ad un utente all'interno della collezione (ricordo che l'id é
     * univoco all'interno della collezione, per questo non serve controllare
     * anche la password per questa eccezione), ed infine una
     * FileNotFoundException se il file indicato non é presente all'interno
     * della collezione. Puó lanciare una RightsErrorException se l'utente che
     * vuole condividere non é proprietario del file, ovvero se non ha diritti
     * in scrittura. Puó lanciare una ShareWithYouException se si tenta una
     * condivisione con se stessi.
     * @param Owner
     * @param passw
     * @param Other
     * @param file
     * @modify files collection of (User) other
     * @throws NullPointerException if (id == null || passw = null || file ==
     * null) [unchecked]
     * @throws WrongPswException if (exists User u | u.id == id && u.passw !=
     * passw) [checked]
     * @throws UserNotFoundException if (not exists User u | u.id == id)
     * [checked]
     * @throws FileNotFoundException if (not exists File f | f is in collection)
     * [checked]
     * @throws RightsErrorException if (User(id, passw) have file &&
     * !(file.getRights.equals("w"))) [checked]
     * @throws ShareWithYouException if (User(id, passw) == User(other))
     * [checked]
     */
    public void shareW(String id, String passw, String other, E file) throws NullPointerException, WrongPswException, UserNotFoundException, FileNotFoundException, RightsErrorException, ShareWithYouException;

    /**
     * @effects Restituisce un iteratore (senza remove) che genera tutti i file
     * dell'utente (in ordine arbitrario). Puó lanciare una NullPointerException
     * se i dati inseriti sono nulli (ne basta uno), una WrongPswException se la
     * password inserita non é la corrispondente dell'id oppure, infine, puó
     * lanciare anche una UserNotFoundException se l'id passato al metodo non
     * corrisponde ad un utente all'interno della collezione. (Ricordo che l'id
     * é univoco all'interno della collezione, per questo non serve controllare
     * anche la password per questa eccezione).
     * @param id
     * @param passw
     * @return Un iteratore (senza remove) che genera tutti i file dell'utente
     * @throws NullPointerException if (id == null || passw == null || file ==
     * null) [unchecked]
     * @throws WrongPswException if (exists User u | u.id == id && u.passw !=
     * passw) [checked]
     * @throws UserNotFoundException if (not exists User u | u.id == id)
     * [checked]
     */
    public Iterator<E> getIterator(String id, String passw) throws NullPointerException, WrongPswException, UserNotFoundException;

    //OTHER FUNCTIONS
    /**
     * @effects Cerca all'interno della collezione un utente con id indicato e
     * ritorna un int che ne indica la posizione oppure -1 se l'utente non é
     * stato trovato. Puó lanciare una NullPointerException se i dati inseriti
     * sono nulli (ne basta uno).
     * @param id
     * @return int i >= 0: se é presente all'interno della collezione un utente
     * avente come username 'id', con i che ne indica la posizione | -1:
     * altrimenti
     * @throws NullPointerException if (id == null) [unchecked]
     */
    public int userSearch(String id) throws NullPointerException;

    /**
     * @effects Cerca all'interno di una collezione di file (non ArrayList di
     * ArrayList, solamente singolo ArrayList) un file e ritorna un int che ne
     * indica la posizione oppure -1 se il file non é stato trovato. Puó
     * lanciare una NullPointerException se i dati inseriti sono nulli (ne basta
     * uno).
     * @param file
     * @param fileColl
     * @return int i >= 0: se é presente all'interno della collezione un file
     * avente come dato effettivo 'file', con i che ne indica la posizione | -1:
     * altrimenti
     * @throws NullPointerException if (file == null || fileColl == null)
     * [unchecked]
     */
    public int fileSearch(E file, ArrayList<E> fileColl) throws NullPointerException;

    /**
     * @effects Restituisce, dato un ArrayList<File<E>> un ArrayList<E>, prende
     * quindi da tutti gli oggetti File solo il campo 'data', quello con la info
     * effettiva, e crea un altro array contentente questi. Puó lanciare una
     * NullPointerException se i dati inseriti sono nulli (ne basta uno).
     * @param array
     * @return Un ArrayList<E> contenente solo i dati effettivi (di tipo E) di
     * un ArrayList<File<E>>
     * @throws NullPointerException if (array == null) [unchecked]
     */
    public ArrayList<E> onlyData(ArrayList<File<E>> array) throws NullPointerException;
}
