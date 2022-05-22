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

    Posicao posicao = new Posicao(0, 0);

    // Cima
    posicao.setPosicao(posicao.getLinha() - 1, posicao.getColuna());
    if (getTabuleiro().posicaoExiste(posicao) && podeMover(posicao)) {
      matriz[posicao.getLinha()][posicao.getColuna()] = true;
    }

    // Baixo
    posicao.setPosicao(posicao.getLinha() + 1, posicao.getColuna());
    if (getTabuleiro().posicaoExiste(posicao) && podeMover(posicao)) {
      matriz[posicao.getLinha()][posicao.getColuna()] = true;
    }

    // Esquerda
    posicao.setPosicao(posicao.getLinha(), posicao.getColuna() - 1);
    if (getTabuleiro().posicaoExiste(posicao) && podeMover(posicao)) {
      matriz[posicao.getLinha()][posicao.getColuna()] = true;
    }

    // Direita
    posicao.setPosicao(posicao.getLinha(), posicao.getColuna() + 1);
    if (getTabuleiro().posicaoExiste(posicao) && podeMover(posicao)) {
      matriz[posicao.getLinha()][posicao.getColuna()] = true;
    }

    // Noroeste
    posicao.setPosicao(posicao.getLinha() - 1, posicao.getColuna() - 1);
    if (getTabuleiro().posicaoExiste(posicao) && podeMover(posicao)) {
      matriz[posicao.getLinha()][posicao.getColuna()] = true;
    }

    // Nordeste
    posicao.setPosicao(posicao.getLinha() - 1, posicao.getColuna() + 1);
    if (getTabuleiro().posicaoExiste(posicao) && podeMover(posicao)) {
      matriz[posicao.getLinha()][posicao.getColuna()] = true;
    }

    // Sudoeste
    posicao.setPosicao(posicao.getLinha() + 1, posicao.getColuna() - 1);
    if (getTabuleiro().posicaoExiste(posicao) && podeMover(posicao)) {
      matriz[posicao.getLinha()][posicao.getColuna()] = true;
    }

    // Sudeste
    posicao.setPosicao(posicao.getLinha() + 1, posicao.getColuna() - 1);
    if (getTabuleiro().posicaoExiste(posicao) && podeMover(posicao)) {
      matriz[posicao.getLinha()][posicao.getColuna()] = true;
    }

    return matriz;
  }
}
