package me.jarvica.santander.secondexercise.file.reader;

import me.jarvica.santander.secondexercise.file.error.ReadFileException;
import me.jarvica.santander.secondexercise.model.Person;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.util.Collection;
import java.util.function.Predicate;

@Component("txtReader")
public final class TxtFileReader implements FileReader {

  @Override
  public Collection<Person> read(final BufferedReader reader, final Predicate<Person> filter) {
    try (final var lines = reader.lines()) {
      return lines.parallel()
          .map(line -> line.split(","))
          .map(parts -> new Person(parts[0], Integer.parseInt(parts[1])))
          .filter(filter)
          .toList();
    } catch (final Exception e) {
      throw new ReadFileException(e);
    }
  }
}
