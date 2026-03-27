public class PilhaEmergencia {
    private int topo;
    private Chamado[] pilha;

    public PilhaEmergencia() {
        pilha = new Chamado[40];
        topo = -1;
    }

    public boolean push(Chamado chamado) {
        if (isFull()) {
            System.out.println("Pilha cheia!");
            return false;
        }
        pilha[++topo] = chamado;
        return true;
    }

    public Chamado pop() {
        if (isEmpty()) {
            System.out.println("Pilha vazia!");
            return null;
        }
        return pilha[topo--];
    }

    public boolean isEmpty() {
        return topo == -1;
    }

    public boolean isFull() {
        return topo == pilha.length - 1;
    }
}