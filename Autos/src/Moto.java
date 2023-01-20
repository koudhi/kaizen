import java.math.BigDecimal;

public class Moto extends Automoveis{
    public Moto(String modelo, String marca, BigDecimal valor) {
        super(modelo, marca, valor, 2);
    }

    @Override
    public void buzina() {
        System.out.println("Buzina de moto");
    }
}
