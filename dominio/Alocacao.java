
/**
 * Title:        Protótipo Tese - Alocacao
 * Description:  Implementação do protótipo da tese. Classe Alocacao
 * Copyright:    Copyright (c) 2004
 * Company:      COPPE Sistemas
 * @author Ahilton S. Barreto
 * @version 1.0
 */

/**
 * @author Ahilton
 * 
 * Esta classe implementa uma alocação, que representa o conjunto de um
 * profissional e uma atividade. Significa que a atividade será executada pelo
 * profissional. Os objetos desta classe são usados na saída do algoritmo de
 * alocação.
 */
public class Alocacao {
	
	/**
	 * Profissional da alocação
	 */
	private Profissional profissional;
	/**
	 * Atividade da alocação
	 */
	private Atividade atividade;
	
	
	/**
	 * Construtor padrão da classe
	 */
	public Alocacao(){
		this.profissional = null;
		this.atividade = null;
	}
	
	/**
	 * Construtor da classe (com parâmetros)
	 * @param parProfissional Profissional da alocação
	 * @param parAtividade Atividade da alocação
	 */
	public Alocacao (Profissional parProfissional, Atividade parAtividade){
		this.profissional = parProfissional;
		this.atividade  = parAtividade.clona();	
	}
	

	/**
	 * @return Returns the atividade.
	 */
	public Atividade getAtividade() {
		return this.atividade;
	}
	
	/**
	 * @param atividade The atividade to set.
	 */
	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
	
	/**
	 * @return Returns the profissional.
	 */
	public Profissional getProfissional() {
		return this.profissional;
	}
	
	/**
	 * @param profissional The profissional to set.
	 */
	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}
}
