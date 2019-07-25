package Tabuleiro;

public class Peca {
protected Posicao posicao;
private TabuleiroXadrez tabuleiro;
public Peca(TabuleiroXadrez tabuleiro) {
	this.tabuleiro = tabuleiro;
}
protected TabuleiroXadrez getTabuleiro() {
	return tabuleiro;
}
 
}
