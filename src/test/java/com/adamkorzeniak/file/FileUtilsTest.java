package com.adamkorzeniak.file;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {

    private static final String TEST_FILE_PATH = "src/test/resources/person.json";
    private static final String SMALL_FILE_PATH = "src/test/resources/small.txt";
    private static final String EMPTY_FILE_PATH = "src/test/resources/empty.json";
    private static final String DUMMY_FILE_PATH = "src/dummy";
    private static final String SMALL_FILE_CONTENT = "xyz: 345\nzyx: 543\nqwe123";

    @Test
    void OpenFileStream_FileExists_FileStreamReturned() throws IOException {
        Stream<String> fileStream = FileUtils.openFileStream(TEST_FILE_PATH);

        assertNotNull(fileStream, "File Stream is null");
        int fileSize = fileStream.collect(Collectors.joining()).length();
        assertThat(fileSize)
                .isEqualTo(354)
                .withFailMessage("File size is incorrect");
    }

    @Test
    void OpenFileStream_EmptyFileExists_EmpptyFileStreamReturned() throws IOException {
        Stream<String> fileStream = FileUtils.openFileStream(EMPTY_FILE_PATH);

        assertNotNull(fileStream, "File Stream is null");
        int fileSize = fileStream.collect(Collectors.joining()).length();
        assertThat(fileSize)
                .isEqualTo(0)
                .withFailMessage("File Size should be empty");
    }

    @Test
    void OpenFileStream_FileNotFound_ExceptionThrown() {
        NoSuchFileException exception =
                assertThrows(NoSuchFileException.class, () -> FileUtils.openFileStream(DUMMY_FILE_PATH));
        String expectedErrorMessage = "java.nio.file.NoSuchFileException: " + DUMMY_FILE_PATH.replace("/", "\\");
        assertThat(exception.toString())
                .isEqualTo(expectedErrorMessage);
    }

    @Test
    void OpenFileStream_NullPath_ExceptionThrown() {
        NullPointerException exception =
                assertThrows(NullPointerException.class, () -> FileUtils.openFileStream(null));
        String expectedErrorMessage = "java.lang.NullPointerException: filePath is marked non-null but is null";
        assertThat(exception.toString())
                .isEqualTo(expectedErrorMessage);
    }

    @Test
    void ReadFileContent_FileExists_FileContentReturned() throws IOException {
        String fileContent = FileUtils.readFileContent(SMALL_FILE_PATH);

        assertNotNull(fileContent, "File Stream is null");
        assertThat(fileContent)
                .isEqualTo(SMALL_FILE_CONTENT);
    }

    @Test
    void ReadFileContent_EmptyFileExists_EmptyFileContentReturned() throws IOException {
        String fileContent = FileUtils.readFileContent(EMPTY_FILE_PATH);

        assertNotNull(fileContent, "File Stream is null");
        assertThat(fileContent)
                .hasSize(0);
    }

}