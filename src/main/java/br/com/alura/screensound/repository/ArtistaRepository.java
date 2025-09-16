package br.com.alura.screensound.repository;

import br.com.alura.screensound.models.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    boolean existsByNome(String nome);
    Optional<Artista> getByNome(String nome);
}
