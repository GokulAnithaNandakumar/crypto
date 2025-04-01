import java.util.*;

public class PlayfairCipher {
    private char[][] matrix = new char[5][5];
    private String key;

    public PlayfairCipher(String key) {
        this.key = key.toUpperCase().replaceAll("J", "I");
        generateMatrix();
    }

    private void generateMatrix() {
        StringBuilder keyBuilder = new StringBuilder();
        boolean[] used = new boolean[26];

        for (char ch : key.toCharArray()) {
            if (!used[ch - 'A'] && ch != 'J') {
                keyBuilder.append(ch);
                used[ch - 'A'] = true;
            }
        }

        for (char ch = 'A'; ch <= 'Z'; ch++) {
            if (!used[ch - 'A'] && ch != 'J') {
                keyBuilder.append(ch);
                used[ch - 'A'] = true;
            }
        }

        int index = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = keyBuilder.charAt(index++);
            }
        }
    }

    private String formatText(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (i > 0 && text.charAt(i) == text.charAt(i - 1)) {
                formatted.append('X');
            }
            formatted.append(text.charAt(i));
        }
        if (formatted.length() % 2 != 0) formatted.append('X');
        return formatted.toString();
    }

    private int[] findPosition(char ch) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == ch) return new int[]{i, j};
            }
        }
        return null;
    }

    public String encrypt(String text) {
        text = formatText(text);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            int[] pos1 = findPosition(text.charAt(i));
            int[] pos2 = findPosition(text.charAt(i + 1));

            if (pos1[0] == pos2[0]) {
                result.append(matrix[pos1[0]][(pos1[1] + 1) % 5]);
                result.append(matrix[pos2[0]][(pos2[1] + 1) % 5]);
            } else if (pos1[1] == pos2[1]) {
                result.append(matrix[(pos1[0] + 1) % 5][pos1[1]]);
                result.append(matrix[(pos2[0] + 1) % 5][pos2[1]]);
            } else {
                result.append(matrix[pos1[0]][pos2[1]]);
                result.append(matrix[pos2[0]][pos1[1]]);
            }
        }
        return result.toString();
    }

    public String decrypt(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            int[] pos1 = findPosition(text.charAt(i));
            int[] pos2 = findPosition(text.charAt(i + 1));

            if (pos1[0] == pos2[0]) {
                result.append(matrix[pos1[0]][(pos1[1] + 4) % 5]);
                result.append(matrix[pos2[0]][(pos2[1] + 4) % 5]);
            } else if (pos1[1] == pos2[1]) {
                result.append(matrix[(pos1[0] + 4) % 5][pos1[1]]);
                result.append(matrix[(pos2[0] + 4) % 5][pos2[1]]);
            } else {
                result.append(matrix[pos1[0]][pos2[1]]);
                result.append(matrix[pos2[0]][pos1[1]]);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String key = "KEYWORD";
        PlayfairCipher playfair = new PlayfairCipher(key);

        String plaintext = "HELLO WORLD";
        String encrypted = playfair.encrypt(plaintext);
        String decrypted = playfair.decrypt(encrypted);

        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
        System.out.println("Verification: " + plaintext.replace(" ", "").equals(decrypted));
    }
}


//    Choose a keyword (e.g., "KEYWORD") and remove duplicate letters.
//
//        Construct a 5x5 matrix using the keyword and the remaining alphabet (combine 'I' and 'J').
//
//        Split the plaintext into digraphs (pairs of two letters).
//
//        If a pair contains duplicate letters, insert a filler (e.g., 'X') between them.
//
//        Find positions of both letters in the matrix.
//
//        Encryption Rules:
//
//        If the letters are in the same row, replace each letter with the one to its right.
//
//        If in the same column, replace each letter with the one below.
//
//        If in different rows and columns, replace each letter with the one in its rectangle (same row, opposite column).
//
//        Decrypting follows the reverse of encryption rules.
//
//        Handle spaces, special characters, and case sensitivity properly.
//
//        Ensure the matrix is printed correctly for debugging.
//
//        Verify correctness by checking if decrypting an encrypted message returns the original plaintext.
//
