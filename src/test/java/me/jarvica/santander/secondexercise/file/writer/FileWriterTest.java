package me.jarvica.santander.secondexercise.file.writer;

import me.jarvica.santander.secondexercise.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class FileWriterTest {

  private static final List<Person> PEOPLE = List.of(
      new Person("John", 25),
      new Person("Jane", 20)
  );

  @Autowired
  @Qualifier("txtWriter")
  private FileWriter txtFileWriter;

  @Autowired
  @Qualifier("jsonWriter")
  private FileWriter jsonFileWriter;

  @Test
  void testTxtWrite() {
    assertDoesNotThrow(() -> this.txtFileWriter.write(PEOPLE));
  }

  @Test
  void testJsonWrite() {
    assertDoesNotThrow(() -> this.jsonFileWriter.write(PEOPLE));
  }
}
