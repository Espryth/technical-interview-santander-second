package me.jarvica.santander.secondexercise.file;

import me.jarvica.santander.secondexercise.file.error.FileTypeNotFoundException;
import org.springframework.http.MediaType;

public enum FileType {
  JSON(".json", MediaType.APPLICATION_JSON_VALUE),
  TXT(".txt", MediaType.TEXT_PLAIN_VALUE);

  private final String extension;
  private final String mediaType;

  FileType(final String extension, final String mediaType) {
    this.extension = extension;
    this.mediaType = mediaType;
  }

  public String extension() {
    return this.extension;
  }

  public String mediaType() {
    return this.mediaType;
  }

  public static FileType fromExtension(final String extension) {
    for (final var type : values()) {
      if (type.extension.equals(extension)) {
        return type;
      }
    }
    throw new FileTypeNotFoundException(extension);
  }
}
