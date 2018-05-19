package es.udc.fi.lbd.monuzz.id.seguros.daos;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.udc.fi.lbd.monuzz.id.seguros.model.Cliente;


//@SuppressWarnings("unchecked")

@Repository
public class ClienteDAOHibImpl implements ClienteDAO {
	
	@Autowired
	private SessionFactory sessionFactory;


	public Long create(Cliente meuCliente) {
		if (meuCliente == null) {
			throw new RuntimeException("Cliente non pode ser nulo");
		}
		Long id = (Long) sessionFactory.getCurrentSession().save(meuCliente);
		return id;
	}


	public void update(Cliente meuCliente) {
		if (meuCliente == null)
			throw new RuntimeException("Cliente Invalido: O Cliente non pode ser nulo");
		sessionFactory.getCurrentSession().update(meuCliente);	
		
	}


	public void remove(Cliente meuCliente) {
		if (meuCliente.getIdCliente() == null)
			throw new RuntimeException("Cliente Invalido: O Cliente non pode ser nulo");
		sessionFactory.getCurrentSession().delete(meuCliente);
		
	}


	public Cliente findById(Long id) {
		Cliente cliente = (Cliente) sessionFactory.getCurrentSession().get(Cliente.class, id);
		return cliente;
	}


	public Cliente findByLogin(String login) {
		Cliente cliente = (Cliente) sessionFactory.getCurrentSession().createQuery("from Cliente c "
				+ "where c.login = :login").setParameter("login",login).uniqueResult();
		return cliente;
	}


	@SuppressWarnings("unchecked")
	public List<Cliente> findAll() {
		List<Cliente> cliente = sessionFactory.getCurrentSession().createQuery("from Cliente c "
				+ "order by c.nome desc").list();
		return cliente;
	}




}
