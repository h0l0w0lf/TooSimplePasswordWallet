package mysite.keyring.model;

import java.io.Serializable;

public abstract class Key implements Serializable {
	private static final long serialVersionUID = 1L;

	protected String nome;

	protected String password;

	protected String descrizione;

	protected String utente;

	protected Key() {
	}
	
	public Key(String nome, String descrizione, String utente, String password) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.utente = utente;
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public String getPassword() {
		return password;
	}

	public String getdescrizione() {
		return this.descrizione;
	}

	public String getUtente() {
		return utente;
	}

	@Override
	public String toString() {
		return this.getClass().getCanonicalName() +   " [nome=" + nome + ", password=" + password + ", descrizione=" + descrizione + ", utente=" + utente + "]";
	}

}