package mysite.keyring.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;

public interface KeyRing extends Serializable{

	void addKey(Key key);

	public void removeKey(String key) throws NoSuchElementException;
	
	public Key getKey(String key) throws NoSuchElementException;

	public List<Key> listKeys();
	
	public void close(boolean save);
}
