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
}
