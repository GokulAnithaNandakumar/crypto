public class VigenereCipher {
    public static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toUpperCase();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            char shift = key.charAt(i % key.length());
            result.append((char) ('A' + (ch - 'A' + shift - 'A') % 26));
        }
        return result.toString();
    }

    public static String decrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toUpperCase();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            char shift = key.charAt(i % key.length());
            result.append((char) ('A' + (ch - shift + 26) % 26));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String plaintext = "HELLO";
        String key = "KEY";
        String encrypted = encrypt(plaintext, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
        System.out.println("Verification: " + plaintext.equals(decrypted));
    }
}
//gorithm:
//        Choose a keyword.
//
//        Repeat the keyword until it matches plaintext length.
//
//        Convert plaintext and keyword characters into numbers.
//
//        Apply Caesar cipher shifting based on keyword letters.
//
//        Convert back into characters for encryption.
//
//        Decrypt by reversing the shift.
//
//        Maintain case sensitivity.
//
//        Handle spaces and non-alphabetic characters.
//
//        Test different keyword lengths.
//
//        Verify correctness by encrypting and decrypting.

