package com.adamkorzeniak.json;

import com.adamkorzeniak.file.FileUtils;
import com.adamkorzeniak.json.model.EmailType;
import com.adamkorzeniak.json.model.Person;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class JsonUtilsTest {

    private static final String TEST_FILE_PATH = "src/test/resources/person.json";
    private static final String SMALL_FILE_PATH = "src/test/resources/small.txt";
    private static final String ADDRESS_FILE_PATH = "src/test/resources/address.json";
    private static final String EMPTY_FILE_PATH = "src/test/resources/empty.json";

    @Test
    void FromJson_PersonJson_ReturnsPersonObject() throws IOException {
        String fileContent = FileUtils.readFileContent(TEST_FILE_PATH);
        Person person = JsonUtils.fromJson(fileContent, Person.class);

        assertNotNull(person);
        assertThat(person.getName()).isEqualTo("Adam");
        assertThat(person.getLastName()).isEqualTo("Korzeniak");
        assertThat(person.getAge()).isEqualTo(123);
        assertNotNull(person.getAddress());
        assertThat(person.getAddress().getStreet()).isEqualTo("Aleje Jerozolimskie");
        assertThat(person.getAddress().getFlatNumber()).isEqualTo(321);
        assertNotNull(person.getEmailAddresses());
        assertThat(person.getEmailAddresses()).hasSize(3);
        assertThat(person.getEmailAddresses().get(0).getType()).isEqualTo(EmailType.PRIVATE);
        assertThat(person.getEmailAddresses().get(0).getAddress()).isEqualTo("adam@gmail.com");
        assertThat(person.getEmailAddresses().get(1).getType()).isEqualTo(EmailType.WORK);
        assertThat(person.getEmailAddresses().get(1).getAddress()).isEqualTo("work@gmail.com");
        assertThat(person.getEmailAddresses().get(2).getType()).isEqualTo(EmailType.SPAM);
        assertThat(person.getEmailAddresses().get(2).getAddress()).isEqualTo("spam@gmail.com");
    }

    @Test
    void FromJson_InvalidJson_ThrowsException() throws IOException {
        String fileContent = FileUtils.readFileContent(SMALL_FILE_PATH);
        JsonParseException exception
            = assertThrows(JsonParseException.class, () -> JsonUtils.fromJson(fileContent, Person.class));

        assertNotNull(exception);
        assertThat(exception.getOriginalMessage())
                .isEqualTo("Unrecognized token 'xyz': was expecting (JSON String, Number, Array, Object or token 'null', 'true' or 'false')");
    }

    @Test
    void FromJson_DifferentJsonModel_ThrowsException() throws IOException {
        String fileContent = FileUtils.readFileContent(ADDRESS_FILE_PATH);
        UnrecognizedPropertyException exception
                = assertThrows(UnrecognizedPropertyException.class, () -> JsonUtils.fromJson(fileContent, Person.class));

        assertNotNull(exception);
        assertThat(exception.getOriginalMessage())
                .isEqualTo("Unrecognized field \"street\" (class com.adamkorzeniak.json.model.Person), not marked as ignorable");
    }
}