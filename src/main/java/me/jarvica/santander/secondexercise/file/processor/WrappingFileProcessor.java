package me.jarvica.santander.secondexercise.file.processor;

import me.jarvica.santander.secondexercise.file.reader.FileReader;
import me.jarvica.santander.secondexercise.file.writer.FileWriter;
import me.jarvica.santander.secondexercise.model.Person;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.util.function.Predicate;

final class WrappingFileProcessor implements FileProcessor {

  private final FileReader fileReader;
  private final FileWriter fileWriter;

  public WrappingFileProcessor(
      final FileReader fileReader,
      final FileWriter fileWriter
  ) {
    this.fileReader = fileReader;
    this.fileWriter = fileWriter;
  }

  @Override
  public Resource process(final BufferedReader reader, final Predicate<Person> filter) {
    return this.fileWriter.write(this.fileReader.read(reader, filter));
  }
}
