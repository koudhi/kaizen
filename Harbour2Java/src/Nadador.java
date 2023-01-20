import javax.swing.*;
import java.util.Scanner;

public class Nadador {
    private String nome;
    private Integer idade;
    private String categoria;

    public Nadador(String nome, Integer idade) {
        this.nome = nome;
        this.idade = idade;

        if( idade < 5){
            categoria = null;
        } else if ( idade < 8 ) {
            categoria = "infantil A";
        } else if ( idade < 11 ) {
            categoria = "infantil B";
        } else if ( idade < 14 ) {
            categoria = "junior A";
        } else if ( idade < 18 ) {
            categoria = "junior B";
        } else {
            categoria = "Senior";
        }
    }
    public static Nadador novoNadador(Scanner  userInput){
        System.out.println("Nome: ");
        String inputNome = userInput.nextLine();
        System.out.println("Idade: ");
        String inputIdade = userInput.nextLine();

        try {
            Integer.parseInt(inputIdade);
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Idade não é um valor numérico");
            return null;
        }
        Integer idade = new Integer(inputIdade);
        if (idade<0) {
            JOptionPane.showMessageDialog(null,"Idade negativa é invalida");
            return null;
        }
        return new Nadador(inputNome,idade);
    }
    public String getCategoria() {
        return categoria;
    }
    public void printInfo(){
        if (categoria == null){
            System.out.println(nome+" está muito novo para participar.");
        } else {
            System.out.println("O nadador " + nome + " está na categoria " + categoria);
        }
    }

    public String getNome() {
        return nome;
    }

    public Integer getIdade() {
        return idade;
    }


}
