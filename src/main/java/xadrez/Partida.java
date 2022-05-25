package xadrez;

import excecao.ChessException;
import lombok.Getter;
import pecas.Cor;
import pecas.Peca;
import pecas.PecaDeXadrez;
import pecas.Rei;
import pecas.Torre;
import tabuleiro.NotacaoXadrez;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** Uma partida de xadrez. */
public class Partida {

  private int rodada;
  private Cor jogadorAtual;
  @Getter private Tabuleiro tabuleiro;
  private boolean xeque;

  private List<Peca> pecasNoTabuleiro = new ArrayList<>();
  private List<Peca> pecasCapturadas = new ArrayList<>();

  /** Construtor customizado. */
  public Partida() {
    tabuleiro = new Tabuleiro(8, 8);
    rodada = 1;
    jogadorAtual = Cor.BRANCO;
    pecasIniciais();
  }

  public int getRodada() {
    return rodada;
  }

  public Cor getJogadorAtual() {
    return jogadorAtual;
  }

  /**
   * Cria uma matriz de pecas.
   *
   * @return Matriz de pecas (pecas podem ser nulas).
   */
  public PecaDeXadrez[][] getPecas() {
    PecaDeXadrez[][] matriz = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
    for (int i = 0; i < tabuleiro.getLinhas(); i++) {
      for (int j = 0; j < tabuleiro.getColunas(); j++) {
        matriz[i][j] = (PecaDeXadrez) tabuleiro.getPeca(i, j);
      }
    }
    return matriz;
  }

  public boolean[][] movimentosPossiveis(NotacaoXadrez posicaoInicial) {
    Posicao posicao = posicaoInicial.paraPosicao();
    validaPosicao(posicao);
    return tabuleiro.getPeca(posicao).movimentosPossiveis();
  }

  /**
   * Faz um movimento de xadrez e retorna a peca capturada ou null.
   *
   * @param posicaoInicial Posicao inicial da peca movida.
   * @param posicaoFinal Posicao final da peca movida.
   * @return Peca capturada, ou null.
   */
  public PecaDeXadrez movimentoDeXadrez(NotacaoXadrez posicaoInicial, NotacaoXadrez posicaoFinal) {
    Posicao posInicial = posicaoInicial.paraPosicao();
    Posicao posFinal = posicaoFinal.paraPosicao();
    validaPosicao(posInicial);
    validaDestino(posInicial, posFinal);
    Peca pecaCapturada = mover(posInicial, posFinal);
    proximaRodada();
    return (PecaDeXadrez) pecaCapturada;
  }

  /**
   * Move a peca e captura caso necessario.
   *
   * @param posInicial Posicao inicial da peca movida.
   * @param posFinal Posicao final da peca movida.
   * @return Peca capturada ou null.
   */
  private Peca mover(Posicao posInicial, Posicao posFinal) {
    Peca peca = tabuleiro.tiraPeca(posInicial);
    Peca pecaCapturada = tabuleiro.tiraPeca(posFinal);
    tabuleiro.colocaPeca(peca, posFinal);

    if (pecaCapturada != null) {
      pecasNoTabuleiro.remove(pecaCapturada);
      pecasCapturadas.add(pecaCapturada);
    }

    return pecaCapturada;
  }

  private void desfazerMovimento(Posicao posInicial, Posicao posFinal, Peca pecaCapturada){
    Peca peca = tabuleiro.tiraPeca(posFinal);
    tabuleiro.colocaPeca(peca, posInicial);

    if(pecaCapturada != null){
      tabuleiro.colocaPeca(pecaCapturada, posFinal);
      pecasCapturadas.remove(pecaCapturada);
      pecasNoTabuleiro.add(pecaCapturada);
    }
  }

  /**
   * Checa se a posicao dada eh valida.
   *
   * @param posicao Posicao para validar.
   */
  private void validaPosicao(Posicao posicao) {
    if (!tabuleiro.temPeca(posicao)) {
      throw new ChessException("Essa posicao nao tem peca.");
    }
    if (jogadorAtual != ((PecaDeXadrez)tabuleiro.getPeca(posicao)).getCor()) {
      throw new ChessException("A peca escolhida nao eh sua.");
    }
    if (!tabuleiro.getPeca(posicao).existeMovimentoPossivel()) {
      throw new ChessException("Nao existe movimento possivel para esta peca.");
    }
  }

  /**
   * Checa se a posicao final do movimento eh valida.
   *
   * @param posInicial Posicao inicial.
   * @param posFinal Posicao final.
   */
  private void validaDestino(Posicao posInicial, Posicao posFinal) {
    if (!tabuleiro.getPeca(posInicial).movimentoPossivel(posFinal)) {
      throw new ChessException("A peca nao pode se mover para esta posicao.");
    }
  }

  private void proximaRodada() {
    rodada++;
    jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
  }

  /** Define a cor do oponente */
  private Cor oponente(Cor cor){
    return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
  }

  /** Localiza o Rei pela cor, dentre as pecas do tabuleiro*/
  private PecaDeXadrez rei(Cor cor){
      List<Peca> list = pecasNoTabuleiro.stream().filter(x ->((PecaDeXadrez)x).getCor() == cor).collect(Collectors.toList());
      for (Peca peca : list){
        if (peca instanceof Rei){
            return (PecaDeXadrez)peca;
        }
      }
      throw new IllegalStateException("Nao existe o Rei da cor"+ cor +" no tabuleiro.");
  }

  /**
   * Coloca uma nova peca no tabuleiro.
   *
   * @param coluna Coluna da peca.
   * @param linha Linha da peca.
   * @param peca Nova peca.
   */
  private void novaPeca(char coluna, int linha, PecaDeXadrez peca) {
    tabuleiro.colocaPeca(peca, new NotacaoXadrez(coluna, linha).paraPosicao());
    pecasNoTabuleiro.add(peca);
  }

  /** Coloca todas as pecasno tabuleiro em suas posicoes iniciais. */
  private void pecasIniciais() {
    novaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
    novaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
    novaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO));

    novaPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
    novaPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
    novaPeca('e', 8, new Rei(tabuleiro, Cor.PRETO));
  }
}
