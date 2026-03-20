public class Chamado {
    private int id;
    private String bairro;
    private String descricao;
    private int nivelUrgencia;
    private Status status;

    public Chamado(int id, String bairro, String descricao, int nivelUrgencia) {
        this.id = id;
        this.bairro = bairro;
        this.descricao = descricao;
        this.nivelUrgencia = nivelUrgencia;
        this.status = Status.ABERTO;
    }

    public int getId() {
        return id;
    }

    public String getBairro() {
        return bairro;
    }

    public String getDescricao() {
        return  descricao;
    }

    public int getNivelUrgencia() {
        return nivelUrgencia;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}