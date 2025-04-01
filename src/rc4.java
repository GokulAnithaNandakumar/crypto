import java.util.Scanner;

public class RC4Cipher {
    private int[] S = new int[256];
    private int[] K = new int[256];

    // Step 1-3: Key Scheduling Algorithm (KSA)
    public void keyScheduling(String key) {
        int keyLength = key.length();
        for (int i = 0; i < 256; i++) {
            S[i] = i;
            K[i] = key.charAt(i % keyLength);
        }
        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + S[i] + K[i]) % 256;
            swap(S, i, j);
        }
    }

    // Step 4-5: Pseudo-Random Generation Algorithm (PRGA)
    public byte[] generateKeystream(int textLength) {
        byte[] keystream = new byte[textLength];
        int i = 0, j = 0;
        for (int k = 0; k < textLength; k++) {
            i = (i + 1) % 256;
            j = (j + S[i]) % 256;
            swap(S, i, j);
            keystream[k] = (byte) S[(S[i] + S[j]) % 256];
        }
        return keystream;
    }

    // Step 6-7: Encryption/Decryption
    public byte[] encryptDecrypt(String text, byte[] keystream) {
        byte[] result = new byte[text.length()];
        for (int i = 0; i < text.length(); i++) {
            result[i] = (byte) (text.charAt(i) ^ keystream[i]);
        }
        return result;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Step 10: Main method to test RC4 Cipher
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RC4Cipher rc4 = new RC4Cipher();

        System.out.print("Enter plaintext: ");
        String plaintext = scanner.nextLine();
        System.out.print("Enter key (same length as plaintext): ");
        String key = scanner.nextLine();

        if (key.length() != plaintext.length()) {
            System.out.println("Key length must be the same as plaintext!");
            return;
        }

        rc4.keyScheduling(key);
        byte[] keystream = rc4.generateKeystream(plaintext.length());
        byte[] ciphertext = rc4.encryptDecrypt(plaintext, keystream);
        byte[] decryptedText = rc4.encryptDecrypt(new String(ciphertext), keystream);

        System.out.println("\nPlaintext in bytes: " + bytesToString(plaintext.getBytes()));
        System.out.println("Keystream: " + bytesToString(keystream));
        System.out.println("Ciphertext: " + bytesToString(ciphertext));
        System.out.println("Decrypted Plaintext in bytes: " + bytesToString(decryptedText));

        scanner.close();
    }

    private static String bytesToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append((b & 0xFF)).append(" ");
        }
        return sb.toString().trim();
    }
}


//🔢 10-Step Algorithm for RC4 Cipher
//        1️⃣ Initialize an S-box (S[256]) with values from 0 to 255.
//        2️⃣ Initialize a key schedule (K[256]) by repeating the given key.
//        3️⃣ Shuffle the S-box using the key to create a pseudo-random permutation.
//        4️⃣ Generate a keystream of the same length as the plaintext.
//        5️⃣ Perform XOR between the plaintext and keystream to generate the ciphertext.
//        6️⃣ To decrypt, use the same keystream and XOR it with the ciphertext.
//        7️⃣ Since XOR is reversible, decryption results in the original plaintext.
//        8️⃣ Ensure that the key length is valid (between 1 and 256 bytes).
//        9️⃣ RC4 is a stream cipher, meaning encryption and decryption are done byte-by-byte.
//        🔟 Print the intermediate values for keystream, ciphertext, and decrypted plaintext.

