
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 * Title:        Protótipo Tese - CadastroProfissionais
 * Description:  Implementação do protótipo da tese. Classe CadastroProfissionais
 * Copyright:    Copyright (c) 2004
 * Company:      COPPE Sistemas
 * @author Ahilton S. Barreto
 * @version 1.0
 * Criado em 10/02/2005
 */

/**
 * @author Ahilton
 * 
 * Esta classe contém a janela do cadastro de profisisonais
 */
public class CadastroProfissionais extends javax.swing.JPanel {

	/** Creates new form CadastroCaracteristicas */
	public CadastroProfissionais() {
		initComponents();
	}

	private void initComponents() {
		// Atribui o tamanho do jPanel
		this.setSize(600, 400);
		// Atribui o layout do jPanel
		this.setLayout(null);

		// Inicializa os componentes (novo)
		this.jTabbedPane = new JTabbedPane();

		this.jPanelPrincipal = new javax.swing.JPanel();
		this.jPanelPrincipal.setLayout(null);
		this.jPanelPrincipal.setBounds(0, 30, 585, 300);
		this.jPanelPrincipal.setBorder(new javax.swing.border.TitledBorder(
		"Cadastro de Profissionais"));

		this.jPanelCaracteristicas = new javax.swing.JPanel();
		this.jPanelCaracteristicas.setLayout(null);
		this.jPanelCaracteristicas.setBounds(0, 30, 585, 300);
		this.jPanelCaracteristicas.setBorder(new javax.swing.border.TitledBorder(
		"Características de Profissional"));

		this.jPanelPeriodos = new javax.swing.JPanel();
		this.jPanelPeriodos.setLayout(null);
		this.jPanelPeriodos.setBounds(0, 30, 585, 300);
		this.jPanelPeriodos.setBorder(new javax.swing.border.TitledBorder(
		"Períodos de Indisponibilidade do Profissional"));

		this.jTabbedPane.addChangeListener(new ChangeListener() {
			// This method is called whenever the selected tab changes
			public void stateChanged(ChangeEvent evt) {
				JTabbedPane pane = (JTabbedPane) evt.getSource();
				int sel = pane.getSelectedIndex();

				if (sel == 1) {
					carregaListaCaracteristicas();
				} else {
					limpaListaCaracteristicas();
				}
				if (sel == 2) {
					carregaListaPeriodos();
				} else {
					limpaListaPeriodos();
				}
			}
		});

		this.jTabbedPane.addTab("Principal", this.jPanelPrincipal);
		this.jTabbedPane.addTab("Características Possuídas",
				this.jPanelCaracteristicas);
		this.jTabbedPane.addTab("Períodos de Indisponibilidade",
				this.jPanelPeriodos);
		this.jTabbedPane.setSelectedIndex(0);
		this.jTabbedPane.setBounds(0, 30, 590, 310);
		// adiciona os tabs
		add(this.jTabbedPane);

		// Inicializa os componentes
		this.jLabelNomeProfissional = new javax.swing.JLabel();
		this.jTextFieldNomeProfissional = new javax.swing.JTextField();
		this.jLabelValorHoraProfissional = new javax.swing.JLabel();
		this.jLabelQtdHorasDiaProfissional = new javax.swing.JLabel();
		this.jTextFieldValorHoraProfissional = new javax.swing.JTextField();
		this.jTextFieldQtdHorasDiaProfissional = new javax.swing.JTextField();
		this.jTableProfissional = null;
		this.jToolBarPrincipal = new javax.swing.JToolBar();
		this.jButtonIncluirProfissional = new javax.swing.JButton();
		this.jButtonAlterarProfissional = new javax.swing.JButton();
		this.jButtonExcluirProfissional = new javax.swing.JButton();
		this.jButtonConfirmarProfissional = new javax.swing.JButton();
		this.jButtonCancelarProfissional = new javax.swing.JButton();
		this.jTextPaneTituloTela = new javax.swing.JTextPane();
		this.jScrollPanePrincipal = new JScrollPane();
		// Tab Períodos
		this.jLabelProfissionalPeriodo = new javax.swing.JLabel();
		this.jLabelDtIni = new javax.swing.JLabel();
		this.jLabelDtFim = new javax.swing.JLabel();
		this.jLabelNumHoras = new javax.swing.JLabel();
		this.jTextFieldProfissionalPeriodo = new javax.swing.JTextField();
		this.jTextFieldNumHoras = new javax.swing.JTextField();
		this.jButtonIncluirPeriodo = new javax.swing.JButton();
		this.jButtonAlterarPeriodo = new javax.swing.JButton();
		this.jButtonExcluirPeriodo = new javax.swing.JButton();
		this.jButtonConfirmarPeriodo = new javax.swing.JButton();
		this.jButtonCancelarPeriodo = new javax.swing.JButton();
		this.jToolBarPeriodo = new javax.swing.JToolBar();
		this.jScrollPanePeriodo = new JScrollPane();
		this.jTablePeriodo = null;
		this.jDatePickerDtIni = new JDatePicker();
		this.jDatePickerDtFim = new JDatePicker();
		// Tab Caracteristicas
		this.jLabelProfissionalCaracteristica = new javax.swing.JLabel();
		this.jLabelNomeCaracteristica = new javax.swing.JLabel();
		this.jLabelValorCaracteristica = new javax.swing.JLabel();
		this.jLabelTipoCaracteristica = new javax.swing.JLabel();
		this.jTextFieldProfissionalCaracteristica = new javax.swing.JTextField();
		this.jComboboxNomeCaracteristica = new JComboBox();
		this.jComboboxValorCaracteristica = new JComboBox();
		this.jComboboxTipoCaracteristica = new JComboBox();
		this.jButtonIncluirCaracteristica = new javax.swing.JButton();
		this.jButtonAlterarCaracteristica = new javax.swing.JButton();
		this.jButtonExcluirCaracteristica = new javax.swing.JButton();
		this.jButtonConfirmarCaracteristica = new javax.swing.JButton();
		this.jButtonCancelarCaracteristica = new javax.swing.JButton();
		this.jToolBarCaracteristica = new javax.swing.JToolBar();
		this.jScrollPaneCaracteristica = new JScrollPane();
		this.jTableCaracteristica = null;

		class MyDefaultTableModel extends DefaultTableModel {

			public MyDefaultTableModel(Object[] columnNames, int value) {
				super(columnNames, value);
			}

			public boolean isCellEditable(int row, int col) {
				return false;
			}
		}

		// Cria tabela baseada no modelo definido e seta sua posição
		Object[] columnNames = new Object[3];
		columnNames[0] = "Nome";
		columnNames[1] = "Valor Hora";
		columnNames[2] = "Qtd. Horas/Dia";

		DefaultTableModel tblModel = new MyDefaultTableModel(columnNames, 0);

		this.jTableProfissional = new javax.swing.JTable(tblModel);
		this.jTableProfissional.setBounds(10, 145, 565, 140);
		this.jTableProfissional
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.jTableProfissional
				.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						selecionaProfissional();
					}
				});

		ListSelectionModel rowSM = this.jTableProfissional.getSelectionModel();
		   rowSM.addListSelectionListener(new ListSelectionListener() {
		   public void valueChanged(ListSelectionEvent e) {
		   	selecionaProfissional();	
		   }
		   });
		
		// ScrollPane que contém a tabela
		this.jScrollPanePrincipal.setBounds(10, 145, 565, 140);
		this.jScrollPanePrincipal.getViewport().add(this.jTableProfissional,
				null);

		// Adiciona scrollpane no jPanel
		this.jPanelPrincipal.add(this.jScrollPanePrincipal);

		// Label do campo "Nome"
		this.jLabelNomeProfissional.setText("Nome:");
		this.jPanelPrincipal.add(this.jLabelNomeProfissional);
		this.jLabelNomeProfissional.setBounds(160, 30, 30, 20);

		// Campo "Nome"
		this.jPanelPrincipal.add(this.jTextFieldNomeProfissional);
		this.jTextFieldNomeProfissional.setBounds(190, 30, 220, 20);
		this.jTextFieldNomeProfissional.disable();

		// Label do campo "Valor Hora"
		this.jLabelValorHoraProfissional.setText("Valor Hora:");
		this.jPanelPrincipal.add(this.jLabelValorHoraProfissional);
		this.jLabelValorHoraProfissional.setBounds(135, 60, 75, 20);

		// Campo "Valor Hora"
		this.jPanelPrincipal.add(this.jTextFieldValorHoraProfissional);
		this.jTextFieldValorHoraProfissional.setBounds(190, 60, 220, 20);
		this.jTextFieldValorHoraProfissional.disable();

		// Campo "Qtd Horas Dia"
		this.jPanelPrincipal.add(this.jTextFieldQtdHorasDiaProfissional);
		this.jTextFieldQtdHorasDiaProfissional.setBounds(190, 90, 220, 20);
		this.jTextFieldQtdHorasDiaProfissional.disable();

		// Label do campo "Qtd Horas Dia"
		this.jLabelQtdHorasDiaProfissional.setText("Qtd. Horas/Dia:");
		this.jPanelPrincipal.add(this.jLabelQtdHorasDiaProfissional);
		this.jLabelQtdHorasDiaProfissional.setBounds(117, 90, 93, 20);

		// Botão inserir
		this.jButtonIncluirProfissional.setText("Inserir  ");
		this.jButtonIncluirProfissional.setIcon(new ImageIcon("Adiciona.gif"));
		this.jButtonIncluirProfissional
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						adicionarProfissional();
					}
				});
		this.jToolBarPrincipal.add(this.jButtonIncluirProfissional);

		// Botão alterar
		this.jButtonAlterarProfissional.setText("Alterar  ");
		this.jButtonAlterarProfissional.setIcon(new ImageIcon("Lapis.gif")); 
		this.jButtonAlterarProfissional
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						alterarProfissional();
					}
				});
		this.jToolBarPrincipal.add(this.jButtonAlterarProfissional);

		// Botão excluir
		this.jButtonExcluirProfissional.setText("Excluir  ");
		this.jButtonExcluirProfissional.setIcon(new ImageIcon("Remove.gif"));
		this.jButtonExcluirProfissional
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						excluirProfissional();
					}
				});
		this.jToolBarPrincipal.add(this.jButtonExcluirProfissional);

		// Botão confirmar
		this.jButtonConfirmarProfissional.setText("Confirma  ");
		this.jButtonConfirmarProfissional.setIcon(new ImageIcon("Confirma.gif"));
		this.jButtonConfirmarProfissional.hide();
		this.jButtonConfirmarProfissional
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						confirmaOperacao();
					}
				});
		this.jToolBarPrincipal.add(this.jButtonConfirmarProfissional);

		// Botão cancelar
		this.jButtonCancelarProfissional.setText("Cancela  ");
		this.jButtonCancelarProfissional.setIcon(new ImageIcon("Cancela.gif"));
		this.jButtonCancelarProfissional.hide();
		this.jButtonCancelarProfissional
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						cancelaOperacao();
					}
				});
		this.jToolBarPrincipal.add(this.jButtonCancelarProfissional);

		// Adiciona toolbar ao panel
		this.jToolBarPrincipal.setFloatable(false);
		this.jPanelPrincipal.add(this.jToolBarPrincipal);
		this.jToolBarPrincipal.setBounds(385, 120, 190, 25);

		// Título do panel
		this.jTextPaneTituloTela
				.setBackground(new java.awt.Color(153, 204, 255));
		this.jTextPaneTituloTela.disable();
		this.jTextPaneTituloTela.setFont(new java.awt.Font(
				"Microsoft Sans Serif", 1, 18));
		this.jTextPaneTituloTela.setText("Cadastro de Profissionais");
		add(this.jTextPaneTituloTela);
		this.jTextPaneTituloTela.setBounds(0, 0, 600, 30);

		/***********************************************************************
		 * Tratamento da TAB Características
		 **********************************************************************/
		Collection tipos = JanelaPrincipal.getAplicacao()
				.getTiposDefinicaoCaracteristica();
		Iterator iter = tipos.iterator();
		while (iter.hasNext()) {
			TipoDefinicaoCaracteristica tipo = (TipoDefinicaoCaracteristica) iter
					.next();
			this.jComboboxTipoCaracteristica.addItem(tipo);
		}
		this.jComboboxTipoCaracteristica
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						preencheNomesCaracteristica();
					}
				});

		this.jComboboxNomeCaracteristica
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						preencheValoresCaracteristica();
					}
				});

		// Cria tabela baseada no modelo definido e seta sua posição
		Object[] columnNamesCaracteristicas = new Object[3];
		columnNamesCaracteristicas[0] = "Característica";
		columnNamesCaracteristicas[1] = "Valor";
		columnNamesCaracteristicas[2] = "Tipo";

		DefaultTableModel tblModelCaracteristica = new MyDefaultTableModel(
				columnNamesCaracteristicas, 0);

		this.jTableCaracteristica = new javax.swing.JTable(
				tblModelCaracteristica);
		this.jTableCaracteristica.setBounds(10, 165, 565, 115);
		this.jTableCaracteristica
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.jTableCaracteristica
				.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						selecionaCaracteristica();
					}
				});
		
		ListSelectionModel rowSM2 = this.jTableCaracteristica.getSelectionModel();
		rowSM2.addListSelectionListener(new ListSelectionListener() {
		   public void valueChanged(ListSelectionEvent e) {
		    selecionaCaracteristica();	
		   }
		   });

		// ScrollPane que contém a tabela
		this.jScrollPaneCaracteristica.setBounds(10, 165, 565, 115);
		this.jScrollPaneCaracteristica.getViewport().add(
				this.jTableCaracteristica, null);

		// Adiciona scrollpane no jPanel
		this.jPanelCaracteristicas.add(this.jScrollPaneCaracteristica);

		// Label do campo "Profissional"
		this.jLabelProfissionalCaracteristica.setText("Profissional:");
		this.jPanelCaracteristicas.add(this.jLabelProfissionalCaracteristica);
		this.jLabelProfissionalCaracteristica.setBounds(130, 20, 60, 20);

		// Campo "Profissional"
		this.jPanelCaracteristicas
				.add(this.jTextFieldProfissionalCaracteristica);
		this.jTextFieldProfissionalCaracteristica.setBounds(190, 20, 240, 20);
		this.jTextFieldProfissionalCaracteristica.disable();

		// Label do campo "Tipo de Característica"
		this.jLabelTipoCaracteristica.setText("Tipo Característica:");
		this.jPanelCaracteristicas.add(this.jLabelTipoCaracteristica);
		this.jLabelTipoCaracteristica.setBounds(95, 50, 100, 20);

		// Campo "Tipo Caracteristica"
		this.jPanelCaracteristicas.add(this.jComboboxTipoCaracteristica);
		this.jComboboxTipoCaracteristica.setBounds(190, 50, 240, 20);
		this.jComboboxTipoCaracteristica.disable();

		// Label do campo "Característica"
		this.jLabelNomeCaracteristica.setText("Característica:");
		this.jPanelCaracteristicas.add(this.jLabelNomeCaracteristica);
		this.jLabelNomeCaracteristica.setBounds(120, 80, 70, 20);

		// Campo "Característica"
		this.jPanelCaracteristicas.add(this.jComboboxNomeCaracteristica);
		this.jComboboxNomeCaracteristica.setBounds(190, 80, 240, 20);
		this.jComboboxNomeCaracteristica.disable();

		// Label do campo "Valor"
		this.jLabelValorCaracteristica.setText("Valor:");
		this.jPanelCaracteristicas.add(this.jLabelValorCaracteristica);
		this.jLabelValorCaracteristica.setBounds(160, 110, 30, 20);

		// Campo "Valor"
		this.jPanelCaracteristicas.add(this.jComboboxValorCaracteristica);
		this.jComboboxValorCaracteristica.setBounds(190, 110, 240, 20);
		this.jComboboxValorCaracteristica.disable();

		// Botão inserir
		this.jButtonIncluirCaracteristica.setText("Inserir  ");
		this.jButtonIncluirCaracteristica.setIcon(new ImageIcon("Adiciona.gif"));
		this.jButtonIncluirCaracteristica
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						adicionarCaracteristica();
					}
				});
		this.jToolBarCaracteristica.add(this.jButtonIncluirCaracteristica);

		// Botão alterar
		this.jButtonAlterarCaracteristica.setText("Alterar  ");
		this.jButtonAlterarCaracteristica.setIcon(new ImageIcon("Lapis.gif"));
		this.jButtonAlterarCaracteristica
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						alterarCaracteristica();
					}
				});
		this.jToolBarCaracteristica.add(this.jButtonAlterarCaracteristica);

		// Botão excluir
		this.jButtonExcluirCaracteristica.setText("Excluir  ");
		this.jButtonExcluirCaracteristica.setIcon(new ImageIcon("Remove.gif"));
		this.jButtonExcluirCaracteristica
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						excluirCaracteristica();
					}
				});
		this.jToolBarCaracteristica.add(this.jButtonExcluirCaracteristica);

		// Botão confirmar
		this.jButtonConfirmarCaracteristica.setText("Confirma  ");
		this.jButtonConfirmarCaracteristica.setIcon(new ImageIcon("Confirma.gif"));
		this.jButtonConfirmarCaracteristica.hide();
		this.jButtonConfirmarCaracteristica
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						confirmaOperacaoCaracteristica();
					}
				});
		this.jToolBarCaracteristica.add(this.jButtonConfirmarCaracteristica);

		// Botão cancelar
		this.jButtonCancelarCaracteristica.setText("Cancela  ");
		this.jButtonCancelarCaracteristica.setIcon(new ImageIcon("Cancela.gif"));
		this.jButtonCancelarCaracteristica.hide();
		this.jButtonCancelarCaracteristica
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						cancelaOperacaoCaracteristica();
					}
				});
		this.jToolBarCaracteristica.add(this.jButtonCancelarCaracteristica);
		this.jToolBarCaracteristica.setFloatable(false);
		// Adiciona toolbar ao panel
		this.jPanelCaracteristicas.add(this.jToolBarCaracteristica);
		this.jToolBarCaracteristica.setBounds(385, 140, 190, 25);

		/***********************************************************************
		 * Tratamento da TAB Períodos de Indisponibilidade
		 **********************************************************************/
		// Cria tabela baseada no modelo definido e seta sua posição
		Object[] columnNamesPeriodos = new Object[3];
		columnNamesPeriodos[0] = "Data Inicial";
		columnNamesPeriodos[1] = "Data Final";
		columnNamesPeriodos[2] = "Núm. Horas";

		DefaultTableModel tblModelPeriodo = new MyDefaultTableModel(
				columnNamesPeriodos, 0);

		this.jTablePeriodo = new javax.swing.JTable(tblModelPeriodo);
		this.jTablePeriodo.setBounds(10, 165, 565, 115);
		this.jTablePeriodo
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.jTablePeriodo.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				selecionaPeriodo();
			}
		});
		
		ListSelectionModel rowSM3 = this.jTablePeriodo.getSelectionModel();
		rowSM3.addListSelectionListener(new ListSelectionListener() {
		   public void valueChanged(ListSelectionEvent e) {
		   	selecionaPeriodo();	
		   }
		   });

		// ScrollPane que contém a tabela
		this.jScrollPanePeriodo.setBounds(10, 165, 565, 115);
		this.jScrollPanePeriodo.getViewport().add(this.jTablePeriodo, null);

		// Adiciona scrollpane no jPanel
		this.jPanelPeriodos.add(this.jScrollPanePeriodo);

		// Label do campo "Profissional"
		this.jLabelProfissionalPeriodo.setText("Profissional:");
		this.jPanelPeriodos.add(this.jLabelProfissionalPeriodo);
		this.jLabelProfissionalPeriodo.setBounds(130, 20, 60, 20);

		// Campo "Profissional"
		this.jPanelPeriodos.add(this.jTextFieldProfissionalPeriodo);
		this.jTextFieldProfissionalPeriodo.setBounds(190, 20, 220, 20);
		this.jTextFieldProfissionalPeriodo.disable();

		// Label do campo "Data Inicial"
		this.jLabelDtIni.setText("Data Inicial:");
		this.jPanelPeriodos.add(this.jLabelDtIni);
		this.jLabelDtIni.setBounds(135, 50, 55, 20);

		// Campo "Data Inicial"
		this.jPanelPeriodos.add(this.jDatePickerDtIni);
		this.jDatePickerDtIni.setBounds(190, 50, 220, 20);
		this.jDatePickerDtIni.setEnabled(false);
		this.jDatePickerDtIni.setColorDisabled(this.getBackground());

		// this.jPanelPeriodos.add(this.jTextFieldDtIni);
		// this.jTextFieldDtIni.setBounds(160, 50, 220, 20);

		// Label do campo "Data Final"
		this.jLabelDtFim.setText("Data Final:");
		this.jPanelPeriodos.add(this.jLabelDtFim);
		this.jLabelDtFim.setBounds(140, 80, 50, 20);

		// Campo "Data Final"
		this.jPanelPeriodos.add(this.jDatePickerDtFim);
		this.jDatePickerDtFim.setBounds(190, 80, 220, 20);
		this.jDatePickerDtFim.setEnabled(false);
		this.jDatePickerDtFim.setColorDisabled(this.getBackground());

		// this.jPanelPeriodos.add(this.jTextFieldDtFim);
		// this.jTextFieldDtFim.setBounds(160, 80, 220, 20);

		// Label do campo "Número de Horas"
		this.jLabelNumHoras.setText("Num. Horas:");
		this.jPanelPeriodos.add(this.jLabelNumHoras);
		this.jLabelNumHoras.setBounds(130, 110, 60, 20);

		// Campo "Número de Horas"
		this.jPanelPeriodos.add(this.jTextFieldNumHoras);
		this.jTextFieldNumHoras.setBounds(190, 110, 220, 20);
		this.jTextFieldNumHoras.disable();

		// Botão inserir
		this.jButtonIncluirPeriodo.setText("Inserir  ");
		this.jButtonIncluirPeriodo.setIcon(new ImageIcon("Adiciona.gif"));
		this.jButtonIncluirPeriodo
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						adicionarPeriodo();
					}
				});
		this.jToolBarPeriodo.add(this.jButtonIncluirPeriodo);

		// Botão alterar
		this.jButtonAlterarPeriodo.setText("Alterar  ");
		this.jButtonAlterarPeriodo.setIcon(new ImageIcon("Lapis.gif"));
		this.jButtonAlterarPeriodo
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						alterarPeriodo();
					}
				});
		this.jToolBarPeriodo.add(this.jButtonAlterarPeriodo);

		// Botão excluir
		this.jButtonExcluirPeriodo.setText("Excluir  ");
		this.jButtonExcluirPeriodo.setIcon(new ImageIcon("Remove.gif"));
		this.jButtonExcluirPeriodo
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						excluirPeriodo();
					}
				});
		this.jToolBarPeriodo.add(this.jButtonExcluirPeriodo);

		// Botão confirmar
		this.jButtonConfirmarPeriodo.setText("Confirma  ");
		this.jButtonConfirmarPeriodo.setIcon(new ImageIcon("Confirma.gif"));
		this.jButtonConfirmarPeriodo.hide();
		this.jButtonConfirmarPeriodo
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						confirmaOperacaoPeriodo();
					}
				});
		this.jToolBarPeriodo.add(this.jButtonConfirmarPeriodo);

		// Botão cancelar
		this.jButtonCancelarPeriodo.setText("Cancela  ");
		this.jButtonCancelarPeriodo.setIcon(new ImageIcon("Cancela.gif"));
		this.jButtonCancelarPeriodo.hide();
		this.jButtonCancelarPeriodo
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						cancelaOperacaoPeriodo();
					}
				});
		this.jToolBarPeriodo.add(this.jButtonCancelarPeriodo);
		this.jToolBarPeriodo.setFloatable(false);
		// Adiciona toolbar ao panel
		this.jPanelPeriodos.add(this.jToolBarPeriodo);
		this.jToolBarPeriodo.setBounds(385, 140, 190, 25);
	}

	/***************************************************************************
	 * Métodos referentes à TAB GERAL
	 * 
	 **************************************************************************/
	protected void adicionarProfissional() {
		/* Controle da barra de botões */
		this.jButtonIncluirProfissional.hide();
		this.jButtonAlterarProfissional.hide();
		this.jButtonExcluirProfissional.hide();
		this.jButtonConfirmarProfissional.show();
		this.jButtonCancelarProfissional.show();

		this.jTextFieldNomeProfissional.enable();
		this.jTextFieldValorHoraProfissional.enable();
		this.jTextFieldQtdHorasDiaProfissional.enable();
		this.jTextFieldNomeProfissional.setText("");
		this.jTextFieldValorHoraProfissional.setText("");
		this.jTextFieldQtdHorasDiaProfissional.setText("");
		this.validate();
		this.repaint();

		this.operacao = "INSERIR";
	}

	protected void alterarProfissional() {
		int linha = this.jTableProfissional.getSelectedRow();
		if (linha != -1) {

			this.jButtonIncluirProfissional.hide();
			this.jButtonAlterarProfissional.hide();
			this.jButtonExcluirProfissional.hide();
			this.jButtonConfirmarProfissional.show();
			this.jButtonCancelarProfissional.show();

			this.jTextFieldValorHoraProfissional.enable();
			this.jTextFieldQtdHorasDiaProfissional.enable();

			this.validate();
			this.repaint();

			this.operacao = "ALTERAR";
		}
	}

	private void gerenciaBotoesConfirma() {
		// Controle da barra de botões
		this.jButtonIncluirProfissional.show();
		this.jButtonAlterarProfissional.show();
		this.jButtonExcluirProfissional.show();
		this.jButtonConfirmarProfissional.hide();
		this.jButtonCancelarProfissional.hide();

		this.jTextFieldNomeProfissional.disable();
		this.jTextFieldValorHoraProfissional.disable();
		this.jTextFieldQtdHorasDiaProfissional.disable();
		this.validate();
		this.repaint();
	}

	protected void confirmaOperacao() {
		if (this.operacao.compareToIgnoreCase("INSERIR") == 0) {
			this.confirmaAdicionarProfissional();
		}
		if (this.operacao.compareToIgnoreCase("ALTERAR") == 0) {
			this.confirmaAlterarProfissional();
		}
	}

	protected void cancelaOperacao() {
		// Controle da barra de botões
		this.jButtonIncluirProfissional.show();
		this.jButtonAlterarProfissional.show();
		this.jButtonExcluirProfissional.show();
		this.jButtonConfirmarProfissional.hide();
		this.jButtonCancelarProfissional.hide();

		this.jTextFieldNomeProfissional.disable();
		this.jTextFieldValorHoraProfissional.disable();
		this.jTextFieldQtdHorasDiaProfissional.disable();

		this.selecionaProfissional();

		this.validate();
		this.repaint();
	}

	protected void confirmaAdicionarProfissional() {
		Object[] linha = new Object[3];
		linha[0] = this.jTextFieldNomeProfissional.getText();
		linha[1] = this.jTextFieldValorHoraProfissional.getText();
		linha[2] = this.jTextFieldQtdHorasDiaProfissional.getText();

		if ((this.jTextFieldNomeProfissional.getText().length() == 0)
				|| (this.jTextFieldValorHoraProfissional.getText().length() == 0)) {
			JOptionPane.showMessageDialog(this,
					"Todos os campos devem ser preenchidos.", "Alerta",
					JOptionPane.WARNING_MESSAGE);
		} else {

			int retorno = JanelaPrincipal.getAplicacao().inserirProfissional(
					this.jTextFieldNomeProfissional.getText(),
					this.jTextFieldValorHoraProfissional.getText(),
					this.jTextFieldQtdHorasDiaProfissional.getText());

			switch (retorno) {
			case 0:
				((DefaultTableModel) this.jTableProfissional.getModel())
						.addRow(linha);
				this.gerenciaBotoesConfirma();
				this.jTableProfissional.setRowSelectionInterval(
						this.jTableProfissional.getRowCount() - 1,
						this.jTableProfissional.getRowCount() - 1);
				break;
			case -1:
				JOptionPane
						.showMessageDialog(
								this,
								"Não é possível cadastrar dois profissionais com o mesmo nome.",
								"Alerta", JOptionPane.WARNING_MESSAGE);
				break;
			case -2:
				JOptionPane.showMessageDialog(this,
						"O campo valor da hora deve ser um número.", "Alerta",
						JOptionPane.WARNING_MESSAGE);
				break;
			case -3:
				JOptionPane
						.showMessageDialog(
								this,
								"O campo quantidade de horas de trabalho por dia deve ser um inteiro.",
								"Alerta", JOptionPane.WARNING_MESSAGE);
				break;
			}
		}
	}

	protected void confirmaAlterarProfissional() {
		Object[] linha = new Object[3];
		linha[0] = this.jTextFieldNomeProfissional.getText();
		linha[1] = this.jTextFieldValorHoraProfissional.getText();
		linha[2] = this.jTextFieldQtdHorasDiaProfissional.getText();

		if ((this.jTextFieldNomeProfissional.getText().length() == 0)
				|| (this.jTextFieldValorHoraProfissional.getText().length() == 0)) {
			JOptionPane.showMessageDialog(this,
					"Todos os campos devem ser preenchidos.", "Alerta",
					JOptionPane.WARNING_MESSAGE);
		} else {
			int linhaSelecionada = this.jTableProfissional.getSelectedRow();
			if (linhaSelecionada != -1) {

				int retorno = JanelaPrincipal.getAplicacao()
						.alterarProfissional(
								this.jTextFieldNomeProfissional.getText(),
								this.jTextFieldValorHoraProfissional.getText(),
								this.jTextFieldQtdHorasDiaProfissional
										.getText());

				switch (retorno) {
				case 0:
					((DefaultTableModel) this.jTableProfissional.getModel())
							.setValueAt(linha[0], linhaSelecionada, 0);
					((DefaultTableModel) this.jTableProfissional.getModel())
							.setValueAt(linha[1], linhaSelecionada, 1);
					((DefaultTableModel) this.jTableProfissional.getModel())
							.setValueAt(linha[2], linhaSelecionada, 2);
					this.gerenciaBotoesConfirma();
					break;
				case -2:
					JOptionPane.showMessageDialog(this,
							"O campo valor da hora deve ser um número.",
							"Alerta", JOptionPane.WARNING_MESSAGE);
					break;
				case -3:
					JOptionPane
							.showMessageDialog(
									this,
									"O campo quantidade de horas de trabalho por dia deve ser um inteiro.",
									"Alerta", JOptionPane.WARNING_MESSAGE);
					break;
				}
			}
		}
	}

	protected void excluirProfissional() {
		int linha = this.jTableProfissional.getSelectedRow();

		if (linha != -1) {
			int retorno = JanelaPrincipal.getAplicacao().excluirProfissional(
					this.jTextFieldNomeProfissional.getText());
			if (retorno == 0) {
				((DefaultTableModel) this.jTableProfissional.getModel())
						.removeRow(this.jTableProfissional.getSelectedRow());
				if (linha > 0) {
					this.jTableProfissional.setRowSelectionInterval(linha - 1,
							linha - 1);
				} else {
					if (this.jTableProfissional.getRowCount() > 0) {
						this.jTableProfissional.setRowSelectionInterval(linha,
								linha);
					} else {
						this.jTextFieldNomeProfissional.setText("");
						this.jTextFieldValorHoraProfissional.setText("");
						this.jTextFieldQtdHorasDiaProfissional.setText("");
					}
				}
				this.selecionaProfissional();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Linha não selecionada.",
					"Alerta", JOptionPane.WARNING_MESSAGE);
		}

	}

	protected void selecionaProfissional() {
		Object[] linha = new Object[3];
		linha[0] = this.jTextFieldNomeProfissional.getText();
		linha[1] = this.jTextFieldValorHoraProfissional.getText();
		linha[2] = this.jTextFieldQtdHorasDiaProfissional.getText();

		int linhaSelecionada = this.jTableProfissional.getSelectedRow();
		if (linhaSelecionada != -1) {
			this.jTextFieldNomeProfissional
					.setText((String) ((DefaultTableModel) this.jTableProfissional
							.getModel()).getValueAt(linhaSelecionada, 0));
			this.jTextFieldValorHoraProfissional
					.setText((String) ((DefaultTableModel) this.jTableProfissional
							.getModel()).getValueAt(linhaSelecionada, 1));
			this.jTextFieldQtdHorasDiaProfissional
					.setText((String) ((DefaultTableModel) this.jTableProfissional
							.getModel()).getValueAt(linhaSelecionada, 2));
			this.jTextFieldProfissionalPeriodo
					.setText((String) ((DefaultTableModel) this.jTableProfissional
							.getModel()).getValueAt(linhaSelecionada, 0));
			this.jTextFieldProfissionalCaracteristica
					.setText((String) ((DefaultTableModel) this.jTableProfissional
							.getModel()).getValueAt(linhaSelecionada, 0));

		}
	}

	protected void carregaListaProfissional() {
		Object[] linha = new Object[3];
		ArrayList listaProfissionais = new ArrayList();

		listaProfissionais = JanelaPrincipal.getAplicacao().getProfissionais();

		Iterator iter1 = listaProfissionais.iterator();
		while (iter1.hasNext()) {
			Profissional p = (Profissional) iter1.next();
			linha[0] = p.getNome();
			linha[1] = p.getValorHora() + "";
			linha[2] = p.getQtdHorasDia() + "";

			((DefaultTableModel) this.jTableProfissional.getModel())
					.addRow(linha);
		}
		if (listaProfissionais.size() > 0) {
			this.jTableProfissional.setRowSelectionInterval(0, 0);
			selecionaProfissional();
		}
	}

	/***************************************************************************
	 * Métodos referentes à TAB CARACTERÍSTICAS
	 * 
	 **************************************************************************/
	protected void adicionarCaracteristica() {
		/* Controle da barra de botões */
		this.jButtonIncluirCaracteristica.hide();
		this.jButtonAlterarCaracteristica.hide();
		this.jButtonExcluirCaracteristica.hide();
		this.jButtonConfirmarCaracteristica.show();
		this.jButtonCancelarCaracteristica.show();

		this.jComboboxTipoCaracteristica.enable();
		this.jComboboxNomeCaracteristica.enable();
		this.jComboboxValorCaracteristica.enable();

		if (this.jComboboxTipoCaracteristica.getItemCount() > 0) {
			this.jComboboxTipoCaracteristica.setSelectedIndex(0);
		}
		if (this.jComboboxNomeCaracteristica.getItemCount() > 0) {
			this.jComboboxNomeCaracteristica.setSelectedIndex(0);
		}
		if (this.jComboboxValorCaracteristica.getItemCount() > 0) {
			this.jComboboxValorCaracteristica.setSelectedIndex(0);
		}

		this.validate();
		this.repaint();

		this.operacaoCaracteristicas = "INSERIR";
	}

	protected void alterarCaracteristica() {
		int linha = this.jTableCaracteristica.getSelectedRow();
		if (linha != -1) {
			this.jButtonIncluirCaracteristica.hide();
			this.jButtonAlterarCaracteristica.hide();
			this.jButtonExcluirCaracteristica.hide();
			this.jButtonConfirmarCaracteristica.show();
			this.jButtonCancelarCaracteristica.show();

			this.jComboboxValorCaracteristica.enable();

			this.validate();
			this.repaint();

			this.operacaoCaracteristicas = "ALTERAR";
		}
	}

	private void gerenciaBotoesConfirmaCaracteristica() {
		// Controle da barra de botões

		this.jButtonIncluirCaracteristica.show();
		this.jButtonAlterarCaracteristica.show();
		this.jButtonExcluirCaracteristica.show();
		this.jButtonConfirmarCaracteristica.hide();
		this.jButtonCancelarCaracteristica.hide();

		this.jComboboxTipoCaracteristica.disable();
		this.jComboboxNomeCaracteristica.disable();
		this.jComboboxValorCaracteristica.disable();
		this.validate();
		this.repaint();
	}

	protected void confirmaOperacaoCaracteristica() {
		if (this.operacaoCaracteristicas.compareToIgnoreCase("INSERIR") == 0) {
			this.confirmaAdicionarCaracteristica();
		}
		if (this.operacaoCaracteristicas.compareToIgnoreCase("ALTERAR") == 0) {
			this.confirmaAlterarCaracteristica();
		}
	}

	protected void cancelaOperacaoCaracteristica() {
		// Controle da barra de botões
		this.jButtonIncluirCaracteristica.show();
		this.jButtonAlterarCaracteristica.show();
		this.jButtonExcluirCaracteristica.show();
		this.jButtonConfirmarCaracteristica.hide();
		this.jButtonCancelarCaracteristica.hide();

		this.jComboboxTipoCaracteristica.disable();
		this.jComboboxNomeCaracteristica.disable();
		this.jComboboxValorCaracteristica.disable();

		this.selecionaCaracteristica();

		this.validate();
		this.repaint();
	}

	protected void preencheValoresCaracteristica() {
		DefinicaoCaracteristica dc = (DefinicaoCaracteristica) this.jComboboxNomeCaracteristica
				.getSelectedItem();

		this.jComboboxValorCaracteristica.removeAllItems();
		if (dc != null) {
			Iterator iter = dc.getTipoDefinicaoCaracteristica().getEscala()
					.getValorEscalaOrdenado().iterator();
			while (iter.hasNext()) {
				ValorEscala ve = (ValorEscala) iter.next();
				this.jComboboxValorCaracteristica.addItem(ve);
			}
		}
	}

	protected void selecionaCaracteristica() {
		int linhaSelecionada = this.jTableCaracteristica.getSelectedRow();

		if (linhaSelecionada != -1) {
			this.jComboboxTipoCaracteristica
					.setSelectedItem(((DefaultTableModel) this.jTableCaracteristica
							.getModel()).getValueAt(linhaSelecionada, 2));

			this.jComboboxNomeCaracteristica
					.setSelectedItem(((DefaultTableModel) this.jTableCaracteristica
							.getModel()).getValueAt(linhaSelecionada, 0));

			this.jComboboxValorCaracteristica
					.setSelectedItem(((DefaultTableModel) this.jTableCaracteristica
							.getModel()).getValueAt(linhaSelecionada, 1));
		}
	}

	protected void preencheNomesCaracteristica() {
		TipoDefinicaoCaracteristica tdc = (TipoDefinicaoCaracteristica) this.jComboboxTipoCaracteristica
				.getSelectedItem();

		this.jComboboxNomeCaracteristica.removeAllItems();
		Iterator iter = JanelaPrincipal.getAplicacao().getCaracteristicas(tdc)
				.iterator();
		while (iter.hasNext()) {
			DefinicaoCaracteristica dc = (DefinicaoCaracteristica) iter.next();
			this.jComboboxNomeCaracteristica.addItem(dc);
		}
	}

	protected void confirmaAdicionarCaracteristica() {
		Object[] linha = new Object[3];
		linha[0] = this.jComboboxNomeCaracteristica.getSelectedItem();
		linha[1] = this.jComboboxValorCaracteristica.getSelectedItem();
		linha[2] = this.jComboboxTipoCaracteristica.getSelectedItem();

		if ((this.jComboboxNomeCaracteristica.getItemCount() <= 0)
				|| (this.jComboboxValorCaracteristica.getItemCount() <= 0)
				|| (this.jComboboxTipoCaracteristica.getItemCount() <= 0)) {
			JOptionPane.showMessageDialog(this,
					"Todos os campos devem ser preenchidos.", "Alerta",
					JOptionPane.WARNING_MESSAGE);
		} else {

			int retorno = JanelaPrincipal
					.getAplicacao()
					.inserirCaracteristicaProfissional(
							this.jTextFieldProfissionalCaracteristica.getText(),
							(DefinicaoCaracteristica) this.jComboboxNomeCaracteristica
									.getSelectedItem(),
							(ValorEscala) this.jComboboxValorCaracteristica
									.getSelectedItem());

			switch (retorno) {
			case 0:
				((DefaultTableModel) this.jTableCaracteristica.getModel())
						.addRow(linha);
				this.gerenciaBotoesConfirmaCaracteristica();
				this.jTableCaracteristica.setRowSelectionInterval(
						this.jTableCaracteristica.getRowCount() - 1,
						this.jTableCaracteristica.getRowCount() - 1);
				break;
			case -1:
				JOptionPane
						.showMessageDialog(
								this,
								"Valor informado não pode ser maior que o valor máximo.",
								"Alerta", JOptionPane.WARNING_MESSAGE);
				break;
			case -2:
				JOptionPane.showMessageDialog(this,
						"Característica já atribuída ao profissional.",
						"Alerta", JOptionPane.WARNING_MESSAGE);
				break;
			}
		}
	}

	protected void confirmaAlterarCaracteristica() {
		Object[] linha = new Object[3];
		linha[0] = this.jComboboxNomeCaracteristica.getSelectedItem();
		linha[1] = this.jComboboxValorCaracteristica.getSelectedItem();
		linha[2] = this.jComboboxTipoCaracteristica.getSelectedItem();

		if ((this.jComboboxNomeCaracteristica.getItemCount() <= 0)
				|| (this.jComboboxValorCaracteristica.getItemCount() <= 0)
				|| (this.jComboboxTipoCaracteristica.getItemCount() <= 0)) {
			JOptionPane.showMessageDialog(this,
					"Todos os campos devem ser preenchidos.", "Alerta",
					JOptionPane.WARNING_MESSAGE);
		} else {
			int linhaSelecionada = this.jTableCaracteristica.getSelectedRow();
			if (linhaSelecionada != -1) {

				int retorno = JanelaPrincipal
						.getAplicacao()
						.alterarCaracteristicaProfissional(
								this.jTextFieldProfissionalCaracteristica
										.getText(),
								(DefinicaoCaracteristica) this.jComboboxNomeCaracteristica
										.getSelectedItem(),
								(ValorEscala) this.jComboboxValorCaracteristica
										.getSelectedItem());

				switch (retorno) {
				case 0:

					((DefaultTableModel) this.jTableCaracteristica.getModel())
							.setValueAt(linha[0], linhaSelecionada, 0);
					((DefaultTableModel) this.jTableCaracteristica.getModel())
							.setValueAt(linha[1], linhaSelecionada, 1);
					((DefaultTableModel) this.jTableCaracteristica.getModel())
							.setValueAt(linha[2], linhaSelecionada, 2);
					this.gerenciaBotoesConfirmaCaracteristica();
					break;
				case -1:
					JOptionPane
							.showMessageDialog(
									this,
									"Valor informado não pode ser maior que o valor máximo.",
									"Alerta", JOptionPane.WARNING_MESSAGE);
					break;
				}
			}
		}
	}

	protected void excluirCaracteristica() {
		int linha = this.jTableCaracteristica.getSelectedRow();

		if (linha != -1) {
			JanelaPrincipal.getAplicacao().excluirCaracteristicaProfissional(
					this.jTextFieldProfissionalCaracteristica.getText(),
					(DefinicaoCaracteristica) this.jComboboxNomeCaracteristica
							.getSelectedItem());

			((DefaultTableModel) this.jTableCaracteristica.getModel())
					.removeRow(this.jTableCaracteristica.getSelectedRow());
			if (linha > 0) {
				this.jTableCaracteristica.setRowSelectionInterval(linha - 1,
						linha - 1);
			} else {
				if (this.jTableCaracteristica.getRowCount() > 0) {
					this.jTableCaracteristica.setRowSelectionInterval(linha,
							linha);
				} else {
					if (this.jComboboxNomeCaracteristica.getItemCount() > 0)
						this.jComboboxNomeCaracteristica.setSelectedIndex(0);
					if (this.jComboboxValorCaracteristica.getItemCount() > 0)
						this.jComboboxValorCaracteristica.setSelectedIndex(0);
					if (this.jComboboxTipoCaracteristica.getItemCount() > 0)
						this.jComboboxTipoCaracteristica.setSelectedIndex(0);
				}
			}
			this.selecionaCaracteristica();
		} else {
			JOptionPane.showMessageDialog(this, "Linha não selecionada.",
					"Alerta", JOptionPane.WARNING_MESSAGE);
		}
	}

	protected void carregaListaCaracteristicas() {
		int linhaSelecionada = this.jTableProfissional.getSelectedRow();
		Object[] linha = new Object[3];
		ArrayList listaCaracteristicas = new ArrayList();
		String nomeProfissional = "";
		if (linhaSelecionada != -1) {
			nomeProfissional = (String) ((DefaultTableModel) this.jTableProfissional
					.getModel()).getValueAt(linhaSelecionada, 0);
			this.jTextFieldProfissionalCaracteristica.setText(nomeProfissional);
			listaCaracteristicas = JanelaPrincipal.getAplicacao()
					.obterCaracteristicasProfissional(nomeProfissional);

			Iterator iter1 = listaCaracteristicas.iterator();
			while (iter1.hasNext()) {
				Caracteristica c = (Caracteristica) iter1.next();
				linha[0] = c.getDefinicaoCaracteristica();
				linha[1] = c.getValor();
				linha[2] = c.getDefinicaoCaracteristica()
						.getTipoDefinicaoCaracteristica();

				((DefaultTableModel) this.jTableCaracteristica.getModel())
						.addRow(linha);
			}
			if (listaCaracteristicas.size() > 0) {
				this.jTableCaracteristica.setRowSelectionInterval(0, 0);
				selecionaCaracteristica();
			}
		}
	}

	protected void limpaListaCaracteristicas() {
		try {
			if (this.jComboboxNomeCaracteristica.getItemCount() > 0)
				this.jComboboxNomeCaracteristica.setSelectedIndex(0);
			if (this.jComboboxValorCaracteristica.getItemCount() > 0)
				this.jComboboxValorCaracteristica.setSelectedIndex(0);
			if (this.jComboboxTipoCaracteristica.getItemCount() > 0)
				this.jComboboxTipoCaracteristica.setSelectedIndex(0);

			while (((DefaultTableModel) this.jTableCaracteristica.getModel())
					.getRowCount() > 0) {
				((DefaultTableModel) this.jTableCaracteristica.getModel())
						.removeRow(0);
			}
		} catch (Exception e) {
		}
	}

	/***************************************************************************
	 * Métodos referentes à TAB PERÍODOS DE INDISPONIBILIDADE
	 * 
	 **************************************************************************/
	private void gerenciaBotoesConfirmaPeriodo() {
		// Controle da barra de botões
		this.jButtonIncluirPeriodo.show();
		this.jButtonAlterarPeriodo.show();
		this.jButtonExcluirPeriodo.show();
		this.jButtonConfirmarPeriodo.hide();
		this.jButtonCancelarPeriodo.hide();

		this.jDatePickerDtIni.setEnabled(false);
		this.jDatePickerDtFim.setEnabled(false);
		this.jTextFieldNumHoras.disable();
		this.validate();
		this.repaint();
	}

	protected void confirmaOperacaoPeriodo() {
		if (this.operacaoPeriodo.compareToIgnoreCase("INSERIR") == 0) {
			this.confirmaAdicionarPeriodo();
		}
		if (this.operacaoPeriodo.compareToIgnoreCase("ALTERAR") == 0) {
			this.confirmaAlterarPeriodo();
		}
	}

	protected void cancelaOperacaoPeriodo() {
		this.jButtonIncluirPeriodo.show();
		this.jButtonAlterarPeriodo.show();
		this.jButtonExcluirPeriodo.show();
		this.jButtonConfirmarPeriodo.hide();
		this.jButtonCancelarPeriodo.hide();

		this.jDatePickerDtIni.setEnabled(false);
		this.jDatePickerDtFim.setEnabled(false);
		this.jTextFieldNumHoras.disable();

		this.selecionaPeriodo();

		this.validate();
		this.repaint();
	}

	protected void adicionarPeriodo() {
		if (this.jTableProfissional.getSelectedRow() != -1) {
			// Controle da barra de botões
			this.jButtonIncluirPeriodo.hide();
			this.jButtonAlterarPeriodo.hide();
			this.jButtonExcluirPeriodo.hide();
			this.jButtonConfirmarPeriodo.show();
			this.jButtonCancelarPeriodo.show();

			this.jDatePickerDtIni.enable();
			this.jDatePickerDtFim.enable();
			this.jTextFieldNumHoras.enable();
			this.jDatePickerDtIni.setEnabled(true);
			this.jDatePickerDtFim.setEnabled(true);
			this.jTextFieldNumHoras.setText("");
			GregorianCalendar gCalendar = new GregorianCalendar();
			this.jDatePickerDtIni.set(gCalendar.get(Calendar.YEAR), gCalendar
					.get(Calendar.MONTH), gCalendar.get(Calendar.DAY_OF_MONTH));
			this.jDatePickerDtFim.set(gCalendar.get(Calendar.YEAR), gCalendar
					.get(Calendar.MONTH), gCalendar.get(Calendar.DAY_OF_MONTH));

			this.validate();
			this.repaint();

			this.operacaoPeriodo = "INSERIR";
		} else {
			JOptionPane.showMessageDialog(this,
					"É preciso selecionar um profissional.", "Alerta",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	protected void alterarPeriodo() {
		if (this.jTableProfissional.getSelectedRow() != -1) {
			int linha = this.jTablePeriodo.getSelectedRow();
			if (linha != -1) {
				// Controle da barra de botões
				this.jButtonIncluirPeriodo.hide();
				this.jButtonAlterarPeriodo.hide();
				this.jButtonExcluirPeriodo.hide();
				this.jButtonConfirmarPeriodo.show();
				this.jButtonCancelarPeriodo.show();

				this.jDatePickerDtIni.enable();
				this.jDatePickerDtFim.enable();
				this.jTextFieldNumHoras.enable();
				this.jDatePickerDtIni.setEnabled(true);
				this.jDatePickerDtFim.setEnabled(true);
				this.jTextFieldNumHoras.setText("");
				GregorianCalendar gCalendar = new GregorianCalendar();
				this.jDatePickerDtIni.set(gCalendar.get(Calendar.YEAR),
						gCalendar.get(Calendar.MONTH), gCalendar
								.get(Calendar.DAY_OF_MONTH));
				this.jDatePickerDtFim.set(gCalendar.get(Calendar.YEAR),
						gCalendar.get(Calendar.MONTH), gCalendar
								.get(Calendar.DAY_OF_MONTH));
				this.validate();
				this.repaint();

				this.operacaoPeriodo = "ALTERAR";
			}
		} else {
			JOptionPane.showMessageDialog(this,
					"É preciso selecionar um profissional.", "Alerta",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	protected void confirmaAdicionarPeriodo() {
		Object[] linha = new Object[3];

		linha[0] = this.jDatePickerDtIni.getDayString() + "/"
				+ this.jDatePickerDtIni.getMonthString() + "/"
				+ this.jDatePickerDtIni.getYearString();
		linha[1] = this.jDatePickerDtFim.getDayString() + "/"
				+ this.jDatePickerDtFim.getMonthString() + "/"
				+ this.jDatePickerDtFim.getYearString();
		linha[2] = this.jTextFieldNumHoras.getText();

		if ((this.jTextFieldNumHoras.getText().length() == 0)) {
			JOptionPane.showMessageDialog(this,
					"Todos os campos devem ser preenchidos.", "Alerta",
					JOptionPane.WARNING_MESSAGE);
		} else {

			int retorno = JanelaPrincipal.getAplicacao()
					.inserirPeriodoIndisponibilidadeProfissional(
							this.jTextFieldProfissionalPeriodo.getText(),
							(String) linha[0], (String) linha[1],
							(String) linha[2]);

			switch (retorno) {
			case 0:
				((DefaultTableModel) this.jTablePeriodo.getModel())
						.addRow(linha);
				this.gerenciaBotoesConfirmaPeriodo();
				this.jTablePeriodo.setRowSelectionInterval(this.jTablePeriodo
						.getRowCount() - 1,
						this.jTablePeriodo.getRowCount() - 1);
				break;
			case -1:
				JOptionPane.showMessageDialog(this, "Data inválida.", "Alerta",
						JOptionPane.WARNING_MESSAGE);
				break;
			case -2:
				JOptionPane.showMessageDialog(this,
						"Data final não pode ser anterior à data inicial.",
						"Alerta", JOptionPane.WARNING_MESSAGE);
				break;
			case -3:
				JOptionPane
						.showMessageDialog(
								this,
								"Número de horas do período de indisponibilidade não deve exceder número máximo de horas trabalhadas por dia pelo profisisonal.",
								"Alerta", JOptionPane.WARNING_MESSAGE);
				break;
			case -4:
				JOptionPane
						.showMessageDialog(
								this,
								"Já há um período de indisponibilidade com estes dados para este profissional.",
								"Alerta", JOptionPane.WARNING_MESSAGE);
				break;
			}
		}
	}

	protected void confirmaAlterarPeriodo() {
		Object[] linha = new Object[3];

		linha[0] = this.jDatePickerDtIni.getDayString() + "/"
				+ this.jDatePickerDtIni.getMonthString() + "/"
				+ this.jDatePickerDtIni.getYearString();
		linha[1] = this.jDatePickerDtFim.getDayString() + "/"
				+ this.jDatePickerDtFim.getMonthString() + "/"
				+ this.jDatePickerDtFim.getYearString();
		linha[2] = this.jTextFieldNumHoras.getText();

		int linhaSelecionada = this.jTablePeriodo.getSelectedRow();
		String dataInicioAnterior = ((String) ((DefaultTableModel) this.jTablePeriodo
				.getModel()).getValueAt(linhaSelecionada, 0));
		String dataFimAnterior = ((String) ((DefaultTableModel) this.jTablePeriodo
				.getModel()).getValueAt(linhaSelecionada, 1));
		String numHorasAnterior = ((String) ((DefaultTableModel) this.jTablePeriodo
				.getModel()).getValueAt(linhaSelecionada, 2));

		if ((this.jTextFieldNumHoras.getText().length() == 0)) {
			JOptionPane.showMessageDialog(this,
					"Todos os campos devem ser preenchidos.", "Alerta",
					JOptionPane.WARNING_MESSAGE);
		} else {
			int retorno = JanelaPrincipal.getAplicacao()
					.alterarPeriodoIndisponibilidadeProfissional(
							this.jTextFieldProfissionalPeriodo.getText(),
							dataInicioAnterior, dataFimAnterior,
							numHorasAnterior, (String) linha[0],
							(String) linha[1], (String) linha[2]);

			switch (retorno) {
			case 0:
				((DefaultTableModel) this.jTablePeriodo.getModel()).setValueAt(
						linha[0], linhaSelecionada, 0);
				((DefaultTableModel) this.jTablePeriodo.getModel()).setValueAt(
						linha[1], linhaSelecionada, 1);
				((DefaultTableModel) this.jTablePeriodo.getModel()).setValueAt(
						linha[2], linhaSelecionada, 2);
				this.gerenciaBotoesConfirmaPeriodo();
				break;
			case -1:
				JOptionPane.showMessageDialog(this, "Data Inválida.", "Alerta",
						JOptionPane.WARNING_MESSAGE);
				break;
			case -2:
				JOptionPane.showMessageDialog(this,
						"Data final não pode ser anterior à data inicial.",
						"Alerta", JOptionPane.WARNING_MESSAGE);
				break;
			case -3:
				JOptionPane
						.showMessageDialog(
								this,
								"Número de horas do período de indisponibilidade não deve exceder número máximo de horas trabalhadas por dia pelo profisisonal.",
								"Alerta", JOptionPane.WARNING_MESSAGE);
				break;
			case -4:
				JOptionPane
						.showMessageDialog(
								this,
								"Já há um período de indisponibilidade com estes dados para este profissional.",
								"Alerta", JOptionPane.WARNING_MESSAGE);
				break;
			}
		}
	}

	protected void excluirPeriodo() {
		if (this.jTableProfissional.getSelectedRow() != -1) {
			int linha = this.jTablePeriodo.getSelectedRow();
			String dataInicio = this.jDatePickerDtIni.getDayString() + "/"
					+ this.jDatePickerDtIni.getMonthString() + "/"
					+ this.jDatePickerDtIni.getYearString();
			String dataFim = this.jDatePickerDtFim.getDayString() + "/"
					+ this.jDatePickerDtFim.getMonthString() + "/"
					+ this.jDatePickerDtFim.getYearString();
			String numHoras = this.jTextFieldNumHoras.getText();

			if (linha != -1) {
				JanelaPrincipal.getAplicacao()
						.excluirPeriodoIndisponibilidadeProfissional(
								this.jTextFieldProfissionalPeriodo.getText(),
								dataInicio, dataFim, numHoras);

				((DefaultTableModel) this.jTablePeriodo.getModel())
						.removeRow(this.jTablePeriodo.getSelectedRow());
				if (linha > 0) {
					this.jTablePeriodo.setRowSelectionInterval(linha - 1,
							linha - 1);
				} else {
					if (this.jTablePeriodo.getRowCount() > 0) {
						this.jTablePeriodo
								.setRowSelectionInterval(linha, linha);
					} else {
						GregorianCalendar gCalendar = new GregorianCalendar();
						this.jDatePickerDtIni.set(gCalendar.get(Calendar.YEAR),
								gCalendar.get(Calendar.MONTH), gCalendar
										.get(Calendar.DAY_OF_MONTH));
						this.jDatePickerDtFim.set(gCalendar.get(Calendar.YEAR),
								gCalendar.get(Calendar.MONTH), gCalendar
										.get(Calendar.DAY_OF_MONTH));
						this.jTextFieldNumHoras.setText("");
					}
				}
				this.selecionaPeriodo();
			} else {
				JOptionPane.showMessageDialog(this, "Linha não selecionada.",
						"Alerta", JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this,
					"É preciso selecionar um profissional.", "Alerta",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	protected void carregaListaPeriodos() {
		int linhaSelecionada = this.jTableProfissional.getSelectedRow();
		Object[] linha = new Object[3];
		ArrayList listaPeriodos = new ArrayList();
		String nomeProfissional = "";
		if (linhaSelecionada != -1) {
			nomeProfissional = (String) ((DefaultTableModel) this.jTableProfissional
					.getModel()).getValueAt(linhaSelecionada, 0);
			this.jTextFieldProfissionalPeriodo.setText(nomeProfissional);
			listaPeriodos = JanelaPrincipal.getAplicacao()
					.obterPeriodosProfissional(nomeProfissional);

			Iterator iter1 = listaPeriodos.iterator();
			while (iter1.hasNext()) {
				PeriodoIndisponivel pi = (PeriodoIndisponivel) iter1.next();
				linha[0] = pi.getDataInicioString();
				linha[1] = pi.getDataFimString();
				linha[2] = pi.getQtdHoras() + "";

				((DefaultTableModel) this.jTablePeriodo.getModel())
						.addRow(linha);
			}
			if (listaPeriodos.size() > 0) {
				this.jTablePeriodo.setRowSelectionInterval(0, 0);
				selecionaPeriodo();
			}
		}
	}

	protected void limpaListaPeriodos() {
		try {
			this.jTextFieldNumHoras.setText("");
			GregorianCalendar gCalendar = new GregorianCalendar();
			this.jDatePickerDtIni.set(gCalendar.get(Calendar.YEAR), gCalendar
					.get(Calendar.MONTH), gCalendar.get(Calendar.DAY_OF_MONTH));
			this.jDatePickerDtFim.set(gCalendar.get(Calendar.YEAR), gCalendar
					.get(Calendar.MONTH), gCalendar.get(Calendar.DAY_OF_MONTH));
			while (((DefaultTableModel) this.jTablePeriodo.getModel())
					.getRowCount() > 0) {
				((DefaultTableModel) this.jTablePeriodo.getModel())
						.removeRow(0);
			}
		} catch (Exception e) {
		}

	}

	protected void selecionaPeriodo() {
		int linhaSelecionada = this.jTablePeriodo.getSelectedRow();
		if (linhaSelecionada != -1) {
			int dia = Integer
					.parseInt(((String) ((DefaultTableModel) this.jTablePeriodo
							.getModel()).getValueAt(linhaSelecionada, 0))
							.substring(0, 2));

			int mes = Integer
					.parseInt(((String) ((DefaultTableModel) this.jTablePeriodo
							.getModel()).getValueAt(linhaSelecionada, 0))
							.substring(3, 5));

			int ano = Integer
					.parseInt(((String) ((DefaultTableModel) this.jTablePeriodo
							.getModel()).getValueAt(linhaSelecionada, 0))
							.substring(6));
			this.jDatePickerDtIni.set(ano, mes - 1, dia);
			dia = Integer
					.parseInt(((String) ((DefaultTableModel) this.jTablePeriodo
							.getModel()).getValueAt(linhaSelecionada, 1))
							.substring(0, 2));

			mes = Integer
					.parseInt(((String) ((DefaultTableModel) this.jTablePeriodo
							.getModel()).getValueAt(linhaSelecionada, 1))
							.substring(3, 5));

			ano = Integer
					.parseInt(((String) ((DefaultTableModel) this.jTablePeriodo
							.getModel()).getValueAt(linhaSelecionada, 1))
							.substring(6));
			this.jDatePickerDtFim.set(ano, mes - 1, dia);
			this.jTextFieldNumHoras
					.setText((String) ((DefaultTableModel) this.jTablePeriodo
							.getModel()).getValueAt(linhaSelecionada, 2));
		}
	}

	// Variables declaration - do not modify

	private String operacao = "";

	private String operacaoPeriodo = "";

	private String operacaoCaracteristicas = "";

	/**
	 * TabbedPane que divide a tela em
	 */
	private JTabbedPane jTabbedPane;

	/**
	 * Painel principal (onde os profissionais são inseridos)
	 */
	private javax.swing.JPanel jPanelPrincipal;

	/**
	 * Painel de características possuídas pelo profissional
	 */
	private javax.swing.JPanel jPanelCaracteristicas;

	/**
	 * Painel de períodos de indisponibilidade do profissional
	 */
	private javax.swing.JPanel jPanelPeriodos;

	/**
	 * Botão Incluir do painel principal
	 */
	private javax.swing.JButton jButtonIncluirProfissional;

	/**
	 * Botão Alterar do painel principal
	 */
	private javax.swing.JButton jButtonAlterarProfissional;

	/**
	 * Botão Excluir do painel principal
	 */
	private javax.swing.JButton jButtonExcluirProfissional;

	/**
	 * Botão Confirmar do painel principal
	 */
	private javax.swing.JButton jButtonConfirmarProfissional;

	/**
	 * Botão Cancelar do painel principal
	 */
	private javax.swing.JButton jButtonCancelarProfissional;

	/**
	 * Label do campo nome
	 */
	private javax.swing.JLabel jLabelNomeProfissional;

	/**
	 * Label do campo valor hora
	 */
	private javax.swing.JLabel jLabelValorHoraProfissional;

	/**
	 * Label do campo quantidade de horas por dia
	 */
	private javax.swing.JLabel jLabelQtdHorasDiaProfissional;

	/**
	 * Tabela do painel principal
	 */
	private javax.swing.JTable jTableProfissional;

	/**
	 * Campo de texto nome
	 */
	private javax.swing.JTextField jTextFieldNomeProfissional;

	/**
	 * Campo de texto valor hora
	 */
	private javax.swing.JTextField jTextFieldValorHoraProfissional;

	/**
	 * Campo de texto quantidade de horas por dia do profissional
	 */
	private javax.swing.JTextField jTextFieldQtdHorasDiaProfissional;

	/**
	 * Texto com o título da tela
	 */
	private javax.swing.JTextPane jTextPaneTituloTela;

	/**
	 * Barra de botões do painel principal
	 */
	private javax.swing.JToolBar jToolBarPrincipal;

	/**
	 * Painel que contém a tabela
	 */
	private JScrollPane jScrollPanePrincipal;

	/**
	 * Profissional considerado na tab Períodos
	 */
	private javax.swing.JLabel jLabelProfissionalPeriodo;

	/**
	 * Label data inicial
	 */
	private javax.swing.JLabel jLabelDtIni;

	/**
	 * Label data fim
	 */
	private javax.swing.JLabel jLabelDtFim;

	/**
	 * Label número de horas
	 */
	private javax.swing.JLabel jLabelNumHoras;

	/**
	 * Campo profissional na tab Períodos
	 */
	private javax.swing.JTextField jTextFieldProfissionalPeriodo;

	/**
	 * Campo número de horas na tab Períodos
	 */
	private javax.swing.JTextField jTextFieldNumHoras;

	/**
	 * Botão Incluir do painel períodos
	 */
	private javax.swing.JButton jButtonIncluirPeriodo;

	/**
	 * Botão Alterar do painel períodos
	 */
	private javax.swing.JButton jButtonAlterarPeriodo;

	/**
	 * Botão Excluir do painel períodos
	 */
	private javax.swing.JButton jButtonExcluirPeriodo;

	/**
	 * Botão Confirmar do painel períodos
	 */
	private javax.swing.JButton jButtonConfirmarPeriodo;

	/**
	 * Botão Cancelar do painel períodos
	 */
	private javax.swing.JButton jButtonCancelarPeriodo;

	/**
	 * Barra de botões do painel períodos
	 */
	private javax.swing.JToolBar jToolBarPeriodo;

	/**
	 * Painel que contém a tabela
	 */
	private JScrollPane jScrollPanePeriodo;

	/**
	 * Tabela do painel períodos
	 */
	private javax.swing.JTable jTablePeriodo;

	private JDatePicker jDatePickerDtIni;

	private JDatePicker jDatePickerDtFim;

	// CARACTERISTICAS
	/**
	 * Botão Incluir do painel caracteristicas
	 */
	private javax.swing.JButton jButtonIncluirCaracteristica;

	/**
	 * Botão Alterar do painel caracteristicas
	 */
	private javax.swing.JButton jButtonAlterarCaracteristica;

	/**
	 * Botão Excluir do painel caracteristicas
	 */
	private javax.swing.JButton jButtonExcluirCaracteristica;

	/**
	 * Botão Confirmar do painel caracteristicas
	 */
	private javax.swing.JButton jButtonConfirmarCaracteristica;

	/**
	 * Botão Cancelar do painel caracteristicas
	 */
	private javax.swing.JButton jButtonCancelarCaracteristica;

	/**
	 * Profissional considerado na tab Caracteristicas
	 */
	private javax.swing.JLabel jLabelProfissionalCaracteristica;

	/**
	 * Label do campo nome
	 */
	private javax.swing.JLabel jLabelNomeCaracteristica;

	/**
	 * Label do campo valor
	 */
	private javax.swing.JLabel jLabelValorCaracteristica;

	/**
	 * Campo Profissional
	 */

	private javax.swing.JTextField jTextFieldProfissionalCaracteristica;

	/**
	 * Campo Característica
	 */
	private javax.swing.JComboBox jComboboxNomeCaracteristica;

	/**
	 * Campo Valor
	 */
	private javax.swing.JComboBox jComboboxValorCaracteristica;

	/**
	 * Tabela do painel caracteristicas
	 */
	private javax.swing.JTable jTableCaracteristica;

	/**
	 * Barra de botões do painel caracteristicas
	 */
	private javax.swing.JToolBar jToolBarCaracteristica;

	/**
	 * Painel que contém a caracteristicas
	 */
	private JScrollPane jScrollPaneCaracteristica;

	private javax.swing.JLabel jLabelTipoCaracteristica;

	private javax.swing.JComboBox jComboboxTipoCaracteristica;

	// End of variables declaration

}
