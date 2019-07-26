package xadrez;

import Tabuleiro.Peca;
import Tabuleiro.Posicao;
import Tabuleiro.TabuleiroXadrez;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {
	private Integer turno;
	private Cor jogadorAtual;
	private TabuleiroXadrez tabuleiro;

	public PartidaDeXadrez() {
		tabuleiro = new TabuleiroXadrez(8, 8);
		turno =1;
		jogadorAtual= Cor.BRANCA;
		initialSetup();
	}
	public Integer getTurno() {
		return this.turno;
	}
	public Cor getJogadorAtual() {
		return this.jogadorAtual;
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
	public boolean[][] possiveisMovimentos(PosicaoXadrez origemPosicao){
		Posicao posicao = origemPosicao.toPosicao();
		ValidarPosicao(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
	}
	public PecaDeXadrez realizarMovimento(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino =posicaoDestino.toPosicao();
		ValidarPosicao(origem);
		validacaoPosicaoDestino(origem,destino);
		Peca pecaCapturada= MovimentoFeito(origem, destino);
		proximoTurno();
		return (PecaDeXadrez)pecaCapturada;
	}
	private Peca MovimentoFeito(Posicao origem , Posicao alvo) {
		Peca p = tabuleiro.removePeca(origem);
		Peca pecaCapturada = tabuleiro.removePeca(alvo);
		tabuleiro.colocarPeca(p, alvo);
		return pecaCapturada;
	}
	private void ValidarPosicao(Posicao posicao) {
		if(!tabuleiro.temUmaPeca(posicao)) {
			throw new ExcecaoXadrez("Não existe peça na posição de origem");
		}
		if(jogadorAtual!=((PecaDeXadrez)tabuleiro.peca(posicao)).getCor()) {
			throw new ExcecaoXadrez ("Não toque as peças do adversário ! ");
		}
		if(!tabuleiro.peca(posicao).aoMenosUmMovimentoPossivel()) {
			throw new ExcecaoXadrez ("Não existe movimento possível para esta peça.");
		}
	}
	private void validacaoPosicaoDestino(Posicao origem, Posicao destino) {
		if(!tabuleiro.peca(origem).movimentosPossiveis(destino)){
			throw new ExcecaoXadrez("A peça escolhida não pode se mover para a posição de destino");
		}
	}
	private void proximoTurno() {
		turno++;
		jogadorAtual= (jogadorAtual==Cor.BRANCA)?Cor.PRETA :Cor.BRANCA;
	}
	private void ColocarNovaPeca(char coluna, int linha ,PecaDeXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
	}

	private void initialSetup() {
		ColocarNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCA));
		ColocarNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCA));
		ColocarNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCA));
		ColocarNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCA));
		ColocarNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCA));
		ColocarNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCA));

		ColocarNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETA));
		ColocarNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETA));
		ColocarNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETA));
		ColocarNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETA));
		ColocarNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETA));
		ColocarNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETA));
	}
}
