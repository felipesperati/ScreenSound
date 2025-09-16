package br.com.alura.screensound.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosArtista(@JsonAlias("name")String nome,
                           @JsonAlias("country")String pais,
                           @JsonAlias("disambiguation")String classificacao,
                           @JsonAlias("life-span")Datas datas,
                           @JsonAlias("type")String tipo){
}
