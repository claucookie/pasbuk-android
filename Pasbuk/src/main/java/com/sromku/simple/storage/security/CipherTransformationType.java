package com.sromku.simple.storage.security;

/**
 * Supported types:
 * http://docs.oracle.com/javase/7/docs/api/javax/crypto/Cipher.html<br>
 * <br>
 * 
 * Every implementation of the Java platform is required to support the
 * following standard Cipher transformations with the keysizes in parentheses:
 * <ul>
 * <li>AES/CBC/NoPadding (128)</li>
 * <li>AES/CBC/PKCS5Padding (128)</li>
 * <li>AES/ECB/NoPadding (128)</li>
 * <li>AES/ECB/PKCS5Padding (128)
 * <li>DES/CBC/NoPadding (56)</li>
 * <li>DES/CBC/PKCS5Padding (56)</li>
 * <li>DES/ECB/NoPadding (56)
 * <li>DES/ECB/PKCS5Padding (56)</li>
 * <li>DESede/CBC/NoPadding (168)</li>
 * <li>DESede/CBC/PKCS5Padding (168)</li>
 * <li>DESede/ECB/NoPadding (168)</li>
 * <li>DESede/ECB/PKCS5Padding (168)</li>
 * <li>RSA/ECB/PKCS1Padding (1024, 2048)</li>
 * <li>RSA/ECB/OAEPWithSHA-1AndMGF1Padding (1024, 2048)</li>
 * <li>RSA/ECB/OAEPWithSHA-256AndMGF1Padding (1024, 2048)</li>
 * </ul>
 * 
 * @author Roman Kushnarenko - sromku (sromku@gmail.com)
 * 
 */
public class CipherTransformationType {
	private static final String SEPARATOR = "/";

	public static final String AES_CBC_PKCS5Padding = CipherAlgorithmType.AES + SEPARATOR + CipherModeType.CBC + SEPARATOR + CipherPaddingType.PKCS5Padding;

}
