package xadrez;

import Tabuleiro.Peca;
import Tabuleiro.TabuleiroXadrez;

public class PecaDeXadrez extends Peca{
	private Cor cor;

	public PecaDeXadrez(TabuleiroXadrez tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	

}
