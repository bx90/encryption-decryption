package src;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EncryptionTest {
    private final static String password = "test";

    @Test
    public void testEncrypt() {
        String encryption = Encryption.byteToString(Encryption.encrypt(password));
        Assert.assertEquals(password, Encryption.decrypt(Encryption.stringToByte(encryption)));
    }

}
