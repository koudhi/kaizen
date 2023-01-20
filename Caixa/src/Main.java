import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        Conta conta1 = new Conta();
        while (conta1.getInativa()) {
            //conta1 = new Conta(1, 78954, "Eric Omori", new BigDecimal("325.25"));
            conta1 = Banco.criarConta();
        }
        boolean continuar = true;
        while(continuar) {
            continuar = Banco.novaOperacao(conta1);
        }
    }
}