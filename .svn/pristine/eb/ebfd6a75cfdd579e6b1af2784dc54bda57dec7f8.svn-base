
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/*
 * cadastroCaracteristicas.java
 *
 * Created on 15 de Março de 2005, 18:03
 */

/**
 * 
 * @author Ahilton
 */
public class CadastroEscalas extends javax.swing.JPanel {

	/** Creates new form CadastroCaracteristicas */
	public CadastroEscalas() {
		initComponents();
	}

	private void initComponents() {
		// Atribui o tamanho do jPanel
		this.setSize(600, 400);

		// Inicializa os componentes
		this.jTabbedPane = new JTabbedPane();
		this.jLabelNome = new javax.swing.JLabel();
		this.jTextFieldNome = new javax.swing.JTextField();
		this.jLabelValorMaximo = new javax.swing.JLabel();
		this.jTextFieldValorMaximo = new javax.swing.JTextField();
		this.jTableEscalas = null;
		this.jToolBar1 = new javax.swing.JToolBar();
		this.jButtonInserir = new javax.swing.JButton();
		this.jButtonAlterar = new javax.swing.JButton();
		this.jButtonExcluir = new javax.swing.JButton();
		this.jButtonConfirmar = new javax.swing.JButton();
		this.jButtonCancelar = new javax.swing.JButton();
		this.jTextPane1 = new javax.swing.JTextPane();

		this.jLabelNomeEscala = new javax.swing.JLabel();
		this.jLabelNomeValorEscala = new javax.swing.JLabel();
		this.jTextFieldNomeEscala = new javax.swing.JTextField();
		this.jTextFieldNomeValorEscala = new javax.swing.JTextField();
		this.jLabelValorNumericoValorEscala = new javax.swing.JLabel();
		this.jTextFieldValorNumericoValorEscala = new javax.swing.JTextField();
		this.jTableValorEscala = null;
		this.jToolBar1ValorEscala = new javax.swing.JToolBar();
		this.jButtonInserirValorEscala = new javax.swing.JButton();
		this.jButtonAlterarValorEscala = new javax.swing.JButton();
		this.jButtonExcluirValorEscala = new javax.swing.JButton();
		this.jButtonConfirmarValorEscala = new javax.swing.JButton();
		this.jButtonCancelarValorEscala = new javax.swing.JButton();

		this.jPanelPrincipal = new javax.swing.JPanel();
		this.jPanelPrincipal.setLayout(null);
		this.jPanelPrincipal.setBounds(0, 30, 585, 300);
		this.jPanelPrincipal.setBorder(new javax.swing.border.TitledBorder(
		"Cadastro de Escalas"));

		this.jPanelValores = new javax.swing.JPanel();
		this.jPanelValores.setLayout(null);
		this.jPanelValores.setBounds(0, 30, 585, 300);
		this.jPanelValores.setBorder(new javax.swing.border.TitledBorder(
		"Valores de Escala"));

		class MyDefaultTableModel extends DefaultTableModel {

			public MyDefaultTableModel(Object[] columnNames, int value) {
				super(columnNames, value);
			}

			public boolean isCellEditable(int row, int col) {
				return false;
			}
		}

		this.jTabbedPane.addTab("Principal", this.jPanelPrincipal);
		this.jTabbedPane.addTab("Valores da Escala", this.jPanelValores);
		this.jTabbedPane.setSelectedIndex(0);
		this.jTabbedPane.setBounds(0, 30, 590, 310);
		// adiciona os tabs
		add(this.jTabbedPane);

		// Atribui o layout do jPanel
		setLayout(null);

		// Cria tabela baseada no modelo definido e seta sua posição
		Object[] columnNames = new Object[2];
		columnNames[0] = "Nome";
		columnNames[1] = "Valor Máximo";

		DefaultTableModel tblModel = new MyDefaultTableModel(columnNames, 0);

		this.jTableEscalas = new JTable(tblModel);
		this.jTableEscalas
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.jTableEscalas.setBounds(10, 115, 565, 170);
		this.jTableEscalas.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				selecionaEscala();
			}
		});
		
		ListSelectionModel rowSM = this.jTableEscalas.getSelectionModel();
		   rowSM.addListSelectionListener(new ListSelectionListener() {
		   public void valueChanged(ListSelectionEvent e) {
		    selecionaEscala();	
		   }
		   });
		

		// Cria ScrollPane que contém a tabela
		this.jScrollPane = new JScrollPane();
		this.jScrollPane.setBounds(10, 115, 565, 170);
		this.jScrollPane.getViewport().add(this.jTableEscalas, null);

		this.jTabbedPane.addChangeListener(new ChangeListener() {
			// This method is called whenever the selected tab changes
			public void stateChanged(ChangeEvent evt) {
				JTabbedPane pane = (JTabbedPane) evt.getSource();
				int sel = pane.getSelectedIndex();

				if (sel == 1) {
					carregaListaValoresEscala();
				} else {
					limpaListaValoresEscala();
				}
			}
		});

		// Adiciona scrollpane no jPanel
		this.jPanelPrincipal.add(this.jScrollPane);

		// Label do campo "Nome"
		this.jLabelNome.setText("Nome:");
		this.jPanelPrincipal.add(this.jLabelNome);
		this.jLabelNome.setBounds(160, 30, 30, 20);

		// Campo "Nome"
		this.jPanelPrincipal.add(this.jTextFieldNome);
		this.jTextFieldNome.setBounds(190, 30, 220, 20);
		this.jTextFieldNome.disable();

		// Label do campo "Valor"
		this.jLabelValorMaximo.setText("Valor Máximo:");
		this.jPanelPrincipal.add(this.jLabelValorMaximo);
		this.jLabelValorMaximo.setBounds(122, 60, 68, 20);

		// Campo "Valor"
		this.jPanelPrincipal.add(this.jTextFieldValorMaximo);
		this.jTextFieldValorMaximo.setBounds(190, 60, 220, 20);
		this.jTextFieldValorMaximo.disable();

		// Botão inserir
		this.jButtonInserir.setText("Inserir  ");
		this.jButtonInserir.setIcon(new ImageIcon("adiciona.gif"));
		this.jButtonInserir
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						adicionarEscala();
					}
				});
		this.jToolBar1.add(this.jButtonInserir);

		// Botão alterar
		this.jButtonAlterar.setText("Alterar  ");
		this.jButtonAlterar.setIcon(new ImageIcon("Lapis.gif"));
		this.jButtonAlterar
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						alterarEscala();
					}
				});
		this.jToolBar1.add(this.jButtonAlterar);

		// Botão excluir
		this.jButtonExcluir.setText("Excluir  ");
		this.jButtonExcluir.setIcon(new ImageIcon("Remove.gif"));
		this.jButtonExcluir
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						excluirEscala();
					}
				});
		this.jToolBar1.add(this.jButtonExcluir);

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
		this.jToolBar1.add(this.jButtonConfirmar);

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
		this.jToolBar1.add(this.jButtonCancelar);

		// Adiciona toolbar ao panel
		this.jToolBar1.setFloatable(false);
		this.jPanelPrincipal.add(this.jToolBar1);
		
		this.jToolBar1.setBounds(385, 90, 190, 25);

		// Título do panel
		this.jTextPane1.setBackground(new java.awt.Color(153, 204, 255));
		this.jTextPane1
				.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 18));
		this.jTextPane1.setText("Cadastro de Escalas");
		add(this.jTextPane1);
		this.jTextPane1.setBounds(0, 0, 600, 30);
		this.jTextPane1.disable();

		// Carrega lista cm]om itens do XML
		//carregaListaEscalas();

		/***********************************************************************
		 * Valores de Escala
		 **********************************************************************/
		// Cria tabela baseada no modelo definido e seta sua posição
		Object[] columnNamesValorEscala = new Object[2];
		columnNamesValorEscala[0] = "Nome";
		columnNamesValorEscala[1] = "Valor Numérico";

		DefaultTableModel tblModelValorEscala = new MyDefaultTableModel(
				columnNamesValorEscala, 0);

		this.jTableValorEscala = new JTable(tblModelValorEscala);
		this.jTableValorEscala
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.jTableValorEscala.setBounds(10, 145, 565, 135);
		this.jTableValorEscala
				.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						selecionaValorEscala();
					}
				});

		// Cria ScrollPane que contém a tabela
		this.jScrollPaneValorEscala = new JScrollPane();
		this.jScrollPaneValorEscala.setBounds(10, 145, 565, 135);
		this.jScrollPaneValorEscala.getViewport().add(this.jTableValorEscala,
				null);

		// Adiciona scrollpane no jPanel
		this.jPanelValores.add(this.jScrollPaneValorEscala);

		// Label do campo "Nome Escala"
		this.jLabelNomeEscala.setText("Escala:");
		this.jPanelValores.add(this.jLabelNomeEscala);
		this.jLabelNomeEscala.setBounds(155, 30, 40, 20);

		// Label do campo "Nome"
		this.jLabelNomeValorEscala.setText("Nome:");
		this.jPanelValores.add(this.jLabelNomeValorEscala);
		this.jLabelNomeValorEscala.setBounds(160, 60, 30, 20);

		// Campo "Nome"
		this.jPanelValores.add(this.jTextFieldNomeEscala);
		this.jTextFieldNomeEscala.setBounds(190, 30, 220, 20);
		this.jTextFieldNomeEscala.disable();

		// Campo "Nome"
		this.jPanelValores.add(this.jTextFieldNomeValorEscala);
		this.jTextFieldNomeValorEscala.setBounds(190, 60, 220, 20);
		this.jTextFieldNomeValorEscala.disable();

		// Label do campo "Valor"
		this.jLabelValorNumericoValorEscala.setText("Valor Numérico:");
		this.jPanelValores.add(this.jLabelValorNumericoValorEscala);
		this.jLabelValorNumericoValorEscala.setBounds(113, 90, 77, 20);

		// Campo "Valor"
		this.jPanelValores.add(this.jTextFieldValorNumericoValorEscala);
		this.jTextFieldValorNumericoValorEscala.setBounds(190, 90, 220, 20);
		this.jTextFieldValorNumericoValorEscala.disable();

		// Botão inserir
		this.jButtonInserirValorEscala.setText("Inserir  ");
		this.jButtonInserirValorEscala.setIcon(new ImageIcon("Adiciona.gif"));
		this.jButtonInserirValorEscala
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						adicionarValorEscala();
					}
				});
		this.jToolBar1ValorEscala.add(this.jButtonInserirValorEscala);

		// Botão alterar
		this.jButtonAlterarValorEscala.setText("Alterar  ");
		this.jButtonAlterarValorEscala.setIcon(new ImageIcon("Lapis.gif"));
		this.jButtonAlterarValorEscala
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						alterarValorEscala();
					}
				});
		this.jToolBar1ValorEscala.add(this.jButtonAlterarValorEscala);

		// Botão excluir
		this.jButtonExcluirValorEscala.setText("Excluir  ");
		this.jButtonExcluirValorEscala.setIcon(new ImageIcon("Remove.gif"));
		this.jButtonExcluirValorEscala
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						excluirValorEscala();
					}
				});
		this.jToolBar1ValorEscala.add(this.jButtonExcluirValorEscala);

		// Botão Confirmar
		this.jButtonConfirmarValorEscala.setText("Confirma  ");
		this.jButtonConfirmarValorEscala.setIcon(new ImageIcon("Confirma.gif"));
		this.jButtonConfirmarValorEscala.hide();
		this.jButtonConfirmarValorEscala
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						confirmaOperacaoValorEscala();
					}
				});
		this.jToolBar1ValorEscala.add(this.jButtonConfirmarValorEscala);

		// Botão Confirmar
		this.jButtonCancelarValorEscala.setText("Cancela  ");
		this.jButtonCancelarValorEscala.setIcon(new ImageIcon("Cancela.gif"));
		this.jButtonCancelarValorEscala.hide();
		this.jButtonCancelarValorEscala
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						cancelaOperacaoValorEscala();
					}
				});
		this.jToolBar1ValorEscala.add(this.jButtonCancelarValorEscala);

		// Adiciona toolbar ao panel
		this.jToolBar1ValorEscala.setFloatable(false);
		this.jPanelValores.add(this.jToolBar1ValorEscala);
		this.jToolBar1ValorEscala.setBounds(385, 120, 190, 25);
	}

	protected void adicionarEscala() {
		/* Controle da barra de botões */
		this.jButtonInserir.hide();
		this.jButtonAlterar.hide();
		this.jButtonExcluir.hide();
		this.jButtonConfirmar.show();
		this.jButtonCancelar.show();

		this.jTextFieldNome.enable();
		this.jTextFieldValorMaximo.enable();
		this.jTextFieldNome.setText("");
		this.jTextFieldValorMaximo.setText("");
		this.validate();
		this.repaint();

		this.operacao = "INSERIR";
	}

	protected void alterarEscala() {
		/* Controle da barra de botões */
		int linha = this.jTableEscalas.getSelectedRow();
		if (linha != -1) {
			this.jButtonInserir.hide();
			this.jButtonAlterar.hide();
			this.jButtonExcluir.hide();
			this.jButtonConfirmar.show();
			this.jButtonCancelar.show();

			this.jTextFieldValorMaximo.enable();
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
		this.jTextFieldValorMaximo.disable();
		this.validate();
		this.repaint();
	}

	protected void confirmaAdicionarEscala() {
		Object[] linha = new Object[2];
		linha[0] = this.jTextFieldNome.getText();
		linha[1] = this.jTextFieldValorMaximo.getText();

		if ((this.jTextFieldNome.getText().length() == 0)
				|| (this.jTextFieldValorMaximo.getText().length() == 0)) {
			JOptionPane.showMessageDialog(this,
					"Todos os campos devem ser preenchidos.", "Alerta",
					JOptionPane.WARNING_MESSAGE);
		} else {
			int retorno = JanelaPrincipal.getAplicacao().inserirEscala(
					this.jTextFieldNome.getText(),
					this.jTextFieldValorMaximo.getText());

			switch (retorno) {
			case 0:
				((DefaultTableModel) this.jTableEscalas.getModel())
						.addRow(linha);
				this.gerenciaBotoesConfirma();
				this.jTableEscalas.setRowSelectionInterval(this.jTableEscalas
						.getRowCount() - 1,
						this.jTableEscalas.getRowCount() - 1);
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
						"O campo valor máximo deve ser maior que zero.",
						"Alerta", JOptionPane.WARNING_MESSAGE);
				break;
			case -3:
				JOptionPane.showMessageDialog(this,
						"O campo valor máximo deve ser um inteiro.", "Alerta",
						JOptionPane.WARNING_MESSAGE);
				break;
			}
		}
	}

	protected void confirmaOperacao() {
		if (this.operacao.compareToIgnoreCase("INSERIR") == 0) {
			this.confirmaAdicionarEscala();
		}
		if (this.operacao.compareToIgnoreCase("ALTERAR") == 0) {
			this.confirmaAlterarEscala();
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
		this.jTextFieldValorMaximo.disable();

		this.selecionaEscala();

		this.validate();
		this.repaint();
	}

	protected void confirmaAlterarEscala() {
		Object[] linha = new Object[2];
		linha[0] = this.jTextFieldNome.getText();
		linha[1] = this.jTextFieldValorMaximo.getText();

		if ((this.jTextFieldNome.getText().length() == 0)
				|| (this.jTextFieldValorMaximo.getText().length() == 0)) {
			JOptionPane.showMessageDialog(this,
					"Todos os campos devem ser preenchidos.", "Alerta",
					JOptionPane.WARNING_MESSAGE);
		} else {
			int linhaSelecionada = this.jTableEscalas.getSelectedRow();
			if (linhaSelecionada != -1) {
				int retorno = JanelaPrincipal.getAplicacao().alterarEscala(
						this.jTextFieldNome.getText(),
						this.jTextFieldValorMaximo.getText());

				switch (retorno) {
				case 0:
					((DefaultTableModel) this.jTableEscalas.getModel())
							.setValueAt(linha[0], linhaSelecionada, 0);
					((DefaultTableModel) this.jTableEscalas.getModel())
							.setValueAt(linha[1], linhaSelecionada, 1);
					this.gerenciaBotoesConfirma();
					break;
				case -1:
					JOptionPane.showMessageDialog(this,
							"O campo valor máximo deve ser maior que zero.",
							"Alerta", JOptionPane.WARNING_MESSAGE);
					break;
				case -2:
					JOptionPane.showMessageDialog(this,
							"Valor Máximo deve ser um inteiro.", "Alerta",
							JOptionPane.WARNING_MESSAGE);
					break;
				case -3:
					JOptionPane
							.showMessageDialog(
									this,
									"Não é possível atirbuir este valor máximo, pois já foi atribuído valor maior a alguma característica que utiliza esta escala em algum profissional.",
									"Alerta", JOptionPane.WARNING_MESSAGE);
					break;
				case -4:
					JOptionPane
							.showMessageDialog(
									this,
									"Não é possível atirbuir este valor máximo, pois já foi atribuído valor maior a alguma característica que utiliza esta escala em alguma atividade.",
									"Alerta", JOptionPane.WARNING_MESSAGE);
					break;
				}
			}
		}
	}

	protected void selecionaEscala() {
		int linhaSelecionada = this.jTableEscalas.getSelectedRow();
		if (linhaSelecionada != -1) {
			this.jTextFieldNome
					.setText((String) ((DefaultTableModel) this.jTableEscalas
							.getModel()).getValueAt(linhaSelecionada, 0));
			this.jTextFieldValorMaximo
					.setText((String) ((DefaultTableModel) this.jTableEscalas
							.getModel()).getValueAt(linhaSelecionada, 1));
			this.jTextFieldNomeEscala
					.setText((String) ((DefaultTableModel) this.jTableEscalas
							.getModel()).getValueAt(linhaSelecionada, 0));
		}
	}

	protected void excluirEscala() {
		int linha = this.jTableEscalas.getSelectedRow();
		if (linha != -1) {

			int retorno = JanelaPrincipal.getAplicacao().excluirEscala(
					this.jTextFieldNome.getText());

			if (retorno == 0) {
				((DefaultTableModel) this.jTableEscalas.getModel())
						.removeRow(this.jTableEscalas.getSelectedRow());
				if (linha > 0) {
					this.jTableEscalas.setRowSelectionInterval(linha - 1,
							linha - 1);
				} else {
					if (this.jTableEscalas.getRowCount() > 0) {
						this.jTableEscalas
								.setRowSelectionInterval(linha, linha);
					} else {
						this.jTextFieldNome.setText("");
						this.jTextFieldValorMaximo.setText("");
					}
				}
				this.selecionaEscala();
			} else {
				if (retorno == -1) {
					JOptionPane
							.showMessageDialog(
									this,
									"Não é possível excluir, pois já foi atribuído valor a alguma característica que utiliza esta escala em algum profissional.",
									"Alerta", JOptionPane.WARNING_MESSAGE);
				}
				if (retorno == -2) {
					JOptionPane
							.showMessageDialog(
									this,
									"Não é possível excluir, pois já foi atribuído valor a alguma característica que utiliza esta escala em alguma atividade.",
									"Alerta", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}

	/***************************************************************************
	 * Métodos de Valor Escala
	 **************************************************************************/
	protected void adicionarValorEscala() {
		/* Controle da barra de botões */
		this.jButtonInserirValorEscala.hide();
		this.jButtonAlterarValorEscala.hide();
		this.jButtonExcluirValorEscala.hide();
		this.jButtonConfirmarValorEscala.show();
		this.jButtonCancelarValorEscala.show();

		this.jTextFieldNomeValorEscala.enable();
		this.jTextFieldValorNumericoValorEscala.enable();
		this.jTextFieldNomeValorEscala.setText("");
		this.jTextFieldValorNumericoValorEscala.setText("");
		this.validate();
		this.repaint();

		this.operacaoValorEscala = "INSERIR";
	}

	protected void alterarValorEscala() {
		/* Controle da barra de botões */
		int linha = this.jTableEscalas.getSelectedRow();
		if (linha != -1) {
			this.jButtonInserirValorEscala.hide();
			this.jButtonAlterarValorEscala.hide();
			this.jButtonExcluirValorEscala.hide();
			this.jButtonConfirmarValorEscala.show();
			this.jButtonCancelarValorEscala.show();

			this.jTextFieldNomeValorEscala.enable();
			this.validate();
			this.repaint();

			this.operacaoValorEscala = "ALTERAR";
		}
	}

	private void gerenciaBotoesConfirmaValorEscala() {
		/* Controle da barra de botões */
		this.jButtonInserirValorEscala.show();
		this.jButtonAlterarValorEscala.show();
		this.jButtonExcluirValorEscala.show();
		this.jButtonConfirmarValorEscala.hide();
		this.jButtonCancelarValorEscala.hide();

		this.jTextFieldNomeValorEscala.disable();
		this.jTextFieldValorNumericoValorEscala.disable();
		this.validate();
		this.repaint();
	}

	protected void confirmaOperacaoValorEscala() {
		if (this.operacaoValorEscala.compareToIgnoreCase("INSERIR") == 0) {
			this.confirmaAdicionarValorEscala();
		}
		if (this.operacaoValorEscala.compareToIgnoreCase("ALTERAR") == 0) {
			this.confirmaAlterarValorEscala();
		}
	}

	protected void cancelaOperacaoValorEscala() {
		/* Controle da barra de botões */
		this.jButtonInserirValorEscala.show();
		this.jButtonAlterarValorEscala.show();
		this.jButtonExcluirValorEscala.show();
		this.jButtonConfirmarValorEscala.hide();
		this.jButtonCancelarValorEscala.hide();

		this.jTextFieldNomeValorEscala.disable();
		this.jTextFieldValorNumericoValorEscala.disable();

		this.selecionaValorEscala();

		this.validate();
		this.repaint();
	}

	protected void selecionaValorEscala() {
		int linhaSelecionada = this.jTableValorEscala.getSelectedRow();
		if (linhaSelecionada != -1) {
			this.jTextFieldNomeValorEscala
					.setText((String) ((DefaultTableModel) this.jTableValorEscala
							.getModel()).getValueAt(linhaSelecionada, 0));
			this.jTextFieldValorNumericoValorEscala
					.setText((String) ((DefaultTableModel) this.jTableValorEscala
							.getModel()).getValueAt(linhaSelecionada, 1));
		}
	}

	protected void excluirValorEscala() {
		JOptionPane
				.showMessageDialog(
						this,
						"Não se pode excluir um valor de escala. Caso deseje, é possível diminuir o valor máximo da escala, e os valores que excederem o novo valor máximo serão excluídos automaticamente.",
						"Alerta", JOptionPane.WARNING_MESSAGE);
	}

	protected void confirmaAdicionarValorEscala() {
		Object[] linha = new Object[2];
		linha[0] = this.jTextFieldNomeValorEscala.getText();
		linha[1] = this.jTextFieldValorNumericoValorEscala.getText();

		if ((this.jTextFieldNomeValorEscala.getText().length() == 0)
				|| (this.jTextFieldValorNumericoValorEscala.getText().length() == 0)) {
			JOptionPane.showMessageDialog(this,
					"Todos os campos devem ser preenchidos.", "Alerta",
					JOptionPane.WARNING_MESSAGE);
		} else {
			int retorno = JanelaPrincipal.getAplicacao().inserirValorEscala(
					this.jTextFieldNomeEscala.getText(),
					this.jTextFieldNomeValorEscala.getText(),
					this.jTextFieldValorNumericoValorEscala.getText());

			switch (retorno) {
			case 0:
				((DefaultTableModel) this.jTableValorEscala.getModel())
						.addRow(linha);
				this.gerenciaBotoesConfirmaValorEscala();
				this.jTableValorEscala.setRowSelectionInterval(
						this.jTableValorEscala.getRowCount() - 1,
						this.jTableValorEscala.getRowCount() - 1);
				break;
			case -1:
				JOptionPane.showMessageDialog(this,
						"Valor deve ser um inteiro.", "Alerta",
						JOptionPane.WARNING_MESSAGE);
				break;
			case -2:
				JOptionPane.showMessageDialog(this,
						"Já existe um item da escala com este valor.",
						"Alerta", JOptionPane.WARNING_MESSAGE);
				break;
			case -3:
				JOptionPane.showMessageDialog(this,
						"Valor é maior que valor máximo definido na escala.",
						"Alerta", JOptionPane.WARNING_MESSAGE);
				break;
			case -4:
				JOptionPane.showMessageDialog(this,
						"Valor deve ser maior que zero.", "Alerta",
						JOptionPane.WARNING_MESSAGE);
				break;

			}
		}
	}

	protected void confirmaAlterarValorEscala() {
		Object[] linha = new Object[2];
		linha[0] = this.jTextFieldNomeValorEscala.getText();
		linha[1] = this.jTextFieldValorNumericoValorEscala.getText();

		if ((this.jTextFieldNomeValorEscala.getText().length() == 0)
				|| (this.jTextFieldValorNumericoValorEscala.getText().length() == 0)) {
			JOptionPane.showMessageDialog(this,
					"Todos os campos devem ser preenchidos.", "Alerta",
					JOptionPane.WARNING_MESSAGE);
		} else {
			int linhaSelecionada = this.jTableValorEscala.getSelectedRow();
			if (linhaSelecionada != -1) {
				int retorno = JanelaPrincipal.getAplicacao()
						.alterarValorEscala(
								this.jTextFieldNomeEscala.getText(),
								this.jTextFieldNomeValorEscala.getText(),
								this.jTextFieldValorMaximo.getText());

				switch (retorno) {
				case 0:
					((DefaultTableModel) this.jTableValorEscala.getModel())
							.setValueAt(linha[0], linhaSelecionada, 0);
					((DefaultTableModel) this.jTableValorEscala.getModel())
							.setValueAt(linha[1], linhaSelecionada, 1);
					this.gerenciaBotoesConfirmaValorEscala();
					break;
				case -1:
					JOptionPane
							.showMessageDialog(
									this,
									"Não é possível inserir dois itens com o mesmo nome.",
									"Alerta", JOptionPane.WARNING_MESSAGE);
					break;
				}
			}
		}
	}

	protected void carregaListaValoresEscala() {
		int linhaSelecionada = this.jTableEscalas.getSelectedRow();
		Object[] linha = new Object[2];
		ArrayList listaValores = new ArrayList();
		String nomeEscala = "";
		if (linhaSelecionada != -1) {
			nomeEscala = (String) ((DefaultTableModel) this.jTableEscalas
					.getModel()).getValueAt(linhaSelecionada, 0);
			this.jTextFieldNomeEscala.setText(nomeEscala);
			listaValores = JanelaPrincipal.getAplicacao().obterValoresEscala(
					nomeEscala);

			Iterator iter1 = listaValores.iterator();
			while (iter1.hasNext()) {
				ValorEscala ve = (ValorEscala) iter1.next();
				linha[0] = ve.getNome();
				linha[1] = ve.getValorNumerico() + "";

				((DefaultTableModel) this.jTableValorEscala.getModel())
						.addRow(linha);
			}
			if (listaValores.size() > 0) {
				this.jTableValorEscala.setRowSelectionInterval(0, 0);
				selecionaValorEscala();
			}
		}
	}

	protected void carregaListaEscalas() {
		Object[] linha = new Object[2];
		Collection listaEscalas = new ArrayList();

		listaEscalas = JanelaPrincipal.getAplicacao().getEscalas().values();

		Iterator iter1 = listaEscalas.iterator();
		while (iter1.hasNext()) {
			Escala e = (Escala) iter1.next();
			linha[0] = e.getNome();
			linha[1] = e.getValorMaximo() + "";

			((DefaultTableModel) this.jTableEscalas.getModel()).addRow(linha);
		}
		if (listaEscalas.size() > 0) {
			this.jTableEscalas.setRowSelectionInterval(0, 0);
			selecionaEscala();
		}
	}

	protected void limpaListaValoresEscala() {
		try {
			this.jTextFieldNomeValorEscala.setText("");
			this.jTextFieldValorNumericoValorEscala.setText("");

			while (((DefaultTableModel) this.jTableValorEscala.getModel())
					.getRowCount() > 0) {
				((DefaultTableModel) this.jTableValorEscala.getModel())
						.removeRow(0);
			}
		} catch (Exception e) {
		}
	}
	
	// Variables declaration - do not modify

	private String operacao = "";

	private javax.swing.JPanel jPanelPrincipal;

	private javax.swing.JButton jButtonInserir;

	private javax.swing.JButton jButtonAlterar;

	private javax.swing.JButton jButtonExcluir;

	private javax.swing.JButton jButtonConfirmar;

	private javax.swing.JButton jButtonCancelar;

	private javax.swing.JLabel jLabelNome;

	private javax.swing.JLabel jLabelValorMaximo;

	private JTable jTableEscalas;

	private javax.swing.JTextField jTextFieldNome;

	private javax.swing.JTextField jTextFieldValorMaximo;

	private javax.swing.JTextPane jTextPane1;

	private javax.swing.JToolBar jToolBar1;

	private JScrollPane jScrollPane;

	private JTabbedPane jTabbedPane;

	// Valores da Escala
	private String operacaoValorEscala = "";

	private javax.swing.JPanel jPanelValores;

	private javax.swing.JButton jButtonInserirValorEscala;

	private javax.swing.JButton jButtonAlterarValorEscala;

	private javax.swing.JButton jButtonExcluirValorEscala;

	private javax.swing.JButton jButtonConfirmarValorEscala;

	private javax.swing.JButton jButtonCancelarValorEscala;

	private javax.swing.JLabel jLabelNomeEscala;

	private javax.swing.JLabel jLabelNomeValorEscala;

	private javax.swing.JLabel jLabelValorNumericoValorEscala;

	private JTable jTableValorEscala;

	private javax.swing.JTextField jTextFieldNomeEscala;

	private javax.swing.JTextField jTextFieldNomeValorEscala;

	private javax.swing.JTextField jTextFieldValorNumericoValorEscala;

	private javax.swing.JToolBar jToolBar1ValorEscala;

	private JScrollPane jScrollPaneValorEscala;
	// End of variables declaration

}
