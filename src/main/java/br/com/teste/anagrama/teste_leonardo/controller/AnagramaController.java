package br.com.teste.anagrama.teste_leonardo.controller;

import br.com.teste.anagrama.teste_leonardo.request.AnagramaRequest;
import br.com.teste.anagrama.teste_leonardo.service.AnagramaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/anagramas")
@Tag(name = "Anagramas - combinações possíveis", description = "Gerar combinações possíveis a partir de texto")
public class AnagramaController {

    private final AnagramaService service;

    public AnagramaController(AnagramaService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(
            summary = "Gerar combinações possíveis",
            description = "Recebe um texto e retorna todas as combinações possiveis, para o exemplo estou limitando a 1000 combinações possíveis"
    )
    public ResponseEntity<List<String>> gerar(@Valid @RequestBody AnagramaRequest request) {
        List<String> anagramas = service.gerar(request.getTexto());
        return ResponseEntity.ok(anagramas);
    }
}