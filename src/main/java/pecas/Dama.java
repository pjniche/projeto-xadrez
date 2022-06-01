package pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public class Dama extends PecaDeXadrez{

    public Dama(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return "D";
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        // above
        p.setPosicao(posicao.getLinha() - 1, posicao.getColuna());
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() - 1);
        }
        if (getTabuleiro().posicaoExiste(p) && temPecaInimiga(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // left
        p.setPosicao(posicao.getLinha(), posicao.getColuna() - 1);
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna() - 1);
        }
        if (getTabuleiro().posicaoExiste(p) && temPecaInimiga(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // right
        p.setPosicao(posicao.getLinha(), posicao.getColuna() + 1);
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna() + 1);
        }
        if (getTabuleiro().posicaoExiste(p) && temPecaInimiga(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // below
        p.setPosicao(posicao.getLinha() + 1, posicao.getColuna());
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() + 1);
        }
        if (getTabuleiro().posicaoExiste(p) && temPecaInimiga(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // nw
        p.setPosicao(posicao.getLinha() - 1, posicao.getColuna() - 1);
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
            p.setPosicao(p.getLinha() - 1, p.getColuna() - 1);
        }
        if (getTabuleiro().posicaoExiste(p) && temPecaInimiga(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // ne
        p.setPosicao(posicao.getLinha() - 1, posicao.getColuna() + 1);
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
            p.setPosicao(p.getLinha() - 1, p.getColuna() + 1);
        }
        if (getTabuleiro().posicaoExiste(p) && temPecaInimiga(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // se
        p.setPosicao(posicao.getLinha() + 1, posicao.getColuna() + 1);
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
            p.setPosicao(p.getLinha() + 1, p.getColuna() + 1);
        }
        if (getTabuleiro().posicaoExiste(p) && temPecaInimiga(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // sw
        p.setPosicao(posicao.getLinha() + 1, posicao.getColuna() - 1);
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
            p.setPosicao(p.getLinha() + 1, p.getColuna() - 1);
        }
        if (getTabuleiro().posicaoExiste(p) && temPecaInimiga(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        return mat;
    }
}
