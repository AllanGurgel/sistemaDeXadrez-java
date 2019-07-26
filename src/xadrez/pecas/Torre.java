package xadrez.pecas;

import Tabuleiro.TabuleiroXadrez;
import xadrez.Cor;
import xadrez.PecaDeXadrez;

public class Torre extends PecaDeXadrez{

	public Torre(TabuleiroXadrez tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
@Override
public String toString() {
	return "T";   
}
@Override
public boolean[][] movimentosPossiveis() {
	boolean[][]mat = new boolean [getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
	return mat;
	}
}
