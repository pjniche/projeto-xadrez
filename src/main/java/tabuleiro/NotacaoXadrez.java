package tabuleiro;

import excecao.ChessException;
import lombok.Getter;
import lombok.ToString;

/** Representacao de posicao usando a notacao do xadrez. */
@ToString
public class NotacaoXadrez {

  @Getter private char coluna;
  @Getter private int linha;

  /**
   * Construtor customizado.
   *
   * @param coluna Coluna.
   * @param linha Linha.
   */
  public NotacaoXadrez(char coluna, int linha) {
    if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
      throw new ChessException("Posicao invalida, entre com valores de a1 ate h8!");
    }
    this.coluna = coluna;
    this.linha = linha;
  }

  /**
   * Converte para Posicao matematica.
   *
   * @return Posicao.
   */
  public Posicao paraPosicao() {
    return new Posicao(8 - linha, coluna - 'a');
  }

  /**
   * Converte uma posicao para a notacao de xadrez.
   *
   * @param posicao Posicao.
   * @return NotacaoXadrez.
   */
  protected static NotacaoXadrez dePosicao(Posicao posicao) {
    return new NotacaoXadrez((char) ('a' - posicao.getColuna()), 8 - posicao.getLinha());
  }
}
