public class Limpeza_Caracteres {

    public Limpeza_Caracteres(){

    }

    public void Remocao(String[] vetor){

        for(int contador = 0; contador < vetor.length; contador++){

                vetor[contador] = vetor[contador].replace(" ", "");
                vetor[contador] = vetor[contador].replace(",", "");

        }


    }

    public void Remocao(String estado_inicial){

        estado_inicial = estado_inicial.replace(" ","");
        estado_inicial = estado_inicial.replace(" ","");

    }

}
