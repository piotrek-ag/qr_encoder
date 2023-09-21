import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class MultiQRCodeGenerator {
    private static final int MAX_CHAR_COUNT = 1272; // Maximum characters for alphanumeric data in Level 40 QR with 'H' error correction

    public static void main(String[] args) {
        String filePath = "C:\\Users\\piotrek\\projects\\UBS\\qr_encoder\\src\\input.txt"; // Text file path
        String qrCodeDir = "qrcodes"; // Directory to save QR codes

        try {
            // Read the text file
            Path textFilePath = Paths.get(filePath);
            String content = Files.readString(textFilePath, StandardCharsets.UTF_8);

            // Create directory for QR codes if it doesn't exist
            Files.createDirectories(Paths.get(qrCodeDir));

            // Split the content and generate QR codes
            for (int i = 0; i < content.length(); i += MAX_CHAR_COUNT) {
                int endIndex = Math.min(i + MAX_CHAR_COUNT, content.length());
                String chunk = content.substring(i, endIndex);

                generateQRCode(chunk, qrCodeDir + "/qr_" + (i / MAX_CHAR_COUNT + 1) + ".png");
            }

            System.out.println("QR codes generated and saved in directory: " + qrCodeDir);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateQRCode(String data, String filePath) {
        int width = 300; // Width of the QR code image
        int height = 300; // Height of the QR code image

        try {
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); // Highest level of error correction

            // Generate the QR code
            BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, width, height, hints);

            // Write to an image file
            Path path = FileSystems.getDefault().getPath(filePath);
            MatrixToImageWriter.writeToPath(matrix, "PNG", path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
