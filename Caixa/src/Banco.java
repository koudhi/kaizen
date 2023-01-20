import javax.swing.*;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Banco {

    public static Conta criarConta(){
        System.out.println("Criar nova conta");
        Scanner input = new Scanner(System.in);

        Integer agencia = scanAgencia(input);
        Integer numeroConta = scanConta(input);
        System.out.println("Digite o nome do Titular");
        String cliente = input.nextLine();
        BigDecimal saldo = scanSaldo(input);
        String message = String.format("Agencia: %4d%nConta: %8d%nTitular: %s%nSaldo %.2f ",agencia,numeroConta,cliente,saldo);
        int option = JOptionPane.showConfirmDialog(null,message,"Confirmar criação de conta",JOptionPane.YES_NO_OPTION);

        return (option==0)?new Conta(agencia,numeroConta,cliente,saldo):criarConta();
    }

    public static int scanAgencia(Scanner input){
        System.out.println("Agencia:");
        String parser = input.nextLine();
        try{
            int agencia = Integer.parseInt(parser);
            return agencia;
        } catch (NumberFormatException | InputMismatchException e){
            System.out.println("valor invalido");
            return scanAgencia(input);
        }
    }

    public static int scanConta(Scanner input){
        System.out.println("Conta:");
        String parser = JOptionPane.showInputDialog("Conta");
        //String parser = input.nextLine();
        try{
            int agencia = Integer.parseInt(parser);
            return agencia;
        } catch (NumberFormatException | InputMismatchException e){
            System.out.println("valor invalido");
            return scanAgencia(input);
        }
    }

    public static BigDecimal scanSaldo(Scanner input){
        System.out.println("Saldo atual:");
        String parser = input.nextLine();
        try{
            BigDecimal saldo = new BigDecimal(parser);
            return  saldo;
        } catch (Exception e){
            return scanSaldo(input);
        }
    }

    public static BigDecimal depositar(BigDecimal deposito, BigDecimal saldo){
        if ( deposito.signum() == -1 ) {
            JOptionPane.showMessageDialog(null,"Tentativa de deposito negativo");
            return saldo;
        }
        saldo = saldo.add(deposito);
        System.out.println("Deposito de "+deposito+" realizado.");
        System.out.println("Saldo atual "+saldo);
        return saldo;
    }
    public static BigDecimal withdraw(BigDecimal withdraw, BigDecimal saldo){
        if ( withdraw.signum() == -1 ) {
            JOptionPane.showMessageDialog(null,"Tentativa de deposito negativo");
            return saldo;
        }
        saldo = saldo.subtract(withdraw);
        System.out.println("Saque de "+withdraw+" realizado.");
        System.out.println("Saldo atual "+saldo);
        return saldo;
    }

    public static boolean novaOperacao(Conta conta){
        String[] options = {"Depositar", "Sacar", "Extrato" ,"Sair"};
        int choosenOption = JOptionPane.showOptionDialog(null,"Selecione uma opção","",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        switch (choosenOption){
            case 0:
                this.depositar(setValue());
                break;
            case 1:
                conta.withdraw(setValue());
                break;
            case 2:
                conta.printInfo();
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
