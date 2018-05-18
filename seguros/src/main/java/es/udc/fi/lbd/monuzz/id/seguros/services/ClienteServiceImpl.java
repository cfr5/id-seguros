package es.udc.fi.lbd.monuzz.id.seguros.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.lbd.monuzz.id.seguros.daos.ClienteDAO;
import es.udc.fi.lbd.monuzz.id.seguros.model.*;

@Service
public class ClienteServiceImpl implements ClienteService {

	
	static Logger log = Logger.getLogger("apps");
	
	@Autowired
	private ClienteDAO clienteDAO;
	
	@Transactional(value="meuTransactionManager")
	public void altaNovoClienteBD(Cliente meuCliente) {
		try {
			clienteDAO.create(meuCliente);
			log.info("Se ha registrado el cliente: " + meuCliente.toString());
		} catch (DataAccessException e) {
			log.error("Error durante la creación del cliente: " + meuCliente.toString());
			throw e;
		}
		
	}
	
	
	@Transactional(value="meuTransactionManager")
	public void borradoClienteBD(Cliente meuCliente) {
		try {
			clienteDAO.remove(meuCliente);
			log.info("Se ha borrado el cliente: " + meuCliente.toString());
		} catch (DataAccessException e) {
			log.error("Error durante el borrado del cliente: " + meuCliente.toString());
			throw e;
		}
		
	}

	@Transactional(value="meuTransactionManager")
	public void modificacionClienteBD(Cliente meuCliente) {
		try {
			clienteDAO.update(meuCliente);
			log.info("Se ha actualizado el cliente: " + meuCliente.toString());
		} catch (DataAccessException e) {
			log.error("Error durante la actualización del cliente: " + meuCliente.toString());
			throw e;
		}
		
	}

	@Transactional(value="meuTransactionManager")
	public Cliente recuperarClienteBDPorId(Long id) {
		Cliente cliente;
		try {
			cliente = clienteDAO.findById(id);
			log.info("Se ha buscado el cliente con id: " + id);
		} catch (DataAccessException e) {
			log.error("Error buscando el cliente con id: " + id);
			throw e;
		}
		return cliente;
	}

	
	@Transactional(value="meuTransactionManager")
	public Cliente recuperarClienteBDPorLogin(String login) {
		Cliente cliente;
		try {
			cliente = clienteDAO.findByLogin(login);
			log.info("Se ha buscado el cliente con login: " + login);
		} catch (DataAccessException e) {
			log.error("Error buscando el cliente con login: " + login);
			throw e;
		}
		return cliente;
	}

	@Transactional(value="meuTransactionManager")
	public List<Cliente> recuperarTodosClientesBD() {
		List<Cliente> clientes;
		try {
			clientes = clienteDAO.findAll();
			log.info("Se han buscado todos los clientes");
		} catch (DataAccessException e) {
			log.error("Error al buscar todos los clientes");
			throw e;
		}
		return clientes;
	}


}
