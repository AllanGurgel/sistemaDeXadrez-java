package application;

import xadrez.PartidaDeXadrez;

public class Program {

	public static void main(String[] args) {
PartidaDeXadrez partida = new PartidaDeXadrez();
UI.ImprimeTabuleiro(partida.getPecas());
	}

}
