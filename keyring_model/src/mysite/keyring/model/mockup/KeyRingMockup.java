package mysite.keyring.model.mockup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import mysite.keyring.model.Key;
import mysite.keyring.model.KeyRing;

public class KeyRingMockup implements KeyRing {

	private static final long serialVersionUID = 1L;

	Map<String, Key> keys;
	
	Key[] keysArray = {
			new KeyMockup("email", "www.nomail.it", "utentemail", "passmail"),
			new KeyMockup("banca", "www.nobank.it", "utentebank", "passbank"),
			new KeyMockup("lavoro", "www.nowork.it", "utentework", "passwork")
	};
	
	public KeyRingMockup() {
		keys = new HashMap<>();
	}
	
	@Override
	public void addKey(Key key) {
		keys.put(key.getNome(), key);
	}

	@Override
	public void removeKey(String key) throws NoSuchElementException {
		if(keys.containsKey(key))
		{
			keys.remove(key);
		}
		else
		{
			throw new NoSuchElementException(key);
		}
	}

	@Override
	public Key getKey(String key) throws NoSuchElementException {
		if(keys.containsKey(key))
		{
			return keys.get(key);
		}
		else
		{
			throw new NoSuchElementException(key);
		}
	}

	@Override
	public List<Key> listKeys() {
		return new ArrayList<Key>(keys.values());
	}

	@Override
	public boolean init(String password) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean open(String password) {
		for(Key key:keysArray)
		{
			keys.put(key.getNome(), key);
		}

		return true;
	}

	@Override
	public void close(boolean save) {		
		keys.clear();
	}

}
