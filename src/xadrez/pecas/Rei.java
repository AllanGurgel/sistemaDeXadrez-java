package xadrez.pecas;

import Tabuleiro.Posicao;
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
private boolean reiPodeMover(Posicao posicao) {
	PecaDeXadrez p =(PecaDeXadrez)getTabuleiro().peca(posicao);
	return p==null || p.getCor()!=getCor();
}
@Override
public boolean[][] movimentosPossiveis() {
	boolean[][]mat = new boolean [getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
	
	Posicao p = new Posicao(0,0);
	//Acima
	p.setValues(posicao.getLinha()-1,posicao.getColuna());
	if(getTabuleiro().posicaoExistente(p)&&reiPodeMover(p)) {
		mat[p.getLinha()][p.getColuna()]=true;
	}
	//Abaixo
		p.setValues(posicao.getLinha()+1,posicao.getColuna());
		if(getTabuleiro().posicaoExistente(p)&&reiPodeMover(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
		}
		//Esquerda
		p.setValues(posicao.getLinha(),posicao.getColuna()-1);
		if(getTabuleiro().posicaoExistente(p)&&reiPodeMover(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
		}
		p.setValues(posicao.getLinha(),posicao.getColuna()+1);
		if(getTabuleiro().posicaoExistente(p)&&reiPodeMover(p)) {
			mat[p.getLinha()][p.getColuna()]=true;
		}
		//Noroeste
				p.setValues(posicao.getLinha()-1,posicao.getColuna()-1);
				if(getTabuleiro().posicaoExistente(p)&&reiPodeMover(p)) {
					mat[p.getLinha()][p.getColuna()]=true;
				}
				//Nordeste
				p.setValues(posicao.getLinha()-1,posicao.getColuna()+1);
				if(getTabuleiro().posicaoExistente(p)&&reiPodeMover(p)) {
					mat[p.getLinha()][p.getColuna()]=true;
				}
				//Sudoeste
				p.setValues(posicao.getLinha()+1,posicao.getColuna()-1);
				if(getTabuleiro().posicaoExistente(p)&&reiPodeMover(p)) {
					mat[p.getLinha()][p.getColuna()]=true;
				}
				//Sudeste
				p.setValues(posicao.getLinha()+1,posicao.getColuna()+1);
				if(getTabuleiro().posicaoExistente(p)&&reiPodeMover(p)) {
					mat[p.getLinha()][p.getColuna()]=true;
				}
	return mat;
}
}
