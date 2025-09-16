package br.com.alura.screensound.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PreArtista(@JsonAlias("artist") NomeArtista nomeArtista) {
}
