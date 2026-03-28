import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        SistemaChamados sistema = new SistemaChamados();
        Scanner entrada = new Scanner(System.in);

        int opcao = -1;

        do {
            System.out.println("\n---------------------------------------------------");
            System.out.println("--- SISTEMA DE ATENDIMENTO DE EMERGÊNCIA URBANA ---");
            System.out.println("---------------------------------------------------");
            System.out.println("1 - Novo Chamado");
            System.out.println("2 - Atender Chamado");
            System.out.println("3 - Finalizar Chamado");
            System.out.println("4 - Relatórios");
            System.out.println("0 - Sair");
            System.out.println("---------------------------------------------------");

            System.out.print("Digite o número da opção desejada: ");

            String entradaOpcao = entrada.next();

            try {
                opcao = Integer.parseInt(entradaOpcao);

                if (opcao < 0 || opcao > 4) {
                    System.out.println("Opção inválida! Digite novamente.");
                    opcao = -1;
                }

            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite novamente.");
                opcao = -1;
            }

            switch (opcao) {

                case 1:
                    System.out.println("\n--- NOVO CHAMADO ---");

                    int id = -1;
                    boolean idValido = false;

                    while (!idValido) {
                        System.out.print("ID: ");

                        try {
                            id = entrada.nextInt();
                            entrada.nextLine();

                            if (id < 0) {
                                System.out.println("ID inválido! Digite positivo.");
                            } else if (sistema.idExiste(id)) {
                                System.out.println("ID já está em uso! Digite outro.");
                            } else {
                                idValido = true;
                            }

                        } catch (Exception e) {
                            System.out.println("Digite apenas números!");
                            entrada.nextLine();
                        }
                    }

                    System.out.print("Bairro: ");
                    String bairro = entrada.nextLine().toUpperCase().trim();

                    while (bairro.isEmpty()) {
                        System.out.println("Bairro não pode ser vazio!");
                        System.out.print("Bairro: ");
                        bairro = entrada.nextLine().toUpperCase().trim();
                    }

                    System.out.print("Descrição: ");
                    String desc = entrada.nextLine();

                    while (desc.isEmpty()) {
                        System.out.println("Descrição não pode ser vazia!");
                        System.out.print("Descrição: ");
                        desc = entrada.nextLine();
                    }

                    int urg = -1;
                    boolean urgValida = false;

                    while (!urgValida) {

                        System.out.println("\n--- TABELA DE URGÊNCIA ---");
                        System.out.println("Tipo: Nível de Urgência");
                        System.out.println("Comum: 1, 2 ou 3");
                        System.out.println("Emergência: 4 ou 5");

                        System.out.print("\nDigite o nível de urgência (1 a 5): ");

                        try {
                            urg = entrada.nextInt();

                            if (urg >= 1 && urg <= 5) {
                                urgValida = true;
                            } else {
                                System.out.println("Valor inválido! Tente novamente.");
                            }

                        } catch (Exception e) {
                            System.out.println("Digite apenas números!");
                            entrada.nextLine();
                        }
                    }

                    Chamado c = new Chamado(id, bairro, desc, urg);
                    sistema.abrirChamado(c);
                    break;

                case 2:
                    sistema.atenderChamado();
                    break;

                case 3:
                    System.out.print("Digite o ID: ");

                    int idFinal = -1;
                    boolean idValidoFinal = false;

                    while (!idValidoFinal) {
                        try {
                            idFinal = entrada.nextInt();

                            if (idFinal < 0) {
                                System.out.println("ID inválido! Digite novamente!");
                            } else {
                                idValidoFinal = true;
                            }

                        } catch (Exception e) {
                            System.out.println("Digite apenas números!");
                            entrada.nextLine();
                        }
                    }

                    sistema.finalizarChamado(idFinal);
                    break;

                case 4:
                    sistema.relatorios();
                    break;

                case 0:
                    System.out.println("Sistema encerrado");
                    break;
            }

        } while (opcao != 0);

        entrada.close();
    }
}