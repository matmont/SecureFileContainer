/**
 * @author Matteo Montalbetti
 * @mat. 559881
 * @corso B
 */
package pr2.project;

import java.util.Objects;

public class User {

    /**
     * Overview: User é una classe che astrae un Utente possessore di un qualche
     * (o nessun) file. Ogni utente avrá i propri dati di accesso (la propria
     * identitá) 'id' e 'passw', da utilizzare per effettuare le varie
     * operazioni.
     */
    private final String id;
    private final String passw;

    // AF: {c.getId(), c.getPsw()}
    /**
     * IR: c != null && c.getId() != null && c.getPsw() != null
     */
    /**
     * @effects Costruttore della classe. Crea un nuovo utente settando id e
     * passw in base ai parametri passati al metodo. Puó lanciare una
     * NullPointerException se i dati passati al metodo sono nulli (ne basta
     * uno).
     * @param id
     * @param passw
     * @throws NullPointerException
     *
     * L'invariante é rispettato grazie alla copertura dell'eccezione
     * NullPointerException. Se quindi precedentemente l'oggetto rispettava
     * l'invariante, lo fará anche successivamente.
     */
    public User(String id, String passw) throws NullPointerException {
        if (id == null || passw == null) {
            throw new NullPointerException();
        }
        this.id = id;
        this.passw = passw;
    }

    /**
     * @effects Ritorna l'id dell'utente
     * @return id dell'utente
     *
     * L'invariante é rispettato poiché non vengono effettuate modifiche
     * sull'oggetto. Se quindi precedentemente l'oggetto rispettava
     * l'invariante, lo fará anche successivamente.
     */
    public String getId() {
        return this.id;
    }

    /**
     * @effects Ritorna la password dell'utente
     * @return passw dell'utente
     *
     * L'invariante é rispettato poiché non vengono effettuate modifiche
     * sull'oggetto. Se quindi precedentemente l'oggetto rispettava
     * l'invariante, lo fará anche successivamente.
     */
    public String getPsw() {
        return this.passw;
    }

    /**
     * @effects Override del metodo Object hashCode.
     * @return L'hash code generato per l'oggetto
     *
     * L'invariante é rispettato poiché non vengono effettuate modifiche
     * sull'oggetto. Se quindi precedentemente l'oggetto rispettava
     * l'invariante, lo fará anche successivamente.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, passw);
    }

    /**
     * @effects Override del metodo Object equals. Ridefinito per controllare
     * l'uguaglianza tra due oggetti di tipo user: si controlla sia id che
     * password per sicurezza. (basterebbe solo l'id)
     * @param obj
     * @return Boolean che mi comunica l'uguaglianza o meno dei due oggetti.
     * @throws NullPointerException
     *
     * L'invariante é rispettato poiché non vengono effettuate modifiche
     * sull'oggetto. Se quindi precedentemente l'oggetto rispettava
     * l'invariante, lo fará anche successivamente.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        if (obj instanceof User) {

            User tmp = (User) obj;
            return this.id.equals(tmp.getId()) && this.passw.equals(tmp.getPsw());

        } else {

            return false;

        }
    }

}
