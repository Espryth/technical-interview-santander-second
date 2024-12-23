package me.jarvica.santander.secondexercise.file.sorting;

import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

public interface FileSortingService {

  Mono<SortedFile> sortFile(final MultipartFile file, final int minAge, final int maxAge);

}
