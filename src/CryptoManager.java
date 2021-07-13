
public class CryptoManager {

	private static final char LOWER_BOUND = ' ';
	private static final char UPPER_BOUND = '_';
	private static final int RANGE = UPPER_BOUND - LOWER_BOUND + 1;

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes
	 * according to the LOWER_BOUND and UPPER_BOUND characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean stringInBounds (String plainText) {
		for(int i = 0; i<plainText.length();  i++){
			if(plainText.charAt(i) < LOWER_BOUND || plainText.charAt(i) > UPPER_BOUND)
				return false;
		}
		return true;
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static int putInBounds(int key){
		while(key > UPPER_BOUND) {
			key -= (UPPER_BOUND - LOWER_BOUND);
		}
		return key;
	}
	public static String encryptCaesar(String plainText, int key) { //HELLO, 6
//		key = putInBounds(key);
		String encryptedCeaser = "";
		if(stringInBounds(plainText)){
			for(int i = 0; i < plainText.length();  i++){
				int ascii = (int) plainText.charAt(i); //H == 72
				int asciiCombined = (int) plainText.charAt(i)+ key; //H == 72
				while(asciiCombined > UPPER_BOUND){
					asciiCombined -= RANGE;
				}

					encryptedCeaser += Character.toString((char) (asciiCombined));
			}
		}
		return encryptedCeaser;
	}

	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String encryptBellaso(String plainText, String bellasoStr) {
		String encryptedBellaso = "";
		String elongateUsingBellasoStr = "";
		int j = 0;
		while(elongateUsingBellasoStr.length() != plainText.length()){
			elongateUsingBellasoStr += bellasoStr.charAt(j);
			j++;
			if(j == bellasoStr.length()){
				j= 0;
			}
		}

		for(int i = 0; i < plainText.length();  i++){
			if(stringInBounds(plainText)){
				int asciiCombined = (int) plainText.charAt(i) + (int) elongateUsingBellasoStr.charAt(i); //H == 72
				if(asciiCombined > UPPER_BOUND) {
					while(asciiCombined > UPPER_BOUND){
						asciiCombined -= RANGE;
					}
				}
				encryptedBellaso += (char)asciiCombined;
			}
		}
		return encryptedBellaso;
	}

	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String decryptCaesar(String encryptedText, int key) {
		String decryptedCeaser = "";
//		key = putInBounds(key);
//
//		for(int i = 0; i < encryptedText.length();  i++){
//			int ascii = (int) encryptedText.charAt(i); //H == 72
//			if(stringInBounds(encryptedText)){
//				decryptedCeaser += Character.toString((char) (ascii-key));
//			}
//		}

		if(stringInBounds(encryptedText)){
			for(int i = 0; i < encryptedText.length();  i++){
				int asciiCombined = (int) encryptedText.charAt(i)- key; //H == 72
				while(asciiCombined < LOWER_BOUND){
					asciiCombined += RANGE;
				}

				decryptedCeaser += Character.toString((char) (asciiCombined));
			}
		}
		return decryptedCeaser;
	}

	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String decryptBellaso(String encryptedText, String bellasoStr) {
		String decryptedBellaso = "";
		int j = 0;
		while(bellasoStr.length() < encryptedText.length()){
			bellasoStr += bellasoStr.charAt(j);
			j++;
			if(j == bellasoStr.length()){
				j= 0;
			}
		}

		for(int i = 0; i < encryptedText.length();  i++){
			if(stringInBounds(encryptedText)){
				int asciiCombined = (int) encryptedText.charAt(i) - (int) bellasoStr.charAt(i); //H == 72
				if(asciiCombined < LOWER_BOUND) {
					while(asciiCombined < LOWER_BOUND){
						asciiCombined += RANGE;
					}
				}
				decryptedBellaso += (char)asciiCombined;
			}
		}
		return decryptedBellaso;
	}
}