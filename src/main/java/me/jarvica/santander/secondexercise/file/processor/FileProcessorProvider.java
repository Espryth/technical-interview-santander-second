package me.jarvica.santander.secondexercise.file.processor;

import me.jarvica.santander.secondexercise.file.FileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public final class FileProcessorProvider {

  private final FileProcessor txtFileProcessor;
  private final FileProcessor jsonFileProcessor;

  @Autowired
  FileProcessorProvider(
      final @Qualifier("txtProcessor") FileProcessor txtFileProcessor,
      final @Qualifier("jsonProcessor") FileProcessor jsonFileProcessor
  ) {
    this.txtFileProcessor = txtFileProcessor;
    this.jsonFileProcessor = jsonFileProcessor;
  }

  public FileProcessor getProcessor(final FileType fileType) {
    return switch (fileType) {
      case TXT -> this.txtFileProcessor;
      case JSON -> this.jsonFileProcessor;
    };
  }
}
