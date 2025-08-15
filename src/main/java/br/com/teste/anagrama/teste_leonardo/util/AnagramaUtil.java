package br.com.teste.anagrama.teste_leonardo.util;

import java.util.*;

public class AnagramaUtil {

    public static final int MAX_COMBINACOES = 1000;

    /**
     * Faz todas as combinações possíveis (anagramas) de letras distintas.
     *
     * @param entrada String no formato "{a,b,c}"
     * @return Lista de anagramas
     */
    public static List<String> gerarAnagramas(String entrada) {
        if (entrada == null || entrada.isBlank()) {
            throw new IllegalArgumentException("Entrada não pode ser vazia.");
        }

        String limpa = entrada.replaceAll("[{}\\s]", ""); // retira chaves se existirem
        String[] partes = limpa.split(","); // quebra por virgulas e faz disso um array

        if (partes.length == 0) {
            throw new IllegalArgumentException("Nenhuma letra foi informada."); //verifica se tem valor
        }

        Set<Character> letrasSet = new HashSet<>(); // monta um Set para ter elementos unicos
        char[] arrayDeLetras = new char[partes.length];

        for (int i = 0; i < partes.length; i++) {
            String letra = partes[i];
            if (letra.length() != 1 || !letra.matches("[a-zA-Z]")) {
                throw new IllegalArgumentException("Apenas letras são permitidas."); // valida somente letras
            }

            char c = letra.charAt(0);
            if (!letrasSet.add(c)) {
                throw new IllegalArgumentException("Letras repetidas não são permitidas."); // nao aceita repetidas
            }

            arrayDeLetras[i] = c; // adiciona letra
        }

        Arrays.sort(arrayDeLetras); // ordena para garantir a nao repetição de letra
        List<String> retorno = new ArrayList<>();
        boolean[] letrasUsadasBoolean = new boolean[arrayDeLetras.length]; // cria novo array para saber se ja foram usadas
        gerar(arrayDeLetras, new StringBuilder(), letrasUsadasBoolean, retorno);
        return retorno;
    }

    /**
     * Gera todas as combinações possíveis (sem repetições) das letras fornecidas,
     * respeitando um limite máximo de combinações para evitar sobrecarga de memória.
     *
     * <p>Esse método usa recursão para montar as combinações letra por letra,
     * garantindo que cada letra seja usada apenas uma vez por combinação.
     * Também evita gerar combinações duplicadas quando há letras repetidas no array.
     *
     * @param letras     Array de caracteres que serão combinados.
     * @param atual      StringBuilder que representa a combinação atual em construção.
     * @param usada      Array booleano que marca quais letras já foram usadas na combinação atual.
     * @param resultado  Lista onde serão armazenadas todas as combinações geradas.
     *
     * <p>Exemplo de uso: se o array de letras for {'a', 'b', 'c'}, o método vai gerar
     * combinações como "abc", "acb", "bac", "bca", "cab", "cba", respeitando o limite
     * definido por {@code MAX_COMBINACOES}.
     */
    private static void gerar(char[] letras, StringBuilder atual, boolean[] usada, List<String> resultado) {
        if (resultado.size() >= MAX_COMBINACOES) return; // sendo igual ao maximo definido, retorna (evitar outofmemory)

        if (atual.length() == letras.length) { // completou o tamanho, adiciona este ultimo char e retorna
            resultado.add(atual.toString());
            return;
        }

        for (int i = 0; i < letras.length; i++) { // looping para percorer array de letras e chamar novamente o mesmo metodo, para compor a lista final
            if (usada[i]) continue;
            if (i > 0 && letras[i] == letras[i - 1] && !usada[i - 1]) continue;

            usada[i] = true; // seta q ja foi usada
            atual.append(letras[i]); // adiciona
            gerar(letras, atual, usada, resultado); // chama mesmo metodo recursivamente par aadd a letra
            atual.deleteCharAt(atual.length() - 1); // exclui o ja usado
            usada[i] = false; // reseta info de usado
        }
    }
}