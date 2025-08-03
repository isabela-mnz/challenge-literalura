package br.com.literalura.client;

import br.com.literalura.dto.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Component
public class GutendexClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "https://gutendex.com/books/?search=";

    public List<BookDTO> buscarLivros(String titulo) {
        String url = API_URL + titulo.replace(" ", "+");
        GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);
        return response.getResults();
    }
}
