import java.io.IOException;
import java.util.Scanner;
public class SõneMäng {
    public static void main(String[] args) throws IOException {
        SõneLugeja sõneLugeja = new SõneLugeja("testSõnad.txt");
        Mängija mängija = new Mängija();
        boolean mängKäib = true;

        System.out.println("Mängija peab ära arvama õige sõna. \n1) Suur täht tähistab äraarvatud tähte\n2) Väike täht tähistab tähte, mis vales kohas\n3) \"_\" tähistab et sellist tähte pole sõnas");
        SõneAnalüsaator sõneAnalüsaator = mängija.uusMäng(sõneLugeja.arvatavSõna(), 3);

        while (mängKäib) {
            //LISADA JUURDE (Scanner) sisestatud sõna kontrollimine
            Scanner sisestatudSõna = new Scanner(System.in);
            String proovitavSõna = sisestatudSõna.nextLine();
            sõneAnalüsaator.setKatse(sõneAnalüsaator.getKatse()+1);
            if (sõneAnalüsaator.kontrolliVastust(proovitavSõna)) {
                mängija.setVõite(mängija.getVõite()+1);
                //LISADA JUURDE (Scanner) mäng küsib kas mängija tahab jätkata või mitte, kui ei taha peatab programmi
                //Lisatud
                System.out.println("\nSõna on äraarvatud - "+sõneAnalüsaator.getÕigeVastus());
                System.out.println("Kas soovite jätkata? (ei/jah)");
                Scanner sisend = new Scanner(System.in);
                String otsus = sisend.nextLine();
                if (otsus.equals("ei")){
                    break;
                }
                sõneAnalüsaator = mängija.uusMäng(sõneLugeja.arvatavSõna(), 3);
                continue;
            }

            if (sõneAnalüsaator.getKatse() >= sõneAnalüsaator.getLubatudKatsied()) {
                //LISADA JUURDE (Scanner) mäng küsib kas mängija tahab jätkata või mitte, kui ei taha peatab programmi
                //Lisatud
                System.out.println("Katsete arv on täis. Sõna oli "+sõneAnalüsaator.getÕigeVastus());
                System.out.println("Kas soovite jätkata? (ei/jah)");
                Scanner sisend = new Scanner(System.in);
                String otsus = sisend.nextLine();
                if (otsus.equals("ei")) {
                    mängKäib = false;
                    break;
                }
                if (otsus.equals("jah")){
                    mängija.setKaotusi(mängija.getKaotusi()+1);
                    sõneAnalüsaator = mängija.uusMäng(sõneLugeja.arvatavSõna(), 3);
                }

            }
        }
    }
}
