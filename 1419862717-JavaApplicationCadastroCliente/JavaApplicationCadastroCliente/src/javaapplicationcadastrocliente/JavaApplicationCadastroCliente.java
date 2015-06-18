/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplicationcadastrocliente;

import apresentacao.JFrameCadastroCliente;
import controle.Cliente;
import controle.Compra;
import java.sql.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author home
 */
public class JavaApplicationCadastroCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

         JFrameCadastroCliente jFrameCadastroCliente = new JFrameCadastroCliente();
         jFrameCadastroCliente.setLocationRelativeTo(null);
         jFrameCadastroCliente.setVisible(true);

    }
}
