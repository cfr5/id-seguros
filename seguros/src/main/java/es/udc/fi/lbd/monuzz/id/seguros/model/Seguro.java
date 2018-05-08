package es.udc.fi.lbd.monuzz.id.seguros.model;

import java.sql.Timestamp;

//@SuppressWarnings("unused")

public class Seguro {
	
	private Long idSeguro;
	private String codigo;
	private Timestamp dataInicio;
	private Timestamp dataFin;
	private Cliente subscritor;

	// Chave natural: codigo
	// Atributos obrigatorios: codigo, dataInicio, subscritor
	// Atributos únicos: codigo
		
	protected Seguro() {	
	};
	
	protected Seguro(String codigo, Timestamp dataInicio) {
		this.idSeguro = null;
		this.codigo = codigo;
		this.dataInicio = dataInicio;
		this.dataFin = null;
		this.subscritor = null;
	}


	// getters
	
	public Long getIdSeguro() {
		return this.idSeguro;
	}
	public String getCodigo() {
		return codigo;
	}
	public Timestamp getDataInicio() {
		return dataInicio;
	}	
	public Timestamp getDataFin() {
		return dataFin;
	}
	public Cliente getSubscritor() {
		return this.subscritor;
	}

	//	setters
	
	public void setIdSeguro(Long idSeguro) {
		this.idSeguro = idSeguro;
	}
	public void setCodigo(String codigo) {
		this.codigo=codigo;
	}
	public void setDataInicio(Timestamp dataAlta) {
		this.dataInicio = dataAlta;
	}
	public void setDataFin(Timestamp dataPro) {
		this.dataFin = dataPro;
	}
	public void setSubscritor(Cliente subscritor) {
		this.subscritor = subscritor;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seguro other = (Seguro) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Seguro [idSeguro=" + idSeguro + ", codigo=" + codigo + ", dataInicio=" + dataInicio + ", dataFin="
				+ dataFin + "]";
	}
	




	
}
