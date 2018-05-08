package es.udc.fi.lbd.monuzz.id.seguros.services;

import java.util.List;
import java.util.Set;

import es.udc.fi.lbd.monuzz.id.seguros.model.Seguro;
import es.udc.fi.lbd.monuzz.id.seguros.model.SeguroFogar;
import es.udc.fi.lbd.monuzz.id.seguros.model.SeguroVida;
import es.udc.fi.lbd.monuzz.id.seguros.model.Cliente;


public interface SeguroService {
	
	// NOTA: Todos os métodos DEBEN rexistrar no log CALQUERA ERRO OU EXCEPCION QUE SE PRODUZA
	
	public void altaNovoSeguroBD (Seguro meuSeguro);
		// Rexistra un novo seguro na BD. 
		// De se produciren un problema, salta excepcion
	public void borradoSeguroBD (Seguro meuSeguro);
		// Elimina os datos dun seguro na BD. 
		// De se produciren un problema, salta excepcion	
	public void modificacionSeguroBD (Seguro meuSeguro);
		// Actualiza os datos dun seguro  na BD. 
		// De se produciren un problema, salta excepcion

	// -------------------------------------------------------------------------------

	public Seguro recuperarSeguroBDPorId(Long id);
		// Carga un seguro desde a BD usando o seu id persistente
	public Seguro recuperarSeguroBDPorCodigo(String codigo);
		// Carga un seguro desde a BD usando o seu codigo (único)

	public List<Seguro> recuperarTodosSegurosBD();
		// Obten unha lista con TODOS os seguros rexistrados ba BD (na orde definida no DAO)
	public List<SeguroVida> recuperarTodosSegurosVidaBD();
	// Obten unha lista con todos os seguros DE VIDA rexistrados ba BD (na orde definida no DAO)
	public List<SeguroFogar> recuperarTodosSegurosFogarBD();
	// Obten unha lista con todos os seguros rexistrados ba BD (na orde definida no DAO)

	// -------------------------------------------------------------------------------
	
	public Cliente recuperarSubscritorSeguroBD (Seguro meuSeguro);
		// Obten desde a BD o cliente que subscribiu un seguro 
		// Hai que chamar a este metodo antes de acceder ao subscritor dun seguro por primeira vez
		// Actualiza a propiedade Seguro.subscritor SE FOSE PRECISO (dependerá da estratexia EAGER/LAZY escollida)
	public List<Seguro> recuperarTodosSegurosSubscritorBD(Cliente meuCliente);
		// Obten unha lista con todos os seguros rexistrados por un cliente (na orde definida no DAO)
		// Hai que chamar a este metodo antes de acceder á coleccion de seguros dun cliente por primeira vez
		// Actualiza a propiedade 'Cliente.segurosSubscritos' SE FOSE PRECISO (dependerá da estratexia EAGER/LAZY escollida)
		// Actualiza a propiedade 'Seguro.subscritor' dos seguros recuperados SE FOSE PRECISO (dependerá da estratexia EAGER/LAZY escollida)
	public Set<Cliente> recuperarTodosBeneficiariosSeguroVidaBD(SeguroVida meuSeguro);
		// Obten todos os beneficiarios dun seguro de vida 
		// Hai que chamar a este metodo antes de acceder á coleccion de beneficiarios dun seguro por primeira vez
		// Actualiza a propiedade SeguroVida.beneficiarios SE FOSE PRECISO (dependerá da estratexia EAGER/LAZY escollida)
	public List<SeguroVida> recuperarTodosSegurosVidaBeneficiarioBD(Cliente meuCliente);
		// Obten unha listaxe con todos os seguros de vida nos que un cliente é beneficiario (na orde definida no DAO)

	// -------------------------------------------------------------------------------
	
	public List<SeguroVida> recuperarTodosSegurosSenBeneficiariosBD();
		// Obten unha lista con TODOS os seguros que non teñen ainda ningún beneficiario asociado  
		// (na orde definida no DAO)
	public List<Cliente> recuperarTodosClientesSenSegurosBD();
		// Obten unha lista con TODOS os clientees que non subscribiron ainda ningun seguro de ningun tipo
		// (na orde definida no DAO)
	public List<Cliente> recuperarTodosClientesSenSeguroVidaBD();
		// Obten unha lista con TODOS os clientes que non subscribiron ainda ningún seguro de vida
		// (na orde definida no DAO)

	// -------------------------------------------------------------------------------

	public Long recuperarNumBeneficiariosClienteBD (Cliente meuCliente);
		//Recupera o número de beneficiarios diferentes en todos os seguros de vida dun cliente determinado	
	
}
