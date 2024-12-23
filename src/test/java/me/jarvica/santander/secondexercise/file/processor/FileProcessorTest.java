package me.jarvica.santander.secondexercise.file.processor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class FileProcessorTest {

  @Autowired
  @Qualifier("txtProcessor")
  private FileProcessor txtFileProcessor;

  @Autowired
  @Qualifier("jsonProcessor")
  private FileProcessor jsonFileProcessor;

  @Test
  void testProcess() {
    assertDoesNotThrow(() -> this.txtFileProcessor.process(new BufferedReader(new StringReader("")), person -> true));
    assertDoesNotThrow(() -> this.jsonFileProcessor.process(new BufferedReader(new StringReader("[]")), person -> true));
  }

}
