package br.ufpe.cin.gui;
import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.ufpe.cin.banco.Banco;
import br.ufpe.cin.banco.Conta;
import br.ufpe.cin.banco.ContaAbstrata;
import br.ufpe.cin.banco.ContaEspecial;
import br.ufpe.cin.banco.ContaImposto;
import br.ufpe.cin.banco.ContaJaCadastradaException;
import br.ufpe.cin.banco.Poupanca;
import br.ufpe.cin.banco.RenderBonusContaEspecialException;
import br.ufpe.cin.banco.RenderJurosPoupancaException;
import br.ufpe.cin.banco.SaldoInsuficienteException;
import br.ufpe.cin.dados.ContaNaoEncontradaException;
import br.ufpe.cin.dados.RepositorioContasArray;

import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JRadioButton;

public class FrameBanco extends JFrame {

	private Banco fachada;
	
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton bt_cadastrar = null;
	private JButton bt_creditar = null;
	private JButton bt_debitar = null;
	private JButton bt_transferir = null;
	private JButton bt_saldo = null;
	private JButton bt_renderJuros = null;
	private JButton bt_renderBonus = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel = null;
	private JTextField tf_numero = null;
	private JTextField tf_valor = null;

	private JRadioButton rb_conta = null;

	private JRadioButton rb_poupanca = null;

	private JRadioButton rb_contaEspecial = null;

	private JRadioButton rb_contaImposto = null;

	private JPanel jPanel = null;

	private JPanel jPanel1 = null;
	/**
	 * This is the default constructor
	 */
	public FrameBanco() {
		super();
		initialize();
		
		fachada = new Banco(new RepositorioContasArray(100));
		
		
		//Veja como usar RadioButton em 
		//http://java.sun.com/j2se/1.5.0/docs/api/javax/swing/JRadioButton.html
		ButtonGroup bg = new ButtonGroup();
		bg.add(rb_conta);
		bg.add(rb_contaEspecial);
		bg.add(rb_contaImposto);
		bg.add(rb_poupanca);
		
	}

	private void erroConversao() {
		JOptionPane.showMessageDialog(this, "O valor deve ser numerico");
		tf_valor.setText("");
		tf_valor.requestFocus();
	}

	private void erroNumero() {
		erroNumero("Informe o número da conta desejada");
	}
	
	private void erroNumero(String mensagem) {
		JOptionPane.showMessageDialog(this, mensagem);
		tf_numero.selectAll();
		tf_numero.requestFocus();
	}
	
	private void erroSaldo(String mensagem) {
		JOptionPane.showMessageDialog(this, mensagem);
		tf_valor.selectAll();
		tf_valor.requestFocus();
	}
	
	private void sucesso(String mensagem) {
		JOptionPane.showMessageDialog(this, mensagem);
		tf_numero.setText("");
		tf_valor.setText("");
		tf_numero.requestFocus();
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(382, 207);
		this.setContentPane(getJContentPane());
		this.setTitle("Aplicação bancária");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(106, 110, 66, 28));
			jLabel.setText("Valor");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(106, 76, 66, 28));
			jLabel1.setText("Numero");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getBt_creditar(), null);
			jContentPane.add(getBt_debitar(), null);
			jContentPane.add(getBt_transferir(), null);
			jContentPane.add(getBt_saldo(), null);
			jContentPane.add(getBt_renderJuros(), null);
			jContentPane.add(getBt_renderBonus(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getJPanel(), null);
			jContentPane.add(getJPanel1(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes bt_cadastrar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBt_cadastrar() {
		if (bt_cadastrar == null) {
			bt_cadastrar = new JButton();
			bt_cadastrar.setText("Cadastrar");
			bt_cadastrar.setBounds(new Rectangle(2, 14, 113, 29));
			bt_cadastrar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cadastrar();
				}
			});
		}
		return bt_cadastrar;
	}

	private void cadastrar() {
		ContaAbstrata conta = null;
		String numero = tf_numero.getText();
		String v = tf_valor.getText();
		if (numero.equals("")) {
			erroNumero();
		} else {
			try {
				double valor = Double.parseDouble(v);
				if(rb_conta.isSelected()) {
					conta = new Conta(numero, valor);
				} else if(rb_poupanca.isSelected()) {
					conta = new Poupanca(numero, valor);
				} else if(rb_contaEspecial.isSelected()) {
					conta = new ContaEspecial(numero, valor);
				} else if(rb_contaImposto.isSelected()) {
					conta = new ContaImposto(numero, valor);
				}
				fachada.cadastrar(conta);
				sucesso(conta.getClass().getSimpleName()+" cadastrada com sucesso");
			} catch (NumberFormatException e) {
				erroConversao();
			} catch (ContaJaCadastradaException e) {
				erroNumero(e.getMessage());
			}
		}
	}


	/**
	 * This method initializes bt_creditar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBt_creditar() {
		if (bt_creditar == null) {
			bt_creditar = new JButton();
			bt_creditar.setBounds(new Rectangle(5, 75, 88, 29));
			bt_creditar.setText("Creditar");
			bt_creditar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					creditar();
				}
			});
		}
		return bt_creditar;
	}

	private void creditar() {
		String numero = tf_numero.getText();
		String v = tf_valor.getText();
		if (numero.equals("")) {
			erroNumero();
		} else {
			try {
				double valor = Double.parseDouble(v);	
				fachada.creditar(numero, valor);
				sucesso("Credito executado com sucesso");
			} catch (NumberFormatException e) {
				erroConversao();
			} catch (ContaNaoEncontradaException e) {
				erroNumero(e.getMessage());
			}
		}
	}

	/**
	 * This method initializes bt_debitar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBt_debitar() {
		if (bt_debitar == null) {
			bt_debitar = new JButton();
			bt_debitar.setBounds(new Rectangle(5, 109, 88, 29));
			bt_debitar.setText("Debitar");
			bt_debitar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					debitar();
				}
			});
		}
		return bt_debitar;
	}

	private void debitar() {
		String numero = tf_numero.getText();
		String v = tf_valor.getText();
		if (numero.equals("")) {
			erroNumero();
		} else {
			try {
				double valor = Double.parseDouble(v);	
				fachada.debitar(numero, valor);
				sucesso("Debito executado com sucesso");
			} catch (NumberFormatException e) {
				erroConversao();
			} catch (ContaNaoEncontradaException e) {
				erroNumero(e.getMessage());
			} catch (SaldoInsuficienteException e) {
				erroSaldo(e.getMessage());
			}
		}
	}

	/**
	 * This method initializes bt_transferir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBt_transferir() {
		if (bt_transferir == null) {
			bt_transferir = new JButton();
			bt_transferir.setBounds(new Rectangle(287, 109, 88, 29));
			bt_transferir.setText("Transferir");
			bt_transferir.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					transferir();
				}
			});
		}
		return bt_transferir;
	}

	private void transferir() {
		String de = tf_numero.getText();
		String v = tf_valor.getText();
		String para = null;
		if (de.equals("")) {
			erroNumero();
		} else {
			try {
				double valor = Double.parseDouble(v);
				do {
					para = JOptionPane.showInputDialog(this, "Informe o numero da conta de destino");
				} while (para.equals(""));
				fachada.transferir(de, para, valor);
				sucesso("Transferencia executada com sucesso");
			} catch (NumberFormatException e) {
				erroConversao();
			} catch (ContaNaoEncontradaException e) {
				erroNumero(e.getMessage());
			} catch (SaldoInsuficienteException e) {
				erroSaldo(e.getMessage());
			}
		}
	}

	/**
	 * This method initializes bt_saldo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBt_saldo() {
		if (bt_saldo == null) {
			bt_saldo = new JButton();
			bt_saldo.setBounds(new Rectangle(287, 75, 88, 29));
			bt_saldo.setText("Saldo");
			bt_saldo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					saldo();
				}
			});
		}
		return bt_saldo;
	}

	private void saldo() {
		String numero = tf_numero.getText();
		if (numero.equals("")) {
			erroNumero();
		} else {
			try {
				double saldo = fachada.getSaldo(numero);
				sucesso("O saldo da conta "+ numero+" eh "+saldo);
			} catch (ContaNaoEncontradaException e) {
				erroNumero(e.getMessage());
			}
		}
	}

	/**
	 * This method initializes bt_renderJuros	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBt_renderJuros() {
		if (bt_renderJuros == null) {
			bt_renderJuros = new JButton();
			bt_renderJuros.setBounds(new Rectangle(217, 148, 113, 29));
			bt_renderJuros.setText("Render juros");
			bt_renderJuros.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					renderJuros();
				}
			});
		}
		return bt_renderJuros;
	}

	private void renderJuros() {
		String numero = tf_numero.getText();
		if (numero.equals("")) {
			erroNumero();
		} else {
			try {
				fachada.renderJuros(numero);
				sucesso("Juros creditado com sucesso");
			} catch (ContaNaoEncontradaException e) {
				erroNumero(e.getMessage());
			} catch (RenderJurosPoupancaException e) {
				erroNumero(e.getMessage());
			}
		}
	}

	/**
	 * This method initializes bt_renderBonus	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBt_renderBonus() {
		if (bt_renderBonus == null) {
			bt_renderBonus = new JButton();
			bt_renderBonus.setBounds(new Rectangle(52, 148, 113, 29));
			bt_renderBonus.setText("Render bonus");
			bt_renderBonus.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					renderBonus();
				}
			});
		}
		return bt_renderBonus;
	}

	private void renderBonus() {
		String numero = tf_numero.getText();
		if (numero.equals("")) {
			erroNumero();
		} else {
			try {
				fachada.renderBonus(numero);
				sucesso("Bonus creditado com sucesso");
			} catch (ContaNaoEncontradaException e) {
				erroNumero(e.getMessage());
			} catch (RenderBonusContaEspecialException e) {
				erroNumero(e.getMessage());
			}
		}
	}

	/**
	 * This method initializes tf_numero	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTf_numero() {
		if (tf_numero == null) {
			tf_numero = new JTextField();
			tf_numero.setToolTipText("Número da conta a ser operada (se transferencia, conta de origem)");
			tf_numero.setBounds(new Rectangle(0
					, 7, 103, 28));
		}
		return tf_numero;
	}

	/**
	 * This method initializes tf_valor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTf_valor() {
		if (tf_valor == null) {
			tf_valor = new JTextField();
			tf_valor.setToolTipText("Valor a ser utilizado pelas operacoes");
			tf_valor.setBounds(new Rectangle(0, 41, 103, 28));
		}
		return tf_valor;
	}

	/**
	 * This method initializes rb_conta	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getRb_conta() {
		if (rb_conta == null) {
			rb_conta = new JRadioButton();
			rb_conta.setText("Conta");
			rb_conta.setSelected(true);
			rb_conta.setBounds(new Rectangle(125, 5, 95, 23));
		}
		return rb_conta;
	}

	/**
	 * This method initializes rb_Poupanca	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getRb_Poupanca() {
		if (rb_poupanca == null) {
			rb_poupanca = new JRadioButton();
			rb_poupanca.setText("Poupanca");
			rb_poupanca.setBounds(new Rectangle(125, 32, 95, 23));
		}
		return rb_poupanca;
	}

	/**
	 * This method initializes rb_contaEspecial	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getRb_contaEspecial() {
		if (rb_contaEspecial == null) {
			rb_contaEspecial = new JRadioButton();
			rb_contaEspecial.setText("Conta especial");
			rb_contaEspecial.setBounds(new Rectangle(223, 5, 131, 23));
		}
		return rb_contaEspecial;
	}

	/**
	 * This method initializes rb_contaImposto	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getRb_contaImposto() {
		if (rb_contaImposto == null) {
			rb_contaImposto = new JRadioButton();
			rb_contaImposto.setText("Conta imposto");
			rb_contaImposto.setBounds(new Rectangle(223, 32, 131, 23));
		}
		return rb_contaImposto;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(16, 9, 353, 59));
			jPanel.setBackground(Color.gray);
			jPanel.add(getBt_cadastrar(), null);
			jPanel.add(getRb_conta(), null);
			jPanel.add(getRb_Poupanca(), null);
			jPanel.add(getRb_contaEspecial(), null);
			jPanel.add(getRb_contaImposto(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.setBounds(new Rectangle(175, 71, 103, 76));
			jPanel1.add(getTf_valor(), null);
			jPanel1.add(getTf_numero(), null);
		}
		return jPanel1;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
