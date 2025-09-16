package br.com.alura.screensound.repository;

import br.com.alura.screensound.models.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MusicaRepository extends JpaRepository<Musica, Long> {
    boolean existsByTitulo(String titulo);

    @Query("SELECT m FROM Musica m WHERE m.artista.nome ILIKE %:nome%")
    List<Musica> buscarPorNomeArtista(String nome);
}
