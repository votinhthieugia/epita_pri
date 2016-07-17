/**
 * IEncryptor.java 
 */

package fr.epita.pri.rackrepresentator.encryption;

/**
 * The {@code IEncryptor} class defines an interface to encrypt data.
 * It declares a method to encrypt user password.    
 * 
 * @author Anh Hoang DOAN
 * @version 1.0
 */
public interface IEncryptor {
	/**
	 * Encrypt the password string.
	 * @param	rawPassword		the raw password to encrypt.
	 * @return	an encrypted password string.
	 */
	public String encryptUserPassword(String rawPassword);
}