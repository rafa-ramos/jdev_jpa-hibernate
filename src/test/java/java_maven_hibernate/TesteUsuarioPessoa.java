package java_maven_hibernate;

import java.util.List;


import org.junit.Test;

import dao.DaoGeneric;
import java_maven_hibernate.HibernateUtil;
import model.Telefone;
import model.UsuarioPessoa;

public class TesteUsuarioPessoa {

	@Test
	public void testeHibernateUtil() {
		HibernateUtil.getEntityManager();

	}

	@Test
	public void testeSalvar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = new UsuarioPessoa("Rafael", "Teste", "rafael@spk.br", "admin", "admin", 20);

		System.out.println(pessoa.toString());
		daoGeneric.salvar(pessoa);
	}

	@Test
	public void testeBuscar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(1);

		pessoa = daoGeneric.pesquisar(pessoa);

		System.out.println(pessoa.toString());
	}

	@Test
	public void testeBuscar2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGeneric.pesquisar(1, UsuarioPessoa.class);

		System.out.println(pessoa.toString());
	}

	@Test
	public void testeUpdate() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGeneric.pesquisar(1, UsuarioPessoa.class);

		pessoa.setEmail("novoemail@123.com");
		pessoa.setNome("Novo");
		pessoa.setSobrenome("Sobrenome");

		pessoa = daoGeneric.updateMerge(pessoa);

		System.out.println(pessoa.toString());
	}

	@Test
	public void testeDelete() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGeneric.pesquisar(1, UsuarioPessoa.class);

		daoGeneric.remover(pessoa);
	}

	@Test
	public void testeConsulta() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		List<UsuarioPessoa> list = daoGeneric.listar(UsuarioPessoa.class);

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("----------------");
		}

	}

	@Test
	public void testeQueryList() {

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery("FROM UsuarioPessoa WHERE nome = 'Rafael'").
				getResultList();
	
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("----------------");
		}

	}
	
	@Test
	public void testeQueryListMaxResult() {

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery("FROM UsuarioPessoa order by id").
				setMaxResults(3).getResultList();
	
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("----------------");
		}

	}
	
	@Test
	public void testeQueryListParameter() {

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery("FROM UsuarioPessoa where nome = "
				+ ":n or sobrenome = :s").
				setParameter("n", "Rafael").
				setParameter("s", "").getResultList();
	
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("----------------");
		}

	}
	
	@Test
	public void testeQuerySomaIdade() {

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		Long somaIdade = (Long) daoGeneric.getEntityManager().createQuery("SELECT SUM(idade) "
				+ "FROM UsuarioPessoa").getSingleResult(); 
		
		System.out.println("A soma das idades é = " + somaIdade);

	}
	
	@Test
	public void testeNameQuery1() {

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		/* 
		 * Cosulta FindAll definida na entidade UsuarioPessoa - anotação @NamedQueries*/
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.FindAll").getResultList();
		
		for (UsuarioPessoa usuario : list) {
			System.out.println(usuario);
		}

	}
	
	@Test
	public void testeNameQuery2() {

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		/* Cosulta GetByName definida na entidade UsuarioPessoa - anotação @NamedQueries */
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.GetByName").
				setParameter("nome","Teste").getResultList();
		
		for (UsuarioPessoa usuario : list) {
			System.out.println(usuario);
		}

	}
	
	@Test
	public void testeSalvarTelefone() {
		DaoGeneric daoGeneric = new DaoGeneric();

		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(1, UsuarioPessoa.class);

		Telefone telefone = new Telefone();
		telefone.setNumero("999999999");
		telefone.setTipo("Celular");
		telefone.setUsuarioPessoa(pessoa);
		
		daoGeneric.salvar(telefone);
	}
	
	@Test
	public void testePesquisarTelefone() {
		DaoGeneric daoGeneric = new DaoGeneric();

		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(1, UsuarioPessoa.class);

		for (Telefone fone : pessoa.getTelefones()) {
			System.out.println(fone.getNumero() + " -- " + fone.getTipo());
		}
	}

	
}
