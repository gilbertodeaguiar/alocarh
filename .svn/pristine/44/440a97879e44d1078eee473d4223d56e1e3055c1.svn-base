/**
 * Title:        Protótipo Tese - Atividade
 * Description:  Implementação do protótipo da tese. Classe Atividade
 * Copyright:    Copyright (c) 2004
 * Company:      COPPE Sistemas
 * @author Ahilton S. Barreto
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Stack;
import java.text.SimpleDateFormat;

/**
 * @author Ahilton
 * 
 * Esta classe implementa uma Atividade que precisa ser realizada em um
 * determinado período e por um profissional que tenha os pré-requisitos
 * necessários.
 */
public class Atividade {

	/**
	 * Nome da atividade
	 */
	public String nome;

	/**
	 * Data de início da atividade
	 */
	public Date dataInicio;

	/**
	 * Data de término da atividade
	 */
	public Date dataFim;

	/**
	 * Número de horas que precisam ser dedicadas à atividade por dia
	 */
	public int numeroHorasDia;

	/**
	 * Pessoa responsavel pela atividade
	 */
	public String pessoa;
	
	/**
	 * Lista de características exigidas que o profissional possua para
	 * desempenhar a atividade
	 */
	public ArrayList caracteristicas;
	
	/**
	 * Lista de pré atividades da atividade
	 */
	public ArrayList preAtividades;
	
	/**
	 * Lista de pós atividades da atividade
	 */
	public ArrayList posAtividades;
	
	/**
	 * Lista auxiliar usada na atualização de rede tarefas
	 */
	private Stack pilhaAuxiliarInicial;
	
	/**
	 * Lista auxiliar usada na atualização de rede tarefas
	 */
	private Stack pilhaAuxiliarFinal;

	/**
	 * Construtor default
	 */
	public Atividade() {
		this.caracteristicas = new ArrayList();
		this.preAtividades = new ArrayList();
		this.posAtividades = new ArrayList();
	}

	/**
	 * Construtor da classe com parâmetros
	 * 
	 * @param parNome
	 *            Nome da atividade
	 * @param parDtInicio
	 *            Data de início da atividade
	 * @param parDtFim
	 *            Data de término da atividade
	 * @param parHorasDia
	 *            Número de horas que devem ser dedicadas à atividade por dia
	 */
	public Atividade(String parNome, String parDtInicio, String parDtFim,
			int parHorasDia, String pessoa) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		this.pessoa = pessoa;
		this.nome = parNome;
		this.numeroHorasDia = parHorasDia;
		this.caracteristicas = new ArrayList();
		this.preAtividades = new ArrayList();
		this.posAtividades = new ArrayList();
		this.pilhaAuxiliarInicial = new Stack();
		this.pilhaAuxiliarFinal = new Stack();

		try {
			this.dataInicio = formato.parse(parDtInicio);
			this.dataFim = formato.parse(parDtFim);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Retorna o nome da atividade
	 * 
	 * @return Nome da atividade
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Retorna a data de inicio da atividade com string
	 * 
	 * @return data de início em formato string
	 */
	public String getDataInicioString() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return formato.format(this.dataInicio);
	}

	/**
	 * Atribui a data de início da atividade, passando uma string
	 * @param parDtInicio
	 */
	public void setDataInicioString(String parDtInicio) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.dataInicio = formato.parse(parDtInicio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retorna a data de início da atividade
	 * 
	 * @return Data de início da atividade
	 */
	public Date getDataInicio() {
		return this.dataInicio;
	}

	/**
	 * Retorna a data de término da atividade com string
	 * 
	 * @return Data de término da atividade em formato string
	 */
	public String getDataFimString() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return formato.format(this.dataFim);
	}

	/**
	 * Atribui a data de fim da atividade, passando uma string
	 * @param parDtFim
	 */
	public void setDataFimString(String parDtFim) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.dataFim = formato.parse(parDtFim);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retorna a data de término da atividade
	 * 
	 * @return Data de término da atividade
	 */
	public Date getDataFim() {
		return this.dataFim;
	}

	/**
	 * Retorna o número de horas que devem ser dedicadas por dia à atividade
	 * 
	 * @return número de horas que devem ser dedicadas por dia à atividade
	 */
	public int getNumeroHorasDia() {
		return this.numeroHorasDia;
	}


	public String getPessoa() {
		return this.pessoa;
	}
	
	/**
	 * Seta o número de horas que deve ser dedicado por dia à atividade
	 * @param parNumeroHorasDia Número de Horas
	 */
	public void setNumeroHorasDia(int parNumeroHorasDia) {
		this.numeroHorasDia = parNumeroHorasDia;
	}

	/**
	 * Retorna aLista das características exigidas pela atividade
	 * @return Lista das características exigidas pela atividade
	 */
	public ArrayList getCaracteristicas() {
		return this.caracteristicas;
	}

	/**
	 * Insere caracteristica em uma atividade
	 * 
	 * @param parDCaracteristica
	 *            Definição da característica que está sendo inserida
	 * @param parValor
	 *            Valor da característica sendo inserida
	 */
	public void inserirCaracteristica(
			DefinicaoCaracteristica parDCaracteristica, ValorEscala parValor) {
		this.caracteristicas.add(new Caracteristica(parDCaracteristica,
				parValor));
	}

	/**
	 * Altera o valor de uma característica exigida pela atividade
	 * @param parNomeCaracteristica Nome da Característica a ser alterada
	 * @param parValor Novo valor
	 */
	public void alterarCaracteristica(String parNomeCaracteristica,
			ValorEscala parValor) {
		Iterator iter1 = this.caracteristicas.iterator();
		while (iter1.hasNext()) {
			Caracteristica c = (Caracteristica) iter1.next();
			if (c.getDefinicaoCaracteristica().getNome().compareToIgnoreCase(
					parNomeCaracteristica) == 0) {
				c.setValor(parValor);
			}
		}
	}

	/**
	 * Exclui uma característica exigida pela atividade
	 * @param parNomeCaracteristica Nome da Característica a ser excluída
	 */
	public void excluirCaracteristica(String parNomeCaracteristica) {
		Iterator iter1 = this.caracteristicas.iterator();
		ArrayList listaExclusao = new ArrayList();
		while (iter1.hasNext()) {
			Caracteristica c = (Caracteristica) iter1.next();
			if (c.getDefinicaoCaracteristica().getNome().compareToIgnoreCase(
					parNomeCaracteristica) == 0) {
				listaExclusao.add(c);
			}
		}
		iter1 = listaExclusao.iterator();
		while (iter1.hasNext()) {
			Caracteristica c = (Caracteristica) iter1.next();
			this.getCaracteristicas().remove(c);
		}
	}

	/**
	 * Retorna a i-ésima caracteristica do vetor de caracteristicas
	 * 
	 * @param i
	 * @return i-ésima característica
	 */
	public Caracteristica getCaracteristica(int i) {
		return ((Caracteristica) this.caracteristicas.get(i));
	}

	/**
	 * Retorna o número de caracteristicas requeridas pela atividade
	 * 
	 * @return número de caracteristicas requeridas pela atividade
	 */
	public int getQuantidadeCaracteristicas() {
		return this.caracteristicas.size();
	}

	/**
	 * Retorna o número de dias da atividade (incluindo os extremos)
	 * 
	 * @return número de dias da atividade (incluindo os extremos)
	 */
	public int getNumeroDias() {
		/* O número grande é pra transformar de milisegundo para dia */
		/* O +1 é pra incluir os dois dias extremos - de início e fim */
		return (int) ((this.dataFim.getTime() - this.dataInicio.getTime()) / 86400000) + 1;
	}

	/**
	 * Número de horas que serão dedicadas à atividade no total
	 * 
	 * @return Número de horas que serão dedicadas à atividade no total
	 */
	public int getNumeroHoras() {
		return this.numeroHorasDia * this.getNumeroDias();
	}

	/**
	 * Para normalização do peso de cada característica no cálculo de menor ou
	 * maior subalocação
	 * 
	 * @param valMaxCaracteristicas
	 *            Valor máximo da intensidade da característica
	 * @return Valor relativo normalizado em relação às outras caracetrísticas
	 */
	public double calculaPontosCaracteristicaRelativo(int valMaxCaracteristicas) {
		int valor = 0;
		Caracteristica c;

		for (int i = 0; i != this.caracteristicas.size(); i++) {
			c = ((Caracteristica) this.caracteristicas.get(i));
			valor += (c.getValor().getValorNumerico() * valMaxCaracteristicas)
					/ c.getDefinicaoCaracteristica()
							.getTipoDefinicaoCaracteristica().getEscala()
							.getValorMaximo();
		}
		return valor;
	}

	/**
	 * Adiciona uma pré atividade à atividade
	 * @param pre Pré Atividade
	 */
	public void adicionaPreAtividade(Atividade pre){
		this.preAtividades.add(pre);
	}
	
	/**
	 * Adiciona uma pós atividade à atividade
	 * @param pos Pós Atividade
	 */
	public void adicionaPosAtividade(Atividade pos){
		this.posAtividades.add(pos);
	}
	
	/**
	 * Exclui uma pré atividade da atividade
	 * @param parPreAtividade Pré Atividade a ser excluída
	 */
	public void excluirPreAtividade(Atividade parPreAtividade) {
		this.preAtividades.remove(parPreAtividade);
	}

	/**
	 * Exclui uma pós atividade da atividade
	 * @param parPosAtividade Pos Atividade a ser excluída
	 */
	public void excluirPosAtividade(Atividade parPosAtividade) {
		this.posAtividades.remove(parPosAtividade);
	}
	
	/**
	 * @return Returns the posAtividades.
	 */
	public ArrayList getPosAtividades() {
		return this.posAtividades;
	}
	/**
	 * @param posAtividades The posAtividades to set.
	 */
	public void setPosAtividades(ArrayList posAtividades) {
		this.posAtividades = posAtividades;
	}
	/**
	 * @return Returns the preAtividades.
	 */
	public ArrayList getPreAtividades() {
		return this.preAtividades;
	}
	/**
	 * @param preAtividades The preAtividades to set.
	 */
	public void setPreAtividades(ArrayList preAtividades) {
		this.preAtividades = preAtividades;
	}
	/**
	 * @param dataFim The dataFim to set.
	 */
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	/**
	 * @param dataInicio The dataInicio to set.
	 */
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public void setPessoa(String nome) {
		
		this.pessoa = nome;
	}
	
	
	/**
	 * @param nome The nome to set.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @param caracteristicas The caracteristicas to set.
	 */
	public void setCaracteristicas(ArrayList caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	/**
	 * @return Returns the pilhaAuxiliarFinal.
	 */
	public Stack getPilhaAuxiliarFinal() {
		return this.pilhaAuxiliarFinal;
	}
	/**
	 * @param pilhaAuxiliarFinal The pilhaAuxiliarFinal to set.
	 */
	public void setPilhaAuxiliarFinal(Stack pilhaAuxiliarFinal) {
		this.pilhaAuxiliarFinal = pilhaAuxiliarFinal;
	}
	/**
	 * @return Returns the pilhaAuxiliarInicial.
	 */
	public Stack getPilhaAuxiliarInicial() {
		return this.pilhaAuxiliarInicial;
	}
	/**
	 * @param pilhaAuxiliarInicial The pilhaAuxiliarInicial to set.
	 */
	public void setPilhaAuxiliarInicial(Stack pilhaAuxiliarInicial) {
		this.pilhaAuxiliarInicial = pilhaAuxiliarInicial;
	}
	
	/**
	 * Clona um objeto atividade
	 * @return objeto atividade igual ao que chamou
	 */
	public Atividade clona(){
		Atividade a = new Atividade();
		
		a.setNome(this.getNome());
		a.setNumeroHorasDia(this.getNumeroHorasDia());
		a.setDataInicioString(this.getDataInicioString());
		a.setDataFimString(this.getDataFimString());
		a.setPessoa(this.getPessoa());
		a.setCaracteristicas(this.getCaracteristicas());

		/*
		for (int i=0; i!=this.preAtividades.size();i++){
			Atividade pa = (Atividade)this.preAtividades.get(i);
			a.getPreAtividades().add(pa.clona());
		}
		*/
		return a;
	}
	
	public String toString(){
		return this.nome;
	}
	
}