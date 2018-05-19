package es.udc.fi.lbd.monuzz.id.seguros.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;



//@SuppressWarnings("unused")

@Entity
@Table(name = "CLIENTE")
public class Cliente {
	
	private Long idCliente;	
	private String login;
	private String password;
	private String nome;
	private List<Seguro> segurosSubscritos; 

	
	// Chave natural: login
	// Atributos obrigatorios: login, password, nome
	// Atributos unicos: login
	
	// OUTRAS RESTRICCIONS (A DEFINIR NA BD) 
	// 		A listaxe de seguros subscritos ESTA ORDENADA por data de inicio (o mais recente ao PRINCIPIO)
	
	
	private Cliente() {}

	public Cliente(String login, String password, String nome) {
		this.login = login;
		this.password = password;
		this.nome = nome;
		this.segurosSubscritos = new ArrayList<Seguro>();
	}


	// GETTERS
	
	@Id
	@SequenceGenerator(name = "cliente_Id", sequenceName = "id_cliente_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_Id")
	@Column
	public Long getIdCliente() {
		return idCliente;
	}
	
	@Column(unique = true, nullable = false)
	public String getLogin() {
		return this.login;
	}
	
	@Column(nullable=false)
	public String getPassword() {
		return this.password;
	}
	
	@Column(nullable=false)
	public String getNome() {
		return this.nome;
	}   
	
	@OneToMany(mappedBy="subscritor",cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	public List<Seguro> getSegurosSubscritos() {
		return this.segurosSubscritos;
	}

	
	// SETTERS
	
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setSegurosSubscritos(List<Seguro> seguros) {
		this.segurosSubscritos = seguros;
	}
	
    // OUTROS 
	
	// Método de conveniencia para dar de alta un novo seguro subscrito polo cliente
	// Insertamos sempre ao principio da lista
	// (SIMPLIFICACION, supoñemos que a data de inicio é sempre a actual, e polo tanto 
	// o novo seguro é o mais recente)
	// Nota: en memoria, NON EN DISCO
	
	public void altaSeguro(Seguro seguro) {
		if (seguro==null)
			throw new RuntimeException("Erro: faltan os datos do seguro a dar de alta");			
		// Como é unha listaxe, comprobar duplicados
		if (this.segurosSubscritos.contains(seguro)) 
			throw new RuntimeException("Erro: o cliente xa inclúe o seguro [" + seguro.getCodigo() + "] na súa listaxe");
		if (seguro.getSubscritor() != null)
			throw new RuntimeException("Erro: o seguro [" + seguro.getCodigo() + "] foi subscrito por outro cliente: " + seguro.getSubscritor().getNome());			
		
		seguro.setSubscritor(this);
		this.segurosSubscritos.add(0,seguro);
		
	}
	
	// Método de conveniencia para dar de baixa un seguro do cliente
	// Nota: en memoria, NON EN DISCO

	public void baixaSeguro(Seguro seguro) {
		if (seguro==null)
			throw new RuntimeException("Erro: faltan os datos do seguro a dar de baixa");			
		if (!seguro.getSubscritor().equals(this))
			throw new RuntimeException("Erro: o seguro [" + seguro.getCodigo() + "] NON está vinculado actualmente ao cliente");
		if (!this.segurosSubscritos.remove(seguro))
			throw new RuntimeException("Erro: o seguro [" + seguro.getCodigo() + "] NON está vinculado actualmente ao cliente");
		seguro.setSubscritor(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", login=" + login + ", password=" + password + ", nome=" + nome
				+ "]";
	}


	

}