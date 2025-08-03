package br.com.literalura.dto;

import lombok.Data;
import java.util.List;

@Data
public class BookDTO {
    private String title;
    private List<AuthorDTO> authors;
    private List<String> languages;
    private Integer download_count;
}
