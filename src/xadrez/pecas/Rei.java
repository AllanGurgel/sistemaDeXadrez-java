package xadrez.pecas;

import Tabuleiro.TabuleiroXadrez;
import xadrez.Cor;
import xadrez.PecaDeXadrez;

public class Rei extends PecaDeXadrez{

	public Rei(TabuleiroXadrez tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		
	}
@Override public String toString(){
	return "R";
}
@Override
public boolean[][] movimentosPossiveis() {
	boolean[][]mat = new boolean [getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
	return mat;
}
}
