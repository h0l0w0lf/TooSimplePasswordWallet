package mysite.keyring.model.mockup;

import mysite.keyring.model.Key;

public final class KeyMockup extends Key {

	private static final long serialVersionUID = 1L;

	KeyMockup(String nome, String descrizione, String utente, String password) {
		super(nome, descrizione, utente, password);
	}
		
}