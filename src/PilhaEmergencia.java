public class PilhaEmergencia {
    private int topo;
    private Chamado[] pilha;

    public PilhaEmergencia() {
        this.pilha = new Chamado[40];
        this.topo = -1;
    }

    public void push(Chamado chamado) {
        if (isFull()) {
            System.out.println("Limite de chamados atingido!");
        } else {
            topo++;
            pilha[topo] = chamado;
        }
    }

    public Chamado pop() {
        if(isEmpty()) {
            System.out.println("Sem chamados para remover");
            return null;
        } else {
            Chamado removido = pilha[topo];
            topo--;
            return removido;
        }
    }

    public boolean isEmpty() {
        if (topo == -1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFull() {
        if (topo == pilha.length - 1) {
            return true;
        } else {
            return false;
        }
    }

}
