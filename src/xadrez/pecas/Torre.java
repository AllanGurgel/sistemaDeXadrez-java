package xadrez.pecas;

import Tabuleiro.Posicao;
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
	
	Posicao p = new Posicao(0, 0);
	//acima
	
	p.setValues(posicao.getLinha()-1, posicao.getColuna());
	while(getTabuleiro().posicaoExistente(p)&&!getTabuleiro().temUmaPeca(p)) {
		mat[p.getLinha()][p.getColuna()]=true;
		p.setLinha(p.getLinha()-1);
	}
	if(getTabuleiro().posicaoExistente(p)&& existePecaAdversaria(p)) {
		mat[p.getLinha()][p.getColuna()]=true;
	}
	//esquerda
	
		p.setValues(posicao.getLinha(), posicao.getColuna()-1);
		while(getTabuleiro().posicaoExistente(p)&&!getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
			p.setColuna(p.getColuna()-1);
		}
		if(getTabuleiro().posicaoExistente(p)&& existePecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
		}
		//direita
		
			p.setValues(posicao.getLinha(), posicao.getColuna()+1);
			while(getTabuleiro().posicaoExistente(p)&&!getTabuleiro().temUmaPeca(p)) {
				mat[p.getLinha()][p.getColuna()]=true;
				p.setColuna(p.getColuna()+1);
			}
			if(getTabuleiro().posicaoExistente(p)&& existePecaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()]=true;
			}
			//abaixo
			
			p.setValues(posicao.getLinha()+1, posicao.getColuna());
			while(getTabuleiro().posicaoExistente(p)&&!getTabuleiro().temUmaPeca(p)) {
				mat[p.getLinha()][p.getColuna()]=true;
				p.setLinha(p.getLinha()+1);
			}
			if(getTabuleiro().posicaoExistente(p)&& existePecaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()]=true;
			}
	return mat;
	}
}
