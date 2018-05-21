package es.udc.fi.lbd.monuzz.id.seguros.services;


import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.lbd.monuzz.id.seguros.daos.SeguroDAO;
import es.udc.fi.lbd.monuzz.id.seguros.model.*;

@Service
public class SeguroServiceImpl implements SeguroService {

	
	static Logger log = Logger.getLogger("apps");
	
	@Autowired
	private SeguroDAO seguroDAO;
	
	
	@Transactional(value="meuTransactionManager")
	public void altaNovoSeguroBD(Seguro meuSeguro) {
		try {
			seguroDAO.create(meuSeguro);
			log.info("Se ha creado el nuevo seguro: " + meuSeguro.toString());
		}catch (DataAccessException e) {
			log.error("Error durante la creación del seguro: " + meuSeguro.toString());
			throw e;
		}
		
	}
	

	@Transactional(value="meuTransactionManager")
	public void borradoSeguroBD(Seguro meuSeguro) {
		try {
			seguroDAO.remove(meuSeguro);
			log.info("Se ha borrado el seguro: " + meuSeguro.toString());
		}catch (DataAccessException e) {
			log.error("Error durante el borrado del seguro: " + meuSeguro.toString());
			throw e;
		}
		
	}

	@Transactional(value="meuTransactionManager")
	public void modificacionSeguroBD(Seguro meuSeguro) {
		try {
			seguroDAO.update(meuSeguro);
			log.info("Se ha actualizado el seguro: " + meuSeguro.toString());
		}catch (DataAccessException e) {
			log.error("Error durante la actualizacion del seguro: " + meuSeguro.toString());
			throw e;
		}
		
	}

	@Transactional(value="meuTransactionManager")
	public Seguro recuperarSeguroBDPorId(Long id) {
		Seguro seguro;
		try {
			seguro = seguroDAO.findById(id);
			log.info("Se ha buscado el seguro con id: " + id);
		}catch (DataAccessException e) {
			log.error("Error buscando el seguro con id: " + id);
			throw e;
		}
		return seguro;
	}
	

	@Transactional(value="meuTransactionManager")
	public Seguro recuperarSeguroBDPorCodigo(String codigo) {
		Seguro seguro;
		try {
			seguro = seguroDAO.findByCodigo(codigo);
			log.info("Se ha buscado el seguro con codigo: " + codigo);
		}catch (DataAccessException e) {
			log.error("Error buscando el seguro con codigo: " + codigo);
			throw e;
		}
		return seguro;
	}

	
	@Transactional(value="meuTransactionManager")
	public List<Seguro> recuperarTodosSegurosBD() {
		
		List<Seguro> seguros;
		
		try {
			seguros = seguroDAO.findAllSeguros();
			log.info("Se han buscado todos los seguros." );
		}catch (DataAccessException e) {
			log.error("Error buscando todos los seguros.");
			throw e;
		}
		return seguros;
	}
	

	@Transactional(value="meuTransactionManager")
	public List<SeguroVida> recuperarTodosSegurosVidaBD() {
		
		List<SeguroVida> seguros;
		
		try {
			seguros = seguroDAO.findAllSegurosVida();
			log.info("Se han buscado todos los seguros de vida." );
		}catch (DataAccessException e) {
			log.error("Error buscando todos los seguros de vida.");
			throw e;
		}
		return seguros;
	}

	
	@Transactional(value="meuTransactionManager")
	public List<SeguroFogar> recuperarTodosSegurosFogarBD() {
		
		List<SeguroFogar> seguros;
		
		try {
			seguros = seguroDAO.findAllSegurosFogar();
			log.info("Se han buscado todos los seguros de hogar." );
		}catch (DataAccessException e) {
			log.error("Error buscando todos los seguros de hogar.");
			throw e;
		}
		return seguros;
	}

	
	@Transactional(value="meuTransactionManager")
	public Cliente recuperarSubscritorSeguroBD(Seguro meuSeguro) {
		
		Cliente cliente;
		
		try {
			cliente = seguroDAO.findSubscritorSeguro(meuSeguro);
			log.info("Se ha buscado el subscritor del seguro." );
		}catch (DataAccessException e) {
			log.error("Error buscando el subscritor del seguro.");
			throw e;
		}
		
		return cliente;
	}

	@Transactional(value="meuTransactionManager")
	public List<Seguro> recuperarTodosSegurosSubscritorBD(Cliente meuCliente) {

		List<Seguro> seguros;
		
		try {
			seguros = seguroDAO.findAllSegurosSubscritor(meuCliente);
			log.info("Se han buscado todos los seguros de: " + meuCliente.toString() );
		}catch (DataAccessException e) {
			log.error("Error buscando todos los seguros de. " + meuCliente.toString());
			throw e;
		}
		return seguros;
	}

	@Transactional(value="meuTransactionManager")
	public Set<Cliente> recuperarTodosBeneficiariosSeguroVidaBD(SeguroVida meuSeguro) {
		Set<Cliente> cliente;
		
		try {
			cliente = seguroDAO.findAllBeneficiariosSeguroVida(meuSeguro);
			log.info("Se han buscado todos los beneficiarios de: " + meuSeguro.toString() );
		}catch (DataAccessException e) {
			log.error("Error buscando todos los seguros de. " + meuSeguro.toString());
			throw e;
		}
		return cliente;
	}

	@Transactional(value="meuTransactionManager")
	public List<SeguroVida> recuperarTodosSegurosVidaBeneficiarioBD(Cliente meuCliente) {
		
		List<SeguroVida> seguros;
		
		try {
			seguros = seguroDAO.findAllSegurosVidaBeneficiario(meuCliente);
			log.info("Se han buscado todos los seguros de vida del que el cliente es beneficiario." );
		}catch (DataAccessException e) {
			log.error("Error buscando todos los seguros de vida del que el cliente es beneficiario.");
			throw e;
		}
		return seguros;
	}

	@Transactional(value="meuTransactionManager")
	public List<SeguroVida> recuperarTodosSegurosSenBeneficiariosBD() {

		List<SeguroVida> seguros;
		
		try {
			seguros = seguroDAO.findAllSegurosSenBeneficiarios();
			log.info("Se han buscado todos los seguros de vida sin beneficiario." );
		}catch (DataAccessException e) {
			log.error("Error buscando todos los seguros de vida sin beneficiario.");
			throw e;
		}
		return seguros;
	}

	@Transactional(value="meuTransactionManager")
	public List<Cliente> recuperarTodosClientesSenSegurosBD() {

		List<Cliente> clientes;
		
		try {
			clientes = seguroDAO.findAllClientesSenSeguros();
			log.info("Se han buscado todos los clientes sin seguro." );
		}catch (DataAccessException e) {
			log.error("Error buscando todos los clienets sin seguro.");
			throw e;
		}
		return clientes;
		
	}

	@Transactional(value="meuTransactionManager")
	public List<Cliente> recuperarTodosClientesSenSeguroVidaBD() {

		List<Cliente> clientes;
		
		try {
			clientes = seguroDAO.findAllClientesSenSeguroVida();
			log.info("Se han buscado todos los clientes sin seguro de vida." );
		}catch (DataAccessException e) {
			log.error("Error buscando todos los clienets sin seguro de vida.");
			throw e;
		}
		return clientes;
	}

	@Transactional(value="meuTransactionManager")
	public Long recuperarNumBeneficiariosClienteBD(Cliente meuCliente) {
		
		Long num;
		try {
			num = seguroDAO.findNumBeneficiariosCliente(meuCliente);
			log.info("Se han buscado el numero de beneficiarios del cliente." );
		}catch (DataAccessException e) {
			log.error("Error buscando el numero de beneficiarios del cliente.");
			throw e;
		}
		return num;
	}


}
