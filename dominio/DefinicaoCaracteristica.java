/**
 * Title:        Protótipo Tese - DefinicaoCaracteristica
 * Description:  Implementação do protótipo da tese. Classe DefinicaoCaracteristica
 * Copyright:    Copyright (c) 2004
 * Company:      COPPE Sistemas
 * @author Ahilton S. Barreto
 * @version 1.0
 */

/**
 * @author Ahilton
 * 
 * Esta classe representa uma definição de característica 
 */
public class DefinicaoCaracteristica {

	/**
	 * Tipo da característica
	 */
	private TipoDefinicaoCaracteristica tipoDefinicaoCaracteristica;

	/**
	 * Nome da característica
	 */
	private String nome;

	/**
	 * Construtor da classe (com parâmetros)
	 * 
	 * @param nome
	 *            Nome da definição característica
	 * @param valorMaximo
	 *            Valor máximo que a intensidade de uma característica desse
	 *            tipo pode assumir
	 */
	public DefinicaoCaracteristica(String nome,
			TipoDefinicaoCaracteristica parTipoDefinicaoCaracteristica) {
		this.nome = nome;
		this.tipoDefinicaoCaracteristica = parTipoDefinicaoCaracteristica;
	}

	/**
	 * Construtor padrão da classe
	 */
	public DefinicaoCaracteristica() {
		this.nome = "";
	}

	/**
	 * Retorna o nome da característica sendo descrita
	 * 
	 * @return Nome da característica
	 */
	public String getNome() {
		return this.nome;
	}

	public TipoDefinicaoCaracteristica getTipoDefinicaoCaracteristica() {
		return this.tipoDefinicaoCaracteristica;
	}

	public void setTipoDefinicaoCaracteristica(
			TipoDefinicaoCaracteristica parTipoDefinicaoCaracteristica) {
		this.tipoDefinicaoCaracteristica = parTipoDefinicaoCaracteristica;
	}
	
	public String toString(){
		return this.nome;
	}
}
