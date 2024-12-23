package me.jarvica.santander.secondexercise.file.reader;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FileReaderTest {

  @Autowired
  @Qualifier("txtReader")
  private FileReader jsonFileReader;

  @Autowired
  @Qualifier("jsonReader")
  private FileReader txtFileReader;

  @Test
  void testTxtRead() {
    final var people = this.jsonFileReader.read(
        new BufferedReader(new StringReader(
            """
              John,25
              Jane,20
              """
        )),
        person -> person.age() == 20
    );
    assertEquals(1, people.size());
  }

  @Test
  void testJsonRead() {
    final var people = this.txtFileReader.read(
        new BufferedReader(new StringReader(
            """
              [
                {
                  "name": "John",
                  "age": 25
                },
                {
                  "name": "Jane",
                  "age": 20
                }
              ]
              """
        )),
        person -> person.age() == 20
    );
    assertEquals(1, people.size());
  }
}
