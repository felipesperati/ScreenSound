package br.com.alura.screensound.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Datas(String begin,
                    String end) {
}
