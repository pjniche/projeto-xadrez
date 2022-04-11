import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.ChessMatch;
import chess.ChessPiece;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChessMatchTest {

  ChessMatch match;

  @BeforeEach
  public void setup() {
    match = new ChessMatch();
  }

  @Test
  public void testGetPieces_whenAnything_thenReturnMatrixOfChessPieces() {
    // Arrange
    ChessPiece[][] chessPieces;
    // Act
    chessPieces = match.getPieces();
    ChessPiece[][] finalChessPieces = chessPieces;
    // Assert
    assertEquals(8, chessPieces.length);
    assertEquals(8, chessPieces[7].length);
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> Arrays.stream(finalChessPieces[8]));
  }

  // Test template.
  @Test
  public void testMethodName_whenCondition_thenExpectedValue() {
    // ToDo: Tests for all methods of all classes.
    // Arrange
    // Act
    // Assert
  }
}
