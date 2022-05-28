package xadrez;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import excecao.ChessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tabuleiro.NotacaoXadrez;
import tabuleiro.Posicao;

public class PartidaTest {

  Partida partida;

  @BeforeEach
  public void setup() {
    partida = new Partida();
  }

  @Test
  public void testMovimentoDeXadrez_quandoValido_entaoMovePeca() {
    // Acao
    partida.movimentoDeXadrez(new NotacaoXadrez('a', 2), new NotacaoXadrez('a', 3));
    // Checagem
    assertTrue(partida.getTabuleiro().temPeca(new Posicao(7, 0)));
  }

  @Test
  public void testMovimentoDeXadrez_quandoMovimentoImpossivel_entaoJogaExcecao() {
    // Nao eh possivel mover o rei 2 casas a frente.
    assertThrows(
        ChessException.class,
        () -> partida.movimentoDeXadrez(new NotacaoXadrez('e', 1), new NotacaoXadrez('e', 3)));
  }

  @Test
  public void testMovimentoDaTorre_quandoInvalido_entaoJogaExcecao() {
    assertThrows(
        ChessException.class,
        () -> partida.movimentoDeXadrez(new NotacaoXadrez('a', 1), new NotacaoXadrez('b', 2)));
  }

  // Modelo para testes.
  @Test
  public void testNomeDaFuncao_quandoCondicao_entaoRetornoExperado() {
    // ToDo: Testes para as outras funcoes
    // Preparacao
    // Acao
    // Checagem
  }
}
