package glsib.carpooling.services;

import java.util.ArrayList;
import java.util.List;

public class PacketConverter {

    public static byte[] convertAsciiToBinary(byte[] asciiData) {
        List<Byte> binaryData = new ArrayList<>();

        for (int i = 0; i < asciiData.length; i++) {
            if (asciiData[i] == 32) { // Skip spaces
                continue;
            }
            String hexString = "";
            // Check if there's enough length for a two-character hex value
            hexString += (char) asciiData[i];
            if (i + 1 < asciiData.length && asciiData[i + 1] != 32) {
                hexString += (char) asciiData[++i];
            }
            // Skip non-hex characters or empty strings
            try {
                binaryData.add((byte) Integer.parseInt(hexString, 16));
            } catch (NumberFormatException e) {
                System.out.println("Skipping invalid hex string: " + hexString);
            }
        }

        // Convert List<Byte> to byte[]
        byte[] result = new byte[binaryData.size()];
        for (int i = 0; i < binaryData.size(); i++) {
            result[i] = binaryData.get(i);
        }
        return result;
    }
}
