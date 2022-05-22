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
    novaPosicao.setPosicao(novaPosicao.getLinha() - 1, novaPosicao.getColuna());
    if (getTabuleiro().posicaoExiste(novaPosicao) && podeMover(novaPosicao)) {
      matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
    }

    // Baixo
    novaPosicao.setPosicao(novaPosicao.getLinha() + 1, novaPosicao.getColuna());
    if (getTabuleiro().posicaoExiste(novaPosicao) && podeMover(novaPosicao)) {
      matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
    }

    // Esquerda
    novaPosicao.setPosicao(novaPosicao.getLinha(), novaPosicao.getColuna() - 1);
    if (getTabuleiro().posicaoExiste(novaPosicao) && podeMover(novaPosicao)) {
      matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
    }

    // Direita
    novaPosicao.setPosicao(novaPosicao.getLinha(), novaPosicao.getColuna() + 1);
    if (getTabuleiro().posicaoExiste(novaPosicao) && podeMover(novaPosicao)) {
      matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
    }

    // Noroeste
    novaPosicao.setPosicao(novaPosicao.getLinha() - 1, novaPosicao.getColuna() - 1);
    if (getTabuleiro().posicaoExiste(novaPosicao) && podeMover(novaPosicao)) {
      matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
    }

    // Nordeste
    novaPosicao.setPosicao(novaPosicao.getLinha() - 1, novaPosicao.getColuna() + 1);
    if (getTabuleiro().posicaoExiste(novaPosicao) && podeMover(novaPosicao)) {
      matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
    }

    // Sudoeste
    novaPosicao.setPosicao(novaPosicao.getLinha() + 1, novaPosicao.getColuna() - 1);
    if (getTabuleiro().posicaoExiste(novaPosicao) && podeMover(novaPosicao)) {
      matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
    }

    // Sudeste
    novaPosicao.setPosicao(novaPosicao.getLinha() + 1, novaPosicao.getColuna() - 1);
    if (getTabuleiro().posicaoExiste(novaPosicao) && podeMover(novaPosicao)) {
      matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
    }

    return matriz;
  }
}
