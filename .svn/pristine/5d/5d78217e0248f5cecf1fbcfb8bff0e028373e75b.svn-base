
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.oracle.xmlns.internal.webservices.jaxws_databinding.ExistingAnnotationsType;

/**
 * Title:        Protótipo Tese - CadastroAtividades
 * Description:  Implementação do protótipo da tese. Classe CadastroAtividades
 * Copyright:    Copyright (c) 2004
 * Company:      COPPE Sistemas
 * @author Ahilton S. Barreto
 * @version 1.0
 * Criado em 17/03/2005
 */

/**
 * @author Ahilton
 * 
 * Esta classe contém a janela do cadastro de atividades
 */
public class CadastroAtividades extends javax.swing.JPanel {

	

	/** Creates new form CadastroAtividades */
	public CadastroAtividades() {
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
				"Cadastro de Atividades"));

		this.jPanelCaracteristicas = new javax.swing.JPanel();
		this.jPanelCaracteristicas.setLayout(null);
		this.jPanelCaracteristicas.setBounds(0, 30, 585, 300);
		this.jPanelCaracteristicas
				.setBorder(new javax.swing.border.TitledBorder(
						"Características Exigidas Pela Atividade"));

		this.jPanelPreAtividades = new javax.swing.JPanel();
		this.jPanelPreAtividades.setLayout(null);
		this.jPanelPreAtividades.setBounds(0, 30, 585, 300);
		this.jPanelPreAtividades.setBorder(new javax.swing.border.TitledBorder(
				"Pré Atividades"));

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
					preencheNomesPreAtividades();
					carregaListaPreAtividades();
				} else {
					limpaListaPreAtividades();
				}
			}
		});

		this.jTabbedPane.addTab("Principal", this.jPanelPrincipal);
		this.jTabbedPane.addTab("Características Necessárias",
				this.jPanelCaracteristicas);
		this.jTabbedPane.addTab("Pré Atividades", this.jPanelPreAtividades);
		this.jTabbedPane.setSelectedIndex(0);
		this.jTabbedPane.setBounds(0, 30, 590, 310);
		// adiciona os tabs
		add(this.jTabbedPane);

		// Inicializa os componentes
		this.jLabelNomeAtividade = new javax.swing.JLabel();
		this.jTextFieldNomeAtividade = new javax.swing.JTextField();
		this.jLabelDataInicial = new JLabel();
		this.jLabelDataFinal = new JLabel();
		this.jLabelNumHorasDia = new JLabel();
		this.jLabelResposavel = new JLabel();
		this.jLabelTipoCaracteristica = new JLabel();
		this.jTextFieldNumHorasDia = new javax.swing.JTextField();
		this.jTableAtividade = null;
		this.jToolBarPrincipal = new javax.swing.JToolBar();
		this.jButtonIncluirAtividade = new javax.swing.JButton();
		this.jButtonAlterarAtividade = new javax.swing.JButton();
		this.jButtonExcluirAtividade = new javax.swing.JButton();
		this.jButtonConfirmarAtividade = new javax.swing.JButton();
		this.jButtonCancelarAtividade = new javax.swing.JButton();
		this.jTextPaneTituloTela = new javax.swing.JTextPane();
		this.jScrollPanePrincipal = new JScrollPane();
		this.jDatePickerDtIni = new JDatePicker();
		this.jDatePickerDtFim = new JDatePicker();
		// Tab Caracteristicas
		this.jLabelAtividadeCaracteristica = new javax.swing.JLabel();
		this.jLabelNomeCaracteristica = new javax.swing.JLabel();
		this.jLabelValorCaracteristica = new javax.swing.JLabel();
		this.jTextFieldAtividadeCaracteristica = new javax.swing.JTextField();
		this.jComboboxTipoCaracteristica = new JComboBox();
		this.jComboboxPessoas = new JComboBox();
		this.jComboboxNomeCaracteristica = new JComboBox();
		this.jComboboxValorCaracteristica = new JComboBox();
		this.jButtonIncluirCaracteristica = new javax.swing.JButton();
		this.jButtonAlterarCaracteristica = new javax.swing.JButton();
		this.jButtonExcluirCaracteristica = new javax.swing.JButton();
		this.jButtonConfirmarCaracteristica = new javax.swing.JButton();
		this.jButtonCancelarCaracteristica = new javax.swing.JButton();
		this.jToolBarCaracteristica = new javax.swing.JToolBar();
		this.jScrollPaneCaracteristica = new JScrollPane();
		this.jTableCaracteristica = null;
		// TAB Pre Atividades
		this.jTablePreAtividades = null;
		this.jScrollPanePreAtividades = new JScrollPane();
		this.jLabelAtividadePreAtividade = new JLabel();
		this.jLabelPreAtividade = new JLabel();
		this.jTextfieldAtividadePreAtividade = new JTextField();
		this.jComboboxPreAtividade = new JComboBox();
		this.jButtonIncluirPreAtividade = new javax.swing.JButton();
		this.jButtonAlterarPreAtividade = new javax.swing.JButton();
		this.jButtonExcluirPreAtividade = new javax.swing.JButton();
		this.jButtonConfirmaPreAtividade = new javax.swing.JButton();
		this.jButtonCancelaPreAtividade = new javax.swing.JButton();
		this.jToolbarPreAtividade = new javax.swing.JToolBar();

		class MyDefaultTableModel extends DefaultTableModel {

			public MyDefaultTableModel(Object[] columnNames, int value) {
				super(columnNames, value);
			}

			public boolean isCellEditable(int row, int col) {
				return false;
			}
		}

		// Cria tabela baseada no modelo definido e seta sua posição
		Object[] columnNames = new Object[5];
		columnNames[0] = "Nome";
		columnNames[1] = "Data Início";
		columnNames[2] = "Data Fim";
		columnNames[3] = "Horas/Dia";
		columnNames[4] = "Responsável";

		DefaultTableModel tblModel = new MyDefaultTableModel(columnNames, 0);

		this.jTableAtividade = new javax.swing.JTable(tblModel);
		this.jTableAtividade.setBounds(10, 160, 565, 85);
		this.jTableAtividade
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.jTableAtividade
				.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						selecionaAtividade();
					}
				});

		ListSelectionModel rowSM = this.jTableAtividade.getSelectionModel();
		rowSM.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				selecionaAtividade();
			}
		});

		// ScrollPane que contém a tabela
		this.jScrollPanePrincipal.setBounds(10, 190, 565, 85);
		this.jScrollPanePrincipal.getViewport().add(this.jTableAtividade, null);

		// Adiciona scrollpane no jPanel
		this.jPanelPrincipal.add(this.jScrollPanePrincipal);

		// Label do campo "Nome"
		this.jLabelNomeAtividade.setText("Nome:");
		this.jPanelPrincipal.add(this.jLabelNomeAtividade);
		this.jLabelNomeAtividade.setBounds(160, 20, 30, 20);

		// Campo "Nome"
		this.jPanelPrincipal.add(this.jTextFieldNomeAtividade);
		this.jTextFieldNomeAtividade.setBounds(190, 20, 220, 20);
		this.jTextFieldNomeAtividade.disable();

		// Label do campo "Data início"
		this.jLabelDataInicial.setText("Data Início:");
		this.jPanelPrincipal.add(this.jLabelDataInicial);
		this.jLabelDataInicial.setBounds(138, 50, 60, 20);

		// Campo "Data Início"
		this.jPanelPrincipal.add(this.jDatePickerDtIni);
		this.jDatePickerDtIni.setBounds(190, 50, 220, 20);
		this.jDatePickerDtIni.setEnabled(false);
		this.jDatePickerDtIni.setColorDisabled(this.getBackground());

		// Label do campo "Data Fim"
		this.jLabelDataFinal.setText("Data Fim:");
		this.jPanelPrincipal.add(this.jLabelDataFinal);
		this.jLabelDataFinal.setBounds(146, 80, 93, 20);

		// Campo "Data Fim"
		this.jPanelPrincipal.add(this.jDatePickerDtFim);
		this.jDatePickerDtFim.setBounds(190, 80, 220, 20);
		this.jDatePickerDtFim.setEnabled(false);
		this.jDatePickerDtFim.setColorDisabled(this.getBackground());

		// Label do campo "Qtd Horas Dia"
		this.jLabelNumHorasDia.setText("Qtd.Horas/Dia:");
		this.jPanelPrincipal.add(this.jLabelNumHorasDia);
		this.jLabelNumHorasDia.setBounds(120, 110, 80, 20);

		
		// Label do campo "Responsável"
		this.jLabelResposavel.setText("Responsável:");
		this.jPanelPrincipal.add(this.jLabelResposavel);
		this.jLabelResposavel.setBounds(120, 135, 80, 20);

		
		// Campo "pessoas"
		this.jPanelPrincipal.add(this.jComboboxPessoas);
		this.jComboboxPessoas.setBounds(190, 135, 220, 20);
		this.jComboboxPessoas.disable();

		
		
		
		// Campo "Data Fim"
		this.jPanelPrincipal.add(this.jTextFieldNumHorasDia);
		this.jTextFieldNumHorasDia.setBounds(190, 110, 220, 20);
		this.jTextFieldNumHorasDia.disable();

		// Botão inserir
		this.jButtonIncluirAtividade.setText("Inserir  ");
		this.jButtonIncluirAtividade.setIcon(new ImageIcon("adiciona.gif"));
		this.jButtonIncluirAtividade
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						adicionarAtividade();
					}
				});
		this.jToolBarPrincipal.add(this.jButtonIncluirAtividade);

		// Botão alterar
		this.jButtonAlterarAtividade.setText("Alterar  ");
		this.jButtonAlterarAtividade.setIcon(new ImageIcon("lapis.gif"));
		this.jButtonAlterarAtividade
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						alterarAtividade();
					}
				});
		this.jToolBarPrincipal.add(this.jButtonAlterarAtividade);

		// Botão excluir
		this.jButtonExcluirAtividade.setText("Excluir  ");
		this.jButtonExcluirAtividade.setIcon(new ImageIcon("Remove.gif"));
		this.jButtonExcluirAtividade
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						excluirAtividade();
					}
				});
		this.jToolBarPrincipal.add(this.jButtonExcluirAtividade);

		// Botão confirmar
		this.jButtonConfirmarAtividade.setText("Confirma  ");
		this.jButtonConfirmarAtividade.setIcon(new ImageIcon("Confirma.gif"));
		this.jButtonConfirmarAtividade.hide();
		this.jButtonConfirmarAtividade
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						confirmaOperacao();
					}
				});
		this.jToolBarPrincipal.add(this.jButtonConfirmarAtividade);

		// Botão cancelar
		this.jButtonCancelarAtividade.setText("Cancela  ");
		this.jButtonCancelarAtividade.setIcon(new ImageIcon("Cancela.gif"));
		this.jButtonCancelarAtividade.hide();
		this.jButtonCancelarAtividade
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						cancelaOperacao();
					}
				});
		this.jToolBarPrincipal.add(this.jButtonCancelarAtividade);

		// Adiciona toolbar ao panel
		this.jToolBarPrincipal.setFloatable(false);
		this.jPanelPrincipal.add(this.jToolBarPrincipal);
		this.jToolBarPrincipal.setBounds(385, 165, 190, 25);
		// this.jToolBarPrincipal.setBorder(new LineBorder(Color.BLACK));

		// Título do panel
		this.jTextPaneTituloTela
				.setBackground(new java.awt.Color(153, 204, 255));
		this.jTextPaneTituloTela.disable();
		this.jTextPaneTituloTela.setFont(new java.awt.Font(
				"Microsoft Sans Serif", 1, 18));
		this.jTextPaneTituloTela.setText("Cadastro de Atividades");
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
		this.jTableCaracteristica.setBounds(10, 160, 565, 115);
		this.jTableCaracteristica
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.jTableCaracteristica
				.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						selecionaCaracteristica();
					}
				});

		ListSelectionModel rowSM2 = this.jTableCaracteristica
				.getSelectionModel();
		rowSM2.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				selecionaCaracteristica();
			}
		});

		// ScrollPane que contém a tabela
		this.jScrollPaneCaracteristica.setBounds(10, 160, 565, 115);
		this.jScrollPaneCaracteristica.getViewport().add(
				this.jTableCaracteristica, null);

		// Adiciona scrollpane no jPanel
		this.jPanelCaracteristicas.add(this.jScrollPaneCaracteristica);

		// Label do campo "Atividade"
		this.jLabelAtividadeCaracteristica.setText("Atividade:");
		this.jPanelCaracteristicas.add(this.jLabelAtividadeCaracteristica);
		this.jLabelAtividadeCaracteristica.setBounds(140, 20, 60, 20);

		// Campo "Atividade"
		this.jPanelCaracteristicas.add(this.jTextFieldAtividadeCaracteristica);
		this.jTextFieldAtividadeCaracteristica.setBounds(190, 20, 220, 20);
		this.jTextFieldAtividadeCaracteristica.disable();

		// Label do campo "Tipo de Característica"
		this.jLabelTipoCaracteristica.setText("Tipo Característica:");
		this.jPanelCaracteristicas.add(this.jLabelTipoCaracteristica);
		this.jLabelTipoCaracteristica.setBounds(95, 50, 100, 20);

		// Campo "Tipo Caracteristica"
		this.jPanelCaracteristicas.add(this.jComboboxTipoCaracteristica);
		this.jComboboxTipoCaracteristica.setBounds(190, 50, 220, 20);
		this.jComboboxTipoCaracteristica.disable();

		// Label do campo "Característica"
		this.jLabelNomeCaracteristica.setText("Característica:");
		this.jPanelCaracteristicas.add(this.jLabelNomeCaracteristica);
		this.jLabelNomeCaracteristica.setBounds(119, 80, 70, 20);

		// Campo "Característica"
		this.jPanelCaracteristicas.add(this.jComboboxNomeCaracteristica);
		this.jComboboxNomeCaracteristica.setBounds(190, 80, 220, 20);
		this.jComboboxNomeCaracteristica.disable();

		// Label do campo "Valor"
		this.jLabelValorCaracteristica.setText("Valor:");
		this.jPanelCaracteristicas.add(this.jLabelValorCaracteristica);
		this.jLabelValorCaracteristica.setBounds(160, 110, 30, 20);

		// Campo "Valor"
		this.jPanelCaracteristicas.add(this.jComboboxValorCaracteristica);
		this.jComboboxValorCaracteristica.setBounds(190, 110, 220, 20);
		this.jComboboxValorCaracteristica.disable();

		// Botão inserir
		this.jButtonIncluirCaracteristica.setText("Inserir  ");
		this.jButtonIncluirCaracteristica
				.setIcon(new ImageIcon("Adiciona.gif"));
		this.jButtonIncluirCaracteristica
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						adicionarCaracteristica();
					}
				});
		this.jToolBarCaracteristica.add(this.jButtonIncluirCaracteristica);

		// Botão alterar
		this.jButtonAlterarCaracteristica.setText("Alterar  ");
		this.jButtonAlterarCaracteristica.setIcon(new ImageIcon("lapis.gif"));
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
		this.jButtonConfirmarCaracteristica.setIcon(new ImageIcon(
				"Confirma.gif"));
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
		this.jButtonCancelarCaracteristica
				.setIcon(new ImageIcon("Cancela.gif"));
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
		this.jToolBarCaracteristica.setBounds(385, 135, 190, 25);

		/***********************************************************************
		 * Tratamento do TAB Pré Atividades
		 **********************************************************************/

		// Cria tabela baseada no modelo definido e seta sua posição
		Object[] columnNamesPreAtividades = new Object[1];
		columnNamesPreAtividades[0] = "Pré Atividade";

		DefaultTableModel tblModelPreAtividades = new MyDefaultTableModel(
				columnNamesPreAtividades, 0);

		this.jTablePreAtividades = new javax.swing.JTable(tblModelPreAtividades);
		this.jTablePreAtividades.setBounds(10, 105, 565, 170);
		this.jTablePreAtividades
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.jTablePreAtividades
				.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						selecionaPreAtividade();
					}
				});

		ListSelectionModel rowSM3 = this.jTablePreAtividades
				.getSelectionModel();
		rowSM3.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				selecionaPreAtividade();
			}
		});

		// ScrollPane que contém a tabela
		this.jScrollPanePreAtividades.setBounds(10, 105, 565, 170);
		this.jScrollPanePreAtividades.getViewport().add(
				this.jTablePreAtividades, null);

		// Adiciona scrollpane no jPanel
		this.jPanelPreAtividades.add(this.jScrollPanePreAtividades);

		// Label do campo "Atividade"
		this.jLabelAtividadePreAtividade.setText("Atividade:");
		this.jPanelPreAtividades.add(this.jLabelAtividadePreAtividade);
		this.jLabelAtividadePreAtividade.setBounds(140, 20, 50, 20);

		// Campo "Atividade"
		this.jPanelPreAtividades.add(this.jTextfieldAtividadePreAtividade);
		this.jTextfieldAtividadePreAtividade.setBounds(190, 20, 220, 20);
		this.jTextfieldAtividadePreAtividade.disable();

		// Label do campo "Pré Atividade"
		this.jLabelPreAtividade.setText("Pré Atividade:");
		this.jPanelPreAtividades.add(this.jLabelPreAtividade);
		this.jLabelPreAtividade.setBounds(120, 50, 70, 20);

		// Campo "Pré Atividade"
		this.jPanelPreAtividades.add(this.jComboboxPreAtividade);
		this.jComboboxPreAtividade.setBounds(190, 50, 220, 20);
		this.jComboboxPreAtividade.disable();

		// Botão inserir
		this.jButtonIncluirPreAtividade.setText("Inserir  ");
		this.jButtonIncluirPreAtividade.setIcon(new ImageIcon("Adiciona.gif"));
		this.jButtonIncluirPreAtividade
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						adicionarPreAtividade();
					}
				});
		this.jToolbarPreAtividade.add(this.jButtonIncluirPreAtividade);

		// Botão alterar
		this.jButtonAlterarPreAtividade.setText("Alterar  ");
		this.jButtonAlterarPreAtividade.setIcon(new ImageIcon("lapis.gif"));
		this.jButtonAlterarPreAtividade.disable();
		this.jToolbarPreAtividade.add(this.jButtonAlterarPreAtividade);

		// Botão excluir
		this.jButtonExcluirPreAtividade.setText("Excluir  ");
		this.jButtonExcluirPreAtividade.setIcon(new ImageIcon("Remove.gif"));
		this.jButtonExcluirPreAtividade
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						excluirPreAtividade();
					}
				});
		this.jToolbarPreAtividade.add(this.jButtonExcluirPreAtividade);

		// Botão confirmar
		this.jButtonConfirmaPreAtividade.setText("Confirma  ");
		this.jButtonConfirmaPreAtividade.setIcon(new ImageIcon("Confirma.gif"));
		this.jButtonConfirmaPreAtividade.hide();
		this.jButtonConfirmaPreAtividade
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						confirmaOperacaoPreAtividade();
					}
				});
		this.jToolbarPreAtividade.add(this.jButtonConfirmaPreAtividade);

		// Botão cancelar
		this.jButtonCancelaPreAtividade.setText("Cancela  ");
		this.jButtonCancelaPreAtividade.setIcon(new ImageIcon("Cancela.gif"));
		this.jButtonCancelaPreAtividade.hide();
		this.jButtonCancelaPreAtividade
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						cancelaOperacaoPreAtividade();
					}
				});
		this.jToolbarPreAtividade.add(this.jButtonCancelaPreAtividade);

		this.jToolbarPreAtividade.setFloatable(false);
		// Adiciona toolbar ao panel
		this.jPanelPreAtividades.add(this.jToolbarPreAtividade);
		this.jToolbarPreAtividade.setBounds(385, 80, 190, 25);

	}

	/***************************************************************************
	 * Métodos referentes à TAB GERAL
	 * 
	 **************************************************************************/
	protected void adicionarAtividade() {
		/* Controle da barra de botões */
		this.jButtonIncluirAtividade.hide();
		this.jButtonAlterarAtividade.hide();
		this.jButtonExcluirAtividade.hide();
		this.jButtonConfirmarAtividade.show();
		this.jButtonCancelarAtividade.show();

		this.jTextFieldNomeAtividade.enable();
		this.jDatePickerDtIni.setEnabled(true);
		this.jDatePickerDtFim.setEnabled(true);
		this.jComboboxPessoas.enable();
		this.jTextFieldNumHorasDia.enable();

		this.jTextFieldNomeAtividade.setText("");
		GregorianCalendar gCalendar = new GregorianCalendar();
		this.jDatePickerDtIni.set(gCalendar.get(Calendar.YEAR), gCalendar
				.get(Calendar.MONTH), gCalendar.get(Calendar.DAY_OF_MONTH));
		gCalendar = new GregorianCalendar();
		this.jDatePickerDtFim.set(gCalendar.get(Calendar.YEAR), gCalendar
				.get(Calendar.MONTH), gCalendar.get(Calendar.DAY_OF_MONTH));
		this.jTextFieldNumHorasDia.setText("");
		this.preenchePessoas();
		this.validate();
		this.repaint();

		this.operacao = "INSERIR";
	}

	protected void alterarAtividade() {
		int linha = this.jTableAtividade.getSelectedRow();
		if (linha != -1) {
			this.jButtonIncluirAtividade.hide();
			this.jButtonAlterarAtividade.hide();
			this.jButtonExcluirAtividade.hide();
			this.jButtonConfirmarAtividade.show();
			this.jButtonCancelarAtividade.show();

			this.jDatePickerDtIni.setEnabled(true);
			this.jDatePickerDtFim.setEnabled(true);
			this.jTextFieldNumHorasDia.enable();
			this.jComboboxPessoas.enable();
			this.preenchePessoas();

			this.validate();
			this.repaint();
			this.selecionarPessoa();

			this.operacao = "ALTERAR";
		}
	}

	private void gerenciaBotoesConfirma() {
		// Controle da barra de botões
		this.jButtonIncluirAtividade.show();
		this.jButtonAlterarAtividade.show();
		this.jButtonExcluirAtividade.show();
		this.jButtonConfirmarAtividade.hide();
		this.jButtonCancelarAtividade.hide();

		this.jTextFieldNomeAtividade.disable();
		this.jDatePickerDtIni.setEnabled(false);
		this.jDatePickerDtFim.setEnabled(false);
		this.jTextFieldNumHorasDia.disable();
		this.jComboboxPessoas.disable();

		this.validate();
		this.repaint();
	}

	protected void confirmaOperacao() {
		if (this.operacao.compareToIgnoreCase("INSERIR") == 0) {
			this.confirmaAdicionarAtividade();
		}
		if (this.operacao.compareToIgnoreCase("ALTERAR") == 0) {
			this.confirmaAlterarAtividade();
		}
	}

	protected void cancelaOperacao() {
		// Controle da barra de botões
		this.jButtonIncluirAtividade.show();
		this.jButtonAlterarAtividade.show();
		this.jButtonExcluirAtividade.show();
		this.jButtonConfirmarAtividade.hide();
		this.jButtonCancelarAtividade.hide();

		this.jTextFieldNomeAtividade.disable();
		this.jDatePickerDtIni.setEnabled(false);
		this.jDatePickerDtFim.setEnabled(false);
		this.jTextFieldNumHorasDia.disable();
		this.jComboboxPessoas.disable();
		
		this.selecionaAtividade();

		this.validate();
		this.repaint();
	}

	protected void confirmaAdicionarAtividade() {
		Object[] linha = new Object[5];
		linha[0] = this.jTextFieldNomeAtividade.getText();
		linha[1] = this.jDatePickerDtIni.getDayString() + "/"
				+ this.jDatePickerDtIni.getMonthString() + "/"
				+ this.jDatePickerDtIni.getYearString();
		linha[2] = this.jDatePickerDtFim.getDayString() + "/"
				+ this.jDatePickerDtFim.getMonthString() + "/"
				+ this.jDatePickerDtFim.getYearString();
		linha[3] = this.jTextFieldNumHorasDia.getText();
		linha[4] = this.jComboboxPessoas.getSelectedItem();

		if ((this.jTextFieldNomeAtividade.getText().length() == 0)
				|| (this.jTextFieldNumHorasDia.getText().length() == 0)) {
			JOptionPane.showMessageDialog(this,
					"Todos os campos devem ser preenchidos.", "Alerta",
					JOptionPane.WARNING_MESSAGE);
		} else {

			int retorno = JanelaPrincipal.getAplicacao().inserirAtividade(
					this.jTextFieldNomeAtividade.getText(), (String) linha[1],
					(String) linha[2], (String) linha[3],this.jComboboxPessoas.getSelectedItem().toString());

			switch (retorno) {
			case 0:
				((DefaultTableModel) this.jTableAtividade.getModel())
						.addRow(linha);
				this.gerenciaBotoesConfirma();
				this.jTableAtividade.setRowSelectionInterval(
						this.jTableAtividade.getRowCount() - 1,
						this.jTableAtividade.getRowCount() - 1);
				break;
			case -1:
				JOptionPane.showMessageDialog(this,
						"A data final não pode ser anterior à data inicial.",
						"Alerta", JOptionPane.WARNING_MESSAGE);
				break;
			case -2:
				JOptionPane
						.showMessageDialog(
								this,
								"O campo quantidade de horas por dia deve ser um inteiro.",
								"Alerta", JOptionPane.WARNING_MESSAGE);
				break;
			case -3:
				JOptionPane.showMessageDialog(this,
						"O campo quantidade de horas deve ser maior que zero.",
						"Alerta", JOptionPane.WARNING_MESSAGE);
				break;
			case -4:
				JOptionPane
						.showMessageDialog(
								this,
								"Não é possível inserir duas atividades com o mesmo nome.",
								"Alerta", JOptionPane.WARNING_MESSAGE);
				break;
			}
		}
	}

	protected void confirmaAlterarAtividade() {
		Object[] linha = new Object[5];
		linha[0] = this.jTextFieldNomeAtividade.getText();
		linha[1] = this.jDatePickerDtIni.getDayString() + "/"
				+ this.jDatePickerDtIni.getMonthString() + "/"
				+ this.jDatePickerDtIni.getYearString();
		linha[2] = this.jDatePickerDtFim.getDayString() + "/"
				+ this.jDatePickerDtFim.getMonthString() + "/"
				+ this.jDatePickerDtFim.getYearString();
		linha[3] = this.jTextFieldNumHorasDia.getText();
		linha[4] = this.jComboboxPessoas.getSelectedItem().toString();

		
		
		if ((this.jTextFieldNomeAtividade.getText().length() == 0)
				|| (this.jTextFieldNumHorasDia.getText().length() == 0)) {
			JOptionPane.showMessageDialog(this,
					"Todos os campos devem ser preenchidos.", "Alerta",
					JOptionPane.WARNING_MESSAGE);
		} else {
			int linhaSelecionada = this.jTableAtividade.getSelectedRow();
			if (linhaSelecionada != -1) {

				int retorno = JanelaPrincipal.getAplicacao()
						.alterarAtividade(
								this.jTextFieldNomeAtividade.getText(),
								(String) linha[1], (String) linha[2],
								(String) linha[3],
								this.jComboboxPessoas.getSelectedItem().toString() );

				switch (retorno) {
				case 0:
					((DefaultTableModel) this.jTableAtividade.getModel())
							.setValueAt(linha[0], linhaSelecionada, 0);
					((DefaultTableModel) this.jTableAtividade.getModel())
							.setValueAt(linha[1], linhaSelecionada, 1);
					((DefaultTableModel) this.jTableAtividade.getModel())
							.setValueAt(linha[2], linhaSelecionada, 2);
					((DefaultTableModel) this.jTableAtividade.getModel())
							.setValueAt(linha[3], linhaSelecionada, 3);
					((DefaultTableModel) this.jTableAtividade.getModel())
					.setValueAt(linha[4], linhaSelecionada, 4);
					this.gerenciaBotoesConfirma();
					break;
				case -1:
					JOptionPane
							.showMessageDialog(
									this,
									"O campo quantidade de horas por dia deve ser um inteiro.",
									"Alerta", JOptionPane.WARNING_MESSAGE);
					break;
				case -2:
					JOptionPane
							.showMessageDialog(
									this,
									"O campo quantidade de horas deve ser maior que zero.",
									"Alerta", JOptionPane.WARNING_MESSAGE);
					break;
				case -3:
					JOptionPane
							.showMessageDialog(
									this,
									"A data final não pode ser anterior à data inicial.",
									"Alerta", JOptionPane.WARNING_MESSAGE);
					break;
				}
			}
		}
	}

	protected void excluirAtividade() {
		int linha = this.jTableAtividade.getSelectedRow();

		if (linha != -1) {
			int retorno = JanelaPrincipal.getAplicacao().excluirAtividade(
					this.jTextFieldNomeAtividade.getText());
			if (retorno == 0) {
				((DefaultTableModel) this.jTableAtividade.getModel())
						.removeRow(this.jTableAtividade.getSelectedRow());
				if (linha > 0) {
					this.jTableAtividade.setRowSelectionInterval(linha - 1,
							linha - 1);
				} else {
					if (this.jTableAtividade.getRowCount() > 0) {
						this.jTableAtividade.setRowSelectionInterval(linha,
								linha);
					} else {
						this.jTextFieldNomeAtividade.setText("");
						GregorianCalendar gCalendar = new GregorianCalendar();
						this.jDatePickerDtIni.set(gCalendar.get(Calendar.YEAR),
								gCalendar.get(Calendar.MONTH), gCalendar
										.get(Calendar.DAY_OF_MONTH));
						this.jDatePickerDtFim.set(gCalendar.get(Calendar.YEAR),
								gCalendar.get(Calendar.MONTH), gCalendar
										.get(Calendar.DAY_OF_MONTH));
						this.jTextFieldNumHorasDia.setText("");
					}
				}
				this.selecionaAtividade();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Linha não selecionada.",
					"Alerta", JOptionPane.WARNING_MESSAGE);
		}

	}

	protected void selecionaAtividade() {
		Object[] linha = new Object[5];
		linha[0] = this.jTextFieldNomeAtividade.getText();
		linha[1] = this.jDatePickerDtIni.getDayString() + "/"
				+ this.jDatePickerDtIni.getMonthString() + "/"
				+ this.jDatePickerDtIni.getYearString();
		linha[2] = this.jDatePickerDtFim.getDayString() + "/"
				+ this.jDatePickerDtFim.getMonthString() + "/"
				+ this.jDatePickerDtFim.getYearString();
		linha[3] = this.jTextFieldNumHorasDia.getText();
		linha[4] = this.jComboboxPessoas.getSelectedItem();
		

		int linhaSelecionada = this.jTableAtividade.getSelectedRow();
		
		
		if (linhaSelecionada != -1) {
			this.jTextFieldNomeAtividade
					.setText((String) ((DefaultTableModel) this.jTableAtividade
							.getModel()).getValueAt(linhaSelecionada, 0));

			int dia = Integer
					.parseInt(((String) ((DefaultTableModel) this.jTableAtividade
							.getModel()).getValueAt(linhaSelecionada, 1))
							.substring(0, 2));

			int mes = Integer
					.parseInt(((String) ((DefaultTableModel) this.jTableAtividade
							.getModel()).getValueAt(linhaSelecionada, 1))
							.substring(3, 5));

			int ano = Integer
					.parseInt(((String) ((DefaultTableModel) this.jTableAtividade
							.getModel()).getValueAt(linhaSelecionada, 1))
							.substring(6));
			this.jDatePickerDtIni.set(ano, mes - 1, dia);
			dia = Integer
					.parseInt(((String) ((DefaultTableModel) this.jTableAtividade
							.getModel()).getValueAt(linhaSelecionada, 2))
							.substring(0, 2));

			mes = Integer
					.parseInt(((String) ((DefaultTableModel) this.jTableAtividade
							.getModel()).getValueAt(linhaSelecionada, 2))
							.substring(3, 5));

			ano = Integer
					.parseInt(((String) ((DefaultTableModel) this.jTableAtividade
							.getModel()).getValueAt(linhaSelecionada, 2))
							.substring(6));
			this.jDatePickerDtFim.set(ano, mes - 1, dia);

			this.jTextFieldNumHorasDia
					.setText((String) ((DefaultTableModel) this.jTableAtividade
							.getModel()).getValueAt(linhaSelecionada, 3));

			this.jTextFieldAtividadeCaracteristica
					.setText((String) ((DefaultTableModel) this.jTableAtividade
							.getModel()).getValueAt(linhaSelecionada, 0));
			

			if (this.jComboboxPessoas.getItemCount() == 0) {
				this.preenchePessoas();
			}
			
			this.selecionarPessoa();
		
			
		}
	}
	
	public void selecionarPessoa(){
		
		for (int i = 0; i < this.jComboboxPessoas.getItemCount(); i++) {
			if(this.jComboboxPessoas.getItemAt(i).toString().equals(((DefaultTableModel) this.jTableAtividade.getModel()).getValueAt(this.jTableAtividade.getSelectedRow(), 4))){
				this.jComboboxPessoas.setSelectedIndex(i);
				break;
			}
		}
		
	}

	protected void carregaListaAtividade() {
		Object[] linha = new Object[5];
		ArrayList listaAtividades = new ArrayList();

		listaAtividades = JanelaPrincipal.getAplicacao().getAtividades();

		Iterator iter1 = listaAtividades.iterator();
		while (iter1.hasNext()) {
			Atividade a = (Atividade) iter1.next();
			linha[0] = a.getNome();
			linha[1] = a.getDataInicioString();
			linha[2] = a.getDataFimString();
			linha[3] = a.getNumeroHorasDia() + "";
			linha[4] = a.getPessoa();

			((DefaultTableModel) this.jTableAtividade.getModel()).addRow(linha);
		}
		if (listaAtividades.size() > 0) {
			this.jTableAtividade.setRowSelectionInterval(0, 0);
			selecionaAtividade();
		}
	}

	/***************************************************************************
	 * Métodos referentes à TAB CARACTERÍSTICAS
	 * 
	 **************************************************************************/
	protected void adicionarCaracteristica() {

		if (this.jTableAtividade.getSelectedRow() != -1) {
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
			if (this.jComboboxPessoas.getItemCount() > 0) {
				this.jComboboxPessoas.setSelectedIndex(0);
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

		} else {
			JOptionPane.showMessageDialog(this,
					"É preciso selecionar uma atividade.", "Alerta",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	protected void alterarCaracteristica() {

		if (this.jTableAtividade.getSelectedRow() != -1) {
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
		} else {
			JOptionPane.showMessageDialog(this,
					"É preciso selecionar uma atividade.", "Alerta",
					JOptionPane.WARNING_MESSAGE);
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

	
	

	protected void preenchePessoas() {		
		Profissional tdc;	
		this.jComboboxPessoas.removeAllItems();

		tdc = (Profissional) this.jComboboxPessoas.getSelectedItem();			
		
		this.jComboboxPessoas.addItem("");

		Iterator iter = JanelaPrincipal.getAplicacao().getPessoas(null).iterator();
		while (iter.hasNext()) {
			Profissional dc = (Profissional) iter.next();
			this.jComboboxPessoas.addItem(dc);
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
					.inserirCaracteristicaAtividade(
							this.jTextFieldAtividadeCaracteristica.getText(),
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
				JOptionPane.showMessageDialog(this,
						"Característica já atribuída à atividade.", "Alerta",
						JOptionPane.WARNING_MESSAGE);
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
						.alterarCaracteristicaAtividade(
								this.jTextFieldAtividadeCaracteristica
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
		if (this.jTableAtividade.getSelectedRow() != -1) {

			int linha = this.jTableCaracteristica.getSelectedRow();

			if (linha != -1) {
				JanelaPrincipal
						.getAplicacao()
						.excluirCaracteristicaAtividade(
								this.jTextFieldAtividadeCaracteristica
										.getText(),
								(DefinicaoCaracteristica) this.jComboboxNomeCaracteristica
										.getSelectedItem());

				((DefaultTableModel) this.jTableCaracteristica.getModel())
						.removeRow(this.jTableCaracteristica.getSelectedRow());
				if (linha > 0) {
					this.jTableCaracteristica.setRowSelectionInterval(
							linha - 1, linha - 1);
				} else {
					if (this.jTableCaracteristica.getRowCount() > 0) {
						this.jTableCaracteristica.setRowSelectionInterval(
								linha, linha);
					} else {
						if (this.jComboboxNomeCaracteristica.getItemCount() > 0)
							this.jComboboxNomeCaracteristica
									.setSelectedIndex(0);
						if (this.jComboboxValorCaracteristica.getItemCount() > 0)
							this.jComboboxValorCaracteristica
									.setSelectedIndex(0);
						if (this.jComboboxTipoCaracteristica.getItemCount() > 0)
							this.jComboboxTipoCaracteristica
									.setSelectedIndex(0);
					}
				}
				this.selecionaCaracteristica();
			} else {
				JOptionPane.showMessageDialog(this, "Linha não selecionada.",
						"Alerta", JOptionPane.WARNING_MESSAGE);
			}

		} else {
			JOptionPane.showMessageDialog(this,
					"É preciso selecionar uma atividade.", "Alerta",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	protected void carregaListaCaracteristicas() {
		int linhaSelecionada = this.jTableAtividade.getSelectedRow();
		Object[] linha = new Object[3];
		ArrayList listaCaracteristicas = new ArrayList();
		String nomeAtividade = "";
		if (linhaSelecionada != -1) {
			nomeAtividade = (String) ((DefaultTableModel) this.jTableAtividade
					.getModel()).getValueAt(linhaSelecionada, 0);
			this.jTextFieldAtividadeCaracteristica.setText(nomeAtividade);
			listaCaracteristicas = JanelaPrincipal.getAplicacao()
					.obterCaracteristicasAtividade(nomeAtividade);

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
	 * Métodos referentes a TAB Pre Atividades
	 **************************************************************************/
	protected void adicionarPreAtividade() {

		if (this.jTableAtividade.getSelectedRow() != -1) {
			this.jButtonIncluirPreAtividade.hide();
			this.jButtonAlterarPreAtividade.hide();
			this.jButtonExcluirPreAtividade.hide();
			this.jButtonConfirmaPreAtividade.show();
			this.jButtonCancelaPreAtividade.show();

			this.jComboboxPreAtividade.enable();

			if (this.jComboboxPreAtividade.getItemCount() > 0) {
				this.jComboboxPreAtividade.setSelectedIndex(0);
			}

			this.validate();
			this.repaint();

			this.operacaoPreAtividades = "INSERIR";

		} else {
			JOptionPane.showMessageDialog(this,
					"É preciso selecionar uma atividade.", "Alerta",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void gerenciaBotoesConfirmaPreAtividades() {
		// Controle da barra de botões

		this.jButtonIncluirPreAtividade.show();
		this.jButtonAlterarPreAtividade.show();
		this.jButtonExcluirPreAtividade.show();
		this.jButtonConfirmaPreAtividade.hide();
		this.jButtonCancelaPreAtividade.hide();

		this.jComboboxPreAtividade.disable();
		this.validate();
		this.repaint();
	}

	protected void confirmaOperacaoPreAtividade() {
		if (this.operacaoPreAtividades.compareToIgnoreCase("INSERIR") == 0) {
			this.confirmaAdicionarPreAtividade();
		}
	}

	protected void cancelaOperacaoPreAtividade() {
		// Controle da barra de botões
		this.jButtonIncluirPreAtividade.show();
		this.jButtonAlterarPreAtividade.show();
		this.jButtonExcluirPreAtividade.show();
		this.jButtonConfirmaPreAtividade.hide();
		this.jButtonCancelaPreAtividade.hide();

		this.jComboboxPreAtividade.disable();

		this.selecionaPreAtividade();

		this.validate();
		this.repaint();
	}

	protected void preencheNomesPreAtividades() {

		this.jComboboxPreAtividade.removeAllItems();
		Iterator iter = JanelaPrincipal.getAplicacao().getAtividades()
				.iterator();
		while (iter.hasNext()) {
			Atividade a = (Atividade) iter.next();
			if (a.getNome().compareToIgnoreCase(
					this.jTextFieldNomeAtividade.getText()) != 0)
				this.jComboboxPreAtividade.addItem(a);
		}

	}

	protected void selecionaPreAtividade() {
		int linhaSelecionada = this.jTablePreAtividades.getSelectedRow();

		if (linhaSelecionada != -1) {
			this.jComboboxPreAtividade
					.setSelectedItem(((DefaultTableModel) this.jTablePreAtividades
							.getModel()).getValueAt(linhaSelecionada, 0));
		}
	}

	protected void confirmaAdicionarPreAtividade() {
		Object[] linha = new Object[1];
		linha[0] = this.jComboboxPreAtividade.getSelectedItem();

		if (this.jComboboxNomeCaracteristica.getItemCount() <= 0) {
			JOptionPane.showMessageDialog(this,
					"Todos os campos devem ser preenchidos.", "Alerta",
					JOptionPane.WARNING_MESSAGE);
		} else {

			int retorno = JanelaPrincipal
					.getAplicacao()
					.inserirPreAtividade(
							this.jTextfieldAtividadePreAtividade.getText(),
							(Atividade) this.jComboboxPreAtividade
									.getSelectedItem());

			switch (retorno) {
			case 0:
				((DefaultTableModel) this.jTablePreAtividades.getModel())
						.addRow(linha);
				this.gerenciaBotoesConfirmaPreAtividades();
				this.jTablePreAtividades.setRowSelectionInterval(
						this.jTablePreAtividades.getRowCount() - 1,
						this.jTablePreAtividades.getRowCount() - 1);
				break;
			case -1:
				JOptionPane.showMessageDialog(this,
						"Pré atividade já atribuída à atividade.", "Alerta",
						JOptionPane.WARNING_MESSAGE);
				break;
			}
		}
	}

	protected void excluirPreAtividade() {
		if (this.jTableAtividade.getSelectedRow() != -1) {

			int linha = this.jTablePreAtividades.getSelectedRow();

			if (linha != -1) {
				JanelaPrincipal
						.getAplicacao()
						.excluirPreAtividade(
								this.jTextfieldAtividadePreAtividade
										.getText(),
								(Atividade) this.jComboboxPreAtividade
										.getSelectedItem());

				((DefaultTableModel) this.jTablePreAtividades.getModel())
						.removeRow(this.jTablePreAtividades.getSelectedRow());
				if (linha > 0) {
					this.jTablePreAtividades.setRowSelectionInterval(
							linha - 1, linha - 1);
				} else {
					if (this.jTablePreAtividades.getRowCount() > 0) {
						this.jTablePreAtividades.setRowSelectionInterval(
								linha, linha);
					} else {
						if (this.jComboboxPreAtividade.getItemCount() > 0)
							this.jComboboxPreAtividade
									.setSelectedIndex(0);
					}
				}
				this.selecionaPreAtividade();
			} else {
				JOptionPane.showMessageDialog(this, "Linha não selecionada.",
						"Alerta", JOptionPane.WARNING_MESSAGE);
			}

		} else {
			JOptionPane.showMessageDialog(this,
					"É preciso selecionar uma atividade.", "Alerta",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	protected void carregaListaPreAtividades() {
		int linhaSelecionada = this.jTableAtividade.getSelectedRow();
		Object[] linha = new Object[1];
		ArrayList listaPreAtividades = new ArrayList();
		String nomeAtividade = "";
		if (linhaSelecionada != -1) {
			nomeAtividade = (String) ((DefaultTableModel) this.jTableAtividade
					.getModel()).getValueAt(linhaSelecionada, 0);
			this.jTextfieldAtividadePreAtividade.setText(nomeAtividade);
			listaPreAtividades = JanelaPrincipal.getAplicacao()
					.obterPreAtividadesAtividade(nomeAtividade);

			Iterator iter1 = listaPreAtividades.iterator();
			while (iter1.hasNext()) {
				Atividade a = (Atividade) iter1.next();
				linha[0] = a.getNome();

				((DefaultTableModel) this.jTablePreAtividades.getModel())
						.addRow(linha);
			}
			if (listaPreAtividades.size() > 0) {
				this.jTablePreAtividades.setRowSelectionInterval(0, 0);
				selecionaPreAtividade();
			}
		}
	}

	protected void limpaListaPreAtividades() {
		try {
			if (this.jComboboxPreAtividade.getItemCount() > 0)
				this.jComboboxPreAtividade.setSelectedIndex(0);

			while (((DefaultTableModel) this.jTablePreAtividades.getModel())
					.getRowCount() > 0) {
				((DefaultTableModel) this.jTablePreAtividades.getModel())
						.removeRow(0);
			}
		} catch (Exception e) {
		}
	}

	// Variables declaration - do not modify

	private String operacao = "";

	private String operacaoCaracteristicas = "";

	private String operacaoPreAtividades = "";

	/**
	 * TabbedPane que divide a tela em
	 */
	private JTabbedPane jTabbedPane;

	/**
	 * Painel principal (onde os profissionais são inseridos)
	 */
	private javax.swing.JPanel jPanelPrincipal;

	/**
	 * Painel de características possuídas pela atividade
	 */
	private javax.swing.JPanel jPanelCaracteristicas;

	/**
	 * Botão Incluir do painel principal
	 */
	private javax.swing.JButton jButtonIncluirAtividade;

	/**
	 * Botão Alterar do painel principal
	 */
	private javax.swing.JButton jButtonAlterarAtividade;

	/**
	 * Botão Excluir do painel principal
	 */
	private javax.swing.JButton jButtonExcluirAtividade;

	/**
	 * Botão Confirmar do painel principal
	 */
	private javax.swing.JButton jButtonConfirmarAtividade;

	/**
	 * Botão Cancelar do painel principal
	 */
	private javax.swing.JButton jButtonCancelarAtividade;

	/**
	 * Label do campo nome
	 */
	private javax.swing.JLabel jLabelNomeAtividade;

	private javax.swing.JLabel jLabelTipoCaracteristica;

	/**
	 * Label do campo data inicial
	 */
	private javax.swing.JLabel jLabelDataInicial;

	/**
	 * Label do campo data final
	 */
	private javax.swing.JLabel jLabelDataFinal;

	/**
	 * Label do campo número de horas do dia
	 */
	private javax.swing.JLabel jLabelNumHorasDia;

	/**
	 * Label do campo responsavel
	 */	
	private javax.swing.JLabel jLabelResposavel;
	
	
	/**
	 * Tabela do painel principal
	 */
	private javax.swing.JTable jTableAtividade;

	private javax.swing.JTextField jTextFieldNomeAtividade;

	private JDatePicker jDatePickerDtIni;

	private JDatePicker jDatePickerDtFim;

	private javax.swing.JTextField jTextFieldNumHorasDia;

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
	 * Atividade considerada na tab Caracteristicas
	 */
	private javax.swing.JLabel jLabelAtividadeCaracteristica;

	/**
	 * Label do campo nome
	 */
	private javax.swing.JLabel jLabelNomeCaracteristica;

	/**
	 * Label do campo valor
	 */
	private javax.swing.JLabel jLabelValorCaracteristica;

	private javax.swing.JTextField jTextFieldAtividadeCaracteristica;

	private javax.swing.JComboBox jComboboxTipoCaracteristica;

	private javax.swing.JComboBox jComboboxPessoas;
	
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

	// TAB Pre Atividades

	private JPanel jPanelPreAtividades;

	private JTable jTablePreAtividades;

	private JScrollPane jScrollPanePreAtividades;

	private JLabel jLabelAtividadePreAtividade;

	private JLabel jLabelPreAtividade;

	private JTextField jTextfieldAtividadePreAtividade;

	private JComboBox jComboboxPreAtividade;

	private javax.swing.JButton jButtonIncluirPreAtividade;

	private javax.swing.JButton jButtonAlterarPreAtividade;

	private javax.swing.JButton jButtonExcluirPreAtividade;

	private javax.swing.JButton jButtonConfirmaPreAtividade;

	private javax.swing.JButton jButtonCancelaPreAtividade;

	private JToolBar jToolbarPreAtividade;

	// End of variables declaration

}
