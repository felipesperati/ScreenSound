package br.com.alura.screensound.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "albuns")
public class Album {
    private String titulo;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer anoDeLancamento;
    @ManyToOne
    private Artista artista;
    @OneToMany(mappedBy = "album")
    private List<Musica> musicas = new ArrayList<>();

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

    public Integer getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public void setAnoDeLancamento(Integer anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
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
        return "Album='" + titulo + '\'' +
                ", anoDeLancamento=" + anoDeLancamento +
                ", artista=" + artista +
                ", musicas=" + musicas;
    }
}
