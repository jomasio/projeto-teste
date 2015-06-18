package br.ufpe.cin.banco;

public class RenderJurosPoupancaException extends Exception {

	public RenderJurosPoupancaException () {
		super("Erro ao render juros, o numero informado nao eh de uma Poupanca");
	}
}
