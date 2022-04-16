package interfacevisual;

import java.util.InputMismatchException;
import java.util.Scanner;
import pecas.Cor;
import pecas.PecaDeXadrez;
import tabuleiro.NotacaoXadrez;

/** Interface de usuario com o terminal. */
public class InterfaceTerminal {

  // Cores
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_WHITE = "\u001B[37m";
  public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";

  /** Limpa a tela do terminal. */
  public static void limpaTela() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  /**
   * Lê uma posicao em notacao de xadrez do usuario.
   *
   * @param scanner Scanner.
   * @return Notacao de xadrez.
   */
  public static NotacaoXadrez leNotacaoXadrez(Scanner scanner) {
    try {
      String entrada = scanner.nextLine();
      char coluna = entrada.charAt(0);
      int linha = Integer.parseInt(entrada.substring(1));
      return new NotacaoXadrez(coluna, linha);
    } catch (RuntimeException e) {
      throw new InputMismatchException("Posicao invalida, entre com valores de a1 ate h8!");
    }
  }

  /**
   * Desenha o tabuleiro no terminal.
   *
   * @param pecas Matriz de pecas.
   */
  public static void mostraTabuleiro(PecaDeXadrez[][] pecas) {
    for (int i = 0; i < pecas.length; i++) {
      System.out.print((8 - i) + " ");
      for (int j = 0; j < pecas.length; j++) {
        mostraPeca(pecas[i][j]);
      }
      System.out.println();
    }
    System.out.println(" a b c d e f g h");
  }

  /**
   * Desenha uma peca no terminal.
   *
   * @param peca Peca.
   */
  private static void mostraPeca(PecaDeXadrez peca) {
    if (peca == null) {
      System.out.print("-");
    } else {
      if (peca.getCor() == Cor.BRANCO) {
        System.out.print(ANSI_WHITE + peca + ANSI_RESET);
      } else {
        System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
      }
    }
    System.out.print(" ");
  }
}