package es.udc.fi.lbd.monuzz.id.seguros;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.udc.fi.lbd.monuzz.id.seguros.model.*;
import es.udc.fi.lbd.monuzz.id.seguros.services.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-config.xml", "/test-spring-config.xml"})
@ActiveProfiles("test")
public class TestSeguros {
	
	private Logger log = Logger.getLogger("seguros");

	@Autowired
	private TestUtils testUtils;

	@Autowired
	private ClienteService clienteService;
	@Autowired
	private SeguroService seguroService;

	@Before
	public void setUp() throws Exception { 
		log.info ("Creando clientes iniciais para caso de proba: " + this.getClass().getName() + "===========================================");
		testUtils.creaSetClientesProba(); 
		log.info ("Foron creados con éxito os datos de clientes iniciais para o caso de proba: " + this.getClass().getName() + "=============");
		log.info ("Creando seguros iniciais para caso de proba: " + this.getClass().getName() + "============================================");
		testUtils.creaSetSegurosProba();
		log.info ("Foron creados con éxito os datos de seguros iniciais para o caso de proba: " + this.getClass().getName() + "==============");
	}

	@After
	public void tearDown() throws Exception {
		
		/* Ao eliminar un cliente deben desaparecer os seus seguros      */
		/* Descomentar só se non se consegue facer funcionar tal e como está */
		/* AVISO: Descomentar reducirá a calificación da práctica                   */
						
		/*
		log.info ("Eliminando seguros iniciais para caso de proba: " + this.getClass().getName() + "========================================");
		testUtils.eliminaSetSegurosProba();
		log.info ("Foron eliminados con éxito os datos de seguros iniciais para o caso de proba: " + this.getClass().getName() + "==========");
		*/  
		
		log.info ("Eliminando clientes iniciais para caso de proba: " + this.getClass().getName() + "========================================");
		testUtils.eliminaSetClientesProba(); 
		log.info ("Foron eliminados con éxito os datos de clientes iniciais para o caso de proba: " + this.getClass().getName() + "==========");
	}
	
	@Test
	public void testCompleto() {
		log.info ("INICIANDO [Test_Consulta] en  " + this.getClass().getName() + "===========================================================");
		a_Test_Consulta();
//		log.info ("INICIANDO [Test_Alta] en  " + this.getClass().getName() + "===============================================================");
//		b_Test_Alta();
//		log.info ("INICIANDO [Test_Actualizacion] en  " + this.getClass().getName() + "======================================================");
//		c_Test_Actualizacion();
//		log.info ("INICIANDO [Test_Borrado] en  " + this.getClass().getName() + "============================================================");
//		d_Test_Borrado();


	}
	
	private void a_Test_Consulta() {

		Cliente clienteX;
		SeguroVida svidaX;
		SeguroFogar sfogarX;

		
		List<Seguro> listaxeS;
		List<SeguroVida> listaxeSV;
		List<SeguroFogar> listaxeSF;
		Set<Cliente> conxuntoC;
		List<Cliente> listaxeC;
		

		// Probamos recuperacion basica

		assertEquals (testUtils.svida_A1, seguroService.recuperarSeguroBDPorCodigo(testUtils.svida_A1.getCodigo()));
		assertEquals (testUtils.svida_A1, seguroService.recuperarSeguroBDPorId(testUtils.svida_A1.getIdSeguro()));

		// Comprobar recuperacion basica na orde axeitada

		listaxeS = (List<Seguro>) seguroService.recuperarTodosSegurosBD();
		assertEquals (listaxeS.size(), 6);
		assertEquals (listaxeS.get(0), testUtils.svida_E2);
		assertEquals (listaxeS.get(1), testUtils.svida_E1);
		assertEquals (listaxeS.get(2), testUtils.sfogar_E);
		assertEquals (listaxeS.get(3), testUtils.sfogar_D);
		assertEquals (listaxeS.get(4), testUtils.svida_A2);
		assertEquals (listaxeS.get(5), testUtils.svida_A1);

		listaxeSV = seguroService.recuperarTodosSegurosVidaBD();
		assertEquals (listaxeSV.size(), 4);
		assertEquals (listaxeSV.get(0), testUtils.svida_E2);
		assertEquals (listaxeSV.get(1), testUtils.svida_E1);
		assertEquals (listaxeSV.get(2), testUtils.svida_A2);
		assertEquals (listaxeSV.get(3), testUtils.svida_A1);
		
		listaxeSF = seguroService.recuperarTodosSegurosFogarBD();
		assertEquals (listaxeSF.size(), 2);
		assertEquals (listaxeSF.get(0), testUtils.sfogar_E);
		assertEquals (listaxeSF.get(1), testUtils.sfogar_D);

		// Comprobar recuperacion sofisticada
		
//		// "recuperarTodosSegurosSubscritorBD": servizo que actualiza se é preciso 'Cliente.segurosSubscritos' (e nalgún caso, 'Seguro.subscritor')
		clienteX = clienteService.recuperarClienteBDPorLogin(testUtils.cliente_A.getLogin());
		listaxeS = (List<Seguro>) seguroService.recuperarTodosSegurosSubscritorBD(clienteX);				
//		// Comprobamos que clienteX.segurosSubscritos() está dispoñible 
//		assertEquals (listaxeS.size(), 2);
//		assertEquals (listaxeS.get(0), testUtils.svida_A2);
//		assertEquals (listaxeS.get(1), testUtils.svida_A1);
//		assertEquals (clienteX.getSegurosSubscritos().size(), 2);
//		assertEquals (clienteX.getSegurosSubscritos().get(0), testUtils.svida_A2);
//		assertEquals (clienteX.getSegurosSubscritos().get(1), testUtils.svida_A1);
//		// Comprobamos que clienteX figura como subscritor deses seguros
//		assertEquals (clienteX.getSegurosSubscritos().get(0).getSubscritor(), clienteX);
//		assertEquals (clienteX.getSegurosSubscritos().get(1).getSubscritor(), clienteX);
//		assertTrue (clienteX.getSegurosSubscritos().get(0).getSubscritor()==clienteX);
//		assertTrue (clienteX.getSegurosSubscritos().get(1).getSubscritor()==clienteX);
//		clienteX = clienteService.recuperarClienteBDPorLogin(testUtils.cliente_E.getLogin());
//		listaxeS = (List<Seguro>) seguroService.recuperarTodosSegurosSubscritorBD(clienteX);				
//		// Comprobamos que clienteX.segurosSubscritos() está dispoñible 
//		assertEquals (listaxeS.size(), 3);
//		assertEquals (listaxeS.get(0), testUtils.svida_E2);
//		assertEquals (listaxeS.get(1), testUtils.svida_E1);
//		assertEquals (listaxeS.get(2), testUtils.sfogar_E);
//		assertEquals (clienteX.getSegurosSubscritos().size(), 3);
//		assertEquals (clienteX.getSegurosSubscritos().get(0), testUtils.svida_E2);
//		assertEquals (clienteX.getSegurosSubscritos().get(1), testUtils.svida_E1);
//		assertEquals (clienteX.getSegurosSubscritos().get(2), testUtils.sfogar_E);
//		// Comprobamos que clienteX figura como subscritor deses seguros
//		assertEquals (clienteX.getSegurosSubscritos().get(0).getSubscritor(), clienteX);
//		assertEquals (clienteX.getSegurosSubscritos().get(1).getSubscritor(), clienteX);
//
//		// "recuperarSubscritorSeguroBD": servizo que actualiza se é preciso 'Seguro.subscritor'
//		svidaX = (SeguroVida) seguroService.recuperarSeguroBDPorCodigo(testUtils.svida_A1.getCodigo());
//		clienteX =  seguroService.recuperarSubscritorSeguroBD(svidaX);
//		assertEquals (clienteX, testUtils.cliente_A);
//		assertEquals (svidaX.getSubscritor(), testUtils.cliente_A);
//
//		sfogarX = (SeguroFogar) seguroService.recuperarSeguroBDPorCodigo(testUtils.sfogar_D.getCodigo());
//		clienteX =  seguroService.recuperarSubscritorSeguroBD(sfogarX);
//		assertEquals (clienteX, testUtils.cliente_D);
//		assertEquals (sfogarX.getSubscritor(), testUtils.cliente_D);
//		
//		// "recuperarTodosBeneficiariosSeguroVidaBD": servizo que actualiza se é preciso 'Seguro.beneficiarios'
//		svidaX = (SeguroVida) seguroService.recuperarSeguroBDPorCodigo(testUtils.svida_A1.getCodigo());
//		conxuntoC = (Set<Cliente>)seguroService.recuperarTodosBeneficiariosSeguroVidaBD(svidaX);
//		// Comprobamos que svidaX.beneficiarios() está dispoñible 
//		assertEquals (conxuntoC.size(), 2);
//		assertTrue (conxuntoC.contains(testUtils.cliente_B));
//		assertTrue (conxuntoC.contains(testUtils.cliente_C));
//		assertEquals (svidaX.getBeneficiarios().size(), 2);
//		assertTrue (svidaX.getBeneficiarios().contains(testUtils.cliente_B));
//		assertTrue (svidaX.getBeneficiarios().contains(testUtils.cliente_C));
//
//		svidaX = (SeguroVida) seguroService.recuperarSeguroBDPorCodigo(testUtils.svida_A2.getCodigo());
//		conxuntoC = (Set<Cliente>)seguroService.recuperarTodosBeneficiariosSeguroVidaBD(svidaX);
//		// Comprobamos que svidaX.beneficiarios() está dispoñible 
//		assertEquals (conxuntoC.size(), 1);
//		assertTrue (conxuntoC.contains(testUtils.cliente_B));
//		assertEquals (svidaX.getBeneficiarios().size(), 1);
//		assertTrue (svidaX.getBeneficiarios().contains(testUtils.cliente_B));
//
//		svidaX = (SeguroVida) seguroService.recuperarSeguroBDPorCodigo(testUtils.svida_E1.getCodigo());
//		conxuntoC = (Set<Cliente>)seguroService.recuperarTodosBeneficiariosSeguroVidaBD(svidaX);
//		// Comprobamos que svidaX.beneficiarios() está dispoñible 
//		assertEquals (conxuntoC.size(), 0);
//		assertEquals (svidaX.getBeneficiarios().size(),0);
//
//		// "recuperarTodosSegurosVidaBeneficiarioBD"
//		clienteX = clienteService.recuperarClienteBDPorLogin(testUtils.cliente_B.getLogin());
//		listaxeSV = seguroService.recuperarTodosSegurosVidaBeneficiarioBD(clienteX);
//		assertEquals (listaxeSV.size(),2);
//		assertEquals (listaxeSV.get(0), testUtils.svida_A2);
//		assertEquals (listaxeSV.get(1), testUtils.svida_A1);
//		
//		clienteX = clienteService.recuperarClienteBDPorLogin(testUtils.cliente_C.getLogin());
//		listaxeSV = seguroService.recuperarTodosSegurosVidaBeneficiarioBD(clienteX);
//		assertEquals (listaxeSV.size(),1);
//		assertEquals (listaxeSV.get(0), testUtils.svida_A1);
//
//		clienteX = clienteService.recuperarClienteBDPorLogin(testUtils.cliente_D.getLogin());
//		listaxeSV = seguroService.recuperarTodosSegurosVidaBeneficiarioBD(clienteX);
//		assertEquals (listaxeSV.size(),0);
//
//		// "recuperarTodosSegurosSenBeneficiariosBD"
//		listaxeSV = seguroService.recuperarTodosSegurosSenBeneficiariosBD();
//		assertEquals (listaxeSV.size(),2);
//		assertEquals (listaxeSV.get(0), testUtils.svida_E1);
//		assertEquals (listaxeSV.get(1), testUtils.svida_E2);
//
//		// "recuperarTodosClientesSenSegurosBD"
//		listaxeC = seguroService.recuperarTodosClientesSenSegurosBD();
//		assertEquals (listaxeC.size(),2);
//		assertEquals (listaxeC.get(0), testUtils.cliente_B);
//		assertEquals (listaxeC.get(1), testUtils.cliente_C);
//
//		// "recuperarTodosClientesSenSeguroVidaBD"
//		listaxeC = seguroService.recuperarTodosClientesSenSeguroVidaBD();
//		assertEquals (listaxeC.size(),3);
//		assertEquals (listaxeC.get(0), testUtils.cliente_B);
//		assertEquals (listaxeC.get(1), testUtils.cliente_C);
//		assertEquals (listaxeC.get(2), testUtils.cliente_D);
//
//		// "recuperarNumBeneficiariosClienteBD"
//		clienteX = clienteService.recuperarClienteBDPorLogin(testUtils.cliente_A.getLogin());
//		assertEquals (seguroService.recuperarNumBeneficiariosClienteBD(clienteX),new Long(2));
//
//		clienteX = clienteService.recuperarClienteBDPorLogin(testUtils.cliente_D.getLogin());
//		assertEquals (seguroService.recuperarNumBeneficiariosClienteBD(clienteX),new Long(0));
//
//		clienteX = clienteService.recuperarClienteBDPorLogin(testUtils.cliente_E.getLogin());
//		assertEquals (seguroService.recuperarNumBeneficiariosClienteBD(clienteX),new Long(0));
		

	}

	private void b_Test_Alta() {

		// As insercions con información correcta xa están probadas ao inicializar as variables de TestUtils
		// Aquí só pretendemos comprobar que as insercións con datos erróneos non se admiten

		
		Cliente clienteX;
		SeguroVida svidaX;
		SeguroFogar sfogarX;
		Boolean error;
		
		List<Seguro> listaxeS;		
		
		// Detección de duplicados
	
		try {
			error=false;
			clienteX = clienteService.recuperarClienteBDPorLogin(testUtils.cliente_B.getLogin());  // Usaremos cliente_B como subscritor
			seguroService.recuperarTodosSegurosSubscritorBD(clienteX);  // Actualmente non ten ningun seguro, pero non temos por que saber iso
			svidaX  = new SeguroVida (testUtils.svida_A1.getCodigo(), Timestamp.valueOf("2999-12-31 00:00:00.0"), new Float(0));
			clienteX.altaSeguro(svidaX);
			seguroService.altaNovoSeguroBD(svidaX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);
		
		try {
			error=false;
			clienteX = clienteService.recuperarClienteBDPorLogin(testUtils.cliente_B.getLogin());  // Usaremos cliente_B como subscritor
			seguroService.recuperarTodosSegurosSubscritorBD(clienteX);  // Actualmente non ten ningun seguro, pero non temos por que saber iso
			sfogarX  = new SeguroFogar (testUtils.sfogar_D.getCodigo(), Timestamp.valueOf("2999-12-31 00:00:00.0"), "nada");			
			sfogarX.altaCobertura("nada", new Float(0));
			clienteX.altaSeguro(sfogarX);		
			seguroService.altaNovoSeguroBD(sfogarX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);
		
		// Deteccion de codigo nulo
		
		try {
			error=false;
			clienteX = clienteService.recuperarClienteBDPorLogin(testUtils.cliente_B.getLogin());  // Usaremos cliente_B como subscritor
			seguroService.recuperarTodosSegurosSubscritorBD(clienteX);  // Actualmente non ten ningun seguro, pero non temos por que saber iso
			svidaX  = new SeguroVida (null, Timestamp.valueOf("2999-12-31 00:00:00.0"), new Float(0));
			clienteX.altaSeguro(svidaX);
			seguroService.altaNovoSeguroBD(svidaX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);
		
		try {
			error=false;
			clienteX = clienteService.recuperarClienteBDPorLogin(testUtils.cliente_B.getLogin());  // Usaremos cliente_B como subscritor
			seguroService.recuperarTodosSegurosSubscritorBD(clienteX);  // Actualmente non ten ningun seguro, pero non temos por que saber iso
			sfogarX  = new SeguroFogar (null, Timestamp.valueOf("2999-12-31 00:00:00.0"), "nada");			
			sfogarX.altaCobertura("nada", new Float(0));
			clienteX.altaSeguro(sfogarX);		
			seguroService.altaNovoSeguroBD(sfogarX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);
		
		// Deteccion de data de inicio nula
		
		try {
			error=false;
			clienteX = clienteService.recuperarClienteBDPorLogin(testUtils.cliente_B.getLogin());  // Usaremos cliente_B como subscritor
			seguroService.recuperarTodosSegurosSubscritorBD(clienteX);  // Actualmente non ten ningun seguro, pero non temos por que saber iso
			svidaX  = new SeguroVida ("nada", null, new Float(0));
			clienteX.altaSeguro(svidaX);
			seguroService.altaNovoSeguroBD(svidaX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);
		
		try {
			error=false;
			clienteX = clienteService.recuperarClienteBDPorLogin(testUtils.cliente_B.getLogin());  // Usaremos cliente_B como subscritor
			seguroService.recuperarTodosSegurosSubscritorBD(clienteX);  // Actualmente non ten ningun seguro, pero non temos por que saber iso
			sfogarX  = new SeguroFogar ("nada", null, "nada");			
			sfogarX.altaCobertura("nada", new Float(0));
			clienteX.altaSeguro(sfogarX);		
			seguroService.altaNovoSeguroBD(sfogarX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);
		
		// Deteccion de importe nulo (vida)
		
		try {
			error=false;
			clienteX = clienteService.recuperarClienteBDPorLogin(testUtils.cliente_B.getLogin());  // Usaremos cliente_B como subscritor
			seguroService.recuperarTodosSegurosSubscritorBD(clienteX);  // Actualmente non ten ningun seguro, pero non temos por que saber iso
			svidaX  = new SeguroVida ("nada", Timestamp.valueOf("2999-12-31 00:00:00.0"), null);
			clienteX.altaSeguro(svidaX);
			seguroService.altaNovoSeguroBD(svidaX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);
	
		// Deteccion de enderezo nulo (fogar)
		try {
			error=false;
			clienteX = clienteService.recuperarClienteBDPorLogin(testUtils.cliente_B.getLogin());  // Usaremos cliente_B como subscritor
			seguroService.recuperarTodosSegurosSubscritorBD(clienteX);  // Actualmente non ten ningun seguro, pero non temos por que saber iso
			sfogarX  = new SeguroFogar ("nada", Timestamp.valueOf("2999-12-31 00:00:00.0"), null);			
			sfogarX.altaCobertura("nada", new Float(0));
			clienteX.altaSeguro(sfogarX);		
			seguroService.altaNovoSeguroBD(sfogarX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);

		// Deteccion de subscritor nulo
		
		try {
			error=false;
			svidaX  = new SeguroVida ("nada", Timestamp.valueOf("2999-12-31 00:00:00.0"), new Float(0));
			seguroService.altaNovoSeguroBD(svidaX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);
		
		try {
			error=false;
			sfogarX  = new SeguroFogar ("nada", Timestamp.valueOf("2999-12-31 00:00:00.0"), "nada");			
			sfogarX.altaCobertura("nada", new Float(0));
			seguroService.altaNovoSeguroBD(sfogarX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);

		// Comprobamos que as insercions erroneas non funcionaron ----------------------------------------------------------
		
		listaxeS = seguroService.recuperarTodosSegurosBD();
		assertEquals (listaxeS.size(),6);
		
	}
	
	private void c_Test_Actualizacion() {

		// Primeiro comprobaremos que modificacions con datos erróneos non se permiten ==============================================================

		Cliente clienteX, clienteY;
		SeguroVida svidaX, svidaY;
		SeguroFogar sfogarX, sfogarY;
		Boolean error;
		
		List<Seguro> listaxeS;
		
		// Recuperar cliente E e seguros svidaE1 e sfogarE para probar modificacions 
		
		clienteX = clienteService.recuperarClienteBDPorLogin(testUtils.cliente_E.getLogin());
		seguroService.recuperarTodosSegurosSubscritorBD(clienteX);
		svidaX = (SeguroVida) clienteX.getSegurosSubscritos().get(1);
		sfogarX = (SeguroFogar) clienteX.getSegurosSubscritos().get(2);
		seguroService.recuperarTodosBeneficiariosSeguroVidaBD(svidaX);
		
		assertEquals (clienteX, testUtils.cliente_E);
		assertEquals (svidaX, testUtils.svida_E1);
		assertEquals (sfogarX, testUtils.sfogar_E);
		
		// Detectar código duplicado
		
		try {
			error=false;
			svidaX.setCodigo(testUtils.svida_A1.getCodigo());			
			seguroService.modificacionSeguroBD(svidaX);
		} catch (DataAccessException e){
			svidaX.setCodigo(testUtils.svida_E1.getCodigo());
			error=true;
		}
		assertTrue(error);

		try {
			error=false;
			sfogarX.setCodigo(testUtils.sfogar_D.getCodigo());			
			seguroService.modificacionSeguroBD(sfogarX);
		} catch (DataAccessException e){
			sfogarX.setCodigo(testUtils.sfogar_E.getCodigo());
			error=true;
		}
		assertTrue(error);
		// Verificar que o codigo quedou restaurado
		assertEquals (svidaX, testUtils.svida_E1);
		assertEquals (sfogarX, testUtils.sfogar_E);

		// Detectar codigo nulo
		
		try {
			error=false;
			svidaX.setCodigo(null);			
			seguroService.modificacionSeguroBD(svidaX);
		} catch (DataAccessException e){
			svidaX.setCodigo(testUtils.svida_E1.getCodigo());
			error=true;
		}
		assertTrue(error);

		try {
			error=false;
			sfogarX.setCodigo(null);			
			seguroService.modificacionSeguroBD(sfogarX);
		} catch (DataAccessException e){
			sfogarX.setCodigo(testUtils.sfogar_E.getCodigo());
			error=true;
		}
		assertTrue(error);
		// Verificar que o codigo quedou restaurado
		assertEquals (svidaX, testUtils.svida_E1);
		assertEquals (sfogarX, testUtils.sfogar_E);
		

		// Detectar data inicio nula
		
		try {
			error=false;
			svidaX.setDataInicio(null);			
			seguroService.modificacionSeguroBD(svidaX);
		} catch (DataAccessException e){
			svidaX.setDataInicio(testUtils.svida_E1.getDataInicio());
			error=true;
		}
		assertTrue(error);

		try {
			error=false;
			sfogarX.setDataInicio(null);			
			seguroService.modificacionSeguroBD(sfogarX);
		} catch (DataAccessException e){
			sfogarX.setDataInicio(testUtils.sfogar_E.getDataInicio());
			error=true;
		}
		assertTrue(error);
		assertEquals (svidaX.getDataInicio(), testUtils.svida_E1.getDataInicio());
		assertEquals (sfogarX.getDataInicio(), testUtils.sfogar_E.getDataInicio());


		// Deteccion de importe nulo (vida)
		
		try {
			error=false;
			svidaX.setImporte(null);
			seguroService.modificacionSeguroBD(svidaX);
		} catch (DataAccessException e){
			svidaX.setImporte(testUtils.svida_E1.getImporte());
			error=true;
		}
		assertTrue(error);
		assertEquals (svidaX.getImporte(), testUtils.svida_E1.getImporte());
	
		// Deteccion de enderezo nulo (fogar)
		try {
			error=false;
			sfogarX.setEnderezo(null);		
			seguroService.modificacionSeguroBD(sfogarX);
		} catch (DataAccessException e){
			sfogarX.setEnderezo(testUtils.sfogar_E.getEnderezo());
			error=true;
		}
		assertTrue(error);
		assertEquals (sfogarX.getEnderezo(), testUtils.sfogar_E.getEnderezo());

		// Detectar subscritor nulo
		
		try {
			error=false;
			svidaX.setSubscritor(null);			
			seguroService.modificacionSeguroBD(svidaX);
		} catch (DataAccessException e){
			svidaX.setSubscritor(testUtils.svida_E1.getSubscritor());
			error=true;
		}
		assertTrue(error);

		try {
			error=false;
			sfogarX.setSubscritor(null);			
			seguroService.modificacionSeguroBD(sfogarX);
		} catch (DataAccessException e){
			sfogarX.setSubscritor(testUtils.sfogar_E.getSubscritor());
			error=true;
		}
		assertTrue(error);
		assertEquals (svidaX.getSubscritor(), testUtils.svida_E1.getSubscritor());
		assertEquals (sfogarX.getSubscritor(), testUtils.sfogar_E.getSubscritor());

		// Agora comprobaremos modificacions con datos correctos ===================================================================================
		
		// 1) Primeiro probamos modificacions de datos básicos
		
		// 1a) Recuperar clientes e seguros para probar modificacions
		
		clienteX = clienteService.recuperarClienteBDPorLogin(testUtils.cliente_E.getLogin());
		clienteY = clienteService.recuperarClienteBDPorLogin(testUtils.cliente_C.getLogin());
		seguroService.recuperarTodosSegurosSubscritorBD(clienteX);
		seguroService.recuperarTodosSegurosSubscritorBD(clienteY);

		svidaX = (SeguroVida) clienteX.getSegurosSubscritos().get(1);
		sfogarX = (SeguroFogar) clienteX.getSegurosSubscritos().get(2);
		seguroService.recuperarTodosBeneficiariosSeguroVidaBD(svidaX);

		// 1b) Actualizacion de datos básicos: cambiamos valor de codigo, datainicio, datafin, importe 
		
		svidaX.setCodigo(svidaX.getCodigo() + "_modificado");
		svidaX.setDataInicio(Timestamp.valueOf("2019-1-1 0:00:00"));
		svidaX.setDataFin(Timestamp.valueOf("2020-1-1 0:00:00"));
		svidaX.setImporte(Float.valueOf("999999"));
		seguroService.modificacionSeguroBD(svidaX);
		
		svidaY=(SeguroVida)seguroService.recuperarSeguroBDPorCodigo(svidaX.getCodigo());
		assertEquals(svidaY.getCodigo(), svidaX.getCodigo());
		assertEquals(svidaY.getDataInicio(), svidaX.getDataInicio());
		assertEquals(svidaY.getDataFin(), svidaX.getDataFin());
		assertEquals(svidaY.getImporte(), svidaX.getImporte());
		
		sfogarX.setCodigo("[modificado]" + sfogarX.getCodigo());
		sfogarX.setDataInicio(Timestamp.valueOf("2019-1-1 0:00:00"));
		sfogarX.setDataFin(Timestamp.valueOf("2020-1-1 0:00:00"));
		sfogarX.setEnderezo("[modificado]" + sfogarX.getEnderezo());
		seguroService.modificacionSeguroBD(sfogarX);
		
		sfogarY=(SeguroFogar)seguroService.recuperarSeguroBDPorCodigo(sfogarX.getCodigo());
		assertEquals(sfogarY.getCodigo(), sfogarX.getCodigo());
		assertEquals(sfogarY.getDataInicio(), sfogarX.getDataInicio());
		assertEquals(sfogarY.getDataFin(), sfogarX.getDataFin());
		assertEquals(sfogarY.getEnderezo(), sfogarX.getEnderezo());

		// 1c) Restauramos os datos orixinais, sobreescribindo as modificacions anteriores
		
		seguroService.modificacionSeguroBD(testUtils.svida_E1);
		seguroService.modificacionSeguroBD(testUtils.sfogar_E);
		
		// 2) Agora probamos modificación de subscritor

		// 2a) Recuperar clientes e seguros para probar modificacions
		
		clienteX = clienteService.recuperarClienteBDPorLogin(testUtils.cliente_E.getLogin());
		clienteY = clienteService.recuperarClienteBDPorLogin(testUtils.cliente_C.getLogin());
		seguroService.recuperarTodosSegurosSubscritorBD(clienteX);
		seguroService.recuperarTodosSegurosSubscritorBD(clienteY);

		svidaX = (SeguroVida) clienteX.getSegurosSubscritos().get(1);
		sfogarX = (SeguroFogar) clienteX.getSegurosSubscritos().get(2);
		seguroService.recuperarTodosBeneficiariosSeguroVidaBD(svidaX);

		listaxeS = (List<Seguro>) seguroService.recuperarTodosSegurosSubscritorBD(clienteX);				
		assertEquals (listaxeS.size(), 3);
		listaxeS = (List<Seguro>) seguroService.recuperarTodosSegurosSubscritorBD(clienteY);				
		assertEquals (listaxeS.size(), 0);
		
		// 2b) Modificación de subscritor: asignamos a cliente_C os seguros de cliente_E

		clienteX.baixaSeguro(svidaX);
		clienteX.baixaSeguro(sfogarX);
		clienteY.altaSeguro(svidaX);
		clienteY.altaSeguro(sfogarX);

		seguroService.modificacionSeguroBD(svidaX);
		seguroService.modificacionSeguroBD(sfogarX);
		
		listaxeS = (List<Seguro>) seguroService.recuperarTodosSegurosSubscritorBD(clienteX);				
		assertEquals (listaxeS.size(), 1);
		listaxeS = (List<Seguro>) seguroService.recuperarTodosSegurosSubscritorBD(clienteY);				
		assertEquals (listaxeS.size(), 2);
		
		// 2c) Modificación de subscriptor: restauramos ao subscritor orixinal

		clienteY.baixaSeguro(svidaX);
		clienteY.baixaSeguro(sfogarX);
		clienteX.altaSeguro(svidaX);
		clienteX.altaSeguro(sfogarX);

		seguroService.modificacionSeguroBD(svidaX);
		seguroService.modificacionSeguroBD(sfogarX);

		listaxeS = (List<Seguro>) seguroService.recuperarTodosSegurosSubscritorBD(clienteX);				
		assertEquals (listaxeS.size(), 3);
		listaxeS = (List<Seguro>) seguroService.recuperarTodosSegurosSubscritorBD(clienteY);				
		assertEquals (listaxeS.size(), 0);

		// 3) Agora probamos modificación de beneficiarios de seguro de vida
		
		// 3a) Recuperamos seguro para probar

		svidaX = (SeguroVida) seguroService.recuperarSeguroBDPorCodigo(testUtils.svida_A1.getCodigo());
		seguroService.recuperarTodosBeneficiariosSeguroVidaBD(svidaX);

		assertEquals(svidaX.getBeneficiarios().size(), 2);
		assertFalse (svidaX.getBeneficiarios().contains(testUtils.cliente_D));

		// 3b) Engadimos un beneficiario
		
		svidaX.addBeneficiario(testUtils.cliente_D);
		seguroService.modificacionSeguroBD(svidaX);

		svidaX = (SeguroVida) seguroService.recuperarSeguroBDPorCodigo(testUtils.svida_A1.getCodigo());
		seguroService.recuperarTodosBeneficiariosSeguroVidaBD(svidaX);
		assertEquals(svidaX.getBeneficiarios().size(), 3);
		assertTrue (svidaX.getBeneficiarios().contains(testUtils.cliente_D));

		// 3c) Restauramos a situacion orixinal, eliminando ao beneficiario que engadimos antes

		svidaX.removeBeneficiario(testUtils.cliente_D);
		seguroService.modificacionSeguroBD(svidaX);

		svidaX = (SeguroVida) seguroService.recuperarSeguroBDPorCodigo(testUtils.svida_A1.getCodigo());
		seguroService.recuperarTodosBeneficiariosSeguroVidaBD(svidaX);
		assertEquals(svidaX.getBeneficiarios().size(), 2);
		assertFalse (svidaX.getBeneficiarios().contains(testUtils.cliente_D));

	
	}


	
	private void d_Test_Borrado() {


		Boolean error;
		SeguroVida svidaX, svidaY;
		SeguroFogar sfogarX, sfogarY;
		
		// Tentamos eliminar cliente que é beneficiario dun seguro (non se pode)
		
		try {
			error=false;
			clienteService.borradoClienteBD(testUtils.cliente_B);
		} catch (Exception e){
			error=true;
		}
		assertTrue(error);
		
		// Imos crear un seguro de vida con beneficiarios e un seguro de fogar para probar eliminacion directa
				
		svidaX = new SeguroVida("SVIDA_X", Timestamp.valueOf("2018-11-25 00:00:00.0"), new Float(6000000));
		testUtils.cliente_B.altaSeguro(svidaX);
		svidaX.addBeneficiario(testUtils.cliente_C);

		sfogarX = new SeguroFogar("SFOGAR_X", Timestamp.valueOf("2018-12-25 00:00:00.0"), new String("Rua Desconcerto 21, 5º A, A Coruña")); 
		sfogarX.altaCobertura("Inundacion", new Float(300000));
		testUtils.cliente_B.altaSeguro(sfogarX);

		// Ningun dos seguros é persistente ainda: comprobamos que non se poden eliminar

		try {
			error=false;
			seguroService.borradoSeguroBD(svidaX);
		} catch (Exception e){
			error=true;
		}
		assertTrue(error);

		try {
			error=false;
			seguroService.borradoSeguroBD(sfogarX);
		} catch (Exception e){
			error=true;
		}
		assertTrue(error);
		
		// Gravamos seguros na BD
		
		seguroService.altaNovoSeguroBD(svidaX);
		seguroService.altaNovoSeguroBD(sfogarX);
		
		svidaY = (SeguroVida) seguroService.recuperarSeguroBDPorCodigo(svidaX.getCodigo());
		sfogarY = (SeguroFogar) seguroService.recuperarSeguroBDPorCodigo(sfogarX.getCodigo());
		seguroService.recuperarSubscritorSeguroBD(svidaY);
		seguroService.recuperarSubscritorSeguroBD(sfogarY);
		seguroService.recuperarTodosBeneficiariosSeguroVidaBD(svidaY);

		assertEquals (svidaY, svidaX);
		assertEquals (sfogarY, sfogarX);
		assertEquals (svidaY.getSubscritor(), testUtils.cliente_B);
		assertEquals (sfogarY.getSubscritor(), testUtils.cliente_B);
		assertTrue (svidaY.getBeneficiarios().contains(testUtils.cliente_C));
		
		// Eliminamos os seguros da BD a pesares de toda esa informacion
		
		seguroService.borradoSeguroBD(svidaY);
		seguroService.borradoSeguroBD(sfogarY);

		assertNull (seguroService.recuperarSeguroBDPorCodigo(svidaX.getCodigo()));
		assertNull (seguroService.recuperarSeguroBDPorCodigo(sfogarX.getCodigo()));

		// Restauramos situación orixinal en memoria
		
		testUtils.cliente_B.baixaSeguro(svidaX);
		testUtils.cliente_B.baixaSeguro(sfogarX);

		
	}
}
