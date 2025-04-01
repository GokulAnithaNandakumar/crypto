//4. SHA-1 Hashing with Verification
//        Algorithm:
//        Compute SHA-1 hash of input text.
//
//        Compute SHA-1 hash again on the same input.
//
//        Compare both hashes.
//
//        If they match, verification is successful.
//
//        Return the verification status.
//
import java.security.MessageDigest;

public class SHA1 {
    public static String hash(String message) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] hash = md.digest(message.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    public static void verify(String message) throws Exception {
        String hash1 = hash(message);
        String hash2 = hash(message);
        System.out.println("Message: " + message);
        System.out.println("SHA-1 Hash: " + hash1);
        System.out.println("Verification: " + hash1.equals(hash2));
    }

    public static void main(String[] args) throws Exception {
        verify("HelloWorld");
    }
}
