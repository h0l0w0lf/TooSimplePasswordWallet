package mysite.test;

import mysite.keyring.model.Key;
import mysite.keyring.model.KeyRing;
import mysite.keyring.model.mockup.EncriptedToFileKeyRing;
import mysite.keyring.model.mockup.KeyRingMockup;

public class TestKeyring {

	public static void main(String[] args) {
		KeyRing krm = new KeyRingMockup();
		KeyRing efkrw = new EncriptedToFileKeyRing();
		
		for(Key k: krm.listKeys())
		{
			System.out.println(k);
			efkrw.addKey(k);
		}
		krm.close(false);
		
		for(Key k: efkrw.listKeys())
		{
			System.out.println(k);
		}

		efkrw.close(true);
		
		KeyRing efkrr = new EncriptedToFileKeyRing();
		
		for(Key k: efkrr.listKeys())
		{
			System.out.println(k);
		}

		efkrr.close(false);
		
	}

}
