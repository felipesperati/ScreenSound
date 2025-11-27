package br.com.alura.screensound;

import br.com.alura.screensound.principal.Principal;
import br.com.alura.screensound.repository.AlbumRepository;
import br.com.alura.screensound.repository.ArtistaRepository;
import br.com.alura.screensound.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreensoundApplication implements CommandLineRunner {

	@Autowired
	private ArtistaRepository artRepository;
	@Autowired
	private MusicaRepository musRepository;
	@Autowired
	private AlbumRepository albRepository;

	public static void main(String[] args) {
		SpringApplication.run(ScreensoundApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(artRepository, musRepository, albRepository);
		principal.exibeMenu();
	}
}
