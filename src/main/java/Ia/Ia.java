package Ia;

import pecas.Cor;
import pecas.PecaDeXadrez;
import tabuleiro.NotacaoXadrez;
import xadrez.Partida;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Ia {

    Partida partida;
    List<Integer> possibilidades = new ArrayList<>();

    public Ia(Partida partida){
        this.partida=partida;
    }

    public NotacaoXadrez getInicio(){
        PecaDeXadrez[][] pecas = partida.getPecas();
        PecaDeXadrez peca = null;
        int linhaAleatoria;
        int colunaAleatoria;
        while(true){
            linhaAleatoria = ThreadLocalRandom.current().nextInt(0, 7 + 1);
            colunaAleatoria = ThreadLocalRandom.current().nextInt(0, 7 + 1);
            peca = pecas[linhaAleatoria][colunaAleatoria];
            if (peca!=null && peca.getCor()==Cor.PRETO) {
                boolean[][] movimentos = peca.movimentosPossiveis();
                possibilidades = new ArrayList<>();
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (movimentos[i][j] == true) {
                            possibilidades.add(i);
                            possibilidades.add(j);
                        }
                    }
                }
                if (!possibilidades.isEmpty()) {
                    break;
                }
            }
        }
        return peca.getNotacaoXadrez();
    }

    public NotacaoXadrez getFim(){
        int numeroAleatorio = ThreadLocalRandom.current().nextInt(0, possibilidades.size());
        int linha;
        int coluna;
        if(numeroAleatorio % 2==0) {
            linha = possibilidades.get(numeroAleatorio);
            coluna = possibilidades.get(numeroAleatorio + 1);
        }else{
            linha = possibilidades.get(numeroAleatorio-1);
            coluna = possibilidades.get(numeroAleatorio);
        }
        possibilidades=new ArrayList<>();
        return new NotacaoXadrez((char) ('a' + coluna),8-linha);
    }

}
