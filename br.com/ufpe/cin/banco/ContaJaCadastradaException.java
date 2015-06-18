package br.ufpe.cin.banco;

public class ContaJaCadastradaException extends Exception {
	
	public ContaJaCadastradaException() {
		super("Ja existe uma conta cadastrada com este numero");
	}
}
