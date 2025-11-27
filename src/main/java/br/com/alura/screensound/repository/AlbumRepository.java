package br.com.alura.screensound.repository;

import br.com.alura.screensound.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    boolean existsByTitulo(String titulo);
}
