package pecas;

import lombok.Getter;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

/** Uma peca possui uma posicao e um tabuleiro para estar. */
public abstract class Peca {

  protected Posicao posicao;
  // Vai causar o bug expose representation, mas ainda nao achei maneira melhor de resolver
  @Getter private Tabuleiro tabuleiro;

  /**
   * Construtor customizado.
   *
   * @param tabuleiro Tabuleiro.
   * @param posicao Posicao da peca.
   */
  public Peca(Tabuleiro tabuleiro, Posicao posicao) {
    this.tabuleiro = tabuleiro;
    this.posicao = new Posicao(posicao.getLinha(), posicao.getColuna());
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

  /**
   * Deve ser implementada por cada peca para checar os movimentos possiveis na situacao atual.
   *
   * @return Matriz de booleanos representando a possibilidade de cada movimento no tabuleiro.
   */
  public abstract boolean[][] movimentosPossiveis();

  /**
   * Usa a implementacao da classe abstrata 'movimentosPossiveis' para checar se a posicao dada eh
   * possivel.
   *
   * @param posicao Posicao a ser checada.
   * @return True ou False.
   */
  public boolean movimentoPossivel(Posicao posicao) {
    return movimentosPossiveis()[posicao.getLinha()][posicao.getColuna()];
  }

  /**
   * Usa a implementacao da classe abstrata 'movimentosPossiveis' para checar se existe algum
   * movimento possivel a ser feito.
   *
   * @return True ou False.
   */
  public boolean existeMovimentoPossivel() {
    boolean[][] matriz = movimentosPossiveis();
    for (boolean[] linha : matriz) {
      for (boolean coluna : linha) {
        // Checa se ha movimento possivel nessa coluna.
        if (coluna) {
          return true;
        }
        // Se nao eh possivel, continua checando.
      }
    }
    return false;
  }
}
