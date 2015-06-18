/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.CompraJpaController;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.swing.JOptionPane;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author home
 */
@Entity
@Table(name = "compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c"),
    @NamedQuery(name = "Compra.findById", query = "SELECT c FROM Compra c WHERE c.id = :id"),
    @NamedQuery(name = "Compra.findByData", query = "SELECT c FROM Compra c WHERE c.data = :data"),
    @NamedQuery(name = "Compra.findByDescricao", query = "SELECT c FROM Compra c WHERE c.descricao = :descricao"),
    @NamedQuery(name = "Compra.findByQtde", query = "SELECT c FROM Compra c WHERE c.qtde = :qtde"),
    @NamedQuery(name = "Compra.findByPrecoUnitario", query = "SELECT c FROM Compra c WHERE c.precoUnitario = :precoUnitario")})
public class Compra implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "Data", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @Column(name = "Descricao", nullable = false, length = 40)
    private String descricao;
    @Basic(optional = false)
    @Column(name = "Qtde", nullable = false)
    private int qtde;
    @Basic(optional = false)
    @Column(name = "PrecoUnitario", nullable = false)
    private double precoUnitario;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Cliente idCliente;

    public Compra() {
    }

    public Compra(Long id) {
        this.id = id;
    }

    public Compra(Long id, Date data, String descricao, int qtde, double precoUnitario) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.qtde = qtde;
        this.precoUnitario = precoUnitario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        Long oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        Date oldData = this.data;
        this.data = data;
        changeSupport.firePropertyChange("data", oldData, data);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        String oldDescricao = this.descricao;
        this.descricao = descricao;
        changeSupport.firePropertyChange("descricao", oldDescricao, descricao);
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        int oldQtde = this.qtde;
        this.qtde = qtde;
        changeSupport.firePropertyChange("qtde", oldQtde, qtde);
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        double oldPrecoUnitario = this.precoUnitario;
        this.precoUnitario = precoUnitario;
        changeSupport.firePropertyChange("precoUnitario", oldPrecoUnitario, precoUnitario);
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        Cliente oldIdCliente = this.idCliente;
        this.idCliente = idCliente;
        changeSupport.firePropertyChange("idCliente", oldIdCliente, idCliente);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controle.Compra[ id=" + id + " ]";
    }

    public boolean armazenado() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaApplicationCadastroClientePU");
            CompraJpaController compraJpaController = new CompraJpaController(emf);
            compraJpaController.create(this);
            JOptionPane.showMessageDialog(null, "Compra incluida");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public boolean atualizado() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaApplicationCadastroClientePU");
            CompraJpaController compraJpaController = new CompraJpaController(emf);
            compraJpaController.edit(this);
            JOptionPane.showMessageDialog(null, "Compra atualizado");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public boolean desarmazenado() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaApplicationCadastroClientePU");
            CompraJpaController compraJpaController = new CompraJpaController(emf);
            compraJpaController.destroy(this.getId());
            JOptionPane.showMessageDialog(null, "Compra excluida");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public boolean encontradoId(Long id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaApplicationCadastroClientePU");
            CompraJpaController compraJpaController = new CompraJpaController(emf);
            Compra compraAux = compraJpaController.findCompra(id);
            if (compraAux != null) {
                this.setId(compraAux.getId());
                this.setDescricao(compraAux.getDescricao());
                this.setData(compraAux.getData());
                this.setQtde(compraAux.getQtde());
                this.setPrecoUnitario(compraAux.getPrecoUnitario());
                this.setIdCliente(compraAux.getIdCliente());
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Compra não encontrada");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            return false;
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
