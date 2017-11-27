/**
 * Title:        Protótipo Tese - ValorEscala
 * Description:  Implementação do protótipo da tese. Classe ValorEscala
 * Copyright:    Copyright (c) 2004
 * Company:      COPPE Sistemas
 * 15/03/2005
 * @author Ahilton S. Barreto
 * @version 1.0
 */

/**
 * @author Ahilton
 * 
 * Esta classe representa um valor de uma escala. Estes valores são atribuídos
 * às características.
 */
public class ValorEscala {
	private String nome;
	private int valorNumerico;
	
	
	public ValorEscala(String parNome, int parValorNumerico){
		this.nome = parNome;
		this.valorNumerico = parValorNumerico;
	}
	
	/**
	 * @return Returns the nome.
	 */
	public String getNome() {
		return this.nome;
	}
	/**
	 * @param nome The nome to set.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return Returns the valorNumerico.
	 */
	public int getValorNumerico() {
		return this.valorNumerico;
	}
	/**
	 * @param valorNumerico The valorNumerico to set.
	 */
	public void setValorNumerico(int valorNumerico) {
		this.valorNumerico = valorNumerico;
	}
	
	public String toString(){
		return this.nome;
	}
}
