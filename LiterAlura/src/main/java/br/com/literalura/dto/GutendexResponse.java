package br.com.literalura.dto;

import lombok.Data;
import java.util.List;

@Data
public class GutendexResponse {
    private List<BookDTO> results;
}
