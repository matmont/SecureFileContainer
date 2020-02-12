/**
 * @author Matteo Montalbetti
 * @mat. 559881
 * @corso B
 */
package pr2.project;

import java.util.Objects;

public class File<E> {

    /**
     * Overview: File é una classe che rappresenta i diversi file contenuti
     * all'interno della collezione, affiancati dai relativi diritti di accesso
     * del dato; Un oggetto di tipo File sará quindi non solo il file vero e
     * proprio, bensí il file vero e proprio e i diritti di accesso su quel file
     * (aventi il proprietario);
     */
    private E data;
    private String accessRights;    // "w" per indicare diritti in scrittura ( e quindi anche in lettura)
    //, "r" per indicare diritti solo in lettura 
    //  altre stringhe non sono consentite e "punite" con una IllegalArgumentException.
    //  Inoltre un diritto di tipo 'w' denota anche il titolo di proprietario.

    // AF: {c.getData(), c.getRights()}
    /**
     * IR: c != null && c.getData() != null && c.getRights() != null &&
     * (c.getRights().equals("w") || c.getRights().equals("r"))
     */
    /**
     * @effects Costruttore che crea un oggetto di tipo File. Versione del
     * costruttore con entrambi i parametri definiti. Puó lanciare una
     * NullPointerException se i dati passati al metodo sono nulli (ne basta
     * uno) oppure una IllegalArgumentException nel caso in cui venga passato
     * come parametro 'accessRights' una stringa che non sia né "w", né "r".
     * (Vedere Overview e IR) N.B -> nel metodo ho deciso di adottare l'utilizzo
     * di toLowerCase() nel controllo di accessRights per comoditá dell'utente:
     * puó a volte capitare ad esempio di avere il BlockMaiusc attivo!
     * @param data
     * @param accessRights
     * @throws NullPointerException if (id == null || passw == null) [unchecked]
     * @throws IllegalArgumentException if
     * (!accessRights.toLowerCase().equals("w") &&
     * !accessRights.toLowerCase().equals("r")) [unchecked]
     *
     * L'invariante é rispettato grazie alla copertura delle varie eccezioni. Se
     * quindi precedentemente l'oggetto rispettava l'invariante, lo fará anche
     * successivamente.
     */
    public File(E data, String accessRights) throws NullPointerException, IllegalArgumentException {
        if (data == null || accessRights == null) {
            throw new NullPointerException();
        }
        if (!(accessRights.toLowerCase().equals("w")) && !(accessRights.toLowerCase().equals("r"))) {
            throw new IllegalArgumentException();
        }

        this.data = data;
        this.accessRights = accessRights;

    }

    /**
     * @effects Costruttore numero due. É stato creato per una questione di
     * comoditá. Ci sono casi in cui (ad esempio creazione del file) il file
     * sará sempre creato con diritti "w" per il proprietario; Puó lanciare una
     * NullPointerException se i dati passati al metodo sono nulli (ne basta
     * uno). L'eccezione e il settaggio dei diritti fatto internamente al
     * metodo, mi assicurano il rispetto dell'invariante.
     * @param data
     * @throws NullPointerException if (id == null || passw == null) [unchecked]
     *
     * L'invariante é rispettato grazie alla copertura delle varie eccezioni. Se
     * quindi precedentemente l'oggetto rispettava l'invariante, lo fará anche
     * successivamente.
     */
    public File(E data) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException();
        }
        this.data = data;
        this.accessRights = "w";
    }

    /**
     * @effects Metodo get usato per recuperare l'effettivo file di tipo E.
     *
     * @return Ritorna l'effettivo file.
     *
     * L'invariante é rispettato poiché non vengono effettuate modifiche
     * sull'oggetto. Se quindi precedentemente l'oggetto rispettava
     * l'invariante, lo fará anche successivamente.
     */
    public E getData() {
        return this.data;
    }

    /**
     * @effects Metodo get usato per recuperare i diritti di accesso del
     * relativo File.
     * @return Ritorna i diritti di accesso del File.
     *
     * L'invariante é rispettato poiché non vengono effettuate modifiche
     * sull'oggetto. Se quindi precedentemente l'oggetto rispettava
     * l'invariante, lo fará anche successivamente.
     */
    public String getRights() {
        return this.accessRights;
    }

    /**
     * @effects Metodo set per settare nuovi diritti ad un determinato file. Puó
     * lanciare una NullPointerException se i dati passati al metodo sono nulli
     * (ne basta uno) oppure una IllegalArgumentException nel caso in cui venga
     * passato come parametro accessRights una stringa che non sia né "w", né
     * "r".
     * @param newRights
     * @throws NullPointerException if (id == null || passw == null) [unchecked]
     * @throws IllegalArgumentException if
     * (!accessRights.toLowerCase().equals("w") &&
     * !accessRights.toLowerCase().equals("r")) [unchecked]
     *
     * L'invariante é rispettato grazie alla copertura delle varie eccezioni. Se
     * quindi precedentemente l'oggetto rispettava l'invariante, lo fará anche
     * successivamente.
     */
    public void setRights(String newRights) throws NullPointerException, IllegalArgumentException {
        if (newRights == null) {
            throw new NullPointerException();
        }
        if (!(newRights.toLowerCase().equals("w")) && !(newRights.toLowerCase().equals("r"))) {
            throw new IllegalArgumentException();
        }
        this.accessRights = newRights;
    }

    /**
     * @effects Override del metodo Object equals. Ridefinito per controllare
     * l'uguaglianza tra due oggetti di tipo file: si controlla solo il dato.
     * Non mi interessa andare a comparare i diritti di accesso poiché non sono
     * realmente parte dell'inforamzione del File, sono solo modi in cui é
     * possibile accedervi.
     * @param obj
     * @return Boolean che mi comunica l'uguaglianza o meno dei due oggetti.
     * @throws NullPointerException
     *
     * L'invariante é rispettato poiché non vengono effettuate modifiche
     * sull'oggetto. Se quindi precedentemente l'oggetto rispettava
     * l'invariante, lo fará anche successivamente.
     */
    @Override
    public boolean equals(Object obj) throws NullPointerException {

        if (obj == null) {
            throw new NullPointerException();
        }
        if (obj instanceof File) {

            File tmp = (File) obj;
            return this.data.equals(tmp.getData()) && this.accessRights.equals(tmp.getRights());

        } else {

            return false;

        }
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
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.data);
        hash = 17 * hash + Objects.hashCode(this.accessRights);
        return hash;
    }

}
