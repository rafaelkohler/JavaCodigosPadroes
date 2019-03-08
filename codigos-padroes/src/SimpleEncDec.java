import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class SimpleEncDec {

	public String encrypt (String key, String initVector, String value) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(value.getBytes());
			
			return Base64.encode(encrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public String decrypt(String key, String initVector, String encrypted) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] original = cipher.doFinal();
			
			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		SimpleEncDec encDec = new SimpleEncDec();
		
		String key = "IDEA1234IDEA1234";
		String initVector = "IDEADevRandVectr";
		
		String encrypted = encDec.encrypt(key, initVector, "IDEA Developers");
		System.out.println(encrypted);
		
		String decrypted = encDec.decrypt(key, initVector, encrypted);
		System.out.println(decrypted);
				
	}
}
