/**
 * Title:        Prot�tipo Tese - Caracteristica
 * Description:  Implementa��o do prot�tipo da tese. Classe Caracteristica
 * Copyright:    Copyright (c) 2004
 * Company:      COPPE Sistemas
 * @author Ahilton S. Barreto
 * @version 1.0
 */

/**
 * @author Ahilton
 * 
 * Esta classe representa uma caracter�stica (conhecimento, experi�ncia,
 * habilidade, forma��o, etc..) possu�da por um profissional ou exigida por uma
 * atividade. Nada mais � que a "instancia��o" de uma DefinicaoCaracteristica,
 * ou seja, uma DefinicaoCaracteristica com valor.
 */
public class Caracteristica {

	/**
	 * Defini��o Caracter�stica que descrve a caracter�stica
	 */
	private DefinicaoCaracteristica caracteristica;

	/**
	 * Valor da caracter�stica
	 */
	private ValorEscala valor;

	/**
	 * Construtor da classe (com par�metros)
	 * 
	 * @param parDCaracteristica
	 *            Defini��o de caracter�stica que descreve a caracter�stica
	 * @param parValor
	 *            Valor da caracter�stica
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
	 * Retorna a Descri��o da Caracter�stica
	 * 
	 * @return Descri��o da Caracter�stica
	 */
	public DefinicaoCaracteristica getDefinicaoCaracteristica() {
		return this.caracteristica;
	}

	/**
	 * Retorna o valor da caracter�stica
	 * 
	 * @return Valor da caracter�stica
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
