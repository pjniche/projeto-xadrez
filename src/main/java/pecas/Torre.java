package pecas;

import tabuleiro.Tabuleiro;

/** Peca Torre de xadrez. */
public class Torre extends PecaDeXadrez {

  /**
   * Construtor customizado.
   *
   * @param tabuleiro Tabuleiro de xadrez.
   * @param cor Cor da peca.
   */
  public Torre(Tabuleiro tabuleiro, Cor cor) {
    super(tabuleiro, cor);
  }

  @Override
  public String toString() {
    return "T";
  }
}
