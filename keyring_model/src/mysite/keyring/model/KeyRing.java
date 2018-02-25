package mysite.keyring.model;

import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;

public interface KeyRing extends Serializable{

	void addKey(Key key);

	public void removeKey(String key) throws NoSuchElementException;
	
	public Key getKey(String key) throws NoSuchElementException;

	public List<Key> listKeys();
	
	public boolean open(String password);
	
	public void close(boolean save);

	public boolean init(String password);

}
