package br.com.alura.screensound.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosMusica(@JsonAlias("title")String titulo,
                          @JsonAlias("first-release-date")String dataDeLancamento,
                          @JsonAlias("artist-credit") List<PreArtista> artistas) {
}
