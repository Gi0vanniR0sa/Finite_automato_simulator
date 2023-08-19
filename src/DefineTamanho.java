import java.io.FileReader;
import java.io.IOException;

public class DefineTamanho {
    FuncaoTransicao[] transicoes;

    public FuncaoTransicao[] defineTamanho(String jsonLocal) {

        try (FileReader fr = new FileReader(jsonLocal)) {

            int content;
            int contagemTransicoes = 0;

            while ((content = fr.read()) != -1) {

                content = (char) content;

                if (content == '}') {

                    contagemTransicoes++;

                }

            }

            transicoes = new FuncaoTransicao[contagemTransicoes - 1];

        } catch (IOException e) {

            System.out.println("Erro ao ler o arquivo: " + e.getMessage());

        }

        return transicoes;

    }

}

