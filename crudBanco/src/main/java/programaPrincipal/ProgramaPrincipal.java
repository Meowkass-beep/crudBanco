package programaPrincipal;

import java.util.List;

import org.apache.log4j.BasicConfigurator;

import console.Console;
import usuario.GerenciarUsuario;
import usuario.Usuario;
import usuario.endereco.Endereco;

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
				console.printMessage("Cadastrar usuário");
				cadastrar();
			} else if (opcao == EDITAR) {
				console.printMessage("Editar usuário");
				editar();
			} else if (opcao == LISTAR) {
				console.printMessage("Listar usuários");
				listar();
			} else if (opcao == REMOVER) {
				console.printMessage("Remover usuário");
				remover();
			}

		} while (opcao != SAIR);
		gerUsuario.fecharConexao();
	}

	private void lerDadosUsuario(Usuario usuario) {
		console.printMessage("Digite o seu nome: ");
		String novoNome = console.readLine();
		console.printMessage("Digite o seu e-mail: ");
		String novoEmail = console.readLine();
		console.printMessage("Digite o seu CPF: ");
		long novoCpf = console.readLong();
		console.printMessage("Digite a sua senha: ");
		String novaSenha = console.readLine();

		usuario.setNome(novoNome);
		usuario.setEmail(novoEmail);
		usuario.setCpf(novoCpf);
		usuario.setSenha(novaSenha);

		lerDadosEndereco(usuario);
	}
	
	private void lerDadosEndereco(Usuario usuario) {
		Endereco endereco = usuario.getEndereco();

		if (endereco == null) {
			endereco = new Endereco();
		}

		String logradouro = console.readLine("Digite o logradouro:");
		String cep = console.readLine("Digite o CEP:");
		String numero = console.readLine("Digite o número:");

		endereco.setCep(cep);
		endereco.setLogradouro(logradouro);
		endereco.setNumero(numero);
		
		usuario.setEndereco(endereco);
		
	}

	private void editar() {

		int id = console.readInt("Digite o ID para editar: ");

		Usuario usuario = gerUsuario.findById(id);

		String novoNome = console.readLine("Digite o novo nome: ");
		String novoEmail = console.readLine("Digite o novo e-mail: ");
		long novoCpf = console.readLong("Digite o novo CPF: ");
		String novaSenha = console.readLine("Digite a nova senha: ");

		usuario.setNome(novoNome);
		usuario.setEmail(novoEmail);
		usuario.setCpf(novoCpf);
		usuario.setSenha(novaSenha);

		gerUsuario.update(usuario);

		console.printMessage("Usuário atualizado com sucesso");
	}

	private void remover() {
		int idRemover = console.readInt("Digite o id para remover: ");

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
			console.printMessage("Id: " + usuario.getId() + 
					"\nNome: " + usuario.getNome() + 
					"\nEmail: " + usuario.getEmail() + 
					"\nCpf: " + usuario.getCpf() + 
					"\nSenha: " + usuario.getSenha());
		}
	}

	private void cadastrar() {
		Usuario usuario = new Usuario();

		lerDadosUsuario(usuario);

		gerUsuario.create(usuario);
		console.printMessage("Usuário cadastro com sucesso");
	}

	private void mostrarMenu() {
		console.printMessage("\n------ SUPER CRUD ------\n");
		console.printMessage("1 - Cadastrar usuário");
		console.printMessage("2 - Editar usuário");
		console.printMessage("3 - Listar usuários");
		console.printMessage("4 - Remover usuário");
		console.printMessage("9 - Sair");
	}

}
