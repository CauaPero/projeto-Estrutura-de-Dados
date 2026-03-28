import java.util.ArrayList;

public class SistemaChamados {
    private PilhaEmergencia pilha;
    private FilaChamados fila;

    private ArrayList<Chamado> historico;
    private ArrayList<Chamado> ativos;

    public SistemaChamados() {
        pilha = new PilhaEmergencia();
        fila = new FilaChamados();
        historico = new ArrayList<>();
        ativos = new ArrayList<>(50); 
    }

    public void abrirChamado(Chamado chamado) {
        int nivelUrgencia = chamado.getNivelUrgencia();

        if(nivelUrgencia >= 4) {
            pilha.push(chamado);
        } else {
            fila.enqueue(chamado);
        }

        historico.add(chamado);
        System.out.println("Chamado aberto com sucesso!");
    }

    public void atenderChamado() {
        Chamado chamado;

        if (!pilha.isEmpty()) {
            chamado = pilha.pop();
        } else if (!fila.isEmpty()) {
            chamado = fila.dequeue();
        } else {
            System.out.println("Nenhum chamado disponível");
            return;
        }

        chamado.setStatus(Status.EM_ATENDIMENTO);
        ativos.add(chamado);

        System.out.println("Chamado em atendimento: " + chamado.getId());
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
            System.out.println("Chamado não encontrado em atendimento");
        } else {
            ativos.remove(encontrado);

            for (Chamado c : historico) {
                if (c.getId() == id) {
                    c.setStatus(Status.FINALIZADO);
                    break; 
                }
            }

            System.out.println("Chamado finalizado");
        }
    }

    public boolean idExiste(int id) {
        for (Chamado c : historico) {
            if (c.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void relatorios() {
        System.out.println("\n--- RELATÓRIO ---");

        System.out.println("Total de chamados: " + historico.size());
        System.out.println("Chamados em atendimento: " + ativos.size());

        int abertos = 0;
        int finalizados = 0;
        int somaUrgencia = 0;

        for (Chamado c : historico) {
            if (c.getStatus() == Status.ABERTO) {
                abertos++;
            } else if (c.getStatus() == Status.FINALIZADO) {
                finalizados++;
            }

            somaUrgencia += c.getNivelUrgencia();
        }

        System.out.println("Chamados abertos: " + abertos);
        System.out.println("Chamados finalizados: " + finalizados);

        if (historico.size() > 0) {
            double media = (double) somaUrgencia / historico.size();
            System.out.println("Média de urgência: " + media);
        }

        System.out.println("Chamados pendentes: " + abertos);

        relatorioPorBairro();
    }

    private void relatorioPorBairro() {
        ArrayList<String> bairros = new ArrayList<>();
        ArrayList<Integer> contagem = new ArrayList<>();

        //conta bairros
        for (Chamado c : historico) {
            String bairro = c.getBairro();

            int index = bairros.indexOf(bairro);

            if (index == -1) {
                bairros.add(bairro);
                contagem.add(1);
            } else {
                contagem.set(index, contagem.get(index) + 1);
            }
        }

        System.out.println("\n--- Total por bairro ---");
        for (int i = 0; i < bairros.size(); i++) {
            System.out.println(bairros.get(i) + ": " + contagem.get(i));
        }

        for (int i = 0; i < contagem.size() - 1; i++) {
            int maxIndex = i;

            for (int j = i + 1; j < contagem.size(); j++) {
                if (contagem.get(j) > contagem.get(maxIndex)) {
                    maxIndex = j;
                }
            }

            int temp = contagem.get(i);
            contagem.set(i, contagem.get(maxIndex));
            contagem.set(maxIndex, temp);

            String tempBairro = bairros.get(i);
            bairros.set(i, bairros.get(maxIndex));
            bairros.set(maxIndex, tempBairro);
        }

        System.out.println("\n--- Ranking de bairros ---");
        for (int i = 0; i < bairros.size(); i++) {
            System.out.println((i + 1) + "º - " + bairros.get(i) + ": " + contagem.get(i));
        }
    }

}