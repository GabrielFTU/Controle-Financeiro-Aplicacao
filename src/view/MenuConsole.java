package view;

import service.GerenciadorFinanceiro;
import model.Transacao;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuConsole {
    private static GerenciadorFinanceiro gerenciadorFinanceiro = new GerenciadorFinanceiro();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            mostrarMenu(); // Exibe o menu
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    adicionarTransacao(); // Adicionar transação
                    break;
                case 2:
                    gerenciadorFinanceiro.listarTransacoes(); // Listar transações
                    break;
                case 3:
                    System.out.println("Saldo total: R$ " + gerenciadorFinanceiro.calcularSaldo()); // Calcular saldo
                    break;
                case 4:
                    removerTransacao(); // Remover transação
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    // Exibe o menu
    public static void mostrarMenu() {
        System.out.println("\n--- Menu de Controle de Finanças ---");
        System.out.println("1. Adicionar Transação");
        System.out.println("2. Listar Transações");
        System.out.println("3. Calcular Saldo");
        System.out.println("4. Remover Transação");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    // Adiciona uma transação
    public static void adicionarTransacao() {
        System.out.print("Tipo (Receita/Despesa): ");
        String tipo = scanner.nextLine();
        if (!tipo.equalsIgnoreCase("Receita") && !tipo.equalsIgnoreCase("Despesa")) {
            System.out.println("Tipo inválido. Por favor, insira 'Receita' ou 'Despesa'. ");
            return;
        }

        System.out.print("Valor: R$ ");
        double valor;
        while (true) {
            try {
                valor = scanner.nextDouble();
                if (valor <= 0) {
                    System.out.println("Valor deve ser maior que zero.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Por favor, insira um valor numérico válido.");
                scanner.next();
            }
        }
        scanner.nextLine(); // Limpar buffer

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        System.out.print("Data (formato yyyy-mm-dd): ");
        String dataStr = scanner.nextLine();
        LocalDate data = LocalDate.parse(dataStr);

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        Transacao transacao = new Transacao(tipo, valor, categoria, data, descricao);
        gerenciadorFinanceiro.adicionarTransacoes(transacao); // Adicionando transação ao gerenciador

        System.out.println("Transação adicionada com sucesso!");
    }

    // Remove uma transação
    public static void removerTransacao() {
        System.out.print("Informe o índice da transação a ser removida: ");
        int indice = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        gerenciadorFinanceiro.removerTransacao(indice); // Removendo transação do gerenciador
    }
}
