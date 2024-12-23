package me.jarvica.santander.secondexercise.controller;

import me.jarvica.santander.secondexercise.file.FileType;
import me.jarvica.santander.secondexercise.file.sorting.FileSortingService;
import me.jarvica.santander.secondexercise.file.sorting.SortedFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FileSortingController.class)
class FileSortingControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private FileSortingService service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testSort() throws Exception {
    final var file = new MockMultipartFile(
        "file",
        "text.txt",
        "text/plain",
        """
            John,25
            Jane,30
            """.getBytes(StandardCharsets.UTF_8)
    );
    when(this.service.sortFile(any(), anyInt(), anyInt())).thenReturn(Mono.just(new SortedFile(
        "file.txt",
        0,
        FileType.TXT,
        new ByteArrayResource(new byte[0])
    )));
    this.mockMvc
        .perform(
            multipart(HttpMethod.POST, "/sort")
                .file(file)
                .param("min-age", "25")
                .param("max-age", "40")

        )
        .andExpect(status().isOk());
  }
}
