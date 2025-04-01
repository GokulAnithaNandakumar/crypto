//3. RSA Algorithm with Verification
//        Algorithm:
//        Generate RSA key pairs.
//
//        Encrypt a message using the public key.
//
//        Decrypt the ciphertext using the private key.
//
//        Compare decrypted message with the original plaintext.
//
//        Return the verification status.

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
    private BigInteger n, d, e;

    public RSA(int bitLength) {
        SecureRandom random = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(bitLength / 2, random);
        BigInteger q = BigInteger.probablePrime(bitLength / 2, random);
        n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitLength / 2, random);
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e = e.add(BigInteger.ONE);
        }
        d = e.modInverse(phi);
    }

    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }

    public BigInteger decrypt(BigInteger cipher) {
        return cipher.modPow(d, n);
    }

    public void verify(BigInteger message) {
        BigInteger encrypted = encrypt(message);
        BigInteger decrypted = decrypt(encrypted);
        System.out.println("Original: " + message);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
        System.out.println("Verification: " + message.equals(decrypted));
    }

    public static void main(String[] args) {
        RSA rsa = new RSA(1024);
        rsa.verify(new BigInteger("123456789"));
    }
}
