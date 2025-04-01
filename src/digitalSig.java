import java.security.*;

public class DigitalSignature {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        Signature sign = Signature.getInstance("SHA256withRSA");

        byte[] message = "HelloWorld".getBytes();

        // Signing
        sign.initSign(pair.getPrivate());
        sign.update(message);
        byte[] signature = sign.sign();

        // Verification
        sign.initVerify(pair.getPublic());
        sign.update(message);
        boolean isValid = sign.verify(signature);

        System.out.println("Message: " + new String(message));
        System.out.println("Signature Verified: " + isValid);
    }
}
//5. Digital Signature with Verification
//        Algorithm:
//        Generate RSA key pairs.
//
//        Sign a message using the private key.
//
//        Verify the signature using the public key.
//
//        If verification succeeds, return true, otherwise false.
//
//        Print verification status.
//
