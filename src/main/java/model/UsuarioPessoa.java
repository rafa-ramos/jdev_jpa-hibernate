package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name = "UsuarioPessoa.FindAll", query = "SELECT u FROM UsuarioPessoa u"),
	@NamedQuery(name = "UsuarioPessoa.GetByName", query = "SELECT u FROM UsuarioPessoa u WHERE u.nome = :nome")
})
public class UsuarioPessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nome;
	private String sobrenome;
	private String email;
	private String login;
	private String senha;
	private Integer idade;
	
	/* Um Usuario pode ter muitos Telefones*/
	@OneToMany(mappedBy = "usuarioPessoa")
	private List<Telefone> telefones;
	
	public UsuarioPessoa() {
		super();
	}
	
	public UsuarioPessoa(Integer id, String nome, String sobrenome, String email, String login, String senha,
			Integer idade) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.idade = idade;
	}
	
	public UsuarioPessoa(String nome, String sobrenome, String email, String login, String senha,
			Integer idade) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.idade = idade;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	@Override
	public String toString() {
		return "UsuarioPessoa [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", email=" + email
				+ ", login=" + login + ", senha=" + senha + ", idade=" + idade + ", getId()=" + getId() + ", getNome()="
				+ getNome() + ", getSobrenome()=" + getSobrenome() + ", getEmail()=" + getEmail() + ", getLogin()="
				+ getLogin() + ", getSenha()=" + getSenha() + ", getIdade()=" + getIdade() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
