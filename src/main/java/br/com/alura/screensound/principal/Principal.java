package br.com.alura.screensound.principal;

import br.com.alura.screensound.models.*;
import br.com.alura.screensound.repository.AlbumRepository;
import br.com.alura.screensound.repository.ArtistaRepository;
import br.com.alura.screensound.repository.MusicaRepository;
import br.com.alura.screensound.service.BuscaArtista;
import br.com.alura.screensound.service.ConsumoAPI;
import br.com.alura.screensound.service.ConverteDados;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner scan = new Scanner(System.in);
    private ArtistaRepository artRepository;
    private MusicaRepository musRepository;
    private AlbumRepository albRepository;
    private final String ENDERECO = "https://musicbrainz.org/ws/2/";
    private final String MUSICA_METHOD = "recording/?query=recording:%22";
    private final String AND_ARTIST_METHOD = "%22%20AND%20artist:%22";
    private final String ALBUM_METHOD1 = "release/?query=release:%22";

    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();
    private BuscaArtista buscaArtista = new BuscaArtista();

    public Principal(ArtistaRepository artRepository,
                     MusicaRepository musRepository,
                     AlbumRepository albRepository) {
        this.artRepository = artRepository;
        this.musRepository = musRepository;
        this.albRepository = albRepository;
    }

    public Principal() {
    }

    public void exibeMenu() {

        var i = -1;
        System.out.println("\n****** Boas Vindas ao ******");
        String menu = """
                ******* Screen Sound *******
                
                1- Cadastrar artistas
                2- Cadastrar álbuns
                3- Cadastrar músicas
                4- Listar músicas
                5- Buscar músicas por artistas
                6- Pesquisar dados sobre um artista
                
                0 - Sair
                """;
        while (i != 0) {
            System.out.println(menu);
            i = scan.nextInt();
            scan.nextLine();

            switch (i) {
                case 1:
                    cadastrarArtistas();
                    break;
                case 2:
                    cadstrarAlbuns();
                    break;
                case 3:
                    cadastrarMusicas();
                    break;
                case 4:
                    listarMusicas();
                    break;
                case 5:
                    buscarMusicasPorArtistas();
                    break;
                case 6:
                    pesquisarDadosDoArtista();
                    break;
                case 0:
                    System.out.println("\nSaindo...\n");
                    break;
                default:
                    System.out.println("\nOpção inválida\n");

            }
        }
    }

    private void cadastrarArtistas() {

        String opcaoNovo = "S";

        while (opcaoNovo.equalsIgnoreCase("S")) {
            System.out.println("\nDigite o nome do artista:");
            String nome = scan.nextLine();
            var artista = buscaArtista.buscar(nome);
            System.out.println(artista);
            boolean cadastrado = artRepository.existsByNome(artista.getNome());
            if (!cadastrado) {
                artRepository.save(artista);
            } else {
                System.out.println("\n*ARTISTA JÁ CADASTRADO NO BANCO DE DADOS*");
            }
            System.out.println("\nDeseja cadastrar outro artista? (S/N)");
            opcaoNovo = scan.nextLine();
        }
    }

    private void cadastrarMusicas() {

        String opcaoNovo = "S";

        while (opcaoNovo.equalsIgnoreCase("S")) {
            System.out.println("\nDigite o nome da música:");
            var nomeMusica = scan.nextLine().replace(" ", "%20");
            System.out.println("\nDigite o nome do artista:");
            String nome = scan.nextLine().replace(" ", "%20");
            var json = consumo.obterDados(ENDERECO + MUSICA_METHOD +
                nomeMusica + AND_ARTIST_METHOD + nome + "%22&fmt=json");
            var listMusicas = conversor.obterDados(json, ListMusicas.class);
            DadosMusica dadosMusica = listMusicas.musicas().get(0);
            Musica musica = new Musica(dadosMusica, artRepository);
            System.out.println(musica + " (" + musica.getDataDeLancamento() + ")");
            boolean cadastrada = musRepository.existsByTitulo(musica.getTitulo());
            if (!cadastrada) {
                musRepository.save(musica);
            } else {
                System.out.println("\n*MÚSICA JÁ CADASTRADA NO BANCO DE DADOS*");
            }
            System.out.println("\nDeseja cadastrar outra música? (S/N)");
            opcaoNovo = scan.nextLine();
        }
    }

    private void cadstrarAlbuns() {

        String opcaoNovo = "S";

        while (opcaoNovo.equalsIgnoreCase("S")) {
            System.out.println("\nDigite o nome do álbum:");
            var nomeAlbum = scan.nextLine().replace(" ", "%20");
            System.out.println("\nDigite o nome do artista:");
            String nomeArt = scan.nextLine().replace(" ", "%20");
            String json = consumo.obterDados(ENDERECO + ALBUM_METHOD1 +
                    nomeAlbum + AND_ARTIST_METHOD +
                    nomeArt + "%22&fmt=json");
            var listAlbuns = conversor.obterDados(json, ListAlbuns.class);
            var dadosAlbum = listAlbuns.listaAlbuns().get(0);
            Album album = new Album(dadosAlbum, artRepository);
            System.out.println(album);
            boolean cadastrado = albRepository.existsByTitulo(album.getTitulo());
            if (!cadastrado) {
                albRepository.save(album);
            } else {
                System.out.println("\n*ÁLBUM JÁ CADASTRADO NO BANCO DE DADOS*");
            }
            System.out.println("\nDeseja cadastrar outro álbum? (S/N)");
            opcaoNovo = scan.nextLine();
        }
    }

    private void listarMusicas() {
        var musicas = musRepository.findAll();
        musicas.stream()
                .sorted(Comparator.comparing(e -> e.getArtista().getNome()))
                .forEach(System.out::println);
        System.out.println("\n");
    }

    private void buscarMusicasPorArtistas() {

        String opcaoNovo = "S";

        while (opcaoNovo.equalsIgnoreCase("S")) {
            System.out.println("\nDigite o nome do artista:");
            String nome = scan.nextLine();
            List<Musica> listaMusicas = musRepository.buscarPorNomeArtista(nome);
            if (!listaMusicas.isEmpty()) {
                listaMusicas.stream()
                        .sorted(Comparator.comparing(Musica::getTitulo))
                        .forEach(System.out::println);
            } else {
                System.out.println("\nArtista não encontrado");
            }
            System.out.println("\nDeseja fazer outra busca? (S/N)");
            opcaoNovo = scan.nextLine();
        }
    }

    private void pesquisarDadosDoArtista() {
    }
}
