package br.com.alura.screensound.models;

import br.com.alura.screensound.repository.ArtistaRepository;
import br.com.alura.screensound.service.BuscaArtista;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "albuns")
public class Album {
    private String titulo;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dataDeLancamento;
    private String descricao;
    private String pais;
    private Integer numeroDeFaixas;
    @ManyToOne
    private Artista artista;
    @OneToMany(mappedBy = "album")
    private List<Musica> musicas = new ArrayList<>();
    @Transient
    private BuscaArtista buscaArtista = new BuscaArtista();

    public Album(DadosAlbum dadosAlbum, ArtistaRepository a) {
        this.titulo = dadosAlbum.titulo();
        this.dataDeLancamento = dadosAlbum.dataDeLancamento();
        this.descricao = dadosAlbum.descricao();
        this.pais = dadosAlbum.pais();
        this.numeroDeFaixas = dadosAlbum.numeroDeFaixas();
        String nomeArtista = dadosAlbum.nomeArtistaList().get(0).nomeArtista().nome();
        Optional<Artista> optionalArtista = a.buscaPorNome(nomeArtista);
        if (optionalArtista.isEmpty()) {
            this.artista = buscaArtista.buscar(nomeArtista);
            a.save(this.artista);
        } else {
            this.artista = optionalArtista.get();
        }
        artista.addAlbum(this);
    }

    public Album() {
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

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    @Override
    public String toString() {
        return "\nAlbum: '" + titulo + "'" +
                "\nArtista: " + artista.getNome() +
                "\nData de Lançamento: " + dataDeLancamento +
                "\nNúmero de Faixas: " + numeroDeFaixas +
                "\nPaís: " + pais +
                "\nDescrição: " + descricao;
    }
}
