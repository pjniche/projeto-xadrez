package xadrez;

import interfacevisual.InterfaceTerminal;
import pecas.Cor;
import pecas.PecaDeXadrez;
import tabuleiro.NotacaoXadrez;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ToDo: Adicionar pontuacao para xeque/mate
 */
public class TesteDeIa {

    private BigDecimal melhorValor = BigDecimal.valueOf(0);
    private NotacaoXadrez melhorInicio, melhorFim;
    private Partida partida = new Partida();
    PecaDeXadrez[][] pieces = partida.getPecas();
    List<PecaDeXadrez> capturadas = new ArrayList<>();
    private boolean first = true;

    @Test
    public void test(){

        partida.movimentoDeXadrez(new NotacaoXadrez('f', 2), new NotacaoXadrez('f', 3));
        partida.movimentoDeXadrez(new NotacaoXadrez('e', 7), new NotacaoXadrez('e', 5));
        partida.movimentoDeXadrez(new NotacaoXadrez('g', 2), new NotacaoXadrez('g', 4));

        InterfaceTerminal.limpaTela();
        InterfaceTerminal.mostraPartida(partida, capturadas);

        Min(2);

        System.out.println("Valor: "+melhorValor+"\nMovimento: "+melhorInicio+" - "+melhorFim);
    }

    public BigDecimal Min(int profundidade){
        BigDecimal menor = BigDecimal.valueOf(0);
        BigDecimal valorAtual = BigDecimal.valueOf(0);
        boolean imFirst = false;

        if(first){
            imFirst = true;
            first=false;
        }

        if(profundidade>=0) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (pieces[i][j] != null && pieces[i][j].getCor() == Cor.PRETO) {
                        try {
                            NotacaoXadrez pretoInicio = new NotacaoXadrez((char) ('a' + j), 8 - i);
                            boolean[][] movimentosPreto = partida.movimentosPossiveis(pretoInicio);
                            for (int k = 0; k < 8; k++) {
                                for (int l = 0; l < 8; l++) {
                                    if (movimentosPreto[k][l]) {
                                        NotacaoXadrez pretoFim = new NotacaoXadrez((char) ('a' + l), 8 - k);
                                        PecaDeXadrez pretoCaptura = moveUtil("" + "st MOVE", pretoInicio, pretoFim, partida, capturadas);
                                        valorAtual = checkValue(partida);

                                        valorAtual.add(Max(profundidade - 1));

                                        if(valorAtual.compareTo(menor)<0){
                                            menor=valorAtual;
                                            if (imFirst){
                                                melhorValor = valorAtual;
                                                melhorInicio = pretoInicio;
                                                melhorFim = pretoFim;
                                            }
                                        }

                                        undoUtil(pretoInicio, pretoFim, partida, capturadas, pretoCaptura);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            // do nothing
                        }
                    }
                }
            }
        }
        return menor;
    }

    public BigDecimal Max(int profundidade){
        BigDecimal maior = BigDecimal.valueOf(0);
        BigDecimal valorAtual = checkValue(partida);

        if(profundidade>=0) {
            for (int m = 0; m < 8; m++) {
                for (int n = 0; n < 8; n++) {
                    if (pieces[m][n] != null && pieces[m][n].getCor() == Cor.BRANCO) {
                        try {
                            NotacaoXadrez brancoInicio = new NotacaoXadrez((char) ('a' + n), 8 - m);
                            boolean[][] movimemntosBranco = partida.movimentosPossiveis(brancoInicio);
                            for (int o = 0; o < 8; o++) {
                                for (int p = 0; p < 8; p++) {
                                    if (movimemntosBranco[o][p]) {
                                        NotacaoXadrez brancoFim = new NotacaoXadrez((char) ('a' + p), 8 - o);
                                        PecaDeXadrez brancoCaptura = moveUtil("" + "st MOVE", brancoInicio, brancoFim, partida, capturadas);
                                        valorAtual = checkValue(partida);

                                        valorAtual.subtract(Min(profundidade - 1));

                                        if(valorAtual.compareTo(maior)>0){
                                            maior=valorAtual;
                                        }

                                        undoUtil(brancoInicio, brancoFim, partida, capturadas, brancoCaptura);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            // do nothing
                        }
                    }
                }
            }
        }
        return maior;
    }



    private PecaDeXadrez moveUtil(String message, NotacaoXadrez source, NotacaoXadrez target, Partida match, List<PecaDeXadrez> captured){
        PecaDeXadrez capturedPiece = match.movimentoDeXadrez(source, target);
        if (capturedPiece != null) {
            captured.add(capturedPiece);
        }
        // printBoard(message,match,captured);

        return capturedPiece;
    }

    private void undoUtil(NotacaoXadrez source, NotacaoXadrez target, Partida match,List<PecaDeXadrez> captured, PecaDeXadrez capturedPiece){
        match.desfazerMovimento(source.paraPosicao(),target.paraPosicao(),capturedPiece);
        //printBoard("UNDO",match,captured);
    }

    private void printBoard(String message, Partida match, List<PecaDeXadrez> captured){
        System.out.println(message+" | Value: "+checkValue(match));
         InterfaceTerminal.limpaTela();
         InterfaceTerminal.mostraPartida(match, captured);
    }

    private boolean checkBoard(Partida match){
        PecaDeXadrez[][] brandNewMatch = new Partida().getPecas();
        PecaDeXadrez[][] actualMatch = match.getPecas();
        int counter=0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (actualMatch[i][j]== null && brandNewMatch[i][j]==null){
                    counter ++;
                } else if (actualMatch[i][j].getCor()==brandNewMatch[i][j].getCor()
                        && actualMatch[i][j].getClass()==brandNewMatch[i][j].getClass()){
                    counter++;
                } else {
                    return false;
                }
            }
        }
        return counter==64;
    }

    private BigDecimal checkValue(Partida match){
        BigDecimal totalValue = BigDecimal.valueOf(0);
        PecaDeXadrez[][] pieces = match.getPecas();
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces.length; j++) {
                PecaDeXadrez piece = pieces[i][j];
                if (piece!=null){
                    if(piece.getCor()== Cor.BRANCO){
                        totalValue = totalValue.add(piece.getValor());
                    } else {
                        totalValue = totalValue.subtract(piece.getValor());
                    }
                }
            }
        }
        return totalValue;
    }

    @Test
    public void temporary(){
        checkBoard(new Partida());
    }


    @Test
    public void myTest3(){
        assertEquals(0,-10.127000000000045 + 10.127);
    }

}
