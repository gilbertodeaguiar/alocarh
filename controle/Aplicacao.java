/**
 * Title:        Classe principal da ferramenta de alocação
 * Description:  Nesta classe é feita toda a lógica da alocação.
 * Copyright:    Copyright (c) 2004
 * Company:      COPPE Sistemas
 * @author Ahilton S. Barreto
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.File;

/**
 * Classe principal, responsável por todo o controle da aplicação. Nela são
 * implementadas as funções de custo e o algoritmo de alocação
 */
public class Aplicacao {

	/**
	 * Lista de profissionais
	 */
	private ArrayList profissionais;

	/**
	 * Lista de atividades
	 */
	private ArrayList atividades;

	/**
	 * Lista de caracteristicas
	 */
	private Hashtable caracteristicas;

	/**
	 * Lista de tipos de definição de características
	 */
	private ArrayList tiposDefinicaoCaracteristica;

	/**
	 * Lista de escalas
	 */
	private Hashtable escalas;

	/**
	 * Lista de soluções
	 */
	private ArrayList solucoes;

	/**
	 * Variável que representa o domínio de cada atividade. Controla a cada
	 * momento que profissional pode ou não pode realizar cada tarefa
	 */
	private int dominio[][];

	// Constantes
	private static final int PROFISSIONAL_HABILITADO = -1;

	private static final int PROFISSIONAL_SELECIONADO = -2;

	private static final int FIM = -3;

	private static final int ATIVIDADE_CONSIDERADA = -4;

	private static final int VAZIO = -5;

	private static final int INFINITO = 9999999;

	private static final int MENOR_CUSTO = 1;

	private static final int MENOR_EQUIPE = 2;

	private static final int MENOR_SUB_ALOCACAO = 3;

	private static final int MAIOR_QUALIFICACAO = 4;

	private static final int MENOR_TEMPO = 5;

	private static final long DIA = 86400000;

	// Controles
	private int valorMaximoCaracteristicas = 0;

	public int num_solucoes = 0;

	public boolean otimizacao = false;

	public boolean todasSolucoes = false;

	public int maximoAtividadesTentativaSolucao = 0;

	public int fatorOtimizar = 0;

	public double melhorCusto;

	public double custoMonetario = 0;

	public double indSubalocacao = 0;

	public int tamEquipe = 0;

	public int tempoProjeto = 0;

	private int numMaxSolucoes = 0;

	private int modeloProdutividade;

	public Document docSaida;

	/**
	 * Construtor da aplicação
	 */
	public Aplicacao() {
		this.profissionais = new ArrayList();
		this.atividades = new ArrayList();
		this.caracteristicas = new Hashtable();
		this.solucoes = new ArrayList();
		this.tiposDefinicaoCaracteristica = new ArrayList();
		this.escalas = new Hashtable();
	}

	/***************************************************************************
	 * 
	 * Funções do algoritmo de alocação
	 * 
	 **************************************************************************/

	/**
	 * Incrementa o valor das funções de utilidade envolvidas
	 * 
	 * @param parIndProfissional
	 *            Índice do profissional no array de profissionais
	 * @param parIndAtividade
	 *            Índice da atividade no array de atividades
	 */
	private void incrementaFuncaoCusto(int parIndProfissional,
			int parIndAtividade) {
		int temp = 0;
		double temp2 = 0;

		this.custoMonetario = this.custoMonetario
				+ ((Profissional) this.profissionais.get(parIndProfissional))
						.getValorHora()
				* ((Atividade) this.atividades.get(parIndAtividade))
						.getNumeroHoras();

		for (int i = 0; i != this.atividades.size(); i++) {
			if (this.dominio[parIndProfissional + 1][i] == PROFISSIONAL_SELECIONADO) {
				temp += 1;
			}
		}
		if (temp == 1) {
			this.tamEquipe += 1;
		}

		this.tempoProjeto = (int) ((this
				.getMaiorDataFinal(this.getAtividades()).getTime() - this
				.getMenorDataInicial(this.getAtividades()).getTime()) / DIA) + 1;

		// Calcula "pontuação" da atividade e do profissional.
		temp2 = Math
				.abs(((Profissional) this.profissionais.get(parIndProfissional))
						.calculaPontosCaracteristicaRelativo(this.valorMaximoCaracteristicas));
		this.indSubalocacao += Math
				.abs(Math
						.abs(((Atividade) this.atividades.get(parIndAtividade))
								.calculaPontosCaracteristicaRelativo(this.valorMaximoCaracteristicas))
						- temp2);
	}

	/**
	 * Decrementa o valor das funções de utilidade envolvidas
	 * 
	 * @param parIndProfissional
	 *            Índice do profissional no array de profissionais
	 * @param parIndAtividade
	 *            Índice da atividade no array de atividades
	 */
	private void decrementaFuncaoCusto(int parIndProfissional,
			int parIndAtividade) {
		int temp = 0;
		double temp2 = 0;
		this.custoMonetario = this.custoMonetario
				- ((Profissional) this.profissionais.get(parIndProfissional))
						.getValorHora()
				* ((Atividade) this.atividades.get(parIndAtividade))
						.getNumeroHoras();

		for (int i = 0; i != this.atividades.size(); i++) {
			if (this.dominio[parIndProfissional + 1][i] == PROFISSIONAL_SELECIONADO) {
				temp += 1;
			}
		}
		if (temp == 0) {
			this.tamEquipe -= 1;
		}

		// Calcula "pontuação" da atividade e do profissional.
		temp2 = Math
				.abs(Math
						.abs(((Atividade) this.atividades.get(parIndAtividade))
								.calculaPontosCaracteristicaRelativo(this.valorMaximoCaracteristicas))
						- Math
								.abs(((Profissional) this.profissionais
										.get(parIndProfissional))
										.calculaPontosCaracteristicaRelativo(this.valorMaximoCaracteristicas)));
		this.indSubalocacao -= temp2;
	}

	/**
	 * Decrementa especificamente a função de custo tempo
	 */
	private void decrementaFuncaoCustoTempo() {
		this.tempoProjeto = (int) ((this
				.getMaiorDataFinal(this.getAtividades()).getTime() - this
				.getMenorDataInicial(this.getAtividades()).getTime()) / DIA) + 1;
	}

	/**
	 * Checa se o custo atual não estourou o melhor custo (Branch and Bound)
	 * 
	 * @return True se não estourou o valor, False caso contrário
	 */
	private boolean checaFuncaoCusto() {

		if (this.fatorOtimizar == MENOR_CUSTO) {
			if (this.custoMonetario <= this.melhorCusto) {
				return true;
			}
			return false;
		}

		if (this.fatorOtimizar == MENOR_EQUIPE) {
			if (this.tamEquipe <= this.melhorCusto) {
				return true;
			}
			return false;
		}

		// tempo é caso especial, função pode crescer e decrescer
		// assim, branch and bound não é adequado
		if (this.fatorOtimizar == MENOR_TEMPO) {
			// if (this.tempoProjeto <= this.melhorCusto) {
			return true;
			// }
			// return false;
		}

		if (this.fatorOtimizar == MENOR_SUB_ALOCACAO) {
			if (this.indSubalocacao <= this.melhorCusto) {
				return true;
			}
			return false;
		}

		// Neste caso, só posso testar nas folhas
		if (this.fatorOtimizar == MAIOR_QUALIFICACAO) {
			return true;
		}

		return false;
	}

	/**
	 * Atualiza o melhor custo
	 */
	private boolean atualizaMelhorCusto() {
		if (this.fatorOtimizar == MENOR_CUSTO) {
			this.melhorCusto = this.custoMonetario;
		}
		if (this.fatorOtimizar == MENOR_EQUIPE) {
			this.melhorCusto = this.tamEquipe;
		}
		if (this.fatorOtimizar == MENOR_TEMPO) {
			this.melhorCusto = this.tempoProjeto;
		}
		if (this.fatorOtimizar == MENOR_SUB_ALOCACAO) {
			this.melhorCusto = this.indSubalocacao;
		}
		if (this.fatorOtimizar == MAIOR_QUALIFICACAO) {
			if (this.indSubalocacao > this.melhorCusto) {
				this.melhorCusto = this.indSubalocacao;
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checa se um profissional pode realizar uma atividade
	 * 
	 * @param parProfissional
	 *            Profissional que está sendo testado
	 * @param parAtividade
	 *            Atividade que está sendo testada
	 * @return True se o profissional pode realizar a atividade. False, caso
	 *         contrário
	 */
	private boolean checaProfissionalAtividade(Profissional parProfissional,
			Atividade parAtividade) {

		/*
		 * Checa se o profissional tem todas as caracteristicas necessárias :
		 * 
		 * Percorre a lista de caracteristicas exigidas pela atividade, testando
		 * se o profissional atende ao minimo 50% das necessidades. Caso não atenda 50% dos requisito, retorna
		 * falso. Se atender a todas(sair do for), passa a testar a
		 * disponibilidade
		 */
		float indice; //indice obtido de qualificacao do profissional
		int contador = 0; //conta as caracteriticas obtidas
					
		for (int i = 0; i != parAtividade.getQuantidadeCaracteristicas(); i++) {
			if ((parProfissional.checaCaracteristica(parAtividade
					.getCaracteristica(i).getDefinicaoCaracteristica()
					.getNome(), parAtividade.getCaracteristica(i).getValor()
					.getValorNumerico()))) {
				contador++;
			}
		}
		
		indice = ((float) contador / (float) parAtividade.getQuantidadeCaracteristicas());
		if (indice  < 0.66)
			return false;		

		/*
		 * Checa se o profissional está disponível no período em que a atividade
		 * precisa ser realizada
		 */
		if (!(parProfissional.checaDisponibilidade(
				parAtividade.getDataInicio(), parAtividade.getDataFim(),
				parAtividade.getNumeroHoras()))) {
			return false;
		}
		return true;
	}

	/**
	 * Função para retornar próxima atividade a ser considerada. No momento,
	 * está sendo pega a que tem menor domínio (MOST CONSTRAINED VARIABLE)
	 * 
	 * @return Índice da próxima atividade
	 */
	private int selecionaProximaAtividade() {
		int menor = 30000;
		int ind_menor = 0;

		for (int i = 0; i != this.atividades.size(); i++) {
			if ((this.dominio[0][i] < menor)
					&& (this.dominio[0][i] != ATIVIDADE_CONSIDERADA)) {
				menor = this.dominio[0][i];
				ind_menor = i;
			}
		}
		if (menor == 30000)
			return FIM;
		return ind_menor;
	}

	/**
	 * Função que fixa a alocação de um profissional a uma atividade
	 * 
	 * @param pro
	 *            Profissional a ser alocado
	 * @param atv
	 *            Atividade a qual o profissional foi alocado
	 * @param produtividade
	 *            Produtividade do profissional na atividade
	 * @return True se a alocação pode ser feita, False caso contrário
	 */
	private boolean fixaAlocacao(int pro, int atv, int produtividade) {
		//System.out.println("passou ->"+this.num_solucoes );

		
		this.dominio[pro + 1][atv] = PROFISSIONAL_SELECIONADO;
		this.dominio[0][atv] = ATIVIDADE_CONSIDERADA;
		Profissional p = ((Profissional) this.profissionais.get(pro));
		Atividade a = (Atividade) this.atividades.get(atv);

		// Atualiza atividade
		this.atualizaRedeTarefas(a, produtividade, 0);

		/* Caso em que se deseja priorizar um fator */
		/* Adiciona custo a funçao */
		this.incrementaFuncaoCusto(pro, atv);
		if (this.otimizacao) {
			if (!this.checaFuncaoCusto())
				return false;
		}

		/*
		 * Insere período de indisponibilidade no profissional, relativo a
		 * atividade atv
		 */
		p.inserirPeriodoIndisponibilidade(a.getDataInicio(), a.getDataFim(), a
				.getNumeroHorasDia());

		/* Os outros não podem mais fazer aquela atividade */
		for (int i = 1; i != this.profissionais.size() + 1; i++) {
			if (this.dominio[i][atv] == PROFISSIONAL_HABILITADO) {
				this.dominio[i][atv] = atv;
			}
		}
		/*
		 * Checa se o próprio profissional ficou impedido de realizar alguma
		 * atividade
		 */
		for (int i = 0; i != this.atividades.size(); i++) {
			//System.out.println(((Atividade) this.atividades.get(i)).getNome() +"aaaaaaaa" + ((Atividade) this.atividades.get(i)).getPessoa() );
			if (this.dominio[pro + 1][i] == PROFISSIONAL_HABILITADO) {
				/*
				 * Se alguma outra atividade tem conflito de tempo com a que foi
				 * atribuída
				 */
				if (!p.checaDisponibilidade(
						((Atividade) this.atividades.get(i)).getDataInicio(),
						((Atividade) this.atividades.get(i)).getDataFim(),
						((Atividade) this.atividades.get(i))
								.getNumeroHorasDia())) {
					this.dominio[pro + 1][i] = atv;
					this.dominio[0][i] = this.dominio[0][i] - 1;
				}
			}
		}

		/* Checa se o domínio foi esvaziado para alguma atividade */
		// FORWARD-CHECKING
		for (int i = 0; i != this.atividades.size(); i++) {
			if (this.dominio[0][i] == 0) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Desfaz uma alocação feita por FixaAlocacao
	 * 
	 * @param pro
	 *            Profssional
	 * @param atv
	 *            Atividade
	 * @param produtividade
	 *            Produtividade do profissional em relação a atividade
	 */
	private void desfazAlocacao(int pro, int atv, int produtividade) {
		this.dominio[pro + 1][atv] = PROFISSIONAL_HABILITADO;
		this.dominio[0][atv] = 0;
		Profissional p = (Profissional) this.profissionais.get(pro);
		Atividade a = (Atividade) this.atividades.get(atv);

		/* Diminui custo da funçao */
		this.decrementaFuncaoCusto(pro, atv);

		/*
		 * Exclui período de indisponibilidade no profissional, relativo a
		 * atividade atv
		 */
		p.excluirPeriodoIndisponibilidade(a.getDataInicio(), a.getDataFim(), a
				.getNumeroHorasDia());

		/* Os outros agora podem fazer aquela atividade */
		for (int i = 1; i != this.profissionais.size() + 1; i++) {
			if (this.dominio[i][atv] == atv) {
				this.dominio[i][atv] = PROFISSIONAL_HABILITADO;
				this.dominio[0][atv] = this.dominio[0][atv] + 1;
			} else {
				if (this.dominio[i][atv] == PROFISSIONAL_HABILITADO) {
					this.dominio[0][atv] = this.dominio[0][atv] + 1;
				}
			}
		}
		/*
		 * Checa se o próprio profissional ficou impedido de realizar alguma
		 * atividade Se sim, habilita novamente
		 */
		for (int i = 0; i != this.atividades.size(); i++) {
			if (this.dominio[pro + 1][i] == atv) {
				this.dominio[pro + 1][i] = PROFISSIONAL_HABILITADO;
				this.dominio[0][i] = this.dominio[0][i] + 1;
			}
		}

		/* Muda datas das atividades de acordo com a produtividade */
		this.atualizaRedeTarefas2(a);

		this.decrementaFuncaoCustoTempo();
	}

	/**
	 * Método principal do algoritmo, responsável por fazer tentativas de
	 * alocação até que se chegue as soluções
	 * 
	 * @param atv
	 *            Atividade sendo considerada
	 */
	public void Aloca(int atv) {
		int ind_profissional, ind_prox_atividade;
		int k, p;
		int ind_profissionais[];

		k = this.dominio[0][atv];
		ind_profissionais = new int[k];
		p = 0;

		/* Obtem profissionais que podem realizar a atividade atv */
		for (int i = 1; i != this.profissionais.size() + 1; i++) {
			if (this.dominio[i][atv] == PROFISSIONAL_HABILITADO) {
				ind_profissionais[p] = i - 1;
				p++;
			}
		}

		for (int i = 0; i != k; i++) {
			//System.out.println(i +"|"+ k);
			ind_profissional = ind_profissionais[i];

			Profissional pr = ((Profissional) this.profissionais
					.get(ind_profissional));
			Atividade at = (Atividade) this.atividades.get(atv);
			int produtividade = pr.checaProdutividadeProfissional(pr, at, this
					.getModeloProdutividade());

			
			//System.out.println(pr.getNome() + " - atividade->" + at.getNome()+ " - pessoa>" + at.getPessoa());
			
			
			
			if (this.fixaAlocacao(ind_profissional, atv, produtividade)) {
				ind_prox_atividade = this.selecionaProximaAtividade();
				if (ind_prox_atividade == FIM) {
					if (this.otimizacao) {
						if (this.atualizaMelhorCusto()) {
							this.num_solucoes++;
							adicionaSolucao();
						}
					} else {
						this.num_solucoes++;
						adicionaSolucao();

						if ((!this.todasSolucoes)
								&& (this.num_solucoes == this.numMaxSolucoes)) {

							this.desfazAlocacao(ind_profissional, atv,
									produtividade);
							return;
						}
					}
				} else {
					this.Aloca(ind_prox_atividade);
					if ((!this.todasSolucoes)
							&& (this.num_solucoes == this.numMaxSolucoes)) {
						this.desfazAlocacao(ind_profissional, atv,
								produtividade);
						return;
					}
				}
			}
			this.desfazAlocacao(ind_profissional, atv, produtividade);
		}
	}

	/**
	 * Método responsável por determinar o domínio inicial de cada atividade,
	 * considerando apenas a qualificação dos profissionais e os períodos de
	 * indisponibilidades pré-cadastrados (restrições estáticas)
	 */
	private void setaDominioInicial() {
		//System.out.println("iniciou");
		/* Seta o domínio inicial */
		for (int i = 0; i != this.profissionais.size(); i++) {
			for (int j = 0; j != this.atividades.size(); j++) {
								
				if(((Atividade) this.atividades.get(j)).getPessoa() == ""){
					if (checaProfissionalAtividade(((Profissional) this.profissionais.get(i)),((Atividade) this.atividades.get(j)))) {
						this.dominio[i + 1][j] = PROFISSIONAL_HABILITADO;
						this.dominio[0][j] = this.dominio[0][j] + 1;					
					} else {
						this.dominio[i + 1][j] = VAZIO;
					}					
				}else{	
					//adicionada esta condição para avaliar se a sugestão do gerente de projetos está disponivel e poderá ser utilizada
					if((((Profissional) this.profissionais.get(i)).getNome() == ((Atividade) this.atividades.get(j)).getPessoa()) && ((checaProfissionalAtividade(((Profissional) this.profissionais.get(i)),((Atividade) this.atividades.get(j)))))) {
						this.dominio[i + 1][j] = PROFISSIONAL_HABILITADO;
						this.dominio[0][j] = this.dominio[0][j] + 1;	
					}else{
						this.dominio[i + 1][j] = VAZIO;
					}					
					
				}		
			}
		}
	}
	
	/**
	 * Adiciona uma solução para o problema na lista de soluções
	 */
	public void adicionaSolucao() {

		boolean limpar = false;
		boolean adicionaMelhorTempo = true;

		if ((this.otimizacao) && (this.num_solucoes > 1)) {

			if (this.fatorOtimizar == MENOR_CUSTO) {
				if (this.melhorCusto < ((Solucao) this.solucoes.get(0))
						.getCustoMonetario()) {
					limpar = true;
				}
			}
			if (this.fatorOtimizar == MENOR_EQUIPE) {
				if (this.melhorCusto < ((Solucao) this.solucoes.get(0))
						.getTamanhoEquipe()) {
					limpar = true;
				}
			}
			adicionaMelhorTempo = false;
			if (this.fatorOtimizar == MENOR_TEMPO) {
				if (this.melhorCusto < ((Solucao) this.solucoes.get(0))
						.getTempoProjeto()) {
					limpar = true;
					adicionaMelhorTempo = true;
				}
				if (this.melhorCusto == ((Solucao) this.solucoes.get(0))
						.getTempoProjeto()) {
					adicionaMelhorTempo = true;
				}
			}
			if (this.fatorOtimizar == MENOR_SUB_ALOCACAO) {
				if (this.melhorCusto < ((Solucao) this.solucoes.get(0))
						.getIndSubalocacao()) {
					limpar = true;
				}
			}
			if (this.fatorOtimizar == MAIOR_QUALIFICACAO) {
				if (this.melhorCusto > ((Solucao) this.solucoes.get(0))
						.getIndSubalocacao()) {
					limpar = true;
				}
			}

			if (limpar) {
				this.solucoes.clear();
				this.num_solucoes = 1;
			}
		}

		if ((this.fatorOtimizar != MENOR_TEMPO)
				|| ((this.fatorOtimizar == MENOR_TEMPO) && (adicionaMelhorTempo))) {
			Solucao solucao = new Solucao(this.num_solucoes,
					this.custoMonetario, this.tamEquipe, this.indSubalocacao,
					this.tempoProjeto);

			for (int i = 0; i != this.atividades.size(); i++) {
				for (int j = 0; j != this.profissionais.size(); j++) {
					if (this.dominio[j + 1][i] == PROFISSIONAL_SELECIONADO) {

						Alocacao alocacao = new Alocacao(
								(Profissional) this.profissionais.get(j),
								(Atividade) this.atividades.get(i));

						solucao.adicionaAlocacao(alocacao);
					}
				}
			}
			//System.out.println("passou ddd");
			this.solucoes.add(solucao);
		}
	}

	/**
	 * Atualiza a rede de tarefas. Dada uma atividade, a rede de tarefas é
	 * atualizada de numDias
	 * 
	 * @param a
	 *            Atividade
	 * @param numDias
	 *            Número de dias para adicionar ou diminuir
	 */
	public void atualizaRedeTarefas(Atividade a, int numDias, int flag) {
		int numDiasNovo = 0;
		Date dataIniTemp = new Date();
		Date dataFimTemp = new Date();
		Date maiorPre = this.getMaiorDataFinal(a.getPreAtividades());

		dataIniTemp = Aplicacao.modificaData(a.getDataInicio(), -1 * numDias);
		dataFimTemp = Aplicacao.modificaData(a.getDataFim(), -1 * numDias);

		if (flag == 0) {
			a.setDataFim(dataFimTemp);
			numDiasNovo = numDias;
			a.getPilhaAuxiliarInicial().push(new Integer(0));
			a.getPilhaAuxiliarFinal().push(new Integer(numDiasNovo));

		} else {
			// Se não há uma pré-atividade com data maior
			if ((maiorPre == null) || (maiorPre.before(dataIniTemp))) {
				a.setDataInicio(dataIniTemp);
				a.setDataFim(dataFimTemp);
				numDiasNovo = numDias;
			}// Se há
			else {
				// Data inicial é um dia depois do maior final de atividade
				// que é pré-atividade
				dataIniTemp = Aplicacao.modificaData(maiorPre, 1);
				numDiasNovo = (int) ((a.dataInicio.getTime() - dataIniTemp
						.getTime()) / DIA);

				dataFimTemp = Aplicacao.modificaData(a.getDataFim(), -1
						* numDiasNovo);

				a.setDataInicio(dataIniTemp);
				a.setDataFim(dataFimTemp);
			}
			a.getPilhaAuxiliarInicial().push(new Integer(numDiasNovo));
			a.getPilhaAuxiliarFinal().push(new Integer(numDiasNovo));
		}

		for (int i = 0; i != a.posAtividades.size(); i++) {
			Atividade pa = (Atividade) a.posAtividades.get(i);
			atualizaRedeTarefas(pa, numDiasNovo, 1);
		}
	}

	/**
	 * Caso especial do método atualizaRedeTarefas
	 * @param a
	 */
	public void atualizaRedeTarefas2(Atividade a) {

		if (!a.getPilhaAuxiliarInicial().empty()
				&& !a.getPilhaAuxiliarFinal().empty()) {
			a.setDataInicio(Aplicacao.modificaData(a.getDataInicio(),
					((Integer) a.getPilhaAuxiliarInicial().pop()).intValue()));
			a.setDataFim(Aplicacao.modificaData(a.getDataFim(), ((Integer) a
					.getPilhaAuxiliarFinal().pop()).intValue()));

			for (int i = 0; i != a.posAtividades.size(); i++) {
				Atividade pa = (Atividade) a.posAtividades.get(i);
				atualizaRedeTarefas2(pa);
			}
		}
	}


	/***************************************************************************
	 * 
	 * Métodos de get e set dos atributos da classe
	 *  
	 **************************************************************************/

	/**
	 * @return Returns the modeloProdutividade.
	 */
	public int getModeloProdutividade() {
		return this.modeloProdutividade;
	}

	/**
	 * @param modeloProdutividade
	 *            The modeloProdutividade to set.
	 */
	public void setModeloProdutividade(int modeloProdutividade) {
		this.modeloProdutividade = modeloProdutividade;
	}

	public Hashtable getEscalas() {
		return this.escalas;
	}

	public ArrayList getTiposDefinicaoCaracteristica() {
		return this.tiposDefinicaoCaracteristica;
	}

	public Hashtable getCaracteristicas() {
		return this.caracteristicas;
	}

	public ArrayList getProfissionais() {
		return this.profissionais;
	}

	public ArrayList getAtividades() {
		return this.atividades;
	}

	public List getCaracteristicas(
			TipoDefinicaoCaracteristica parTipoDefinicaoCaracteristica) {
		List caracs = new ArrayList();
		Iterator iter = this.caracteristicas.values().iterator();
		while (iter.hasNext()) {
			DefinicaoCaracteristica dc = (DefinicaoCaracteristica) iter.next();
			if (dc.getTipoDefinicaoCaracteristica().getNome()
					.compareToIgnoreCase(
							parTipoDefinicaoCaracteristica.getNome()) == 0) {
				caracs.add(dc);
			}
		}
		return caracs;
	}
	
	//BUSCA PESSOAS PARA O COMBO
	public List getPessoas(Profissional profissional) {
		List caracs = new ArrayList();
		Iterator iter = this.profissionais.iterator();
		while (iter.hasNext()) {
			Profissional dc = (Profissional) iter.next();
				caracs.add(dc);
			
		}
		return caracs;
	}

	public ArrayList getSolucoes() {
		return this.solucoes;
	}

	public void setOtimizacao(boolean parOtimizacao) {
		this.otimizacao = parOtimizacao;
	}

	public void setTodasSolucoes(boolean parTodasSolucoes) {
		this.todasSolucoes = parTodasSolucoes;
	}

	public void setFatorOtimizar(int parFatorOtimizar) {
		this.fatorOtimizar = parFatorOtimizar;
	}

	public void setNumMaxSolucoes(int parNumMaxSolucoes) {
		this.numMaxSolucoes = parNumMaxSolucoes;
	}

	/**********************************************************************
	 * 
	 * Métodos de entrada e saída
	 *
	 **********************************************************************/

	/**
	 * Método auxiliar para imprimir todas as soluções no console 
	 */
	public void imprimeTodasSolucoes() {
		System.out.println("EXISTEM " + this.solucoes.size() + " SOLUÇÕES");

		String str = "";
		for (int i = 0; i != this.solucoes.size(); i++) {
			str = ((Solucao) this.solucoes.get(i)).getNumero() + ";"
					+ ((Solucao) this.solucoes.get(i)).getCustoMonetario()
					+ ";" + ((Solucao) this.solucoes.get(i)).getTamanhoEquipe()
					+ ";"
					+ ((Solucao) this.solucoes.get(i)).getIndSubalocacao()
					+ ";" + ((Solucao) this.solucoes.get(i)).getTempoProjeto();

			for (int j = 0; j != ((Solucao) this.solucoes.get(i))
					.getAlocacoes().size(); j++) {
				str += ";"
						+ ((Alocacao) ((Solucao) this.solucoes.get(i))
								.getAlocacoes().get(j)).getProfissional()
								.getNome();
			}

			System.out.println(str);

		}

	}

	/**
	 * Salva arquivo XML com os dados de entrada cadastrados
	 * 
	 * @param parArquivo
	 */
	public void salvarArquivoEntradaXML(File parArquivo) {
		DocumentBuilderFactory docBuilderFactory;
		DocumentBuilder docBuilder;
		Document documento;
		Element e0;
		Element e1;
		Element e2;
		try {
			docBuilderFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docBuilderFactory.newDocumentBuilder();

			documento = docBuilder.newDocument();

			/* Parte inicial de controles do arquivo */
			e0 = documento.createElement("dadosEntrada");
			documento.appendChild(e0);

			// Escalas e Valores de Escala
			Iterator iter = this.escalas.values().iterator();
			Iterator iter2 = null;
			while (iter.hasNext()) {
				Escala e = (Escala) iter.next();
				iter2 = e.getValoresEscala().values().iterator();
				e1 = documento.createElement("escala");
				e1.setAttribute("nome", e.getNome());
				e1.setAttribute("valor", e.getValorMaximo() + "");
				e0.appendChild(e1);
				while (iter2.hasNext()) {
					ValorEscala ve = (ValorEscala) iter2.next();
					e2 = documento.createElement("valorEscala");
					e2.setAttribute("nome", ve.getNome());
					e2.setAttribute("valor", ve.getValorNumerico() + "");
					e1.appendChild(e2);
				}
			}

			// Tipos de Definições de Características
			iter = this.tiposDefinicaoCaracteristica.iterator();
			while (iter.hasNext()) {
				TipoDefinicaoCaracteristica tdc = (TipoDefinicaoCaracteristica) iter
						.next();
				e1 = documento.createElement("tipoDefinicaoCaracteristica");
				e1.setAttribute("nome", tdc.getNome());
				e1.setAttribute("nomeEscala", tdc.getEscala().getNome());
				e0.appendChild(e1);
			}

			// Definições de Características
			iter = this.caracteristicas.values().iterator();
			while (iter.hasNext()) {
				DefinicaoCaracteristica dc = (DefinicaoCaracteristica) iter
						.next();
				e1 = documento.createElement("definicaoCaracteristica");
				e1.setAttribute("nome", dc.getNome());
				e1.setAttribute("nomeTipoDefinicaoCaracteristica", dc
						.getTipoDefinicaoCaracteristica().getNome());
				e0.appendChild(e1);
			}

			// Profissionais
			iter = this.profissionais.iterator();
			while (iter.hasNext()) {
				Profissional p = (Profissional) iter.next();
				e1 = documento.createElement("profissional");
				e1.setAttribute("nome", p.getNome());
				e1.setAttribute("valorHora", p.getValorHora() + "");
				e1.setAttribute("qtdHorasDia", p.getQtdHorasDia() + "");
				e0.appendChild(e1);
				iter2 = p.getCaracteristicas().values().iterator();
				while (iter2.hasNext()) {
					Caracteristica c = (Caracteristica) iter2.next();
					e2 = documento.createElement("caracteristicaProfissional");
					e2.setAttribute("nomeDefinicaoCaracteristica", c
							.getDefinicaoCaracteristica().getNome());
					e2.setAttribute("valorEscala", c.getValor()
							.getValorNumerico()
							+ "");
					e1.appendChild(e2);
				}
				iter2 = p.getPeriodosIndisponibilidade().iterator();
				while (iter2.hasNext()) {
					PeriodoIndisponivel pi = (PeriodoIndisponivel) iter2.next();
					e2 = documento.createElement("periodoIndisponivel");
					e2.setAttribute("dataInicio", pi.getDataInicioString());
					e2.setAttribute("dataFim", pi.getDataFimString());
					e2.setAttribute("qtdHoras", pi.getQtdHoras() + "");
					e1.appendChild(e2);
				}
			}

			// Atividades
			iter = this.atividades.iterator();
			while (iter.hasNext()) {
				Atividade a = (Atividade) iter.next();
				e1 = documento.createElement("atividade");
				e1.setAttribute("nome", a.getNome());
				e1.setAttribute("dataInicio", a.getDataInicioString());
				e1.setAttribute("dataFim", a.getDataFimString());
				e1.setAttribute("pessoa", a.getPessoa());
				e1.setAttribute("numeroHorasDia", a.getNumeroHorasDia() + "");
				
				e0.appendChild(e1);
				iter2 = a.getCaracteristicas().iterator();
				while (iter2.hasNext()) {
					Caracteristica c = (Caracteristica) iter2.next();
					e2 = documento.createElement("caracteristicaAtividade");
					e2.setAttribute("nomeDefinicaoCaracteristica", c
							.getDefinicaoCaracteristica().getNome());
					e2.setAttribute("valorEscala", c.getValor()
							.getValorNumerico()
							+ "");
					e1.appendChild(e2);
				}
				iter2 = a.getPreAtividades().iterator();
				while (iter2.hasNext()) {
					Atividade pa = (Atividade) iter2.next();
					e2 = documento.createElement("preAtividade");
					e2.setAttribute("nomePreAtividade", pa.getNome());
					e1.appendChild(e2);
				}
			}

			// Prepare the DOM document for writing
			Source source = new DOMSource(documento);

			// Prepare the output file
			Result result = new StreamResult(parArquivo);

			// Write the DOM document to the file
			Transformer xformer = TransformerFactory.newInstance()
					.newTransformer();
			xformer.setOutputProperty("indent", "yes");
			xformer.setOutputProperty("encoding", "UTF-16");
			xformer.transform(source, result);
		} catch (TransformerConfigurationException err) {
			System.out.println(err.getMessage());
		} catch (TransformerException err) {
			System.out.println(err.getMessage());
		} catch (Exception err) {
			System.out.println(err.getMessage());
		}
	}

	/**
	 * Abre arquivo XML com os dados de entrada
	 * 
	 * @param parArquivo
	 *            Nome do arquivo
	 */
	public void abrirArquivoEntradaXML(File parArquivo) {
		DocumentBuilderFactory docBuilderFactory;
		DocumentBuilder docBuilder;
		Document doc;
		NodeList nl, nl2;

		try {
			// Obtém o documento XML
			docBuilderFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docBuilderFactory.newDocumentBuilder();
			doc = docBuilder.parse(parArquivo);

			nl = doc.getElementsByTagName("escala");
			for (int i = 0; i != nl.getLength(); i++) {
				this.inserirEscala(((Element) nl.item(i)).getAttribute("nome"),
						((Element) nl.item(i)).getAttribute("valor"));

				nl2 = ((Element) nl.item(i))
						.getElementsByTagName("valorEscala");
				for (int j = 0; j != nl2.getLength(); j++) {
					this.inserirValorEscala(((Element) nl.item(i))
							.getAttribute("nome"), ((Element) nl2.item(j))
							.getAttribute("nome"), ((Element) nl2.item(j))
							.getAttribute("valor"));
				}
			}
			nl = doc.getElementsByTagName("tipoDefinicaoCaracteristica");
			for (int i = 0; i != nl.getLength(); i++) {
				Escala e = (Escala) this.escalas.get(((Element) nl.item(i))
						.getAttribute("nomeEscala"));
				this.inserirTipoDefinicaoCaracteristica(((Element) nl.item(i))
						.getAttribute("nome"), e);
			}

			nl = doc.getElementsByTagName("definicaoCaracteristica");
			for (int i = 0; i != nl.getLength(); i++) {

				Iterator iterTipo = this.tiposDefinicaoCaracteristica
						.iterator();
				TipoDefinicaoCaracteristica tdc = null;
				while (iterTipo.hasNext()) {
					tdc = (TipoDefinicaoCaracteristica) iterTipo.next();
					if (tdc
							.getNome()
							.compareToIgnoreCase(
									((Element) nl.item(i))
											.getAttribute("nomeTipoDefinicaoCaracteristica")) == 0) {
						break;
					}
				}
				this.inserirDefinicaoCaracteristica(((Element) nl.item(i))
						.getAttribute("nome"), tdc);
			}

			nl = doc.getElementsByTagName("profissional");
			for (int i = 0; i != nl.getLength(); i++) {
				this.inserirProfissional(((Element) nl.item(i))
						.getAttribute("nome"), ((Element) nl.item(i))
						.getAttribute("valorHora"), ((Element) nl.item(i))
						.getAttribute("qtdHorasDia"));

				nl2 = ((Element) nl.item(i))
						.getElementsByTagName("caracteristicaProfissional");
				for (int j = 0; j != nl2.getLength(); j++) {
					DefinicaoCaracteristica dc = (DefinicaoCaracteristica) this.caracteristicas
							.get(((Element) nl2.item(j))
									.getAttribute("nomeDefinicaoCaracteristica"));
					ValorEscala ve = (ValorEscala) dc
							.getTipoDefinicaoCaracteristica().getEscala()
							.getValoresEscala().get(
									new Integer(((Element) nl2.item(j))
											.getAttribute("valorEscala")));
					this.inserirCaracteristicaProfissional(((Element) nl
							.item(i)).getAttribute("nome"), dc, ve);
				}

				nl2 = ((Element) nl.item(i))
						.getElementsByTagName("periodoIndisponivel");
				for (int j = 0; j != nl2.getLength(); j++) {
					this.inserirPeriodoIndisponibilidadeProfissional(
							((Element) nl.item(i)).getAttribute("nome"),
							((Element) nl2.item(j)).getAttribute("dataInicio"),
							((Element) nl2.item(j)).getAttribute("dataFim"),
							((Element) nl2.item(j)).getAttribute("qtdHoras"));
				}
			}

			nl = doc.getElementsByTagName("atividade");
			for (int i = 0; i != nl.getLength(); i++) {
				
				
				
				
				
				
				
				
				this.inserirAtividade(((Element) nl.item(i))
						.getAttribute("nome"), ((Element) nl.item(i))
						.getAttribute("dataInicio"), ((Element) nl.item(i))
						.getAttribute("dataFim"), ((Element) nl.item(i))
						.getAttribute("numeroHorasDia"), ((Element) nl.item(i))
						.getAttribute("pessoa").toString() );

				nl2 = ((Element) nl.item(i))
						.getElementsByTagName("caracteristicaAtividade");
				for (int j = 0; j != nl2.getLength(); j++) {
					DefinicaoCaracteristica dc = (DefinicaoCaracteristica) this.caracteristicas
							.get(((Element) nl2.item(j))
									.getAttribute("nomeDefinicaoCaracteristica"));
					ValorEscala ve = (ValorEscala) dc
							.getTipoDefinicaoCaracteristica().getEscala()
							.getValoresEscala().get(
									new Integer(((Element) nl2.item(j))
											.getAttribute("valorEscala")));
					this.inserirCaracteristicaAtividade(((Element) nl.item(i))
							.getAttribute("nome"), dc, ve);
				}

				// pré atividades
				nl2 = ((Element) nl.item(i))
						.getElementsByTagName("preAtividade");

				for (int j = 0; j != nl2.getLength(); j++) {
					Atividade pa = this
							.getAtividadeNome(((Element) nl2.item(j))
									.getAttribute("nomePreAtividade"));
					Atividade a = this.getAtividadeNome(((Element) nl.item(i))
							.getAttribute("nome"));

					a.adicionaPreAtividade(pa);
					pa.adicionaPosAtividade(a);
				}
			}

			//this.imprimeTeste();

		} catch (Exception err) {
			err.printStackTrace();
		}
	}

	/***********************************************************************
	 * 
	 * Métodos auxiliares
	 * 
	 ***********************************************************************/

	/**
	 * Adiciona num dias a data parData
	 * 
	 * @param parData
	 *            Data a ser modificada
	 * @param numDias
	 *            Número de dias a serem adicionados/removidos
	 * @return Data modificada
	 */
	public static Date modificaData(Date parData, int numDias) {
		if (numDias != 0) {
			Calendar now = new GregorianCalendar();
			now.setTime(parData);
			now.add(Calendar.DAY_OF_MONTH, numDias);
			return now.getTime();
		}
		return parData;
	}

	/**
	 * Obtém a menor data inicial das atividades
	 * 
	 * @return Menor data inicial
	 */
	public Date getMenorDataInicial(ArrayList listaAtividades) {
		Date menor = null;
		for (int i = 0; i != listaAtividades.size(); i++) {
			Atividade a = (Atividade) listaAtividades.get(i);
			if (menor == null) {
				menor = a.getDataInicio();
			} else {
				if (a.getDataInicio().before(menor)) {
					menor = a.getDataInicio();
				}
			}
		}
		return menor;
	}

	/**
	 * Obtém a maior data final das atividades
	 * 
	 * @return Maior data final
	 */
	public Date getMaiorDataFinal(ArrayList listaAtividades) {
		Date maior = null;
		for (int i = 0; i != listaAtividades.size(); i++) {
			Atividade a = (Atividade) listaAtividades.get(i);
			if (maior == null) {
				maior = a.getDataFim();
			} else {
				if (a.getDataFim().after(maior)) {
					maior = a.getDataFim();
				}
			}
		}
		return maior;
	}

	/**
	 * Obtém uma atividade, dado seu nome
	 * @param parNomeAtividade Nome da atividade
	 * @return Objeto atividade
	 */
	public Atividade getAtividadeNome(String parNomeAtividade) {
		for (int i = 0; i != this.atividades.size(); i++) {
			Atividade a = (Atividade) this.atividades.get(i);
			if (parNomeAtividade.compareToIgnoreCase(a.getNome()) == 0)
				return a;
		}
		return null;
	}

	/***********************************************************************
	 * 
	 * Métodos de cadastro
	 * 
	 ***********************************************************************/
	
	/**
	 * Cria um objeto escala
	 * @param parNome
	 * @param parValor
	 * @return
	 */
	public int inserirEscala(String parNome, String parValor) {
		int return_code = 0;
		int teste = 0;

		if (this.escalas.get(parNome) != null) {
			return_code = -1;
			return return_code;
		}
		try {
			teste = Integer.parseInt(parValor);
			if (teste <= 0) {
				return_code = -2;
				return return_code;
			}
		} catch (Exception e) {
			return_code = -3;
			return return_code;
		}

		if (this.valorMaximoCaracteristicas < Integer.parseInt(parValor)) {
			this.valorMaximoCaracteristicas = Integer.parseInt(parValor);
		}

		this.escalas.put(parNome, new Escala(parNome, Integer
				.parseInt(parValor)));
		return return_code;
	}

	/**
	 * Altera um objeto escala
	 * @param parNome
	 * @param parValorMaximo
	 * @return
	 */
	public int alterarEscala(String parNome, String parValorMaximo) {
		int return_code = 0;
		int teste = 0;

		// Novo valor dev ser inteiro
		try {
			teste = Integer.parseInt(parValorMaximo);
			if (teste <= 0) {
				return_code = -1;
				return return_code;
			}
		} catch (Exception e) {
			return_code = -2;
			return return_code;
		}

		Iterator iter1 = this.profissionais.iterator();
		Iterator iter2 = null;
		while (iter1.hasNext()) {
			Profissional p = (Profissional) iter1.next();
			iter2 = p.getCaracteristicas().values().iterator();
			while (iter2.hasNext()) {
				Caracteristica c = (Caracteristica) iter2.next();
				if (c.getValor().getValorNumerico() > Integer
						.parseInt(parValorMaximo)) {
					return_code = -3;
					return return_code;
				}
			}
		}

		iter1 = this.atividades.iterator();
		iter2 = null;
		while (iter1.hasNext()) {
			Atividade a = (Atividade) iter1.next();
			iter2 = a.getCaracteristicas().iterator();
			while (iter2.hasNext()) {
				Caracteristica c = (Caracteristica) iter2.next();
				if (c.getValor().getValorNumerico() > Integer
						.parseInt(parValorMaximo)) {
					return_code = -4;
					return return_code;
				}
			}
		}

		Escala e = (Escala) this.escalas.get(parNome);

		// Deleta itens de escala que deixaram de existir

		ArrayList listaExclusao = new ArrayList();
		if ((e.getValoresEscala() != null)
				&& (e.getValoresEscala().values().size() > 0)) {
			if (e.getValorMaximo() > Integer.parseInt(parValorMaximo)) {
				iter1 = e.getValoresEscala().values().iterator();
				while (iter1.hasNext()) {
					ValorEscala ve = (ValorEscala) iter1.next();
					if (ve.getValorNumerico() > Integer
							.parseInt(parValorMaximo)) {
						listaExclusao.add(ve);
					}
				}
			}
		}

		iter1 = listaExclusao.iterator();
		while (iter1.hasNext()) {
			ValorEscala ve = (ValorEscala) iter1.next();
			e.excluirValorEscala(ve.getValorNumerico());
		}
		listaExclusao = null;

		if (this.valorMaximoCaracteristicas < Integer.parseInt(parValorMaximo)) {
			this.valorMaximoCaracteristicas = Integer.parseInt(parValorMaximo);
		}
		e.setValorMaximo(Integer.parseInt(parValorMaximo));

		return return_code;
	}

	/**
	 * Exclui um objeto escala
	 * @param parNome
	 * @return
	 */
	public int excluirEscala(String parNome) {
		int return_code = 0;

		Iterator iter1 = this.profissionais.iterator();
		Iterator iter2 = null;
		while (iter1.hasNext()) {
			Profissional p = (Profissional) iter1.next();
			iter2 = p.getCaracteristicas().values().iterator();
			while (iter2.hasNext()) {
				Caracteristica c = (Caracteristica) iter2.next();
				if (c.getDefinicaoCaracteristica()
						.getTipoDefinicaoCaracteristica().getEscala().getNome()
						.compareTo(parNome) == 0) {
					return_code = -1;
					return return_code;
				}
			}
		}

		iter1 = this.atividades.iterator();
		iter2 = null;
		while (iter1.hasNext()) {
			Atividade a = (Atividade) iter1.next();
			iter2 = a.getCaracteristicas().iterator();
			while (iter2.hasNext()) {
				Caracteristica c = (Caracteristica) iter2.next();
				if (c.getDefinicaoCaracteristica()
						.getTipoDefinicaoCaracteristica().getEscala().getNome()
						.compareTo(parNome) == 0) {
					return_code = -2;
					return return_code;
				}
			}
		}
		this.escalas.remove(parNome);

		return return_code;
	}

	/**
	 * Insere valor de escala
	 * @param parNomeEscala
	 * @param parNome
	 * @param parValor
	 * @return
	 */
	public int inserirValorEscala(String parNomeEscala, String parNome,
			String parValor) {
		int return_code = 0;

		Escala e = (Escala) this.escalas.get(parNomeEscala);

		try {
			Integer.parseInt(parValor);
		} catch (Exception err) {
			return_code = -1;
			return return_code;
		}

		if ((e.getValoresEscala() != null)
				&& (e.getValoresEscala().get(new Integer(parValor)) != null)) {
			return_code = -2;
			return return_code;
		}
		if (Integer.parseInt(parValor) > e.getValorMaximo()) {
			return_code = -3;
			return return_code;
		}
		if (Integer.parseInt(parValor) <= 0) {
			return_code = -4;
			return return_code;
		}

		e.inserirValorEscala(parNome, Integer.parseInt(parValor));

		return return_code;
	}

	/**
	 * Alterar valor de escala
	 * @param parNomeEscala
	 * @param parNome
	 * @param parValor
	 * @return
	 */
	public int alterarValorEscala(String parNomeEscala, String parNome,
			String parValor) {
		int return_code = 0;

		Escala e = (Escala) this.escalas.get(parNomeEscala);
		ValorEscala ve = (ValorEscala) e.getValoresEscala().get(
				new Integer(parValor));

		Iterator iter1 = e.getValoresEscala().values().iterator();
		while (iter1.hasNext()) {
			ValorEscala v = (ValorEscala) iter1.next();
			if (v.getNome().compareToIgnoreCase(parNome) == 0) {
				return_code = -1;
				return return_code;
			}
		}
		ve.setNome(parNome);
		return return_code;
	}

	/**
	 * Cria novo tipo de definição de característica
	 * @param parNome
	 * @param parEscala
	 * @return
	 */
	public int inserirTipoDefinicaoCaracteristica(String parNome,
			Escala parEscala) {
		int return_code = 0;

		Iterator iter = this.tiposDefinicaoCaracteristica.iterator();
		while (iter.hasNext()) {
			if (parNome.compareToIgnoreCase(((TipoDefinicaoCaracteristica) iter
					.next()).getNome()) == 0) {
				return_code = -1;
				return return_code;
			}
		}

		TipoDefinicaoCaracteristica tdc = new TipoDefinicaoCaracteristica();
		tdc.setNome(parNome);
		tdc.setEscala(parEscala);
		this.tiposDefinicaoCaracteristica.add(tdc);

		return return_code;
	}

	/**
	 * Altera objeto tipo de definição de característica
	 * @param parNome
	 * @param parEscala
	 * @return
	 */
	public int alterarTipoDefinicaoCaracteristica(String parNome,
			Escala parEscala) {
		int return_code = 0;

		Iterator iter1 = this.profissionais.iterator();
		Iterator iter2 = null;
		while (iter1.hasNext()) {
			Profissional p = (Profissional) iter1.next();
			iter2 = p.getCaracteristicas().values().iterator();
			while (iter2.hasNext()) {
				Caracteristica c = (Caracteristica) iter2.next();
				if (c.getDefinicaoCaracteristica()
						.getTipoDefinicaoCaracteristica().getEscala().getNome()
						.compareTo(parEscala.getNome()) == 0) {
					return_code = -1;
					return return_code;
				}
			}
		}

		iter1 = this.atividades.iterator();
		iter2 = null;
		while (iter1.hasNext()) {
			Atividade a = (Atividade) iter1.next();
			iter2 = a.getCaracteristicas().iterator();
			while (iter2.hasNext()) {
				Caracteristica c = (Caracteristica) iter2.next();
				if (c.getDefinicaoCaracteristica()
						.getTipoDefinicaoCaracteristica().getEscala().getNome()
						.compareTo(parEscala.getNome()) == 0) {
					return_code = -2;
					return return_code;
				}
			}
		}

		iter1 = this.tiposDefinicaoCaracteristica.iterator();
		while (iter1.hasNext()) {
			TipoDefinicaoCaracteristica tdc = (TipoDefinicaoCaracteristica) iter1
					.next();
			if (tdc.getNome().compareToIgnoreCase(parNome) == 0) {
				tdc.setEscala(parEscala);
				return return_code;
			}
		}
		return return_code;
	}

	/**
	 * Exclui objeto tipo de definição de característica
	 * @param parNome
	 * @return
	 */
	public int excluirTipoDefinicaoCaracteristica(String parNome) {
		int return_code = 0;
		TipoDefinicaoCaracteristica tdc = null;

		Iterator iter1 = this.caracteristicas.values().iterator();
		while (iter1.hasNext()) {
			DefinicaoCaracteristica dc = (DefinicaoCaracteristica) iter1.next();
			if (dc.getNome().compareTo(parNome) == 0) {
				return_code = -1;
				return return_code;
			}
		}

		iter1 = this.tiposDefinicaoCaracteristica.iterator();
		while (iter1.hasNext()) {
			tdc = (TipoDefinicaoCaracteristica) iter1.next();
			if (tdc.getNome().compareToIgnoreCase(parNome) == 0) {
				this.tiposDefinicaoCaracteristica.remove(tdc);
				return return_code;
			}
		}
		return return_code;
	}

	/**
	 * Insere definição de característica Não permite incluir com nome repetido
	 * 
	 * @param parNome
	 * @return 0 se ok, -1 se forem dados repetidos
	 */
	public int inserirDefinicaoCaracteristica(String parNome,
			TipoDefinicaoCaracteristica parTipoDefinicaoCaracteristica) {
		int return_code = 0;

		if (this.caracteristicas.get(parNome) != null) {
			return_code = -1;
			return return_code;
		}

		this.caracteristicas.put(parNome, new DefinicaoCaracteristica(parNome,
				parTipoDefinicaoCaracteristica));
		return return_code;

	}

	/**
	 * Altera definição de característica. Só altera valor máximo. Testa se o
	 * novo valor máximo é menor que o valor dado a alguma característica desta
	 * definição
	 * 
	 * @param parNome
	 * @param parValorMaximo
	 * @return 0 se ok, -1 se alteração não for feita
	 */
	public int alterarDefinicaoCaracteristica(String parNome,
			TipoDefinicaoCaracteristica parTipoDefinicaoCaracteristica) {
		int return_code = 0;

		Iterator iter1 = this.profissionais.iterator();
		Iterator iter2 = null;
		while (iter1.hasNext()) {
			Profissional p = (Profissional) iter1.next();
			iter2 = p.getCaracteristicas().values().iterator();
			while (iter2.hasNext()) {
				Caracteristica c = (Caracteristica) iter2.next();
				if (c.getDefinicaoCaracteristica().getNome().compareTo(parNome) == 0) {
					return_code = -1;
					return return_code;
				}
			}
		}

		iter1 = this.atividades.iterator();
		iter2 = null;
		while (iter1.hasNext()) {
			Atividade a = (Atividade) iter1.next();
			iter2 = a.getCaracteristicas().iterator();
			while (iter2.hasNext()) {
				Caracteristica c = (Caracteristica) iter2.next();
				if (c.getDefinicaoCaracteristica().getNome().compareTo(parNome) == 0) {
					return_code = -2;
					return return_code;
				}
			}
		}

		((DefinicaoCaracteristica) this.caracteristicas.get(parNome))
				.setTipoDefinicaoCaracteristica(parTipoDefinicaoCaracteristica);

		return return_code;
	}

	/**
	 * Exclui definição de característica Checa se definição sendo excluída está
	 * presente em alguma característica
	 * 
	 * @param parNome
	 * @return 0 se ok, -1 se exclusão não for realizada
	 */
	public int excluirDefinicaoCaracteristica(String parNome) {
		int return_code = 0;
		/*
		 * É preciso testar se já existe algum profissional com a caracteristica
		 * sendo excluida
		 */
		Iterator iter1 = this.profissionais.iterator();
		while (iter1.hasNext()) {
			Profissional p = (Profissional) iter1.next();
			Caracteristica c = (Caracteristica) p.getCaracteristicas().get(
					parNome);
			if (c != null) {
				return_code = -1;
				return return_code;
			}
		}

		iter1 = this.atividades.iterator();
		while (iter1.hasNext()) {
			if (return_code != 0)
				break;
			Atividade a = (Atividade) iter1.next();
			Iterator iter2 = a.getCaracteristicas().iterator();

			while (iter2.hasNext()) {
				Caracteristica c = (Caracteristica) iter2.next();
				if (c.getDefinicaoCaracteristica().getNome()
						.compareToIgnoreCase(parNome) == 0) {
					return_code = -1;
					return return_code;
				}
			}
		}
		this.caracteristicas.remove(parNome);
		return return_code;
	}

	/**
	 * Cria novo profissional
	 * @param parNome
	 * @param parValorHora
	 * @param parQtdHorasDia
	 * @return
	 */
	public int inserirProfissional(String parNome, String parValorHora,
			String parQtdHorasDia) {
		int return_code = 0;
		double valorHora;
		int qtdHorasDia;

		try {
			valorHora = Double.parseDouble(parValorHora);
		} catch (Exception e) {
			return_code = -2;
			return return_code;
		}

		try {
			qtdHorasDia = Integer.parseInt(parQtdHorasDia);
		} catch (Exception e) {
			return_code = -3;
			return return_code;
		}

		// Checa se profissional já está na lista
		Iterator iter1 = this.profissionais.iterator();
		while (iter1.hasNext()) {
			Profissional p1 = (Profissional) iter1.next();
			if (p1.getNome().compareToIgnoreCase(parNome) == 0) {
				return_code = -1;
				return return_code;
			}
		}

		Profissional p = new Profissional(parNome, valorHora, qtdHorasDia);
		this.profissionais.add(p);
		return return_code;
	}

	/**
	 * Altera objeto profissional
	 * @param parNome
	 * @param parValorHora
	 * @param parQtdHorasDia
	 * @return
	 */
	public int alterarProfissional(String parNome, String parValorHora,
			String parQtdHorasDia) {
		int return_code = 0;

		double valorHora;
		int qtdHorasDia;

		try {
			valorHora = Double.parseDouble(parValorHora);
		} catch (Exception e) {
			return_code = -2;
			return return_code;
		}

		try {
			qtdHorasDia = Integer.parseInt(parQtdHorasDia);
		} catch (Exception e) {
			return_code = -3;
			return return_code;
		}

		Iterator iter1 = this.profissionais.iterator();
		while (iter1.hasNext()) {
			Profissional p1 = (Profissional) iter1.next();
			if (p1.getNome().compareToIgnoreCase(parNome) == 0) {
				p1.setValorHora(valorHora);
				p1.setQtdHorasDia(qtdHorasDia);
				return return_code;
			}
		}

		return return_code;
	}

	/**
	 * Exclui objeto profissional
	 * @param parNome
	 * @return
	 */
	public int excluirProfissional(String parNome) {
		int return_code = 0;

		Iterator iter1 = this.profissionais.iterator();
		while (iter1.hasNext()) {
			Profissional p1 = (Profissional) iter1.next();
			if (p1.getNome().compareToIgnoreCase(parNome) == 0) {
				this.profissionais.remove(p1);
				return return_code;
			}
		}
		return return_code;
	}

	/**
	 * Insere nova atividade
	 * @param parNome
	 * @param parDtInicio
	 * @param parDtFim
	 * @param parQtdHorasDia
	 * @return
	 */
	public int inserirAtividade(String parNome, String parDtInicio,
			String parDtFim, String parQtdHorasDia, String pessoa) {
		int return_code = 0;
		int teste = 0;

		Date dataInicio = null;
		Date dataFim = null;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			dataInicio = formato.parse(parDtInicio);
			dataFim = formato.parse(parDtFim);
		} catch (Exception e) {
		}

		// Data fim está antes da data inicial
		if (dataFim.before(dataInicio)) {
			return_code = -1;
			return return_code;
		}

		try {
			teste = Integer.parseInt(parQtdHorasDia);
		} catch (Exception e) {
			return_code = -2;
			return return_code;
		}

		if (teste <= 0) {
			return_code = -3;
			return return_code;
		}

		// Checa se atividade já está na lista
		Iterator iter1 = this.atividades.iterator();
		while (iter1.hasNext()) {
			Atividade a1 = (Atividade) iter1.next();
			if (a1.getNome().compareToIgnoreCase(parNome) == 0) {
				return_code = -4;
				return return_code;
			}
		}

		Atividade a = new Atividade(parNome, parDtInicio, parDtFim, Integer.parseInt(parQtdHorasDia),pessoa);
		this.atividades.add(a);
		return return_code;
	}

	/**
	 * Altera objeto atividade
	 * @param parNome
	 * @param parDtInicio
	 * @param parDtFim
	 * @param parQtdHorasDia
	 * @return
	 */
	public int alterarAtividade(String parNome, String parDtInicio,
			String parDtFim, String parQtdHorasDia, String pessoa) {
		int return_code = 0;
		int teste = 0;

		try {
			teste = Integer.parseInt(parQtdHorasDia);
		} catch (Exception e) {
			return_code = -1;
			return return_code;
		}

		if (teste <= 0) {
			return_code = -2;
			return return_code;
		}

		Date dataInicio = null;
		Date dataFim = null;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			dataInicio = formato.parse(parDtInicio);
			dataFim = formato.parse(parDtFim);
		} catch (Exception e) {
		}

		// Data fim está antes da data inicial
		if (dataFim.before(dataInicio)) {
			return_code = -3;
			return return_code;
		}

		Iterator iter1 = this.atividades.iterator();
		while (iter1.hasNext()) {
			Atividade a1 = (Atividade) iter1.next();
			if (a1.getNome().compareToIgnoreCase(parNome) == 0) {
				a1.setDataInicioString(parDtInicio);
				a1.setDataFimString(parDtFim);
				a1.setNumeroHorasDia(Integer.parseInt(parQtdHorasDia));
				a1.setPessoa(pessoa);
				return return_code;
			}
		}
		return return_code;
	}

	/**
	 * Exclui objeto atividade
	 * @param parNome
	 * @return
	 */
	public int excluirAtividade(String parNome) {
		int return_code = 0;

		Iterator iter1 = this.atividades.iterator();
		while (iter1.hasNext()) {
			Atividade a1 = (Atividade) iter1.next();
			if (a1.getNome().compareToIgnoreCase(parNome) == 0) {
				this.atividades.remove(a1);
				return return_code;
			}
		}
		return return_code;
	}

	/**
	 * Insere pre atividade na atividade
	 * @param parNome
	 * @param preAtividade
	 * @return
	 */
	public int inserirPreAtividade(String parNome, Atividade preAtividade) {
		Atividade a;
		int return_code = 0;

		a = this.getAtividadeNome(parNome);

		if (!(a.getPreAtividades().contains(preAtividade))) {
			a.adicionaPreAtividade(preAtividade);
		} else {
			return_code = -1;
		}
		if (!(preAtividade.getPosAtividades().contains(a))) {
			preAtividade.adicionaPosAtividade(a);
		}

		return return_code;
	}

	/**
	 * Exclui uma pré atividade de uma atividade
	 * 
	 * @param parNome
	 *            Nome da atividade da qual se deseja excluir a prá atividade
	 * @param preAtividade
	 *            Pré atividade sendo excluida
	 * @return Código de retorno
	 */
	public int excluirPreAtividade(String parNome, Atividade preAtividade) {
		Atividade a;

		a = this.getAtividadeNome(parNome);

		a.excluirPreAtividade(preAtividade);
		preAtividade.excluirPosAtividade(a);

		int return_code = 0;

		return return_code;
	}

	public ArrayList obterPreAtividadesAtividade(String parNomeAtividade) {
		Atividade a;

		a = this.getAtividadeNome(parNomeAtividade);

		return a.getPreAtividades();
	}

	public int inserirCaracteristicaProfissional(String parNomeProfissional,
			DefinicaoCaracteristica parDefinicaoCaracteristica,
			ValorEscala parValor) {
		int return_code = 0;

		Iterator iter1 = this.profissionais.iterator();
		Iterator iter2 = null;
		while (iter1.hasNext()) {
			Profissional p1 = (Profissional) iter1.next();
			if (p1.getNome().compareToIgnoreCase(parNomeProfissional) == 0) {
				iter2 = p1.getCaracteristicas().values().iterator();
				while (iter2.hasNext()) {
					Caracteristica c = (Caracteristica) iter2.next();
					if (c.getDefinicaoCaracteristica().getNome()
							.compareToIgnoreCase(
									parDefinicaoCaracteristica.getNome()) == 0) {
						return_code = -2;
						return return_code;
					}
				}

				p1.inserirCaracteristica(parDefinicaoCaracteristica, parValor);
				return return_code;
			}
		}
		return return_code;
	}

	public int alterarCaracteristicaProfissional(String parNomeProfissional,
			DefinicaoCaracteristica parDefinicaoCaracteristica,
			ValorEscala parValor) {
		int return_code = 0;

		Iterator iter1 = this.profissionais.iterator();
		while (iter1.hasNext()) {
			Profissional p1 = (Profissional) iter1.next();
			if (p1.getNome().compareToIgnoreCase(parNomeProfissional) == 0) {
				p1.alterarCaracteristica(parDefinicaoCaracteristica.getNome(),
						parValor);
				return return_code;
			}
		}
		return return_code;
	}

	public void excluirCaracteristicaProfissional(String parNomeProfissional,
			DefinicaoCaracteristica parDefinicaoCaracteristica) {

		Iterator iter1 = this.profissionais.iterator();
		while (iter1.hasNext()) {
			Profissional p1 = (Profissional) iter1.next();
			if (p1.getNome().compareToIgnoreCase(parNomeProfissional) == 0) {
				p1.excluirCaracteristica(parDefinicaoCaracteristica.getNome());
				return;
			}
		}
	}

	public int inserirCaracteristicaAtividade(String parNomeAtividade,
			DefinicaoCaracteristica parCaracteristica, ValorEscala parValor) {
		int return_code = 0;

		Iterator iter1 = this.atividades.iterator();
		Iterator iter2 = null;
		while (iter1.hasNext()) {
			Atividade a1 = (Atividade) iter1.next();
			if (a1.getNome().compareToIgnoreCase(parNomeAtividade) == 0) {
				iter2 = a1.getCaracteristicas().iterator();
				while (iter2.hasNext()) {
					Caracteristica c = (Caracteristica) iter2.next();
					if (c.getDefinicaoCaracteristica().getNome()
							.compareToIgnoreCase(parCaracteristica.getNome()) == 0) {
						return_code = -1;
						return return_code;
					}
				}
				a1.inserirCaracteristica(parCaracteristica, parValor);
				return return_code;
			}
		}
		return return_code;
	}

	public int alterarCaracteristicaAtividade(String parNomeAtividade,
			DefinicaoCaracteristica parCaracteristica, ValorEscala parValor) {
		int return_code = 0;

		Iterator iter1 = this.atividades.iterator();
		while (iter1.hasNext()) {
			Atividade a1 = (Atividade) iter1.next();
			if (a1.getNome().compareToIgnoreCase(parNomeAtividade) == 0) {
				a1.alterarCaracteristica(parCaracteristica.getNome(), parValor);
				return return_code;
			}
		}
		return return_code;
	}

	public void excluirCaracteristicaAtividade(String parNomeAtividade,
			DefinicaoCaracteristica parCaracteristica) {

		Iterator iter1 = this.atividades.iterator();
		while (iter1.hasNext()) {
			Atividade a1 = (Atividade) iter1.next();
			if (a1.getNome().compareToIgnoreCase(parNomeAtividade) == 0) {
				a1.excluirCaracteristica(parCaracteristica.getNome());
				return;
			}
		}
	}

	public int inserirPeriodoIndisponibilidadeProfissional(
			String parNomeProfissional, String parDtInicio, String parDtFim,
			String parNumHoras) {
		int return_code = 0;
		Date dataInicio = null;
		Date dataFim = null;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			dataInicio = formato.parse(parDtInicio);
			dataFim = formato.parse(parDtFim);
		} catch (Exception e) {
			// Data inválida
			return_code = -1;
			return return_code;
		}

		// Data fim está antes da data inicial
		if (dataFim.before(dataInicio)) {
			return_code = -2;
			return return_code;
		}

		Iterator iter1 = this.profissionais.iterator();
		while (iter1.hasNext()) {
			Profissional p = (Profissional) iter1.next();
			if (p.getNome().compareToIgnoreCase(parNomeProfissional) == 0) {
				// Testa se num de horas do período é maior que número de horas
				// trabalhadas
				if (Integer.parseInt(parNumHoras) > p.getQtdHorasDia()) {
					return_code = -3;
					return return_code;
				}
				// Testa se já existe
				Iterator iter2 = p.getPeriodosIndisponibilidade().iterator();
				while (iter2.hasNext()) {
					PeriodoIndisponivel pi = (PeriodoIndisponivel) iter2.next();
					if ((pi.getDataInicio().compareTo(dataInicio) == 0)
							&& (pi.getDataFim().compareTo(dataFim) == 0)
							&& (pi.getQtdHoras() == Integer
									.parseInt(parNumHoras))) {
						return_code = -4;
						return return_code;
					}
				}

				p.inserirPeriodoIndisponibilidade(dataInicio, dataFim, Integer
						.parseInt(parNumHoras));
				return return_code;
			}
		}
		return return_code;
	}

	public int alterarPeriodoIndisponibilidadeProfissional(
			String parNomeProfissional, String parDtInicioAnterior,
			String parDtFimAnterior, String parNumHorasAnterior,
			String parDtInicio, String parDtFim, String parNumHoras) {
		int return_code = 0;
		Date dataInicioAnterior = null;
		Date dataFimAnterior = null;
		Date dataInicio = null;
		Date dataFim = null;

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		try {
			dataInicioAnterior = formato.parse(parDtInicioAnterior);
			dataFimAnterior = formato.parse(parDtFimAnterior);
			dataInicio = formato.parse(parDtInicio);
			dataFim = formato.parse(parDtFim);
		} catch (Exception e) {
			// Data inválida
			return_code = -1;
			return return_code;
		}

		// Data fim está antes da data inicial
		if (dataFim.before(dataInicio)) {
			return_code = -2;
			return return_code;
		}

		Iterator iter1 = this.profissionais.iterator();
		while (iter1.hasNext()) {
			Profissional p = (Profissional) iter1.next();
			if (p.getNome().compareToIgnoreCase(parNomeProfissional) == 0) {
				// Testa se num de horas do período é maior que número de horas
				// trabalhadas
				if (Integer.parseInt(parNumHoras) > p.getQtdHorasDia()) {
					return_code = -3;
					return return_code;
				}
				Iterator iter2 = p.getPeriodosIndisponibilidade().iterator();
				while (iter2.hasNext()) {
					PeriodoIndisponivel pi = (PeriodoIndisponivel) iter2.next();
					if ((pi.getDataInicio().compareTo(dataInicio) == 0)
							&& (pi.getDataFim().compareTo(dataFim) == 0)
							&& (pi.getQtdHoras() == Integer
									.parseInt(parNumHoras))) {
						return_code = -4;
						return return_code;
					}
				}
				iter2 = p.getPeriodosIndisponibilidade().iterator();
				while (iter2.hasNext()) {
					PeriodoIndisponivel pi = (PeriodoIndisponivel) iter2.next();
					if ((pi.getDataInicio().compareTo(dataInicioAnterior) == 0)
							&& (pi.getDataFim().compareTo(dataFimAnterior) == 0)
							&& (pi.getQtdHoras() == Integer
									.parseInt(parNumHorasAnterior))) {

						pi.setDataInicio(dataInicio);
						pi.setDataFim(dataFim);
						pi.setQtdHoras(Integer.parseInt(parNumHoras));
						return return_code;
					}
				}
			}
		}
		return return_code;
	}

	public void excluirPeriodoIndisponibilidadeProfissional(
			String parNomeProfissional, String parDtInicio, String parDtFim,
			String parNumHoras) {
		Date dataInicio = null;
		Date dataFim = null;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			dataInicio = formato.parse(parDtInicio);
			dataFim = formato.parse(parDtFim);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Iterator iter1 = this.profissionais.iterator();
		while (iter1.hasNext()) {
			Profissional p = (Profissional) iter1.next();
			if (p.getNome().compareToIgnoreCase(parNomeProfissional) == 0) {
				Iterator iter2 = p.getPeriodosIndisponibilidade().iterator();
				while (iter2.hasNext()) {
					PeriodoIndisponivel pi = (PeriodoIndisponivel) iter2.next();
					if ((pi.getDataInicio().compareTo(dataInicio) == 0)
							&& (pi.getDataFim().compareTo(dataFim) == 0)
							&& (pi.getQtdHoras() == Integer
									.parseInt(parNumHoras))) {
						p.excluirPeriodoIndisponibilidade(dataInicio, dataFim,
								Integer.parseInt(parNumHoras));
						return;
					}
				}
			}
		}
	}

	public ArrayList obterPeriodosProfissional(String parNomeProfissional) {
		ArrayList periodos = new ArrayList();
		Iterator iter1 = this.profissionais.iterator();
		while (iter1.hasNext()) {
			Profissional p = (Profissional) iter1.next();
			if (p.getNome().compareToIgnoreCase(parNomeProfissional) == 0) {
				periodos = p.getPeriodosIndisponibilidade();
				return periodos;
			}
		}
		return periodos;
	}

	public ArrayList obterCaracteristicasProfissional(String parNomeProfissional) {
		ArrayList caracteristicasProfissional = new ArrayList();
		Iterator iter1 = this.profissionais.iterator();
		Iterator iter2 = null;
		while (iter1.hasNext()) {
			Profissional p = (Profissional) iter1.next();
			if (p.getNome().compareToIgnoreCase(parNomeProfissional) == 0) {
				iter2 = p.getCaracteristicas().values().iterator();
				while (iter2.hasNext()) {
					caracteristicasProfissional.add(iter2.next());
				}
				return caracteristicasProfissional;
			}
		}
		return caracteristicasProfissional;
	}

	public ArrayList obterCaracteristicasAtividade(String parNomeAtividade) {
		ArrayList caracteristicasAtividade = new ArrayList();
		Iterator iter1 = this.atividades.iterator();
		Iterator iter2 = null;
		while (iter1.hasNext()) {
			Atividade a = (Atividade) iter1.next();
			if (a.getNome().compareToIgnoreCase(parNomeAtividade) == 0) {
				iter2 = a.getCaracteristicas().iterator();
				while (iter2.hasNext()) {
					caracteristicasAtividade.add(iter2.next());
				}
				return caracteristicasAtividade;
			}
		}
		return caracteristicasAtividade;
	}

	public ArrayList obterValoresEscala(String parNomeEscala) {
		ArrayList valoresEscalas = new ArrayList();
		Iterator iter1 = this.escalas.values().iterator();
		Iterator iter2 = null;
		while (iter1.hasNext()) {
			Escala e = (Escala) iter1.next();
			if (e.getNome().compareToIgnoreCase(parNomeEscala) == 0) {
				iter2 = e.getValoresEscala().values().iterator();
				while (iter2.hasNext()) {
					valoresEscalas.add(iter2.next());
				}
				return valoresEscalas;
			}
		}
		return valoresEscalas;
	}


	/**********************************************************
	 * 
	 * MÉTODO PRINCIPAL DA APLICAÇÃO - EXECUTA A ALOCAÇÃO 
	 * 
	 **********************************************************/
	
	public void executar() {
		// Aplicacao a = new Aplicacao();
		int ind_atividade;
		int ind_prox_atividade;
		int ind_profissional;
		int k, p;
		int ind_profissionais[];

		this.num_solucoes = 0;

		if (this.otimizacao) {
			if ((this.fatorOtimizar == MENOR_CUSTO)
					|| (this.fatorOtimizar == MENOR_EQUIPE)
					|| (this.fatorOtimizar == MENOR_SUB_ALOCACAO)
					|| (this.fatorOtimizar == MENOR_TEMPO)) {
				this.melhorCusto = INFINITO;
			} else {
				this.melhorCusto = 0;
			}
		}
		//cria matriz de pessoas e atividades
		this.dominio = new int[this.profissionais.size() + 1][this.atividades.size()];

		this.solucoes.clear();
		this.setaDominioInicial();

		ind_atividade = this.selecionaProximaAtividade();

		k = this.dominio[0][ind_atividade];
		ind_profissionais = new int[k];
		p = 0;

		/* Obtem profissionais que podem realizar a atividade atv */
		for (int i = 1; i != this.profissionais.size() + 1; i++) {
			if (this.dominio[i][ind_atividade] == PROFISSIONAL_HABILITADO) {
				ind_profissionais[p] = i - 1;
				p++;
			}
		}
		
		try {
			for (int i = 0; i != k; i++) {				
				ind_profissional = ind_profissionais[i];

				Profissional pr = ((Profissional) this.profissionais
						.get(ind_profissional));
				Atividade at = (Atividade) this.atividades.get(ind_atividade);
				int produtividade = pr.checaProdutividadeProfissional(pr, at,
						this.getModeloProdutividade());

				if (this.fixaAlocacao(ind_profissional, ind_atividade,
						produtividade)) {

					ind_prox_atividade = this.selecionaProximaAtividade();

					if (ind_prox_atividade == FIM) {
						if (this.otimizacao) {
							if (this.atualizaMelhorCusto()) {
								this.num_solucoes++;
								this.adicionaSolucao();
							}
						} else {
							this.num_solucoes++;
							this.adicionaSolucao();
							if ((!this.todasSolucoes)
									&& (this.num_solucoes == this.numMaxSolucoes)) {
								this.desfazAlocacao(ind_profissional,
										ind_atividade, produtividade);
								return;
							}
						}
					} else {
						this.Aloca(ind_prox_atividade);
						if ((!this.todasSolucoes)
								&& (this.num_solucoes == this.numMaxSolucoes)) {
							this.desfazAlocacao(ind_profissional,
									ind_atividade, produtividade);
							return;
						}
					}
				}
				this.desfazAlocacao(ind_profissional, ind_atividade,
						produtividade);

			}
		} catch (OutOfMemoryError e) {
			System.out
					.println("Não é possível completar a operação com as configurações atuis de sua JVM. É preciso aumentar o tamanho do heap. Ex.: java -Xms1G -Xmx1G <classe>.");
		}
		//imprimeTodasSolucoes();
		// this.testaConsistenciaSolucoes();
	}

	
	/**************************************************************
	 * 
	 * Métodos de DEBUG 
	 *
	 **************************************************************/
	
	public void imprimeTeste() {

		System.out.println("ESCALAS:");
		Iterator iter = this.escalas.values().iterator();
		Iterator iter2 = null;
		while (iter.hasNext()) {
			Escala e = (Escala) iter.next();
			System.out.println(e.getNome() + " : " + e.getValorMaximo());
			iter2 = e.getValoresEscala().values().iterator();
			while (iter2.hasNext()) {
				ValorEscala ve = (ValorEscala) iter2.next();
				System.out.println("  " + ve.getNome() + " : "
						+ ve.getValorNumerico());
			}

		}

		System.out.println("CARACTERISTICAS:");
		iter = this.caracteristicas.values().iterator();
		iter2 = null;
		while (iter.hasNext()) {
			DefinicaoCaracteristica dc = (DefinicaoCaracteristica) iter.next();
			System.out.println(dc.getNome()
					+ " : "
					+ dc.getTipoDefinicaoCaracteristica().getNome()
					+ " : "
					+ dc.getTipoDefinicaoCaracteristica().getEscala()
							.getValorMaximo());
		}

		System.out.println("PROFISSIONAIS:");
		iter = this.profissionais.iterator();
		while (iter.hasNext()) {
			Profissional p = (Profissional) iter.next();
			System.out.println(p.getNome() + " : " + p.getValorHora() + " : "
					+ p.getQtdHorasDia());

			System.out.println("  PERIODOS INDISP.:");
			iter2 = p.getPeriodosIndisponibilidade().iterator();
			while (iter2.hasNext()) {
				PeriodoIndisponivel pi = (PeriodoIndisponivel) iter2.next();
				System.out.println(pi.getDataInicioString() + " : "
						+ pi.getDataFimString() + " : " + pi.getQtdHoras());
			}
			System.out.println("  CARACS.POSSUIDAS: "
					+ p.getCaracteristicas().values().size());
			iter2 = p.getCaracteristicas().values().iterator();
			while (iter2.hasNext()) {
				Caracteristica c = (Caracteristica) iter2.next();
				System.out.println(c.getDefinicaoCaracteristica().getNome()
						+ " : " + c.getValor());
			}
		}

		System.out.println("ATIVIDADES:");
		iter = this.atividades.iterator();
		while (iter.hasNext()) {
			Atividade a = (Atividade) iter.next();
			System.out.println(a.getNome() + " : " + a.getDataInicioString()
					+ " : " + a.getDataFimString());

			System.out.println("  CARACS.POSSUIDAS: "
					+ a.getCaracteristicas().size());
			iter2 = a.getCaracteristicas().iterator();
			while (iter2.hasNext()) {
				Caracteristica c = (Caracteristica) iter2.next();
				System.out.println(c.getDefinicaoCaracteristica().getNome()
						+ " : " + c.getValor());
			}

			System.out.println("  PRÉ ATIVIDADES: "
					+ a.getPreAtividades().size());
			iter2 = a.getPreAtividades().iterator();
			while (iter2.hasNext()) {
				Atividade pa = (Atividade) iter2.next();
				System.out.println(pa.getNome());
			}

			System.out.println("  POS ATIVIDADES: "
					+ a.getPosAtividades().size());
			iter2 = a.getPosAtividades().iterator();
			while (iter2.hasNext()) {
				Atividade pa = (Atividade) iter2.next();
				System.out.println(pa.getNome());
			}
		}

	}

	/**
	 * Faz algumas checagens de consistência nas soluções geradas 
	 */
	public void testaConsistenciaSolucoes() {
		// //////////////////
		// PARA EXECUTAR, DESCOMENTAR Clona, em Atividade!!!!
		// /////////////////

		int numErros = 0;

		// Percorre todas as soluções
		for (int i = 0; i != this.getSolucoes().size(); i++) {
			Solucao s = (Solucao) this.getSolucoes().get(i);
			ArrayList atividadesSolucao = s.getTodasAtividades();
			for (int j = 0; j != atividadesSolucao.size(); j++) {
				Atividade a = (Atividade) atividadesSolucao.get(j);

				// Testa data fim sempre maior que data ini
				if (a.getDataFim().before(a.getDataInicio())) {
					System.out.println("Solução:" + s.getNumero());
					System.out.println("  Atividade:" + a.getNome());
					System.out
							.println("     Data final é anterior à data inicial");
					numErros += 1;
				}
				// Testa data ini sempre um dia depois do fim da maior pré
				// atividade
				if (a.getPreAtividades().size() > 0) {

					long lng = a.getDataInicio().getTime()
							- this.getMaiorDataFinal(a.getPreAtividades())
									.getTime();
					if (lng != DIA) {

						SimpleDateFormat formato = new SimpleDateFormat(
								"dd/MM/yyyy");

						System.out.println("Solução:" + s.getNumero());
						System.out.println("  Atividade:" + a.getNome());
						System.out
								.println("     Data inicial menor que a maior data final de pré atividade."
										+ a.getDataInicioString()
										+ ":"
										+ formato.format(this
												.getMaiorDataFinal(a
														.getPreAtividades())));
						numErros += 1;
					}
				}
			}
		}
		if (numErros == 0) {
			System.out.println("--------------");
			System.out.println("As datas estão todas consistentes");
		} else {
			System.out.println("--------------");
			System.out.println(numErros + " erros encontrados.");
		}
	}

}