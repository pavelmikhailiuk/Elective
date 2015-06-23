package by.epam.elective.encoder;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {
    private MessageDigest messageDigest;

    {
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new ExceptionInInitializerError();
        }
    }

    public String encode(String password) {
        messageDigest.update(password.getBytes());
        byte array[] = messageDigest.digest();
        BigInteger bigInteger = new BigInteger(1, array);
        String resHex = bigInteger.toString(16);
        return resHex;
    }
}

