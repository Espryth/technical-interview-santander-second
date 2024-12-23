package me.jarvica.santander.secondexercise.file.sorting;

import me.jarvica.santander.secondexercise.file.FileType;
import org.springframework.core.io.Resource;

public record SortedFile(
    String id,
    long size,
    FileType fileType,
    Resource resource
) {

  public String name() {
    return this.id + this.fileType.extension();
  }
}
