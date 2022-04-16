package pecas;

import lombok.Getter;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

/** Uma peca de xadrez com uma cor. */
public class PecaDeXadrez extends Peca {

  @Getter private Cor cor;

  /**
   * Construtor customizado.
   *
   * @param tabuleiro Tabuleiro de xadrez.
   * @param cor Cor da peca.
   */
  public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
    super(tabuleiro, new Posicao());
    this.cor = cor;
  }
}
