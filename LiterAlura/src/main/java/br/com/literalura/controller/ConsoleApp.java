package br.com.literalura.controller;

import br.com.literalura.client.GutendexClient;
import br.com.literalura.model.Livro;
import br.com.literalura.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class ConsoleApp implements CommandLineRunner {
    private final GutendexClient client;
    private final LivroService service;
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) {
        int opcao;
        do {
            System.out.println("\n===== LITERALURA =====");
            System.out.println("1 - Buscar livro na API");
            System.out.println("2 - Adicionar livro manualmente");
            System.out.println("3 - Ver catálogo pessoal");
            System.out.println("4 - Atualizar livro");
            System.out.println("5 - Remover livro");
            System.out.println("6 - Sair");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> buscarLivro();
                case 2 -> adicionarLivroManual();
                case 3 -> listarCatalogo();
                case 4 -> atualizarLivro();
                case 5 -> removerLivro();
            }
        } while (opcao != 6);
    }

    private void buscarLivro() {
        System.out.print("Digite o título: ");
        String titulo = scanner.nextLine();
        var livros = client.buscarLivros(titulo);
        livros.forEach(l -> System.out.println("- " + l.getTitle() + " (" + l.getAuthors().get(0).getName() + ")"));
    }

    private void adicionarLivroManual() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Idioma: ");
        String idioma = scanner.nextLine();
        System.out.print("Ano publicação: ");
        int ano = Integer.parseInt(scanner.nextLine());
        Livro livro = new Livro(null, titulo, idioma, autor, ano, null, "Desejo Ler");
        service.salvarLivro(livro);
    }

    private void listarCatalogo() {
        var livros = service.listarCatalogo();
        livros.forEach(System.out::println);
    }

    private void atualizarLivro() {
        System.out.print("ID do livro: ");
        Long id = Long.parseLong(scanner.nextLine());
        var livroOpt = service.buscarPorId(id);
        if (livroOpt.isPresent()) {
            Livro livro = livroOpt.get();
            System.out.print("Nova nota pessoal (0-10): ");
            livro.setNotaPessoal(Double.parseDouble(scanner.nextLine()));
            System.out.print("Novo status (Lido, Lendo, Desejo Ler): ");
            livro.setStatus(scanner.nextLine());
            service.atualizarLivro(livro);
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    private void removerLivro() {
        System.out.print("ID do livro: ");
        Long id = Long.parseLong(scanner.nextLine());
        service.removerLivro(id);
    }
}
