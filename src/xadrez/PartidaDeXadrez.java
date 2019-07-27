package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Tabuleiro.Peca;
import Tabuleiro.Posicao;
import Tabuleiro.TabuleiroXadrez;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {
	private Integer turno;
	private Cor jogadorAtual;
	private TabuleiroXadrez tabuleiro;
	private boolean check;
	private boolean checkMate;
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	public PartidaDeXadrez() {
		tabuleiro = new TabuleiroXadrez(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCA;
		initialSetup();
	}

	public Integer getTurno() {
		return this.turno;
	}

	public Cor getJogadorAtual() {
		return this.jogadorAtual;
	}

	public boolean getCheck() {
		return this.check;
	}

	public boolean getCheckMate() {
		return this.checkMate;
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

	public boolean[][] possiveisMovimentos(PosicaoXadrez origemPosicao) {
		Posicao posicao = origemPosicao.toPosicao();
		ValidarPosicao(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
	}

	public PecaDeXadrez realizarMovimento(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		ValidarPosicao(origem);
		validacaoPosicaoDestino(origem, destino);
		Peca pecaCapturada = MovimentoFeito(origem, destino);
		if (testarCheck(jogadorAtual)) {
			desfazerMovimento(origem, destino, pecaCapturada);
			throw new ExcecaoXadrez("Você não pode se colocar em check");
		}
		check = (testarCheck(adversario(jogadorAtual))) ? true : false;
		if (testeCheckMate(adversario(jogadorAtual))) {
			checkMate = true;
		} else {
			proximoTurno();
		}
		return (PecaDeXadrez) pecaCapturada;
	}

	private Peca MovimentoFeito(Posicao origem, Posicao alvo) {
		Peca p = tabuleiro.removePeca(origem);
		Peca pecaCapturada = tabuleiro.removePeca(alvo);
		tabuleiro.colocarPeca(p, alvo);
		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		return pecaCapturada;
	}

	private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
		Peca p = tabuleiro.removePeca(destino);
		tabuleiro.colocarPeca(p, origem);
		if (pecaCapturada != null) {
			tabuleiro.colocarPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);

		}
	}

	private void ValidarPosicao(Posicao posicao) {
		if (!tabuleiro.temUmaPeca(posicao)) {
			throw new ExcecaoXadrez("Não existe peça na posição de origem");
		}
		if (jogadorAtual != ((PecaDeXadrez) tabuleiro.peca(posicao)).getCor()) {
			throw new ExcecaoXadrez("Não toque as peças do adversário ! ");
		}
		if (!tabuleiro.peca(posicao).aoMenosUmMovimentoPossivel()) {
			throw new ExcecaoXadrez("Não existe movimento possível para esta peça.");
		}
	}

	private void validacaoPosicaoDestino(Posicao origem, Posicao destino) {
		if (!tabuleiro.peca(origem).movimentosPossiveis(destino)) {
			throw new ExcecaoXadrez("A peça escolhida não pode se mover para a posição de destino");
		}
	}

	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
	}

	private Cor adversario(Cor cor) {
		return (cor == cor.BRANCA) ? cor.PRETA : cor.BRANCA;
	}

	private PecaDeXadrez Rei(Cor cor) {
		List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca p : lista) {
			if (p instanceof Rei) {
				return (PecaDeXadrez) p;
			}
		}
		throw new IllegalStateException("Não existe rei " + cor + " no tabuleiro");
	}

	private boolean testarCheck(Cor cor) {
		Posicao posicaoRei = Rei(cor).getPosicaoXadrez().toPosicao();
		List<Peca> pecasAdversario = pecasNoTabuleiro.stream()
				.filter(x -> ((PecaDeXadrez) x).getCor() == adversario(cor)).collect(Collectors.toList());
		for (Peca p : pecasAdversario) {
			boolean[][] mat = p.movimentosPossiveis();
			if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}

	private boolean testeCheckMate(Cor cor) {
		if (!testarCheck(cor)) {
			return false;
		}
		List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca p : lista) {
			boolean[][] mat = p.movimentosPossiveis();
			for (int ri = 0; ri < tabuleiro.getLinhas(); ri++) {
				for (int x = 0; x < tabuleiro.getColunas(); x++) {
					if (mat[ri][x]) {
						Posicao origem = ((PecaDeXadrez) p).getPosicaoXadrez().toPosicao();
						Posicao destino = new Posicao(ri, x);
						Peca pecaCapturada = MovimentoFeito(origem, destino);
						boolean testarCheck = testarCheck(cor);
						desfazerMovimento(origem, destino, pecaCapturada);
						if (!testarCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private void ColocarNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(peca);
	}

	private void initialSetup() {
		ColocarNovaPeca('h', 7, new Torre(tabuleiro, Cor.BRANCA));
		ColocarNovaPeca('d', 1, new Torre(tabuleiro, Cor.BRANCA));
		ColocarNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCA));
		/*
		 * ColocarNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCA));
		 * ColocarNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCA));
		 * ColocarNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCA));
		 * 
		 * ColocarNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETA));
		 * ColocarNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETA));
		 * ColocarNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETA));
		 * ColocarNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETA));
		 */
		ColocarNovaPeca('b', 8, new Torre(tabuleiro, Cor.PRETA));
		ColocarNovaPeca('a', 8, new Rei(tabuleiro, Cor.PRETA));
	}
}
