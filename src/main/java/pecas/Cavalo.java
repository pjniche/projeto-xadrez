package pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

import java.math.BigDecimal;

/** Peca Cavalo de xadrez. */
public class Cavalo extends PecaDeXadrez {

    /**
     * Construtor customizado.
     *
     * @param tabuleiro Tabuleiro de xadrez.
     * @param cor Cor da peca.
     */
    public Cavalo(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor, BigDecimal.valueOf(6.198));
    }

    @Override
    public String toString() {
        return "C";
    }

    private boolean podeMover(Posicao posicao) {
        PecaDeXadrez peca = (PecaDeXadrez)getTabuleiro().getPeca(posicao);
        return peca == null || peca.getCor() != getCor();
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao novaPosicao = new Posicao(0, 0);

        novaPosicao.setPosicao(this.getPosicao().getLinha() - 1, this.getPosicao().getColuna() - 2);
        if (getTabuleiro().posicaoExiste(novaPosicao) && podeMover(novaPosicao)) {
            matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
        }

        novaPosicao.setPosicao(this.getPosicao().getLinha() - 2, this.getPosicao().getColuna() - 1);
        if (getTabuleiro().posicaoExiste(novaPosicao) && podeMover(novaPosicao)) {
            matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
        }

        novaPosicao.setPosicao(this.getPosicao().getLinha() - 2, this.getPosicao().getColuna() + 1);
        if (getTabuleiro().posicaoExiste(novaPosicao) && podeMover(novaPosicao)) {
            matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
        }

        novaPosicao.setPosicao(this.getPosicao().getLinha() -1 , this.getPosicao().getColuna() + 2);
        if (getTabuleiro().posicaoExiste(novaPosicao) && podeMover(novaPosicao)) {
            matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
        }

        novaPosicao.setPosicao(this.getPosicao().getLinha() + 1, this.getPosicao().getColuna() + 2);
        if (getTabuleiro().posicaoExiste(novaPosicao) && podeMover(novaPosicao)) {
            matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
        }

        novaPosicao.setPosicao(this.getPosicao().getLinha() + 2, this.getPosicao().getColuna() + 1);
        if (getTabuleiro().posicaoExiste(novaPosicao) && podeMover(novaPosicao)) {
            matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
        }

        novaPosicao.setPosicao(this.getPosicao().getLinha() + 2, this.getPosicao().getColuna() - 1);
        if (getTabuleiro().posicaoExiste(novaPosicao) && podeMover(novaPosicao)) {
            matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
        }

        novaPosicao.setPosicao(this.getPosicao().getLinha() + 1, this.getPosicao().getColuna() - 2);
        if (getTabuleiro().posicaoExiste(novaPosicao) && podeMover(novaPosicao)) {
            matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
        }

        return matriz;
    }
}
