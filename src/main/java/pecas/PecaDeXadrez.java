package pecas;

import lombok.Getter;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

/** Uma peca de xadrez com uma cor. */
public abstract class PecaDeXadrez extends Peca {

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

  /**
   * Checa se existe uma peca da cor oposta na posicao dada.
   *
   * @param posicao Posicao a checar.
   * @return Sim ou nao.
   */
  public boolean temPecaInimiga(Posicao posicao) {
    PecaDeXadrez peca = (PecaDeXadrez) getTabuleiro().getPeca(posicao);
    return peca != null && peca.getCor() != this.cor;
  }
}
