package xadrez;

import excecao.ChessException;
import lombok.Getter;
import pecas.Cor;
import pecas.Peca;
import pecas.PecaDeXadrez;
import pecas.Rei;
import pecas.Torre;
import tabuleiro.NotacaoXadrez;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

/** Uma partida de xadrez. */
public class Partida {

  @Getter private Tabuleiro tabuleiro;

  /** Construtor customizado. */
  public Partida() {
    tabuleiro = new Tabuleiro(8, 8);
    pecasIniciais();
  }

  /**
   * Cria uma matriz de pecas.
   *
   * @return Matriz de pecas (pecas podem ser nulas).
   */
  public PecaDeXadrez[][] getPecas() {
    PecaDeXadrez[][] matriz = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
    for (int i = 0; i < tabuleiro.getLinhas(); i++) {
      for (int j = 0; j < tabuleiro.getColunas(); j++) {
        matriz[i][j] = (PecaDeXadrez) tabuleiro.getPeca(i, j);
      }
    }
    return matriz;
  }

  /**
   * Faz um movimento de xadrez e retorna a peca capturada ou null.
   *
   * @param posicaoInicial Posicao inicial da peca movida.
   * @param posicaoFinal Posicao final da peca movida.
   * @return Peca capturada, ou null.
   */
  public PecaDeXadrez movimentoDeXadrez(NotacaoXadrez posicaoInicial, NotacaoXadrez posicaoFinal) {
    Posicao posInicial = posicaoInicial.paraPosicao();
    Posicao posFinal = posicaoFinal.paraPosicao();
    validaPosicao(posInicial);
    Peca pecaCapturada = mover(posInicial, posFinal);
    return (PecaDeXadrez) pecaCapturada;
  }

  /**
   * Move a peca e captura caso necessario.
   *
   * @param posInicial Posicao inicial da peca movida.
   * @param posFinal Posicao final da peca movida.
   * @return Peca capturada ou null.
   */
  private Peca mover(Posicao posInicial, Posicao posFinal) {
    Peca peca = tabuleiro.tiraPeca(posInicial);
    Peca pecaCapturada = tabuleiro.tiraPeca(posFinal);
    tabuleiro.colocaPeca(peca, posFinal);
    return pecaCapturada;
  }

  /**
   * Checa se a posicao dada é valida.
   *
   * @param posicao Posicao para validar.
   */
  private void validaPosicao(Posicao posicao) {
    if (!tabuleiro.temPeca(posicao)) {
      throw new ChessException("Essa posicao nao tem peca.");
    }
    if (!tabuleiro.getPeca(posicao).existeMovimentoPossivel()) {
      throw new ChessException("Nao existe movimento possivel para esta peca.");
    }
  }

  /**
   * Coloca uma nova peca no tabuleiro.
   *
   * @param coluna Coluna da peca.
   * @param linha Linha da peca.
   * @param peca Nova peca.
   */
  private void novaPeca(char coluna, int linha, PecaDeXadrez peca) {
    tabuleiro.colocaPeca(peca, new NotacaoXadrez(coluna, linha).paraPosicao());
  }

  /** Coloca todas as pecasno tabuleiro em suas posicões iniciais. */
  private void pecasIniciais() {
    novaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
    novaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
    novaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO));

    novaPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
    novaPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
    novaPeca('e', 8, new Rei(tabuleiro, Cor.PRETO));
  }
}
