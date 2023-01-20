import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Scanner;

public class Pessoa {
    LocalDate nascimento;
    String nome;
    public void printInfo(){
        System.out.println("Nome          : "+nome+"\nNascimento    : "+nascimento);
    }

    public void printIdade(){
        LocalDate hoje = LocalDate.now();
        Period idade = Period.between(nascimento,hoje);
        int idadeDia = idade.getDays();
        int idadeMes = idade.getMonths();
        int idadeAno = idade.getYears();

        System.out.printf("Idade         : %d ano(s), %d mes(es) e %d dia(s)\n",idadeAno,idadeMes,idadeDia);

    }
    public void nascidoEm(){
        System.out.printf("Nascido em    : %d de %s de %d\n",nascimento.getDayOfMonth(),nascimento.getMonth(),nascimento.getYear());
        System.out.printf("Nascido em uma: %s\n",nascimento.getDayOfWeek());
    }
    public void proximoAniversario(){
        LocalDate hoje = LocalDate.now();
        LocalDate proximo = nascimento.withYear(hoje.getYear());
        boolean aniversario = false;
        if (Period.between(hoje,proximo).isNegative()){
            proximo=proximo.plusYears(1);
        }
        if (Period.between(hoje,proximo).isZero()){
            proximo=proximo.plusYears(1);
            aniversario = true;
        }
        Period ateProximo = Period.between(hoje,proximo);
        System.out.printf("Proximo aniversário em     : %d meses e %d dias\n",ateProximo.getMonths(),ateProximo.getDays());
        System.out.printf("Proximo aniversário em uma : %s\n",proximo.getDayOfWeek());
        System.out.printf("Calendario                 : %s de %d\n",proximo.getMonth(),proximo.getYear());
        if (aniversario){
            System.out.println("Parabens "+nome+" hoje é o seu aniversario");
        }

    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public Pessoa(LocalDate nascimento, String nome) {
        this.nascimento = nascimento;
        this.nome = nome;
    }
    public static Pessoa novaPessoa(Scanner input){
        System.out.println("Digite o nome");
        String nome = input.nextLine();
        if (nome.equalsIgnoreCase("sair")){
            return null;
        }
        LocalDate nascimento = readData(input);
        return new Pessoa(nascimento,nome);

    }

    private static LocalDate readData(Scanner input) {
        String parserDia;
        String parserMes;
        String parserAno;
        LocalDate data;
        int ano=0, mes=0, dia=0;
        System.out.println("Digite o ano de nascimento");
        parserAno = input.nextLine();
        System.out.println("Digite o mes de nascimento");
        parserMes = input.nextLine();
        System.out.println("Digite o dia de nascimento");
        parserDia = input.nextLine();
        try {
            dia = Integer.parseInt(parserDia);
            mes = Integer.parseInt(parserMes);
            ano = Integer.parseInt(parserAno);
        } catch ( NumberFormatException e ){
            System.out.println("Informe um valor numérico");
            readData(input);
        }
        try {
            data = LocalDate.of(ano,mes,dia);
            return data;
        }catch (Exception e){
            System.out.println("Data inválida");
            System.out.println(e);
            return readData(input);
        }
    }
}
