
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/*
 * cadastroCaracteristicas.java
 *
 * Created on 10 de Fevereiro de 2005, 12:25
 */

/**
 * 
 * @author Ahilton
 */
public class CadastroCaracteristicas extends javax.swing.JPanel {

	/** Creates new form CadastroCaracteristicas */
	public CadastroCaracteristicas() {
		initComponents();
	}

	private void initComponents() {
		// Atribui o tamanho do jPanel
		this.setSize(600, 400);

		// Inicializa os componentes
		this.jLabel1 = new javax.swing.JLabel();
		this.jTextField1 = new javax.swing.JTextField();
		this.jLabel2 = new javax.swing.JLabel();
		this.jTable1 = null;
		this.jComboboxTipos = new JComboBox();
		this.jToolBar1 = new javax.swing.JToolBar();
		this.jButton1 = new javax.swing.JButton();
		this.jButton2 = new javax.swing.JButton();
		this.jButton3 = new javax.swing.JButton();
		this.jButton4 = new javax.swing.JButton();
		this.jButton5 = new javax.swing.JButton();
		this.jTextPane1 = new javax.swing.JTextPane();
		this.jPanelPrincipal = new JPanel();

		this.jPanelPrincipal.setLayout(null);
		this.jPanelPrincipal.setBounds(3, 30, 585, 310);
		this.jPanelPrincipal.setBorder(new javax.swing.border.TitledBorder(
		"Cadastro de Definições de Característica"));
		
		class MyDefaultTableModel extends DefaultTableModel {

			public MyDefaultTableModel(Object[] columnNames, int value) {
				super(columnNames, value);
			}

			public boolean isCellEditable(int row, int col) {
				return false;
			}
		}

		// Atribui o layout do jPanel
		setLayout(null);
		this.add(this.jPanelPrincipal);
		
		// Cria tabela baseada no modelo definido e seta sua posição
		Object[] columnNames = new Object[2];
		columnNames[0] = "Nome";
		columnNames[1] = "Tipo";

		DefaultTableModel tblModel = new MyDefaultTableModel(columnNames, 0);

		this.jTable1 = new JTable(tblModel);
		this.jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.jTable1.setBounds(10, 115, 565, 195);
		this.jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				selecionaCaracteristica();
			}
		});

		ListSelectionModel rowSM = this.jTable1.getSelectionModel();
		   rowSM.addListSelectionListener(new ListSelectionListener() {
		   public void valueChanged(ListSelectionEvent e) {
		    selecionaCaracteristica();	
		   }
		   });
		
		// Cria ScrollPane que contém a tabela
		this.jScrollPane = new JScrollPane();
		this.jScrollPane.setBounds(10, 115, 565, 195);
		this.jScrollPane.getViewport().add(this.jTable1, null);

		// Adiciona scrollpane no jPanel
		this.jPanelPrincipal.add(this.jScrollPane);

		// Label do campo "Tipo"
		this.jLabel1.setText("Nome:");
		this.jPanelPrincipal.add(this.jLabel1);
		this.jLabel1.setBounds(130, 30, 30, 20);

		// Campo "tipo"
		this.jPanelPrincipal.add(this.jTextField1);
		this.jTextField1.setBounds(160, 30, 220, 20);
		this.jTextField1.disable();

		// Label do campo "nome"
		this.jLabel2.setText("Tipo:");
		this.jPanelPrincipal.add(this.jLabel2);
		this.jLabel2.setBounds(135, 60, 25, 20);

		// Campo "nome"
		this.jPanelPrincipal.add(this.jComboboxTipos);
		this.jComboboxTipos.setBounds(160, 60, 220, 20);
		this.jComboboxTipos.disable();

		// Botão inserir
		this.jButton1.setText("Inserir  ");
		this.jButton1.setIcon(new ImageIcon("Adiciona.gif"));
		this.jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				adicionarCaracteristica();
			}
		});
		this.jToolBar1.add(this.jButton1);

		// Botão alterar
		this.jButton2.setText("Alterar  ");
		this.jButton2.setIcon(new ImageIcon("Lapis.gif"));
		this.jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				alterarCaracteristica();
			}
		});
		this.jToolBar1.add(this.jButton2);

		// Botão excluir
		this.jButton3.setText("Excluir  ");
		this.jButton3.setIcon(new ImageIcon("Remove.gif"));
		this.jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				excluirCaracteristica();
			}
		});
		this.jToolBar1.add(this.jButton3);

		// Botão Confirmar
		this.jButton4.setText("Confirma  ");
		this.jButton4.setIcon(new ImageIcon("Confirma.gif"));
		this.jButton4.hide();
		this.jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				confirmaOperacao();
			}
		});
		this.jToolBar1.add(this.jButton4);

		// Botão Confirmar
		this.jButton5.setText("Cancela  ");
		this.jButton5.setIcon(new ImageIcon("Cancela.gif"));
		this.jButton5.hide();
		this.jButton5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				cancelaOperacao();
			}
		});
		this.jToolBar1.add(this.jButton5);

		// Adiciona toolbar ao panel
		this.jToolBar1.setFloatable(false);
		this.jPanelPrincipal.add(this.jToolBar1);
		this.jToolBar1.setBounds(385, 90, 190, 25);

		// Título do panel
		this.jTextPane1.setBackground(new java.awt.Color(153, 204, 255));
		this.jTextPane1
				.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 18));
		this.jTextPane1.setText("Cadastro de Caracter\u00edsticas");
		add(this.jTextPane1);
		this.jTextPane1.setBounds(0, 0, 600, 30);
		this.jTextPane1.disable();
	}

	protected void adicionarCaracteristica() {
		/* Controle da barra de botões */
		this.jButton1.hide();
		this.jButton2.hide();
		this.jButton3.hide();
		this.jButton4.show();
		this.jButton5.show();

		this.jTextField1.enable();
		this.jComboboxTipos.enable();
		
		this.jTextField1.setText("");
		if (this.jComboboxTipos.getItemCount()>0)
			this.jComboboxTipos.setSelectedIndex(0);
		
		this.validate();
		this.repaint();

		this.operacao = "INSERIR";
	}

	protected void alterarCaracteristica() {
		int linha = this.jTable1.getSelectedRow();
		if (linha != -1) {
			this.jButton1.hide();
			this.jButton2.hide();
			this.jButton3.hide();
			this.jButton4.show();
			this.jButton5.show();

			this.jComboboxTipos.enable();
			this.validate();
			this.repaint();

			this.operacao = "ALTERAR";
		}
	}

	private void gerenciaBotoesConfirma() {
		/* Controle da barra de botões */
		this.jButton1.show();
		this.jButton2.show();
		this.jButton3.show();
		this.jButton4.hide();
		this.jButton5.hide();

		this.jTextField1.disable();
		this.jComboboxTipos.disable();
		this.validate();
		this.repaint();
	}

	protected void confirmaAdicionarCaracteristica() {
		Object[] linha = new Object[2];
		linha[0] = this.jTextField1.getText();
		linha[1] = this.jComboboxTipos.getSelectedItem();


		if ((this.jTextField1.getText().length() == 0)
				|| (this.jComboboxTipos.getItemCount() == 0)) {
			JOptionPane.showMessageDialog(this,
					"Todos os campos devem ser preenchidos.", "Alerta",
					JOptionPane.WARNING_MESSAGE);
		} else {
			int retorno = JanelaPrincipal.getAplicacao()
					.inserirDefinicaoCaracteristica(this.jTextField1.getText(),
							(TipoDefinicaoCaracteristica)this.jComboboxTipos.getSelectedItem());

			switch (retorno) {
			case 0:
				((DefaultTableModel) this.jTable1.getModel()).addRow(linha);
				this.gerenciaBotoesConfirma();
				this.jTable1.setRowSelectionInterval(
						this.jTable1.getRowCount() - 1, this.jTable1
								.getRowCount() - 1);
				break;
			case -1:
				JOptionPane
						.showMessageDialog(
								this,
								"Não é possível inserir dois elementos com nomes iguais.",
								"Alerta", JOptionPane.WARNING_MESSAGE);
				break;
			case -2:
				JOptionPane.showMessageDialog(this,
						"O campo valor máximo deve ser um inteiro.", "Alerta",
						JOptionPane.WARNING_MESSAGE);
				break;
			}
		}
	}

	protected void confirmaOperacao() {
		if (this.operacao.compareToIgnoreCase("INSERIR") == 0) {
			this.confirmaAdicionarCaracteristica();
		}
		if (this.operacao.compareToIgnoreCase("ALTERAR") == 0) {
			this.confirmaAlterarCaracteristica();
		}
	}

	protected void cancelaOperacao() {
		/* Controle da barra de botões */
		this.jButton1.show();
		this.jButton2.show();
		this.jButton3.show();
		this.jButton4.hide();
		this.jButton5.hide();

		this.jTextField1.disable();
		this.jComboboxTipos.disable();

		this.selecionaCaracteristica();

		this.validate();
		this.repaint();
	}

	protected void confirmaAlterarCaracteristica() {
		Object[] linha = new Object[2];
		linha[0] = this.jTextField1.getText();
		linha[1] = this.jComboboxTipos.getSelectedItem();

		/* Seria bom validar que não podem ser incluídos elementos iguais */
		if ((this.jTextField1.getText().length() == 0)
				|| (this.jComboboxTipos.getItemCount() == 0)) {
			JOptionPane.showMessageDialog(this,
					"Todos os campos devem ser preenchidos.", "Alerta",
					JOptionPane.WARNING_MESSAGE);
		} else {
			int linhaSelecionada = this.jTable1.getSelectedRow();
			if (linhaSelecionada != -1) {
				int retorno = JanelaPrincipal.getAplicacao()
						.alterarDefinicaoCaracteristica(
								this.jTextField1.getText(),
								(TipoDefinicaoCaracteristica)this.jComboboxTipos.getSelectedItem());

				switch (retorno) {
				case 0:
					((DefaultTableModel) this.jTable1.getModel()).setValueAt(
							linha[0], linhaSelecionada, 0);
					((DefaultTableModel) this.jTable1.getModel()).setValueAt(
							linha[1], linhaSelecionada, 1);
					this.gerenciaBotoesConfirma();
					break;
				case -1:
					JOptionPane
							.showMessageDialog(
									this,
									"Não é possível atirbuir este valor máximo, pois já foi atribuído valor maior a esta característica em algum profissional ou atividade.",
									"Alerta", JOptionPane.WARNING_MESSAGE);
					break;
				case -2:
					JOptionPane.showMessageDialog(this,
							"O campo valor máximo deve ser um inteiro.",
							"Alerta", JOptionPane.WARNING_MESSAGE);
					break;
				}
			}
		}
	}

	protected void selecionaCaracteristica() {
		int linhaSelecionada = this.jTable1.getSelectedRow();
		if (linhaSelecionada != -1) {
			this.jTextField1.setText((String) ((DefaultTableModel) this.jTable1
					.getModel()).getValueAt(linhaSelecionada, 0));
			
			this.jComboboxTipos
			.setSelectedItem(((DefaultTableModel) this.jTable1
					.getModel()).getValueAt(linhaSelecionada, 1));
		}
	}

	protected void excluirCaracteristica() {
		int linha = this.jTable1.getSelectedRow();
		if (linha != -1) {

			int retorno = JanelaPrincipal.getAplicacao()
					.excluirDefinicaoCaracteristica(this.jTextField1.getText());

			if (retorno == 0) {
				((DefaultTableModel) this.jTable1.getModel())
						.removeRow(this.jTable1.getSelectedRow());
				if (linha > 0) {
					this.jTable1.setRowSelectionInterval(linha - 1, linha - 1);
				} else {
					if (this.jTable1.getRowCount() > 0) {
						this.jTable1.setRowSelectionInterval(linha, linha);
					} else {
						this.jTextField1.setText("");
						if (this.jComboboxTipos.getItemCount()>0)
							this.jComboboxTipos.setSelectedIndex(0);
						
					}
				}
				this.selecionaCaracteristica();
			} else {
				if (retorno == -1) {
					JOptionPane
							.showMessageDialog(
									this,
									"Não é possível excluir, pois esta característica já recebeu valor em algum profissional ou atividade.",
									"Alerta", JOptionPane.WARNING_MESSAGE);
				}
			}

		}
	}

	protected void carregaListaDefinicoesCaracteristica() {
		Object[] linha = new Object[2];
		Collection listaCaracteristicas = new ArrayList();

		listaCaracteristicas = JanelaPrincipal.getAplicacao().getCaracteristicas().values();

		Iterator iter1 = listaCaracteristicas.iterator();
		while (iter1.hasNext()) {
			DefinicaoCaracteristica dc = (DefinicaoCaracteristica) iter1.next();
			linha[0] = dc.getNome();
			linha[1] = dc.getTipoDefinicaoCaracteristica();

			((DefaultTableModel) this.jTable1.getModel()).addRow(linha);
		}
		if (listaCaracteristicas.size() > 0) {
			this.jTable1.setRowSelectionInterval(0, 0);
			selecionaCaracteristica();
		}
	}
	
	protected void carregaComboTipos(){
		ArrayList listaNomesTipos = JanelaPrincipal.getAplicacao().getTiposDefinicaoCaracteristica();
		Iterator i = listaNomesTipos.iterator();
		while (i.hasNext()) {
			TipoDefinicaoCaracteristica tipo = (TipoDefinicaoCaracteristica) i.next();
			this.jComboboxTipos.addItem(tipo);
		}		
	}
	
	protected void limpaComboTipos(){
		this.jComboboxTipos.removeAllItems();
	}
	
	// Variables declaration - do not modify

	private String operacao = "";

	private javax.swing.JButton jButton1;

	private javax.swing.JButton jButton2;

	private javax.swing.JButton jButton3;

	private javax.swing.JButton jButton4;

	private javax.swing.JButton jButton5;

	private javax.swing.JLabel jLabel1;

	private javax.swing.JLabel jLabel2;

	private JTable jTable1;

	private javax.swing.JTextField jTextField1;

	private JComboBox jComboboxTipos;

	private javax.swing.JTextPane jTextPane1;

	private javax.swing.JToolBar jToolBar1;

	private JScrollPane jScrollPane;
	
	private JPanel jPanelPrincipal;
	// End of variables declaration

}
