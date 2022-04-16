package pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

/** Uma peca possui uma posicao e um tabuleiro para estar. */
public class Peca {

  private Posicao posicao;
  private Tabuleiro tabuleiro;

  /**
   * Construtor customizado.
   *
   * @param tabuleiro Tabuleiro.
   * @param posicao Posicao da peca.
   */
  public Peca(Tabuleiro tabuleiro, Posicao posicao) {
    this.tabuleiro = new Tabuleiro(tabuleiro.getLinhas(), tabuleiro.getColunas());
    this.posicao = new Posicao(posicao.getLinha(), posicao.getColuna());
  }

  /**
   * Getter customizado para tabuleiro. Evita os bugs: EI_EXPOSE_REP, EI_EXPOSE_REP2
   *
   * @return Tabuleiro.
   */
  public Tabuleiro getTabuleiro() {
    return new Tabuleiro(tabuleiro.getLinhas(), tabuleiro.getColunas());
  }

  /**
   * Getter customizado para posicao. Evita os bugs: EI_EXPOSE_REP, EI_EXPOSE_REP2
   *
   * @return Posicao.
   */
  public Posicao getPosicao() {
    return new Posicao(posicao.getLinha(), posicao.getColuna());
  }

  /**
   * Setter customizado para posicao. Evita os bugs: EI_EXPOSE_REP, EI_EXPOSE_REP2
   *
   * @param posicao Nova posicao.
   */
  public void setPosicao(Posicao posicao) {
    this.posicao.setLinha(posicao.getLinha());
    this.posicao.setColuna(posicao.getColuna());
  }
}