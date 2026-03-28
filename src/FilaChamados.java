
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
        if (isFull()) {
            System.out.println("Fila cheia");
        } else {
            fim++;
            fila[fim] = chamado;
        }
    }

    public Chamado dequeue() {
        if (isEmpty()) {
            System.out.println("Fila vazia");
            return null;
        } else {
            Chamado removido = fila[inicio];
            inicio++;

            if (inicio > fim) {
                inicio = 0;
                fim = -1;
            }

            return removido;
        }
    }

    public boolean isEmpty() {
        if (inicio > fim) {
            return true;
        }
        
        return false;
    }


    public boolean isFull() {
        if (fim == fila.length - 1) {
            return true;
        }
        
        return false;
    }

}