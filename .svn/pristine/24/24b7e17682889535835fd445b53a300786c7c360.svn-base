/**
 * Title:        Protótipo Tese - PeriodoIndisponivel
 * Description:  Implementação do protótipo da tese. Classe PeriodoIndisponivel
 * Copyright:    Copyright (c) 2004
 * Company:      COPPE Sistemas
 * @author Ahilton S. Barreto
 * @version 1.0
 */

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * @author Ahilton
 * 
 * Esta classe implementa um período de indisponibilidade de um recurso (por
 * exemplo, um profissional). Essa indisponibilidade pode ser por qualquer
 * motivo: férias, alocação a outra atividade, etc.
 */
public class PeriodoIndisponivel {

	/**
	 * Data de início do período de indisponibilidade
	 */
	private Date dataInicio;

	/**
	 * Data de término do período de indisponibilidade
	 */
	private Date dataFim;

	/**
	 * Quantidade de horas por dia em que o profissional está indisponível no
	 * período de indisponibilidade
	 */
	private int qtdHoras;

	/**
	 * Construtor da classe (com parâmetros)
	 * 
	 * @param dt_inicio
	 *            Data de início
	 * @param dt_fim
	 *            Data de término
	 * @param horas
	 *            Horas diárias de indisponibilidade
	 */
	public PeriodoIndisponivel(String dt_inicio, String dt_fim, int horas) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		this.qtdHoras = horas;

		try {
			this.dataInicio = formato.parse(dt_inicio);
			this.dataFim = formato.parse(dt_fim);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Construtor da classe (com parâmetros usando Date)
	 * 
	 * @param dt_inicio
	 *            Data de início
	 * @param dt_fim
	 *            Data de término
	 * @param horas
	 *            Horas diárias de indisponibilidade
	 */
	public PeriodoIndisponivel(Date dt_inicio, Date dt_fim, int horas) {
		this.qtdHoras = horas;
		this.dataInicio = dt_inicio;
		this.dataFim = dt_fim;
	}

	/**
	 * Retorna a data inicial do período de indisponibilidade
	 * 
	 * @return Data inicial do período
	 */
	public Date getDataInicio() {
		return this.dataInicio;
	}
	
	public void setDataInicio(Date parDataInicio) {
		this.dataInicio = parDataInicio;
	}
	
	public String getDataInicioString() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return formato.format(this.dataInicio);
	}

	/**
	 * Retorna a data final do período de indisponibilidade
	 * 
	 * @return Data final do período
	 */
	public Date getDataFim() {
		return this.dataFim;
	}
	
	public void setDataFim(Date parDataFim) {
		this.dataFim = parDataFim;
	}
	
	public String getDataFimString() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return formato.format(this.dataFim);
	}

	/**
	 * Retorna o número de horas no dia do período de indisponibilidade
	 * 
	 * @return Número de horas por dia do período
	 */
	public int getQtdHoras() {
		return this.qtdHoras;
	}
	
	public void setQtdHoras(int parQtdHoras) {
		this.qtdHoras = parQtdHoras;
	}

	/**
	 * Testa se o período de indisponibilidade faz com que o profissional se
	 * torne indisponível num período passado como parâmetro
	 * 
	 * @param parDtInicio Data de início
	 * @param parDtFim Data de término
	 * @return
	 */
	public boolean checaDisponibilidadeData(Date parDtInicio, Date parDtFim) {
		/**
		 * Se a data inicial está no intervalo de indisponibilidade, se a data
		 * final está no intervalo ou se o intervalo ou se o período passado
		 * contém o intervalo de indisponibilidade, não há disponibilidade no
		 * período
		 */

		if (((parDtInicio.compareTo(this.dataInicio) >= 0) && (parDtInicio
				.compareTo(this.dataFim) <= 0))
				|| ((parDtFim.compareTo(this.dataInicio) >= 0) && (parDtFim
						.compareTo(this.dataFim) <= 0))
				|| ((parDtInicio.compareTo(this.dataInicio) <= 0) && (parDtFim
						.compareTo(this.dataFim) >= 0))) {
			return false;
		}
		return true;
	}

}