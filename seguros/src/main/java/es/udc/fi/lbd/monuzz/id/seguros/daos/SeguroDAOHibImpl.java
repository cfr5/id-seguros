package es.udc.fi.lbd.monuzz.id.seguros.daos;

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
				+ "order by s.dataInicio").list();
		return seguro;
	}


	@SuppressWarnings("unchecked")
	public List<SeguroVida> findAllSegurosVida() {
		List<SeguroVida> seguro = sessionFactory.getCurrentSession().createQuery("from SeguroVida s "
				+ "order by s.dataInicio").list();
		return seguro;
	}

	@Override
	public List<SeguroFogar> findAllSegurosFogar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente findSubscritorSeguro(Seguro meuSeguro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seguro> findAllSegurosSubscritor(Cliente meuCliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Cliente> findAllBeneficiariosSeguroVida(SeguroVida meuSeguro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SeguroVida> findAllSegurosVidaBeneficiario(Cliente meuCliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SeguroVida> findAllSegurosSenBeneficiarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> findAllClientesSenSeguros() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> findAllClientesSenSeguroVida() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long findNumBeneficiariosCliente(Cliente meuCliente) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
