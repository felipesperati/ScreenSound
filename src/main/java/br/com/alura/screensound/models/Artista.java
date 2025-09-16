package br.com.alura.screensound.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {
    @Column(unique = true)
    private String nome;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipoArtista tipo;
    private String dataDeNascimento;
    private String dataDeFim;
    private String paisDeOrigem;
    private String classificacao;
    @OneToMany(mappedBy = "artista")
    private List<Album> albuns = new ArrayList<>();
    @OneToMany(mappedBy = "artista", fetch = FetchType.EAGER)
    private List<Musica> musicas = new ArrayList<>();

    public Artista() {}

    public Artista(DadosArtista dadosArtista) {
        this.nome = dadosArtista.nome();
        this.tipo = TipoArtista.fromString(dadosArtista.tipo());
        this.paisDeOrigem = dadosArtista.pais();
        this.classificacao = (dadosArtista.classificacao() != null ?
                dadosArtista.classificacao() : "Sem classificação");
        this.dataDeNascimento = dadosArtista.datas().begin();
        this.dataDeFim = dadosArtista.datas().end() != null ?
                dadosArtista.datas().end() : "Indefinido";
    }

    public Artista(String nome, TipoArtista tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoArtista getTipo() {
        return tipo;
    }

    public void setTipo(TipoArtista tipo) {
        this.tipo = tipo;
    }

    public List<Album> getAlbuns() {
        return albuns;
    }

    public void setAlbuns(List<Album> albuns) {
        this.albuns = albuns;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void addMusica(Musica musica) {
        this.musicas.add(musica);
    }

    public void addMusicas(List<Musica> musicas) {
        this.musicas.addAll(musicas);
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getDataDeFim() {
        return dataDeFim;
    }

    public void setDataDeFim(String dataDeFim) {
        this.dataDeFim = dataDeFim;
    }

    public String getPaisDeOrigem() {
        return paisDeOrigem;
    }

    public void setPaisDeOrigem(String paisDeOrigem) {
        this.paisDeOrigem = paisDeOrigem;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    @Override
    public String toString() {
        return "Nome: '" + nome +"\'\n" +
                "Data de Nascimento: " + dataDeNascimento + "\n" +
                "País de Origem: " +paisDeOrigem + "\n" +
                "Tipo: " + tipo + "\n" +
                "Classificação: " + classificacao + "\n" +
                "Data de Fim: " + dataDeFim;
    }
}

