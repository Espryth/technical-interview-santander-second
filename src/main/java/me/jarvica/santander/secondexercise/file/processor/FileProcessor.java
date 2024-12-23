package me.jarvica.santander.secondexercise.file.processor;

import me.jarvica.santander.secondexercise.model.Person;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.util.function.Predicate;

public sealed interface FileProcessor permits WrappingFileProcessor {

  Resource process(final BufferedReader reader, final Predicate<Person> filter);

}
