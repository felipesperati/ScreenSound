package br.com.alura.screensound.models;

import br.com.alura.screensound.service.BuscaArtista;
import br.com.alura.screensound.principal.Principal;
import br.com.alura.screensound.repository.ArtistaRepository;
import br.com.alura.screensound.service.ConverteDados;
import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name = "musicas")
public class Musica {
    private String titulo;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dataDeLancamento;
    @ManyToOne
    private Album album;
    @ManyToOne
    private Artista artista;
    @Transient
    private ConverteDados conversor = new ConverteDados();
    @Transient
    private Principal principal = new Principal();
    @Transient
    private BuscaArtista buscaArtista = new BuscaArtista();

    public Musica(DadosMusica dadosMusica, ArtistaRepository artRepository) {
        this.titulo = dadosMusica.titulo();
        this.dataDeLancamento = dadosMusica.dataDeLancamento();
        var optionalArtista = repoGetArtByNome(dadosMusica, artRepository);
        if (optionalArtista.isEmpty()) {
            this.artista = buscaArtista.buscar(findNomeArtista(dadosMusica));
            artRepository.save(this.artista);
        } else {
            this.artista = optionalArtista.get();
        }
        artista.addMusica(this);
    }

    public Musica() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataDeLancamento() {
        return dataDeLancamento;
    }

    public void setDataDeLancamento(String dataDeLancamento) {
        this.dataDeLancamento = dataDeLancamento;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    private String findNomeArtista(DadosMusica dadosMusica) {
        return dadosMusica.artistas().get(0).nomeArtista().nome();
    }

    private Optional<Artista> repoGetArtByNome(DadosMusica dadosMusica, ArtistaRepository a) {
        return a.buscaPorNome(findNomeArtista(dadosMusica));
    }

    @Override
    public String toString() {
        return this.artista.getNome() + " - '" + this.titulo + "'";
//                "Música: '" + titulo + "' - " + "Artista: " + artista.getNome();
    }
}
