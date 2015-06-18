package br.ufpe.cin.banco;

public class ContaImposto extends ContaAbstrata {
	public static final double CPMF = 0.0038;
	
	public ContaImposto(String numero, double valor) {
		super(numero, valor);
	}
	
	public void debitar(double valor) throws SaldoInsuficienteException {
		if (this.getSaldo() < valor)
			throw new SaldoInsuficienteException(this.getNumero(), this.getSaldo());
		double imposto = valor * CPMF;
	    double total = valor + imposto;
	    this.setSaldo(this.getSaldo() - total);
	}

}