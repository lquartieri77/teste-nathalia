package br.com.teste.anagrama.teste_leonardo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class AnagramaRequest {

    @Schema(
            description = "Texto com letras separadas por vírgula",
            example = "a,b,c"
    )
    @NotBlank(message = "Texto não pode ser vazio.")
    @Pattern(regexp = "^[a-zA-Z](,[a-zA-Z])*$", message = "Texto deve conter apenas letras separadas por vírgula.")
    private String texto;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}