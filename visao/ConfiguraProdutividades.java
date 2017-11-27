
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/*
 * Created on 14/05/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author Ahilton
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ConfiguraProdutividades extends JPanel {

	/** Creates new form TelaGeracaoEquipes */
	public ConfiguraProdutividades() {
		initComponents();
	}

	private void initComponents() {
		this.jLabelNomeModelo = new JLabel();
		this.jLabelProfissionais = new JLabel();
		this.jEditorDescricao = new JEditorPane();
		this.jComboModelo = new JComboBox();
		this.jComboProfissional = new JComboBox();
		this.jTextPaneTituloTela = new JTextPane();
		this.jScrollPaneModelo2 = new JScrollPane();
		this.jScrollPaneModelo3 = new JScrollPane();

		this.jComboModelo.addItem("Nenhum Modelo");
		this.jComboModelo.addItem("Modelo 1");
		this.jComboModelo.addItem("Modelo 2");
		this.jComboModelo.addItem("Modelo 3");

		// Atribui o tamanho do jPanel
		this.setSize(600, 400);

		// Atribui o layout do jPanel
		this.setLayout(null);

		// Inicializa os componentes (novo)

		this.jPanelPrincipal = new javax.swing.JPanel();
		this.jPanelPrincipal.setLayout(null);
		this.jPanelPrincipal.setBounds(0, 30, 590, 370);
		this.jPanelPrincipal.setBorder(new javax.swing.border.TitledBorder(
				"Modelos de Produtividade"));

		this.jPanelDescricao = new javax.swing.JPanel();
		this.jPanelDescricao.setLayout(null);
		this.jPanelDescricao.setBounds(5, 50, 580, 70);
		this.jPanelDescricao.setBorder(new javax.swing.border.TitledBorder(
				"Descrição do Modelo"));

		this.jPanelConfiguracao = new javax.swing.JPanel();
		this.jPanelConfiguracao.setLayout(null);
		this.jPanelConfiguracao.setBounds(5, 120, 580, 190);
		this.jPanelConfiguracao.setBorder(new javax.swing.border.TitledBorder(
				"Configurações"));

		add(this.jPanelPrincipal);

		this.jPanelPrincipal.add(this.jPanelDescricao);
		this.jPanelPrincipal.add(this.jPanelConfiguracao);

		this.jLabelNomeModelo.setText("Modelo:");
		this.jPanelPrincipal.add(this.jLabelNomeModelo);
		this.jLabelNomeModelo.setBounds(15, 20, 50, 20);

		this.jPanelPrincipal.add(this.jComboModelo);
		this.jComboModelo.setBounds(60, 20, 520, 20);
		this.jComboModelo
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						exibeDescricao();
					}
				});

		this.jEditorDescricao.setBounds(5, 15, 570, 50);
		this.jEditorDescricao.setBackground(this.jPanelDescricao
				.getBackground());
		this.jPanelDescricao.add(this.jEditorDescricao);

		this.jEditorDescricao.setContentType("text/plain");
		this.jPanelDescricao.add(this.jEditorDescricao);
		this.jEditorDescricao.setEditable(false);

		class MyDefaultTableModel extends DefaultTableModel {

			public MyDefaultTableModel(Object[] columnNames, int value) {
				super(columnNames, value);
			}

			public boolean isCellEditable(int row, int col) {
				if (col == 1)
					return true;
				return false;
			}
		}

		// Cria tabela baseada no modelo definido e seta sua posição
		Object[] columnNames = new Object[2];
		columnNames[0] = "Profissional";
		columnNames[1] = "Produtividade";

		DefaultTableModel tblModel = new MyDefaultTableModel(columnNames, 0);

		tblModel.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				TableModel source = (TableModel) e.getSource();
				Double novoValor = new Double(1);
				if (e.getType() == TableModelEvent.UPDATE) {
					try {
						novoValor = new Double((String) (source.getValueAt(e
								.getFirstRow(), e.getColumn())));

						if (novoValor.doubleValue() >= 1) {
							atualizaProdutividadeGlobalProfissional(novoValor
									.doubleValue(), (Profissional) source
									.getValueAt(e.getFirstRow(),
											e.getColumn() - 1));
						} else {
							source.setValueAt("1.0", e.getFirstRow(), e
									.getColumn());
							exibeMensagem("A produtividade deve ser um número igual ou maior que 1.");
						}

					} catch (Exception err) {
						source
								.setValueAt("1.0", e.getFirstRow(), e
										.getColumn());
						exibeMensagem("A produtividade deve ser um número igual ou maior que 1.");
					}

				}

			}
		});

		this.jTableModelo2 = new javax.swing.JTable(tblModel);
		this.jTableModelo2.setBounds(5, 15, 580, 180);
		this.jTableModelo2
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.jScrollPaneModelo2.setBounds(5, 25, 570, 160);
		this.jScrollPaneModelo2.getViewport().add(this.jTableModelo2, null);
		this.jPanelConfiguracao.add(this.jScrollPaneModelo2);
		this.jScrollPaneModelo2.setVisible(false);

		Object[] columnNamesMod3 = new Object[2];
		columnNamesMod3[0] = "Atividades";
		columnNamesMod3[1] = "Produtividade";

		DefaultTableModel tblModelMod3 = new MyDefaultTableModel(
				columnNamesMod3, 0);

		tblModelMod3.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				TableModel source = (TableModel) e.getSource();
				Double novoValor = new Double(1);
				if (e.getType() == TableModelEvent.UPDATE) {
					try {
						novoValor = new Double((String) (source.getValueAt(e
								.getFirstRow(), e.getColumn())));

						if (novoValor.doubleValue() >= 1) {
							atualizaProdutividadeProfissionalAtividade(
									novoValor.doubleValue(), (Atividade) source
											.getValueAt(e.getFirstRow(), e
													.getColumn() - 1));
						} else {
							source.setValueAt("1.0", e.getFirstRow(), e
									.getColumn());
							exibeMensagem("A produtividade deve ser um número igual ou maior que 1.");
						}

					} catch (Exception err) {
						source
								.setValueAt("1.0", e.getFirstRow(), e
										.getColumn());
						exibeMensagem("A produtividade deve ser um número igual ou maior que 1.");
					}

				}

			}
		});

		this.jLabelProfissionais.setText("Profissional:");
		this.jLabelProfissionais.setBounds(10, 25, 60, 20);
		this.jPanelConfiguracao.add(this.jLabelProfissionais);

		this.jComboProfissional.setBounds(70, 25, 500, 20);
		this.jPanelConfiguracao.add(this.jComboProfissional);
		this.jComboProfissional.setVisible(false);
		this.jLabelProfissionais.setVisible(false);

		this.jComboProfissional
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						carregaTabelaModulo3();
					}
				});

		this.jTableModelo3 = new javax.swing.JTable(tblModelMod3);
		this.jTableModelo3.setBounds(5, 55, 570, 130);
		this.jTableModelo3
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.jScrollPaneModelo3.setBounds(5, 55, 570, 130);
		this.jScrollPaneModelo3.getViewport().add(this.jTableModelo3, null);
		this.jPanelConfiguracao.add(this.jScrollPaneModelo3);
		this.jScrollPaneModelo3.setVisible(false);

		// Título do panel
		this.jTextPaneTituloTela
				.setBackground(new java.awt.Color(153, 204, 255));
		this.jTextPaneTituloTela.disable();
		this.jTextPaneTituloTela.setFont(new java.awt.Font(
				"Microsoft Sans Serif", 1, 18));
		this.jTextPaneTituloTela.setText("Modelo de Produtividade");
		add(this.jTextPaneTituloTela);
		this.jTextPaneTituloTela.setBounds(0, 0, 600, 30);

		exibeDescricao();
	}

	protected void exibeDescricao() {
		int modelo = this.jComboModelo.getSelectedIndex();
		JanelaPrincipal.getAplicacao().setModeloProdutividade(modelo);
		switch (modelo) {
		case 0:
			this.jEditorDescricao
					.setText("Nenhum modelo de produtividade deve ser considerado no problema");
			this.jScrollPaneModelo3.setVisible(false);
			this.jScrollPaneModelo2.setVisible(false);
			this.jComboProfissional.setVisible(false);
			this.jLabelProfissionais.setVisible(false);
			break;
		case 1:
			this.jEditorDescricao
					.setText("A produtividade irá variar entre 1 e 1.33, de acordo com a qualificação do profissional que excede a qualificação exigida. Baseado nos estudos de ABDEL-HAMID e JONES.");
			this.jScrollPaneModelo3.setVisible(false);
			this.jScrollPaneModelo2.setVisible(false);
			this.jComboProfissional.setVisible(false);
			this.jLabelProfissionais.setVisible(false);
			break;
		case 2:
			this.jEditorDescricao
					.setText("Deve ser atribuído, para cada profissional, um modificador de produtividade, que indica a produtividade do profissional em relação à produtividade mínima (1).");
			this.jScrollPaneModelo3.setVisible(false);
			this.jScrollPaneModelo2.setVisible(true);
			this.jComboProfissional.setVisible(false);
			this.jLabelProfissionais.setVisible(false);
			this.carregaTabelaModulo2();
			break;
		case 3:
			this.jEditorDescricao
					.setText("Deve ser atribuído, para cada profissional em cada atividade, um modificador de produtividade, que indica a produtividade do profissional em relação à produtividade mínima (1).");
			this.jScrollPaneModelo2.setVisible(false);
			this.jComboProfissional.setVisible(true);
			this.jLabelProfissionais.setVisible(true);
			this.jScrollPaneModelo3.setVisible(true);
			this.carregaComboModulo3();
			this.carregaTabelaModulo3();
			break;
		}
	}

	private void carregaTabelaModulo2() {

		while (((DefaultTableModel) this.jTableModelo2.getModel())
				.getRowCount() > 0) {
			((DefaultTableModel) this.jTableModelo2.getModel()).removeRow(0);
		}
		ArrayList profissionais = JanelaPrincipal.getAplicacao()
				.getProfissionais();
		Iterator iter = profissionais.iterator();
		Object[] linha = new Object[2];

		while (iter.hasNext()) {
			Profissional p = (Profissional) iter.next();
			linha[0] = p;
			linha[1] = new Double(p.getIndiceProdutividadeGlobal());
			((DefaultTableModel) this.jTableModelo2.getModel()).addRow(linha);
		}
	}

	private void carregaTabelaModulo3() {

		while (((DefaultTableModel) this.jTableModelo3.getModel())
				.getRowCount() > 0) {
			((DefaultTableModel) this.jTableModelo3.getModel()).removeRow(0);
		}

		Profissional p = (Profissional) this.jComboProfissional
				.getSelectedItem();

		if (p != null) {
			Iterator iter = JanelaPrincipal.getAplicacao().getAtividades()
					.iterator();
			Object[] linha = new Object[2];
			Double valor = new Double(1.0);

			while (iter.hasNext()) {
				Atividade a = (Atividade) iter.next();

				if (p.getProdutividadeAtividade().get(a.getNome()) != null)
					valor = (Double) p.getProdutividadeAtividade().get(
							a.getNome());
				else {
					p.getProdutividadeAtividade().put(a.nome, new Double(1.0));
				}

				linha[0] = a;
				linha[1] = valor;
				((DefaultTableModel) this.jTableModelo3.getModel())
						.addRow(linha);
			}

		}
	}

	private void carregaComboModulo3() {
		this.jComboProfissional.removeAllItems();
		ArrayList a = JanelaPrincipal.getAplicacao().getProfissionais();
		Iterator iter = a.iterator();
		while (iter.hasNext()) {
			Profissional p = (Profissional) iter.next();
			this.jComboProfissional.addItem(p);
		}
		this.jComboProfissional.setSelectedIndex(0);
	}

	protected void atualizaProdutividadeGlobalProfissional(
			double produtividade, Profissional p) {
		p.setIndiceProdutividadeGlobal(produtividade);
	}

	protected void atualizaProdutividadeProfissionalAtividade(
			double produtividade, Atividade a) {
		Profissional p = (Profissional) this.jComboProfissional
				.getSelectedItem();

		p.getProdutividadeAtividade().put(a.nome, new Double(produtividade));
	}

	protected void exibeMensagem(String mensagem) {
		JOptionPane.showMessageDialog(this, mensagem, "Alerta",
				JOptionPane.WARNING_MESSAGE);
	}

	// Variables declaration - do not modify

	/**
	 * Painel principal
	 */
	private JPanel jPanelPrincipal;

	private JLabel jLabelNomeModelo;

	private JLabel jLabelProfissionais;

	private JEditorPane jEditorDescricao;

	private JComboBox jComboModelo;

	private JPanel jPanelDescricao;

	private JPanel jPanelConfiguracao;

	private JTextPane jTextPaneTituloTela;

	private JTable jTableModelo2;

	private JScrollPane jScrollPaneModelo2;

	private JTable jTableModelo3;

	private JScrollPane jScrollPaneModelo3;

	private JComboBox jComboProfissional;

}
