package com.gocart.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Read files for testing and retuns strings
 */
public final class FileUtil {
    public static String readFile(String fileName) {
        StringBuilder contentBuilder = new StringBuilder();

        Path currentDir = Paths.get("");
        Path absolutePath = currentDir.toAbsolutePath();
        String testJsonPath = "src/test/java/com/gocart";
        Path pathToFile = Paths.get(absolutePath.toString(), testJsonPath, fileName);

        try (Stream<String> stream = Files.lines(pathToFile, StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            System.out.println("The file named " + fileName + "does not exist");
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }
}
