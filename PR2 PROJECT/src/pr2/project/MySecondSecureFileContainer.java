/**
 * @author Matteo Montalbetti
 * @mat. 559881
 * @corso B
 */
package pr2.project;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Set;

public class MySecondSecureFileContainer<E> implements SecureFileContainer<E> {

    /**
     * Overview: SecureFileContainer é una collezione di oggetti di tipo
     * generico E. Il compito della collezione é quello di permettere la
     * memorizzazione e la condivisione di file, garantendo meccanismi di
     * sicurezza e protezione per gli utenti. Ogni file della collezione é
     * associato ad un certo utente, il quale ne é proprietario. A sua volta
     * ogni utente é identificato (E si identifica) attraverso dei dati di
     * accesso quali username (o id) e password. Il primo tra i due dovrá essere
     * unico all'interno della collezione, mentre per la password questo non
     * viene richiesto. É quindi necessario che la collezione si occupi, prima
     * di procedere ad attuare certe operazioni su un certo file, di controllare
     * la corrispondenza tra identitá di chi sta richiedendo le operazioni e
     * identitá di chi possiede il file.
     *
     * In questa seconda implementazione ho voluto utilizzare un HashMap, quindi
     * una struttura dati di tipo tabellare <chiave, valore> dove le chiavi sono
     * gli utenti, i valori i rispettivi file associati ad ognuno di essi. I
     * valori saranno quindi degli ArrayList di File<E>;
     *
     */
    private HashMap<User, ArrayList<File<E>>> map;

    // AF: {[[c.map.get(k).getId(), c.map.get(k).getPsw()], [c.map.get(k).get(i) | per ogni 0 < i <= c.map.get(k).size()]] per ogni 0 <= k < c.map.keySet().size()}
    /**
     * IR: c != null && c.map != null && {per ogni i | 0 <= i * * *      * < c.map.keySet().size() -> [c.map.get(i) != null &&
     * c.map.get(i).getId() != null && c.map.get(i).getPsw() != null]} && {per
     * ogni i,j | i != j && 0 <= i,j < c.map.keySet().size() ->
     * [!(c.map.get(i).getId().equals(c.map.get(j).getId()))]} && {per ogni k
     * (appartenente a keySet) i | 0 <= i < c.map.get(k).size() ->
     * [c.map.get(k).get(i) != null && c.map.get(k).get(i).getData() != null &&
     * c.map.get(k).get(i).getRights() != null &&
     * (c.map.get(k).get(i).getRights().equals("w") ||
     * c.map.get(k).get(i).getRights().equals("r"))]}
     */
    /**
     * @effects Costruttore della classe. Crea quindi la collezione creando
     * l'hashMap inizialmente vuota.
     *
     * L'invariante é rispettato; questo perché il metodo crea la collezione e i
     * due array, i quali saranno quindi NON nulli (anche se vuoti). Per quanto
     * riguarda gli altri membri dell'espressione sono banalmente verificati
     * poiché non esistono effetivamente dati all'interno dell'array; Si puó
     * quindi dire che, dal momento in cui l'invariante é rispettato prima
     * dell'esecuzione di questa funzione, lo sará anche dopo;
     */
    public MySecondSecureFileContainer() {
        this.map = new HashMap<User, ArrayList<File<E>>>();
    }

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
     *
     * L'invariante é rispettato; questo grazie alla copertura data dalle
     * eccezioni. Non potró aggiungere un User nullo, né tanto meno con uno dei
     * due attributi nulli. Inoltre verrá creato l'array dei file, inizialmente
     * vuoto (e quindi rispetta banalmente l'invariante), associati all'utente.
     * Si puó quindi dire che, dal momento in cui l'invariante é rispettato
     * prima dell'esecuzione di questa funzione, lo sará anche dopo;
     */
    @Override
    public void createUser(String id, String passw) throws NullPointerException, ExistingUserException, IllegalArgumentException {
        if (id == null || passw == null) {
            throw new NullPointerException();
        }
        if (this.userSearch(id) != -1) {
            throw new ExistingUserException();
        }

        if ((id.replaceAll("\\s", "")).equals("")) {
            throw new IllegalArgumentException();
        }

        this.map.put(new User(id, passw), new ArrayList<File<E>>());

    }

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
     *
     * L'invariante é rispettato poiché non vengono effettuate modifiche sulla
     * collezione; Si puó quindi dire che, dal momento in cui l'invariante é
     * rispettato prima dell'esecuzione di questa funzione, lo sará anche dopo;
     */
    @Override
    public int getSize(String id, String passw) throws NullPointerException, WrongPswException, UserNotFoundException {
        if (id == null || passw == null) {
            throw new NullPointerException();
        }

        User tmp = takeUsr(id);
        if (tmp == null) {
            throw new UserNotFoundException();
        }

        if (tmp.getPsw().equals(passw)) {
            return this.map.get(tmp).size();
        } else {
            throw new WrongPswException();
        }
    }

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
     *
     * L'invariante é rispettato grazie alla copertura data dalle eccezioni. Non
     * potró mai aggiungere un file nullo, né tanto meno settare dei diritti di
     * accesso diversi da "w" e "r" poiché viene utilizzato il secondo
     * costrutto: i diritti saranno settati automaticamente come "w" al momento
     * della creazione del file. Si puó quindi dire che, dal momento in cui
     * l'invariante é rispettato prima dell'esecuzione di questa funzione, lo
     * sará anche dopo;
     */
    @Override
    public boolean put(String id, String passw, E file) throws NullPointerException, WrongPswException, UserNotFoundException {
        if (id == null || passw == null) {
            throw new NullPointerException();
        }
        User tmp = takeUsr(id);
        if (tmp == null) {
            throw new UserNotFoundException();
        }

        if (tmp.getPsw().equals(passw)) {
            this.map.get(tmp).add(new File(file));
            return true;

        } else {
            throw new WrongPswException();
        }

    }

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
     *
     * L'invariante é rispettato poiché non vengono applicate modifiche alla
     * collezione. (si restituisce una copia!) Si puó quindi dire che, dal
     * momento in cui l'invariante é rispettato prima dell'esecuzione di questa
     * funzione, lo sará anche dopo;
     *
     */
    @Override
    public E get(String id, String passw, E file) throws NullPointerException, WrongPswException, UserNotFoundException, FileNotFoundException {
        if (id == null || passw == null) {
            throw new NullPointerException();
        }
        User tmp = takeUsr(id);
        if (tmp == null) {
            throw new UserNotFoundException();
        }

        if (tmp.getPsw().equals(passw)) {
            int filePos = this.getFilePos(file, this.map.get(tmp));
            if (filePos == -1) {
                throw new FileNotFoundException();
            }
            return this.map.get(tmp).get(filePos).getData();

        } else {
            throw new WrongPswException();
        }

    }

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
     *
     * L'invariante é rispettato grazie alla copertura delle eccezioni;
     * sicuramente andró a togliere un file che esiste (anche se il file
     * eliminato fosse l'ultimo file della collezione, a fine esecuzione della
     * funzione la collezione sarebbe vuota: banalmente una collezione vuota
     * verifica sempre l'IR). Si puó quindi dire che, dal momento in cui
     * l'invariante é rispettato prima dell'esecuzione di questa funzione, lo
     * sará anche dopo;
     */
    @Override
    public E remove(String id, String passw, E file) throws NullPointerException, WrongPswException, UserNotFoundException, FileNotFoundException {
        if (id == null || passw == null) {
            throw new NullPointerException();
        }
        User tmp = takeUsr(id);
        if (tmp == null) {
            throw new UserNotFoundException();
        }

        if (tmp.getPsw().equals(passw)) {
            int filePos = this.getFilePos(file, this.map.get(tmp));
            if (filePos == -1) {
                throw new FileNotFoundException();
            }
            E fileRem = this.map.get(tmp).get(filePos).getData();
            this.map.get(tmp).remove(filePos);
            return fileRem;

        } else {
            throw new WrongPswException();
        }

    }

    /**
     * @effects Crea una copia del file richiesto e la associa all'utente (la
     * copia avrá stessi diritti del file originale). Puó lanciare una
     * NullPointerException se i dati inseriti sono nulli (ne basta uno), una
     * WrongPswException se la password inserita non é la corrispondente dell'id
     * oppure, una UserNotFoundException se l'id passato al metodo non
     * corrisponde ad un utente all'interno della collezione (ricordo che l'id é
     * univoco all'interno della collezione, per questo non serve controllare
     * anche la password per questa eccezione), ed infine una
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
     *
     * L'invariante é rispettato grazie alla copertura delle eccezioni; vediamo
     * la copia come una creazione + inseriemento di un file, indipendentemente
     * dal fatto che sia o meno uguale ad un altro (ció é permesso). Sicuramente
     * grazie ai controlli e alle relative eccezioni lanciate, non potró mai
     * aggiungere (copiare) un file nullo, o con attributi nulli. Per i diritti
     * di accesso anche posso stare tranquillo, poiché se sto copiando un file
     * precedentemente presente, la copia avrá i suoi stessi diritti. Si puó
     * quindi dire che, dal momento in cui l'invariante é rispettato prima
     * dell'esecuzione di questa funzione, lo sará anche dopo;
     */
    @Override
    public void copy(String id, String passw, E file) throws NullPointerException, WrongPswException, UserNotFoundException, FileNotFoundException {
        if (id == null || passw == null) {
            throw new NullPointerException();
        }
        User tmp = takeUsr(id);
        if (tmp == null) {
            throw new UserNotFoundException();
        }

        if (tmp.getPsw().equals(passw)) {
            int filePos = this.getFilePos(file, this.map.get(tmp));
            if (filePos == -1) {
                throw new FileNotFoundException();
            }

            this.map.get(tmp).add(new File(file, this.map.get(tmp).get(filePos).getRights()));

        } else {
            throw new WrongPswException();
        }
    }

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
     *
     * L'invariante é rispettato grazie alla copertura delle eccezioni; i motivi
     * sono pressoché gli stessi del metodo 'copy' poiché quello che sto facendo
     * é effettivamente una copia di un file (seppur in un'altra collezione!).
     * Per quanto riguarda i diritti di accesso, sono prefissati dal metodo e
     * quindi settati (validi) internamente. Si puó quindi dire che, dal momento
     * in cui l'invariante é rispettato prima dell'esecuzione di questa
     * funzione, lo sará anche dopo;
     */
    @Override
    public void shareR(String id, String passw, String other, E file) throws NullPointerException, WrongPswException, UserNotFoundException, FileNotFoundException, RightsErrorException, ShareWithYouException {
        if (id == null || passw == null) {
            throw new NullPointerException();
        }
        User tmp = takeUsr(id);
        if (tmp == null) {
            throw new UserNotFoundException();
        }

        if (tmp.getPsw().equals(passw)) {
            int filePos = this.getFilePos(file, this.map.get(tmp));
            if (filePos == -1) {
                throw new FileNotFoundException();
            }
            if (!(this.map.get(tmp).get(filePos).getRights().equals("w"))) {
                throw new RightsErrorException();
            }
            User tmp2 = takeUsr(other);
            if (tmp2 == null) {
                throw new UserNotFoundException();
            }
            if (tmp2.equals(tmp)) {
                throw new ShareWithYouException();
            }
            File shared = this.map.get(tmp).get(filePos);
            this.map.get(tmp2).add(new File(shared.getData(), "r"));

        } else {
            throw new WrongPswException();
        }
    }

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
     *
     * L'invariante é rispettato grazie alla copertura delle eccezioni; i motivi
     * sono pressoché gli stessi del metodo 'copy' poiché quello che sto facendo
     * é effettivamente una copia di un file (seppur in un'altra collezione!).
     * Per quanto riguarda i diritti di accesso, sono prefissati dal metodo e
     * quindi settati (validi) internamente. Si puó quindi dire che, dal momento
     * in cui l'invariante é rispettato prima dell'esecuzione di questa
     * funzione, lo sará anche dopo;
     */
    @Override
    public void shareW(String id, String passw, String other, E file) throws NullPointerException, WrongPswException, UserNotFoundException, FileNotFoundException, RightsErrorException, ShareWithYouException {
        if (id == null || passw == null) {
            throw new NullPointerException();
        }
        User tmp = takeUsr(id);
        if (tmp == null) {
            throw new UserNotFoundException();
        }

        if (tmp.getPsw().equals(passw)) {
            int filePos = this.getFilePos(file, this.map.get(tmp));
            if (filePos == -1) {
                throw new FileNotFoundException();
            }
            if (!(this.map.get(tmp).get(filePos).getRights().equals("w"))) {
                throw new RightsErrorException();
            }
            User tmp2 = takeUsr(other);
            if (tmp2 == null) {
                throw new UserNotFoundException();
            }
            if (tmp2.equals(tmp)) {
                throw new ShareWithYouException();
            }
            File shared = this.map.get(tmp).get(filePos);
            this.map.get(tmp2).add(new File(shared.getData(), "w"));

        } else {
            throw new WrongPswException();
        }
    }

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
    @Override
    public Iterator<E> getIterator(String id, String passw) throws NullPointerException, WrongPswException, UserNotFoundException {
        if (id == null || passw == null) {
            throw new NullPointerException();
        }
        User tmp = takeUsr(id);
        if (tmp == null) {
            throw new UserNotFoundException();
        }

        if (tmp.getPsw().equals(passw)) {
            return new usrIterator<E>(this.onlyData(this.map.get(tmp)));

        } else {
            throw new WrongPswException();
        }
    }
    //Classe per la gestione dell'iteratore

    private static class usrIterator<E> implements Iterator<E> {

        /**
         * Overview: classe che rappresentante un'iteratore personalizzato.
         * Implementa l'interfaccia Iterator e serve a creare un iteratore come
         * richiesto dal progetto. La cosa importnte é la scelta di ridefinire
         * il metodo 'remove' come 'Unsupported Operation' ; difatti il progetto
         * chiedeva di definire un iteratore SENZA REMOVE.
         */
        private ArrayList<E> userFiles;
        private int currentIndex;

        public usrIterator(ArrayList<E> array) {

            this.userFiles = new ArrayList<E>(array);    //creo un nuovo arr uguale ad array
            this.currentIndex = -1; //cosicché inizio con next subito;
        }

        @Override
        public boolean hasNext() {
            return this.currentIndex + 1 < this.userFiles.size();
        }

        @Override
        public E next() throws NoSuchElementException {
            if (this.hasNext()) {
                this.currentIndex++;
                return this.userFiles.get(currentIndex);
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }
        
        

    }

    //OTHER FUNCTIONS
    /**
     * @effects Cerca all'interno della collezione un utente con id indicato e
     * ritorna un int che ne indica la presenza oppure -1 se l'utente non é
     * stato trovato. Puó lanciare una NullPointerException se i dati inseriti
     * sono nulli (ne basta uno).
     * @param id
     * @return int 0: se é presente all'interno della collezione un utente
     * avente come username 'id', con i che ne indica la posizione | -1:
     * altrimenti
     * @throws NullPointerException if (id == null) [unchecked]
     */
    @Override
    public int userSearch(String id) throws NullPointerException {
        if (id == null) {
            throw new NullPointerException();
        }
        int found = -1;
        Set<User> users = this.map.keySet();
        Iterator<User> it = users.iterator();
        while (it.hasNext() && found == -1) {
            if (it.next().getId().equals(id)) {
                found = 0;
            }
        }
        return found;
    }

    /**
     * @effects Cerca all'interno di una collezione di file (non ArrayList di
     * ArrayList, solamente singolo ArrayList) un file e ritorna un int che ne
     * indica la posizione oppure -1 se il file non é stato trovato.
     * @param file
     * @param fileColl
     * @return int i >= 0: se é presente all'interno della collezione un file
     * avente come dato effettivo 'file', con i che ne indica la posizione | -1:
     * altrimenti
     * @throws NullPointerException if (file == null || fileColl == null)
     * [unchecked]
     *
     * L'invariante é rispettato poiché non vengono applicate modifiche alla
     * collezione.
     */
    @Override
    public int fileSearch(E file, ArrayList<E> fileColl) throws NullPointerException {
        if (file == null || fileColl == null) {
            throw new NullPointerException();
        }
        int pos = -1;
        int c = 0;
        while (pos == -1 && c < fileColl.size()) {
            if (fileColl.get(c).equals(file)) {
                pos = c;
            }
            c++;
        }
        return pos;
    }

    /**
     * @effects Restituisce, dato un ArrayList<File<E>> un ArrayList<E>, prende
     * quindi da tutti gli oggetti File solo il campo 'data', quello con la info
     * effettiva, e crea un altro array contentente questi. Puó lanciare una
     * NullPointerException se i dati inseriti sono nulli (ne basta uno).
     * @param array
     * @return Un ArrayList<E> contenente solo i dati effettivi (di tipo E) di
     * un ArrayList<File<E>>
     * @throws NullPointerException if (array == null) [unchecked]
     *
     * L'invariante é rispettato poiché non vengono applicate modifiche alla
     * collezione.
     */
    public ArrayList<E> onlyData(ArrayList<File<E>> arr) throws NullPointerException {
        ArrayList<E> tmp = new ArrayList<E>();

        if (arr == null) {
            throw new NullPointerException();
        }

        int c = 0;
        while (c < arr.size()) {
            tmp.add(arr.get(c).getData());
            c++;
        }

        return tmp;
    }

    /**
     * @effects Altra funzione di ricerca utente all'interno della collezione.
     * Questa volta mi serve per avere come valore di ritorno al posto della
     * posizione (non funzionale con le hashmap) l'oggetto utente stesso (grazie
     * all'uso dell'iteratore).
     * @param id
     * @return L'utente avente quell'id, o null se non lo trova
     * @throws NullPointerException if (id == null) [unchecked]
     */
    public User takeUsr(String id) throws NullPointerException {
        User res = null;
        boolean found = false;
        Set<User> users = this.map.keySet();
        Iterator<User> it = users.iterator();
        while (!found && it.hasNext()) {
            User current = it.next();
            if (current.getId().equals(id)) {
                res = current;
                found = true;
            }
        }
        return res;
    }

    /**
     * @effects Cerca un file senza 'passare' per il metodo 'onlyData'.
     * @param file
     * @param arr
     * @return La posizione del file cercato (posizione dell'oggetto File
     * all'interno dell'ArrayList<File<E>> avente come campo data il file
     * cercato) se esiste, altrimenti -1
     * @throws NullPointerException if (file == null || arr == null)
     */
    public int getFilePos(E file, ArrayList<File<E>> arr) throws NullPointerException {
        int pos = -1;

        int c = 0;
        boolean found = false;
        while (!found && c < arr.size()) {
            if (arr.get(c).getData().equals(file)) {
                found = true;
                pos = c;
            }
            c++;
        }

        return pos;

    }
}
