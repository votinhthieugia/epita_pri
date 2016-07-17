/**
 * MD5Encryptor.java 
 */

package fr.epita.pri.rackrepresentator.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The {@code MD5Encryptor} class implements the interface IEncryptor.
 * 
 * @author Anh Hoang DOAN
 * @version 1.0
 */
public class MD5Encryptor implements IEncryptor {
	/**
	 * Encrypt the password string by using Md5.
	 * @param	rawPassword		the raw password to encrypt.
	 * @return	an encrypted password string.
	 */
	@Override
	public String encryptUserPassword(String rawPassword) {
		String encrypted = rawPassword;
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(rawPassword.getBytes());
			byte[] byteData = md.digest();
			StringBuilder sb = new StringBuilder();
			
	        for (int i = 0; i < byteData.length; i++) {
	        	sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        
	        encrypted = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return encrypted; 
	}
}