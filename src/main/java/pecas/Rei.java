package pecas;

import tabuleiro.Tabuleiro;

/** Peca Rei de xadrez. */
public class Rei extends PecaDeXadrez {

  /**
   * Construtor customizado.
   *
   * @param tabuleiro Tabuleiro de xadrez.
   * @param cor Cor da peca.
   */
  public Rei(Tabuleiro tabuleiro, Cor cor) {
    super(tabuleiro, cor);
  }

  @Override
  public String toString() {
    return "R";
  }

  @Override
  public boolean[][] movimentosPossiveis() {
    boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
    // TEMPORARIO: Torna todas as opcoes impossiveis pois o default eh false.
    return matriz;
  }
}
