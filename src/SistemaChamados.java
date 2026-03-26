public class SistemaChamados {
    private PilhaEmergencia pilha;
    private FilaChamados fila;

    public SistemaChamados() {
        pilha = new PilhaEmergencia();
        fila = new FilaChamados();
    }


    public void abrirChamado(Chamado chamado) {
        int nivelUrgencia = chamado.getNivelUrgencia();
        if(nivelUrgencia >= 8) {
            pilha.push(chamado);
        } else {
            fila.enqueue(chamado);
        }
    }
}