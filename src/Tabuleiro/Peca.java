package Tabuleiro;

public abstract class Peca {
	protected Posicao posicao;
	private TabuleiroXadrez tabuleiro;

	public Peca(TabuleiroXadrez tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	protected TabuleiroXadrez getTabuleiro() {
		return tabuleiro;
	}

	public abstract boolean[][] movimentosPossiveis();

	public boolean movimentosPossiveis(Posicao posicao) {
		return movimentosPossiveis()[posicao.getLinha()][posicao.getColuna()];
	}

	public boolean aoMenosUmMovimentoPossivel() {
		boolean[][] mat = movimentosPossiveis();
		for (int ri = 0; ri < mat.length; ri++) {
			for (int x = 0; x < mat.length; x++) {
				if (mat[ri][x]) {
					return true;
				}
			}
		}
		return false;
	}
}
