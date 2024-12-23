package me.jarvica.santander.secondexercise.file.error;

public final class FileTypeNotFoundException extends RuntimeException {

  public FileTypeNotFoundException(final String message) {
    super(message);
  }
}
