import java.math.BigDecimal;

public class Automoveis {
    private String modelo;
    private String marca;
    private Integer rodas;
    private BigDecimal valor;

    public Automoveis(String modelo, String marca, BigDecimal valor) {
        this.modelo = modelo;
        this.marca = marca;
        this.valor = valor;
    }
    public Automoveis(String modelo, String marca, BigDecimal valor, Integer rodas) {
        this.modelo = modelo;
        this.marca = marca;
        this.valor = valor;
        this.rodas = rodas;
    }
    public void PrintAll(){
        System.out.println("modelo= "+this.modelo+
                ", Marca= "+this.marca+
                ", Rodas= "+this.rodas+
                ", Valor= "+this.valor);
    }
    public void buzina(){
        System.out.println("Sem buzina");
    }
}
