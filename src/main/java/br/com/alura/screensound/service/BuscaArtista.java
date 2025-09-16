package br.com.alura.screensound.service;

import br.com.alura.screensound.models.Artista;
import br.com.alura.screensound.models.DadosArtista;
import br.com.alura.screensound.models.ListArtistas;

public class BuscaArtista {

    private ConsumoAPI consumo = new ConsumoAPI();
    private final String ENDERECO = "https://musicbrainz.org/ws/2/";
    private final String ARTIST_METHOD = "artist?query=";
    private ConverteDados conversor = new ConverteDados();

    public Artista buscar(String nomeArtista) {
        var json = consumo.obterDados(ENDERECO + ARTIST_METHOD + nomeArtista + "&fmt=json");
        var listArtistas = conversor.obterDados(json, ListArtistas.class);
        DadosArtista dados = listArtistas.artistas().get(0);
        return new Artista(dados);
    }
}
