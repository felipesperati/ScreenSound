package br.com.alura.screensound.service;

import br.com.alura.screensound.models.Artista;
import br.com.alura.screensound.models.DadosArtista;
import br.com.alura.screensound.models.ListArtistas;

public class BuscaArtista {

    private ConsumoAPI consumo = new ConsumoAPI();
    private final String ENDERECO = "https://musicbrainz.org/ws/2/";
    private final String ARTIST_METHOD = "artist?query=%22";
    private ConverteDados conversor = new ConverteDados();

    public Artista buscar(String nomeArtista) {
        var nomeEncoded = nomeArtista.replace(" ", "%20");
        var json = consumo.obterDados(ENDERECO + ARTIST_METHOD + nomeEncoded + "%22&fmt=json");
        var listArtistas = conversor.obterDados(json, ListArtistas.class);
        return new Artista(listArtistas.artistas().get(0));
    }
}
