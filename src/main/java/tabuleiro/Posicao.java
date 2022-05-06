package tabuleiro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/** Representacao matemática de uma posicao no tabuleiro. */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Posicao {

  private int linha;
  private int coluna;

  /**
   * Setter auxiliar para os 2 campos ao mesmo tempo.
   *
   * @param linha Nova linha.
   * @param coluna Nova coluna.
   */
  public void setPosicao(int linha, int coluna) {
    this.linha = linha;
    this.coluna = coluna;
  }
}
