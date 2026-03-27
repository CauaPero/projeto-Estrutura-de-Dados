public class FilaChamados {
    private Chamado[] fila;
    private int inicio, fim, tamanho;

    public FilaChamados() {
        fila = new Chamado[40];
        inicio = 0;
        fim = 0;
        tamanho = 0;
    }

    public boolean enqueue(Chamado chamado) {
        if (isFull()) {
            System.out.println("Fila cheia!");
            return false;
        }
        fila[fim] = chamado;
        fim = (fim + 1) % fila.length;
        tamanho++;
        return true;
    }

    public Chamado dequeue() {
        if (isEmpty()) {
            System.out.println("Fila vazia!");
            return null;
        }
        Chamado c = fila[inicio];
        inicio = (inicio + 1) % fila.length;
        tamanho--;
        return c;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public boolean isFull() {
        return tamanho == fila.length;
    }
}