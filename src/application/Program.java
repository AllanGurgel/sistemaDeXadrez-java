package application;

import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoXadrez;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partida = new PartidaDeXadrez();
		while (true) {
			UI.ImprimeTabuleiro(partida.getPecas());
			System.out.println();
			System.out.print("Origem : ");
			PosicaoXadrez origem = UI.lerPosicao(sc);
			System.out.println();
			System.out.println("Destino ");
			PosicaoXadrez alvo = UI.lerPosicao(sc);
			
			PecaDeXadrez pecaCapturada = partida.realizarMovimento(origem, alvo); 
		}

	}
}