import java.util.*;

public class HillCipher {
    static int[][] keyMatrix = {{6, 24}, {1, 13}};

    public static String encrypt(String text) {
        text = text.toUpperCase();
        int[] vector = {text.charAt(0) - 'A', text.charAt(1) - 'A'};
        int[] result = new int[2];

        for (int i = 0; i < 2; i++)
            result[i] = (keyMatrix[i][0] * vector[0] + keyMatrix[i][1] * vector[1]) % 26;

        return "" + (char) ('A' + result[0]) + (char) ('A' + result[1]);
    }

    public static void main(String[] args) {
        String plaintext = "HI";
        String encrypted = encrypt(plaintext);

        System.out.println("Encrypted: " + encrypted);
    }
}

//Algorithm:
//        Define a 2x2 or 3x3 matrix key for encryption.
//
//        Convert plaintext into a numeric vector using ASCII.
//
//        Multiply the matrix key with the plaintext vector.
//
//        Compute modulo 26 to keep values within alphabetic range.
//
//        Convert the encrypted numeric values back to characters.
//
//        For decryption, find the inverse matrix modulo 26.
//
//        Multiply the inverse matrix with the encrypted vector.
//
//        Compute modulo 26 again.
//
//        Convert numbers back into characters to get plaintext.
//
//        Verify the correctness by checking decryption output.