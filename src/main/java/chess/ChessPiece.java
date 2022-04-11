package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import lombok.Getter;

/** A chess piece with a color. */
public class ChessPiece extends Piece {

  @Getter private Color color;

  public ChessPiece(Board board, Color color) {
    super(board, new Position());
    this.color = color;
  }
}
