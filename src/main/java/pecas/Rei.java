package pecas;

import tabuleiro.Posicao;
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

  private boolean podeMover(Posicao posicao) {
    PecaDeXadrez peca = (PecaDeXadrez)getTabuleiro().getPeca(posicao);
    return peca == null || peca.getCor() != getCor();
  }

  @Override
  public boolean[][] movimentosPossiveis() {
    boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

    Posicao novaPosicao = new Posicao(0, 0);

    // Cima
    novaPosicao.setPosicao(this.getPosicao().getLinha() - 1, this.getPosicao().getColuna());
    if (getTabuleiro().posicaoExiste(novaPosicao) && podeMover(novaPosicao)) {
      matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
    }

    // Baixo
    novaPosicao.setPosicao(this.getPosicao().getLinha() + 1, this.getPosicao().getColuna());
    if (getTabuleiro().posicaoExiste(novaPosicao) && podeMover(novaPosicao)) {
      matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
    }

    // Esquerda
    novaPosicao.setPosicao(this.getPosicao().getLinha(), this.getPosicao().getColuna() - 1);
    if (getTabuleiro().posicaoExiste(novaPosicao) && podeMover(novaPosicao)) {
      matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
    }

    // Direita
    novaPosicao.setPosicao(this.getPosicao().getLinha(), this.getPosicao().getColuna() + 1);
    if (getTabuleiro().posicaoExiste(novaPosicao) && podeMover(novaPosicao)) {
      matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
    }

    // Noroeste
    novaPosicao.setPosicao(this.getPosicao().getLinha() - 1, this.getPosicao().getColuna() - 1);
    if (getTabuleiro().posicaoExiste(novaPosicao) && podeMover(novaPosicao)) {
      matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
    }

    // Nordeste
    novaPosicao.setPosicao(this.getPosicao().getLinha() - 1, this.getPosicao().getColuna() + 1);
    if (getTabuleiro().posicaoExiste(novaPosicao) && podeMover(novaPosicao)) {
      matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
    }

    // Sudoeste
    novaPosicao.setPosicao(this.getPosicao().getLinha() + 1, this.getPosicao().getColuna() - 1);
    if (getTabuleiro().posicaoExiste(novaPosicao) && podeMover(novaPosicao)) {
      matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
    }

    // Sudeste
    novaPosicao.setPosicao(this.getPosicao().getLinha() + 1, this.getPosicao().getColuna() - 1);
    if (getTabuleiro().posicaoExiste(novaPosicao) && podeMover(novaPosicao)) {
      matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
    }

    return matriz;
  }
}
