package core;

import util.CryptoUtils;
import java.awt.image.BufferedImage;

public class Steganography {

    public static BufferedImage encode(BufferedImage image, String message, String key)
            throws Exception {

        byte[] encrypted = CryptoUtils.encrypt(message, key);
        int msgLen = encrypted.length;

        // ✅ IMAGE CAPACITY VALIDATION (IMPROVEMENT)
        int totalBits = (msgLen + 4) * 8; // 4 bytes for length
        int maxBits = image.getWidth() * image.getHeight();

        if (totalBits > maxBits) {
            throw new Exception(
                "Message is too large for the selected image. Please choose a larger image."
            );
        }

        int bitIndex = 0;

        // store message length (32 bits)
        for (int i = 0; i < 32; i++) {
            int x = bitIndex % image.getWidth();
            int y = bitIndex / image.getWidth();
            int rgb = image.getRGB(x, y);

            int bit = (msgLen >> (31 - i)) & 1;
            int blue = (rgb & 0xFF & 0xFE) | bit;

            image.setRGB(x, y, (rgb & 0xFFFFFF00) | blue);
            bitIndex++;
        }

        // store encrypted message
        for (byte b : encrypted) {
            for (int i = 0; i < 8; i++) {
                int x = bitIndex % image.getWidth();
                int y = bitIndex / image.getWidth();
                int rgb = image.getRGB(x, y);

                int bit = (b >> (7 - i)) & 1;
                int blue = (rgb & 0xFF & 0xFE) | bit;

                image.setRGB(x, y, (rgb & 0xFFFFFF00) | blue);
                bitIndex++;
            }
        }
        return image;
    }

    public static String decode(BufferedImage image, String key)
            throws Exception {

        int bitIndex = 0;
        int msgLen = 0;

        // read message length
        for (int i = 0; i < 32; i++) {
            int x = bitIndex % image.getWidth();
            int y = bitIndex / image.getWidth();
            int bit = image.getRGB(x, y) & 1;

            msgLen = (msgLen << 1) | bit;
            bitIndex++;
        }

        byte[] encrypted = new byte[msgLen];

        // read encrypted message
        for (int i = 0; i < msgLen; i++) {
            byte b = 0;
            for (int j = 0; j < 8; j++) {
                int x = bitIndex % image.getWidth();
                int y = bitIndex / image.getWidth();
                int bit = image.getRGB(x, y) & 1;

                b = (byte) ((b << 1) | bit);
                bitIndex++;
            }
            encrypted[i] = b;
        }

        return CryptoUtils.decrypt(encrypted, key);
    }
}
