package me.jarvica.santander.secondexercise.file.sorting;

import me.jarvica.santander.secondexercise.file.FileType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileSortingServiceTest {

  @Autowired
  private FileSortingService service;

  @Test
  void testSortTxt() {
    final var txtFile = new MockMultipartFile(
        "file",
        "text.txt",
        "text/plain",
        new byte[] {}
    );
    final var sortedFile = this.service.sortFile(txtFile, 20, 25).block();
    assertNotNull(sortedFile);
    assertSame(sortedFile.fileType(), FileType.TXT);
  }

  @Test
  void testSortJson() {
    final var jsonFile = new MockMultipartFile(
        "file",
        "text.json",
        "application/json",
        "[]".getBytes(StandardCharsets.UTF_8)
    );
    final var sortedFile = this.service.sortFile(jsonFile, 20, 25).block();
    assertNotNull(sortedFile);
    assertSame(sortedFile.fileType(), FileType.JSON);
  }
}
