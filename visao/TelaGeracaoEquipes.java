
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * Title:        Protótipo Tese - TelaGeracaoEquipes
 * Description:  Implementação do protótipo da tese. Classe TelaGeracaoEquipes
 * Copyright:    Copyright (c) 2004
 * Company:      COPPE Sistemas
 * @author Ahilton S. Barreto
 * @version 1.0
 * Criado em 20/03/2005
 */

/**
 * @author Ahilton
 * 
 * Esta classe contém a janela da tela de geração de equipes
 */
public class TelaGeracaoEquipes extends javax.swing.JPanel implements
		ActionListener, ItemListener {

	/** Creates new form TelaGeracaoEquipes */
	public TelaGeracaoEquipes() {
		initComponents();
	}

	private void initComponents() {
		this.jLabelFatoresOtimizar = new JLabel();
		this.jLabelNumSolucoes = new JLabel();
		this.jLabelOrdenar = new JLabel();
		this.jLabelOrdenar2 = new JLabel();
		this.jTextboxNumSolucoes = new JTextField();
		this.jComboboxFatoresOtimizar = new JComboBox();
		this.jComboboxOrdenar = new JComboBox();
		this.jComboboxOrdenar2 = new JComboBox();
		this.jCheckboxTodos = new JCheckBox();
		this.jCheckboxParcial = new JCheckBox();
		this.jButtonGerar = new JButton();
		this.jEditorpaneDetalhe = new JEditorPane();
		this.jLabelCusto = new JLabel();
		this.jLabelTempo = new JLabel();
		this.jLabelTamanho = new JLabel();
		this.jLabelSubAlocacao = new JLabel();
		this.jLabelValorCusto = new JLabel();
		this.jLabelValorTamanho = new JLabel();
		this.jLabelValorSubAlocacao = new JLabel();
		this.jLabelValorTempo = new JLabel();

		this.jComboboxFatoresOtimizar.addItem("Menor Custo");
		this.jComboboxFatoresOtimizar.addItem("Menor Equipe");
		this.jComboboxFatoresOtimizar.addItem("Menor Sub-Alocação");
		this.jComboboxFatoresOtimizar.addItem("Maior Qualificação");
		this.jComboboxFatoresOtimizar.addItem("Menor Tempo");
		
		this.jComboboxOrdenar.addItem("Número da Solução");
		this.jComboboxOrdenar.addItem("Custo");
		this.jComboboxOrdenar.addItem("Tamanho da Equipe");
		this.jComboboxOrdenar.addItem("Ind. de Subalocação");
		this.jComboboxOrdenar.addItem("Tempo");
		
		this.jComboboxOrdenar2.addItem("Número da Solução");
		this.jComboboxOrdenar2.addItem("Custo");
		this.jComboboxOrdenar2.addItem("Tamanho da Equipe");
		this.jComboboxOrdenar2.addItem("Ind. de Subalocação");
		this.jComboboxOrdenar2.addItem("Tempo");
		
		
		

		// Atribui o tamanho do jPanel
		this.setSize(600, 600);
		
		// Atribui o layout do jPanel
		this.setLayout(null);

		// Inicializa os componentes (novo)

		this.jPanelPrincipal = new javax.swing.JPanel();
		this.jPanelPrincipal.setLayout(null);
		this.jPanelPrincipal.setBounds(0,30,590,270);
		this.jPanelPrincipal.setBorder(new javax.swing.border.TitledBorder("Geração das Equipes"));

		this.jPanelDetalheEquipe = new javax.swing.JPanel();
		
		this.jPanelDetalheEquipe.setLayout(null);
		this.jPanelDetalheEquipe.setBounds(0,300,590,190);
		this.jPanelDetalheEquipe.setBorder(new javax.swing.border.TitledBorder("Detalhes da Equipe"));

		
		add(this.jPanelPrincipal);
		//this.jTabbedPane.addTab("Detalhes da Equipe", this.jPanelDetalheEquipe);
		add(this.jPanelDetalheEquipe);
		

		// Inicializa os componentes
		this.jTableAlocacoes = null;
		this.jTextPaneTituloTela = new javax.swing.JTextPane();
		this.jScrollPanePrincipal = new JScrollPane();
		this.jScrollPaneDetalhe = new JScrollPane();

		class MyDefaultTableModel extends DefaultTableModel {
			
			protected int sortCol = 0;
			protected boolean isSortAsc = true;
			protected int columnsCount = 1;
			
			public MyDefaultTableModel(Object[] columnNames, int value) {
				super(columnNames, value);
			}

			public boolean isCellEditable(int row, int col) {
				return false;
			}
		}

		// Cria tabela baseada no modelo definido e seta sua posição
		Object[] columnNames = new Object[5];
		columnNames[0] = "Número";
		columnNames[1] = "Tamanho";
		columnNames[2] = "Custo";
		columnNames[3] = "Tempo";
		columnNames[4] = "Ind.Sub-alocação";

		DefaultTableModel tblModel = new MyDefaultTableModel(columnNames, 0);

		this.jTableAlocacoes = new javax.swing.JTable(tblModel);
		this.jTableAlocacoes.setBounds(10, 130, 570, 130);
		this.jTableAlocacoes
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	   ListSelectionModel rowSM = this.jTableAlocacoes.getSelectionModel();
	   rowSM.addListSelectionListener(new ListSelectionListener() {
	   public void valueChanged(ListSelectionEvent e) {
	    exibirDetalhe();	
	   }
	   });

		// ScrollPane que contém a tabela
		this.jScrollPanePrincipal.setBounds(10, 130, 570, 130);
		this.jScrollPanePrincipal.getViewport().add(this.jTableAlocacoes, null);

		// Adiciona scrollpane no jPanel
		this.jPanelPrincipal.add(this.jScrollPanePrincipal);

		this.jRadioButtonOtimizar = new JRadioButton(
				"Gerar equipes que atendam às restrições e otimizem algum fator");
		this.jRadioButtonOtimizar.setActionCommand("otimizar");

		this.jRadioButtonQuaisquer = new JRadioButton(
				"Gerar equipes quaisquer que atendam às restrições");
		this.jRadioButtonQuaisquer.setActionCommand("quaisquer");

		this.buttonGroupOpcoes = new ButtonGroup();
		this.buttonGroupOpcoes.add(this.jRadioButtonOtimizar);
		this.buttonGroupOpcoes.add(this.jRadioButtonQuaisquer);

		this.jRadioButtonOtimizar.addActionListener(this);
		this.jRadioButtonQuaisquer.addActionListener(this);

		this.jPanelPrincipal.add(this.jRadioButtonOtimizar);
		this.jRadioButtonOtimizar.setBounds(10, 20, 350, 20);

		this.jPanelPrincipal.add(this.jRadioButtonQuaisquer);
		this.jRadioButtonQuaisquer.setBounds(10, 40, 300, 20);

		this.jLabelFatoresOtimizar.setText("Fator:");
		this.jPanelPrincipal.add(this.jLabelFatoresOtimizar);
		this.jLabelFatoresOtimizar.setBounds(10, 70, 30, 20);
		this.jLabelFatoresOtimizar.hide();

		this.jPanelPrincipal.add(this.jComboboxFatoresOtimizar);
		this.jComboboxFatoresOtimizar.setBounds(40, 70, 150, 20);
		this.jComboboxFatoresOtimizar.hide();

		this.jLabelNumSolucoes.setText("Num.Soluções a Gerar:");
		this.jPanelPrincipal.add(this.jLabelNumSolucoes);
		this.jLabelNumSolucoes.setBounds(70, 70, 115, 20);
		this.jLabelNumSolucoes.hide();

		this.jTextboxNumSolucoes.setBounds(185, 70, 80, 20);
		this.jPanelPrincipal.add(this.jTextboxNumSolucoes);
		this.jTextboxNumSolucoes.hide();

		this.jPanelPrincipal.add(this.jCheckboxTodos);
		this.jCheckboxTodos.setText("Todas");
		this.jCheckboxTodos.addItemListener(this);

		this.jCheckboxTodos.setBounds(10, 70, 60, 20);
		this.jCheckboxTodos.hide();

		this.jPanelPrincipal.add(this.jCheckboxParcial);
		this.jCheckboxParcial
				.setText("Gerar Soluções Parcias, caso não existam soluções.");
		this.jCheckboxParcial.setBounds(10, 100, 300, 20);
		this.jCheckboxParcial.hide();

		this.jButtonGerar.setText("Gerar Equipes");
		this.jButtonGerar
				.setToolTipText("Clique aqui para gerar as equipes que atendem às restrições impostas.");
		this.jButtonGerar.setBounds(10, 100, 150, 20);
		this.jButtonGerar
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						gerarEquipes();
					}
				});
		this.jPanelPrincipal.add(this.jButtonGerar);
		
		this.jLabelOrdenar.setText("Ordenar por:");
		this.jPanelPrincipal.add(this.jLabelOrdenar);
		this.jLabelOrdenar.setBounds(240, 100, 70, 20);
		
		this.jPanelPrincipal.add(this.jComboboxOrdenar);
		this.jComboboxOrdenar.setBounds(310, 100, 120, 20);
		/*
		this.jComboboxOrdenar
		.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				limpaResultado();
				exibirResultado();
			}
		});		
        */
		
		this.jLabelOrdenar2.setText(" e:");
		this.jPanelPrincipal.add(this.jLabelOrdenar2);
		this.jLabelOrdenar2.setBounds(440, 100, 30, 20);
		
		this.jPanelPrincipal.add(this.jComboboxOrdenar2);
		this.jComboboxOrdenar2.setBounds(460, 100, 120, 20);
		/*
		this.jComboboxOrdenar2
		.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				limpaResultado();
				exibirResultado();
			}
		});
		*/
		
		// Título do panel
		this.jTextPaneTituloTela
				.setBackground(new java.awt.Color(153, 204, 255));
		this.jTextPaneTituloTela.disable();
		this.jTextPaneTituloTela.setFont(new java.awt.Font(
				"Microsoft Sans Serif", 1, 18));
		this.jTextPaneTituloTela.setText("Gerar Equipes");
		add(this.jTextPaneTituloTela);
		this.jTextPaneTituloTela.setBounds(0, 0, 600, 30);

		// TAB Detalhes

		this.jLabelCusto.setText("Custo da Equipe:");
		this.jPanelDetalheEquipe.add(this.jLabelCusto);
		this.jLabelCusto.setBounds(10, 15, 90, 20);
		this.jPanelDetalheEquipe.add(this.jLabelValorCusto);
		this.jLabelValorCusto.setBounds(95, 15, 40, 20);

		this.jLabelTamanho.setText("Tamanho da Equipe:");
		this.jPanelDetalheEquipe.add(this.jLabelTamanho);
		this.jLabelTamanho.setBounds(160, 15, 100, 20);
		this.jPanelDetalheEquipe.add(this.jLabelValorTamanho);
		this.jLabelValorTamanho.setBounds(260, 15, 30, 20);

		this.jLabelSubAlocacao.setText("Índ. Sub-Alocação:");
		this.jPanelDetalheEquipe.add(this.jLabelSubAlocacao);
		this.jLabelSubAlocacao.setBounds(295, 15, 100, 20);
		this.jPanelDetalheEquipe.add(this.jLabelValorSubAlocacao);
		this.jLabelValorSubAlocacao.setBounds(390, 15, 30, 20);
		
		this.jLabelTempo.setText("Num. Dias:");
		this.jPanelDetalheEquipe.add(this.jLabelTempo);
		this.jLabelTempo.setBounds(440, 15, 60, 20);
		this.jPanelDetalheEquipe.add(this.jLabelValorTempo);
        this.jLabelValorTempo.setBounds(497, 15, 30, 20);

		this.jEditorpaneDetalhe.setBounds(10, 40, 570, 140);
		this.jEditorpaneDetalhe.setContentType("text/html");
		this.jPanelDetalheEquipe.add(this.jEditorpaneDetalhe);
		this.jEditorpaneDetalhe.setEditable(false);		
		
		this.jScrollPaneDetalhe.setBounds(10, 40, 570, 140);
		this.jScrollPaneDetalhe.getViewport().add(this.jEditorpaneDetalhe,null);
		this.jPanelDetalheEquipe.add(this.jScrollPaneDetalhe);

	}

	/***************************************************************************
	 * Tratamento da TAB Detalhe
	 **************************************************************************/

	/***************************************************************************
	 * Métodos referentes à TAB GERAL
	 * 
	 **************************************************************************/

	/***************************************************************************
	 * Métodos referentes à TAB CARACTERÍSTICAS
	 * 
	 **************************************************************************/
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().compareToIgnoreCase("otimizar") == 0) {
			this.jLabelNumSolucoes.hide();
			this.jTextboxNumSolucoes.hide();
			this.jCheckboxTodos.hide();

			this.jLabelFatoresOtimizar.show();
			this.jComboboxFatoresOtimizar.show();
			this.jCheckboxTodos.setSelected(false);

			this.otimizar = true;
		} else {
			this.jLabelNumSolucoes.show();
			this.jTextboxNumSolucoes.show();
			this.jCheckboxTodos.show();

			this.jLabelFatoresOtimizar.hide();
			this.jComboboxFatoresOtimizar.hide();
			this.otimizar = false;
		}
		//this.jCheckboxParcial.show();
		this.validate();
		this.repaint();

	}

	public void itemStateChanged(ItemEvent e) {

		Object source = e.getItemSelectable();

		if (source == this.jCheckboxTodos) {
			this.todasSolucoes = true;
			this.jTextboxNumSolucoes.disable();
			this.jTextboxNumSolucoes.setText("");
		}

		if (e.getStateChange() == ItemEvent.DESELECTED) {
			if (source == this.jCheckboxTodos) {
				this.todasSolucoes = false;
				this.jTextboxNumSolucoes.enable();
			}
		}
	}

	/**
	 * Preenche tabela com as possíveis equipes para a situação 
	 */
	public void exibirResultado() {
		Object[] linha = new Object[5];
		ArrayList listaSolucoes = new ArrayList();

		listaSolucoes = ordenaResultado();

		Iterator iter1 = listaSolucoes.iterator();
		while (iter1.hasNext()) {
			Solucao s = (Solucao) iter1.next();
			linha[0] = s.getNumero() + "";
			linha[1] = s.getTamanhoEquipe() + "";
			linha[2] = s.getCustoMonetario() + "";
			linha[3] = s.getTempoProjeto() + "";
			linha[4] = s.getIndSubalocacao() + "";

			((DefaultTableModel) this.jTableAlocacoes.getModel()).addRow(linha);
		}
	}
	
	public int retornaOrdemSegundoFator(Object a, Object b){
		if (this.jComboboxOrdenar2.getSelectedIndex() == 0){
        	if (((Solucao) a).getNumero() < ((Solucao) b).getNumero())
            	return -1;
            if (((Solucao) a).getNumero() == ((Solucao) b).getNumero()){
            	return 0;
            }
            if (((Solucao) a).getNumero() > ((Solucao) b).getNumero())
            	return 1;
        }
        if (this.jComboboxOrdenar2.getSelectedIndex() == 1){
        	if (((Solucao) a).getCustoMonetario() < ((Solucao) b).getCustoMonetario())
            	return -1;
            if (((Solucao) a).getCustoMonetario() == ((Solucao) b).getCustoMonetario())
            	return 0;
            if (((Solucao) a).getCustoMonetario() > ((Solucao) b).getCustoMonetario())
            	return 1;
        }
        if (this.jComboboxOrdenar2.getSelectedIndex() == 2){
        	if (((Solucao) a).getTamanhoEquipe() < ((Solucao) b).getTamanhoEquipe())
            	return -1;
            if (((Solucao) a).getTamanhoEquipe() == ((Solucao) b).getTamanhoEquipe())
            	return 0;
            if (((Solucao) a).getTamanhoEquipe() > ((Solucao) b).getTamanhoEquipe())
            	return 1;
        }
        if (this.jComboboxOrdenar2.getSelectedIndex() == 3){
        	if (((Solucao) a).getIndSubalocacao() < ((Solucao) b).getIndSubalocacao())
            	return -1;
            if (((Solucao) a).getIndSubalocacao() == ((Solucao) b).getIndSubalocacao())
            	return 0;
            if (((Solucao) a).getIndSubalocacao() > ((Solucao) b).getIndSubalocacao())
            	return 1;
        }
        if (this.jComboboxOrdenar2.getSelectedIndex() == 4){
        	if (((Solucao) a).getTempoProjeto() < ((Solucao) b).getTempoProjeto())
            	return -1;
            if (((Solucao) a).getTempoProjeto() == ((Solucao) b).getTempoProjeto())
            	return 0;
            if (((Solucao) a).getTempoProjeto() > ((Solucao) b).getTempoProjeto())
            	return 1;
        }
        return 0;
	}
	
	
	/**
	 * @return Lista de soluções ordenada pelo fator selecionado
	 */
	public ArrayList ordenaResultado() {
		ArrayList listaSolucoes = new ArrayList();

		listaSolucoes = JanelaPrincipal.getAplicacao().getSolucoes();

		class SolucoesComparator implements Comparator {
            public final int compare(Object a, Object b) {
                if (jComboboxOrdenar.getSelectedIndex() == 0){
                	if (((Solucao) a).getNumero() < ((Solucao) b).getNumero())
                    	return -1;
                    if (((Solucao) a).getNumero() == ((Solucao) b).getNumero()){
                    	return retornaOrdemSegundoFator(a,b);
                    }
                    if (((Solucao) a).getNumero() > ((Solucao) b).getNumero())
                    	return 1;
                }
                if (jComboboxOrdenar.getSelectedIndex() == 1){
                	if (((Solucao) a).getCustoMonetario() < ((Solucao) b).getCustoMonetario())
                    	return -1;
                    if (((Solucao) a).getCustoMonetario() == ((Solucao) b).getCustoMonetario())
                    	return retornaOrdemSegundoFator(a,b);
                    if (((Solucao) a).getCustoMonetario() > ((Solucao) b).getCustoMonetario())
                    	return 1;
                }
                if (jComboboxOrdenar.getSelectedIndex() == 2){
                	if (((Solucao) a).getTamanhoEquipe() < ((Solucao) b).getTamanhoEquipe())
                    	return -1;
                    if (((Solucao) a).getTamanhoEquipe() == ((Solucao) b).getTamanhoEquipe())
                    	return retornaOrdemSegundoFator(a,b);
                    if (((Solucao) a).getTamanhoEquipe() > ((Solucao) b).getTamanhoEquipe())
                    	return 1;
                }
                if (jComboboxOrdenar.getSelectedIndex() == 3){
                	if (((Solucao) a).getIndSubalocacao() < ((Solucao) b).getIndSubalocacao())
                    	return -1;
                    if (((Solucao) a).getIndSubalocacao() == ((Solucao) b).getIndSubalocacao())
                    	return retornaOrdemSegundoFator(a,b);
                    if (((Solucao) a).getIndSubalocacao() > ((Solucao) b).getIndSubalocacao())
                    	return 1;
                }
                if (jComboboxOrdenar.getSelectedIndex() == 4){
                	if (((Solucao) a).getTempoProjeto() < ((Solucao) b).getTempoProjeto())
                    	return -1;
                    if (((Solucao) a).getTempoProjeto() == ((Solucao) b).getTempoProjeto())
                    	return retornaOrdemSegundoFator(a,b);
                    if (((Solucao) a).getTempoProjeto() > ((Solucao) b).getTempoProjeto())
                    	return 1;
                }
                return 0;
            } 
        } 
        if (listaSolucoes.size() > 0)
        	Collections.sort(listaSolucoes, new SolucoesComparator());
		
        return listaSolucoes;
	}
	

	public void limpaResultado() {
		while (((DefaultTableModel) this.jTableAlocacoes.getModel())
				.getRowCount() > 0) {
			((DefaultTableModel) this.jTableAlocacoes.getModel()).removeRow(0);
		}
	}
	
	public void exibirDetalhe() {
		this.jEditorpaneDetalhe.setText(gerarTextoDetalhe());
	}

	/*
	 * Retorna lista de gaps entre a pessoa alocada e atividade
	 * 
	 * */
	public String getGapsPessoaAtividade(Profissional parProfissional,Atividade parAtividade){		
		String retorno = "";
		for (int i = 0; i != parAtividade.getQuantidadeCaracteristicas(); i++) {			
			if (!(parProfissional.checaCaracteristica(parAtividade
					.getCaracteristica(i).getDefinicaoCaracteristica()
					.getNome(), parAtividade.getCaracteristica(i).getValor()
					.getValorNumerico()))) {
				retorno = retorno + parAtividade.getCaracteristica(i).getDefinicaoCaracteristica().getNome()+";";				
			}
		}			
		return retorno;
	}
	
	
	public String gerarTextoDetalhe() {
		ArrayList listaSolucoes = new ArrayList();
		String saida = "";

		int linhaSelecionada = this.jTableAlocacoes.getSelectedRow();

		if (linhaSelecionada != -1) {
			String num = (String) ((DefaultTableModel) this.jTableAlocacoes
					.getModel()).getValueAt(linhaSelecionada, 0);

			listaSolucoes = JanelaPrincipal.getAplicacao().getSolucoes();
			Iterator iter1 = listaSolucoes.iterator();
			Iterator iter2 = null;
			while (iter1.hasNext()) {
				Solucao s = (Solucao) iter1.next();
				if (num.compareToIgnoreCase(s.getNumero() + "") == 0) {

					this.jLabelValorCusto.setText(s.getCustoMonetario() + "");
					this.jLabelValorTamanho.setText(s.getTamanhoEquipe() + "");
					this.jLabelValorSubAlocacao.setText(s.getIndSubalocacao()
							+ "");
					this.jLabelValorTempo.setText(s.getTempoProjeto()
							+ "");

					saida = "<html>";
					saida += "<table width=100% cellspacing=2 cellpadding=1 style='background-color:#FFFFFF;'>";
					saida += "<tr>";
					saida += "<td style='background-color:#E6E8FA;border:#000000;'>";
					saida += "<font face='Arial'><b>Atividade</b></font>";
					saida += "</td>";
					saida += "<td style='background-color:#E6E8FA;border:#000000;'>";
					saida += "<font face='Arial'><b>Profissional</b></font>";
					saida += "</td>";
					saida += "<td style='background-color:#E6E8FA;border:#000000;'>";
					saida += "<font face='Arial'><b>Data Inicial</b></font>";
					saida += "</td>";
					saida += "<td style='background-color:#E6E8FA;border:#000000;'>";
					saida += "<font face='Arial'><b>Data Final</b></font>";
					saida += "</td>";
					saida += "<td style='background-color:#E6E8FA;border:#000000;'>";
					saida += "<font face='Arial'><b>Horas/Dia</b></font>";
					saida += "</td>";
					saida += "<td style='background-color:#E6E8FA;border:#000000;'>";
					saida += "<font face='Arial'><b>Gaps</b></font>";
					saida += "</td>";
					saida += "</tr>";

					iter2 = s.getAlocacoes().iterator();
					while (iter2.hasNext()) {
						Alocacao a = (Alocacao) iter2.next();
						saida += "<tr>";
						saida += "<td style='background-color:#FFFFFF;border:#000000;'>";
						saida += "<font face='Arial'>"
								+ a.getAtividade().getNome() + "</font>";
						saida += "</td>";
						saida += "<td style='background-color:#FFFFFF;border:#000000;'>";
						saida += "<font face='Arial'>"
								+ a.getProfissional().getNome() + "</font>";
						saida += "</td>";
						saida += "<td style='background-color:#FFFFFF;border:#000000;'>";
						saida += "<font face='Arial'>"
								+ a.getAtividade().getDataInicioString()
								+ "</font>";
						saida += "</td>";
						saida += "<td style='background-color:#FFFFFF;border:#000000;'>";
						saida += "<font face='Arial'>"
								+ a.getAtividade().getDataFimString()
								+ "</font>";
						saida += "</td>";
						saida += "<td align='center' style='background-color:#FFFFFF;border:#000000;'>";
						saida += "<font face='Arial'>"
								+ a.getAtividade().getNumeroHorasDia()
								+ "</font>";
						saida += "</td>";
						
						saida += "<td align='center' style='background-color:#FFFFFF;border:#000000;'>";
						saida += "<font face='Arial'>"
								+ this.getGapsPessoaAtividade(a.getProfissional(), a.getAtividade())
								+ "</font>";
						saida += "</td>";
						
						
						saida += "</tr>";
					}
					// saida+="<h1>Alocação Número " + s.getNumero() + "</h1>";

					saida += "</html>";

				}
			}
		}

		return saida;
	}

	public void gerarEquipes() {  
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		if (this.otimizar) {
			int fator = this.jComboboxFatoresOtimizar.getSelectedIndex();
			switch (fator) {
			case 0:
				this.fatorOtimizar = 1;
				break;
			case 1:
				this.fatorOtimizar = 2;
				break;
			case 2:
				this.fatorOtimizar = 3;
				break;
			case 3:
				this.fatorOtimizar = 4;
				break;
			case 4:
				this.fatorOtimizar = 5;
				break;				
			}
		} else{
			if (!this.todasSolucoes){
				try{
					this.numMaxSolucoes = Integer.parseInt(this.jTextboxNumSolucoes.getText());
					if (this.numMaxSolucoes <= 0){
						JOptionPane.showMessageDialog(this,
								"Número de soluções deve ser maior que zero.", "Alerta",
								JOptionPane.WARNING_MESSAGE);
						return;
					}					
				} catch(Exception err){
					JOptionPane.showMessageDialog(this,
							"Número de soluções deve ser um inteiro maior que zero.", "Alerta",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		}
		
		JanelaPrincipal.getAplicacao().setOtimizacao(this.otimizar);
		JanelaPrincipal.getAplicacao().setFatorOtimizar(this.fatorOtimizar);

		
		JanelaPrincipal.getAplicacao().setTodasSolucoes(this.todasSolucoes);
		JanelaPrincipal.getAplicacao().setNumMaxSolucoes(this.numMaxSolucoes);

		JanelaPrincipal.getAplicacao().executar();

		limpaResultado();
		exibirResultado();
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	// Variables declaration - do not modify

	private boolean otimizar = false;

	private int fatorOtimizar = 0;

	private int numMaxSolucoes = 0; 
	
	private boolean todasSolucoes = false;
	
	/**
	 * Painel principal (onde os profissionais são inseridos)
	 */
	private javax.swing.JPanel jPanelPrincipal;

	/**
	 * Painel de características possuídas pela atividade
	 */
	private javax.swing.JPanel jPanelDetalheEquipe;

	/**
	 * Tabela do painel principal
	 */
	private javax.swing.JTable jTableAlocacoes;

	/**
	 * Texto com o título da tela
	 */
	private javax.swing.JTextPane jTextPaneTituloTela;

	/**
	 * Painel que contém a tabela
	 */
	private JScrollPane jScrollPanePrincipal;
	
	private JScrollPane jScrollPaneDetalhe;

	private JRadioButton jRadioButtonOtimizar;

	private JRadioButton jRadioButtonQuaisquer;

	private ButtonGroup buttonGroupOpcoes;

	private JComboBox jComboboxFatoresOtimizar;
	
	private JComboBox jComboboxOrdenar;
	
	private JComboBox jComboboxOrdenar2;

	private JLabel jLabelFatoresOtimizar;

	private JLabel jLabelNumSolucoes;

	private JTextField jTextboxNumSolucoes;

	private JCheckBox jCheckboxTodos;

	private JCheckBox jCheckboxParcial;

	private JButton jButtonGerar;

	private JEditorPane jEditorpaneDetalhe;

	private JLabel jLabelValorCusto;

	private JLabel jLabelValorTamanho;

	private JLabel jLabelValorSubAlocacao;
	
	private JLabel jLabelValorTempo;
	
	private JLabel jLabelOrdenar;
	
	private JLabel jLabelOrdenar2;
	
	private JLabel jLabelCusto;

	private JLabel jLabelTamanho;

	private JLabel jLabelSubAlocacao;
	
	private JLabel jLabelTempo;


	// End of variables declaration

}
