package application;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import exception.ChessException;
import java.util.InputMismatchException;
import java.util.Scanner;

/** Main class of the program. */
public class Program {

  /**
   * Runs the program.
   *
   * @param args No args needed.
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in, "UTF-8");
    ChessMatch chessMatch = new ChessMatch();

    while (true) {
      try {
        UserInterface.clearScreen();
        UserInterface.printBoard(chessMatch.getPieces());
        System.out.println();
        System.out.print("Source: ");
        ChessPosition source = UserInterface.readChessPosition(sc);

        System.out.println();
        System.out.print("Target: ");
        ChessPosition target = UserInterface.readChessPosition(sc);

        ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
      } catch (ChessException e) {
        System.out.println(e.getMessage());
        sc.nextLine();
      } catch (InputMismatchException e) {
        System.out.println(e.getMessage());
        sc.nextLine();
      }
    }
  }
}
