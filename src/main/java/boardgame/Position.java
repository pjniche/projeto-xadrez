package boardgame;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/** The 2D representation of a position in a board. */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Position {

  private int row;
  private int column;
}
