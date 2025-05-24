package users;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Password {
    byte[] salt;

    public String getPasswordHashed(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(salt);
        byte[] hashBytes = digest.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public String getPasswordHashed(String password) throws NoSuchAlgorithmException {
        this.salt = generateSalt(16);
        return getPasswordHashed(password, salt);
    }

    private byte[] generateSalt(int length) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[length];
        random.nextBytes(salt);
        return salt;
    }

    public byte[] getSalt() {
        return salt;
    }
}
