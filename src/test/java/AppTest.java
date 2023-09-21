import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AppTest {

    @Test
    public void testQRCodeImageGeneration() {
        String testFilePath = "./MyQRCode.png";
        File file = new File(testFilePath);

        // Delete the file if it already exists
        if (file.exists()) {
            file.delete();
        }

        // Run the main method of App to generate the QR code
        App.main(new String[]{});

        // Check if the file was created
        assertTrue(file.exists());

        // Clean up the test file
        file.delete();
    }

    @Test
    public void testQRCodeImageContent() {
        String testFilePath = "./MyQRCode.png";
        File file = new File(testFilePath);

        // Delete the file if it already exists
        if (file.exists()) {
            file.delete();
        }

        // Run the main method of App to generate the QR code
        App.main(new String[]{});

        // Check if the file was created and is not empty
        assertTrue(file.exists() && file.length() > 0);

        // Clean up the test file
        file.delete();
    }
}
