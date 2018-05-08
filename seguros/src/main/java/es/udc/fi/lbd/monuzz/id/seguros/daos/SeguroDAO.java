package es.udc.fi.lbd.monuzz.id.seguros.daos;

import java.util.List;
import java.util.Set;

import es.udc.fi.lbd.monuzz.id.seguros.model.SeguroVida;
import es.udc.fi.lbd.monuzz.id.seguros.model.Seguro;
import es.udc.fi.lbd.monuzz.id.seguros.model.SeguroFogar;
import es.udc.fi.lbd.monuzz.id.seguros.model.Cliente;

public interface SeguroDAO {
	public Long create(Seguro meuSeguro);
		// Rexistra un novo seguro na BD
		// Xenerar excepcion se o seguro xa é persistente
	public void update (Seguro meuSeguro);
		// Modifica os datos dun seguro na BD
		// Xenerar excepcion se o seguro non é persistente ainda	
	public void remove (Seguro meuSeguro);
		// Elimina os datos dun seguro da BD
		// Xenerar excepcion se o seguro non é persistente ainda

	// -----------------------------------------------------------------------------

	public Seguro findById(Long id);
		//Recupera os datos dun seguro desde a BD usando o seu id persistente
	public Seguro findByCodigo (String codigo);	
		//Recupera os datos dun seguro desde a BD usando o seu codigo 

	public List<Seguro> findAllSeguros();
		// REcupera TODOS os seguros
		// (por orde de data de inicio, o mais recente ao PRINCIPIO) 	
	public List<SeguroVida> findAllSegurosVida();
	// REcupera TODOS os seguros DE VIDA
	// (por orde de data de inicio, o mais recente ao PRINCIPIO) 		
	public List<SeguroFogar> findAllSegurosFogar();
	// REcupera TODOS os seguros DE FOGAR
	// (por orde de data de inicio, o mais recente ao PRINCIPIO) 	
	
	// -----------------------------------------------------------------------------

	public Cliente findSubscritorSeguro(Seguro meuSeguro);
		// Recupera ao cliente subscritor dun seguro. 	
		// Dependendo da estratexia seleccionada (EAGER/LAZY) accede á BD, 
		// ou simplemente devolve o valor da propiedade
	public List<Seguro> findAllSegurosSubscritor(Cliente meuCliente);
		// Recupera TODOS os seguros subscritos polo cliente indicado 
		// (por orde de data de inicio, o mais recente ao PRINCIPIO)
		// Dependendo da estratexia seleccionada (EAGER/LAZY) accede á BD, 
		// ou simplemente devolve o valor da propiedade
	public Set<Cliente> findAllBeneficiariosSeguroVida (SeguroVida meuSeguro);
		// Recupera ao conxunto dos beneficiarios dun seguro 
		// (a orde é indiferente) 	
		// Dependendo da estratexia seleccionada (EAGER/LAZY) accede á BD, 
		// ou simplemente devolve o valor da propiedade
	public List<SeguroVida> findAllSegurosVidaBeneficiario(Cliente meuCliente);
		// Recupera TODOS os seguros nos que a persoa indicada é beneficiaria
		// (por orde de data de inicio, o mais recente ao PRINCIPIO)

	// -----------------------------------------------------------------------------

	public List<SeguroVida> findAllSegurosSenBeneficiarios();
		//Recupera TODOS os seguros de vida que non teñen ningún beneficiario asociado  
		// (por orde de data de inicio, o mais recente ao FINAL) 	
	public List<Cliente> findAllClientesSenSeguros();
		//Recupera TODOS os clientes que non son subscritores de ningun seguro de ningun tipo
		// (por orde de login [A-Z] 
	public List<Cliente> findAllClientesSenSeguroVida();
		//Recupera TODOS os participantes que non subscribiron ningun seguro de vida
		// (por orde de login [A-Z] 

	// -----------------------------------------------------------------------------

	public Long findNumBeneficiariosCliente (Cliente meuCliente);
		// Recupera o número de beneficiarios DIFERENTES en TODOS os seguros de vida 
		// rexistrados por un determinado cliente  	

}
