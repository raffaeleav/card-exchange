package registrazione;

/**
 * La classe Utente rappresenta un utente nel sistema di Card eXchange,
 * contiene informazioni come il nome dell'utente, la password, email, nome e cognome.
 * Include metodi di getter e setter, utilizzati per visualizzare e/o settare parametri dell'istanza.
 * @author Michele Menzione
 */
public class Utente {
    private int idUtente;
    private String username,password,nome,cognome,email;

    public Utente() {
    }

    public Utente(String username, String password, String nome, String cognome, String email) {
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }
    public Utente(int idUtente, String username, String password, String nome, String cognome, String email) {
        this.idUtente = idUtente;
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
