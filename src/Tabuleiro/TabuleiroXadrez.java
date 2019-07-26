package Tabuleiro;

public class TabuleiroXadrez {
private Integer linhas;
private Integer colunas;
private Peca[][] pecas;
public TabuleiroXadrez(Integer linhas, Integer colunas) {
	if (linhas <1 || colunas<1) {
		throw new ExcecaoTabuleiro("Erro ao criar tabuleiro ! Deve existir linhas e colunas");
	}
	this.linhas = linhas;
	this.colunas = colunas;
	pecas = new Peca[linhas][colunas];
}
public Integer getLinhas() {
	return linhas;
}
public Integer getColunas() {
	return colunas;
}
 public Peca peca(int linha, int coluna) {
	 if (!posicaoExistente(linha, coluna)) {
		 throw new ExcecaoTabuleiro("posição inexistente no tabuleiro.");
	 }
	 return pecas[linha][coluna];
 }
 public Peca peca(Posicao posicao) {
	 if (!posicaoExistente(posicao)) {
		 throw new ExcecaoTabuleiro("posição inexistente no tabuleiro.");
	 }
	 return pecas[posicao.getLinha()][posicao.getColuna()];
 }
 public void colocarPeca(Peca peca, Posicao posicao) {
	 if(temUmaPeca(posicao)) {
		 throw new ExcecaoTabuleiro("Existe uma peça ocupando essa posição ");
	 }
	 pecas[posicao.getLinha()][posicao.getColuna()]=peca;
	 peca.posicao=posicao;
 }
 public Peca removePeca(Posicao posicao) {
	 if(!posicaoExistente(posicao)) {
		 throw new ExcecaoTabuleiro("Posição inexistente");
	 }
	 if (peca(posicao)==null) {
		 return null;
	 }
	 Peca aux = peca(posicao);
	 aux.posicao=null;
	 pecas[posicao.getLinha()][posicao.getColuna()]=null;
	 return aux;
 }
 private boolean posicaoExistente(int linha, int coluna) {
	 return linha >=0 && linha< linhas && coluna>=0&& coluna<colunas;
 }
 public boolean posicaoExistente(Posicao posicao) {
	 return posicaoExistente(posicao.getLinha(), posicao.getColuna());
 }
 public boolean temUmaPeca(Posicao posicao) {
	 if (!posicaoExistente(posicao)) {
		 throw new ExcecaoTabuleiro("posição inexistente no tabuleiro.");
	 }
	 return peca(posicao)!=null;
 }
}
