//MD5 Hashing with Verification
//        Algorithm:
//        Compute the MD5 hash of the input text.
//
//        Compute the MD5 hash again on the same input.
//
//        Compare both hashes.
//
//        If they match, verification is successful.
//
//        Print verification status.
import java.security.MessageDigest;

public class MD5Hashing {
    public static String hash(String message) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
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
        System.out.println("MD5 Hash: " + hash1);
        System.out.println("Verification: " + hash1.equals(hash2));
    }

    public static void main(String[] args) throws Exception {
        verify("HelloWorld");
    }
}
