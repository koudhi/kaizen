import javax.swing.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        Cliente cliente1 = Cliente.novoCliente(userInput);
        Loja venda = new Loja();

        while (true) {
            BigDecimal totalVenda = venda.novaVenda(userInput, cliente1.getCredito());
            cliente1.useCredito(totalVenda);

            System.out.println("Total: " + totalVenda + " credito restante: " + cliente1.getCredito());
            System.out.printf("Total: %.2f credito restante: %.2f\n", totalVenda, cliente1.getCredito());
            if (JOptionPane.showConfirmDialog(null,"Continuar?","",0 )>0){
                break;
            }
        }

    }
}