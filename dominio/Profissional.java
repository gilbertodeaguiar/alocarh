/**
 * Title:        Prot�tipo Tese - Profissional
 * Description:  Implementa��o do prot�tipo da tese. Classe Profissional
 * Copyright:    Copyright (c) 2004
 * Company:      COPPE Sistemas
 * @author Ahilton S. Barreto
 * @version 1.0
 */

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * @author Ahilton
 * 
 * Esta classe representa um dos profissionais da organiza��o, que pode ser
 * alocado �s atividades dos projetos.
 */
public class Profissional {

	/**
	 * Nome do profissional
	 */
	private String nome;

	/**
	 * Valor da hora de trabalho do profissional
	 */
	private double valorHora;

	/**
	 * Caracteristicas Possuidas pelo profissional
	 */
	private Hashtable caracteristicas;

	/**
	 * Per�odos em que o profissional est� indispon�vel
	 */
	private ArrayList periodosIndisponibilidade;

	/**
	 * N�mero de horas de trabalho di�rias do profissional
	 */
	private int qtdHorasDia;

	private double indiceProdutividadeGlobal;

	private Hashtable produtividadeAtividade;

	/**
	 * Construtor da classe
	 * 
	 * @param parNome
	 *            Nome do profissional
	 * @param parValorHora
	 *            Valor da hora de trabalho do profissional
	 * @param parQtdHorasDia
	 *            N�mero de horas trabalhadas por dia pelo profissional
	 */
	public Profissional(String parNome, double parValorHora, int parQtdHorasDia) {
		this.nome = parNome;
		this.valorHora = parValorHora;
		this.caracteristicas = new Hashtable();
		this.periodosIndisponibilidade = new ArrayList();
		this.qtdHorasDia = parQtdHorasDia;
		this.indiceProdutividadeGlobal = 1.0;
		this.produtividadeAtividade = new Hashtable();
	}

	/**
	 * Retorna o nome do profissional
	 * 
	 * @return nome do profissional
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Retorna o valor da hora de trabalho do profissional
	 * 
	 * @return valor da hora do profissional
	 */
	public double getValorHora() {
		return this.valorHora;
	}

	public void setValorHora(double parValorHora) {
		this.valorHora = parValorHora;
	}

	/**
	 * Retorna o n�mero de horas de trabalho di�rio do profissional
	 * 
	 * @return n�mero de horas de trabalho di�rio do profissional
	 */
	public int getQtdHorasDia() {
		return this.qtdHorasDia;
	}

	public void setQtdHorasDia(int parQtdHorasDia) {
		this.qtdHorasDia = parQtdHorasDia;
	}

	public Hashtable getCaracteristicas() {
		return this.caracteristicas;
	}

	public ArrayList getPeriodosIndisponibilidade() {
		return this.periodosIndisponibilidade;
	}

	/**
	 * @return Returns the indiceProdutividadeGlobal.
	 */
	public double getIndiceProdutividadeGlobal() {
		return this.indiceProdutividadeGlobal;
	}

	/**
	 * @param indiceProdutividadeGlobal
	 *            The indiceProdutividadeGlobal to set.
	 */
	public void setIndiceProdutividadeGlobal(double indiceProdutividadeGlobal) {
		this.indiceProdutividadeGlobal = indiceProdutividadeGlobal;
	}

	/**
	 * @return Returns the produtividadeAtividade.
	 */
	public Hashtable getProdutividadeAtividade() {
		return this.produtividadeAtividade;
	}

	/**
	 * @param produtividadeAtividade
	 *            The produtividadeAtividade to set.
	 */
	public void setProdutividadeAtividade(Hashtable produtividadeAtividade) {
		this.produtividadeAtividade = produtividadeAtividade;
	}

	/**
	 * Insere caracteristica em um profissional
	 * 
	 * @param parDCaracteristica
	 *            Defini��o Caracter�stica da caracter�stica sendo inserida
	 * @param parValor
	 *            Valor (Intensidade) da caracter�stica sendo inserida
	 */
	public void inserirCaracteristica(
			DefinicaoCaracteristica parDCaracteristica, ValorEscala parValor) {
		this.caracteristicas.put(parDCaracteristica.getNome(),
				new Caracteristica(parDCaracteristica, parValor));
	}

	public void alterarCaracteristica(String parNomeCaracteristica,
			ValorEscala parValor) {
		Caracteristica c = (Caracteristica) this.caracteristicas
				.get(parNomeCaracteristica);
		c.setValor(parValor);
	}

	public void excluirCaracteristica(String parNomeCaracteristica) {
		this.caracteristicas.remove(parNomeCaracteristica);
	}

	/**
	 * Insere per�odo de indisponibilidade em um profissional (Strings)
	 * 
	 * @param parDtInicio
	 *            Data In�cio do per�odo de indisponibilidade
	 * @param parDtFim
	 *            Data fim do per�odo de indisponibilidade
	 * @param parHoras
	 *            N�mero de horas di�rias em que o profissional estar�
	 *            indispon�vel
	 */
	public void inserirPeriodoIndisponibilidade(String parDtInicio,
			String parDtFim, int parHoras) {
		this.periodosIndisponibilidade.add(new PeriodoIndisponivel(parDtInicio,
				parDtFim, parHoras));
	}

	/**
	 * Insere per�odo de indisponibilidade em um profissional (Datas)
	 * 
	 * @param parDtInicio
	 *            Data In�cio do per�odo de indisponibilidade
	 * @param parDtFim
	 *            Data fim do per�odo de indisponibilidade
	 * @param parHoras
	 *            N�mero de horas di�rias em que o profissional estar�
	 *            indispon�vel
	 */
	public void inserirPeriodoIndisponibilidade(Date parDtInicio,
			Date parDtFim, int parHoras) {
		this.periodosIndisponibilidade.add(new PeriodoIndisponivel(parDtInicio,
				parDtFim, parHoras));
	}

	/**
	 * Exclui um per�odo de indisponibilidade
	 * 
	 * @param parDtInicio
	 *            Data In�cio do per�odo de indisponibilidade
	 * @param parDtFim
	 *            Data fim do per�odo de indisponibilidade
	 * @param parQtdHorasDia
	 *            N�mero de horas di�rias em que o profissional est�
	 *            indispon�vel
	 */
	public void excluirPeriodoIndisponibilidade(Date parDtInicio,
			Date parDtFim, int parQtdHorasDia) {
		for (int i = 0; i < this.periodosIndisponibilidade.size(); i++) {

			if ((((PeriodoIndisponivel) this.periodosIndisponibilidade.get(i))
					.getDataInicio().equals(parDtInicio))
					&& (((PeriodoIndisponivel) this.periodosIndisponibilidade
							.get(i)).getDataFim().equals(parDtFim))
					&& (((PeriodoIndisponivel) this.periodosIndisponibilidade
							.get(i)).getQtdHoras() == parQtdHorasDia)) {
				this.periodosIndisponibilidade.remove(i);
				break;
			}
		}
	}

	/**
	 * Testa se profissional possui uma dada caracter�stica em uma certa
	 * intensidade
	 * 
	 * @param parNome
	 *            Nome da Caracter�stica sendo testada
	 * @param parValor
	 *            Valor da caracter�stica sendo testada
	 * @return
	 */
	public boolean checaCaracteristica(String parNome, int parValor) {
		Caracteristica varCaracteristica;
		varCaracteristica = (Caracteristica) this.caracteristicas.get(parNome);
		if (varCaracteristica == null) {
			return false;
		} else if (varCaracteristica.getValor().getValorNumerico() >= parValor)
			return true;
		else
			return false;
	}

	/**
	 * Testa se um profissional est� indispon�vel em um dado per�odo
	 * 
	 * @param parDtInicio
	 *            Data de in�cio do per�odo que se deseja testar
	 * @param parDtFim
	 *            Data de fim do per�odo que se deseja testar
	 * @param parQtdHoras
	 *            Quantidade de horas di�ria do per�odo que se deseja testar
	 * @return True se o profissinal est� dispon�vel no per�odo, False se n�o
	 */
	public boolean checaDisponibilidade(Date parDtInicio, Date parDtFim,
			int parQtdHoras) {
		for (int i = 0; i != this.periodosIndisponibilidade.size(); i++) {
			// testa se falha na data
			if (!(((PeriodoIndisponivel) this.periodosIndisponibilidade.get(i))
					.checaDisponibilidadeData(parDtInicio, parDtFim))) {
				// testa por horas
				/*
				 * Se o n�mero de horas que o profissional trabalha � maior ou
				 * igual ao n�mero de horas em que ele est� indispon�vel no
				 * per�odo somado ao n�mero de horas da atividade a ser feita, o
				 * profissional n�o est� impedido de realizar a atividade
				 */
				if (this.qtdHorasDia < ((PeriodoIndisponivel) this.periodosIndisponibilidade
						.get(i)).getQtdHoras()
						+ parQtdHoras) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Retorna o n�mero de dias que deve ser diminu�do no projeto, devido �
	 * aloca��o de p a a
	 * 
	 * @param p
	 * @param a
	 * @return
	 */
	public int checaProdutividadeProfissional(Profissional p, Atividade a,
			int modeloProdutividade) {
		if (modeloProdutividade == 0) {
			return 0;
		}

		int numeroDiasInicialAtividade = 0;
		int resultado = 0;
		numeroDiasInicialAtividade = a.getNumeroDias();

		if (modeloProdutividade == 1) {
			int numeroCaracteristicasAtividade = a.getCaracteristicas().size();
			int pontosExtrasProfissional = 0;
			double modificador = 0;
			double experiencia = 0.5; // Profissional m�dio (varia de 0.5 a 1)

			for (int i = 0; i != numeroCaracteristicasAtividade; i++) {
				Caracteristica ca = (Caracteristica) a.getCaracteristicas()
						.get(i);
				Caracteristica cp = (Caracteristica) p.getCaracteristicas()
						.get(ca.getDefinicaoCaracteristica().getNome());
				if (ca.getValor().getValorNumerico() > 0
						&& cp.getValor().getValorNumerico() > 0) {
					if (cp.getValor().getValorNumerico() > ca.getValor()
							.getValorNumerico()) {
						pontosExtrasProfissional += 1;
					}
				}
			}

			experiencia = experiencia
					+ (experiencia * pontosExtrasProfissional / numeroCaracteristicasAtividade);

			modificador = (1 - 0.337) + 2 * 0.337 * experiencia;

			resultado = (int) (numeroDiasInicialAtividade / modificador);

			if ((numeroDiasInicialAtividade % modificador > 0)
					&& (resultado >= 1))
				resultado += 1;

			return numeroDiasInicialAtividade - resultado;
		}

		if (modeloProdutividade == 2) {
			numeroDiasInicialAtividade = a.getNumeroDias();
			resultado = (int) (numeroDiasInicialAtividade / p
					.getIndiceProdutividadeGlobal());
			if ((numeroDiasInicialAtividade % p.getIndiceProdutividadeGlobal() > 0)
					&& (resultado >= 1))
				resultado += 1;
			return numeroDiasInicialAtividade - resultado;
		}

		if (modeloProdutividade == 3) {
			numeroDiasInicialAtividade = a.getNumeroDias();
			Double valor = new Double(1.0);
			if (p.getProdutividadeAtividade().get(a.getNome()) != null)
				valor = (Double) p.getProdutividadeAtividade().get(a.getNome());
			else {
				p.getProdutividadeAtividade().put(a.nome, valor);
			}

			resultado = (int) (numeroDiasInicialAtividade / ((Double) p
					.getProdutividadeAtividade().get(a.getNome()))
					.doubleValue());

			if ((numeroDiasInicialAtividade
					% ((Double) p.getProdutividadeAtividade().get(a.getNome()))
							.doubleValue() > 0)
					&& (resultado >= 1))
				resultado += 1;
			return numeroDiasInicialAtividade - resultado;
		}

		return 0;
	}

	/**
	 * Para normaliza��o do peso de cada caracter�stica no c�lculo de menor ou
	 * maior subaloca��o
	 * 
	 * @param valMaxCaracteristicas
	 *            Valor m�ximo da intensidade da caracter�stica
	 * @return N�mero com o valor relativo normalizado em rela��o �s outras
	 *         caracter�sticas
	 */
	public double calculaPontosCaracteristicaRelativo(int valMaxCaracteristicas) {
		int valor = 0;
		Iterator i;
		Caracteristica c;

		i = this.caracteristicas.values().iterator();
		if (i.hasNext()) {
			do {
				c = ((Caracteristica) i.next());
				valor += (c.getValor().getValorNumerico() * valMaxCaracteristicas)
						/ c.getDefinicaoCaracteristica()
								.getTipoDefinicaoCaracteristica().getEscala()
								.getValorMaximo();
			} while (i.hasNext());
		}
		return valor;
	}

	public String toString() {
		return this.nome;
	}
}