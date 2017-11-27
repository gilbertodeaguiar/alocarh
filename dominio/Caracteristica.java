/**
 * Title:        Protótipo Tese - Caracteristica
 * Description:  Implementação do protótipo da tese. Classe Caracteristica
 * Copyright:    Copyright (c) 2004
 * Company:      COPPE Sistemas
 * @author Ahilton S. Barreto
 * @version 1.0
 */

/**
 * @author Ahilton
 * 
 * Esta classe representa uma característica (conhecimento, experiência,
 * habilidade, formação, etc..) possuída por um profissional ou exigida por uma
 * atividade. Nada mais é que a "instanciação" de uma DefinicaoCaracteristica,
 * ou seja, uma DefinicaoCaracteristica com valor.
 */
public class Caracteristica {

	/**
	 * Definição Característica que descrve a característica
	 */
	private DefinicaoCaracteristica caracteristica;

	/**
	 * Valor da característica
	 */
	private ValorEscala valor;

	/**
	 * Construtor da classe (com parâmetros)
	 * 
	 * @param parDCaracteristica
	 *            Definição de característica que descreve a característica
	 * @param parValor
	 *            Valor da característica
	 */
	public Caracteristica(DefinicaoCaracteristica parDCaracteristica,
			ValorEscala parValor) {
		this.caracteristica = parDCaracteristica;
		this.valor = parValor;
	}

	/**
	 * Construtor Padrao da Classe
	 */
	public Caracteristica() {
		this.caracteristica = null;
		this.valor = null;
	}

	/**
	 * Retorna a Descrição da Característica
	 * 
	 * @return Descrição da Característica
	 */
	public DefinicaoCaracteristica getDefinicaoCaracteristica() {
		return this.caracteristica;
	}

	/**
	 * Retorna o valor da característica
	 * 
	 * @return Valor da característica
	 */
	public ValorEscala getValor() {
		return this.valor;
	}
	
	/**
	 * Atribui o valor
	 * @param parValor
	 */
	public void setValor(ValorEscala parValor) {
		this.valor = parValor;
	}
}
