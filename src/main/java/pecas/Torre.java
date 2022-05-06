package pecas;

import tabuleiro.Posicao;
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

    Posicao novaPosicao = new Posicao(0, 0);

    // Cima
    novaPosicao.setPosicao(this.getPosicao().getLinha() - 1, this.getPosicao().getColuna());
    while (getTabuleiro().posicaoExiste(novaPosicao) && !getTabuleiro().temPeca(novaPosicao)) {
      matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
      novaPosicao.setLinha(novaPosicao.getLinha() - 1);
    }
    if (getTabuleiro().posicaoExiste(novaPosicao) && temPecaInimiga(novaPosicao)) {
      matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
    }

    // Baixo
    novaPosicao.setPosicao(this.getPosicao().getLinha() + 1, this.getPosicao().getColuna());
    while (getTabuleiro().posicaoExiste(novaPosicao) && !getTabuleiro().temPeca(novaPosicao)) {
      matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
      novaPosicao.setLinha(novaPosicao.getLinha() + 1);
    }
    if (getTabuleiro().posicaoExiste(novaPosicao) && temPecaInimiga(novaPosicao)) {
      matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
    }

    // Direita
    novaPosicao.setPosicao(this.getPosicao().getLinha(), this.getPosicao().getColuna() + 1);
    while (getTabuleiro().posicaoExiste(novaPosicao) && !getTabuleiro().temPeca(novaPosicao)) {
      matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
      novaPosicao.setColuna(novaPosicao.getColuna() + 1);
    }
    if (getTabuleiro().posicaoExiste(novaPosicao) && temPecaInimiga(novaPosicao)) {
      matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
    }

    // Esquerda
    novaPosicao.setPosicao(this.getPosicao().getLinha(), this.getPosicao().getColuna() - 1);
    while (getTabuleiro().posicaoExiste(novaPosicao) && !getTabuleiro().temPeca(novaPosicao)) {
      matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
      novaPosicao.setColuna(novaPosicao.getColuna() - 1);
    }
    if (getTabuleiro().posicaoExiste(novaPosicao) && temPecaInimiga(novaPosicao)) {
      matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
    }

    return matriz;
  }
}
