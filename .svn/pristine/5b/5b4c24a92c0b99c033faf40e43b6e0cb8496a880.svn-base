
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

/*
 * Created on 09/02/2005
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
public class JanelaPrincipal extends JFrame {

	private javax.swing.JPanel jContentPane = null;

	private javax.swing.JMenuBar jJMenuBar = null;

	private javax.swing.JMenu menuArquivo = null;

	private javax.swing.JMenu menuCadastros = null;

	private javax.swing.JMenu menuGerarEquipes = null;

	private javax.swing.JMenu menuAjuda = null;

	private javax.swing.JMenuItem menuItemSair = null;

	private javax.swing.JMenuItem aboutMenuItem = null;

	private javax.swing.JMenuItem menuItemEscalas = null;

	private javax.swing.JMenuItem menuItemTipoDefinicaoCaracteristica = null;

	private javax.swing.JMenuItem menuItemCaracteristicas = null;

	private javax.swing.JMenuItem menuItemProfissionais = null;

	private javax.swing.JMenuItem menuItemAtividades = null;
	
	private javax.swing.JMenuItem menuItemModelos = null;

	private javax.swing.JMenuItem menuItemAbrirArquivo = null;

	private javax.swing.JMenuItem menuItemSalvarArquivo = null;

	private javax.swing.JMenuItem menuItemGerarEquipes = null;

	private JScrollPane scrpnlFerramenta = null;

	private static Aplicacao aplicacao = null;

	private static final long serialVersionUID = 0;

	private CadastroCaracteristicas cadastroCaracteristicas = null;

	private CadastroProfissionais cadastroProfissionais = null;

	private CadastroAtividades cadastroAtividades = null;
	
	private ConfiguraProdutividades configuraProdutividades = null;

	private CadastroEscalas cadastroEscalas = null;

	private CadastroTipoDefinicaoCaracteristica cadastroTipoDefinicaoCaracteristica = null;
	
	private TelaGeracaoEquipes telaGeracaoEquipes = null;

	/**
	 * This is the default constructor
	 */
	public JanelaPrincipal() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(getJJMenuBar());
		this.setSize(600, 400);
		this.setLocation(300,300);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.getContentPane().add(this.getScrpnlFerramenta());
		this.setTitle("AlocaRH");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if (this.jContentPane == null) {
			this.jContentPane = new javax.swing.JPanel();
			this.jContentPane.setLayout(new java.awt.BorderLayout());
			this.jContentPane.setSize(600, 400);
		}
		return this.jContentPane;
	}

	public static Aplicacao getAplicacao() {
		return aplicacao;
	}

	private JScrollPane getScrpnlFerramenta() {
		if (this.scrpnlFerramenta == null) {
			this.scrpnlFerramenta = new JScrollPane();
			this.scrpnlFerramenta.setSize(600, 400);
		}
		return this.scrpnlFerramenta;
	}

	/**
	 * This method initializes jJMenuBar
	 * 
	 * @return javax.swing.JMenuBar
	 */
	private javax.swing.JMenuBar getJJMenuBar() {
		if (this.jJMenuBar == null) {
			this.jJMenuBar = new javax.swing.JMenuBar();
			this.jJMenuBar.add(getMenuArquivo());
			this.jJMenuBar.add(getMenuCadastros());
			this.jJMenuBar.add(getMenuGerarEquipes());
			this.jJMenuBar.add(getMenuAjuda());
		}
		return this.jJMenuBar;
	}

	/**
	 * This method initializes jMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	private javax.swing.JMenu getMenuArquivo() {
		if (this.menuArquivo == null) {
			this.menuArquivo = new javax.swing.JMenu();
			this.menuArquivo.setText("Arquivo");
			this.menuArquivo.add(getMenuItemAbrirArquivo());
			this.menuArquivo.add(getMenuItemSalvarArquivo());
			this.menuArquivo.add(getMenuItemSair());
		}
		return this.menuArquivo;
	}

	/**
	 * This method initializes jMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	private javax.swing.JMenu getMenuCadastros() {
		if (this.menuCadastros == null) {
			this.menuCadastros = new javax.swing.JMenu();
			this.menuCadastros.setText("Cadastros");

			this.menuCadastros.add(getMenuItemEscalas());
			this.menuCadastros.add(getMenuItemTipoDefinicaoCaracteristica());
			this.menuCadastros.add(getMenuItemCaracteristicas());
			this.menuCadastros.add(getMenuItemProfissionais());
			this.menuCadastros.add(getMenuItemAtividades());
			this.menuCadastros.add(getMenuItemModelos());
		}
		return this.menuCadastros;
	}

	/**
	 * This method initializes jMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	private javax.swing.JMenu getMenuGerarEquipes() {
		if (this.menuGerarEquipes == null) {
			this.menuGerarEquipes = new javax.swing.JMenu();
			this.menuGerarEquipes.setText("Gerar Equipes");
			this.menuGerarEquipes.add(getMenuItemGerarEquipes());
		}
		return this.menuGerarEquipes;
	}

	private javax.swing.JMenu getMenuAjuda() {
		if (this.menuAjuda == null) {
			this.menuAjuda = new javax.swing.JMenu();
			this.menuAjuda.setText("Ajuda");
			this.menuAjuda.add(getAboutMenuItem());
		}
		return this.menuAjuda;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private javax.swing.JMenuItem getMenuItemSair() {
		if (this.menuItemSair == null) {
			this.menuItemSair = new javax.swing.JMenuItem();
			this.menuItemSair.setText("Sair");
			this.menuItemSair
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							System.exit(0);
						}
					});
		}
		return this.menuItemSair;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private javax.swing.JMenuItem getAboutMenuItem() {
		if (this.aboutMenuItem == null) {
			this.aboutMenuItem = new javax.swing.JMenuItem();
			this.aboutMenuItem.setText("Sobre");
			this.aboutMenuItem
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							new javax.swing.JDialog(JanelaPrincipal.this,
									"Sobre", true).show();
						}
					});
		}
		return this.aboutMenuItem;
	}

	private javax.swing.JMenuItem getMenuItemEscalas() {
		if (this.menuItemEscalas == null) {
			this.menuItemEscalas = new javax.swing.JMenuItem();
			this.menuItemEscalas.setText("Escalas");
			this.menuItemEscalas.setAccelerator(javax.swing.KeyStroke
					.getKeyStroke(java.awt.event.KeyEvent.VK_E,
							java.awt.Event.CTRL_MASK, true));
			this.menuItemEscalas
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							mostrarTelaCadastroEscalas();
						}
					});
		}
		return this.menuItemEscalas;
	}

	private javax.swing.JMenuItem getMenuItemTipoDefinicaoCaracteristica() {
		if (this.menuItemTipoDefinicaoCaracteristica == null) {
			this.menuItemTipoDefinicaoCaracteristica = new javax.swing.JMenuItem();
			this.menuItemTipoDefinicaoCaracteristica
					.setText("Tipos de Característica");
			this.menuItemTipoDefinicaoCaracteristica
					.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
							java.awt.event.KeyEvent.VK_I,
							java.awt.Event.CTRL_MASK, true));
			this.menuItemTipoDefinicaoCaracteristica
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							mostrarTelaCadastroTipoDefinicaoCaracteristica();
						}
					});
		}
		return this.menuItemTipoDefinicaoCaracteristica;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private javax.swing.JMenuItem getMenuItemCaracteristicas() {
		if (this.menuItemCaracteristicas == null) {
			this.menuItemCaracteristicas = new javax.swing.JMenuItem();
			this.menuItemCaracteristicas.setText("Características");
			this.menuItemCaracteristicas.setAccelerator(javax.swing.KeyStroke
					.getKeyStroke(java.awt.event.KeyEvent.VK_C,
							java.awt.Event.CTRL_MASK, true));
			this.menuItemCaracteristicas
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							mostrarTelaCadastroCaracteristicas();
						}
					});
		}
		return this.menuItemCaracteristicas;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private javax.swing.JMenuItem getMenuItemProfissionais() {
		if (this.menuItemProfissionais == null) {
			this.menuItemProfissionais = new javax.swing.JMenuItem();
			this.menuItemProfissionais.setText("Profissionais");
			this.menuItemProfissionais.setAccelerator(javax.swing.KeyStroke
					.getKeyStroke(java.awt.event.KeyEvent.VK_P,
							java.awt.Event.CTRL_MASK, true));
			this.menuItemProfissionais
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							mostrarTelaCadastroProfissionais();
						}
					});
		}
		return this.menuItemProfissionais;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private javax.swing.JMenuItem getMenuItemAtividades() {
		if (this.menuItemAtividades == null) {
			this.menuItemAtividades = new javax.swing.JMenuItem();
			this.menuItemAtividades.setText("Atividades");
			this.menuItemAtividades.setAccelerator(javax.swing.KeyStroke
					.getKeyStroke(java.awt.event.KeyEvent.VK_T,
							java.awt.Event.CTRL_MASK, true));
			this.menuItemAtividades
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							mostrarTelaCadastroAtividades();
						}
					});
		}
		return this.menuItemAtividades;
	}
	
	private javax.swing.JMenuItem getMenuItemModelos() {
		if (this.menuItemModelos == null) {
			this.menuItemModelos = new javax.swing.JMenuItem();
			this.menuItemModelos.setText("Produtividade");
			this.menuItemModelos.setAccelerator(javax.swing.KeyStroke
					.getKeyStroke(java.awt.event.KeyEvent.VK_R,
							java.awt.Event.CTRL_MASK, true));
			this.menuItemModelos
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							mostrarTelaConfiguraProdutividades();
						}
					});
		}
		return this.menuItemModelos;
	}
	
	private javax.swing.JMenuItem getMenuItemGerarEquipes() {
		if (this.menuItemGerarEquipes == null) {
			this.menuItemGerarEquipes = new javax.swing.JMenuItem();
			this.menuItemGerarEquipes.setText("Gerar Equipes");
			this.menuItemGerarEquipes.setAccelerator(javax.swing.KeyStroke
					.getKeyStroke(java.awt.event.KeyEvent.VK_G,
							java.awt.Event.CTRL_MASK, true));
			this.menuItemGerarEquipes
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							mostrarTelaGeracaoEquipes();
						}
					});
		}
		return this.menuItemGerarEquipes;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private javax.swing.JMenuItem getMenuItemAbrirArquivo() {
		if (this.menuItemAbrirArquivo == null) {
			this.menuItemAbrirArquivo = new javax.swing.JMenuItem();
			this.menuItemAbrirArquivo.setText("Abrir Arquivo");
			this.menuItemAbrirArquivo.setAccelerator(javax.swing.KeyStroke
					.getKeyStroke(java.awt.event.KeyEvent.VK_A,
							java.awt.Event.CTRL_MASK, true));

			this.menuItemAbrirArquivo
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							abrirArquivoXML();
						}
					});
		}
		return this.menuItemAbrirArquivo;
	}

	private javax.swing.JMenuItem getMenuItemSalvarArquivo() {
		if (this.menuItemSalvarArquivo == null) {
			this.menuItemSalvarArquivo = new javax.swing.JMenuItem();
			this.menuItemSalvarArquivo.setText("Salvar Arquivo");
			this.menuItemSalvarArquivo.setAccelerator(javax.swing.KeyStroke
					.getKeyStroke(java.awt.event.KeyEvent.VK_S,
							java.awt.Event.CTRL_MASK, true));
			this.menuItemSalvarArquivo
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							salvarArquivoXML();
						}
					});
		}
		return this.menuItemSalvarArquivo;
	}

	private CadastroCaracteristicas getCadastroCaracteristicas() {
		if (this.cadastroCaracteristicas == null) {
			this.cadastroCaracteristicas = new CadastroCaracteristicas();
		}
		this.cadastroCaracteristicas.limpaComboTipos();
		this.cadastroCaracteristicas.carregaComboTipos();
		return this.cadastroCaracteristicas;
	}

	private CadastroProfissionais getCadastroProfissionais() {
		if (this.cadastroProfissionais == null) {
			this.cadastroProfissionais = new CadastroProfissionais();
		}
		return this.cadastroProfissionais;
	}

	private CadastroAtividades getCadastroAtividades() {
		if (this.cadastroAtividades == null) {
			this.cadastroAtividades = new CadastroAtividades();
		}
		return this.cadastroAtividades;
	}
	
	private ConfiguraProdutividades getConfiguraProdutividades() {
		if (this.configuraProdutividades == null) {
			this.configuraProdutividades = new ConfiguraProdutividades();
		}
		return this.configuraProdutividades;
	}

	private CadastroEscalas getCadastroEscalas() {
		if (this.cadastroEscalas == null) {
			this.cadastroEscalas = new CadastroEscalas();
		}
		return this.cadastroEscalas;
	}

	private CadastroTipoDefinicaoCaracteristica getCadastroTipoDefinicaoCaracteristica() {
		if (this.cadastroTipoDefinicaoCaracteristica == null) {
			this.cadastroTipoDefinicaoCaracteristica = new CadastroTipoDefinicaoCaracteristica();
		}
		this.cadastroTipoDefinicaoCaracteristica.limpaComboEscala();
		this.cadastroTipoDefinicaoCaracteristica.carregaComboEscala();
		return this.cadastroTipoDefinicaoCaracteristica;
	}
	
	private TelaGeracaoEquipes getTelaGeracaoEquipes() {
		if (this.telaGeracaoEquipes == null) {
			this.telaGeracaoEquipes = new TelaGeracaoEquipes();
		}
		return this.telaGeracaoEquipes;
	}

	protected void abrirArquivoXML() {
		File f = this.mostrarTelaAbrir();
		if (f != null) {
			JanelaPrincipal.getAplicacao().abrirArquivoEntradaXML(f);
			this.getCadastroEscalas().carregaListaEscalas();
			this.getCadastroTipoDefinicaoCaracteristica()
					.carregaListaTiposDefinicaoCaracteristica();
			this.getCadastroCaracteristicas()
					.carregaListaDefinicoesCaracteristica();
			this.getCadastroProfissionais().carregaListaProfissional();
			this.getCadastroAtividades().carregaListaAtividade();
		}

	}

	protected void salvarArquivoXML() {
		File f = this.mostrarTelaSalvar();
		if (f != null) {
			JanelaPrincipal.getAplicacao().salvarArquivoEntradaXML(f);
		}

	}

	protected File mostrarTelaAbrir() {
		String filename = File.separator + "xml";
		JFileChooser fc = new JFileChooser(new File(filename));

		// Show open dialog; this method does not return until the dialog is
		// closed
		fc.showOpenDialog(this);
		return fc.getSelectedFile();
	}

	protected File mostrarTelaSalvar() {
		String filename = File.separator + "xml";
		JFileChooser fc = new JFileChooser(new File(filename));

		// Show save dialog; this method does not return until the dialog is
		// closed
		fc.showSaveDialog(this);
		return fc.getSelectedFile();
	}

	protected void executar() {
		aplicacao.executar();
		// aplicacao.imprimeTeste();
	}

	protected void mostrarTelaCadastroCaracteristicas() {
		this.setSize(600,400);
		this.validate();
		this.repaint();
		this.getScrpnlFerramenta().getViewport().add(
				this.getCadastroCaracteristicas());
	}

	protected void mostrarTelaCadastroProfissionais() {
		this.setSize(600,400);
		this.validate();
		this.repaint();
		this.getScrpnlFerramenta().getViewport().add(
				this.getCadastroProfissionais());
	}

	protected void mostrarTelaCadastroAtividades() {
		this.setSize(600,400);
		this.validate();
		this.repaint();
		this.getScrpnlFerramenta().getViewport().add(
				this.getCadastroAtividades());
	}
	
	protected void mostrarTelaConfiguraProdutividades() {
		this.setSize(600,400);
		this.validate();
		this.repaint();
		this.getScrpnlFerramenta().getViewport().add(
				this.getConfiguraProdutividades());
	}

	protected void mostrarTelaCadastroEscalas() {
		this.setSize(600,400);
		this.validate();
		this.repaint();
		this.getScrpnlFerramenta().getViewport().add(this.getCadastroEscalas());
	}

	protected void mostrarTelaCadastroTipoDefinicaoCaracteristica() {
		this.setSize(600,400);
		this.validate();
		this.repaint();
		this.getScrpnlFerramenta().getViewport().add(
				this.getCadastroTipoDefinicaoCaracteristica());
	}
	
	protected void mostrarTelaGeracaoEquipes() {
		this.setSize(600,550);
		this.validate();
		this.repaint();
		this.getScrpnlFerramenta().getViewport().add(
				this.getTelaGeracaoEquipes());
	}

	/**
	 * Launches this application
	 */
	public static void main(String[] args) {
		JanelaPrincipal application = new JanelaPrincipal();
		// Seta o look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		application.show();
		aplicacao = new Aplicacao();
	}
} // @jve:decl-index=0:visual-constraint="15,25"
