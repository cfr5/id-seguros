package es.udc.fi.lbd.monuzz.id.seguros.daos;

import java.util.List;
import java.util.Set;



import es.udc.fi.lbd.monuzz.id.seguros.model.SeguroVida;
import es.udc.fi.lbd.monuzz.id.seguros.model.Cliente;
import es.udc.fi.lbd.monuzz.id.seguros.model.Seguro;
import es.udc.fi.lbd.monuzz.id.seguros.model.SeguroFogar;

//@SuppressWarnings("unchecked")


public class SeguroDAOHibImpl implements SeguroDAO {

	@Override
	public Long create(Seguro meuSeguro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Seguro meuSeguro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Seguro meuSeguro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seguro findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seguro findByCodigo(String codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seguro> findAllSeguros() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SeguroVida> findAllSegurosVida() {
		// TODO Auto-generated method stub
		return null;
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
