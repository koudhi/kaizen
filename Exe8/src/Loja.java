import java.math.BigDecimal;
import java.util.Scanner;

public class Loja {
    Produto a = new Produto("a",new BigDecimal(10));
    Produto b = new Produto("b",new BigDecimal("5.25"));
    Produto c = new Produto("c",new BigDecimal(20));
    public BigDecimal novaVenda(Scanner input, BigDecimal credito){
        while (true) {
            BigDecimal total = new BigDecimal(0);
            String parser;

            total = getQuantia(input, credito, total, a);

            if (total == null) continue;

            total = getQuantia(input, credito, total, b);

            if (total == null) continue;

            total = getQuantia(input, credito, total, c);

            if (total == null) continue;

            return total;
        }
    }

    private BigDecimal getQuantia(Scanner input, BigDecimal credito, BigDecimal total, Produto produto) {
        String parser;
        while (true) {
            System.out.println("Digite a quantidade de "+produto.nome);
            parser = input.nextLine();
            if (parser.equals("exit")){
                System.exit(0);
            }
            try {
                BigDecimal quantia = new BigDecimal(parser);
                if (total.add(produto.valor.multiply(quantia)).doubleValue() > credito.doubleValue()) {
                    System.out.println("Valor total excedido");
                    return null;
                } else {
                    total = total.add(produto.valor.multiply(quantia));
                    System.out.println("total = " + total + ", credito = " + credito);
                    return total;
                }
            } catch (NumberFormatException e) {
                System.out.println("quantidade não numérica, digite novamente");
                continue;
            }
        }
    }
}
