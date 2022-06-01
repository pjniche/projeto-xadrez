package pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Partida;

/** Peca Rei de xadrez. */
public class Rei extends PecaDeXadrez {

  Partida partida;

  /**
   * Construtor customizado.
   *
   * @param tabuleiro Tabuleiro de xadrez.
   * @param cor Cor da peca.
   */
  public Rei(Tabuleiro tabuleiro, Cor cor,Partida partida) {
    super(tabuleiro, cor);
    this.partida = partida;
  }

  @Override
  public String toString() {
    return "R";
  }

  private boolean podeMover(Posicao posicao) {
    PecaDeXadrez peca = (PecaDeXadrez)getTabuleiro().getPeca(posicao);
    return peca == null || peca.getCor() != getCor();
  }

  private boolean testRookCastling(Posicao posicao) {
    PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().getPeca(posicao);
    return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContagemDeMovimento() == 0;
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

    // Roque
    if (getContagemDeMovimento() == 0 && !partida.getXeque()) {

      // Roque menor
      Posicao posT1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
      if (testRookCastling(posT1)) {
        Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
        Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
        if (getTabuleiro().getPeca(p1) == null && getTabuleiro().getPeca(p2) == null) {
          matriz[posicao.getLinha()][posicao.getColuna() + 2] = true;
        }
      }

      // Roque maior
      Posicao posT2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
      if (testRookCastling(posT2)) {
        Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
        Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
        Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
        if (getTabuleiro().getPeca(p1) == null && getTabuleiro().getPeca(p2) == null && getTabuleiro().getPeca(p3) == null) {
          matriz[posicao.getLinha()][posicao.getColuna() - 2] = true;
        }
      }
    }

    return matriz;
  }
}
