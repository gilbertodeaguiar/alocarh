
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.Icon;
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
 * Created on 15 da Março de 2005
 */

/**
 * 
 * @author Ahilton
 */
public class CadastroTipoDefinicaoCaracteristica extends javax.swing.JPanel {

	/** Creates new form CadastroTipoDefinicaoCaracteristica */
	public CadastroTipoDefinicaoCaracteristica() {
		initComponents();
	}

	private void initComponents() {
		// Atribui o tamanho do jPanel
		this.setSize(600, 400);

		// Inicializa os componentes
		this.jLabelNome = new javax.swing.JLabel();
		this.jTextFieldNome = new javax.swing.JTextField();
		this.jLabelEscala = new javax.swing.JLabel();
		this.jTableTipos = null;
		this.jComboboxEscala = new JComboBox();
		this.jToolBarPrincipal = new javax.swing.JToolBar();
		this.jButtonInserir = new javax.swing.JButton();
		this.jButtonAlterar = new javax.swing.JButton();
		this.jButtonExcluir = new javax.swing.JButton();
		this.jButtonConfirmar = new javax.swing.JButton();
		this.jButtonCancelar = new javax.swing.JButton();
		this.jTextPane1 = new javax.swing.JTextPane();
		this.jPanelPrincipal = new JPanel();

		this.jPanelPrincipal.setLayout(null);
		this.jPanelPrincipal.setBounds(3, 30, 585, 310);
		this.jPanelPrincipal.setBorder(new javax.swing.border.TitledBorder(
		"Cadastro de Tipos de Definição de Característica"));
		
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
		columnNames[1] = "Escala";

		DefaultTableModel tblModel = new MyDefaultTableModel(columnNames, 0);

		this.jTableTipos = new JTable(tblModel);
		this.jTableTipos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.jTableTipos.setBounds(10, 115, 565, 195);
		this.jTableTipos.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				selecionaTipoDefinicaoCaracteristica();
			}
		});

		
		ListSelectionModel rowSM = this.jTableTipos.getSelectionModel();
		   rowSM.addListSelectionListener(new ListSelectionListener() {
		   public void valueChanged(ListSelectionEvent e) {
		    selecionaTipoDefinicaoCaracteristica();	
		   }
		   });
		   
		// Cria ScrollPane que contém a tabela
		this.jScrollPane = new JScrollPane();
		this.jScrollPane.setBounds(10, 115, 565, 195);
		this.jScrollPane.getViewport().add(this.jTableTipos, null);

		// Adiciona scrollpane no jPanel
		this.jPanelPrincipal.add(this.jScrollPane);

		// Label do campo "Nome"
		this.jLabelNome.setText("Nome:");
		this.jPanelPrincipal.add(this.jLabelNome);
		this.jLabelNome.setBounds(130, 30, 30, 20);

		// Campo "tipo"
		this.jPanelPrincipal.add(this.jTextFieldNome);
		this.jTextFieldNome.setBounds(160, 30, 260, 20);
		this.jTextFieldNome.disable();

		// Label do campo "Escala"
		this.jLabelEscala.setText("Escala:");
		this.jPanelPrincipal.add(this.jLabelEscala);
		this.jLabelEscala.setBounds(125, 60, 35, 20);

		// Campo "escala"
		this.jPanelPrincipal.add(this.jComboboxEscala);
		this.jComboboxEscala.setBounds(160, 60, 260, 20);
		this.jComboboxEscala.disable();

		// Botão inserir
		this.jButtonInserir.setText("Inserir  ");
		this.jButtonInserir.setIcon(new ImageIcon("Adiciona.gif"));
		this.jButtonInserir
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						adicionarTipoDefinicaoCaracteristica();
					}
				});
		this.jToolBarPrincipal.add(this.jButtonInserir);

		// Botão alterar
		this.jButtonAlterar.setText("Alterar  ");
		this.jButtonAlterar.setIcon(new ImageIcon("Lapis.gif"));
		this.jButtonAlterar
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						alterarTipoDefinicaoCaracteristica();
					}
				});
		this.jToolBarPrincipal.add(this.jButtonAlterar);

		// Botão excluir
		this.jButtonExcluir.setText("Excluir  ");
		this.jButtonExcluir.setIcon(new ImageIcon("Remove.gif"));
		this.jButtonExcluir
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						excluirTipoDefinicaoCaracteristica();
					}
				});
		this.jToolBarPrincipal.add(this.jButtonExcluir);

		// Botão Confirmar
		this.jButtonConfirmar.setText("Confirma  ");
		this.jButtonConfirmar.setIcon(new ImageIcon("Confirma.gif"));
		this.jButtonConfirmar.hide();
		this.jButtonConfirmar
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						confirmaOperacao();
					}
				});
		this.jToolBarPrincipal.add(this.jButtonConfirmar);

		// Botão Confirmar
		this.jButtonCancelar.setText("Cancela  ");
		this.jButtonCancelar.setIcon(new ImageIcon("Cancela.gif"));
		this.jButtonCancelar.hide();
		this.jButtonCancelar
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						cancelaOperacao();
					}
				});
		this.jToolBarPrincipal.add(this.jButtonCancelar);

		// Adiciona toolbar ao panel
		this.jToolBarPrincipal.setFloatable(false);
		this.jPanelPrincipal.add(this.jToolBarPrincipal);
		this.jToolBarPrincipal.setBounds(385, 90, 190, 25);

		// Título do panel
		this.jTextPane1.setBackground(new java.awt.Color(153, 204, 255));
		this.jTextPane1
				.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 18));
		this.jTextPane1.setText("Cadastro de Tipos de Característica");
		add(this.jTextPane1);
		this.jTextPane1.setBounds(0, 0, 600, 30);
		this.jTextPane1.disable();

		//carregaListaTiposDefinicaoCaracteristica();
	}

	protected void adicionarTipoDefinicaoCaracteristica() {
		/* Controle da barra de botões */
		this.jButtonInserir.hide();
		this.jButtonAlterar.hide();
		this.jButtonExcluir.hide();
		this.jButtonConfirmar.show();
		this.jButtonCancelar.show();

		this.jTextFieldNome.enable();
		this.jComboboxEscala.enable();

		this.jTextFieldNome.setText("");
		if (this.jComboboxEscala.getItemCount() > 0)
			this.jComboboxEscala.setSelectedIndex(0);

		this.validate();
		this.repaint();

		this.operacao = "INSERIR";
	}

	protected void alterarTipoDefinicaoCaracteristica() {
		int linha = this.jTableTipos.getSelectedRow();
		if (linha != -1) {
			this.jButtonInserir.hide();
			this.jButtonAlterar.hide();
			this.jButtonExcluir.hide();
			this.jButtonConfirmar.show();
			this.jButtonCancelar.show();

			this.jComboboxEscala.enable();
			this.validate();
			this.repaint();

			this.operacao = "ALTERAR";
		}
	}

	private void gerenciaBotoesConfirma() {
		/* Controle da barra de botões */
		this.jButtonInserir.show();
		this.jButtonAlterar.show();
		this.jButtonExcluir.show();
		this.jButtonConfirmar.hide();
		this.jButtonCancelar.hide();

		this.jTextFieldNome.disable();
		this.jComboboxEscala.disable();
		this.validate();
		this.repaint();
	}

	protected void confirmaAdicionarTipoDefinicaoCaracteristica() {
		Object[] linha = new Object[2];
		linha[0] = this.jTextFieldNome.getText();
		linha[1] = this.jComboboxEscala.getSelectedItem();

		if ((this.jTextFieldNome.getText().length() == 0)
				|| (this.jComboboxEscala.getItemCount() == 0)) {
			JOptionPane.showMessageDialog(this,
					"Todos os campos devem ser preenchidos.", "Alerta",
					JOptionPane.WARNING_MESSAGE);
		} else {
			int retorno = JanelaPrincipal.getAplicacao()
					.inserirTipoDefinicaoCaracteristica(
							this.jTextFieldNome.getText(),
							(Escala) this.jComboboxEscala.getSelectedItem());

			switch (retorno) {
			case 0:
				((DefaultTableModel) this.jTableTipos.getModel()).addRow(linha);
				this.gerenciaBotoesConfirma();
				this.jTableTipos.setRowSelectionInterval(this.jTableTipos
						.getRowCount() - 1, this.jTableTipos.getRowCount() - 1);
				break;
			case -1:
				JOptionPane
						.showMessageDialog(
								this,
								"Não é possível inserir dois elementos com nomes iguais.",
								"Alerta", JOptionPane.WARNING_MESSAGE);
				break;
			}
		}
	}

	protected void confirmaOperacao() {
		if (this.operacao.compareToIgnoreCase("INSERIR") == 0) {
			this.confirmaAdicionarTipoDefinicaoCaracteristica();
		}
		if (this.operacao.compareToIgnoreCase("ALTERAR") == 0) {
			this.confirmaAlterarTipoDefinicaoCaracteristica();
		}
	}

	protected void cancelaOperacao() {
		/* Controle da barra de botões */
		this.jButtonInserir.show();
		this.jButtonAlterar.show();
		this.jButtonExcluir.show();
		this.jButtonConfirmar.hide();
		this.jButtonCancelar.hide();

		this.jTextFieldNome.disable();
		this.jComboboxEscala.disable();

		this.selecionaTipoDefinicaoCaracteristica();

		this.validate();
		this.repaint();
	}

	protected void confirmaAlterarTipoDefinicaoCaracteristica() {
		Object[] linha = new Object[3];
		linha[0] = this.jTextFieldNome.getText();
		linha[1] = this.jComboboxEscala.getSelectedItem();

		if ((this.jTextFieldNome.getText().length() == 0)
				|| (this.jComboboxEscala.getItemCount() == 0)) {
			JOptionPane.showMessageDialog(this,
					"Todos os campos devem ser preenchidos.", "Alerta",
					JOptionPane.WARNING_MESSAGE);
		} else {
			int linhaSelecionada = this.jTableTipos.getSelectedRow();
			if (linhaSelecionada != -1) {
				int retorno = JanelaPrincipal
						.getAplicacao()
						.alterarTipoDefinicaoCaracteristica(
								this.jTextFieldNome.getText(),
								(Escala) this.jComboboxEscala.getSelectedItem());

				switch (retorno) {
				case 0:
					((DefaultTableModel) this.jTableTipos.getModel())
							.setValueAt(linha[0], linhaSelecionada, 0);
					((DefaultTableModel) this.jTableTipos.getModel())
							.setValueAt(linha[1], linhaSelecionada, 1);
					this.gerenciaBotoesConfirma();
					break;
				case -1:
					JOptionPane
							.showMessageDialog(
									this,
									"Não é possível alterar a escala, pois algum profissional já tem alguma característica que a utiliza.",
									"Alerta", JOptionPane.WARNING_MESSAGE);
					break;
				case -2:
					JOptionPane
							.showMessageDialog(
									this,
									"Não é possível alterar a escala, pois alguma atividade já tem alguma característica que a utiliza.",
									"Alerta", JOptionPane.WARNING_MESSAGE);
					break;
				}
			}
		}
	}

	protected void selecionaTipoDefinicaoCaracteristica() {
		int linhaSelecionada = this.jTableTipos.getSelectedRow();
		if (linhaSelecionada != -1) {
			this.jTextFieldNome
					.setText((String) ((DefaultTableModel) this.jTableTipos
							.getModel()).getValueAt(linhaSelecionada, 0));
			this.jComboboxEscala
					.setSelectedItem(((DefaultTableModel) this.jTableTipos
							.getModel()).getValueAt(linhaSelecionada, 1));
		}
	}

	protected void excluirTipoDefinicaoCaracteristica() {
		int linha = this.jTableTipos.getSelectedRow();
		if (linha != -1) {

			int retorno = JanelaPrincipal.getAplicacao()
					.excluirTipoDefinicaoCaracteristica(
							this.jTextFieldNome.getText());

			if (retorno == 0) {
				((DefaultTableModel) this.jTableTipos.getModel())
						.removeRow(this.jTableTipos.getSelectedRow());
				if (linha > 0) {
					this.jTableTipos.setRowSelectionInterval(linha - 1,
							linha - 1);
				} else {
					if (this.jTableTipos.getRowCount() > 0) {
						this.jTableTipos.setRowSelectionInterval(linha, linha);
					} else {
						this.jTextFieldNome.setText("");
						if (this.jComboboxEscala.getItemCount() > 0)
							this.jComboboxEscala.setSelectedIndex(0);
					}
				}
				this.selecionaTipoDefinicaoCaracteristica();
			} else {
				if (retorno == -1) {
					JOptionPane
							.showMessageDialog(
									this,
									"Não é possível excluir, pois há alguma definição de característica associada.",
									"Alerta", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
	
	protected void carregaListaTiposDefinicaoCaracteristica() {
		Object[] linha = new Object[2];
		ArrayList listaTipos = new ArrayList();

		listaTipos = JanelaPrincipal.getAplicacao().getTiposDefinicaoCaracteristica();

		Iterator iter1 = listaTipos.iterator();
		while (iter1.hasNext()) {
			TipoDefinicaoCaracteristica tdc = (TipoDefinicaoCaracteristica) iter1.next();
			linha[0] = tdc.getNome();
			linha[1] = tdc.getEscala();

			((DefaultTableModel) this.jTableTipos.getModel()).addRow(linha);
		}
		if (listaTipos.size() > 0) {
			this.jTableTipos.setRowSelectionInterval(0, 0);
			selecionaTipoDefinicaoCaracteristica();
		}
	}
	
	protected void carregaComboEscala(){
		Collection escalas = JanelaPrincipal.getAplicacao().getEscalas().values();
		Iterator iter = escalas.iterator();
		while (iter.hasNext()) {
			Escala escala = (Escala) iter.next();
			this.jComboboxEscala.addItem(escala);
		}
	}
	
	protected void limpaComboEscala(){
		this.jComboboxEscala.removeAllItems();
	}

	// Variables declaration - do not modify

	private String operacao = "";

	private javax.swing.JButton jButtonInserir;

	private javax.swing.JButton jButtonAlterar;

	private javax.swing.JButton jButtonExcluir;

	private javax.swing.JButton jButtonConfirmar;

	private javax.swing.JButton jButtonCancelar;

	private javax.swing.JLabel jLabelNome;

	private javax.swing.JLabel jLabelEscala;

	private JTable jTableTipos;

	private javax.swing.JTextField jTextFieldNome;

	private JComboBox jComboboxEscala;

	private javax.swing.JTextPane jTextPane1;

	private javax.swing.JToolBar jToolBarPrincipal;

	private JScrollPane jScrollPane;
	
	private JPanel jPanelPrincipal;
	// End of variables declaration

}
