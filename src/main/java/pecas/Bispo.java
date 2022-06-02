package pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

/** Peca Bispo de xadrez. */
public class Bispo extends PecaDeXadrez {

    /**
     * Construtor customizado.
     *
     * @param tabuleiro Tabuleiro de xadrez.
     * @param cor Cor da peca.
     */
    public Bispo(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao novaPosicao = new Posicao(0, 0);

        // Diagonal superior esquerda
        novaPosicao.setPosicao(this.getPosicao().getLinha() - 1, this.getPosicao().getColuna() -1);
        while (getTabuleiro().posicaoExiste(novaPosicao) && !getTabuleiro().temPeca(novaPosicao)) {
            matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
            novaPosicao.setPosicao(novaPosicao.getLinha() -1, novaPosicao.getColuna() -1);
        }
        if (getTabuleiro().posicaoExiste(novaPosicao) && temPecaInimiga(novaPosicao)) {
            matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
        }

        // Diagonal superior direita
        novaPosicao.setPosicao(this.getPosicao().getLinha() - 1, this.getPosicao().getColuna() + 1);
        while (getTabuleiro().posicaoExiste(novaPosicao) && !getTabuleiro().temPeca(novaPosicao)) {
            matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
            novaPosicao.setPosicao(novaPosicao.getLinha() - 1, novaPosicao.getColuna() + 1);
        }
        if (getTabuleiro().posicaoExiste(novaPosicao) && temPecaInimiga(novaPosicao)) {
            matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
        }

        // Diagonal inferior direita
        novaPosicao.setPosicao(this.getPosicao().getLinha() + 1, this.getPosicao().getColuna() + 1);
        while (getTabuleiro().posicaoExiste(novaPosicao) && !getTabuleiro().temPeca(novaPosicao)) {
            matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
            novaPosicao.setPosicao(novaPosicao.getLinha() + 1, novaPosicao.getColuna() + 1);
        }
        if (getTabuleiro().posicaoExiste(novaPosicao) && temPecaInimiga(novaPosicao)) {
            matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
        }

        // Diagonal inferior esquerda
        novaPosicao.setPosicao(this.getPosicao().getLinha() + 1, this.getPosicao().getColuna() - 1);
        while (getTabuleiro().posicaoExiste(novaPosicao) && !getTabuleiro().temPeca(novaPosicao)) {
            matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
            novaPosicao.setPosicao(novaPosicao.getLinha() + 1, novaPosicao.getColuna() - 1);
        }
        if (getTabuleiro().posicaoExiste(novaPosicao) && temPecaInimiga(novaPosicao)) {
            matriz[novaPosicao.getLinha()][novaPosicao.getColuna()] = true;
        }

        return matriz;
    }
}