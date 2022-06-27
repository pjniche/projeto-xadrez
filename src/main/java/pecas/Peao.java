package pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Partida;

public class Peao extends PecaDeXadrez{

    private Partida partida;

    public Peao(Tabuleiro board, Cor color, Partida partida) {
        super(board, color);
        this.partida = partida;
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        if (getCor() == Cor.BRANCO) {
            p.setPosicao(this.posicao.getLinha() - 1, this.posicao.getColuna());
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.setPosicao(this.posicao.getLinha() - 2, this.posicao.getColuna());
            Posicao p2 = new Posicao(this.posicao.getLinha() - 1, this.posicao.getColuna());
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().temPeca(p2) && getContagemDeMovimento() == 0) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.setPosicao(this.posicao.getLinha() - 1, this.posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExiste(p) && temPecaInimiga(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.setPosicao(this.posicao.getLinha() - 1, this.posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExiste(p) && temPecaInimiga(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            // En passant em peças BRANCAS
            if (posicao.getLinha() == 3) {
                Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                if (getTabuleiro().posicaoExiste(esquerda) && temPecaInimiga(esquerda) && getTabuleiro().getPeca(esquerda) == partida.getEnPassant()) {
                    mat[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
                }
                Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                if (getTabuleiro().posicaoExiste(direita) && temPecaInimiga(direita) && getTabuleiro().getPeca(direita) == partida.getEnPassant()) {
                    mat[direita.getLinha() - 1][direita.getColuna()] = true;
                }
            }
        }
        else {
            p.setPosicao(this.posicao.getLinha() + 1, this.posicao.getColuna());
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.setPosicao(this.posicao.getLinha() + 2, this.posicao.getColuna());
            Posicao p2 = new Posicao(this.posicao.getLinha() + 1, this.posicao.getColuna());
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().temPeca(p2) && getContagemDeMovimento() == 0) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.setPosicao(this.posicao.getLinha() + 1, this.posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExiste(p) && temPecaInimiga(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.setPosicao(this.posicao.getLinha() + 1, this.posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExiste(p) && temPecaInimiga(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            // En passant em peças PRETAS
            if (posicao.getLinha() == 4) {
                Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                if (getTabuleiro().posicaoExiste(esquerda) && temPecaInimiga(esquerda) && getTabuleiro().getPeca(esquerda) == partida.getEnPassant()) {
                    mat[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
                }
                Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                if (getTabuleiro().posicaoExiste(direita) && temPecaInimiga(direita) && getTabuleiro().getPeca(direita) == partida.getEnPassant()) {
                    mat[direita.getLinha() + 1][direita.getColuna()] = true;
                }
            }
        }
        return mat;
    }

    @Override
    public String toString() {
        return "P";
    }

}
