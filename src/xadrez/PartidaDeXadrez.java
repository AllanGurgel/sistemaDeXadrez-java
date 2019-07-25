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
		ColocarNovaPeca('b', 6, new Torre(tabuleiro, Cor.BRANCA));
		ColocarNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETA));
		ColocarNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCA));
	}
}
