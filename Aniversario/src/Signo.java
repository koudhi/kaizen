import java.time.LocalDate;

public class Signo {

    public static String getSigno(LocalDate nascimento){
        int dia = nascimento.getDayOfMonth();
        int mes = nascimento.getMonthValue();
        String signo;

        if ( 21+31*1< dia+31*mes && dia+31*mes < 19+31*2){
            signo = "Aquário";
        } else if ( 19+31*2< dia+31*mes && dia+31*mes < 21+31*3){
            signo = "Peixes";
        } else if ( 21+31*3< dia+31*mes && dia+31*mes < 21+31*4){
            signo = "Áries";
        } else if ( 21+31*4< dia+31*mes && dia+31*mes < 21+31*5){
            signo = "Touro";
        } else if ( 21+31*5< dia+31*mes && dia+31*mes < 21+31*6){
            signo = "Gêmeos";
        } else if ( 21+31*6< dia+31*mes && dia+31*mes < 21+31*7){
            signo = "Câncer";
        } else if ( 23+31*7< dia+31*mes && dia+31*mes < 23+31*8){
            signo = "Leão";
        } else if ( 23+31*8< dia+31*mes && dia+31*mes < 23+31*9){
            signo = "Virgem";
        } else if ( 23+31*9< dia+31*mes && dia+31*mes < 23+31*10){
            signo = "Libra";
        } else if ( 23+31*10< dia+31*mes && dia+31*mes < 22+31*11){
            signo = "Escorpião";
        } else if ( 22+31*11< dia+31*mes && dia+31*mes < 21+31*12){
            signo = "Sagitário";
        } else {
            signo = "Capricornio";
        }

        return signo;
    }
}
