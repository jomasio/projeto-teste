package br.ufpe.cin.dados;

import br.ufpe.cin.banco.ContaAbstrata;

/**
 * Metodos ajustados para lancar a excecao ContaNaoEncontradaException  
 *
 */
public interface RepositorioContas {
	  void inserir(ContaAbstrata conta);
	  ContaAbstrata procurar(String numero) throws ContaNaoEncontradaException;
	  void remover(String numero) throws ContaNaoEncontradaException;
	  void atualizar(ContaAbstrata conta) throws ContaNaoEncontradaException;
	  boolean existe(String numero);

}
