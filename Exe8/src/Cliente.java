import java.math.BigDecimal;
import java.util.Scanner;

public class Cliente {
    private String nome;
    private BigDecimal credito;

    public void setCredito(BigDecimal credito) {
        this.credito = credito;
    }
    public void useCredito(BigDecimal gasto){
        this.credito = this.credito.subtract(gasto);
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getCredito() {
        return credito;
    }

    public Cliente(String nome, BigDecimal credito) {
        this.nome = nome;
        this.credito = credito;
    }

    public static Cliente novoCliente(Scanner input){
        System.out.println("Nome:");
        String nome = input.nextLine();
        BigDecimal credito;

        while (true) {
            System.out.println("Credito:");
            try {
                credito = new BigDecimal(input.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Valor de credito não numerico");
            }
        }
        return new Cliente(nome,credito);
    }
}
