package es.udc.fi.lbd.monuzz.id.seguros;

import static org.junit.Assert.*;

import java.util.List;

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
public class TestClientes {
	
	private Logger log = Logger.getLogger("seguros");

	@Autowired
	private TestUtils testUtils;

	@Autowired
	private ClienteService clienteService;
	
	@Before
	public void setUp() throws Exception {
		//Creamos usuarios de proba iniciais
		log.info ("Creando clientes iniciais para caso de proba: " + this.getClass().getName() + " ===========================================");
		testUtils.creaSetClientesProba(); 
		log.info ("Foron creados con éxito os datos de clientes iniciais para o caso de proba: " + this.getClass().getName() + " =============");
	}

	@After
	public void tearDown() throws Exception {
		log.info ("Eliminando clientes iniciais para caso de proba: " + this.getClass().getName() + " ========================================");
		testUtils.eliminaSetClientesProba(); 
		log.info ("Foron eliminados con éxito os datos de clientes iniciais para o caso de proba: " + this.getClass().getName() + " ==========");
	}
	
	@Test
	public void testCompleto() {

		log.info ("INICIANDO [Test_Consulta] en: " + this.getClass().getName() + " ===========================================================");
		a_Test_Consulta();
		log.info ("INICIANDO [Test_Alta] en: " + this.getClass().getName() + " ===============================================================");
		b_Test_Alta();
		log.info ("INICIANDO [Test_Actualizacion] en: " + this.getClass().getName() + " ======================================================");
		c_Test_Actualizacion();
		log.info ("INICIANDO [Test_Borrado] en: " + this.getClass().getName() + " ============================================================");
		d_Test_Borrado();

	}
	
	private void a_Test_Consulta() {
		
		
		List<Cliente> listaxeC;

		// Probamos recuperación básica -------------------------------------------------------------------------------------

		
		assertEquals(testUtils.cliente_A, clienteService.recuperarClienteBDPorId(testUtils.cliente_A.getIdCliente()));
		assertEquals(testUtils.cliente_A, clienteService.recuperarClienteBDPorLogin(testUtils.cliente_A.getLogin()));

		listaxeC = clienteService.recuperarTodosClientesBD();
		assertEquals (listaxeC.size(), 5);
		assertEquals (listaxeC.get(0),testUtils.cliente_E);
		assertEquals (listaxeC.get(1),testUtils.cliente_D);
		assertEquals (listaxeC.get(2),testUtils.cliente_C);
		assertEquals (listaxeC.get(3),testUtils.cliente_B);
		assertEquals (listaxeC.get(4),testUtils.cliente_A);

	}
	
	private void b_Test_Alta() {

		Cliente clienteX;
		List<Cliente> listaxeC;
		Boolean error;
		
		// Deteccion duplicados -------------------------------------------------------------------------------------
				

		//login
		try {
			error=false;
			clienteX  = new Cliente (testUtils.cliente_A.getLogin(), "nada", "nada");
			clienteService.altaNovoClienteBD(clienteX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);

		//Deteccion login nulo -------------------------------------------------------------------------------------
		
		try {
			error=false;
			clienteX  = new Cliente (null, "nada", "nada");
			clienteService.altaNovoClienteBD(clienteX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);		
		
		//Deteccion password nulo -------------------------------------------------------------------------------------
		
		try {
			error=false;
			clienteX  = new Cliente ("nada", null, "nada");
			clienteService.altaNovoClienteBD(clienteX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);
		
		//Deteccion nome nulo -------------------------------------------------------------------------------------
		
		try {
			error=false;
			clienteX  = new Cliente ("nada", "nada", null);
			clienteService.altaNovoClienteBD(clienteX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);
		
		// Comprobamos que as insercions erroneas non funcionaron ----------------------------------------------------------
		
		listaxeC = clienteService.recuperarTodosClientesBD();
		assertEquals (listaxeC.size(),5);
	
	}
	
	
	
	private void c_Test_Actualizacion() {

		Cliente clienteX, clienteY;
		Boolean error;
		
		// Creamos un cliente para probar 

		clienteX  = new Cliente ("clientex", "clientex", "CLIENTEX");
		clienteService.altaNovoClienteBD(clienteX);
		
		// Comprobar actualizacion login duplicado
		
		try {
			error=false;
			clienteX.setLogin(testUtils.cliente_A.getLogin());
			clienteService.modificacionClienteBD(clienteX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);
		
		// Comprobar actualizacion login non duplicado

		clienteX.setLogin("cliente_x");
		clienteService.modificacionClienteBD(clienteX);
		clienteY = (Cliente) clienteService.recuperarClienteBDPorLogin("cliente_x");
		assertEquals(clienteX, clienteY);
		
		// Comprobar actualizacion dos outros datos (contrasinal e nome)
		
		clienteX.setPassword("cliente_x");
		clienteX.setNome("CLIENTE_X");
		clienteService.modificacionClienteBD(clienteX);
		clienteY = (Cliente) clienteService.recuperarClienteBDPorLogin("cliente_x");
		assertEquals(clienteX.getNome(), clienteY.getNome());
		assertEquals(clienteX.getPassword(), clienteY.getPassword());

		// Comprobar actualizacion nulos

		// Nome
		
		// Login
		try {
			clienteX.setLogin(null);
			clienteService.modificacionClienteBD(clienteX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);
		clienteX = (Cliente) clienteService.recuperarClienteBDPorLogin("cliente_x");
		assertNotNull (clienteX);
		assertNotNull (clienteX.getLogin());
		
		// Password
		try {
			clienteX.setPassword(null);
			clienteService.modificacionClienteBD(clienteX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);
		clienteX = (Cliente) clienteService.recuperarClienteBDPorLogin("cliente_x");
		assertNotNull (clienteX);
		assertNotNull (clienteX.getPassword());

		// Nome
		try {
			error=false;
			clienteX.setNome(null);
			clienteService.modificacionClienteBD(clienteX);
		} catch (DataAccessException e){
			error=true;
		}
		assertTrue(error);
		clienteX = (Cliente) clienteService.recuperarClienteBDPorLogin("cliente_x");
		assertNotNull (clienteX);
		assertNotNull (clienteX.getNome());
	
	}

	
	private void d_Test_Borrado() {

		// Os borrados con información correcta xa se proban ao eliminar as variables de TestUtils
		// Aquí só pretendemos comprobar que os borrados con datos erróneos non se admiten

		Cliente clienteX;
		Boolean error;
		
		// Intento de borrado dun cliente que non existe (id persistente nulo)

		clienteX = new Cliente ("clienteProba", "clienteproba", "CLIENTE_PROBA");
		try {
			error=false;
			clienteService.borradoClienteBD(clienteX);
		} catch (Exception e){
			error=true;
		}
		assertTrue(error);
		
		// Eliminamos usuarios de proba (sen seguros asociados)

		assertEquals (clienteService.recuperarTodosClientesBD().size(), 6);
		
		clienteX = (Cliente) clienteService.recuperarClienteBDPorLogin("cliente_x");
		clienteService.borradoClienteBD(clienteX);

		assertNull (clienteService.recuperarClienteBDPorLogin("clienteX"));
		assertEquals (clienteService.recuperarTodosClientesBD().size(), 5);

	}
	
}
