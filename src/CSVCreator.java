import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CSVCreator {

    String route = "C:\\Users\\Giovanni R\\Desktop\\Caminho_Teste1\\Out\\output.csv";


    public void writteWord(String entrada, String route, int aceitaRejeita, long time){

        criaArquivo(route);

        try{

            List<String> linhasExistentes = LinhaExistentes(route);
            SimpleDateFormat sdf = new SimpleDateFormat("s.SSS");
            String secondToMilissegundos = sdf.format(new Date(time));

            String linha = entrada + ";" + aceitaRejeita + ";" + secondToMilissegundos + " (milissegundos)";
            linhasExistentes.add(linha);

            String todasAsLinhas = unicaLinha(linhasExistentes);
            FileWriter arquivo = new FileWriter(route);
            PrintWriter pw = new PrintWriter(arquivo);
            pw.write(todasAsLinhas);
            pw.close();

            System.out.println("Dados adicionados com sucesso ao arquivo CSV.");

        }
        catch(IOException e){

            System.out.println("Erro na escrita do arquivo : " + route);

        }

    }

    public String unicaLinha(List<String> linhasExistentes){

        StringBuilder unicaLinha = new StringBuilder();

        for(String linhaLida : linhasExistentes){

            unicaLinha.append(linhaLida).append("\n");

        }

        return unicaLinha.toString();

    }

    public void criaArquivo(String route){

        try{

            File arquivo = new File(route);
            arquivo.createNewFile();

        }
        catch(IOException e){

            System.out.println("Erro ao gerar o arquivo! : " + e);

        }

    }

    public List<String> LinhaExistentes(String route){

        List<String> conteudo = new ArrayList<String>();

        try{

            Path path = Paths.get(route);
            conteudo = Files.readAllLines(path);


        }
        catch(IOException e){

            System.out.println("Erro ao realizar a leitura dentro do arquivo : " + e);

        }

        return conteudo;

    }

}
