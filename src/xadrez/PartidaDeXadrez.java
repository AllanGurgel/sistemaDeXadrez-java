package xadrez;

import Tabuleiro.TabuleiroXadrez;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {
	private TabuleiroXadrez tabuleiro;

	public PartidaDeXadrez() {
		tabuleiro = new TabuleiroXadrez(8, 8);
		initialSetup();
	}

	public PecaDeXadrez[][] getPecas() {
		PecaDeXadrez[][] mat = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int ri = 0; ri < tabuleiro.getLinhas(); ri++) {
			for (int z = 0; z < tabuleiro.getColunas(); z++) {
				mat[ri][z] = (PecaDeXadrez) tabuleiro.peca(ri, z);
			}

		}
		return mat;
	}
	private void ColocarNovaPeca(char coluna, int linha ,PecaDeXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
	}

	private void initialSetup() {
		ColocarNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCA));
		ColocarNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCA));
		ColocarNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCA));
		ColocarNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCA));
		ColocarNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCA));
		ColocarNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCA));

		ColocarNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETA));
		ColocarNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETA));
		ColocarNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETA));
		ColocarNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETA));
		ColocarNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETA));
		ColocarNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETA));
	}
}
