import java.util.ArrayList;
import java.util.List;

public class SistemaChamados {
    private List<Chamado> historico;
    private List<Chamado> ativos;
    private PilhaEmergencia pilha;
    private FilaChamados fila;

    public SistemaChamados() {
        historico = new ArrayList<>();
        ativos = new ArrayList<>(50);
        pilha = new PilhaEmergencia();
        fila = new FilaChamados();
    }

    public void abrirChamado(Chamado chamado) {
        int nivelUrgencia = chamado.getNivelUrgencia();
        if(nivelUrgencia >= 4) {
            pilha.push(chamado);
        } else {
            fila.enqueue(chamado);
        }

        historico.add(chamado);
    }

    public void atenderChamado() {

    }


}

