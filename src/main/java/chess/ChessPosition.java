package chess;

import boardgame.Position;
import exception.ChessException;
import lombok.Getter;
import lombok.ToString;

@ToString
public class ChessPosition {

  @Getter private char column;
  @Getter private int row;

  public ChessPosition(char column, int row) {
    if (column < 'a' || column > 'h' || row < 1 || row > 8) {
      throw new ChessException("Posicao invalida, entre com valores de a1 ate h8!");
    }
    this.column = column;
    this.row = row;
  }

  protected Position toPosition() {
    return new Position(8 - row, column - 'a');
  }

  protected static ChessPosition fromPosition(Position position) {
    return new ChessPosition((char) ('a' - position.getColumn()), 8 - position.getRow());
  }
}
