package xadrez;

import excecao.ChessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tabuleiro.NotacaoXadrez;
import tabuleiro.Posicao;

import static org.junit.jupiter.api.Assertions.*;

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

  @Test
  public void testMatePastor_quandoPartidaPadrao_entaoMate() {
    partida.movimentoDeXadrez(new NotacaoXadrez('e', 2), new NotacaoXadrez('e', 4));
    partida.movimentoDeXadrez(new NotacaoXadrez('e', 7), new NotacaoXadrez('e', 5));
    partida.movimentoDeXadrez(new NotacaoXadrez('f', 1), new NotacaoXadrez('c', 4));
    partida.movimentoDeXadrez(new NotacaoXadrez('b', 8), new NotacaoXadrez('c', 6));
    partida.movimentoDeXadrez(new NotacaoXadrez('d', 1), new NotacaoXadrez('h', 5));
    partida.movimentoDeXadrez(new NotacaoXadrez('g', 8), new NotacaoXadrez('f', 6));
    partida.movimentoDeXadrez(new NotacaoXadrez('h', 5), new NotacaoXadrez('f', 7));
    assertTrue(partida.getXequeMate());
  }

  @Test
  public void testMateDoLouco_quandoPartidaPadrao_entaoMate() {
    partida.movimentoDeXadrez(new NotacaoXadrez('f', 2), new NotacaoXadrez('f', 3));
    partida.movimentoDeXadrez(new NotacaoXadrez('e', 7), new NotacaoXadrez('e', 5));
    partida.movimentoDeXadrez(new NotacaoXadrez('g', 2), new NotacaoXadrez('g', 4));
    partida.movimentoDeXadrez(new NotacaoXadrez('d', 8), new NotacaoXadrez('h', 4));
    assertTrue(partida.getXequeMate());
  }

  @Test
  public void testDefesa_quandoXeque_entaoNaoEhMate() {
    partida.movimentoDeXadrez(new NotacaoXadrez('c', 2), new NotacaoXadrez('c', 3));
    partida.movimentoDeXadrez(new NotacaoXadrez('d', 7), new NotacaoXadrez('d', 6));
    partida.movimentoDeXadrez(new NotacaoXadrez('d', 1), new NotacaoXadrez('a', 4));
    assertTrue(partida.getXeque());
    assertFalse(partida.getXequeMate());
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
