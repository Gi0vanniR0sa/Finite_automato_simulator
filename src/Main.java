import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, JsonProcessingException {

        FiniteAutomata fa = new FiniteAutomata();
        fa.RealizaLeitura();
        fa.DefineTransicao();
        run(fa);


    }

    public static void run(FiniteAutomata fa) {


        Scanner sc = new Scanner(System.in);
        int choice;

        do {

            System.out.println("\n" + "Escolha uma opção:" + "\n");
            System.out.println("1. Autômato Determinístico (AFD)");
            System.out.println("2. Autômato Não-Determinístico (AFND)");
            System.out.println("0. Sair" + "\n");
            System.out.print("Opção: ");

            choice = sc.nextInt();


            switch (choice) {


                case 1:

                    fa.AutomatoDeterministico();
                    break;

                case 2:

                    fa.AutomatoNDeterministico();
                    break;

                case 0:

                    System.out.println("Encerrando o programa.");
                    break;

                default:

                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                    break;

            }

        } while (choice != 0);

    }

}
