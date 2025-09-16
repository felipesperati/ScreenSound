package br.com.alura.screensound.models;

public enum TipoArtista {
    SOLO("person"),
    BANDA("banda"),
    GRUPO("group"),
    DUPLA("dupla");

    private String tipo;

    TipoArtista(String tipo) {
        this.tipo = tipo;
    }

    public static TipoArtista fromString(String text) {
        for (TipoArtista tipoArtista : TipoArtista.values()) {
            if (tipoArtista.tipo.equalsIgnoreCase(text)) {
                return tipoArtista;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
