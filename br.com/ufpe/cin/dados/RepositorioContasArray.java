package br.ufpe.cin.dados;

import br.ufpe.cin.banco.ContaAbstrata;

/**
 * Modifique a classe RepositorioContasArray para lancar a excecao 
 * ContaNaoEncontradaException ao inves de RuntimeException quando 
 * a conta nao for encontrada. Faça os ajustes necessarios nas demais 
 * classes e interface da aplicacao.
 * 
 */
public class RepositorioContasArray implements RepositorioContas {

	private ContaAbstrata[] contas;
	private int indice;

	public RepositorioContasArray(int tamanho) {
		contas = new ContaAbstrata[tamanho];
		indice = 0;
	}

	public void inserir(ContaAbstrata conta) {
		contas[indice] = conta;
		indice = indice + 1;
	}

	public ContaAbstrata procurar(String numero) throws ContaNaoEncontradaException {
		ContaAbstrata resposta = null;
		int i = this.getIndice(numero);
		if (i == this.indice) {
			throw new ContaNaoEncontradaException();
		} else {
			resposta = this.contas[i];
		}
		return resposta;
	}

	public void remover(String numero) throws ContaNaoEncontradaException {
		int i = this.getIndice(numero);
		if (i == this.indice) {
			throw new ContaNaoEncontradaException();
		} else {
			this.indice = this.indice - 1;
			this.contas[i] = this.contas[this.indice];
			this.contas[this.indice] = null;
		}
	}

	public void atualizar(ContaAbstrata conta) throws ContaNaoEncontradaException {
		int i = this.getIndice(conta.getNumero());
		if (i == this.indice) {
			throw new ContaNaoEncontradaException();
		} else {
			this.contas[i] = conta;
		}
	}

	public boolean existe(String numero) {
		int i = this.getIndice(numero);
		return (i != this.indice);
	}

	private int getIndice(String numero) {
		String n;
		boolean achou = false;
		int i = 0;
		while ((!achou) && (i < this.indice)) {
			n = contas[i].getNumero();
			if (n.equals(numero)) {
				achou = true;
			} else {
				i = i + 1;
			}
		}
		return i;
	}
}