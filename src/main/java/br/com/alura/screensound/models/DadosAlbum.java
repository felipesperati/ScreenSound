package br.com.alura.screensound.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAlbum(@JsonAlias("title")String titulo,
                         @JsonAlias("disambiguation")String descricao,
                         @JsonAlias("date")String dataDeLancamento,
                         @JsonAlias("country")String pais,
                         @JsonAlias("track-count")Integer numeroDeFaixas,
                         @JsonAlias("artist-credit") List<PreArtista> nomeArtistaList) {
}
