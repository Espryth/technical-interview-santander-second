package me.jarvica.santander.secondexercise.file.sorting;

import me.jarvica.santander.secondexercise.file.FileType;
import me.jarvica.santander.secondexercise.file.error.ReadFileException;
import me.jarvica.santander.secondexercise.file.processor.FileProcessorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

@Service
public final class FileSortingServiceImpl implements FileSortingService {

  private final FileProcessorProvider fileProcessorProvider;

  @Autowired
  FileSortingServiceImpl(final FileProcessorProvider fileProcessorProvider) {
    this.fileProcessorProvider = fileProcessorProvider;
  }

  @Override
  public Mono<SortedFile> sortFile(final MultipartFile file, final int minAge, final int maxAge) {
    return Mono.fromCallable(() -> {
      final var fileName = Objects.requireNonNull(file.getOriginalFilename());
      final var extension = fileName.substring(fileName.lastIndexOf('.'));
      final var fileType = FileType.fromExtension(extension);
      try (final var reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
        final var resource = this.fileProcessorProvider.getProcessor(fileType)
            .process(
                reader,
                person -> person.age() >= minAge && person.age() <= maxAge
            );
        return new SortedFile(
            generateRandomFileName(),
            resource.contentLength(),
            fileType,
            resource
        );
      } catch (final IOException e) {
        throw new ReadFileException(e);
      }
    });
  }

  private static String generateRandomFileName() {
    return String.format("%s-%d", System.currentTimeMillis(), (int) (Math.random() * 1000));
  }
}
