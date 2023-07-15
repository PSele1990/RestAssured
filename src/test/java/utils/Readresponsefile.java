package utils;

import java.io.FileWriter;
import java.io.IOException;

public class Readresponsefile {

    public static void saveResponse(String response , String RESPONSE_FILEPATH) {
        try (FileWriter writer = new FileWriter(RESPONSE_FILEPATH)) {
            writer.write(response);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}