package me.jarvica.santander.secondexercise.file.writer;

import me.jarvica.santander.secondexercise.file.error.WriteFileException;
import me.jarvica.santander.secondexercise.model.Person;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Collection;

@Component("txtWriter")
public final class TxtFileWriter implements FileWriter {

  @Override
  public Resource write(final Collection<Person> people) {
    try {
      final var content = people.parallelStream()
          .map(person -> person.name() + "," + person.age())
          .reduce((line1, line2) -> line1 + "\n" + line2)
          .orElse("");
      return new ByteArrayResource(content.getBytes(StandardCharsets.UTF_8));
    } catch (final Exception e) {
      throw new WriteFileException(e);
    }
  }
}
