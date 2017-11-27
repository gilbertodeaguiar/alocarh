
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

/**
 * Title:        Protótipo Tese - Escala
 * Description:  Implementação do protótipo da tese. Classe Escala
 * Copyright:    Copyright (c) 2004
 * Company:      COPPE Sistemas
 * 15/03/2005
 * @author Ahilton S. Barreto
 * @version 1.0
 */

/**
 * @author Ahilton
 * 
 * Esta classe representa uma das escalas de valores que podem ser associadas a
 * cada tipo de definição de característica.
 */
public class Escala {
	/**
	 * Nome da escala
	 */
	private String nome;

	/**
	 * Valor máximo que a escala pode assumir Deve sempre haver este número de
	 * valores de escala.
	 */
	private int valorMaximo;

	private Hashtable valoresEscala;

	
	public Escala (String parNome, int parValor){
		this.nome = parNome;
		this.valorMaximo = parValor;
		this.valoresEscala = new Hashtable();
	}
	
	/**
	 * @return Returns the nome.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * @param nome
	 *            The nome to set.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return Returns the valorMaximo.
	 */
	public int getValorMaximo() {
		return this.valorMaximo;
	}

	/**
	 * @param valorMaximo
	 *            The valorMaximo to set.
	 */
	public void setValorMaximo(int valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	/**
	 * @return Returns the valoresEscala.
	 */
	public Hashtable getValoresEscala() {
		return this.valoresEscala;
	}

	/**
	 * @param valoresEscala
	 *            The valoresEscala to set.
	 */
	public void setValoresEscala(Hashtable valoresEscala) {
		this.valoresEscala = valoresEscala;
	}

	public void inserirValorEscala(String parNome, int parValor) {
		this.valoresEscala.put(new Integer(parValor), new ValorEscala(parNome,
				parValor));
	}

	public void alterarValorEscala(String parNome,
			int parValor) {
		ValorEscala ve = (ValorEscala) this.valoresEscala
				.get(new Integer(parValor));
		ve.setNome(parNome);
	}

	public void excluirValorEscala(int parValor) {
		this.valoresEscala.remove(new Integer(parValor));
	}
	
	public Collection getValorEscalaOrdenado(){
		List c = new ArrayList(); 
		Iterator i = this.valoresEscala.values().iterator();
		while (i.hasNext()){
			c.add(i.next());
		}
		class ValorEscalaComparator implements Comparator {
            public final int compare(Object a, Object b) {
                if (((ValorEscala) a).getValorNumerico() < ((ValorEscala) b).getValorNumerico())
                	return -1;
                if (((ValorEscala) a).getValorNumerico() == ((ValorEscala) b).getValorNumerico())
                	return 0;
                if (((ValorEscala) a).getValorNumerico() > ((ValorEscala) b).getValorNumerico())
                	return 1;
                return 0;
            } 
        } 
        Collections.sort(c, new ValorEscalaComparator());
        return c;
	}
	
	
	public String toString(){
		return this.nome;
	}
}
