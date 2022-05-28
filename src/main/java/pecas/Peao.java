package pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public class Peao extends PecaDeXadrez{

    public Peao(Tabuleiro board, Cor color) {
        super(board, color);
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
        }
        else {
            p.setPosicao(this.posicao.getLinha() + 1, this.posicao.getColuna());
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.setPosicao(this.posicao.getLinha() + 2, this.posicao.getColuna());
            Posicao p2 = new Posicao(this.posicao.getLinha() - 1, this.posicao.getColuna());
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
        }
        return mat;
    }

    @Override
    public String toString() {
        return "P";
    }

}
