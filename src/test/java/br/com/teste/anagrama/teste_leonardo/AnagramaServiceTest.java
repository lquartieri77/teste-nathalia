package br.com.teste.anagrama.teste_leonardo;

import br.com.teste.anagrama.teste_leonardo.service.AnagramaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class AnagramaServiceTest {

    @InjectMocks
    private AnagramaService anagramaService;

    @Test
    void deveGerarAnagramasValidos() {
        String entrada = "{a,b,c}";
        List<String> resultado = anagramaService.gerar(entrada);

        assertNotNull(resultado);
        assertEquals(6, resultado.size()); // 3 letras → 3! = 6 combinações
        assertTrue(resultado.contains("abc"));
        assertTrue(resultado.contains("acb"));
        assertTrue(resultado.contains("bac"));
        assertTrue(resultado.contains("bca"));
        assertTrue(resultado.contains("cab"));
        assertTrue(resultado.contains("cba"));
    }

    @Test
    void deveLancarExcecaoParaEntradaVazia() {
        String entrada = "";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            anagramaService.gerar(entrada);
        });

        assertEquals("Entrada não pode ser vazia.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoParaLetrasRepetidas() {
        String entrada = "{a,b,a}";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            anagramaService.gerar(entrada);
        });

        assertEquals("Letras repetidas não são permitidas.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoParaCaracteresInvalidos() {
        String entrada = "{a,1,c}";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            anagramaService.gerar(entrada);
        });

        assertEquals("Apenas letras são permitidas.", exception.getMessage());
    }
}