package me.jarvica.santander.secondexercise.controller;

import me.jarvica.santander.secondexercise.exception.ExceptionResponse;
import me.jarvica.santander.secondexercise.file.sorting.FileSortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

@RestController
public final class FileSortingController {

  private final FileSortingService service;

  @Autowired
  FileSortingController(final FileSortingService service) {
    this.service = service;
  }

  @PostMapping("/sort")
  Mono<ResponseEntity<Resource>> sort(
      final @RequestParam("min-age") int minAge,
      final @RequestParam("max-age") int maxAge,
      final @RequestParam("file") MultipartFile file
  ) {
    return this.service.sortFile(file, minAge, maxAge)
        .map(sortedFile ->
            ResponseEntity.ok()
                .headers(headers -> {
                  headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + sortedFile.name());
                  headers.add(HttpHeaders.CONTENT_TYPE, sortedFile.fileType().mediaType());
                  headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(sortedFile.size()));
                })
                .contentLength(sortedFile.size())
                .body(sortedFile.resource())
        )
        .doOnError(throwable ->
            ResponseEntity
                .internalServerError()
                .body(ExceptionResponse.of("An internal error occurred", throwable.getMessage()))
        );

  }
}
