package br.com.literalura.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livro {
    @Id @GeneratedValue
    private Long id;
    private String titulo;
    private String idioma;
    private String autor;
    private Integer anoPublicacao;
    private Double notaPessoal;
    private String status;
}
