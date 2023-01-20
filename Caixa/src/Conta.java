import javax.swing.*;
import java.math.BigDecimal;
import java.util.Scanner;

public class Conta {
    private Integer agencia;
    private Integer numeroConta;
    private String  cliente;
    private BigDecimal saldo;
    private Boolean contaInativa = true;

    public Conta(Integer agencia, Integer numeroConta, String cliente, BigDecimal saldo) {
        if (agencia<0 | agencia> 9999){
            System.out.println("O numero da agencia deve estar entre 0000 e 9999");
            return;
        }
        if (numeroConta<0 | numeroConta> 99999999){
            System.out.println("O numero da conta deve estar entre 00000000 e 99999999");
            return;
        }
        this.agencia = agencia;
        this.numeroConta = numeroConta;
        this.cliente = cliente;
        this.saldo = saldo;
        contaInativa = false;
    }

    public Conta() {
    }

    public void printInfo(){
        System.out.printf("Agencia: %04d\n",agencia);
        System.out.printf("Conta  : %08d\n",numeroConta);
        System.out.printf("Titular: %s\n",cliente);
        System.out.printf("Saldo  : %s\n",saldo);
    }

    public void depositar(BigDecimal deposito){
        if ( deposito.signum() == -1 ) {
            JOptionPane.showMessageDialog(null,"Tentativa de deposito negativo");
            return;
        }
        saldo = saldo.add(deposito);
        System.out.println("Deposito de "+deposito+" realizado.");
        System.out.println("Saldo atual "+saldo);
    }
    public void withdraw(BigDecimal withdraw){
        if ( withdraw.signum() == -1 ) {
            JOptionPane.showMessageDialog(null,"Tentativa de deposito negativo");
            return;
        }
        saldo = saldo.subtract(withdraw);
        System.out.println("Saque de "+withdraw+" realizado.");
        System.out.println("Saldo atual "+saldo);
    }

    public Boolean getInativa() {
        return contaInativa;
    }

    public boolean novaOperacao(Scanner input){
        String[] options = {"Depositar", "Sacar", "Extrato" ,"Sair"};
        int choosenOption = JOptionPane.showOptionDialog(null,"Selecione uma opção","",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        switch (choosenOption){
            case 0:
                this.depositar(setValue());
                break;
            case 1:
                this.withdraw(setValue());
                break;
            case 2:
                this.printInfo();
                break;
            default:
                return false;
        }
        return true;
    }

    private BigDecimal setValue(){
        Scanner input = new Scanner(System.in);
        System.out.println("Valor: ");
        String strValue = input.nextLine();
        try{
            BigDecimal value = new BigDecimal(strValue);
            if ( value.scale() > 2 ){
                System.out.println("Apenas duas casa após a virgula serão consideradas");
                value = value.setScale(2,2);
            }
            return value;
        } catch (NumberFormatException e){
            return setValue();
        }
    }
}
