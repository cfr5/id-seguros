package es.udc.fi.lbd.monuzz.id.seguros.daos;

import java.util.List;

import es.udc.fi.lbd.monuzz.id.seguros.model.Cliente;

public interface ClienteDAO {
	
	public Long create(Cliente meuCliente);
		// Rexistra un novo cliente na BD
		// Xenerar excepcion se o cliente xa é persistente
	public void update (Cliente meuCliente);
		// Modifica os datos dun cliente na BD
		// Xenerar excepcion se o cliente non é persistente ainda	
	public void remove (Cliente meuCliente);
		// Elimina os datos dun cliente da BD
		// Xenerar excepcion se o cliente non é persistente ainda

	// -----------------------------------------------------------------------------

	public Cliente findById(Long id);
		// Recupera os datos dun cliente desde a BD usando o seu id persistente
	public Cliente findByLogin (String login);	
		// Recupera os datos dun cliente desde a BD usando o seu login 

	// -----------------------------------------------------------------------------

	public List<Cliente> findAll();
		// Recupera TODOS os clientes rexistrados na BD, ordenados por nome (Z-A) 

}
