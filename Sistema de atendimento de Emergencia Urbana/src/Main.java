import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        SistemaChamados sistema = new SistemaChamados();
        Scanner sc = new Scanner(System.in);

        int op = -1;

        do {
            System.out.println("\n==================================================");
            System.out.println("   SISTEMA DE ATENDIMENTO DE EMERGÊNCIA URBANA ");
            System.out.println("==================================================");
            System.out.println("1 - Novo Chamado");
            System.out.println("2 - Atender Chamado");
            System.out.println("3 - Finalizar Chamado");
            System.out.println("4 - Relatórios");
            System.out.println("0 - Sair");
            System.out.println("==================================================");

            System.out.print("Digite o número da opção desejada: ");

            String entrada = sc.next();

            try {
                op = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite novamente.");
                continue;
            }

            if (op < 0 || op > 4) {
                System.out.println("Opção inválida! Digite novamente.");
                continue;
            }

            switch (op) {

                case 1:
                    System.out.println("\n--- NOVO CHAMADO ---");

                    int id;

                    while (true) {
                        System.out.print("ID: ");

                        try {
                            id = sc.nextInt();
                            sc.nextLine();
                        } catch (Exception e) {
                            System.out.println("Digite apenas números!");
                            sc.nextLine(); // limpa erro
                            continue;
                        }

                        if (id < 0) {
                            System.out.println("ID inválido! Digite positivo.");
                            continue;
                        }

                        if (sistema.idExiste(id)) {
                            System.out.println("ID já está em uso! Digite outro.");
                        } else {
                            break;
                        }
                    }

                    System.out.print("Bairro: ");
                    String bairro = sc.nextLine();

                    System.out.print("Descrição: ");
                    String desc = sc.nextLine();

                    int urg;

                    do {
                        System.out.println("\n--- TABELA DE URGÊNCIA ---");
                        System.out.println("Tipo         | Nível de Urgência");
                        System.out.println("Comum        | 1, 2 ou 3");
                        System.out.println("Emergência   | 4 ou 5");

                        System.out.print("\nDigite o nível de urgência (1 a 5): ");

                        try {
                            urg = sc.nextInt();
                        } catch (Exception e) {
                            System.out.println("Digite apenas números!");
                            sc.nextLine();
                            urg = -1;
                        }

                        if (urg < 1 || urg > 5) {
                            System.out.println("Valor inválido! Tente novamente.");
                        }

                    } while (urg < 1 || urg > 5);

                    Chamado c = new Chamado(id, bairro, desc, urg);
                    sistema.abrirChamado(c);
                    break;

                case 2:
                    sistema.atenderChamado();
                    break;

                case 3:
                    System.out.print("Digite o ID: ");

                    int idFinal;

                    try {
                        idFinal = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("ID inválido!");
                        sc.nextLine();
                        break;
                    }

                    sistema.finalizarChamado(idFinal);
                    break;

                case 4:
                    sistema.relatorios();
                    sistema.gerarRelatorioArquivo();
                    break;

                case 0:
                    System.out.println("Sistema encerrado.");
                    break;
            }

        } while (op != 0);
    }
}