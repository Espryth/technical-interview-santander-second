package me.jarvica.santander.secondexercise.file.processor;

import me.jarvica.santander.secondexercise.file.reader.FileReader;
import me.jarvica.santander.secondexercise.file.writer.FileWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileProcessorConfig {

  @Bean("txtProcessor")
  FileProcessor txtFileProcessor(
      final @Qualifier("txtWriter") FileWriter txtFileWriter,
      final @Qualifier("txtReader") FileReader txtFileReader
  ) {
    return new WrappingFileProcessor(txtFileReader, txtFileWriter);
  }

  @Bean("jsonProcessor")
  FileProcessor jsonFileProcessor(
      final @Qualifier("jsonWriter") FileWriter jsonFileWriter,
      final @Qualifier("jsonReader") FileReader jsonFileReader
  ) {
    return new WrappingFileProcessor(jsonFileReader, jsonFileWriter);
  }
}
