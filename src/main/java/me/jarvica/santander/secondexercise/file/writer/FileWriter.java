package me.jarvica.santander.secondexercise.file.writer;

import me.jarvica.santander.secondexercise.model.Person;
import org.springframework.core.io.Resource;

import java.util.Collection;

public sealed interface FileWriter permits JsonFileWriter, TxtFileWriter {

  Resource write(final Collection<Person> people);

}
