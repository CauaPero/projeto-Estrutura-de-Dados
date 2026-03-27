import java.util.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class SistemaChamados {

    private LinkedList<Chamado> historico;
    private ArrayList<Chamado> ativos;
    private PilhaEmergencia pilha;
    private FilaChamados fila;

    public SistemaChamados() {
        historico = new LinkedList<>();
        ativos = new ArrayList<>(50);
        pilha = new PilhaEmergencia();
        fila = new FilaChamados();
    }

    public boolean idExiste(int id) {
        for (Chamado c : historico) {
            if (c.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void abrirChamado(Chamado chamado) {

        if (idExiste(chamado.getId())) {
            System.out.println("ID já existe!");
            return;
        }

        if (chamado.getNivelUrgencia() >= 4) {
            pilha.push(chamado);
        } else {
            fila.enqueue(chamado);
        }

        historico.add(chamado);
        System.out.println("Chamado registrado com sucesso!");
    }

    // 🔥 ATENDER
    public void atenderChamado() {
        Chamado c;

        if (!pilha.isEmpty()) {
            c = pilha.pop();
        } else {
            c = fila.dequeue();
        }

        if (c == null) {
            System.out.println("Nenhum chamado disponível!");
            return;
        }

        c.setStatus(Status.EM_ATENDIMENTO);
        ativos.add(c);

        System.out.println("Atendendo chamado ID: " + c.getId());
    }

    public void finalizarChamado(int id) {
        Chamado encontrado = null;

        for (Chamado c : ativos) {
            if (c.getId() == id) {
                encontrado = c;
                break;
            }
        }

        if (encontrado == null) {
            System.out.println("Chamado não está em atendimento!");
            return;
        }

        ativos.remove(encontrado);

        for (Chamado c : historico) {
            if (c.getId() == id) {
                c.setStatus(Status.FINALIZADO);
                break;
            }
        }

        System.out.println("Chamado finalizado com sucesso!");
    }

    public void relatorios() {

        ArrayList<BairroRanking> lista = new ArrayList<>();

        for (Chamado c : historico) {
            boolean achou = false;

            for (BairroRanking b : lista) {
                if (b.bairro.equals(c.getBairro())) {
                    b.quantidade++;
                    achou = true;
                    break;
                }
            }

            if (!achou) {
                lista.add(new BairroRanking(c.getBairro(), 1));
            }
        }

        for (int i = 0; i < lista.size() - 1; i++) {
            int max = i;

            for (int j = i + 1; j < lista.size(); j++) {
                if (lista.get(j).quantidade > lista.get(max).quantidade) {
                    max = j;
                }
            }

            BairroRanking temp = lista.get(i);
            lista.set(i, lista.get(max));
            lista.set(max, temp);
        }

        System.out.println("\n===== RANKING DE BAIRROS =====");
        for (BairroRanking b : lista) {
            System.out.println(b.bairro + ": " + b.quantidade);
        }

        int soma = 0;
        for (Chamado c : historico) {
            soma += c.getNivelUrgencia();
        }

        double media = historico.isEmpty() ? 0 : (double) soma / historico.size();
        System.out.println("\nMédia de urgência: " + media);

        System.out.println("\nChamados pendentes:");
        for (Chamado c : historico) {
            if (c.getStatus() != Status.FINALIZADO) {
                System.out.println("ID: " + c.getId() + " | Urgencia: " + c.getNivelUrgencia());
            }
        }
    }

    public void gerarRelatorioArquivo() {

        try {
            FileWriter arquivo = new FileWriter("relatorio.txt");
            PrintWriter gravar = new PrintWriter(arquivo);

            gravar.println("RELATÓRIO DE CHAMADOS");
            gravar.println("========================");

            for (Chamado c : historico) {
                gravar.println("ID: " + c.getId() +
                        " | Bairro: " + c.getBairro() +
                        " | Urgência: " + c.getNivelUrgencia() +
                        " | Status: " + c.getStatus());
            }

            int soma = 0;
            for (Chamado c : historico) {
                soma += c.getNivelUrgencia();
            }

            double media = historico.isEmpty() ? 0 : (double) soma / historico.size();
            gravar.println("\nMédia de urgência: " + media);

            gravar.println("\nChamados pendentes:");
            for (Chamado c : historico) {
                if (c.getStatus() != Status.FINALIZADO) {
                    gravar.println("ID: " + c.getId() + " | URGENCIA: " + c.getNivelUrgencia());
                }
            }

            gravar.close();
            arquivo.close();

            System.out.println("Relatório salvo em arquivo com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro ao gerar arquivo!");
        }
    }
}