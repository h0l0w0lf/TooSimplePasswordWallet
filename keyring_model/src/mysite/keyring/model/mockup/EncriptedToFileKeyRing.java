package mysite.keyring.model.mockup;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import mysite.keyring.model.Key;
import mysite.keyring.model.KeyRing;

public class EncriptedToFileKeyRing implements KeyRing {

	private static final long serialVersionUID = 1L;

	private static final String keyStore = "keystore";   
	private static final String  password = "1234567812345678";
	
	Map<String, Key> keys;
	
	@SuppressWarnings("unchecked")
	public EncriptedToFileKeyRing() {
		File f = new File(keyStore);
		if(!f.exists())
		{
			System.out.println("non tovato " + keyStore + " creo nuovo keyring");
			keys = new HashMap<>();
			return;
		}
		
		
		try {
			Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
			
			try {
				SecretKeySpec sks = new SecretKeySpec(password.getBytes("UTF-8"), "AES");
				
				try {
					c.init(Cipher.DECRYPT_MODE, sks, new IvParameterSpec(password.getBytes("UTF-8")));
					
					FileInputStream fileInStream = null;
					try {
						fileInStream = new FileInputStream(keyStore);

						byte[] plainIn = new byte[16368]; 
						int n;
						n = fileInStream.read(plainIn);
						if(n==-1)
						{
							throw new RuntimeException("keystore troppo grande");
						}
						
						try {
							byte[] encIn = c.doFinal(plainIn, 0, n);
							
							ObjectInputStream inStream = null;
							inStream = new ObjectInputStream(new ByteArrayInputStream(encIn));

							try {
								keys = (Map<String, Key>)inStream.readObject();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						} catch (IllegalBlockSizeException | BadPaddingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						if(fileInStream != null)
						{
							try {
								fileInStream.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}

				} catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
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
	public void close(boolean save) {
		if(!save)
		{
			return;
		}
		
		ObjectOutputStream outStream;
		try {

			ByteArrayOutputStream byteStream = new ByteArrayOutputStream(16368);
			outStream = new ObjectOutputStream(byteStream);
			outStream.writeObject(keys);
			byte[] plainOut = byteStream.toByteArray();
			
			try {
				Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
				
				SecretKeySpec sks = new SecretKeySpec(password.getBytes("UTF-8"), "AES");
				try {
					c.init(Cipher.ENCRYPT_MODE, sks,new IvParameterSpec(password.getBytes("UTF-8")));
					
					FileOutputStream fileOutStream = null;
					try {
						byte[] encOut = c.doFinal(plainOut);
						
						fileOutStream = new FileOutputStream(keyStore);
						fileOutStream.write(encOut);
					} catch (IllegalBlockSizeException | BadPaddingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						if(fileOutStream!=null)
						{
							try {
								fileOutStream.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}

				} catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}