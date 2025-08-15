package br.com.teste.anagrama.teste_leonardo.service;


import br.com.teste.anagrama.teste_leonardo.util.AnagramaUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnagramaService {

    public List<String> gerar(String entradaFormatada) {
        return AnagramaUtil.gerarAnagramas(entradaFormatada);
    }
}