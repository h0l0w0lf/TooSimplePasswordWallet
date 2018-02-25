package mysite.test;

import mysite.keyring.model.Key;
import mysite.keyring.model.KeyRing;
import mysite.keyring.model.mockup.EncriptedToFileKeyRing;
import mysite.keyring.model.mockup.KeyRingMockup;

public class TestKeyring {

	private static String password = "1234567812345678";
	
	public static void main(String[] args) {
		KeyRing efkrw = new EncriptedToFileKeyRing();
		if(efkrw.open(password) != true)
		{
			System.out.println("keyring non aperto");
			efkrw.init(password);
			
			return;
		}	

		KeyRing krm = new KeyRingMockup();	
		krm.open(password);
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
		if(efkrr.open(password) != true)
		{
			System.out.println("keyring non aperto");
			return;
		}	

		for(Key k: efkrr.listKeys())
		{
			System.out.println(k);
		}

		efkrr.close(false);
	}

}
