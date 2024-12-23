package me.jarvica.santander.secondexercise.file.writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.jarvica.santander.secondexercise.file.error.WriteFileException;
import me.jarvica.santander.secondexercise.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("jsonWriter")
public final class JsonFileWriter implements FileWriter {

  private final ObjectMapper objectMapper;

  @Autowired
  JsonFileWriter(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public Resource write(final Collection<Person> people) {
    try {
      return new ByteArrayResource(this.objectMapper.writeValueAsBytes(people));
    } catch (final Exception e) {
      throw new WriteFileException(e);
    }
  }
}
