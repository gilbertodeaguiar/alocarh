import java.util.ArrayList;
import java.util.List;

/**
 * Title:        Protótipo Tese - Solucao
 * Description:  Implementação do protótipo da tese. Classe Solucao
 * Copyright:    Copyright (c) 2004
 * Company:      COPPE Sistemas
 * @author Ahilton S. Barreto
 * @version 1.0
 */

/**
 * @author Ahilton
 * 
 * Esta classe implementa uma solução, que representa uma possível solução para
 * o problema da alocação
 */
public class Solucao {

	/**
	 * Número da solução
	 */
	private int numero;

	/**
	 * Custo monetário associado à solução
	 */
	private double custoMonetario;

	/**
	 * Tamanho da equipe da solução
	 */
	private int tamanhoEquipe;

	private int tempoProjeto;
	
	/**
	 * Índice de subalocação da solução
	 */
	private double indSubalocacao;
	
	private List alocacoes;

	/**
	 * Construtor padrão da classe
	 */
	public Solucao() {

	}

	/**
	 * Construtor da classe com parâmetros
	 * 
	 * @param parNumero
	 *            Número da solução
	 * @param parCustoMonetario
	 *            Custo monetário associado à solução
	 * @param parTamanhoEquipe
	 *            Tamanho da equipe da solução
	 * @param parIndSubalocacao
	 *            Índice de subalocação da solução
	 */
	public Solucao(int parNumero, double parCustoMonetario,
			int parTamanhoEquipe, double parIndSubalocacao, int parTempoProjeto) {
		this.numero = parNumero;
		this.custoMonetario = parCustoMonetario;
		this.tamanhoEquipe = parTamanhoEquipe;
		this.tempoProjeto = parTempoProjeto;
		this.indSubalocacao = parIndSubalocacao;
		this.alocacoes = new ArrayList();
	}

	/**
	 * @return Returns the custoMonetario.
	 */
	public double getCustoMonetario() {
		return this.custoMonetario;
	}
	
	/**
	 * @param custoMonetario The custoMonetario to set.
	 */
	public void setCustoMonetario(double custoMonetario) {
		this.custoMonetario = custoMonetario;
	}
	
	/**
	 * @return Returns the indSubalocacao.
	 */
	public double getIndSubalocacao() {
		return this.indSubalocacao;
	}
	
	/**
	 * @param indSubalocacao The indSubalocacao to set.
	 */
	public void setIndSubalocacao(double indSubalocacao) {
		this.indSubalocacao = indSubalocacao;
	}
	
	/**
	 * @return Returns the numero.
	 */
	public int getNumero() {
		return this.numero;
	}
	
	/**
	 * @param numero The numero to set.
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	/**
	 * @return Returns the tamanhoEquipe.
	 */
	public int getTamanhoEquipe() {
		return this.tamanhoEquipe;
	}
	
	/**
	 * @param tamanhoEquipe The tamanhoEquipe to set.
	 */
	public void setTamanhoEquipe(int tamanhoEquipe) {
		this.tamanhoEquipe = tamanhoEquipe;
	}
	
	/**
	 * @return Returns the alocacoes.
	 */
	public List getAlocacoes() {
		return this.alocacoes;
	}
	
	/**
	 * @param alocacoes The alocacoes to set.
	 */
	public void setAlocacoes(List alocacoes) {
		this.alocacoes = alocacoes;
	}
	
	/**
	 * Adiciona uma alocação na solução
	 * @param parAlocacao Alocação sendo inserida
	 */
	public void adicionaAlocacao(Alocacao parAlocacao){
		this.alocacoes.add(parAlocacao);
	}
	/**
	 * @return Returns the tempoProjeto.
	 */
	public int getTempoProjeto() {
		return this.tempoProjeto;
	}
	/**
	 * @param tempoProjeto The tempoProjeto to set.
	 */
	public void setTempoProjeto(int tempoProjeto) {
		this.tempoProjeto = tempoProjeto;
	}
	
	/**
	 * Retorna todas as atividades de todas as alocações da solução
	 * @return
	 */
	public ArrayList getTodasAtividades(){
		ArrayList retorno = new ArrayList();
		for (int i=0; i!=this.getAlocacoes().size();i++){
			Atividade a = ((Alocacao)this.getAlocacoes().get(i)).getAtividade(); 
			retorno.add(a);
		}
		return retorno;
	}
}
