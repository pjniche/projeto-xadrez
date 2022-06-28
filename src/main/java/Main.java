import excecao.ChessException;
import interfacevisual.InterfaceTerminal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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
    List<PecaDeXadrez> pecasCapturadas = new ArrayList<>();

    while (!partida.getXequeMate()) {
      try {
        InterfaceTerminal.limpaTela();
        InterfaceTerminal.mostraPartida(partida, pecasCapturadas);
        System.out.println();
        System.out.print("Posicao Inicial: ");
        NotacaoXadrez posicaoInicial = InterfaceTerminal.leNotacaoXadrez(scanner);

        boolean[][] movimentosPossiveis = partida.movimentosPossiveis(posicaoInicial);
        InterfaceTerminal.limpaTela();
        InterfaceTerminal.mostraTabuleiro(partida.getPecas(), movimentosPossiveis);
        System.out.println();
        System.out.print("Posicao Final: ");
        NotacaoXadrez posicaoFinal = InterfaceTerminal.leNotacaoXadrez(scanner);

        PecaDeXadrez pecaCapturada = partida.movimentoDeXadrez(posicaoInicial, posicaoFinal);

        if (pecaCapturada != null) {
          pecasCapturadas.add(pecaCapturada);
        }

        if (partida.getPromovido() != null){
          System.out.print("Digite a peca desejada para promocao (B/C/R/D): ");
          String tipo = scanner.nextLine();
          partida.trocaPecaPromovida(tipo);
        }
      } catch (ChessException e) {
        log.warn(e.getMessage());
        scanner.nextLine();
      } catch (InputMismatchException e) {
        log.warn(e.getMessage());
        scanner.nextLine();
      }
    }
    InterfaceTerminal.limpaTela();
    InterfaceTerminal.mostraPartida(partida,pecasCapturadas);
  }
}
