import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Pessoa pessoa1 = new Pessoa(LocalDate.of(1990,01,17),"Eric");
        Scanner userInput = new Scanner(System.in);

        while (true) {
            Pessoa pessoa1 = Pessoa.novaPessoa(userInput);
            if (pessoa1 == null) {
                System.exit(1);
            }
            pessoa1.printInfo();
            pessoa1.nascidoEm();
            System.out.printf("Signo   :%s\n", Signo.getSigno(pessoa1.nascimento));
            pessoa1.printIdade();
            pessoa1.proximoAniversario();
        }
    }
}