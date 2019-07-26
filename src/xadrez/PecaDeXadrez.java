package xadrez;

import Tabuleiro.Peca;
import Tabuleiro.Posicao;
import Tabuleiro.TabuleiroXadrez;

public abstract class PecaDeXadrez extends Peca{
	private Cor cor;

	public PecaDeXadrez(TabuleiroXadrez tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	protected boolean existePecaAdversaria(Posicao posicao) {
		PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().peca(posicao);
		return p!= null && p.getCor()!= cor;
	}
	

}
