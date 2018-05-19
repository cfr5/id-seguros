package es.udc.fi.lbd.monuzz.id.seguros.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "seguro_Id")
@Table(name="SEGUROVIDA")
public class SeguroVida extends Seguro {

	private Float importe;
	private Set<Cliente> beneficiarios = new HashSet<Cliente>();

	// Atributos obligatorios: importe
	// Atributos únicos: -
	
	// O conxunto de beneficiarios NON ESTA ORDENADO (e non hai repetidos)
	
	// OUTRAS RESTRICCIONS (A GARANTIR NA BD) 
	// Debe ser imposible, na BD, que un cliente figure duas ou mais veces como beneficiario do mesmo SEGURO DE VIDA
	// (a chave primaria debe estar correctamente definida na taboa M:N)


	public SeguroVida() {
	}

	public SeguroVida(String codigo, Timestamp dataInicio, Float importe) {
		super(codigo, dataInicio);
		this.importe = importe;
	}

	// GETTERS
	@Column(nullable=false)
	public Float getImporte() {
		return this.importe;
	}
	
	@ElementCollection
	@CollectionTable(name = "BENEFICIARIOS", joinColumns = @JoinColumn(name = "seguros_Id"))
	//@Column(name = "BENEFICIARIO")
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)//mirar o de cascade=CascadeType.ALL
	@JoinColumn(name = "beneficiarios")
	public Set<Cliente> getBeneficiarios() {
		return this.beneficiarios;
	}
	

	// SETTERS
	
	public void setImporte(Float importe) {
		this.importe = importe;
	}
	
	public void setBeneficiarios(Set<Cliente> beneficiarios) {
		this.beneficiarios = beneficiarios;
	}

	// Métodos de conveniencia (EN MEMORIA, NON NA BD!!!!!)
	
	public void addBeneficiario (Cliente cliente) {
		if (cliente == null)
			throw new RuntimeException("Erro: faltan os datos do cliente");
		if (cliente.equals(this.getSubscritor()))
			throw new RuntimeException("Erro: o titular '"+cliente.getNome()+"' non pode ser beneficiario do seu propio seguro de vida: " + this.getCodigo());  //Duplicado
		if (!this.beneficiarios.add(cliente))
			throw new RuntimeException("Erro: o cliente '"+cliente.getNome()+"' xa era beneficiario do seguro: " + this.getCodigo());  //Duplicado
	}
	
	public void removeBeneficiario (Cliente cliente) {
		if (cliente == null)
			throw new RuntimeException("Erro: faltan os datos do cliente");
		if (!this.beneficiarios.remove(cliente))
			throw new RuntimeException("Erro: o cliente '"+cliente.getNome()+"' non é beneficiario do seguro: " + this.getCodigo()); //Non está no conxunto

	}
	
	@Override
	public String toString() {
		return super.toString() +  " Tipo: VIDA [importe=" + importe + "]";
	}


	public String beneficiariosToString() {
		String texto = "Beneficiarios: ";
	      Iterator<Cliente> iterator = this.beneficiarios.iterator();
	      while(iterator.hasNext()) {
	         Cliente cli = iterator.next();
	         texto += " [" + cli.getNome()+ "] ";
	      }
	    return texto;
	}

}
