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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TesteDeIa {

    private BigDecimal melhorValor = BigDecimal.valueOf(0);
    private NotacaoXadrez melhorInicio, melhorFim;
    private Partida partida = new Partida();
    PecaDeXadrez[][] pieces = partida.getPecas();
    List<PecaDeXadrez> capturadas = new ArrayList<>();

    @Test
    public void test(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(pieces[i][j]!=null && pieces[i][j].getCor()==Cor.PRETO) {
                    try {
                        NotacaoXadrez pretoInicio = new NotacaoXadrez((char) ('a' + j), 8 - i);
                        boolean[][] movimentosPreto = partida.movimentosPossiveis(pretoInicio);
                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {
                                if (movimentosPreto[k][l]) {
                                    NotacaoXadrez pretoFim = new NotacaoXadrez((char) ('a' + l), 8 - k);
                                    PecaDeXadrez pretoCaptura = moveUtil("" + "st MOVE", pretoInicio, pretoFim, partida, capturadas);

                                    // Prevendo Brancas
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
                                                                BigDecimal valorAtual = checkValue(partida);
                                                                if(valorAtual.compareTo(melhorValor)<0){
                                                                    melhorValor=valorAtual;
                                                                    melhorInicio=pretoInicio;
                                                                    melhorFim=pretoFim;
                                                                }
                                                                undoUtil(brancoInicio,brancoFim,partida,capturadas,brancoCaptura);
                                                            }
                                                        }
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println(e.getMessage());
                                                }
                                            }
                                        }
                                    }

                                    undoUtil(pretoInicio, pretoFim, partida, capturadas, pretoCaptura);
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }

        assertTrue(checkBoard(partida));
        System.out.println("Valor: "+melhorValor+"\nMovimento: "+melhorInicio+" - "+melhorFim);
    }

    public BigDecimal Min(int profundidade){

        BigDecimal valorAtual = checkValue(partida);

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

                                    valorAtual.add(Max(profundidade-1));

                                    if(valorAtual.compareTo(melhorValor)<0){
                                        melhorValor=valorAtual;
                                        melhorInicio=pretoInicio;
                                        melhorFim=pretoFim;
                                    }

                                    undoUtil(pretoInicio, pretoFim, partida, capturadas, pretoCaptura);
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        return valorAtual;
    }

    public BigDecimal Max(int profundidade){
        BigDecimal valorAtual = checkValue(partida);

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

                                    valorAtual.subtract(Min(profundidade-1));

                                    undoUtil(brancoInicio,brancoFim,partida,capturadas,brancoCaptura);
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        return valorAtual;
    }



    private PecaDeXadrez moveUtil(String message, NotacaoXadrez source, NotacaoXadrez target, Partida match, List<PecaDeXadrez> captured){
        PecaDeXadrez capturedPiece = match.movimentoDeXadrez(source, target);
        if (capturedPiece != null) {
            captured.add(capturedPiece);
        }
        printBoard(message,match,captured);

        return capturedPiece;
    }

    private void undoUtil(NotacaoXadrez source, NotacaoXadrez target, Partida match,List<PecaDeXadrez> captured, PecaDeXadrez capturedPiece){
        match.desfazerMovimento(source.paraPosicao(),target.paraPosicao(),capturedPiece);
        //printBoard("UNDO",match,captured);
    }

    private void printBoard(String message, Partida match, List<PecaDeXadrez> captured){
        System.out.println(message+" | Value: "+checkValue(match));
//         InterfaceTerminal.limpaTela();
//         InterfaceTerminal.mostraPartida(match, captured);
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
