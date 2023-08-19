import java.io.*;
import java.io.IOException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class FiniteAutomata {

    String[] estados;
    String[] alfabeto;
    String estado_inicial;
    String[] estados_finais;
    Limpeza_Caracteres lp = new Limpeza_Caracteres();

    List<FuncaoTransicao> transicoes = new ArrayList<>();
    HashMap<String, String> afd = new HashMap<>();
    HashMap<String, Set<String>> afnd = new HashMap<>();

    CSVCreator csvCreator = new CSVCreator();
    String targetFileStr;

    public void RealizaLeitura(){

        Scanner sc = new Scanner(System.in);
        System.out.print("\n" + "Por favor, informe o caminho do arquivo de entradas (.txt)." + "\n" +
                          "\n" + "O mesmo deve conter (∑,Q,q0,F), sendo os itens do conjunto separados " +
                          "\n" + "por \",\" e cada um dos elementos da 4-upla posicionados por linha: ");
        String sourceFileStr = sc.nextLine();

        File sourceFile = new File(sourceFileStr);
        String sourceFolderStr = sourceFile.getParent();

        boolean success = new File(sourceFolderStr + "\\Out").mkdir();
        targetFileStr = sourceFolderStr + "\\Out\\Output.in.csv";

        try(BufferedReader br = new BufferedReader(new FileReader(sourceFileStr))){

            String output = br.readLine(); //Armazena na String: "q0, q1"
            estados = output.split(","); //Define 2 como o tamanho da String estados pois há
            //duas palavras no total, uma antes da linha e outra depois
            lp.Remocao(estados);

            output = br.readLine();
            alfabeto = output.split(",");
            lp.Remocao(alfabeto);

            output = br.readLine();

            if(output.length() != 2){

                System.out.println("\n" + "Por favor forneça o estado inicial com uma sintaxe válida. Ex: \"qx\".");
                System.out.println("A linha referente ao estado inicial deve conter dois caracteres.");

                System.exit(0);

            }
            else{

                estado_inicial = output;

            }

            output = br.readLine();
            estados_finais = output.split(",");
            lp.Remocao(estados_finais);

        }

        catch(IOException e){

            System.out.println("Erro ao ler o arquivo: " + e.getMessage());

        }


    }

    public void DefineTransicao() throws FileNotFoundException, JsonProcessingException {

        Scanner sc = new Scanner(System.in);

        System.out.print("\n" + "Por favor, forneça o diretório do arquivo de especificação da máquina de entradas (.json): ");
        String jsonLocal = sc.nextLine();

        DefineTamanho dt = new DefineTamanho();
        //transicoes = dt.defineTamanho(jsonLocal);
        //System.out.println(transicoes.length);

        try{

            JSONParser parser = new JSONParser();
            //Realiza a leitura de uma matriz de objetos contida no arquivo .json, logo o casting '(JSONObject)' é
            //necessário já que 'jsonObject' espera um objeto json e não uma matriz
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(jsonLocal));

            //'jsonArray' define um array do tipo JSONArray em que seu tamanho é estabelecido pelo número de objetos presentes
            //em 'jsonObject' ("transitions"), cujo cada célula sua acomoda os campos estadoAtual, simbolo e proximoEstado
            JSONArray jsonArray = (JSONArray) jsonObject.get("transitions");


            //Efetua a leitura de cada célula de 'jsonArray' armazenando em 'transicoesObj' depois salva cada um dos campos
            //fazendo um casting para o tipo String para as variáveis locais estadoAtual, simbolo e proximoEstado, utilizando
            //essas para instanciar um novo objeto FuncaoTransicao e adicionar na ArrayList 'transicoes'
            for(Object transicoesObj : jsonArray){


                String estadoAtual = (String) (((JSONObject) transicoesObj).get("estadoAtual"));
                String simbolo = (String) ((JSONObject) transicoesObj).get("simbolo");
                String proximoEstado = (String) ((JSONObject) transicoesObj).get("proximoEstado");

                FuncaoTransicao transicao = new FuncaoTransicao(estadoAtual, simbolo, proximoEstado);
                transicoes.add(transicao);

            }



        } catch(FileNotFoundException e){

            e.printStackTrace();

        } catch(IOException e){

            e.printStackTrace();

        } catch(ParseException e){

            e.printStackTrace();

        }


    }

    public void AutomatoDeterministico(){


        for(FuncaoTransicao list : transicoes){

            afd.put(list.getEstadoAtual() + "," + list.getSimbolo(), list.getProximoEstado());

        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("\n" + "Informe a linguagem a ser reconhecida: ");
        String entrada = scanner.nextLine();

        String estado_atual = estado_inicial;

        long inicio = System.currentTimeMillis();

        for (char simbolo : entrada.toCharArray()) {

            System.out.println("\n" + "Estado atual: " + estado_atual);
            System.out.println("Entrada atual: " + simbolo);

            estado_atual = afd.get(estado_atual + "," + simbolo);

            System.out.println("Próximo estado: " + estado_atual);

            if (estado_atual == null) {

                System.out.println("O autômato não reconheceu a linguagem");
                break;

            }
        }

        int aceitaRejeita;

        List<String> lista_estados_finais = Arrays.asList(estados_finais);

        if (lista_estados_finais.contains(estado_atual)) {

            System.out.println("\n" + "Reconhecido" + "\n");
            aceitaRejeita = 1;

        }
        else {

            System.out.println("\n" + "Não reconhecido" + "\n");
            aceitaRejeita = 0;

        }

        long fim = System.currentTimeMillis();
        long time = (fim - inicio);
        csvCreator.writteWord(entrada, targetFileStr, aceitaRejeita, time);


    }

    public void AutomatoNDeterministico(){

        Scanner scanner = new Scanner(System.in);

        for(FuncaoTransicao list : transicoes){

            afnd.put(list.getEstadoAtual() + "," + list.getSimbolo(), Collections.singleton(list.getProximoEstado()));

        }

        System.out.print("Informe a linguagem a ser reconhecida: ");
        String entrada = scanner.nextLine();

        //
        Set<String> estados_atuais = new HashSet<>();
        estados_atuais.add(estado_inicial);

        long inicio = System.currentTimeMillis();

        for (char simbolo : entrada.toCharArray()) {

            Set<String> proximos_estados = new HashSet<>();

            for (String estado_atual : estados_atuais) {

                //Multiplos estados de destino alcançados a partir do estado atual e símbolo
                Set<String> estados_destino = afnd.get(estado_atual + "," + simbolo);

                if (estados_destino != null) {

                    proximos_estados.addAll(estados_destino);
                }

            }

            estados_atuais = proximos_estados;

        }

        boolean reconhecido = false;

        List<String> lista_estados_finais = Arrays.asList(estados_finais);

        for (String estado_atual : estados_atuais) {

            if (lista_estados_finais.contains(estado_atual)) {

                reconhecido = true;
                break;

            }
        }

        int aceitaRejeita;

        if (reconhecido) {

            System.out.println("Reconhecido");
            aceitaRejeita = 1;

        } else {

            System.out.println("Não reconhecido");
            aceitaRejeita = 0;

        }

        long fim = System.currentTimeMillis();
        long time = (fim - inicio);
        csvCreator.writteWord(entrada, targetFileStr, aceitaRejeita, time);

    }

}




