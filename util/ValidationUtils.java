package util;

import java.awt.image.BufferedImage;

public class ValidationUtils {

    public static void validateInputs(BufferedImage img, String msg, String key)
            throws Exception {

        if (img == null)
            throw new Exception("Please select an image");

        if (msg == null || msg.isEmpty())
            throw new Exception("Message cannot be empty");

        validateKey(key);
    }

    public static void validateKey(String key) throws Exception {
        if (key == null || key.length() < 6)
            throw new Exception("Password must be at least 6 characters");
    }
}
