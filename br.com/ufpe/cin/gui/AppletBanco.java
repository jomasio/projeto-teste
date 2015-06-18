package br.ufpe.cin.gui;

import javax.swing.JApplet;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;

import br.ufpe.cin.banco.Banco;

import java.awt.GridBagLayout;
import java.awt.Rectangle;

public class AppletBanco extends JApplet {

	private JButton bt_inicia = null;
	private JPanel jPanel = null;

	/**
	 * This method initializes 
	 * 
	 */
	public AppletBanco() {
		super();
		
	}

	/**
	 * This method initializes this
	 * 
	 */
	public void init() {
        this.setSize(new Dimension(134, 80));
        this.setContentPane(getJPanel());
			
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (bt_inicia == null) {
			bt_inicia = new JButton();
			bt_inicia.setBounds(new Rectangle(5, 25, 122, 29));
			bt_inicia.setText("Iniciar banco");
			bt_inicia.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					FrameBanco frame = new FrameBanco();
					frame.setVisible(true);
				}
			});
		}
		return bt_inicia;
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
			jPanel.add(getJButton(), null);
		}
		return jPanel;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
