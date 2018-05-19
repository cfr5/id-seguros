package es.udc.fi.lbd.monuzz.id.seguros.daos;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.udc.fi.lbd.monuzz.id.seguros.model.SeguroVida;
import es.udc.fi.lbd.monuzz.id.seguros.model.Cliente;
import es.udc.fi.lbd.monuzz.id.seguros.model.Seguro;
import es.udc.fi.lbd.monuzz.id.seguros.model.SeguroFogar;

//@SuppressWarnings("unchecked")

@Repository
public class SeguroDAOHibImpl implements SeguroDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	
	public Long create(Seguro meuSeguro) {
		if (meuSeguro == null) {
			throw new RuntimeException("Seguro non pode ser nulo");
		}
		Long id = (Long) sessionFactory.getCurrentSession().save(meuSeguro);
		return id;
	}

	
	public void update(Seguro meuSeguro) {
		if (meuSeguro == null)
			throw new RuntimeException("Seguro Invalido: O Seguro non pode ser nulo");
		sessionFactory.getCurrentSession().update(meuSeguro);	
	}

	
	public void remove(Seguro meuSeguro) {
		if (meuSeguro == null)
			throw new RuntimeException("Seguro Invalido: O Seguro non pode ser nulo");
		sessionFactory.getCurrentSession().delete(meuSeguro);	
		
	}

	
	public Seguro findById(Long id) {
		Seguro seguro = (Seguro) sessionFactory.getCurrentSession().get(Seguro.class, id);
		return seguro;
	}

	
	public Seguro findByCodigo(String codigo) {
		Seguro seguro = (Seguro) sessionFactory.getCurrentSession().createQuery("from Seguro s "
				+ "where s.codigo = :codigo").setParameter("codigo",codigo).uniqueResult();
		return seguro;
	}

	
	@SuppressWarnings("unchecked")
	public List<Seguro> findAllSeguros() {
		List<Seguro> seguro = sessionFactory.getCurrentSession().createQuery("from Seguro s "
				+ "order by s.dataInicio desc").list();
		return seguro;
	}


	@SuppressWarnings("unchecked")
	public List<SeguroVida> findAllSegurosVida() {
		List<SeguroVida> seguro = sessionFactory.getCurrentSession().createQuery("from SeguroVida s "
				+ "order by s.dataInicio desc").list();
		return seguro;
	}

	@SuppressWarnings("unchecked")
	public List<SeguroFogar> findAllSegurosFogar() {
		List<SeguroFogar> seguro = sessionFactory.getCurrentSession().createQuery("from SeguroFogar s "
				+ "order by s.dataInicio desc").list();
		return seguro;
	}

	//COIDAO CON ESTOS 3
	public Cliente findSubscritorSeguro(Seguro meuSeguro) {
		Cliente cliente = meuSeguro.getSubscritor();
		return cliente;
	}

	public List<Seguro> findAllSegurosSubscritor(Cliente meuCliente) {
		List<Seguro> seguro = null;
//		seguro = sessionFactory.getCurrentSession().createQuery("from segurosSubscritos c "
//				+ "where c.cliente_Id= :cliente_Id").setLong("cliente_Id", meuCliente.getIdCliente()).list();
		seguro = meuCliente.getSegurosSubscritos();
		return seguro;
	}

	
	@SuppressWarnings("unchecked")
	public Set<Cliente> findAllBeneficiariosSeguroVida(SeguroVida meuSeguro) {
		Set<Cliente> beneficiarios = new HashSet<Cliente>();
		beneficiarios = (Set<Cliente>) sessionFactory.getCurrentSession().createQuery("select s.beneficiarios from SeguroVida s"
				+ "where s.seguro_Id = :seguro_Id").setLong("seguro_Id", meuSeguro.getIdSeguro()).list();
		return beneficiarios;
	}

	
	@SuppressWarnings("unchecked")
	public List<SeguroVida> findAllSegurosVidaBeneficiario(Cliente meuCliente) {
		List<SeguroVida> seguro = null;
		seguro = sessionFactory.getCurrentSession().createQuery("select c.segurosSubscritos from Cliente c"
				+ "where c.cliente_Id = :cliente_Id").setLong("cliente_Id", meuCliente.getIdCliente()).list();
		return seguro;
	}

	
	@SuppressWarnings("unchecked")
	public List<SeguroVida> findAllSegurosSenBeneficiarios() {
		List<SeguroVida> seguro = null;
		seguro = sessionFactory.getCurrentSession().createQuery("from SeguroVida s where s.beneficiario is NULL order by s.dataInicio desc").list();
		return seguro;
	}

	
	@SuppressWarnings("unchecked")
	public List<Cliente> findAllClientesSenSeguros() {
		List<Cliente> cliente = null;
		cliente = sessionFactory.getCurrentSession().createQuery("from Cliente c where c.segurosSubscritos is NULL order by c.login").list();
		return cliente;
	}


	@SuppressWarnings("unchecked")
	public List<Cliente> findAllClientesSenSeguroVida() {
		List<Cliente> cliente = null;
		cliente = sessionFactory.getCurrentSession().createQuery("from Cliente c where c.segurosSubscritos is not in (select s.codigo from SeguroVida s) order by c.login").list();
		return cliente;
	}

	
	public Long findNumBeneficiariosCliente(Cliente meuCliente) {
		Long beneficiarios = (Long) sessionFactory.getCurrentSession().createQuery("select count(s.beneficiarios) from SeguroVida s"
				+ "where s.suscriptor = :meuCliente").setLong("meuCliente", meuCliente.getIdCliente()).uniqueResult();
		return beneficiarios;
	}


	
}
