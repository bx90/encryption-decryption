package src;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;

public class Encryption {
    final static Logger logger = LoggerFactory.getLogger(Encryption.class);

    private final static String key = "12345678";

    public static byte[] encrypt(String content) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            return cipher.doFinal(content.getBytes());
        } catch (InvalidKeyException | IllegalBlockSizeException | NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | InvalidKeySpecException e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(byte[] content) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, securekey, random);
            byte[] result = cipher.doFinal(content);
            return new String(result);
        } catch (InvalidKeyException | IllegalBlockSizeException | NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | InvalidKeySpecException e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    public static byte[] StringtoByte(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        String[] byteValues = str.split("%");
        byte[] bytes = new byte[byteValues.length];
        for (int i = 0, len = bytes.length; i < len; i++) {
            bytes[i] = Byte.parseByte(byteValues[i].trim());
        }
        return bytes;
    }

    public static String BytetoString(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(b).append("%");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}