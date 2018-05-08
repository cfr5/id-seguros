package es.udc.fi.lbd.monuzz.id.seguros.services;
import java.util.List;

import es.udc.fi.lbd.monuzz.id.seguros.model.Cliente;

public interface ClienteService {
		
	// NOTA: Todos os métodos DEBEN rexistrar no log CALQUERA ERRO OU EXCEPCION QUE SE PRODUZA
	
	void altaNovoClienteBD (Cliente meuCliente);
		// Rexistra un novo cliente na BD. 
		// De se produciren un problema, salta excepcion
	void borradoClienteBD (Cliente meuCliente);
		// Elimina un cliente da BD. 		
		// De se produciren un problema, salta excepcion
		// Nota: Ao eliminar un cliente, todos os seguros subscritos por el/ela TAMEN SE BORRAN
		// Nota: NON SE PODE eliminar un cliente que ainda figura como beneficiario de algún seguro de vida.	      
		// 		MOI IMPORTANTE:		
		//		Como o cliente non garda coleccion de seguros dos que é beneficiario, 
		//      Hibernate non sabe chegar á táboa M:N e eliminar as filas asociadas,
		//      polo que se producirá unha violacion de integridade referencial.
		// 		Habería que dalo de baixa como beneficiario antes de borralo.
		//      PERO NON SE PIDE que borradoClienteBD o faga. Simplemente se debe capturar a excepcion se esta se produce.
	void modificacionClienteBD (Cliente meuCliente);
		// Actualiza os datos dun cliente rexistrado na BD.
		// De se produciren un problema, salta excepcion

	// -------------------------------------------------------------------------------

	Cliente recuperarClienteBDPorId(Long id);
		// Carga a un cliente desde a BD usando o seu id persistente
	Cliente recuperarClienteBDPorLogin(String login);
		// Carga a un cliente desde a BD usando o seu login (único)

	// -------------------------------------------------------------------------------

	List<Cliente> recuperarTodosClientesBD();
		//Recupera a todos os clientes rexistrados na BD, na orde definida no DAO.
	
	
}
