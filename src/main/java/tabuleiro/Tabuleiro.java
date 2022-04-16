package tabuleiro;

import excecao.BoardException;
import lombok.Getter;
import lombok.Setter;
import pecas.Peca;

/** Representa um tabuleiro de xadrez como uma matriz. */
public class Tabuleiro {

  @Getter @Setter private int linhas;
  @Getter @Setter private int colunas;
  private Peca[][] pecas;

  /**
   * Construtor customizado.
   *
   * @param linhas Quantas linhas.
   * @param colunas Quantas colunas.
   */
  public Tabuleiro(int linhas, int colunas) {
    if (linhas < 1 || colunas < 1) {
      throw new BoardException("Erro criando o tabuleiro!");
    }
    this.linhas = linhas;
    this.colunas = colunas;
    pecas = new Peca[linhas][colunas];
  }

  /**
   * Pega a peca da posicao dada.
   *
   * @param linha Linha da peca.
   * @param coluna Coluna da peca.
   * @return Peca ou null.
   */
  public Peca getPeca(int linha, int coluna) {
    if (!posicaoExiste(linha, coluna)) {
      throw new BoardException("Posicao invalida!");
    }
    return pecas[linha][coluna];
  }

  /**
   * Pega a poca da posicao dada.
   *
   * @param posicao Posicao.
   * @return Peca ou null.
   */
  public Peca getPeca(Posicao posicao) {
    if (!posicaoExiste(posicao)) {
      throw new BoardException("Posicao invalida!");
    }
    return pecas[posicao.getLinha()][posicao.getColuna()];
  }

  /**
   * Coloca a peca na posicao dada.
   *
   * @param peca Peca selecionada.
   * @param posicao Posicao de destino.
   */
  public void colocaPeca(Peca peca, Posicao posicao) {
    if (temPeca(posicao)) {
      throw new BoardException("Ja existe uma peca na posicao " + posicao);
    }
    pecas[posicao.getLinha()][posicao.getColuna()] = peca;
    peca.setPosicao(posicao);
  }

  /**
   * Remove a peca da posicao dada.
   *
   * @param posicao Posicao.
   * @return Peca ou null.
   */
  public Peca tiraPeca(Posicao posicao) {
    if (!posicaoExiste(posicao)) {
      throw new BoardException("Posicao invalida!");
    }
    if (getPeca(posicao) == null) {
      return null;
    }
    Peca aux = getPeca(posicao);
    aux.setPosicao(new Posicao());
    pecas[posicao.getLinha()][posicao.getColuna()] = null;
    return aux;
  }

  /**
   * Checa se a posicao existe.
   *
   * @param linha Linha.
   * @param coluna Coluna.
   * @return True ou False.
   */
  private boolean posicaoExiste(int linha, int coluna) {
    return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
  }

  /**
   * Checa se a posicao existe.
   *
   * @param posicao Posicao.
   * @return True ou False.
   */
  public boolean posicaoExiste(Posicao posicao) {
    return posicaoExiste(posicao.getLinha(), posicao.getColuna());
  }

  /**
   * Checa se existe uma peca na posicao dada.
   *
   * @param posicao Posicao.
   * @return True ou False.
   */
  public boolean temPeca(Posicao posicao) {
    if (!posicaoExiste(posicao)) {
      throw new BoardException("Posicao invalida!");
    }
    return getPeca(posicao) != null;
  }
}
