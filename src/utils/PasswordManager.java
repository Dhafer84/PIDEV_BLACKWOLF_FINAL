package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordManager {
	// Hashing password
	// Using the _SHA_1_ algorithm
	public static String SecurePassword(String passwordToHash) {
		String generatedPassword = null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-1");

			byte[] bytes = md.digest(passwordToHash.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return generatedPassword;
	}

	// Hashing password
	public static Boolean verifyPassword(String inputPassword, String hashedPassword) {
		String inputPasswordHashed = PasswordManager.SecurePassword(inputPassword);
		if(inputPasswordHashed.equals(hashedPassword))
			return true;
		return false;
	}
}
