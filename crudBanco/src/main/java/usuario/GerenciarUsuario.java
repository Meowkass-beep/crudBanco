package usuario;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class GerenciarUsuario {
	private Usuario usuario;
	private EntityManager em;
	private EntityManagerFactory emf;

	public GerenciarUsuario() {
		emf = Persistence.createEntityManagerFactory("hiber");
		em = emf.createEntityManager();
	}

	public void create(Usuario usuario) {
		em.getTransaction().begin();

		em.persist(usuario);

		em.getTransaction().commit();
	}

	public Usuario findById(int id) {
		Usuario usuario = em.find(Usuario.class, id);
		return usuario;
	}

	public void update(Usuario usuario) {
		em.getTransaction().begin();

		em.merge(usuario);

		em.getTransaction().commit();
	}

	public void remove(Usuario usuario) {
		em.getTransaction().begin();

		em.remove(usuario);

		em.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> list() {
		String sqlOO = "from Usuario";
		Query hql = em.createQuery(sqlOO);

		List<Usuario> usuarios = hql.getResultList();
		return usuarios;
	}

	public void fecharConexao() {
		em.close();
		emf.close();
	}

}
