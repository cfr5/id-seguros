package es.udc.fi.lbd.monuzz.id.seguros.model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;



public class SeguroFogar extends Seguro {

	private String enderezo;	
	private Map<String, Float> coberturas = new HashMap<String, Float>();
	
	// Atributos obrigatorios: enderezo
	// Atributos unicos: -

	// OUTRAS RESTRICCIONS (A GARANTIR NA BD) 
	
	// Debe ser imposible, na BD, que un SEGURO DE FOGAR teña beneficiarios definidos. 
	// SO OS SEGUROS DE VIDA poderan incluir beneficiarios

	
	public  SeguroFogar() {
	}

	public SeguroFogar(String codigo, Timestamp dataInicio, String enderezo) {
		super(codigo, dataInicio);
		this.enderezo = enderezo;		
	}

	//GETTERS
	
	public String getEnderezo() {
		return this.enderezo;
	}
	public Map<String, Float> getCoberturas() {
		return this.coberturas;
	}
	
	//SETTERS
	
	public void setEnderezo(String enderezo) {
		this.enderezo = enderezo;
	}
	public void setCoberturas (Map<String, Float> coberturas) {
		this.coberturas = coberturas;
		
	}
	
	//OUTROS
	
	// Método de conveniencia para dar de alta unha nova cobertura de fogar
	// Nota: en memoria, NON EN DISCO
	
	public void altaCobertura(String cobertura, Float importe) {
		if (cobertura == null || importe==null)
			throw new RuntimeException("Erro: datos da cobertura incompletos" );			
		if (this.coberturas.containsKey(cobertura))
			throw new RuntimeException("Erro: a cobertura '" + cobertura + "' xa estába incluida no seguro [ " + this.getCodigo()+ " ]" );			
		this.coberturas.put(cobertura,  importe);
	}
	
	// Método de conveniencia para dar de baixa unha cobertura de fogar
	// Nota: en memoria, NON EN DISCO

	public void baixaCobertura(String cobertura) {
		if (!this.coberturas.containsKey(cobertura))
			throw new RuntimeException("Erro: a cobertura '" + cobertura + "' non está incluida no seguro [ " + this.getCodigo()+ " ]" );			
		this.coberturas.remove(cobertura);
	}
	
	
	@Override
	public String toString() {
		return super.toString() +  " Tipo: Fogar [enderezo=" + this.enderezo + "]";
	}

	public String coberturasToString() {
		String texto = "Coberturas: ";
		Set<Entry<String, Float>> set = this.coberturas.entrySet();
	      Iterator<Entry<String, Float>> iterator = set.iterator();
	      while(iterator.hasNext()) {
	         Entry<String, Float> mentry = iterator.next();
	         texto += " ["+ mentry.getKey() + ": " + mentry.getValue() + "]";
	      }
	    return texto;
	}
}
