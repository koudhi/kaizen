import javax.swing.*;
import java.util.Scanner;

public class Exe11 {
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        while (true){
            Nadador nadador = Nadador.novoNadador(userInput);
            if (nadador == null) continue;
            nadador.printInfo();
            int  continuar = JOptionPane.showConfirmDialog(null,
                    "Cadastrar novo nadador?", "", JOptionPane.YES_NO_OPTION);
            if (continuar == 1){
                break;
            }
        }
    }

}