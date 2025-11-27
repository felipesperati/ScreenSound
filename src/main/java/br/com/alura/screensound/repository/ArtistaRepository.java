package br.com.alura.screensound.repository;

import br.com.alura.screensound.models.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    boolean existsByNome(String nome);
    @Query("SELECT a FROM Artista a WHERE a.nome ILIKE :nome")
    Optional<Artista> buscaPorNome(String nome);
}
