package com.adamkorzeniak.file;

import lombok.NonNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {

    private FileUtils() {}

    public static String readFileContent(String filePath) throws IOException {
        Stream<String> stream = openFileStream(filePath);
        return stream.collect(Collectors.joining("\n"));
    }

    public static Stream<String> openFileStream(@NonNull String filePath) throws IOException {
        return Files.lines(Paths.get(filePath), StandardCharsets.UTF_8);
    }
}
