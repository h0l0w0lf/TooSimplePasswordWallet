package mysite.keyring.model;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

public abstract class Key implements Serializable {
	private static final long serialVersionUID = 1L;

	protected String nome;

	protected byte[] encryptedPassword;

	protected String sito;

	protected byte[] encryptedUser;

	protected Key() {
	}
	
	public Key(String nome, String sito, String utente, String password) {
		this.nome = nome;
		this.sito = sito;
		try {
			this.encryptedPassword = password.getBytes("US-ASCII");
			this.encryptedUser = utente.getBytes("US-ASCII");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getNome() {
		return this.nome;
	}

	public String getPassword() {
		try {
			return new String(encryptedPassword, "US-ASCII");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public String getSito() {
		return this.sito;
	}

	public String getUtente() {
		try {
			return new String(encryptedUser, "US-ASCII");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}