import excecao.ChessException;
import interfacevisual.InterfaceTerminal;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;
import pecas.PecaDeXadrez;
import tabuleiro.NotacaoXadrez;
import xadrez.Partida;

/** Classe principal do programa. */
@Slf4j
public class Main {

  /**
   * Loop do jogo.
   *
   * @param args Nenhum arg necessario.
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
    Partida partida = new Partida();

    while (true) {
      try {
        InterfaceTerminal.limpaTela();
        InterfaceTerminal.mostraTabuleiro(partida.getPecas());
        System.out.println();
        System.out.print("Posicao Inicial: ");
        NotacaoXadrez posicaoInicial = InterfaceTerminal.leNotacaoXadrez(scanner);

        System.out.println();
        System.out.print("Posicao Final: ");
        NotacaoXadrez posicaoFinal = InterfaceTerminal.leNotacaoXadrez(scanner);

        PecaDeXadrez pecaCapturada = partida.movimentoDeXadrez(posicaoInicial, posicaoFinal);
      } catch (ChessException e) {
        log.warn(e.getMessage());
        scanner.nextLine();
      } catch (InputMismatchException e) {
        log.warn(e.getMessage());
        scanner.nextLine();
      }
    }
  }
}
