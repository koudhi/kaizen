import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Automoveis carro1 = new Carro("Gol","VW",new BigDecimal(20000));
        Scanner scan = new Scanner(System.in);
        carro1.PrintAll();
        carro1.buzina();

        Automoveis moto1 = new Moto("Titan","Honda",new BigDecimal(12000));
        moto1.PrintAll();
        moto1.buzina();

        while (true) {
            String modelo = scan.nextLine();
            String marca = scan.nextLine();
            float valor = scan.nextFloat();
        }


    }
}