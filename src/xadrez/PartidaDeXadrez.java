package xadrez;

import Tabuleiro.TabuleiroXadrez;

public class PartidaDeXadrez {
private TabuleiroXadrez tabuleiro;
public PartidaDeXadrez() {
	tabuleiro = new TabuleiroXadrez(8,8);
}
public PecaDeXadrez[][]getPecas(){
	PecaDeXadrez[][]mat = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
	for (int ri =0; ri <tabuleiro.getLinhas();ri++) {
		for(int z =0;z<tabuleiro.getColunas();z++ ) {
			mat[ri][z]=(PecaDeXadrez)tabuleiro.peca(ri,z);
		}
		
	}
	return mat;
}
}
