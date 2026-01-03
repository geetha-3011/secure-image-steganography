package util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

public class CryptoUtils {

    private static final String ALGO = "AES";

    public static byte[] encrypt(String msg, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, getKey(key));
        return cipher.doFinal(msg.getBytes());
    }

    public static String decrypt(byte[] data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.DECRYPT_MODE, getKey(key));
        return new String(cipher.doFinal(data)).trim();
    }

    private static SecretKeySpec getKey(String key) {
        byte[] k = Arrays.copyOf(key.getBytes(), 16);
        return new SecretKeySpec(k, ALGO);
    }
}
