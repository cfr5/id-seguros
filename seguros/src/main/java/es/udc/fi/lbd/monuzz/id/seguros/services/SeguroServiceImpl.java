package es.udc.fi.lbd.monuzz.id.seguros.services;


import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import es.udc.fi.lbd.monuzz.id.seguros.model.*;

@Service
public class SeguroServiceImpl implements SeguroService {

	@Override
	public void altaNovoSeguroBD(Seguro meuSeguro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borradoSeguroBD(Seguro meuSeguro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificacionSeguroBD(Seguro meuSeguro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seguro recuperarSeguroBDPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seguro recuperarSeguroBDPorCodigo(String codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seguro> recuperarTodosSegurosBD() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SeguroVida> recuperarTodosSegurosVidaBD() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SeguroFogar> recuperarTodosSegurosFogarBD() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente recuperarSubscritorSeguroBD(Seguro meuSeguro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seguro> recuperarTodosSegurosSubscritorBD(Cliente meuCliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Cliente> recuperarTodosBeneficiariosSeguroVidaBD(SeguroVida meuSeguro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SeguroVida> recuperarTodosSegurosVidaBeneficiarioBD(Cliente meuCliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SeguroVida> recuperarTodosSegurosSenBeneficiariosBD() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> recuperarTodosClientesSenSegurosBD() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> recuperarTodosClientesSenSeguroVidaBD() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long recuperarNumBeneficiariosClienteBD(Cliente meuCliente) {
		// TODO Auto-generated method stub
		return null;
	}


}
