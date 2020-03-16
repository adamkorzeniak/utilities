package com.adamkorzeniak.file;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileUtils {

    public static String readFileContent(String filePath) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        Stream<String> stream = openFileStream(filePath);
        stream.forEach(s -> contentBuilder.append(s).append("\n"));
        return contentBuilder.toString();
    }

    public static Stream<String> openFileStream(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath), StandardCharsets.UTF_8);
    }
}
