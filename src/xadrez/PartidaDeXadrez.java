package xadrez;

import Tabuleiro.Posicao;
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

	private void initialSetup() {
		tabuleiro.colocarPeca(new Torre(tabuleiro, Cor.BRANCA), new Posicao(2, 1));
		tabuleiro.colocarPeca(new Rei(tabuleiro, Cor.PRETA), new Posicao(0, 4));
		tabuleiro.colocarPeca(new Rei(tabuleiro, Cor.BRANCA), new Posicao(7, 4));
	}
}
