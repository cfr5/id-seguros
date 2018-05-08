package es.udc.fi.lbd.monuzz.id.seguros;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;

import es.udc.fi.lbd.monuzz.id.seguros.model.*;
import es.udc.fi.lbd.monuzz.id.seguros.services.*;

public class TestUtils {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private SeguroService seguroService;
	
	
	public final long timeout = 50;
	
	public Cliente cliente_A;
	public Cliente cliente_B;
	public Cliente cliente_C;
	public Cliente cliente_D;
	public Cliente cliente_E;

	public SeguroVida svida_A1;
	public SeguroVida svida_A2;
	public SeguroVida svida_E1;
	public SeguroVida svida_E2;

	public SeguroFogar sfogar_D;
	public SeguroFogar sfogar_E;
	

	public void creaSetClientesProba() {

		// CLIENTES

		// public Cliente(String login, String password, String nome)

		cliente_A = new Cliente ("clienteA", "clientea", "CLIENTE_A");
		cliente_B = new Cliente ("clienteB", "clienteb", "CLIENTE_B");
		cliente_C = new Cliente ("clienteC", "clientec", "CLIENTE_C");
		cliente_D = new Cliente ("clienteD", "cliented", "CLIENTE_D");
		cliente_E = new Cliente ("clienteE", "clientee", "CLIENTE_E");
		
		clienteService.altaNovoClienteBD(cliente_A);
		clienteService.altaNovoClienteBD(cliente_B);
		clienteService.altaNovoClienteBD(cliente_C);
		clienteService.altaNovoClienteBD(cliente_D);
		clienteService.altaNovoClienteBD(cliente_E);

	}
	
	public void eliminaSetClientesProba() {

		clienteService.borradoClienteBD(cliente_A);
		clienteService.borradoClienteBD(cliente_B);
		clienteService.borradoClienteBD(cliente_C);
		clienteService.borradoClienteBD(cliente_D);
		clienteService.borradoClienteBD(cliente_E);
	}

	public void creaSetSegurosProba() {

		// Crea tres seguros de vida vinculados aos clientes creados anteriormente
		// public SeguroVida(String codigo, Timestamp dataInicio, Float importe)
		
		svida_A1 = new SeguroVida("SVIDA_A1", Timestamp.valueOf("2017-01-25 10:35:14.0"), new Float(6000000));
		svida_A1.addBeneficiario(cliente_B);
		svida_A1.addBeneficiario(cliente_C);
		cliente_A.altaSeguro(svida_A1);

		svida_A2 = new SeguroVida("SVIDA_A2", Timestamp.valueOf("2017-08-30 22:29:00.0"), new Float(3000000));
		svida_A2.addBeneficiario(cliente_B);
		cliente_A.altaSeguro(svida_A2);

		svida_E1 = new SeguroVida("SVIDA_E1", Timestamp.valueOf("2018-04-01 11:02:29.0"), new Float(6000000));
		cliente_E.altaSeguro(svida_E1);
		svida_E2 = new SeguroVida("SVIDA_E2", Timestamp.valueOf("2018-04-13 11:02:29.0"), new Float(6000000));
		cliente_E.altaSeguro(svida_E2);

		// Crea dous seguros de fogar vinculados aos clientes creados anteriormente
		// public SeguroFogar(String codigo, Timestamp dataInicio, String enderezo)

		sfogar_D = new SeguroFogar("SFOGAR_D", Timestamp.valueOf("2018-03-21 11:02:29.0"), new String("Rua Desconcerto 21, 5º A, A Coruña")); 
		sfogar_D.altaCobertura("Inundacion", new Float(300000));
		sfogar_D.altaCobertura("Incendio", new Float(300000));
		cliente_D.altaSeguro(sfogar_D);

		sfogar_E = new SeguroFogar("SFOGAR_E", Timestamp.valueOf("2018-03-28 11:02:29.0"), new String("Rua Concerto 12, 9º B, Lugo")); 
		sfogar_E.altaCobertura("Inundacion", new Float(100000));
		sfogar_E.altaCobertura("Incendio", new Float(100000));
		sfogar_E.altaCobertura("Visita cuñao", new Float(100000));
		cliente_E.altaSeguro(sfogar_E);


		seguroService.altaNovoSeguroBD(svida_A1);
		seguroService.altaNovoSeguroBD(svida_A2);
		seguroService.altaNovoSeguroBD(svida_E1);
		seguroService.altaNovoSeguroBD(svida_E2);
		seguroService.altaNovoSeguroBD(sfogar_D);
		seguroService.altaNovoSeguroBD(sfogar_E);
		
	}

	public void eliminaSetSegurosProba() {
		
		seguroService.borradoSeguroBD(svida_A1);
		seguroService.borradoSeguroBD(svida_A2);
		seguroService.borradoSeguroBD(svida_E1);
		seguroService.borradoSeguroBD(svida_E2);
		seguroService.borradoSeguroBD(sfogar_D);
		seguroService.borradoSeguroBD(sfogar_E);

	}
	
}
