package me.jarvica.santander.secondexercise.file.reader;

import me.jarvica.santander.secondexercise.model.Person;

import java.io.BufferedReader;
import java.util.Collection;
import java.util.function.Predicate;

public sealed interface FileReader permits JsonFileReader, TxtFileReader {

  Collection<Person> read(final BufferedReader reader, final Predicate<Person> filter);

}
