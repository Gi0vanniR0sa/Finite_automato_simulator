public class FuncaoTransicao {
    private String estadoAtual;
    private String simbolo;
    private String proximoEstado;


    public FuncaoTransicao(){


    }

    public FuncaoTransicao(String estadoAtual, String simbolo, String proximoEstado){

        this.estadoAtual = estadoAtual;
        this.simbolo = simbolo;
        this.proximoEstado = proximoEstado;

    }


    //Métodos get e set dos campos da classe
    public String getEstadoAtual() {

        return estadoAtual;

    }

    public void setEstadoAtual(String estadoAtual) {

        this.estadoAtual = estadoAtual;

    }

    public String getSimbolo() {

        return simbolo;

    }

    public void setSimbolo(String simbolo) {

        this.simbolo = simbolo;

    }

    public String getProximoEstado() {

        return proximoEstado;

    }

    public void setProximoEstado(String proximoEstado) {

        this.proximoEstado = proximoEstado;

    }

}
