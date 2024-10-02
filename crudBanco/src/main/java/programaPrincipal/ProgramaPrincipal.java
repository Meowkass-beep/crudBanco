package programaPrincipal;

import java.util.List;

import org.apache.log4j.BasicConfigurator;

import console.Console;
import usuario.GerenciarUsuario;
import usuario.Usuario;

public class ProgramaPrincipal {

	private Console console;
	private final static int CADASTRAR = 1;
	private final static int EDITAR = 2;
	private final static int LISTAR = 3;
	private final static int REMOVER = 4;
	private final static int SAIR = 9;
	private GerenciarUsuario gerUsuario;

	public ProgramaPrincipal() {
		console = new Console();
		gerUsuario = new GerenciarUsuario();
	}

	public static void main(String[] args) {
		BasicConfigurator.configure();
		new ProgramaPrincipal().executar();
	}

	private void executar() {
		int opcao = 0;

		do {
			mostrarMenu();

			opcao = console.readInt();

			if (opcao == CADASTRAR) {
				System.out.println("Cadastrar usuário");
				cadastrar();
			} else if (opcao == EDITAR) {
				System.out.println("Editar usuário");
				editar();
			} else if (opcao == LISTAR) {
				System.out.println("Listar usuários");
				listar();
			} else if (opcao == REMOVER) {
				System.out.println("Remover usuário");
				remover();
			}

		} while (opcao != SAIR);
		gerUsuario.fecharConexao();
	}

	private void editar() {
		console.printMessage("Digite o ID para editar: ");

		int id = console.readInt();

		Usuario usuario = gerUsuario.findById(id);

		console.printMessage("Digite o novo nome: ");
		String novoNome = console.readLine();
		console.printMessage("Digite o novo e-mail: ");
		String novoEmail = console.readLine();
		console.printMessage("Digite o novo CPF: ");
		long novoCpf = console.readLong();
		console.printMessage("Digite a nova senha: ");
		String novaSenha = console.readLine();

		usuario.setNome(novoNome);
		usuario.setEmail(novoEmail);
		usuario.setCpf(novoCpf);
		usuario.setSenha(novaSenha);

		gerUsuario.update(usuario);

		console.printMessage("Usuário atualizado com sucesso");
	}

	private void remover() {
		console.printMessage("Digite o id para remover: ");

		int idRemover = console.readInt();

		Usuario usuario = gerUsuario.findById(idRemover);
		if (usuario == null) {
			console.printMessage("ID informado não existe");
		} else {
			console.printMessage("ID: " + usuario.getId());
			console.printMessage("Nome: " + usuario.getNome());
			console.printMessage("E-mail: " + usuario.getEmail());
			console.printMessage("Nome: " + usuario.getCpf());
			console.printMessage("E-mail: " + usuario.getSenha());

			console.printMessage("Confirma a exclusão: 0 - sim, 1 - não");
			int opcao = console.readInt();

			if (opcao == 0) {
				gerUsuario.remove(usuario);
				console.printMessage("OK, removido com sucesso");
			} else {
				console.printMessage("Cancelar");
			}

		}
	}

	private void listar() {
		List<Usuario> lista = gerUsuario.list();
		for (Usuario usuario : lista) {
			console.printMessage("Id: " + usuario.getId() + "\nNome: " + usuario.getNome() + "\nEmail: "
					+ usuario.getEmail() + "\nCpf: " + usuario.getCpf() + "\nSenha: " + usuario.getSenha());
		}
	}

	private void cadastrar() {

		console.printMessage("Digite o seu nome: ");
		String novoNome = console.readLine();
		console.printMessage("Digite o seu e-mail: ");
		String novoEmail = console.readLine();
		console.printMessage("Digite o seu CPF: ");
		long novoCpf = console.readLong();
		console.printMessage("Digite a sua senha: ");
		String novaSenha = console.readLine();

		Usuario usuario = new Usuario();

		usuario.setNome(novoNome);
		usuario.setEmail(novoEmail);
		usuario.setCpf(novoCpf);
		usuario.setSenha(novaSenha);

		gerUsuario.create(usuario);
		console.printMessage("Usuário cadastro com sucesso");
	}

	private void mostrarMenu() {
		System.out.println("\n------ SUPER CRUD ------\n");
		System.out.println("1 - Cadastrar usuário");
		System.out.println("2 - Editar usuário");
		System.out.println("3 - Listar usuários");
		System.out.println("4 - Remover usuário");
		System.out.println("9 - Sair");
	}

}
