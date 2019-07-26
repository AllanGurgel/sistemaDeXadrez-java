package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.ExcecaoXadrez;
import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoXadrez;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partida = new PartidaDeXadrez();
		while (true) {
			try {
				UI.LimparTela();
				UI.ImprimeTabuleiro(partida.getPecas());
				System.out.println();
				System.out.print("Origem : ");
				PosicaoXadrez origem = UI.lerPosicao(sc);
				System.out.println();
				System.out.println("Destino ");
				PosicaoXadrez alvo = UI.lerPosicao(sc);
				
				PecaDeXadrez pecaCapturada = partida.realizarMovimento(origem, alvo); 
			}catch(ExcecaoXadrez e) {
				System.out.println(e.getMessage());
				sc.nextLine();
				
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}

	}
}