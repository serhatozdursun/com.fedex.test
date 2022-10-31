package helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

public class FileHelper {
    private final Logger log = LogManager.getLogger(FileHelper.class);

    public String readFileAsString(InputStream fileDirectory) {
        try {
            return new String(fileDirectory.readAllBytes());
        } catch (IOException e) {
            log.error("An error occurred message:{}", e.getMessage());
            return "";
        }
    }
}
