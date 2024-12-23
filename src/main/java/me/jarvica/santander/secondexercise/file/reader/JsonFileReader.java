package me.jarvica.santander.secondexercise.file.reader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.jarvica.santander.secondexercise.file.error.ReadFileException;
import me.jarvica.santander.secondexercise.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

@Component("jsonReader")
public final class JsonFileReader implements FileReader {

  private final ObjectMapper objectMapper;

  @Autowired
  JsonFileReader(final ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public Collection<Person> read(final BufferedReader reader, final Predicate<Person> filter) {
    try {
      return this.objectMapper.readValue(reader, new TypeReference<List<Person>>() {})
          .parallelStream()
          .filter(filter)
          .toList();
    } catch (final Exception e) {
      throw new ReadFileException(e);
    }
  }
}
