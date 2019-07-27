package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.ExcecaoXadrez;
import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoXadrez;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partida = new PartidaDeXadrez();
		List<PecaDeXadrez>capturadas=new ArrayList<>();
		while (!partida.getCheckMate()) {
			try {
				UI.LimparTela();
				UI.ImprimePartida(partida,capturadas);
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = UI.lerPosicao(sc);
				boolean [][]possiveisMovimentos= partida.possiveisMovimentos(origem);
				UI.LimparTela();
			    UI.ImprimeTabuleiro(partida.getPecas(),possiveisMovimentos);
				
				System.out.println();
				System.out.println("Destino: ");
				PosicaoXadrez alvo = UI.lerPosicao(sc);
				
				PecaDeXadrez pecaCapturada = partida.realizarMovimento(origem, alvo); 
				if(pecaCapturada!=null) {
					capturadas.add(pecaCapturada);
				}
			}catch(ExcecaoXadrez e) {
				System.out.println(e.getMessage());
				sc.nextLine();
				
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.LimparTela();
		UI.ImprimePartida(partida, capturadas);

	}
}