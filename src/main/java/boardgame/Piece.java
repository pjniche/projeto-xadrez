package boardgame;

/** A piece has a position and a board to be in. */
public class Piece {

  private Position position;
  private Board board;

  public Piece(Board board, Position position) {
    this.board = new Board(board.getRows(), board.getColumns());
    this.position = new Position(position.getRow(), position.getColumn());
  }

  public Board getBoard() {
    return new Board(board.getRows(), board.getColumns());
  }

  public Position getPosition() {
    return new Position(position.getRow(), position.getColumn());
  }

  public void setPosition(Position position) {
    this.position.setRow(position.getRow());
    this.position.setColumn(position.getColumn());
  }
}
