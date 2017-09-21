package src;

import org.junit.Test;

public class EncryptionTest {
    final static String password = "test";
    @Test
    public void testEncrypt() {
        System.out.println(Encryption.BytetoString(Encryption.encrypt(password)));
    }

}
