public class CaesarCipher {
    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                result.append((char) (base + (ch - base + shift) % 26));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - shift);
    }

    public static void main(String[] args) {
        String plaintext = "HelloWorld";
        int shift = 3;
        String encrypted = encrypt(plaintext, shift);
        String decrypted = decrypt(encrypted, shift);

        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
        System.out.println("Verification: " + plaintext.equals(decrypted));
    }
}


//Algorithm:
//        Take input plaintext and shift value.
//
//        Convert each character to its ASCII value.
//
//        Shift each character forward by the shift value.
//
//        If shifting goes beyond 'Z' or 'z', wrap around.
//
//        Construct the encrypted text.
//
//        For decryption, shift characters backward by the same shift value.
//
//        Ensure wrapping works correctly for uppercase and lowercase.
//
//        Handle non-alphabetic characters (keep them unchanged).
//
//        Display encrypted and decrypted text.
//
//        Verify correctness by encrypting and then decrypting.