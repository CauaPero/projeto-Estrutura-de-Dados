public class FilaChamados {
    private Chamado[] fila;
    private int inicio;
    private int fim;

    public FilaChamados() {
        fila = new Chamado[40];
        inicio = 0;
        fim = -1;     
    }

    public void enqueue(Chamado chamado) {
        if(fim == fila.length - 1) {
            System.out.println("Fila cheia");
        } else {
            fim++;
            fila[fim] = chamado;
        }
    }
}
