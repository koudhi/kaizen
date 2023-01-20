import java.math.BigDecimal;

public class Carro extends Automoveis{
    public Carro(String modelo, String marca, BigDecimal valor) {
        super(modelo, marca, valor, 4);
    }
    public void buzina(){
        System.out.println("buzina de carro\n");
    }
}
