package pecas;

import java.util.Arrays;
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

  @Override
  public boolean[][] movimentosPossiveis() {
    boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
    // TEMPORARIO: Torna todas as opcoes possiveis.
    for (boolean[] linha : matriz) {
      Arrays.fill(linha, true);
    }
    return matriz;
  }
}
